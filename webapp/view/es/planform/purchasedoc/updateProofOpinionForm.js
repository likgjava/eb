
var proofOpinionForm={};

$(document).ready(function(){
	//附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text()
	});
	
	$('#proofOpinionForm').validate();
	$("#updateTime").epsDatepicker();
	$("#createTime").epsDatepicker();
	//返回
	$('#proofOpinionReturn').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
	//提交
	$('#proofOpinionSave').click(function(){
		if(!$('#proofOpinionForm').valid()){
			alert('请正确填写表单!');
			return false;
		}
		var proofOpinion = formToJsonObject('proofOpinionForm');
		proofOpinion.attachRelaId=$("input[name=attachRelaId]").val();
		proofOpinion.opinion = $("#opinion").val();
		$.getJSON($('#initPath').val()+'/ProofOpinionController.do?method=updateProofOpinion', proofOpinion, function(json){
			if(json.failure){
				alert(json.result);
				return false;
			}
			if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null ){
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			 } 
			$('#epsDialogCloseNoReload').click();
		});
	});

});
