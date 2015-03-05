<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/PointsMall.css"/>

<div id="contentSub" class="PointsMallLeft">
	<div class="PointsMallCol firstCol newsInfo">
	  <%@ include file="/view/staticpags/load/jifenmall/desc.html" %>
	</div>
	<div id="recommendGift">
	</div>
</div>

<div id="contentMain" class="PointsMallCenter">
	<div id="conTitle" class="hidden">
		<div class="navCurrent">
		</div>
	</div>
	
	<div id="conBody">
		<div class="firstCol">
			<h2>虚拟物品兑换</h2>
			<c:forEach var ="virtualGift" items="${virtualGiftList}" >
				<ul>
					<li class="giftSmallImgLi"><a href="javascript:gift_list.showDetail('${virtualGift.objId}');"><img class="giftImg smallSize" src="<c:url value="AttachmentController.do?method=showImg&objId=${virtualGift.picture}" />" /></a></li>
					<li class="center"><a href="javascript:gift_list.showDetail('${virtualGift.objId}');">${virtualGift.giftName}</a></li>
					<li class="center">兑换积分:<em class="em"><c:forEach var="giftExchange" items="${virtualGift.giftExchangeRuleSet}" end="0">${giftExchange.score}</c:forEach></em> 积分</li>
					<li class="center">已兑换${virtualGift.virtualRecordQty}人[${virtualGift.totalEvalQty}人评论]</li>
					<li class="center"><input class="PointsMallBtn" type="button" value="详情" onclick="gift_list.showDetail('${virtualGift.objId}')"/></li>
				</ul>
        	</c:forEach>
        	<span class="more"><a class="right" href="javascript:void(0);" onclick="showList('','','01');">更多</a></span>
        </div>
        <div class="secondCol">
          <div class="effect"><img src="<%=request.getContextPath()%>/view/resource/images/effectPic.jpg" /></div>
          <!--.effect-->
          <div class="MallGg newsInfo" >
          	<%@ include file="/view/staticpags/load/jifenmall/bulletin.html" %>
          </div>
          <!--.MallGg-->
        </div>
        <!--.secondCol-->
        <div class="threeCol">
          	<div id="epsTabs">
            	<ul>
					<c:forEach var= "giftSeries" items = "${giftSeriesFirstList}" varStatus="status">
						<li><a href="#giftSeriesInfo" onclick="show_gift_index.loadGiftSeriesInfo('${giftSeries.objId}');"><span>${giftSeries.name }</span></a></li>
	            	</c:forEach>
	            </ul>
            <div id="giftSeriesInfo" class="TabbedPanelsContent">
            	<c:forEach var = "seriesAndGift" items="${seriesAndReallyGift}">
              	<c:if test="${fn:length(seriesAndGift['giftList'])>0}">
              	
              	<h3><span><a href="javascript:void(0);" onclick="showList('${seriesAndGift['giftSeries'].objId}','','00');">${seriesAndGift['giftSeries'].name}</a></span></h3>
            		<div class="giftList">
            		<c:forEach var ="gift" items="${seriesAndGift['giftList']}">
				<ul>
				
					<li class="giftBigImgLi"><a href="javascript:gift_list.showDetail('${gift.objId}');"><img class="giftImg bigSize" src="<c:url value="AttachmentController.do?method=showImg&objId=${gift.picture}" />" /></a></li>
					<li class="center"><a href="javascript:gift_list.showDetail('${gift.objId}');">${gift.giftName}</a></li>
					<li class="center">兑换积分:<em class="em"><c:forEach var="giftExchange" items="${gift.giftExchangeRuleSet}" end="0">${giftExchange.score}</c:forEach></em> 积分</li>
					<li class="center">已兑换${gift.realRecordQty}人[${gift.totalEvalQty}人评论]</li>
					<li class="center">
						<input type="button" class="PointsMallBtn" value="兑 换" onclick="exchange('${gift.objId}','<c:forEach var="giftExchangeRule" items="${gift.giftExchangeRuleSet}" end="0">${giftExchangeRule.objId}</c:forEach>','1','${gift.giftType }');" />
						<input type="button" class="PointsMallBtn" value="详 情" onclick="gift_list.showDetail('${gift.objId}');"/>
					</li>
				</ul>
            		</c:forEach>
            		</div>
            	</c:if>
            	</c:forEach>
            </div>
          </div>
        </div>
      <!--.threeCol-->
    </div>
</div>
<div id="contentSupp" class="hidden"></div>



<script type="text/javascript">

//兑换
exchange = function(giftId,ruleId,qty,type){
	//实物
	if(type=="00"){
		$("#conBody").loadPage($("#initPath").val()+"/RealGiftRecordController.do?method=toExchangeView&giftId="+giftId+"&count="+qty+"&eruleId="+ruleId);
	}else{
		$("#conBody").loadPage($("#initPath").val()+"/VirtualGiftRecordController.do?method=toExchangeView&giftId="+giftId+"&eruleId="+ruleId+"&count="+qty);
	}
}

//跳至列表页面
showList = function(seriesId,keyWord,type) {
	$("#sysContent").loadPage($('#initPath').val()+'/GiftShowController.do?method=toGiftList&rp=21&page=1&seriesId='+seriesId+'&giftType='+type) ;
}

var show_gift_index  = {};

//加载某个顶级分类下的子分类和礼品信息
show_gift_index.loadGiftSeriesInfo = function(giftSeriesId){
	$("#giftSeriesInfo").loadPage($("#initPath").val()+"/GiftShowController.do?method=toChangeRootSeries&giftSeriesId="+giftSeriesId);
}

$(document).ready(function(){
	//调整页面布局 
	changeTabsCss("goToGift");
	
	//选中查询下拉框
	keyWordTypeChange('7');
	
	$('#epsTabs').tabs();//加载tabs
	$("#recommendGift").loadPage($("#initPath").val()+"/GiftShowController.do?method=getRecommendGift");//加载推荐礼品
})
	
</script>