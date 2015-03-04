
var bidEvaluationReportForm={};

$(document).ready(function(){
	bidEvaluationReportForm.oFCKeditor = new FCKeditor('bullContents','100%','240','Template','') ;
	$('#bullContents').text($("#bulletin").text());
	bidEvaluationReportForm.oFCKeditor.ReplaceTextarea();
	//保存
	$('#bulletinSave').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#bidEvaluationReportForm').valid()){alert('请正确填写表单!');return;}
			if(window.confirm("确认保存?")) {
			$.getJSON($('#initPath').val()+'/EvaSellerRecordController.do?method=saveBidEvaluationReport&subProjId='+$("#subProjId").val(), formToJsonObject('bidEvaluationReportForm'), function(json){
				if(json.failure)return;
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				if($("#projectTaskId") && $("#projectTaskId").val() != ""){
					planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				}
			});
		 }
		
	});
	
	
	//提交
	$('#bulletinSubmit').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#bidEvaluationReportForm').valid()){alert('请正确填写表单!');return;}
			if(window.confirm("确认提交?")) {
				$.getJSON($('#initPath').val()+'/EvaSellerRecordController.do?method=saveSubmitBidEvaluationReport&subProjId='+$("#subProjId").val(), formToJsonObject('bidEvaluationReportForm'), function(json){
					if(json.failure)return;
					planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
					if($("#projectTaskId") && $("#projectTaskId").val() != ""){
						planTemplateTask.clickMethod($("#projectTaskId").val()+"");
					}
				});
			}
		
	});
	
	//打印预览
	$('#toPrint').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		var jsonObject = formToJsonObject('bidEvaluationReportForm');
		$.getJSON($('#initPath').val()+'/EvaSellerRecordController.do?method=toEvaSellerRecordPrint',jsonObject,function(json){
		    if(json.result)alert(json.result);if(json.failure)return;
		    window.open($('#initPath').val()+'/view/es/common/RequestContentPrint.jsp');
		});	
	});
});
