<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
var recommendProjectForm = {};

//表单提交
recommendProjectForm.submit = function(){
	var url = $('#initPath').val()+'/RecommendProjectController.do?method=saveRecommendProject';
 	$('#recommendProjectForm').ajaxSubmit({
 		url:url,
 		dataType:'json',
 		success:function(json){
 			alert("推荐成功！");
 			$('#recommendSuccess').val('1'); //标记为推荐成功
 			$('.epsDialogClose').trigger('click');
 		},
 		error:function(msg){
 			alert(JSON.stringify(msg));
 		}
 	});
 }
 
$(document).ready(function(){
	//确定
	$("#OK").click(function(){
		if($("#pictureFile").val() == "") {alert("请选择上传的图片！");return ; }

		recommendProjectForm.submit();
	})
});
</script>


<form id="recommendProjectForm" name="recommendProjectForm" method="post" enctype="multipart/form-data">
	<input type="hidden" id="projectIds"  name="projectIds" value="<c:out value="${param.projectIds}"/>"/>

	<div>
		<div id="newPreview">
			<jsp:include page="/view/srplatform/upload/img_upload_load.jsp">
				<jsp:param name="maxWidth" value="150" />
				<jsp:param name="maxHeight" value="150" />
				<jsp:param name="propertieName" value="pictureFile" />
				<jsp:param name="nopicPath" value="AttachmentController.do?method=showImg" />
			</jsp:include>
		</div>
	</div>
	<div style="margin: 10px;">
		<label><strong>推荐理由：</strong></label>
		<textarea id="recommendReasonInput" name="reason" style="width: 365px; height: 120px"></textarea>
	</div>
	
	<div class="conOperation">
		<button id="OK" type="button" tabindex="18"><span>确定</span></button>
	</div>
</form>
