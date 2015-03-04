/*
 * 执行平台，委托书列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var consignList={};

//设定返回时的路径
consignList.setRetrun=function(){
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/consign/consignList.jsp");
}

//新增
consignList.add=function(name,grid){
	$('#conBody').empty().loadPage($('#initPath').val()+"/view/es/planform/consign/consignDraft.jsp");
}   
//修改
consignList.update=function(name,grid){
	if(!consignList.validation(name,grid))return;
	//跳转到修改页面
	$("#returnUrl").val($('#initPath').val()+'/view/es/planform/consign/blockTradeConsignList.jsp');
	$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=toUpdateBlockTradeDraft&objId='+$(grid).getSelect());
}   

//查看
consignList.showDetail=function(name,grid){
	if(!consignList.validation(name,grid))return;
	$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=showDetail&isBlockTrade=isBlockTrade&objId='+$(grid).getSelect());
}

//提交确认
consignList.submit=function(name,grid){
	if(!consignList.validation(name,grid))return;
	if(window.confirm('确定要提交招标中心确认吗？')){
		$.getJSON($('#initPath').val()+'/ConsignController.do?method=batchSubmitConsign',{"objIds":$(grid).getSelects(),"confirmStatus":"01"},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

//删除
consignList.remove=function(name,grid){
	if(window.confirm('确定删除委托协议?')){
		$.getJSON($('#initPath').val()+'/ConsignController.do?method=deleteConsign',{consignId:$(grid).getSelect()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

//列表操作验证
consignList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请至少选择一份委托协议');return false;}//是否选中
	return true;
}
//查询条件过滤
consignList.before=function(){
	var option={"createUser.objId":PlatForm.user.objId,
			"confirmStatus":"00"
	}
	$('#consignGrid').flexOptions({params:option});
	return true;
}

consignList.success = function(){
	
	if(consignList.currentTabID == "tabs_toSubmit" || consignList.currentTabID == "tabs_toAdjust"){
		$("#consignGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=showDetail&isBlockTrade=isBlockTrade&objId='+rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$("#returnUrl").val($('#initPath').val()+'/view/es/planform/consign/blockTradeConsignList.jsp');
					$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=toUpdateBlockTradeDraft&objId='+rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					if(window.confirm('确定删除委托协议?')){
						$.getJSON($('#initPath').val()+'/ConsignController.do?method=deleteConsign',{"consignId":rowId},function(json){
							if(json.result)alert(json.result);if(json.failure)return;
							$("#consignGrid").reload();
						});
					}
				}).appendTo(obj);
			}
		});
	}
	if(consignList.currentTabID == "tabs_toSure" || consignList.currentTabID == "tabs_done"){
		$("#consignGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=showDetail&isBlockTrade=isBlockTrade&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}
}

$(document).ready(function(){
	//日历控件
	$('#consTime').epsDatepicker();
	
	//设定返回时的路径
	consignList.setRetrun();

	//加载tabs
	$('#epsTabs').tabs();
	consignList.currentTabID = "tabs_toSubmit";
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		consignList.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		var buttons = [];
		if(consignList.currentTabID == "tabs_toSubmit"){//待提交	
			buttons = [{name:"批量提交",bclass:"audit",onpress:consignList.submit}];
			$('#consignGrid').attr("p").url = "ConsignController.do?method=list&confirmStatus=00";
		}else if(consignList.currentTabID == "tabs_toSure"){//待确认
			buttons = [];
			$('#consignGrid').attr("p").url = "ConsignController.do?method=list&confirmStatus=01";
		}else if(consignList.currentTabID == "tabs_toAdjust"){//被退回
			buttons = [{name:"批量提交",bclass:"audit",onpress:consignList.submit}];
			$('#consignGrid').attr("p").url = "ConsignController.do?method=list&confirmStatus=04";
		}else if(consignList.currentTabID == "tabs_done"){//已确认
			buttons = [];
			$('#consignGrid').attr("p").url = "ConsignController.do?method=list&confirmStatus=02";
		}
		$('#consignGrid').flexReDrawButtons(buttons);
		$('#consignGrid').reload();
	})
});

