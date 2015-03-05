<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var gift_list={};
//跳至详情页面
gift_list.showDetail = function(giftId,tabId) {
	var params = "";
	if(tabId) {
		params += "::tabId=" + tabId;
	}
	var targetUrl = $('#initPath').val()+"/GiftShowController.do?method=getGiftInfo::objId=" + giftId+"::giftSeries="+$("#giftSeriesId").val() + params;
	var contentSubUrl = $('#initPath').val()+'/GiftShowController.do?method=getRecommendGift';
	window.open( $('#initPath').val()+"/IndexViewController.do?method=index&contentMainUrl="+targetUrl+"&contentSubUrl="+contentSubUrl );
}

//兑换
exchange = function(giftId,ruleId,qty,type){
	if(!common.isLogin(true)){return;}
	
	//实物
	if(type=="00"){
		$("#conBody").loadPage($("#initPath").val()+"/RealGiftRecordController.do?method=toExchangeView&giftId="+giftId+"&count="+qty+"&eruleId="+ruleId);
	}else{
		$("#conBody").loadPage($("#initPath").val()+"/VirtualGiftRecordController.do?method=toExchangeView&giftId="+giftId+"&eruleId="+ruleId+"&count="+qty);
	}
}
</script>

<div class="PointsMallCol secondCol" >
<h2>推荐商品</h2>
<c:forEach var= "recommendGift" items="${recommendList}">
<ul>
	<li class="giftBigImgLi"><a href="javascript:gift_list.showDetail('${recommendGift.objId}');"><img class="giftImg bigSize" src="<c:url value="AttachmentController.do?method=showImg&objId=${recommendGift.picture}" />" /></a></li>
	<li class="center"><a href="javascript:gift_list.showDetail('${recommendGift.objId}');">${recommendGift.giftName}</a></li>
	<li class="center">兑换积分:<em class='em'><c:forEach var="giftExchange" items="${recommendGift.giftExchangeRuleSet}" end="0">${giftExchange.score}</c:forEach></em> 积分</li>
	<li class="center">已兑换${recommendGift.virtualRecordQty+recommendGift.realRecordQty}人[${recommendGift.totalEvalQty}人评论]</li>
	<li class="center">
		<input type="button" class="PointsMallBtn" value="兑 换" onclick="exchange('${recommendGift.objId}','<c:forEach var="giftExchangeRule" items="${recommendGift.giftExchangeRuleSet}" end="0">${giftExchangeRule.objId}</c:forEach>','1','${recommendGift.giftType }');" />
		<input type="button" class="PointsMallBtn" value="详 情" onclick="gift_list.showDetail('${recommendGift.objId}');" />
	</li>
</ul>
</c:forEach>
</div>
