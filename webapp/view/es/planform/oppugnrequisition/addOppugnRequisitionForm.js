
var oppugnRequisitionForm={};

oppugnRequisitionForm.checkOppuContent = function(obj){
	if($(obj).attr("value").length>248){
		$(obj).val($(obj).attr("value").substr(0,248));
		alert('质疑内容不能超过248个字');
		return false;
	} else {
		$(obj).css("color","green");
		return true;
	}
}

$(document).ready(function(){
	$("input[name='project.objId']").val($("#projectId").val());
	var projectId=$("input[name='project.objId']").val();
	$('#oppugnRequisitionForm').validate();
     			$("#createTime").epsDatepicker();
     			$("#updateTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/OppugnRequisitionController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('oppugnRequisitionForm', json.oppugnRequisition);
    	});
    }
	//返回
	$('#oppugnRequisitionReturn').click(function(){
		$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqSupplierList.jsp');
	});
	
	//附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text()
	});
	
	//保存
	$('#oppugnRequisitionSave').click(function(){
		if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
		if(!$('#oppugnRequisitionForm').valid()){alert('请正确填写表单!');return;}
		if(!oppugnRequisitionForm.checkOppuContent($('#oppuContent'))){return false;}
		$('#useStatus').val('00');
		$.getJSON($('#initPath').val()+'/OppugnRequisitionController.do?method=saveOppugnRequisition&projectId='+projectId, formToJsonObject('oppugnRequisitionForm'), function(json){
			if(json.result){alert(json.result);}else if(json.failure){return;}
			alert('保存成功!');
			$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqSupplierList.jsp');
		});
	});
	//提交
	$('#oppugnRequisitionSubmit').click(function(){
		if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
		$('#useStatus').val('01');
		if(!$('#oppugnRequisitionForm').valid()){alert('请正确填写表单!');return;}
		if(!oppugnRequisitionForm.checkOppuContent($('#oppuContent'))){return false;}
		$.getJSON($('#initPath').val()+'/OppugnRequisitionController.do?method=submitOppugnRequisition&projectId='+projectId, formToJsonObject('oppugnRequisitionForm'), function(json){
			if(json.result){alert(json.result);}else if(json.failure){return;}
			alert('提交成功!');
			$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqSupplierList.jsp');
		});
	});
	
});
