
var apiLogList={};
apiLogList.rows=null;//列表查询的结果集

	//列表操作验证
	apiLogList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择日志记录'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个日志记录'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	apiLogList.before=function(){
		var option={
			"status":'false'
		}
		$('#apiLogGrid').flexOptions({params:option});
		return true;
	}

	apiLogList.sendBulletin = function(name,grid){//批量发送公告
		if($(grid).isSelectEmpty()){alert('请选择一条记录');return false;}//是否选中
		if(window.confirm('确定'+name+'?')){
			var ids = $(grid).getSelects();
			$.getJSON($('#initPath').val()+'/ApiLogController.do?method=toSendBulletin',{'bizId':ids},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	
	//加载数据成功之后调用的函数
	apiLogList.success=function(){
		if(apiLogList.currentTabID == "tabs_toFalse"){//失败
			$("#apiLogGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">发送</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
					btn.click(function(){
						if(window.confirm('确定发送吗?')){
							$.getJSON($('#initPath').val()+'/ApiLogController.do?method=toSendBulletin',{'bizId':rowId},function(json){
								if(json.result)alert(json.result);if(json.failure)return;
								$("#apiLogGrid").reload();
							});
						}
					}).appendTo(obj);
				}
			});
		}else if (apiLogList.currentTabID == "tabs_toException") {//异常
			$("#apiLogGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">发送</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
					btn.click(function(){
						if(window.confirm('确定发送吗?')){
							$.getJSON($('#initPath').val()+'/ApiLogController.do?method=toSendBulletin',{'bizId':rowId},function(json){
								if(json.result)alert(json.result);if(json.failure)return;
								$("#apiLogGrid").reload();
							});
						}
					}).appendTo(obj);
				}
			});
		}
	}
$(document).ready(function(){
	
	//日历控件
	$('#_applyDate').epsDatepicker();
	
	//加载tabs
	$('#epsTabs').tabs();
	apiLogList.currentTabID = "tabs_toFalse";//初始化时，默认当前TABID为tabs_toTemp
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		$("#apiLogGrid").find("tDiv").empty();
		apiLogList.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		var buttons = [];
		if(apiLogList.currentTabID == "tabs_toFalse"){//失败
			buttons = [{name:"批量发送",bclass:"tableGo",onpress:apiLogList.sendBulletin}];
			$('#apiLogGrid').attr("p").url = "ApiLogController.do?method=list&status=false";
		}else if(apiLogList.currentTabID == "tabs_toTrue"){//成功
			buttons = [];
			$('#apiLogGrid').attr("p").url = "ApiLogController.do?method=list&status=true";
		}else if(apiLogList.currentTabID == "tabs_toException"){//异常
			buttons = [{name:"批量发送",bclass:"tableGo",onpress:apiLogList.sendBulletin}];
			$('#apiLogGrid').attr("p").url = "ApiLogController.do?method=list&status=exception";
		}
		$('#apiLogGrid').flexReDrawButtons(buttons);
		$('#apiLogGrid').reload();
	})
});

