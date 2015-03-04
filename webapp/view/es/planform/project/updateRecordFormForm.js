$(document).ready(function(){
	$("#a1").click(function(){
		window.open($("#a1").attr("href"));
	})
	$("#a2").click(function(){
		window.open($("#a2").attr("href"));
	})
	$("#a3").click(function(){
		window.open($("#a3").attr("href"));
	})
	$("#recordFormReturn").click(function(){
			$('#recordFormDetail').loadPage($('#initPath').val()+"/RecordFormController.do?method=toRecordFormDetail&projectId="+$("#projectId").val());
	})
	$("#recordFormSave").click(function(){
		if(null == $('#tendererProve').val() || "" == $('#tendererProve').val()){
			$("#isUploadFile1").val("false");
		}else{
			$("#isUploadFile1").val("true");
		}
		if(null == $('#projApproval').val() || "" == $('#projApproval').val()){
			$("#isUploadFile2").val("false");
		}else{
			$("#isUploadFile2").val("true");
		}
		if(null == $('#fundsProof').val() || "" == $('#fundsProof').val()){
			$("#isUploadFile3").val("false");
		}else{
			$("#isUploadFile3").val("true");
		}
		$('#recordFormForm').ajaxSubmit({
			url:$('#initPath').val()+"/RecordFormController.do?method=updateRecordForm",
			dataType:'json',
			success:function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#recordFormDetail').loadPage($('#initPath').val()+"/RecordFormController.do?method=toRecordFormDetail&projectId="+$("#projectId").val());
			},
			error:function(msg){
				alert(msg);
			}
		});
	})

})
