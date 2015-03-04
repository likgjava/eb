/*
 * 执行平台，编辑需求列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var purRequirementDraft={};

$(document).ready(function(){

	$('#purRequirementForm').validate();
	
	//加载附件
//	$("#requireFile").empty().loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=requireAtt');
	
	//附件
	$('#requireFile').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'requireFile',//存放关联id的属性名
		attachRelaId:$("#requireFile").text()
	});
	
	//提交
	$('#purRequirementSave').click(function(){
		if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
		if(!$('#purRequirementForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/PurRequirementController.do?method=saveOrUpdatePurRequirement&taskPlanId='+$("#taskPlanId").val(), formToJsonObject('purRequirementForm'), function(json){
			$("#objId").val(json.purreq.objId);
			alert("保存成功！");
		    $("#preqEntryList").empty().loadPage($('#initPath').val()+"/view/es/planform/requirement/preqEntryList.jsp");
		    $("#preqEntryList").show();
		   
		});
	});
});
