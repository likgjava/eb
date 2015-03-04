var LeaderGroupMemberList ={};


$(document).ready(function(){
	LeaderGroupMemberList.reflushPage = function(){  //刷新页面
		if($("#fromType").val()=='fromDesk'){
			$('#workMemberDiv').empty().loadPage($('#initPath').val()+'/WorkGroupController.do?method=toOpenBidWorkGroupForProject&fromType=fromDesk&groupType=03&projectId='+$("#projectId").val());
		}else{
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		}
	}
 
	
	LeaderGroupMemberList.addWorkgMember = function(workgmType){//新增
		$.epsDialog({
	        title:"组建领导小组",
	        url:$("#initPath").val()+"/WorkgMemberController.do?method=toUpdateLeaderGroupMember&workGroupId="+$('#workGroupId').val()+"&projectId="+$('#subOrProjectId').val(),
	        width: 770,
	        height: 250,
	        isReload: false,
	        onClose: function(){
	       	}
		});
	}
	
	LeaderGroupMemberList.delWorkMember = function(objId){//删除
		if(window.confirm("确定要删除吗?")){
		  $.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=remove',{'objId':objId},function(json){			
			if(json.result)alert(json.result);if(json.failure)return;
			LeaderGroupMemberList.reflushPage();
		  });
		}
	}
	
	LeaderGroupMemberList.updateWorkMember = function(objId){//修改
		$.epsDialog({
	        title:"修改领导小组信息",
	        url:$("#initPath").val()+"/WorkgMemberController.do?method=toUpdateLeaderGroupMember&workgMemberId="+objId,
	        width: 770,
	        height: 250,
	        isReload: false,
	        onClose: function(){
	       	}
		});
	}
	
	LeaderGroupMemberList.viewWorkMember = function(objId){//查看莫个人的信息
		$.epsDialog({
	        title:"开标人信息",
	        url:$("#initPath").val()+"/WorkgMemberController.do?method=toViewWorkgMember&workgMemberId="+objId,
	        width: 770,
	        height: 150,
	        isReload: false,
	        onClose: function(){
	       	}
		});
	}
	$("#saveBtn").click(function(){ //完成组建领导小组
		var jsonObj = {};
		jsonObj.projectId = $("#subOrProjectId").val();
		var a = $("a[id='WorkGroupController.do?method=toOpenBidWorkGroupForProject&groupType=03&projectId=']").attr("projecttaskid");
		jsonObj.projecPlanId = a;
		$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=saveWorkgMemberDesion',jsonObj,function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			LeaderGroupMemberList.reflushPage();
		});	
	});
	
	$("#printBtn").click(function(){//打印领导小组
		var projectId = $("#subOrProjectId").val();
	    var groupType = $("#groupType").val();
	    window.open($("#initPath").val()+'/WorkgMemberController.do?method=printLeaderGroupMember&projectId='+projectId+'&groupType='+groupType);
	});
});