var openBidInfoList ={};


$(document).ready(function(){
	openBidInfoList.reflushPage = function(){  //刷新页面
		if($("#fromType").val()=='fromDesk'){
			$('#workMemberDiv').empty().loadPage($('#initPath').val()+'/WorkGroupController.do?method=toOpenBidWorkGroupForProject&fromType=fromDesk&groupType=03&projectId='+$("#projectId").val());
		}else{
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		}
	}
	
	openBidInfoList.getEmpIds = function(attr,objId){//获取员工Id
		var empIds = new Array();
		$(attr).each(function(){
			if (objId!=null&&objId!=""&&objId!=undefined) {//判断是否有当前用户
				if ($(this).val()!=objId) {//添加不是当前用户的Id
					empIds.push($(this).val());
				}
			}else{
				empIds.push($(this).val());
			}
		});
		return empIds.toString();
	}
	
	openBidInfoList.addWorkgMember = function(workgmType){//新增
		var empIds = '';
		var t = '';
		if (workgmType=='00') {//招标单位代表
			empIds = openBidInfoList.getEmpIds('input[name=buyerEmpId]',null);
			t="招标方代表"
		} else if (workgmType=='02') {//经办人代表
			empIds = openBidInfoList.getEmpIds('input[name=agentEmpId]',null);
			t="招标代理方代表"
		} else if (workgmType=='03') {//监管人代表
			empIds = openBidInfoList.getEmpIds('input[name=superviseEmpId]',null);
			t="监察人员"
		}else{
			t="专家";
		}
		$.epsDialog({
	        title:t,
	        id:"openBidMember",
	        url:$("#initPath").val()+"/WorkgMemberController.do?method=toAddWorkgMember&t="+escape(encodeURIComponent(t))+"&empIds="+empIds+"&workGroupId="+$('#workGroupId').val()+"&workgmType="+workgmType+"&projectId="+$('#subOrProjectId').val()+"&groupType="+$('#groupType').val(),
	        width: 770,
	        height: 150,
	        isReload: false,
	        onClose: function(){
	       	}
		});
	}
	
	openBidInfoList.delWorkMember = function(objId){//删除
		if(window.confirm("确定要删除吗?")){
		  $.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=remove',{'objId':objId},function(json){			
			if(json.result)alert(json.result);if(json.failure)return;
			openBidInfoList.reflushPage();
		  });
		}
	}
	
	openBidInfoList.updateWorkMember = function(objId,workgmType,empId){//修改
		var empIds = '';
		if (workgmType=='00') {//招标单位代表
			empIds = openBidInfoList.getEmpIds('input[name=buyerEmpId]',empId);
		} else if (workgmType=='02') {//经办人代表
			empIds = openBidInfoList.getEmpIds('input[name=agentEmpId]',empId);
		} else if (workgmType=='03') {//监管人代表
			empIds = openBidInfoList.getEmpIds('input[name=superviseEmpId]',empId);
		}
		$.epsDialog({
	        title:"修改开标人信息",
	        url:$("#initPath").val()+"/WorkgMemberController.do?method=toUpdateWorkgMember&empIds="+empIds+"&workgmType="+workgmType+"&workgMemberId="+objId+"&projectId="+$('#subOrProjectId').val(),
	        id:"updateOpenBidMember",
	        width: 770,
	        height: 150,
	        isReload: false,
	        onClose: function(){
	       	}
		});
	}
	
	openBidInfoList.viewWorkMember = function(objId){//查看莫个人的信息
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
	$("#saveBtn").click(function(){ //完成组建开标小组
		var zbf = $("#zbf tr").length  ;    //招标方代表
		var zj = $("#zj tr").length ;  //专家
		var dlf = $("#dlf tr").length;  //代理方
		var jc = $("#jc tr").length;    //监察人员
		var flag = false;
		var mesg = "";
		var jsonObj = {};
		jsonObj.projectId = $("#subOrProjectId").val();
		jsonObj.projecPlanId = $("a[id='WorkGroupController.do?method=toOpenBidWorkGroupForProject&groupType=03&projectId=']").attr("projecttaskid");
		
		if(zbf==1){
			mesg += '招标方代表  ';
			flag = true;
		}
		if(zj==1){
			mesg += '专家  ';
			flag = true;
		}
		if(dlf==1){
			mesg += '招标代理方代表  ';
			flag = true;
		}
		if(jc==1){
			mesg += '监察人员  ';
			flag = true;
		}
		if(flag){
			if(window.confirm(mesg+"还未录入人员，确定要完成吗？ ")){
				$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=saveWorkgMemberDesion',jsonObj,function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					openBidInfoList.reflushPage();
				});
			}
		}else{
			$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=saveWorkgMemberDesion',jsonObj,function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				openBidInfoList.reflushPage();
			});
		}
	})
});