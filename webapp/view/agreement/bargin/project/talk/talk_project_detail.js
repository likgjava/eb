/** 议价项目详情页面 */
var TalkProjectDetail = {};

//显示采购人详情
TalkProjectDetail.showBuyerDetail = function(buyerId) {
	common.geToBuyerDetail(buyerId);
}

//显示商品详情
TalkProjectDetail.showGoodsDetail = function(goodsId) {
	common.geToGoodsDetail(goodsId);
}

//加载最低报价和聊天记录或隐藏
TalkProjectDetail.loadBiddingAndChat = function(supplierId) {
	if($('#biddingAndChat_'+supplierId).html() == ''){
		$('#biddingAndChat_'+supplierId).loadPage($('#initPath').val()+"/TalkProjectController.do?method=loadTalkBiddingAndChatView&projId="+$('#projectId').val()+"&supplierId="+supplierId);
	}else{
		if($('#biddingAndChat_'+supplierId).hasClass('hidden')){
			$('#biddingAndChat_'+supplierId).removeClass('hidden');
		}else{
			$('#biddingAndChat_'+supplierId).addClass('hidden');
		}
	}
}

//高亮显示最低报价
TalkProjectDetail.highShow = function(goodsId) {
	if($('#requireItemListT').find('tr[name=goods_'+goodsId+']').hasClass('high_show')){
		$('#requireItemListT').find('tr[name^=goods_]').removeClass('high_show');
		$('div[name^=goods_]').removeClass('high_show');
		
		$('#requireItemListT').find('tr[name=goods_'+goodsId+']').removeClass('high_show');
		$('div[name=goods_'+goodsId+']').removeClass('high_show');
	}else{
		$('#requireItemListT').find('tr[name^=goods_]').removeClass('high_show');
		$('div[name^=goods_]').removeClass('high_show');
		
		$('#requireItemListT').find('tr[name=goods_'+goodsId+']').addClass('high_show');
		$('div[name=goods_'+goodsId+']').addClass('high_show');
	}
}

//查看全部报价信息
TalkProjectDetail.showAllBiddingRecord = function(supplierId) {
	$.epsDialog({
		id:"biddingRecordId", 
		title:"报价信息",
		url:$("#initPath").val()+"/TalkProjectController.do?method=toTalkBiddingRecordAllView&projId="+$('#projectId').val()+"&supplierId="+supplierId
	});
}

//显示或隐藏商品礼包
TalkProjectDetail.showOrHideGoodsGift = function(obj){
	$(obj).parent().parent().next().toggle();
}

//操作
TalkProjectDetail.operator = function(opType){
	
	var projectId = $("#projectId").val(); //项目id
	var userType = $("#userType").val(); //用户类型
	
	//修改项目
	if("update"==opType){
		TalkProjectDetail.setHrefUrl();
		
		$('#conBody').loadPage($('#initPath').val()+"/TalkProjectController.do?method=toUpdateTalkProjectView&objId="+projectId);
	}
	//提交
	else if("submit"==opType){
		var jsonObj = {};
		jsonObj.objId = projectId;
		jsonObj.auditStatus = '01';//通过
		jsonObj.useStatus = '01';//有效
		$.getJSON($('#initPath').val()+'/TalkProjectController.do?method=updateTalkProject',jsonObj, function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			window.location.reload();
		});
	}
	//进入议价厅
	else if("intoHall"==opType){
		var url = $('#initPath').val()+"/TalkProjectController.do?method=toBuyerTalkHall&inType="+userType+"&objId="+projectId;
		loadPage_openModelWindow(url,"970");
		window.location.reload();
	}
	//确认成交结果
	else if("confirm"==opType){
		TalkProjectDetail.setHrefUrl();
		
		$("#conBody").loadPage($("#initPath").val()+"/BuyResultXyghController.do?method=toConfirmResultBySupplierView&projectId="+projectId );
	}
	//查看成交结果
	else if("viewResult"==opType){
		TalkProjectDetail.setHrefUrl();
		
		$("#conBody").loadPage($("#initPath").val()+"/BuyResultXyghController.do?method=toResultDetailView&projectId="+projectId+"&userType="+userType);
	}
	//评价
	else if("evaluate"== opType){
		$.epsDialog({
			id:"evaluateDailog", 
			title:"对参与项目的机构评价",
			url:$("#initPath").val()+"/view/agreement/bargin/project/project_evaluate_div.jsp?userType="+userType+"&projectId="+projectId+"&projectName="+native2ascii($("#projName").html())
		});
	}
	//投诉
	else if("complaints"== opType){
		TalkProjectDetail.openComplainDiv(projectId,userType,'tell');
	}
	//举报
	else if("report"== opType){
		TalkProjectDetail.openComplainDiv(projectId,userType,'complain');
	}
}

//设置跳转返回路径
TalkProjectDetail.setHrefUrl = function(){
	$("#hrefUrl").val( $("#initPath").val()+'/TalkProjectController.do?method=toTalkProjectDetailView&userType='+$("#userType").val()+'&objId='+$("#projectId").val() );
}

//投诉/举报
TalkProjectDetail.openComplainDiv = function(projectId,userType,type){
	$.epsDialog({
		id:"complainDiv", 
		title:"选择参与项目的机构",
		url:$("#initPath").val()+"/view/agreement/bargin/project/project_complain_div.jsp?userType="+userType+"&projectId="+projectId+"&type="+type+"&ebuyMethod=05"
	});
}

$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val($("#initPath").val()+"/BargainProjectController.do?method=orgProjectQueryListView");
})