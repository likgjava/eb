/*
 * 执行平台，采购申报书明细表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanSubForm={};

$(document).ready(function(){

    if($('#planSubId').val()!=''){
    	//获取申报书条目信息
    	$.getJSON($('#initPath').val()+'/TaskPlanSubController.do?method=createOrUpdate',{objId:$('#planSubId').val(),includedProperties:'purchase'},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			json2Object('taskPlanSubForm', json.taskPlanSub);
			var money = $("#totalPrice").html();
			$("#totalPrice").html(yToWany(money));
    	});
    	//根据申报书条目ID获取需求条目信息
    	$.getJSON($('#initPath').val()+'/PreqEntryController.do?method=getPreqEntryByTaskPlanSubId',{taskPlanSubId:$('#planSubId').val(),includedProperties:'purchase'},function(json){
    		json2Object('preqEntryForm', json.preqEntry);
			//加载附件
			if(json.preqEntry && json.preqEntry.attachRelaId){
				//$("#attachRelaId").empty().loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=attachRelaId&isSelect=yes&attachRelaId='+json.preqEntry.attachRelaId);
				//附件
				$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
					defineSelf:'attachRelaId',//存放关联id的属性名
					attachRelaId:json.preqEntry.attachRelaId,
					readOnly:'yes'
				});
			}
    	});
    }
    
    
	//关闭
	$('#taskPlanSubClose').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
});
