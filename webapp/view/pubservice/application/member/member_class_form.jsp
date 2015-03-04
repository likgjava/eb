<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" >
var MemberClassFrom = {};

$(document).ready(function(){
	$("#memberClassForm").validate();
	
	//保存
	$("#memberClassSave").click(function(){
		if(!$('#memberClassForm').valid()){return;}

		$('#memberClassSave').attr('disabled','disabled');
		var url = $('#initPath').val()+"/MemberClassController.do?method=saveMemberClass";
		$('#memberClassForm').ajaxSubmit({
			url:url,
			dataType:'json',
			success:function(json){
				if("success" == json.returnMessage){
					alert("保存成功！");
					$("#memberClassReturn").click();
				}else{
					alert(json.returnMessage);
					$('#memberClassSave').removeAttr('disabled');
				}
			},
			error:function(msg){
				$('#memberClassSave').removeAttr('disabled');
				alert(JSON.stringify(msg));
			}
		});
	});

	//返回
	$("#memberClassReturn").click(function(){
		$('#conBody').loadPage('view/pubservice/application/member/member_class_list.jsp');
	});

	//预览图片
	$("#pictureFile").change(function(){
		var filePath = $("#pictureFile").val();
		var fileName = filePath.replace(/.+\\([^\\]+)/,'$1');
		var i = fileName.lastIndexOf('.');       	 //从右边开始找第一个'.'
		var len = fileName.length;                	 //取得总长度
		var str = fileName.substring(len,i+1);    	 //取得后缀名
		var exName = "PNG,BMP,JPG,GIF";       		 //允许的后缀名
		var k = exName.indexOf(str.toUpperCase());	 //转成大写后判断
		if(k==-1){                                	 //没有符合的
		    alert("上传文件错误！只能上传"+exName);
			this.value="";
		}else{
			$("#newPreview").find('img').attr("src",preViewPic(this));
		}
	});
})
</script>

<form:form id="memberClassForm" method="post" modelAttribute="memberClass" enctype="multipart/form-data"> 
	<input type="hidden" id="objId" name="objId" value="${memberClass.objId }"/>
	
	<div class="formLayout form2Pa">
	    <h4><span>添加会员级别</span></h4>
	    <div class="k1">
			<div class="img_250_2" id="newPreview">
				<c:choose>
					<c:when test="${memberClass.memberClassPic==null}">
						<img id="view"  src="<%=request.getContextPath()%>/view/resource/skin/smallscale/img/expert_add.gif" width="200px" height="200px"></img>
					</c:when>
					<c:otherwise>
						<img src="<c:url value="AttachmentController.do?method=showImg&objId=${memberClass.memberClassPic}" />" width="200px" height="200px">
					</c:otherwise>
				</c:choose>
			</div>
			<input name="pictureFile" type="file" id="pictureFile" size="22"/>
		</div>
		<ul>
			<li class="fullLine">
				<label>会员级别名称：</label>
				<input id="memberClassName" name="memberClassName" value="${memberClass.memberClassName}" size="30" class="required">
				<span class="eleRequired">*</span> 
			</li>	
			<li class="fullLine">
				<label>会员级别：</label>
				<html:select id="memberClassNum" name="memberClassNum" selectedValue="${memberClass.memberClassNum}" code="pubservice.application.service.memberClassNum"></html:select>
			</li>
			<li class="fullLine">
				<label>入会最小时长（月）：</label>
				<input type="text" id="minAge" name="minAge" class="digits" value="${memberClass.minAge}" maxlength="3"/>
			</li>
			<li class="fullLine">
				<label>入会最大时长（月）：</label>
				<input type="text" id="maxAge" name="maxAge" class="digits" value="${memberClass.maxAge}" maxlength="3"/>
			</li>
			<li class="fullLine">
				<label>缴费最小金额（元）：</label>
				<input type="text" id="minFee" name="minFee" class="money" value="${memberClass.minFee}"/>
			</li>
			<li class="fullLine">
				<label>缴费最大金额（元）：</label>
				<input type="text" id="maxFee" name="maxFee" class="money" value="${memberClass.maxFee}"/>
			</li>
			<li class="formTextarea">
				<label>级别描述：</label>
				<textarea name="memberClassDesc" id="memberClassDesc" maxlength="1000">${memberClass.memberClassDesc}</textarea>
				<span class="eleRequired">*</span>
			</li>
		</ul>
	</div>
	
	<div class="conOperation center">
		<button id="memberClassSave" type="button" class="enterBtn"><span><spring:message code="globe.save"/></span></button>
		<button id="memberClassReturn" type="button" class="enterBtn"><span><spring:message code="globe.return"/></span></button>
	</div>
</form:form>