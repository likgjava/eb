
var bulletinForm={};

$(document).ready(function(){
	
	bulletinForm.oFCKeditor = new FCKeditor('bullContents','100%','240','Template','') ;
	$('#bullContents').text($("#bulletin").text());
	bulletinForm.oFCKeditor.ReplaceTextarea();
	//保存
	$('#bulletinSave').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#variationForm').valid()){alert('请正确填写表单!');return;}
		if(window.confirm("确认保存?"))
		{
			$.getJSON($('#initPath').val()+'/ChangebulletinController.do?method=saveChangeBulletin', formToJsonObject('variationForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				 $('#returnBtn').click();
			});
		}
	});
	//提交
	$('#bulletinSubmit').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#variationForm').valid()){alert('请正确填写表单!');return;}
		if(window.confirm("确认提交?"))
		{
		$.getJSON($('#initPath').val()+'/ChangebulletinController.do?method=submitChangeBulletin', formToJsonObject('variationForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			 $('#returnBtn').click();
		});
	 }
	});
	
	$('#returnBtn').click(function(){//返回
		$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/bulletin/allprojectList.jsp');
	});
});
