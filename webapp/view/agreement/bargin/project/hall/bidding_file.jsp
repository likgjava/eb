<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="BiddingFileForm" name="BiddingFileForm" method="post">
    <input type="hidden" name="relId" id="relId" value="${param.relId}"/>
		
	<div id="bargainFileDiv">
	</div>
	
    <div class="conOperation">
			<button id="close" type="button"><span>关闭</span></button>
	</div>
</form>

<script>
var BiddingFileForm={};

$(document).ready(function(){ 
	$('#bargainFileDiv').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=bargainFile&isSelect=yes&attachRelaId='+$('#relId').val());

	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
});
</script>