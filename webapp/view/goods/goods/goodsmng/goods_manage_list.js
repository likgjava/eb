var GoodsManageList = {};
GoodsManageList.currentTabID = "tabs_newGoods";
GoodsManageList.getOperatorStr=function(objId,auditStatus,currentId){
	var operatorHtml = "";
	operatorHtml += '<td align="center">';
	if ("tabs_newGoods"==GoodsManageList.currentTabID || "tabs_modifyGoods"==GoodsManageList.currentTabID) {
		if(auditStatus=="00" || auditStatus=="03"){//草稿
			operatorHtml += '<a title="修改" href="javascript:void(0);" onclick="GoodsManageList.toUpdateGoods(\''+objId+'\');return false;">修改</a>';
		}
		operatorHtml += '<a title="选配" href="javascript:void(0);" onclick="GoodsManageList.manageOption(\''+objId+'\');return false;">选配</a>';
		/**协议供货
		operatorHtml += '<a title="零配件" href="javascript:void(0);" onclick="GoodsManageList.manageAccessory(\''+objId+'\');return false;">零配件</a>';
		*/
	} else if ("tabs_validGoods"==GoodsManageList.currentTabID) {
		if(currentId == "" || currentId == null) {
			operatorHtml += '<a title="修改" href="javascript:void(0);" onclick="GoodsManageList.toUpdateGoods(\''+objId+'\');return false;">修改</a>';
			operatorHtml += '<a title="变更" href="javascript:void(0);" onclick="GoodsManageList.toModify(\''+objId+'\');return false;">变更</a>';
			operatorHtml += '<a title="查看历史" href="javascript:void(0);" onclick="GoodsManageList.history(\''+objId+'\');return false;">查看历史</a>';
		}
		operatorHtml += '<a title="选配" href="javascript:void(0);" onclick="GoodsManageList.manageOption(\''+objId+'\');return false;">选配</a>';
		/**协议供货
		operatorHtml += '<a title="零配件" href="javascript:void(0);" onclick="GoodsManageList.manageAccessory(\''+objId+'\');return false;">零配件</a>';
		*/
	} 
	operatorHtml += '<a title="查看" href="javascript:void(0);" onclick="GoodsManageList.view(\''+objId+'\');return false;">查看</a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//新增商品
GoodsManageList.toCreateGoods=function(){
	window.open($('#initPath').val()+"/GoodsController.do?method=toCreateGoods");
}

//修改商品
GoodsManageList.toUpdateGoods=function(id){
	window.open($('#initPath').val()+"/GoodsController.do?method=toUpdateGoods&objId="+id);
}

//变更
GoodsManageList.toModify=function(id){
	var url = $('#initPath').val()+"/GoodsChangeController.do?method=toGoodsChange&objId="+id;
	$.epsDialog({
		id:'goodsChangeDiv',
        title:'商品变更',
        url:url
    })
}

//查看历史
GoodsManageList.history=function(id){
	var url = $('#initPath').val()+"/GoodsController.do?method=getGoodsHistory&id="+id;
	$.epsDialog({
		id:'goodsChangeHistoryDiv',
        title:'商品变更历史',
        url:url,
        width:600,
        height:300
    }); 
}

//查看
GoodsManageList.view=function(id){
	window.open($('#initPath').val()+"/GoodsShowController.do?method=getGoodsInfo&isShowPic=true&objId=" + id+"&viewName=goodsPreview");
}  
//禁卖
GoodsManageList.forbid=function(ids){
	if(window.confirm("确定要禁卖此商品吗?")){
		$.getJSON($('#initPath').val()+"/GoodsController.do?method=disableGoods", {"objIds":ids} , function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			alert(json.result);
			GoodsManageList.oTable.fnDraw();
		});
	}
} 
//启卖
GoodsManageList.start=function(ids){
	if(window.confirm("确定要启卖此商品吗?")){
		$.getJSON($('#initPath').val()+"/GoodsController.do?method=enableGoods", {"objIds":ids} , function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			alert(json.result);
			GoodsManageList.oTable.fnDraw();
		});
	}
}
//报废
GoodsManageList.scrapped=function(ids){
	if(window.confirm("确定要报废此商品吗?")){
		$.getJSON($('#initPath').val()+"/GoodsController.do?method=scrappedGoods", {"objIds":ids} , function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			alert(json.result);
			GoodsManageList.oTable.fnDraw();
		});
	}
}
//删除
GoodsManageList.remove=function(ids){
	if(window.confirm("确定删除所选商品吗")){
		$.getJSON($('#initPath').val()+'/GoodsController.do?method=removeGoods',{"objIds":ids},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			GoodsManageList.oTable.fnDraw();
		});
	}
} 
//维护选配
GoodsManageList.manageOption=function(id) {
	//有效的商品只能禁用选配不能删除选配
	var url = $("#initPath").val()+'/GoodsController.do?method=toAddOrModifyGoodsParam&goodsId='+id+"&type=manage";
	if("tabs_validGoods"==GoodsManageList.currentTabID){
		url += '&disableFitting=1';
	}else{
		url += '&disableFitting=0';
	}
	$.epsDialog({
        title:'维护选配',
        url:url
    })
}
//维护零配件
GoodsManageList.manageAccessory=function(id) {
	$('#conBody').loadPage($('#initPath').val()+"/view/goods/goods/goodsmng/goodsAccessories_list.jsp?goodsId="+id);
}
//批量操作按钮
GoodsManageList.getOperButton=function(){
	if ("tabs_newGoods"==GoodsManageList.currentTabID || "tabs_modifyGoods"==GoodsManageList.currentTabID) {
		$("#removeGoodsBtn").removeClass("hidden");
		
		$("#forbidGoodsBtn").addClass("hidden");
		$("#scrappGoodsBtn").addClass("hidden");
		$("#startGoodsBtn").addClass("hidden");
	}else if ("tabs_validGoods"==GoodsManageList.currentTabID) {
		$("#forbidGoodsBtn").removeClass("hidden");
		$("#scrappGoodsBtn").removeClass("hidden");
		
		$("#removeGoodsBtn").addClass("hidden");
		$("#startGoodsBtn").addClass("hidden");
	}else if ("tabs_historyGoods"==GoodsManageList.currentTabID) {
		$("#startGoodsBtn").removeClass("hidden");
		
		$("#forbidGoodsBtn").addClass("hidden");
		$("#scrappGoodsBtn").addClass("hidden");
		$("#removeGoodsBtn").addClass("hidden");
	}
}

//创建或刷新列表数据
GoodsManageList.getTableList = function() {
	//添加操作按钮
	GoodsManageList.getOperButton();
	//加载商品列表
	if(!GoodsManageList.oTable) {
		GoodsManageList.oTable = $('#goodsManageList').dataTable({
			'singleSelect' : false,
			'checkbox' : true,
			'queryColumns' : 'picture,productName,productCode,goodsBrand.brandName,createUser.emp.name,auditStatus,sellStatus',
			'alias' : 'picture,productName,productCode,goodsBrand.brandName,createUser.emp.name,auditStatusCN,sellStatusCN',
			'hiddenColumns':'useStatus,currentId',
			'fnInitComplete' : function(oSettings) {
			},
			'fnDrawCallback' : function(oSettings) {
				GoodsManageList.oTable.oSettings = oSettings;
			},
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				//有效商品列表中，如果商品已被变更，不允许禁卖或报废
				if(aData["sellStatus"]=='01' && aData["useStatus"]=='01'){
					if(aData["currentId"]){
						$(nRow).find('input:checkbox').each(function(i,n){
							$(n).attr("disabled","disabled");
						})
					}
				}
				if(aData["useStatus"]=='02'){//报废商品不能启卖
					$(nRow).find('input:checkbox').each(function(i,n){
						$(n).attr("disabled","disabled");
					})
					$(nRow).find('td[name=sellStatus]').html('报废');
				}
				$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["picture"]+'" CURSOR="hand" width="60px" height="60px"/>');
				//添加操作按钮
				$(nRow).append(GoodsManageList.getOperatorStr(aData.objId,aData.auditStatus,aData.currentId));
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/GoodsController.do?method=listGoods",
			"params":{"type":GoodsManageList.currentTabID.replace("tabs_","")},
			'searchZone':'GoodsManageSearchForm'
		});
	}else {
		$(GoodsManageList.oTable.dataTableSettings).attr("params", {"type":GoodsManageList.currentTabID.replace("tabs_","")});
		GoodsManageList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val($("#initPath").val()+"/view/goods/goods/goodsmng/goods_manage_list.jsp");
	
    //开始时间
    $("#startDate").epsDatepicker();  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker();  //增加开始时间的规则
    
    //高级查询
	$("#hightSearchSwitch").click(function(){
		$(".conSearch .hightSearch").toggle("slow");
		$(this).toggleClass("collapsable");
		$(".operationBtnDiv").toggleClass("hightSearchBtn");
	});
	
	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			GoodsManageList.currentTabID = ui.tab.id;
			$("#currentTab").val(ui.index); //当前tab的index
			GoodsManageList.getTableList();
		}
	});
	
	//指定某一个tab被选中，默认值为0
	$tabs.tabs('select', parseInt($("#currentTab").val()));
	
	//tab无法触发第一个选中，所以需要手动加载一次
	if($("#currentTab").val() == "0") {
		GoodsManageList.getTableList();
	}
	
	//新增
	$("#addGoodsBtn").click(function() {
		GoodsManageList.toCreateGoods("");
	})
	//删除
	$("#removeGoodsBtn").click(function() {
		var selects = GoodsManageList.oTable.dtSelects();
		if(selects.length == 0){
			alert("请至少选中一个商品");return;
		}
		GoodsManageList.remove(selects);
	})
	//报废
	$("#scrappGoodsBtn").click(function() {
		var selects = GoodsManageList.oTable.dtSelects();
		if(selects.length == 0){
			alert("请至少选中一个商品");return;
		}
		GoodsManageList.scrapped(selects);
	})
	//禁卖
	$("#forbidGoodsBtn").click(function() {
		var selects = GoodsManageList.oTable.dtSelects();
		if(selects.length == 0){
			alert("请至少选中一个商品");return;
		}
		GoodsManageList.forbid(selects);
	})
	//启卖
	$("#startGoodsBtn").click(function() {
		var selects = GoodsManageList.oTable.dtSelects();
		if(selects.length == 0){
			alert("请至少选中一个商品");return;
		}
		GoodsManageList.start(selects);
	})
	// 查询
	$("#query").click(function() {
		if($("#startDate").val() != "" && $("#endDate").val() != ""){
			if($("#endDate").val() < $("#startDate").val()){
				alert("请输入正确的创建时间范围!");return;
			}
		}
		GoodsManageList.oTable.fnDraw();
	})
	
	//商品数据迁移
	$("#transferGoodsBtn").click(function() {
		if(window.confirm('此举会将商品的参数数据拷贝到spec字段中,确认操作吗?')) {
			$.getJSON($('#initPath').val()+"/GoodsController.do?method=update_goods_transfer", {} , function(json){
				if(json.failure){if(json.result)alert(json.result);return;}
				alert(json.result);
			});
		}
	})
})