
var updateResultPublicityForm={};

$(document).ready(function(){
	updateResultPublicityForm.reflushPage = function(){ //刷新页面数据
		if($("#fromType").val()=='fromDesk'){
			alert("保存成功!")
			$('#epsDialogClose').click();
		}else{
			if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			  } else {
				$("#myDesktop").click();
			}
		}
		
	}
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
	 
	$('#bulletinForm').validate();
	
	updateResultPublicityForm.oFCKeditor = new FCKeditor('bullContents','100%','240','Template','') ;
	$('#bullContents').text($("#bulletin").text());
	updateResultPublicityForm.oFCKeditor.ReplaceTextarea();
	//保存
	$('#bulletinSave').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#bulletinForm').valid()){alert('请正确填写表单!');return;}
		if(window.confirm("确认保存?")){
			$.getJSON($('#initPath').val()+'/ResultPublicityController.do?method=saveUpdateResultPublicity', formToJsonObject('bulletinForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				updateResultPublicityForm.reflushPage();
			});
		}
	});
	//提交
	$('#bulletinSubmit').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#bulletinForm').valid()){alert('请正确填写表单!');return;}
		if(window.confirm("确认提交?")){
		$.getJSON($('#initPath').val()+'/ResultPublicityController.do?method=submitUpdateResultPublicity', formToJsonObject('bulletinForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			updateResultPublicityForm.reflushPage();
		});
	 }
	});
});
