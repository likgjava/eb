
var noticeForm={};

$(document).ready(function(){
	$("input[name='project.objId']").val($("#projectId").val());
	var projectId=$("input[name='project.objId']").val();
	$('#noticeForm').validate();
     			$("#createTime").epsDatepicker();
     			$("#updateTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/NoticeController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('noticeForm', json.notice);
    	});
    }
	//返回
	$('#noticeReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/NoticeController.do");
	});
	
	noticeForm.oFCKeditor = new FCKeditor('noticeContent','100%','300','Template','') ;
	noticeForm.oFCKeditor.ReplaceTextarea();
	//提交
	$('#noticeSave').click(function(){
		FCKeditor_BackValue(); 	//此方法很重要， 在AJAX提交之前把FCK里的东西往FORM里放.
		if(!$('#noticeForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/NoticeController.do?method=saveNotice&projectId='+projectId, formToJsonObject('noticeForm'), function(json){
			if(json.result){
				if(json.result=='success'){
					checkProjectMenu('menu_notice');
				}else{
					alert(json.result);
				}	
			}
			if(json.failure)return;
		});
	});
	codeHighlight.init();
});
