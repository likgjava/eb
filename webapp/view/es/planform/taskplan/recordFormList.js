
var recordFormList={};
recordFormList.rows=null;//列表查询的结果集
	//新增
	recordFormList.add=function(name,grid){
		//跳转到新增页面
		$('#conBody').loadPage($('#initPath').val()+"/RecordFormController.do?method=toCreateOrUpdate&isComplete=true");
	}   
	//修改
//	recordFormList.update=function(name,grid){
//		if(!recordFormList.validation(name,grid))return;
//		//跳转到修改页面
//		$('#conBody').loadPage($('#initPath').val()+'/RecordFormController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
//	}   
	//删除
	recordFormList.remove=function(name,grid){
		if(!recordFormList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/RecordFormController.do?method=toDelRecordForm',{objIds:$(grid).getSelects()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	recordFormList.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择备案书'+name);return false;}//是否选中
		if(!$(grid).getSelects()){alert('请选择一个备案书'+name);return false;}//是否有选中的信息
		return true;
	}
	//查询条件过滤
	recordFormList.before=function(){
		var option={"order":"createTime",
				"order_flag":"true",
				"createUser.objId":PlatForm.user.objId}
		$('#recordFormGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	recordFormList.success=function(){
		
		if(recordFormList.currentTabID == "tabs_toTemp"){//临时 
			$("#recordFormGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">查看</a></span>'   : function(btn,rowId,obj){
				btn.click(function(){
					$.epsDialog({
				    	title:'招标投标登记表',
				    	url:$('#initPath').val()+"/RecordFormController.do?method=toShowRecordForm&objId="+rowId,
				    	width: '800',
				    	height: '500',
						dragAndResize:true,
						hasBg:false,
				    	isReload: false
					});
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">修改</a></span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/RecordFormController.do?method=toModifyRecordForm&objId='+rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">删除</a></span>' : function(btn,rowId,obj){
				btn.click(function(){
					if(window.confirm('确定删除吗?')){
						$.getJSON($('#initPath').val()+'/RecordFormController.do?method=toDelRecordForm',{objIds:rowId},function(json){
							if(json.result)alert(json.result);if(json.failure)return;
							$("#recordFormGrid").reload();
						});
					}
				}).appendTo(obj);
			}
		
			});
		}else if (recordFormList.currentTabID == "tabs_toBack"){//被退回
			$("#recordFormGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">查看</a></span>'   : function(btn,rowId,obj){
				btn.click(function(){
					$.epsDialog({
				    	title:'招标投标登记表',
				    	url:$('#initPath').val()+"/RecordFormController.do?method=toShowRecordForm&objId="+rowId,
				    	width: '800',
				    	height: '500',
						dragAndResize:true,
						hasBg:false,
				    	isReload: false
					});
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">修改</a></span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/RecordFormController.do?method=toModifyRecordForm&objId='+rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">删除</a></span>' : function(btn,rowId,obj){
				btn.click(function(){
					if(window.confirm('确定删除吗?')){
						$.getJSON($('#initPath').val()+'/RecordFormController.do?method=toDelRecordForm',{objIds:rowId},function(json){
							if(json.result)alert(json.result);if(json.failure)return;
							$("#recordFormGrid").reload();
						});
					}
				}).appendTo(obj);
			}
			});
		}else {
			$("#recordFormGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">查看</a></span>'   : function(btn,rowId,obj){
				btn.click(function(){
					$.epsDialog({
				    	title:'招标投标登记表',
				    	url:$('#initPath').val()+"/RecordFormController.do?method=toShowRecordForm&objId="+rowId,
				    	width: '800',
				    	height: '500',
						dragAndResize:true,
						hasBg:false,
				    	isReload: false
					});
				}).appendTo(obj);
			}});
		}
	}
$(document).ready(function(){
	//加载tabs
	$('#epsTabs').tabs();
	
	recordFormList.currentTabID = "tabs_toTemp";//初始化时，默认当前TABID为tabs_toTemp
	recordFormList.preTabID = "";
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		$("#recordFormGrid").find("tDiv").empty();
		recordFormList.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		var buttons = [];
		var userId = PlatForm.user.objId;
		if(recordFormList.currentTabID == "tabs_toTemp"){//临时	
			buttons = [{name:"新增",bclass:"add",onpress:recordFormList.add},
			           {name:"修改",bclass:"update",onpress:recordFormList.update},
			           {name:"批量删除",bclass:"delete",onpress:recordFormList.remove}
			];
			$('#recordFormGrid').attr("p").newp = 1;
			$('#recordFormGrid').attr("p").url = "RecordFormController.do?method=list&order=createTime&order_flag=true&useStatus=00&createUser.objId="+userId;
			$('#recordFormInfo').find('input[type=checkbox]').show();
			$('#recordFormGrid').attr("p").checkbox=true;
		}else if(recordFormList.currentTabID == "tabs_toWait"){//正式
			buttons = [];
			$('#recordFormGrid').attr("p").newp = 1;
			$('#recordFormGrid').attr("p").url = "RecordFormController.do?method=list&order=createTime&order_flag=true&useStatus=01&auditStatus=00&createUser.objId="+userId;
			$('#recordFormInfo').find('input[type=checkbox]').hide();
			$('#recordFormGrid').attr("p").checkbox=false;
		}else if(recordFormList.currentTabID == "tabs_toBack"){//被退回
			buttons = [];
			$('#recordFormGrid').attr("p").newp = 1;
			$('#recordFormGrid').attr("p").url = "RecordFormController.do?method=list&order=createTime&order_flag=true&useStatus=01&auditStatus=02&createUser.objId="+userId;
			$('#recordFormInfo').find('input[type=checkbox]').hide();
			$('#recordFormGrid').attr("p").checkbox=false;
		}else if(recordFormList.currentTabID == "tabs_toFormal"){//已审核
			buttons = [];
			$('#recordFormGrid').attr("p").newp = 1;
			$('#recordFormGrid').attr("p").url = "RecordFormController.do?method=list&order=createTime&order_flag=true&useStatus=01&auditStatus=01&createUser.objId="+userId;
			$('#recordFormInfo').find('input[type=checkbox]').hide();
			$('#recordFormGrid').attr("p").checkbox=false;
		}else if(recordFormList.currentTabID == "tabs_aleradyCreateHistory"){//查看采购历史
			buttons = [];
			$('#recordFormGrid').attr("p").newp = 1;
			$('#recordFormGrid').attr("p").url = "RecordFormController.do?method=list&order=createTime&order_flag=true&createUser.objId="+userId;
			$('#recordFormInfo').find('input[type=checkbox]').hide();
			$('#recordFormGrid').attr("p").checkbox=false;
		}
		$('#recordFormGrid').flexReDrawButtons(buttons);
		$('#recordFormGrid').reload();
	})
});

