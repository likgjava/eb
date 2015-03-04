/*
 * 执行平台，编辑需求列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */	
var purRequirementForm={};

$(document).ready(function(){
	$('#purRequirementForm').validate();
	
	//展示需求条目列表
	if($("#objId").val()!='')
	{
		$.getJSON($('#initPath').val()+'/PurRequirementController.do?method=createOrUpdate',{objId:$('#objId').val()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('purRequirementForm', json.purRequirement);
    		
    		//加载附件
//    		$("#requireFile").empty().loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=requireAtt');
    		//附件
    		$('#requireFile').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
    			defineSelf:'requireFile',//存放关联id的属性名
    			attachRelaId:$("#requireFile").text()
    		});
    	});
		
		$("#preqEntryList").empty().loadPage($('#initPath').val()+"/view/es/planform/requirement/preqEntryList.jsp");
	}
	
	//提交
	$('#purRequirementSave').click(function(){
		if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
		if(!$('#purRequirementForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/PurRequirementController.do?method=saveOrUpdatePurRequirement', formToJsonObject('purRequirementForm'), function(json){
			alert("保存成功！");
		});
	});
});
