<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="noticeForm">
	<input type="hidden" id="noticeId" name="objId" value="${notice.objId}">
	<div class="formLayout formPa">
		<ul>
				<li>
			      	<label >标题：</label>
			      	<span>${notice.noticeTitle}</span>
			      </li>
				<li>
			      	<label >结果：</label>
			      	<span>${notice.noticeTypeCn}</span>
			      </li>
		</ul>
		
	</div>
		<div class="formLayout formPa">
			<h5><span>内容</span></h5>
			<span style="height:auto;">${notice.contents}</span>
		</div>
			<div class="conOperation">
				<button id="toPrint" type="button" tabindex="19"><span>打印预览</span></button>
				<button id="noticeReturn" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
 			</div>
</form>
<script language="javascript">
//关闭
$("#noticeReturn").click(function(){
	$("#epsDialogClose").click();
});
//打印预览
$('#toPrint').click(function(){
	var noticeId = $("#noticeId").val();
	$.getJSON($('#initPath').val()+'/NoticeController.do?method=toNoticePrintPage&noticeId='+noticeId,function(json){
	    if(json.result)alert(json.result);if(json.failure)return;
	    window.open($('#initPath').val()+'/view/es/common/RequestContentPrint.jsp');
	});	
});

</script>