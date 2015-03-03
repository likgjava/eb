<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">

//创建FCK
createFckedit = function(content){
	$("#FCKContent_content").text(content)
	oFCKeditor = new FCKeditor('FCKContent_content','80%','500','Template');
	oFCKeditor.ReplaceTextarea();
}

// 根据获得模板的属性
function getFtlTemplateProperty(ftlType){
	$('#ftlTemplateProperty').empty();
	if(ftlType == ""){
		$('#ftlTemplateProperty').append('<li>请选择模板类型</li>');
		return;
	}
	$.getJSON($('#initPath').val()+'/FtlTemplatePropertyController.do?method=getObjectQuery&queryColumns=objId,propertyTitle,propertyValue',{"ftlType":ftlType,"order":"sort"},function(json){
		if(json.result.length > 0){
			$.each(json.result,function(i,n){
				$('#ftlTemplateProperty').append('<li><a href="javascritp:void(0)">'+n.propertyTitle+'</a><span class="hidden" style="display:none">'+n.propertyValue+'</span></li>');
			})
			
			// 插入标签
			$('#ftlTemplateProperty').find('a').click(function(){
				var oEditor = FCKeditorAPI.GetInstance('FCKContent_content') ;
				var _property = $(this).parent().find('span').html();
			     oEditor.InsertHtml(_property);
			});
		}else{
			$('#ftlTemplateProperty').append('<li>未检索到模板属性</li>');
		}
	});
	
}

$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val($('#initPath').val()+"/FtlTemplateController.do")
	
	// 新增模板
	$('#addFtlTemplateBtn').click(function(){
		$('#conBody').loadPage($("#initPath").val()+"/FtlTemplateController.do?method=toCreateOrUpdate");
	});
	if($('#ftlTemplateId').val()!=""){
		$.getJSON($('#initPath').val()+'/FtlTemplateController.do?method=createOrUpdate',{objId:$('#ftlTemplateId').val()},function(json){
			jsonObjectToForm('ftlTemplateForm', json.ftlTemplate);
			createFckedit(json.ftlTemplate.content);
			getFtlTemplateProperty(json.ftlTemplate.ftlType)
		});

	}else{
		createFckedit("");
	}

	// 根据模板类型获取模板的属性
	$('#ftlType').change(function(){
		getFtlTemplateProperty($(this).find('option:selected').val());
	});

	// 返回
	$('#returnFtlTemplateListBtn').click(function(){
		$('#conBody').loadPage($('#returnUrl').val());
	})
	
	// 保存模板
	$('#saveFtlTemplateBtn').click(function(){
		if(!$('#ftlTemplateForm').valid()){alert('请正确填写表单!');return;}
		$('#saveFtlTemplateBtn').attr("disabled",true);
		FCKeditor_BackValue(); 
		var template = formToJsonObject('ftlTemplateForm');

		if(!$('#ftlTemplateForm').valid()){
			alert('请正确填写表单!');
			$('#saveFtlTemplateBtn').attr("disabled",false);
			return false;
		}
		$.getJSON($('#initPath').val()+'/FtlTemplateController.do?method=saveFtlTemplate', template, function(json){
			if(json.result)alert(json.result);if(json.failure){$('#saveFtlTemplateBtn').attr("disabled",false);return};
    		if(json.success){
				alert('保存成功!');
				$('#conBody').loadPage($('#returnUrl').val());
        	}
		});
	});

	// 查看模板
	$('#viewFtlTemplateBtn').click(function(){
		FCKeditor_BackValue(); 
		$.epsDialog({
			title:"模板内容",
			height:400, 
			width:800, 
			url:$("#initPath").val()+'/view/agreement/ftl/ftl_template_detil.jsp',
			afterLoad: function(){ 
				$('#ftlTemplateDetil').html($('#FCKContent_content').text())
			}
		})
		return false;
	})
	
	$('#ftlName').blur(function(){
		if($(this).val() != ""){
			$.getJSON($('#initPath').val()+'/FtlTemplateController.do?method=isUnique', {"objId":$('#ftlTemplateForm').find('input[name=objId]').val(),"ftlName":native2ascii($('#ftlName').val())}, function(json){
	    		if(json.isUnique != 'true'){
	    			$('#ftlName').next('span').html("该模板名称已经存在！").removeClass('eleRight').addClass('eleWarning');
	        	}else{
	        		$('#ftlName').next('span').html('');
		        }
			});
		}
	});

	//表单验证
	$("#ftlTemplateForm").validate();	
});

</script>
<div class="formLayout">
<input type="hidden" id="ftlTemplateId" value="${param.objId }" />
<form action="" id="ftlTemplateForm">

<input type="hidden" name="fileName" id="fileName" />
<input type="hidden" name="objId" />
<ul>
	<li><label>模板名称 ：</label> <input type="text" class="required" name="ftlName" id="ftlName" /><span class="eleRequired">*</span></li>
	<li><label>模板类型 ：</label> <html:select selectedValue="0" styleClass="required" id="ftlType" name="ftlType" code="ftlTemplateType"><html:option value="">-请选择类型-</html:option></html:select><span class="eleRequired">*</span></li>
</ul>
<textarea id="FCKContent_content" name="content" cols="60" rows="8" ></textarea>
<div style="float:right;width:18%">
<ul id="ftlTemplateProperty">
<li>请选择模板类型</li>
</ul>
</div>
<div class="conOperation">
	<button type="button" id="saveFtlTemplateBtn"><span>保存</span></button>
	<button type="button" id="viewFtlTemplateBtn" tabindex="19"><span>查看</span></button>
	<button type="button" id="returnFtlTemplateListBtn" tabindex="19"><span>返回</span></button>
</div>
</form>
</div>