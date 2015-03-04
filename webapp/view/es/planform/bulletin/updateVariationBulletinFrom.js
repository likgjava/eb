
var updateVariationBulletinFrom={};

updateVariationBulletinFrom.reflushPage = function(){ //刷新页面数据
	if($("#fromType").val()=='fromDesk'){
		alert("保存成功!")
		$('#epsDialogClose').click();
	}else{
		if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			$('#epsDialogClose').click();
		  }
	}
	
}
$(document).ready(function(){
	updateVariationBulletinFrom.oFCKeditor = new FCKeditor('bullContents','100%','240','Template','') ;
	$('#bullContents').text($("#bulletin").text());
	updateVariationBulletinFrom.oFCKeditor.ReplaceTextarea();
	//保存
	$('#bulletinSave').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#variationForm').valid()){alert('请正确填写表单!');return;}
		if(window.confirm("确认保存?"))
		{
			$.getJSON($('#initPath').val()+'/VariationBulletinController.do?method=saveUpdateVariationBulletin', formToJsonObject('variationForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				updateVariationBulletinFrom.reflushPage();
			});
		}
	});
	//提交
	$('#bulletinSubmit').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#variationForm').valid()){alert('请正确填写表单!');return;}
		if(window.confirm("确认提交?"))
		{
		$.getJSON($('#initPath').val()+'/VariationBulletinController.do?method=submitUpdateVariationBulletin', formToJsonObject('variationForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			updateVariationBulletinFrom.reflushPage();
		});
	 }
	});
	
	$('#returnBtn').click(function(){//返回
		$('#epsDialogCloseReload').click();
	});
});
