
var projectExceptionApplyList={};
projectExceptionApplyList.rows=null;//列表查询的结果集
	
//加载数据成功之后调用的函数
projectExceptionApplyList.success=function(){
	$("#projectExceptionApplyGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn"><span></span></a></span>' : function(btn,rowId,obj){
		 var json = $("#projectExceptionApplyGrid").flexGetRowJsonById(rowId); //得到JSON对象
		 var html = "";
		 if (json["auditStatus"]=='00') {
			 html = '查看';
		 }else if (json["auditStatus"]=='01') {
			 html = '查看';
		 } else if (json["auditStatus"]=='02') {
			 html = '进入';
		 }
		 $(btn).find('span').empty().append(html);
		 btn.click(function(){
			 if (json["auditStatus"]=='00') {
				 $('#conBody').loadPage($('#initPath').val()+'/ProjectExceptionApplyController.do?method=toViewProjectExceptionApply&projectExceptionApplyId='+rowId);
			 }else if (json["auditStatus"]=='01') {
				 $('#conBody').loadPage($('#initPath').val()+'/ProjectExceptionApplyController.do?method=toViewProjectExceptionApply&projectExceptionApplyId='+rowId);
			 } else if (json["auditStatus"]=='02') {
				 $('#conBody').loadPage($('#initPath').val()+'/ProjectExceptionApplyController.do?method=toUpdateProjectExceptionApply&projectExceptionApplyId='+rowId);
			 }
		 }).appendTo(obj);
	}
	});
}

//查询条件过滤
projectExceptionApplyList.before=function(){
	var option={"adviceProcway":"01"}
	$('#projectExceptionApplyGrid').flexOptions({params:option});
	return true;
}
$(document).ready(function(){
	//加载tabs
	$('#epsTabs').tabs();
	projectExceptionApplyList.currentTabID = "tabs_toRePurchase";//初始化时，默认当前TABID为tabs_toRePurchase
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		$("#projectExceptionApplyGrid").find("tDiv").empty();
		projectExceptionApplyList.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		var buttons = [];
		if(projectExceptionApplyList.currentTabID == "tabs_toRePurchase"){//重新招标
			$('#projectExceptionApplyGrid').attr("p").url = "ProjectExceptionApplyController.do?method=getProjectExceptionList&adviceProcway=01";
		}else if(projectExceptionApplyList.currentTabID == "tabs_toChangeEBuyMemthod"){//变更采购方式
			$('#projectExceptionApplyGrid').attr("p").url = "ProjectExceptionApplyController.do?method=getProjectExceptionList&adviceProcway=02";
		}else if(projectExceptionApplyList.currentTabID == "tabs_toStop"){//终止项目
			$('#projectExceptionApplyGrid').attr("p").url = "ProjectExceptionApplyController.do?method=getProjectExceptionList&adviceProcway=03";
		}
		$('#projectExceptionApplyGrid').flexReDrawButtons(buttons);
		$('#projectExceptionApplyGrid').reload();
	})
});

