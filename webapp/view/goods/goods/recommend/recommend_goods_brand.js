/*
 * 推荐商品品牌页面
 */

var RecommendGoodsBrand = {};
RecommendGoodsBrand.currentPage = ''; //当前页数
RecommendGoodsBrand.totalPage = ''; //总页数
RecommendGoodsBrand.currentTabID = 'tabs_toNoRecommend'; //当前Tab页

//取消推荐
RecommendGoodsBrand.cancleRrecommend = function(singleRecommendGoodsBrandId){
	var recommendGoodsBrandIds;
	//批量取消推荐
	if(singleRecommendGoodsBrandId == null){
		recommendGoodsBrandIds = $("#goodsBrandList2").dtSelects();
		if(recommendGoodsBrandIds.length<=0){alert("请至少选择一行数据！");return;}
	}
	//单个取消推荐
	else{
		recommendGoodsBrandIds = singleRecommendGoodsBrandId;
	}
	
	if(confirm("确定取消推荐？")){
		$.getJSON($("#initPath").val()+"/RecommendGoodsBrandController.do?method=remove", {"objId":recommendGoodsBrandIds.split(",")}, function(json){
			if(json.success){alert("操作成功！")}
			RecommendGoodsBrand.oTable2.fnDraw(); //刷新列表
		});
	}
};

//推荐商品品牌
RecommendGoodsBrand.recommendGoodsBrand=function(singleGoodsId){
	var goodsBrandIds;
	//批量推荐
	if(singleGoodsId == null){
		goodsBrandIds = $("#goodsBrandList1").dtSelects();
		if(goodsBrandIds.length<=0){alert("请至少选择一行数据！");return;}
	}
	//单个推荐
	else{
		goodsBrandIds = singleGoodsId;
	}
	$("#goodsBrandIds").val(goodsBrandIds);
	
	//填写商品品牌推荐理由
	$.epsDialog({
		id:'rgbReason',
        title:'填写商品品牌推荐理由',
        url:$('#initPath').val()+'/view/goods/goods/recommend/recommend_goods_brand_reason.jsp?dialogId=rgbReason',
        width: '400',
        height: '250',
        onClose: function(){
        	if($("#recommendSuccess").val()=='1'){
        		RecommendGoodsBrand.oTable1.fnDraw();
        		$("#recommendSuccess").val('0');
        	}
        }
    }); 
};

//查看品牌信息
RecommendGoodsBrand.showBrandInfo = function(brandId){
	$.epsDialog({
		title:"查看品牌信息",
		width: 900,
		height: 500,
		url:$('#initPath').val()+'/GoodsBrandController.do?method=toGoodsBrandDetailView&pageType=detail&objId='+brandId
	});
}

//加载已推荐商品品牌列表
RecommendGoodsBrand.recommendGoodsBrandList = function(){
	RecommendGoodsBrand.oTable2 = $('#goodsBrandList2').dataTable({
		'singleSelect' : false,
		'checkbox' : true,
		'queryColumns' : 'goodsBrand.brandCode,goodsBrand.brandName',
		'hiddenColumns':'goodsBrand.objId',
		'params' : {"order":"sort"},
		'fnInitComplete' : function(oSettings) {//表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback' : function(oSettings) {
			RecommendGoodsBrand.oTable1.oSettings = oSettings;
			var totalRecords = oSettings._iRecordsTotal;
			RecommendGoodsBrand.currentPage = oSettings._iDisplayStart;
			var pageSize = oSettings._iDisplayLength;
			var totalPage = totalRecords % pageSize == 0 ? totalRecords/pageSize : totalRecords/pageSize+1;
			RecommendGoodsBrand.totalPage = parseInt(totalPage);
		   
			//第一页
			if(RecommendGoodsBrand.currentPage==1){
				$("#goodsBrandList2 tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
			}
			//最后一页
			if(RecommendGoodsBrand.currentPage==RecommendGoodsBrand.totalPage){
				$("#goodsBrandList2 tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
			}
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find('td[name=goodsBrand.brandName]').html('<a href="javascript:void(0);" onclick="RecommendGoodsBrand.showBrandInfo(\''+aData['goodsBrand.objId']+'\');return false;">'+aData['goodsBrand.brandName']+'</a>');
			$(nRow).append('<td class="center"><a name="up" href="javascript:void(0);" onclick="RecommendGoodsBrand.upClick(this);return false;"><span class="sysicon siUp">&nbsp;</span></a><a name="down" href="javascript:void(0);" onclick="RecommendGoodsBrand.downClick(this)"><span class="sysicon siDown">&nbsp;</span></a></td>');
			
			$(nRow).append('<td class="center"><a href="javascript:void(0);" onclick="RecommendGoodsBrand.cancleRrecommend(\''+aData.objId+'\');return false;">取消推荐</a></td>');
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/RecommendGoodsBrandController.do?method=list",
		'searchZone':'goodsBrandSearchForm'			
	});
};

$(document).ready(function(){
	//加载tab页
	var $tabs = $('#epsTabs').tabs({}); 
	
	//加载未推荐商品品牌列表
	RecommendGoodsBrand.oTable1 = $('#goodsBrandList1').dataTable({
		'singleSelect' : false,
		'checkbox' : true,
		'queryColumns' : 'brandCode,brandName',
		'hiddenColumns':'',
		'fnInitComplete' : function(oSettings) {},
		'fnDrawCallback' : function(oSettings) {
			RecommendGoodsBrand.oTable1.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			//添加操作按钮
			$(nRow).find('td[name=brandName]').html('<a href="javascript:void(0);" onclick="RecommendGoodsBrand.showBrandInfo(\''+aData.objId+'\');return false;">'+aData.brandName+'</a>');
			$(nRow).append('<td class="center"><a href="javascript:void(0);" onclick="RecommendGoodsBrand.recommendGoodsBrand(\''+aData.objId+'\');return false;">推荐</a></td>');
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/RecommendGoodsBrandController.do?method=listNoRecommendGoodsBrand",
		'searchZone':'goodsBrandSearchForm'			
	});
	
	//查询未推荐商品品牌或推荐商品品牌
	$("#query").click(function() {
		if(RecommendGoodsBrand.currentTabID == "tabs_toNoRecommend"){
			RecommendGoodsBrand.oTable1.fnDraw();
		}else{
			RecommendGoodsBrand.oTable2.fnDraw();
		}
	});
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		RecommendGoodsBrand.currentTabID = $(this).attr("id");
		//未推荐
		if(RecommendGoodsBrand.currentTabID == "tabs_toNoRecommend"){
			RecommendGoodsBrand.oTable1.fnDraw();
			$("#cancleRecommendBatchsLi").hide();
			$("#recommendGoodsBrandBatchLi").show();
		}
		//已推荐
		else if(RecommendGoodsBrand.currentTabID == "tabs_toRecommend"){
			if(!RecommendGoodsBrand.oTable2){
				RecommendGoodsBrand.recommendGoodsBrandList();
			}else{
				RecommendGoodsBrand.oTable2.fnDraw();
			}
			$("#recommendGoodsBrandBatchLi").hide();
			$("#cancleRecommendBatchsLi").show();
		}
	});
});

//设置上下移动的样式
RecommendGoodsBrand.drawUpAndDownCss=function(){
	$("#goodsBrandList2 tr a[name=up]>span").removeClass("siUpGray").addClass("siUp");
	$("#goodsBrandList2 tr a[name=down]>span").removeClass("siDownGray").addClass("siDown");
	
    //顶级节点 
	if(RecommendGoodsBrand.currentPage==1){
		$("#goodsBrandList2 tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
	}
	if(RecommendGoodsBrand.currentPage==RecommendGoodsBrand.totalPage){
		$("#goodsBrandList2 tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
	}
}

//向上
RecommendGoodsBrand.upClick=function(obj){
	if($(obj).find("span").hasClass("siUpGray")) {
		return false;
	}
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).prev();
	var id = $(curRow).attr("objid");
	
	//更新到数据库
	$.getJSON($('#initPath').val()+"/RecommendGoodsBrandController.do?method=updateSort",{"objId":id,"isToUp":'true'},function(json){
		if(json.success){
			var firstRow = $("#goodsBrandList2 tr").eq(1);
			var firstObjId = $(firstRow).attr("objid");
			if(firstObjId == id){ //如果移动的是第一行，则重画列表。
				RecommendGoodsBrand.oTable2.fnDraw();
			}else{
				$(targetRow).before(curRow);	//把当前行放到目标行之前
				RecommendGoodsBrand.drawUpAndDownCss();	//重画向上向下的样式
			}
		}
	})
};

//向下
RecommendGoodsBrand.downClick=function(obj){
	if($(obj).find("span").hasClass("siDownGray")) {
		return false;
	}
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).next();
	var id = $(curRow).attr("objid");
	
	//更新到数据库
	$.getJSON($('#initPath').val()+"/RecommendGoodsBrandController.do?method=updateSort",{"objId":id,"isToUp":"false"},function(json){
		if(json.success){
			var lastRow = $("#goodsBrandList2 tr:last");
			var lastObjId = $(lastRow).attr("objid");
			
			if(lastObjId == id){ //如果移动的是最后一行，则重画列表。
				RecommendGoodsBrand.oTable2.fnDraw();
			}else{
				$(targetRow).after(curRow);	//把当前行放到目标行之后
				RecommendGoodsBrand.drawUpAndDownCss();	//重画向上向下的样式
			}
		}
	})
};