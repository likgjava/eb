var updateOpenBidInfoForm ={};

updateOpenBidInfoForm.reflushPage = function(){
	if($("#fromType").val()=='fromDesk'){
		$('#updateOpenBidMember').find(".epsDialogClose").click();
		$('#workMemberDiv').empty().loadPage($('#initPath').val()+'/WorkGroupController.do?method=toOpenBidWorkGroupForProject&fromType=fromDesk&groupType=03&projectId='+$("#projectId").val());
	}else{
		$('#epsDialogClose').click();
		planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	}
}

$(document).ready(function(){
	$("#isLeader").click(function(){
		if($("#isLeader").attr("checked")){
			$("#isLeader").val("01");
			$("#isLeaderDiv").show();
		}else{
			$("#isLeader").val("00");
			$("#isLeaderDiv").hide();
		}
	})
	//选择品目
	$("#workgmName").autocomplete(
		$('#initPath').val() + '/UserApiController.do?method=getEmpListByCompanyId&empIds='+$('#empIds').val()+'&companyId='+$('#orgId').val(), 
		{
			matchColumn:'workgmName',//作为查询显示, 被选中之后匹配的列
			extraParams:{},
			mustMatch: true,
			formatItem: function(data, i, total) {
				return data.name;
			},
			formatMatch: function(data, i, total) {
				return data.name;
			},
			formatResult: function(data) {
				return data.name;
			}
		}
	).result(function(event,data,formatted){
		if(data){
			$("#workgmName").val(data.name);
//			$("#workgmAccount").val(data.);
			$("#linkerPhoneId").val(data.mobile);
			$("#emailId").val(data.email);
			$("#empId").val(data.objId);
		}
	});
	
	$('#closeBtn').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
	
	$('#saveBtnId').click(function(){
		if(!$('#workgMemberFormId').valid()){alert('请正确填写表单!');return;}
		var jsonObj = formToJsonObject('workgMemberFormId');
		if(!$("#isLeader").attr("checked")){
			jsonObj.workgmIsLeader ="";
		}
		$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=updateWorkgMember&empId='+$('#empId').val(),jsonObj, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			updateOpenBidInfoForm.reflushPage();
		});
	});
});