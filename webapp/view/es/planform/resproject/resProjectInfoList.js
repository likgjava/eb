var resProjectList = {};
resProjectList.loadInit = function(){
	$('#resProjectListGrid').flexOptions({params:{}});
	return true;
}

function getAgentMessage(resProjectId){
	$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=searchProjectListDljg&serviceName=searchProjectListForAgentManager&resProjectId='+resProjectId);			
}

resProjectList.add = function(resProjectId){
	$('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=toResProjectInfoForm&objId="+resProjectId);
}

//查询条件过滤
resProjectList.before=function(){
	var option={"order":"createTime",
			"order_flag":"true",
			"createUser.objId":PlatForm.user.objId}
	$('#resProjectList').flexOptions({params:option});
	return true;
}

resProjectList.success = function(){
	//添加查看招标中心信息超链接
	   $("#resProjectListGrid").flexGetColByName({
	   	'projectName':function(id,t){
	   		var json = $("#resProjectListGrid").flexGetRowJsonById(id); 
	   		var isLeaf = json['isLeaf'];
		    if(isLeaf=='1'){
		    	 $(t).html("<a href='#' onClick=getAgentMessage('"+id+"');>"+$(t).html()+"</a>");
		    }
		   }
		});
		if(resProjectList.currentTabID == "tabs_toTemp" || resProjectList.currentTabID == "tabs_toBack"){//临时 
			$("#resProjectListGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">查看</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						$('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=toResProjectInfoDetail&objId="+rowId);
					}).appendTo(obj);
				},
				'<span><a href="#" class="abtn">申请</a>&nbsp;</span>': function(btn,rowId,obj){
					 btn.click(function(){
						 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=toResProjectInfoForm&objId="+rowId);
					 }).appendTo(obj);
				}
			});
		}else{
			$("#resProjectListGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">查看</a>&nbsp;</span>': function(btn,rowId,obj){
				 btn.click(function(){
					 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=toResProjectInfoDetail&objId="+rowId);
				 }).appendTo(obj);
				}
			});
		}
}

resProjectList.add = function(){
	$('#conBody').loadPage($('#initPath').val()+'/ResProjectController.do?method=loadResProjectInfo');
}

$(document).ready(function(){
	//加载tabs
	$('#epsTabs').tabs();
	resProjectList.currentTabID = "tabs_toTemp";//初始化时，默认当前TABID为tabs_toTemp
	resProjectList.preTabID = "";
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		$("#resProjectListGrid").find("tDiv").empty();
		resProjectList.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		var buttons = [];
		var userId = PlatForm.user.objId;
		if(resProjectList.currentTabID == "tabs_toTemp"){//临时	
			buttons = [];
			$('#resProjectListGrid').attr("p").newp = 1;
			$('#resProjectListGrid').attr("p").url = "ResProjectController.do?method=list&recordForm.auditStatus=01&useStatus=00&createUser.objId="+userId;
		}else if(resProjectList.currentTabID == "tabs_toWait"){//待审核
			buttons = [];
			$('#resProjectListGrid').attr("p").newp = 1;
			$('#resProjectListGrid').attr("p").url = "ResProjectController.do?method=list&recordForm.auditStatus=01&order=createTime&order_flag=true&useStatus=01&auditStatus=00&createUser.objId="+userId;
		}else if(resProjectList.currentTabID == "tabs_toBack"){//被退回
			buttons = [];
			$('#resProjectListGrid').attr("p").newp = 1;
			$('#resProjectListGrid').attr("p").url = "ResProjectController.do?method=list&recordForm.auditStatus=01&order=createTime&order_flag=true&useStatus=01&auditStatus=02&createUser.objId="+userId;
		}else if(resProjectList.currentTabID == "tabs_toFormal"){//已审核
			buttons = [];
			$('#resProjectListGrid').attr("p").newp = 1;
			$('#resProjectListGrid').attr("p").url = "ResProjectController.do?method=list&recordForm.auditStatus=01&order=createTime&order_flag=true&useStatus=01&auditStatus=01&createUser.objId="+userId;
		}else if(resProjectList.currentTabID == "tabs_aleradyCreateHistory"){//查看采购历史
			buttons = [];
			$('#resProjectListGrid').attr("p").newp = 1;
			$('#resProjectListGrid').attr("p").url = "ResProjectController.do?method=list&recordForm.auditStatus=01&order=createTime&order_flag=true&createUser.objId="+userId;
		}
		$('#resProjectListGrid').flexReDrawButtons(buttons);
		$('#resProjectListGrid').reload();
	});
	
});
