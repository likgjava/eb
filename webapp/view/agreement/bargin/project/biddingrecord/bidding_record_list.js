var BiddingRecordDetailList = {};
BiddingRecordDetailList.oTable;

//加载报价记录列表
BiddingRecordDetailList.getBiddingRecordDetailList = function(){
	if(null==BiddingRecordDetailList.oTable){
		BiddingRecordDetailList.oTable = $('#biddingRecordDetailList').dataTable({
			'queryColumns' : 'goods.productName,project.projName,goodsPrice,barginTime',
			'hiddenColumns': 'goods.objId,project.objId',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				var productName = aData["goods.productName"];
				if(productName.length > 21){productName = productName.substring(0, 20)+"...";}
				$(nRow).find("td[name=goods.productName]").html('<a href="javascript:void(0);" onclick="BiddingRecordDetailList.showGoodsDetail(\''+aData["goods.objId"]+'\')">'+productName+'</a>');
				var projName = aData["project.projName"];
				if(projName.length > 21){projName = projName.substring(0, 20)+"...";}
				$(nRow).find("td[name=project.projName]").html('<a href="javascript:void(0);" onclick="BiddingRecordDetailList.showProjectDetail(\''+aData["project.objId"]+'\')">'+projName+'</a>');
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+"/BiddingRecordDetailController.do?method=list",
			"params":{'goods.objId':null,'goods.objId_op':'!='},
			'searchZone':'biddingRecordDetailSearchForm'
		});
	}else{
		BiddingRecordDetailList.oTable.fnDraw();
	}
}

//查看商品详情
BiddingRecordDetailList.showGoodsDetail = function(goodsId){
	common.geToGoodsDetail(goodsId);
}

//查看项目详情
BiddingRecordDetailList.showProjectDetail = function(projectId){
	window.open($("#initPath").val()+"/BargainProjectController.do?method=toProjectView&userType=supplier&projectId="+projectId);
	return false;
}

$(document).ready(function(){
	//加载列表
	BiddingRecordDetailList.getBiddingRecordDetailList();
})