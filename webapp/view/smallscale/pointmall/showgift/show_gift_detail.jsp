<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/PointsMall.css"/>

<input type="hidden" id="tabId" value="${tabId}">
<input type="hidden" id="giftId" value="${gift.objId}" >

<!--主内容-->
<div id="conTitle">
	<div class="navCurrent">
		<a href="javascript:void(0);" onclick="searchByTitle('${goodsClass.goodsClassCode }');return false;" >${goodsClass.goodsClassName }</a>
		礼品详情
	</div>
</div>
<div id="conBody">
	<div class="imgAndInfo smallImg">
		<div id="showImg" >
			<div class="short">
				<img src="<c:url value="AttachmentController.do?method=showImg&objId=${gift.picture}" />">
			</div>
		</div>
		<div id="showInfo">
				<ul class="meta">	
				<li class="detail-title"><span>礼品名称：</span>${gift.giftName}</li>
				<li class="detail-title"><span>礼品编号：</span>${gift.giftCode}</li>
				<li class="detail-title"><span>礼品类型：</span>${gift.giftTypeCN}</li>
				<li class="detail-title"><span>库存容量：</span><label id="exchangeCount">${gift.exchangeCount}</label></li>
				<li class="detail-title"><span>兑换规则：</span> 
					<c:forEach var="giftExchangeRule" items="${gift.giftExchangeRuleSet}" varStatus="statu">
						<input type="radio" name="giftExchangeRule" value="${giftExchangeRule.objId}" <c:if test="${statu.index==0}">checked=checked</c:if>>${giftExchangeRule.amount}&nbsp;元+${giftExchangeRule.score}&nbsp;分
					</c:forEach>
				</li>
				<c:if test="${param.isEdit!='false'}">
					<li class="detail-title" style="vertical-align:middle; height:25px; line-height:25px"><span>购买数量：</span><input name="giftQty" class="digits" type="text" value="1" style="width:25px;"/>
					&nbsp;<button type="button" style="vertical-align:inherit;" onclick="gift_detail.exchange('${gift.objId}','${gift.giftType}');" class="favBtn" height="25px">兑换</button>
					</li>
				</c:if>
				</ul>
		</div>
	</div>
	<div id="epsTabs" class="epsTabs">
		<ul>
			<li><a href="#baseParam" id="baseParamTab"><span>礼品参数</span></a></li>
			<li><a href="#recordList" id="recordListTab"><span>成交记录</span></a></li>
			<li><a href="#evaluateList" id="evaluateListTab"><span>评价信息</span></a></li>
		</ul>
		<div id="baseParam" class="formLayout">
			<!-- 有商品时 -->
			<c:if test="${gift.goods!=null}">
				<div class="attributes" id="attributes"> 
					<dl class="attributes-list">
			   			<dt><em>商品属性：</em></dt>
			   			<dd><c:choose>
				   			<c:when test="${gift.goods.special }"><span class="award_star_gold_3">特供商品</span></c:when>
				   			<c:otherwise><span class="award_star_delete">非特供商品</span></c:otherwise>
			   			</c:choose></dd>
			   			<dd><c:choose>
				   			<c:when test="${gift.goods.isCustom }"><span class="ruby">自定义商品</span></c:when>
				   			<c:otherwise><span class="ruby_delete">非自定义商品</span></c:otherwise>
			   			</c:choose></dd>
			   			<dd><c:choose>
				   			<c:when test="${gift.goods.isAccessory }"><span class="cog">零配件</span></c:when>
				   			<c:otherwise><span class="cog_delete">非零配件</span></c:otherwise>
			   			</c:choose></dd>
			   			<dd><c:choose>
				   			<c:when test="${gift.goods.soleToSell }"><span class="coins">可单独出售</span></c:when>
				   			<c:otherwise><span class="coins_delete">不可单独出售</span></c:otherwise>
			   			</c:choose></dd>
			   		</dl>
					<ul class="attributes-list">
						<li> 自主创新认定编号：${gift.goods.creationCode}<c:if test="${gift.goods.creationCode == null}">无</c:if></li>
						<li> 环境标志产品编号：${gift.goods.environmentLabel}<c:if test="${gift.goods.environmentLabel == null}">无</c:if></li>
						<li> 节能产品编号编号：${gift.goods.energySavingProductNo}<c:if test="${gift.goods.energySavingProductNo == null}">无</c:if></li>
						<li> 含有密码技术的信息产品编号：${gift.goods.cryptographyTechCode}<c:if test="${gift.goods.cryptographyTechCode == null}">无</c:if></li>
					</ul>
	    		</div>
		    	<div>
		    		<h4><span>基本参数</span></h4>
		    		<table class="frontTableList">
		    			<c:choose>
		    				<c:when test="${gift.goods.goodsParamSet != null && fn:length(gift.goods.goodsParamSet) > 0}">
			    				<c:forEach var="goodsParam" items="${gift.goods.goodsParamSet}">
									<c:if test="${goodsParam.goodsClassParam.isLeaf == false}">
										<tr><td colspan="2"><em>${goodsParam.goodsClassParam.paramName}</em></td>
											<c:forEach var="paramLeaf" items="${gift.goods.goodsParamSet}">
												<c:if test="${paramLeaf.goodsClassParam.isLeaf == true && paramLeaf.goodsClassParam.parent.objId==goodsParam.goodsClassParam.objId && fn:length(paramLeaf.paramValue) > 0}">
													<tr>
														<td><label>${paramLeaf.paramName}</label></td>
														<td >${paramLeaf.paramValue}</td>
													</tr>
												</c:if>
											</c:forEach>
										</tr>
									</c:if>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td>${goods.spec }</td></tr>
							</c:otherwise>
						</c:choose>
		    		</table>
		    	</div>
    		<div>
    			<h4><span>商品功能描述</span></h4>
    			${gift.goods.functionIntro }
    		</div>
			</c:if>
			<c:if test="${gift.goods==null}">
			<div>
				<h4><span>礼品功能描述</span></h4>
				${gift.giftComment }
			</div>
			</c:if>
		</div>
		<div id="recordList">
			<div class="formLayout form2Pa">
			<table>
			<c:forEach var="realGiftRecord" items="${realGiftRecordList}">
				<tr>
   				    <td><label>兑换人：</label><span>${realGiftRecord.createUser.usName}</span></td>
   					<td><label>兑换数量：</label><span>${realGiftRecord.giftCount}</span></td>
   					<td><label>兑换时间：</label><span><fmt:formatDate value="${realGiftRecord.dealTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
    			</tr>
			</c:forEach>
			<c:forEach var="virtualGiftRecord" items="${virtualGiftRecordList}">
   				<tr>
   				    <td><label>兑换人：</label><span>${virtualGiftRecord.createUser.usName}</span></td>
   					<td><label>兑换数量：</label><span>1</span></td>
   					<td><label>兑换时间：</label><span><fmt:formatDate value="${virtualGiftRecord.dealTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
   				</tr>
			</c:forEach>
			</table>
			</div>
		</div>
		<div id="evaluateList">评价信息</div>
	</div>
	<!-- 评价from -->
	<div id="evaluateDiv"><%@ include file="/view/smallscale/pointmall/gift_comment_form.jsp"%></div>
</div>

<script type="text/javascript">
var gift_detail = {};

//兑换
gift_detail.exchange = function(giftId,type){
	if(!common.isLogin(true)){return;}
	var ruleId = $("input[name=giftExchangeRule]:checked").val();
	var giftQty = $("input[name=giftQty]").val();
	if(!giftQty||giftQty>Number($("#exchangeCount").html())){
		alert("请输入正确的兑换数量！");return;
	}
	exchange(giftId,ruleId,giftQty,type);
}

//兑换
exchange = function(giftId,ruleId,qty,type){
	//实物
	if(type=="00"){
		$("#conBody").loadPage($("#initPath").val()+"/RealGiftRecordController.do?method=toExchangeView&giftId="+giftId+"&count="+qty+"&eruleId="+ruleId);
	}else{
		$("#conBody").loadPage($("#initPath").val()+"/VirtualGiftRecordController.do?method=toExchangeView&giftId="+giftId+"&eruleId="+ruleId+"&count="+qty);
	}
}

$(document).ready(function(){

	//过滤数量
	$("input[name=giftQty]").inputFillter({type:'int'});

	//调整页面布局 
	changeTabsCss("goToGift");
	
	//选中查询下拉框
	keyWordTypeChange('7');

	$("#contentSub").removeClass().addClass("contentSub").addClass("PointsMallLeft");
	$("#contentMain").removeClass().addClass("contentMain").addClass("index2paRR");//样式调整 
	$("#contentSupp").addClass("hidden");

	$('#epsTabs').tabs();//加载tabs

	//加载评价列表
	$("#evaluateListTab").click(function(){
		$("#evaluateList").loadPage($("#initPath").val()+"/view/smallscale/pointmall/gift_comment_list.jsp");
	})
});
</script>