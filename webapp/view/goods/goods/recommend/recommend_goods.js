/* 推荐商品页面 */

var RecommendGoods = {};
RecommendGoods.currentPage = ''; //当前页数
RecommendGoods.totalPage = ''; //总页数

//取消推荐
RecommendGoods.cancleRrecommend = function(singleRecommendGoodsId){
	var recommendGoodsIds;
	//批量取消推荐
	if(singleRecommendGoodsId == null){
		recommendGoodsIds = $("#goodsList2").dtSelects();
		if(recommendGoodsIds.length<=0){alert("请至少选择一行数据！");return;}
	}
	//单个取消推荐
	else{
		recommendGoodsIds = singleRecommendGoodsId;
	}
	
	if(confirm("确定取消推荐？")){
		$.getJSON($("#initPath").val()+"/RecommendGoodsController.do?method=remove", {"objId":recommendGoodsIds.split(",")}, function(json){
			if(json.success){alert(json.result)}
			RecommendGoods.oTable2.fnDraw(); //刷新列表
		});
	}
};

//推荐商品
RecommendGoods.recommendGoods = function(singleGoodsId){
	var goodsIds = "";
	//批量推荐
	if(singleGoodsId == null){
		goodsIds = $("#goodsList1").dtSelects();
		if(goodsIds.length<=0){alert("请至少选择一行数据！");return;}
	}
	//单个推荐
	else{
		goodsIds = singleGoodsId;
	}
	$("#goodsIds").val(goodsIds); //保存推荐的商品的id供弹出层使用
	
	//填写商品推荐理由
	$.epsDialog({
		id:'RecommendGoodsReason',
        title:'填写商品推荐理由',
        url:$('#initPath').val()+'/view/goods/goods/recommend/recommend_goods_reason.jsp?dialogId=RecommendGoodsReason',
        width: '400',
        height: '250',
        onOpen: function(){ },
        afterLoad: function(){ },
        onClose: function(){
        	if($("#recommendSuccess").val()=='1'){
        		RecommendGoods.oTable1.fnDraw();
        		$("#recommendSuccess").val('0');
        	}
        }
    }); 
};

//加载已推荐商品列表
RecommendGoods.recommendGoodsList=function(){
	RecommendGoods.oTable2 = $('#goodsList2').dataTable({
		'singleSelect' : false,
		'checkbox' : true,
		'queryColumns' : 'goods.productName,goods.productCode,goods.goodsBrand.brandName,reason',
		'params' : {"order":"sort"},
		'fnInitComplete' : function(oSettings) {//表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback' : function(oSettings) {
			RecommendGoods.oTable1.oSettings = oSettings;
			var totalRecords = oSettings._iRecordsTotal;
			RecommendGoods.currentPage = oSettings._iDisplayStart;
			var pageSize = oSettings._iDisplayLength;
			var totalPage = totalRecords % pageSize == 0 ? totalRecords/pageSize : totalRecords/pageSize+1;
			RecommendGoods.totalPage = parseInt(totalPage);
		   
			//第一页
			if(RecommendGoods.currentPage==1){
				$("#goodsList2 tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
			}
			//最后一页
			if(RecommendGoods.currentPage==RecommendGoods.totalPage){
				$("#goodsList2 tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
			}
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).append('<td class="operation"><a name="up" href="javascript:void(0);" onclick="RecommendGoods.upClick(this);return false;"><span class="sysicon siUp">&nbsp;</span></a><a name="down" href="javascript:void(0);" onclick="RecommendGoods.downClick(this)"><span class="sysicon siDown">&nbsp;</span></a></td>');
			
			$(nRow).append('<td class="operation"><a href="javascript:void(0);" onclick="RecommendGoods.cancleRrecommend(\''+aData.objId+'\');return false;">取消推荐</a></td>');
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/RecommendGoodsController.do?method=list",
		'searchZone':'recommendGoodsSearchForm'			
	});
};

$(document).ready(function(){
	//加载tab页
	var $tabs = $('#epsTabs').tabs({});
	
	//加载未推荐商品列表
	RecommendGoods.oTable1 = $('#goodsList1').dataTable({
		'singleSelect' : false,
		'checkbox' : true,
		'queryColumns' : 'productName,productCode,goodsBrand.brandName,goodsClass.goodsClassName',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			RecommendGoods.oTable1.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			//添加操作按钮
			$(nRow).append('<td class="operation"><a title="推荐此商品" href="javascript:void(0);" onclick="RecommendGoods.recommendGoods(\''+aData.objId+'\');return false;">推荐</a></td>');
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/RecommendGoodsController.do?method=listNoRecommendGoods",
		'searchZone':'noRecommendGoodsSearchForm'			
	});
	
	//选择商品分类
	$('#goodsClassName').click(function(){
		$.epsDialog({
			title:'选择分类',
			url:$('#initPath').val()+'/view/goods/goodsclass/goodsclass_select.jsp',
			width: 380,
			height: 400
		});
	});
	
	//查询未推荐商品
	$("#query1").click(function() {
		RecommendGoods.oTable1.fnDraw();
	});
	//查询已推荐商品
	$("#query2").click(function() {
		RecommendGoods.oTable2.fnDraw();
	});
	
	//批量推荐商品
	$("#recommendGoodsBatch").click(function(){
		RecommendGoods.recommendGoods();
	});
	
	//批量取消推荐
	$("#cancleRecommendBatch").click(function(){
		RecommendGoods.cancleRrecommend();
	});
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		RecommendGoods.currentTabID = $(this).attr("id");
		//未推荐
		if(RecommendGoods.currentTabID == "tabs_toNoRecommend"){
			RecommendGoods.oTable1.fnDraw();
			$("#recommendGoodsSearchForm").hide();
			$("#noRecommendGoodsSearchForm").show();
			$("#cancleRecommendBatchsLi").hide();
			$("#recommendGoodsBatchLi").show();
		}
		//已推荐
		else if(RecommendGoods.currentTabID == "tabs_toRecommend"){
			if(!RecommendGoods.oTable2){
				RecommendGoods.recommendGoodsList();
			}else{
				RecommendGoods.oTable2.fnDraw();
			}
			$("#noRecommendGoodsSearchForm").hide();
			$("#recommendGoodsSearchForm").show();
			$("#recommendGoodsBatchLi").hide();
			$("#cancleRecommendBatchsLi").show();
		}
	});
});

//设置上下移动的样式
RecommendGoods.drawUpAndDownCss=function(){
	$("#goodsList2 tr a[name=up]>span").removeClass("siUpGray").addClass("siUp");
	$("#goodsList2 tr a[name=down]>span").removeClass("siDownGray").addClass("siDown");
	
    //顶级节点 
	if(RecommendGoods.currentPage==1){
		$("#goodsList2 tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
	}
	if(RecommendGoods.currentPage==RecommendGoods.totalPage){
		$("#goodsList2 tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
	}
}

//向上
RecommendGoods.upClick=function(obj){
	if($(obj).find("span").hasClass("siUpGray")) { return false; }
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).prev();
	var id = $(curRow).attr("objid");
	
	//更新到数据库
	$.getJSON($('#initPath').val()+"/RecommendGoodsController.do?method=updateSort",{"sourceObjId":id,"isToUp":'true'},function(json){
		if(json.success){
			var firstRow = $("#goodsList2 tr").eq(1);
			var firstObjId = $(firstRow).attr("objid");
			if(firstObjId == id){ //如果移动的是第一行，则重画列表。
				RecommendGoods.oTable2.fnDraw();
			}else{
				$(targetRow).before(curRow);	//把当前行放到目标行之前
				RecommendGoods.drawUpAndDownCss();	//重画向上向下的样式
			}
		}
	});
};

//向下
RecommendGoods.downClick=function(obj){
	if($(obj).find("span").hasClass("siDownGray")) { return false; }
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).next();
	var id = $(curRow).attr("objid");
	
	//更新到数据库
	$.getJSON($('#initPath').val()+"/RecommendGoodsController.do?method=updateSort",{"sourceObjId":id,"isToUp":"false"},function(json){
		if(json.success){
			var lastRow = $("#goodsList2 tr:last");
			var lastObjId = $(lastRow).attr("objid");
			if(lastObjId == id){ //如果移动的是最后一行，则重画列表。
				RecommendGoods.oTable2.fnDraw();
			}else{
				$(targetRow).after(curRow);	//把当前行放到目标行之后
				RecommendGoods.drawUpAndDownCss();	//重画向上向下的样式
			}
		}
	});
};