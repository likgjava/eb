
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
	 
	$('#bulletinForm').validate();
	
	bulletinForm.oFCKeditor = new FCKeditor('bullContents','100%','240','Template','') ;
	$('#bullContents').text($("#bulletin").text());
	bulletinForm.oFCKeditor.ReplaceTextarea();
	//保存
	$('#bulletinSave').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#bulletinForm').valid()){alert('请正确填写表单!');return;}
		if(window.confirm("确认提交?"))
		{
			$.getJSON($('#initPath').val()+'/ResultBulletinController.do?method=saveResultBulletin', formToJsonObject('bulletinForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				alert("保存成功！");
				$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/bulletin/viewBulletinList.jsp');	
			});
		}
	});
	//提交
	$('#bulletinSubmit').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#bulletinForm').valid()){alert('请正确填写表单!');return;}
		if(window.confirm("确认提交?"))
		{
		$.getJSON($('#initPath').val()+'/ResultBulletinController.do?method=submitResultBulletin', formToJsonObject('bulletinForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("提交成功！");
			$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/bulletin/viewBulletinList.jsp');	
		});
		}
	});
	//codeHighlight.init();
});
