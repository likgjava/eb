var resProjectList = {};


resProjectList.audit = function(resProjectId){
	$('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=toResProjectInfoForm&objId="+resProjectId);
}

//查询条件过滤
resProjectList.before=function(){
	var option={"order":"createTime",
				"order_flag":"true"
				}
	$('#resProjectList').flexOptions({params:option});
	return true;
}

resProjectList.success = function(){
		if(resProjectList.currentTabID == "tabs_toWait"){//临时 
			$("#resProjectListGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">查看</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						$('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=toResProjectInfoDetail&from=auditList&objId="+rowId);
					}).appendTo(obj);
				},
				'<span><a href="#" class="abtn">审核</a>&nbsp;</span>': function(btn,rowId,obj){
					 btn.click(function(){
						 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=toResProjectInfoForAudit&objId="+rowId);
					 }).appendTo(obj);
				}
			});
		}else{
			$("#resProjectListGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">查看</a>&nbsp;</span>': function(btn,rowId,obj){
				 btn.click(function(){
					 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=toResProjectInfoDetail&from=auditList&objId="+rowId);
				 }).appendTo(obj);
				}
			});
		}
}

$(document).ready(function(){
	//加载tabs
	$('#epsTabs').tabs();
	resProjectList.currentTabID = "tabs_toWait";//初始化时，默认当前TABID为tabs_toTemp
	resProjectList.preTabID = "";
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		$("#resProjectListGrid").find("tDiv").empty();
		resProjectList.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		var buttons = [];
		if(resProjectList.currentTabID == "tabs_toWait"){//待审核
			buttons = [];
			$('#resProjectListGrid').attr("p").newp = 1;
			$('#resProjectListGrid').attr("p").url = "ResProjectController.do?method=list&auditStatus=00&useStatus=01";
		}else if(resProjectList.currentTabID == "tabs_toBack"){//被退回
			buttons = [];
			$('#resProjectListGrid').attr("p").newp = 1;
			$('#resProjectListGrid').attr("p").url = "ResProjectController.do?method=list&auditStatus=02&useStatus=01";
		}else if(resProjectList.currentTabID == "tabs_toFormal"){//已审核
			buttons = [];
			$('#resProjectListGrid').attr("p").newp = 1;
			$('#resProjectListGrid').attr("p").url = "ResProjectController.do?method=list&auditStatus=01&useStatus=01";
		}
		$('#resProjectListGrid').flexReDrawButtons(buttons);
		$('#resProjectListGrid').reload();
	});
	
});
