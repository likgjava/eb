/*
 * 推荐供应商页面
 * created by likg
 */

var RecommendSupplier = {};
RecommendSupplier.currentPage = ''; //当前页数
RecommendSupplier.totalPage = ''; //总页数

// 取消推荐
RecommendSupplier.cancleRrecommend=function(singleRecommendSupplierId){
	var recommendSupplierIds;
	if(singleRecommendSupplierId == null)	// 批量取消推荐
	{
		recommendSupplierIds = $("#supplierList2").dtSelects();
		if(recommendSupplierIds.length<=0){alert("请至少选择一行数据！");return;}
	}
	else	// 单个取消推荐
	{
		recommendSupplierIds = singleRecommendSupplierId;
	}
	
	if(confirm("确定取消推荐？"))
	{
		$.getJSON($("#initPath").val()+"/RecommendSupplierController.do?method=deleteRecommedSupplier", {"objId":recommendSupplierIds}, function(json){
			if(json.success){alert("操作成功！")}
			RecommendSupplier.oTable2.fnDraw(); // 刷新列表
		});
	}
};

//推荐供应商
RecommendSupplier.recommendSupplier=function(singleSupplierId){
	var supplierIds;
	if(singleSupplierId == null)	// 批量推荐
	{
		supplierIds = $("#supplierList1").dtSelects();
		if(supplierIds.length<=0){alert("请至少选择一行数据！");return;}
	}
	else	// 单个推荐
	{
		supplierIds = singleSupplierId;
	}
	
	// 填写供应商推荐理由
	$.epsDialog({
		id:'RecommendSupplierReason',
        title:'填写供应商推荐理由',
        url:$('#initPath').val()+'/view/bizplatform/suppliers/recommend/recommend_supplier_reason.jsp?property=recommendReason&dialogId=RecommendSupplierReason',
        width: '400',
        height: '250',
        onOpen: function(){ },
        afterLoad: function(){ },
        onClose: function(){
        	var recommendReason = $("#recommendReason").val();
        	if(recommendReason!=""){
        		$.getJSON($('#initPath').val()+'/RecommendSupplierController.do?method=recommendSupplier',
            			{"supplierIds":supplierIds,"recommendReason":recommendReason},
            			function(json){
            				if(json.success){
            					alert("推荐成功！");
            					$("#recommendReason").val(''); //清空推荐理由
            					RecommendSupplier.oTable1.fnDraw();
            				}
            			}
            	);
        	}
        }
    }); 
};

//加载已推荐供应商列表
RecommendSupplier.recommendSupplierList=function(){
	RecommendSupplier.oTable2 = $('#supplierList2').dataTable({
		'singleSelect' : false,
		'checkbox' : true,
		'queryColumns' : 'orgInfo.orgName,reason',
		'hiddenColumns':'',
		'params' : {"order":"sort"},
		'fnInitComplete' : function(oSettings) {//表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback' : function(oSettings) {
			RecommendSupplier.oTable1.oSettings = oSettings;
			var totalRecords = oSettings._iRecordsTotal;
			RecommendSupplier.currentPage = oSettings._iDisplayStart;
			var pageSize = oSettings._iDisplayLength;
			var totalPage = totalRecords % pageSize == 0 ? totalRecords/pageSize : totalRecords/pageSize+1;
			RecommendSupplier.totalPage = parseInt(totalPage);
		   
			//第一页
			if(RecommendSupplier.currentPage==1){
				$("#supplierList2 tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
			}
			//最后一页
			if(RecommendSupplier.currentPage==RecommendSupplier.totalPage){
				$("#supplierList2 tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
			}
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).append('<td class="center"><a name="up" href="javascript:void(0);" onclick="RecommendSupplier.upClick(this);return false;"><span class="sysicon siUp">&nbsp;</span></a><a  name="down" href="javascript:void(0);" onclick="RecommendSupplier.downClick(this)"><span class="sysicon siDown">&nbsp;</span></a></td>');
			
			$(nRow).append('<td  class="operation"><a href="javascript:void(0);" onclick="  RecommendSupplier.cancleRrecommend(\''+aData.objId+'\');return false;">取消推荐</a></td>');
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/RecommendSupplierController.do?method=list",
		'searchZone':'recommendSupplierSearchForm'			
	});
};

$(document).ready(function(){
	//加载tab页
	var $tabs = $('#epsTabs').tabs({}); 
	
	//加载未推荐供应商列表
	RecommendSupplier.oTable1 = $('#supplierList1').dataTable({
		'singleSelect' : false,
		'checkbox' : true,
		'queryColumns' : 'orgName,entPrpt,unitScape',// 屏蔽注册资金regCapital
		'alias' : 'orgName,entPrptCN,unitScapeCN',
		'hiddenColumns':'',
		'fnInitComplete' : function(oSettings) {//表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback' : function(oSettings) {
			RecommendSupplier.oTable1.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=regCapital]").html(aData.regCapital/10000);
			$(nRow).append('<td  class="operation"><a href="javascript:void(0);" onclick="RecommendSupplier.recommendSupplier(\''+aData.objId+'\');return false;">推荐</a></td>');
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/RecommendSupplierController.do?method=listNoRecommendSupplier",
		'searchZone':'noRecommendSupplierSearchForm'			
	});
	
	//查询未推荐供应商
	$("#query1").click(function() {
		RecommendSupplier.oTable1.fnDraw();
	});
	
	//查询已推荐供应商
	$("#query2").click(function() {
		RecommendSupplier.oTable2.fnDraw();
	});
	
	//批量推荐供应商
	$("#recommendSupplierBatch").click(function(){
		RecommendSupplier.recommendSupplier();
	});
	
	//批量取消推荐
	$("#cancleRecommendBatch").click(function(){
		RecommendSupplier.cancleRrecommend();
	});
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		RecommendSupplier.currentTabID = $(this).attr("id");
		if(RecommendSupplier.currentTabID == "tabs_toNoRecommend")	// 未推荐
		{
			RecommendSupplier.oTable1.fnDraw();
			$("#recommendSupplierSearchForm").hide();
			$("#noRecommendSupplierSearchForm").show();
			$("#cancleRecommendBatchsLi").hide();
			$("#recommendSupplierBatchLi").show();
		}
		else if(RecommendSupplier.currentTabID == "tabs_toRecommend")	// 已推荐
		{
			if(!RecommendSupplier.oTable2)
			{
				RecommendSupplier.recommendSupplierList();
			}
			else
			{
				RecommendSupplier.oTable2.fnDraw();
			}
			$("#noRecommendSupplierSearchForm").hide();
			$("#recommendSupplierSearchForm").show();
			$("#recommendSupplierBatchLi").hide();
			$("#cancleRecommendBatchsLi").show();
		}
	});
});

//设置上下移动的样式
RecommendSupplier.drawUpAndDownCss=function(){
	$("#supplierList2 tr a[name=up]>span").removeClass("siUpGray").addClass("siUp");
	$("#supplierList2 tr a[name=down]>span").removeClass("siDownGray").addClass("siDown");
	
	//顶级节点 
	if(RecommendSupplier.currentPage==1){
		$("#supplierList2 tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
	}
	if(RecommendSupplier.currentPage==RecommendSupplier.totalPage){
		$("#supplierList2 tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
	}
}

//向上
RecommendSupplier.upClick=function(obj){
	if($(obj).find("span").hasClass("siUpGray")) {
		return false;
	}
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).prev();
	var id = $(curRow).attr("objid");
	
	$.getJSON($('#initPath').val()+"/RecommendSupplierController.do?method=updateSort",{"sourceObjId":id,"isToUp":"true"},function(json){})
	
	var firstRow = $("#supplierList2 tr").eq(1);
	var firstObjId = $(firstRow).attr("objid");
	if(firstObjId == id){ //如果移动的是第一行，则重画列表。
		RecommendSupplier.oTable2.fnDraw();
	}else{
		$(targetRow).before(curRow);	//把当前行放到目标行之前
		RecommendSupplier.drawUpAndDownCss();	//重画向上向下的样式
	}
};

//向下
RecommendSupplier.downClick=function(obj){
	if($(obj).find("span").hasClass("siDownGray")) {
		return false;
	}
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).next();
	var id = $(curRow).attr("objid");
	var targetObjId = $(targetRow).attr("objid");
	
	$.getJSON($('#initPath').val()+"/RecommendSupplierController.do?method=updateSort",{"sourceObjId":id,"isToUp":"false"},function(json){})
	
	var lastRow = $("#supplierList2 tr:last");
	var lastObjId = $(lastRow).attr("objid");
	
	if(lastObjId == id){ //如果移动的是最后一行，则重画列表。
		RecommendSupplier.oTable2.fnDraw();
	}else{
		$(targetRow).after(curRow);	//把当前行放到目标行之后
		RecommendSupplier.drawUpAndDownCss();	//重画向上向下的样式
	}
};
