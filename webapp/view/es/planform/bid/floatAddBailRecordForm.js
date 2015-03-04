
var bailRecordForm={};
bailRecordForm.downloadFile1 = function(attachRelaId){	
	    	$('#downLoadView1').empty().loadPage($('#initPath').val()+"/view/es/inquiryprice/inqpdoc/docDownLoad.jsp?attachRelaId="+attachRelaId);
}
bailRecordForm.downloadFile2 = function(attachRelaId){	
	    $('#downLoadView2').empty().loadPage($('#initPath').val()+"/view/es/inquiryprice/inqpdoc/docDownLoad.jsp?attachRelaId="+attachRelaId);
}
bailRecordForm.getFileName1= function(attachRelaId){
	 $.getJSON($('#initPath').val()+'/DosBuyRecordController.do?method=getAttachmentByobjId&attachmentId='+attachRelaId,function(json){
		 if(json.result)alert(json.result);if(json.failure)return;
		 $("#fileName1").text(json.attachment.viewName);
		 alert($("#fileName1").text());
		});
}
bailRecordForm.getFileName2= function(attachRelaId){
	 $.getJSON($('#initPath').val()+'/DosBuyRecordController.do?method=getAttachmentByobjId&attachmentId='+attachRelaId,function(json){
		 if(json.result)alert(json.result);if(json.failure)return;
		 $("#fileName2").text(json.attachment.viewName);
		});
}
$(document).ready(function(){
	$('#bailRecordForm').validate();
	if($("#renderAtt").val()!='')
	{
		bailRecordForm.getFileName1($("#renderAtt").val());
	}
	if($("#returnedAtt").val()!='')
	{
		bailRecordForm.getFileName2($("#returnedAtt").val());
	}
    $("#renderTime").epsDatepicker();
    $("#returnedTime1").epsDatepicker();
    $("#createTime").epsDatepicker();
    $("#returnedTime").val(null);
	//返回
	$('#bailRecordReturn').click(function(){
		$('#epsDialogClose').click();
		return;
	});
	//保存
	$('#bailRecordSave').click(function(){
		var signUprecordId =$("#signUprecordId").val();
		if(null == $('#attachFile1').val() || "" == $('#attachFile1').val()){
			$("#isUploadFile1").val("false");
		}else{
			$("#isUploadFile1").val("true");
		}
		if(null == $('#attachFile2').val() || "" == $('#attachFile2').val()){
			$("#isUploadFile2").val("false");
		}else{
			$("#isUploadFile2").val("true");
		}
		if(isNaN($("#ballMoney").val())){alert('保证金金额必须为数字！');return;}
		if(!$('#bailRecordForm').valid()){alert('请正确填写表单!');return;}
		if($("#returnedTime1").val()!=null&&$("#returnedTime1").val()!=''){$("#returnedTime").val($("#returnedTime1").val());}
		if($("#returnedTime1").val()!=null&&$("#returnedTime1").val()!=''&&$("#renderTime").val()>=$("#returnedTime").val())
			{
				alert("缴纳时间应在退回时间之后！");
				return false;
			}
		var a = $("#SubobjId").val();
		$('#bailRecordForm').ajaxSubmit({
			url:$('#initPath').val()+"/BailRecordController.do?method=addBailRecord&signUprecordId="+signUprecordId,
			dataType:'json',
			success:function(json){
				if(json.failure){alert(json.result);return;}
				$("#epsDialogCloseReload").click();
				$("#bailR").empty().loadPage($('#initPath').val()+'/BailRecordController.do?method=toapplyRecordForPack&projectId='+a);return;
			},
			error:function(msg){
				alert(msg);
			}
		});
	});

});
