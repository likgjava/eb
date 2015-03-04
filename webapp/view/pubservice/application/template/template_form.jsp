<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="templateForm" method="post" enctype="multipart/form-data">
	<!-- 非管理员新增时，记录机构id -->
	<c:if test="${dotTemplate.objId == null}">
		<input type="hidden" name="org.objId" value="${orgInfoId}" />
	</c:if>
	
	<div class="formLayout form2Pa">
		<h4 class="title"><span>范本信息</span><span class="eleRequired">（带*号的为必填项）</span></h4>
		<ul>
			<li class="fullLine">
				<label>范本名称：</label>
				<input type="text" id="templateName" name="templateName" class="required" maxlength="200" style="width: 300px;" value="${dotTemplate.templateName}"/>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>选择区域：</label>
				<input type="hidden" id="districtCode" name="districtCode" value="${dotTemplate.districtCode}" />
				<input type="text" id="districtName" name="districtName" class="sysicon siSearch required" readonly="readonly" style="width: 283px;" value="${dotTemplate.districtName}" />
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>选择品目：</label> 
				<input type="hidden" id="categoryCode" name="categoryCode" value="${dotTemplate.categoryCode}" />
				<input type="text" id="categoryName" name="categoryName" class="sysicon siSearch required" readonly="readonly" style="width: 283px;" value="${dotTemplate.categoryName}" />
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>范本类型：</label>
				<html:select id="templateType" name="templateType" selectedValue="${dotTemplate.templateType}" code="pubservice.application.template.templateType"></html:select>
			</li>
			<li class="fullLine">
				<label>是否共享：</label>
				<input type="radio" name="isShare" value="1" <c:if test="${dotTemplate.isShare==null || dotTemplate.isShare}">checked="checked"</c:if> />是&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="isShare" value="0" <c:if test="${dotTemplate.isShare!=null && !dotTemplate.isShare}">checked="checked"</c:if> />否
				（如果选择共享，则其他用户可以下载此范本。）
			</li>
			<li class="formTextarea">
				<label>简要描述：</label>
				<textarea id="templateDesc" name="templateDesc" maxLength="250">${dotTemplate.templateDesc}</textarea>
			</li>
			<li class="fullLine">
				<label>范本文件：</label>
				<input type="hidden" id="templateFile" name="templateFile" value="${dotTemplate.templateFile}" />
				<input type="file" name="uploadFile" id="uploadFile" class="required" <c:if test="${dotTemplate.templateFile != null}">disabled="disabled"</c:if> />
				<span class="eleRequired">*</span>
				<c:if test="${dotTemplate.templateFile != null}">
				<span>
					原附件:[<a href="AttachmentController.do?method=downLoadFile&objId=${dotTemplate.templateFile}">${attachmentViewName}</a>]
					&nbsp;&nbsp;<a href="javascript:void(0);" id="reUploadFile">重新上传</a>
				</span>
				</c:if>
			</li>
		</ul>
	</div>
	<div class="conOperation">
		<button id="saveTemplateBtn" type="button"><span>保存</span></button>
		<button name="historyBackBtn" type="button"><span>返回</span></button>
	</div>
</form>
	
<script>
var TemplateForm = {};

//保存
TemplateForm.saveTemplate = function(){
	//表单验证
	if(!$("#templateForm").valid()){ alert("请正确填写范本信息!");return; }
	$('#saveTemplateBtn').attr('disabled', true);
	
	$('#templateForm').ajaxSubmit({
		url: $('#initPath').val()+"/DotTemplateController.do?method=saveDotTemplate",
		dataType:'json',
		success:function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			$('button[name=historyBackBtn]').click();
		},
		error:function(msg){
			alert(JSON.stringify(msg));
			$('#saveTemplateBtn').attr('disabled', false);
		}
	});
}

$(document).ready(function(){
	//表单验证
	$("#templateForm").validate();

	//选择区域
	$('#districtName').click(function(){
		$.epsDialog({
	        title:'选择区域',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=districtCode&NAMES=districtName&className=District&action=listTop&checkStatus=true&isCheckBox=true',
	        onClose: function(){
				//补全区域名称
    			if($('#districtCode').val()!=null && $('#districtCode').val()!=''){
        			var districtCodes = $('#districtCode').val().split(',');
        			$('#districtName').val('');
        			for(var i=0; i<districtCodes.length; i++){
		    			$.getJSON($("#initPath").val()+"/DistrictController.do?method=getObjectQuery&queryColumns=objId,name,parent.name,parent.parent.name",{"objId":districtCodes[i]},function(json){
			    			if($('#districtName').val()!=''){
								$('#districtName').val($('#districtName').val()+',');
			    			}
			    			var districtName = json.result[0]['parent.parent.name']+json.result[0]['parent.name']+json.result[0]['name'];
			    			$('#districtName').val($('#districtName').val()+districtName);
		    			});
        			}

    			}
	      	}
	    });
	});
	
	//选择品目
	$('#categoryName').click(function(){
		$.epsDialog({
			title:'选择品目',
			url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=categoryCode&NAMES=categoryName&className=PurCategory&action=listTop&checkStatus=true&isCheckBox=true',
			onClose: function(){
			}
		});
	});
	
	//保存范本信息
	$('#saveTemplateBtn').click(function(){
		TemplateForm.saveTemplate();
	});
	
	//重新上传附件
	$('#reUploadFile').click(function(){
		$('#uploadFile').attr('disabled', false);
		$('#templateFile').val('');
		$(this).parent().hide();
	});
});
</script>