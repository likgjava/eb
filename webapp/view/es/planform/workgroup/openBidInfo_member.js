var openBidInfoForm ={};


$(document).ready(function(){
	openBidInfoForm.reflushPage = function(){
		if($("#fromType").val()=='fromDesk'){
			$('#openBidMember').find(".epsDialogClose").click();
			$('#workMemberDiv').empty().loadPage($('#initPath').val()+'/WorkGroupController.do?method=toOpenBidWorkGroupForProject&fromType=fromDesk&groupType=03&projectId='+$("#projectId").val());
		}else{
			$('#epsDialogClose').click();
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		}
	}
	$("#isLeader").click(function(){
		if($("#isLeader").attr("checked")){
			$("#isLeader").val("01");
			$("#isLeaderDiv").show();
		}else{
			$("#isLeader").val("00");
			$("#isLeaderDiv").hide();
		}
	})
	//选择人员类型
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
			$("#linkerPhoneId").val(data.mobile);
			$("#emailId").val(data.email);
			$("#empId").val(data.objId);
		}
	});
	
	$('#closeBtn').click(function(){  
		openBidInfoForm.reflushPage();
	});
	
	$('#saveBtnId').click(function(){
		if(!$('#workgMemberFormId').valid()){alert('请正确填写表单!');return;}
		var projectId = $('#subOrProjectId1').val();
		var groupType = $('#groupType').val();
		var workGroupId = $('#workGroupId').val();
		var empId = $('#empId').val();
		if(null == $('#workgmCertId').val() || "" == $('#workgmCertId').val()){
			$("#isUploadFile").val("false");
		}else{
			$("#isUploadFile").val("true");
		}

		var jsonObj = formToJsonObject('workgMemberFormId');
		if(!$("#isLeader").attr("checked")){
			jsonObj.workgmIsLeader ="";
		}
		$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=saveWorkgMember&workGroupId='+workGroupId+'&projectId='+projectId+'&groupType='+groupType+'&empId='+empId,jsonObj, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			openBidInfoForm.reflushPage();
		});
	});
});