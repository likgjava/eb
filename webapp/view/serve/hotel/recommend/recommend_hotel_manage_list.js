/*
 * 推荐酒店管理列表页面
 * created by likg
 */

var RecommendHotelList = {};
RecommendHotelList.currentPage = ''; //当前页数
RecommendHotelList.totalPage = ''; //总页数
RecommendHotelList.currentTabID = 'tabs_toNoRecommend';

//取消推荐
RecommendHotelList.cancleRrecommend=function(singleRecommendHotelId){
	var recommendHotelIds;
	if(singleRecommendHotelId == null){	//批量取消推荐
		recommendHotelIds = $("#hotelList2").dtSelects();
		if(recommendHotelIds.length<=0){alert("请至少选择一行数据！");return;}
	}else{	//单个取消推荐
		recommendHotelIds = singleRecommendHotelId;
	}
	
	var objIdArr = recommendHotelIds.split(",");
	
	if(confirm("确定取消推荐？")) {
		$.getJSON($("#initPath").val()+"/RecommendHotelController.do?method=remove", {"objId":objIdArr}, function(json){
			if(json.success){alert("操作成功！")}
			RecommendHotelList.oTable2.fnDraw(); //刷新列表
		});
	}
};

//推荐酒店
RecommendHotelList.recommendHotel=function(singleHotelId){
	var hotelIds;
	if(singleHotelId == null){	//批量推荐
		hotelIds = $("#hotelList1").dtSelects();
		if(hotelIds.length<=0){alert("请至少选择一行数据！");return;}
	}else{	//单个推荐
		hotelIds = singleHotelId;
	}
	
	//填写酒店推荐理由
	$.epsDialog({
		id:'RecommendHotelReason',
        title:'填写酒店推荐理由',
        url:$('#initPath').val()+'/view/serve/hotel/recommend/recommend_hotel_reason.jsp?property=recommendReason&dialogId=RecommendHotelReason',
        width: '400',
        height: '250',
        onOpen: function(){ },
        afterLoad: function(){ },
        onClose: function(){
        	var recommendReason = $("#recommendReason").val();
        	if(recommendReason!=""){
        		$.getJSON($('#initPath').val()+'/RecommendHotelController.do?method=recommendHotel',
            			{"hotelIds":hotelIds,"recommendReason":recommendReason},
            			function(json){
            				if(json.success){
            					alert("推荐成功！");
            					$("#recommendReason").val(''); //清空推荐理由
            					RecommendHotelList.oTable1.fnDraw();
            				}
            			}
            	);
        	}
        }
    }); 
};

//加载已推荐酒店列表
RecommendHotelList.recommendHotelList=function(){
	RecommendHotelList.oTable2 = $('#hotelList2').dataTable({
		'singleSelect' : false,
		'checkbox' : true,
		'queryColumns' : 'hotel.picture,hotel.hotelName,reason',
		'hiddenColumns':'',
		'params' : {},
		'fnInitComplete' : function(oSettings) {//表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback' : function(oSettings) {
			RecommendHotelList.oTable2.oSettings = oSettings;
		    var totalRecords = oSettings._iRecordsTotal;
		    RecommendHotelList.currentPage = oSettings._iDisplayStart;
			var pageSize = oSettings._iDisplayLength;
			var totalPage = totalRecords % pageSize == 0 ? totalRecords/pageSize : totalRecords/pageSize+1;
			RecommendHotelList.totalPage = parseInt(totalPage);
		   
			//第一页
			if(RecommendHotelList.currentPage==1){
				$("#hotelList2 tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
			}
			//最后一页
			if(RecommendHotelList.currentPage==RecommendHotelList.totalPage){
				$("#hotelList2 tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
			}
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=hotel.picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData['hotel.picture']+'" style="cursor:pointer" width="30px" height="30px" id="'+aData.picture+'" onmouseover="RecommendHotelList.zoomInPicture(\''+aData.picture+'\');" />');
			$(nRow).append('<td class="center"><a  name="up" href="javascript:void(0);" onclick="RecommendHotelList.upClick(this);return false;"><span class="sysicon siUp">&nbsp;</span></a><a name="down" href="javascript:void(0);" onclick="RecommendHotelList.downClick(this)"><span class="sysicon siDown">&nbsp;</span></a></td>');
			
			$(nRow).append('<td class="operation"><a href="javascript:void(0);" onclick="RecommendHotelList.cancleRrecommend(\''+aData.objId+'\');return false;">取消推荐</a></td>');
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/RecommendHotelController.do?method=list",
		'searchZone':'recommendHotelSearchForm'			
	});
};

$(document).ready(function(){
	// 加载tab页
	var $tabs = $('#epsTabs').tabs({}); 
	
	//加载未推荐酒店列表
	RecommendHotelList.oTable1 = $('#hotelList1').dataTable({
		'singleSelect' : false,
		'checkbox' : true,
		'queryColumns' : 'picture,hotelName,hotelAddress,star',
		'alias' : 'picture,hotelName,hotelAddress,starCN',
		'hiddenColumns':'',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			RecommendHotelList.oTable1.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData.picture+'" style="cursor:pointer" width="30px" height="30px" id="'+aData.picture+'" onmouseover="RecommendHotelList.zoomInPicture(\''+aData.picture+'\');" />');
			//添加操作按钮
			$(nRow).append('<td class="operation"><a href="javascript:void(0);" onclick="RecommendHotelList.recommendHotel(\''+aData.objId+'\');return false;">推荐</a></td>');
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/RecommendHotelController.do?method=getNoRecommendHotelList",
		'searchZone':'noRecommendHotelSearchForm'			
	});
	
	//查询未推荐酒店
	$("#query1").click(function() {
		RecommendHotelList.oTable1.fnDraw();
	});
	
	//查询已推荐酒店
	$("#query2").click(function() {
		RecommendHotelList.oTable2.fnDraw();
	});
	
	//批量推荐酒店
	$("#recommendHotelBatch").click(function(){
		RecommendHotelList.recommendHotel();
	});
	
	//批量取消推荐
	$("#cancleRecommendBatch").click(function(){
		RecommendHotelList.cancleRrecommend();
	});
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		RecommendHotelList.currentTabID = $(this).attr("id");
		if(RecommendHotelList.currentTabID == "tabs_toNoRecommend"){ //未推荐
			RecommendHotelList.oTable1.fnDraw();
			$("#recommendHotelSearchForm").hide();
			$("#noRecommendHotelSearchForm").show();
			$("#cancleRecommendBatchsLi").hide();
			$("#recommendHotelBatchLi").show();
		}else if(RecommendHotelList.currentTabID == "tabs_toRecommend"){ //已推荐
			if(!RecommendHotelList.oTable2){
				RecommendHotelList.recommendHotelList();
			}else{
				RecommendHotelList.oTable2.fnDraw();
			}
			$("#noRecommendHotelSearchForm").hide();
			$("#recommendHotelSearchForm").show();
			$("#recommendHotelBatchLi").hide();
			$("#cancleRecommendBatchsLi").show();
		}
	});
});

//设置上下移动的样式
RecommendHotelList.drawUpAndDownCss=function(){
	$("#hotelList2 tr a[name=up]>span").removeClass("siUpGray").addClass("siUp");
	$("#hotelList2 tr a[name=down]>span").removeClass("siDownGray").addClass("siDown");
	
	//顶级节点 
	if(RecommendHotelList.currentPage==1){
		$("#hotelList2 tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
	}
	if(RecommendHotelList.currentPage==RecommendHotelList.totalPage){
		$("#hotelList2 tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
	}
}

//向上
RecommendHotelList.upClick=function(obj){
	if($(obj).find("span").hasClass("siUpGray")) {
		return false;
	}
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).prev();
	var id = $(curRow).attr("objid");
	
	$.getJSON($('#initPath').val()+"/RecommendHotelController.do?method=updateSort",{"sourceObjId":id,"isToUp":"true"},function(json){})
	
	var firstRow = $("#hotelList2 tr").eq(1);
	var firstObjId = $(firstRow).attr("objid");
	if(firstObjId == id){ //如果移动的是第一行，则重画列表。
		RecommendHotelList.oTable2.fnDraw();
	}else{
		$(targetRow).before(curRow);	//把当前行放到目标行之前
		RecommendHotelList.drawUpAndDownCss();	//重画向上向下的样式
	}
};

//向下
RecommendHotelList.downClick=function(obj){
	if($(obj).find("span").hasClass("siDownGray")) {
		return false;
	}
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).next();
	var id = $(curRow).attr("objid");
	
	$.getJSON($('#initPath').val()+"/RecommendHotelController.do?method=updateSort",{"sourceObjId":id,"isToUp":"false"},function(json){})
	
	var lastRow = $("#hotelList2 tr:last");
	var lastObjId = $(lastRow).attr("objid");
	
	if(lastObjId == id){ //如果移动的是最后一行，则重画列表。
		RecommendHotelList.oTable2.fnDraw();
	}else{
		$(targetRow).after(curRow);	//把当前行放到目标行之后
		RecommendHotelList.drawUpAndDownCss();	//重画向上向下的样式
	}
};