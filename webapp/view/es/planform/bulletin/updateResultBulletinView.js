
var bulletinForm={};


$(document).ready(function(){
	/**
	 * 初始化关联项目Id
	 */
	$("*[name=project.objId]").val($("#projectId").val());
	
	 if($('#objId').val()!=''){
	    	$.getJSON($('#initPath').val()+'/BulletinController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
	    		jsonObjectToForm('bulletinForm', json.bulletin);
	    	});
	    }
	 
//打印预览
 $('#toPrint').click(function(){
	 	FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		var jsonObject = formToJsonObject('bulletinForm');
		$.getJSON($('#initPath').val()+'/BulletinController.do?method=toBulletinPrintPageByType',jsonObject,function(json){
		    if(json.result)alert(json.result);if(json.failure)return;
		    window.open($('#initPath').val()+'/view/es/common/RequestContentPrint.jsp');
		});
 });
	 
	$('#bulletinForm').validate();
	
	bulletinForm.oFCKeditor = new FCKeditor('bullContents','100%','240','Template','') ;
	$('#bullContents').text($("#bulletin").text());
	bulletinForm.oFCKeditor.ReplaceTextarea();
	//保存
	$('#bulletinSave').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#bulletinForm').valid()){alert('请正确填写表单!');return;}
		if(window.confirm("确认保存?"))
		{
			$.getJSON($('#initPath').val()+'/ResultBulletinController.do?method=saveUpdateResultBulletin', formToJsonObject('bulletinForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
					planTemplateTask.clickMethod($("#projectTaskId").val()+"");
					planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				  } else {
					$("#myDesktop").click();
				}
			});
		}
	});
	//提交
	$('#bulletinSubmit').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#bulletinForm').valid()){alert('请正确填写表单!');return;}
		if(window.confirm("确认提交?"))
		{
		$.getJSON($('#initPath').val()+'/ResultBulletinController.do?method=submitUpdateResultBulletin', formToJsonObject('bulletinForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			  } else {
				$("#myDesktop").click();
			}
		});
		}
	});
});
//返回
function bullentinReturn(){
	var auditStatus = $('#auditStatus').val();
	var bullType = $('#bullType').val();
	$('#conBody').loadPage($('#initPath').val()+'/BulletinController.do?method=getMoreForBulletinList&auditStatus='+auditStatus+'&bullType='+bullType);

}


//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=01');
});
