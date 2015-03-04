
var oppugnRequisitionForm={};
$(document).ready(function(){
	//附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text(),
		readOnly:'yes'
	});
	//返回
	$('#oppugnRequisitionReturn').click(function(){
		var projectId = $("#projectId").val();
		if($("#type").val()=="buyer") {//招标单位
			$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqBuyerList.jsp?projectId='+projectId);
		}else{//招标中心
			$("#projectDoDiv").empty().loadPage($('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqAgencyList.jsp?projectId='+projectId);
		}
	});
});