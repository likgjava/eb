<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/talk/start_talk_project.js"></script>

<form id="talkProjectStartForm" name="BargainProjectStartForm" method="post" >
	<input type="hidden" name="taskItemIds" id="taskItemIds" value="${taskItemIds}" />
	<input type="hidden" name="requirementIds" id="requirementIds" value="${param.requirementIds}" />
	<input type="hidden" name="budgetTotalMoney" id="budgetTotalMoney"/>

	<div class="formLayout form2Pa">
		<h4>填写项目信息</h4>
		<ul>
			<li class="fullLine">
				<label>项目名称：</label>
				<input type="text" name="projName" id="projName" class="required" value="" maxlength="100" size="40"/>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label>议价开始时间：</label>
	        	<input type="text" name="evalStartTime" value="" id="evalStartTime" class="required" size="38" readonly="readonly"/>
	 			<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
	    		<label>议价结束时间：</label>
	 			<input type="text" name="evalEndTime" value="" id="evalEndTime" class="required" size="38" readonly="readonly"/>
	        	<span class="eleRequired">*</span>
	       </li>
		</ul>
	</div>
			
	<h4>需求条目信息</h4>
	<table class="frontTableList" id="requireItemList">
	<thead>
		<tr>
			<th>商品名称</th>
			<th>市场价</th>
			<th>协议价</th>
			<th>预算</th>
			<th>商品数量</th>
			<th>计量单位</th>
			<th>金额</th>
			<th >删除</th>
		</tr>
	</thead>
	<tbody id="goodsAndOption">
		<c:forEach var = "shoppingCartItem" items="${ShoppingCartItemList}" varStatus="status">
			<tr id="${shoppingCartItem.objId }" class="goodsInfo" status="${status.index+1}">
				<td>
					<span id="productName${status.index+1}" title="${shoppingCartItem.goods.productName }">
					<c:choose>
					<c:when test="${fn:length(shoppingCartItem.goods.productName) > 15}">
						${fn:substring(shoppingCartItem.goods.productName,0,14) }...
					</c:when>
					<c:otherwise>
						${shoppingCartItem.goods.productName }
					</c:otherwise>
					</c:choose>
					</span>
					<!-- 礼包 -->
					<c:if test="${!empty shoppingCartItem.cartGoodsGifts && fn:length(shoppingCartItem.cartGoodsGifts) > 0}">
						<a href="javascript:void(0);" title="商品礼包详情" onclick="TalkProjectStartForm.showOrHideGoodsGift(this);return false;"><img src="view/resource/skin/sysicon/16/goods_gift.png" /></a>
					</c:if>
					<input type="hidden" name="goodsId" value ="${ shoppingCartItem.goods.objId}">
				</td>
				<td class="money"> 
					<span id="marketPrice${status.index+1}"><fmt:formatNumber value="${shoppingCartItem.marketPrice }" pattern="#,##0.00#" /></span>
					<input type="hidden" id="marketPrice${status.index+1}" name="marketPrice${status.index+1}" value="${shoppingCartItem.marketPrice }" />
				</td>
				<td class="money">
					<span id="agreePrice${status.index+1}"><fmt:formatNumber value="${shoppingCartItem.agreePrice }" pattern="#,##0.00#" /></span>
					<input type="hidden" id="agreePrice${status.index+1}" name="agreePrice${status.index+1}" value="${shoppingCartItem.agreePrice }" />
				</td>
				<td class="money"> 
					<input type="text" name="goodsPrice${status.index+1}" id="goodsPrice${status.index+1}" onchange="javascript:changeTotal(this,'float');" value="${shoppingCartItem.agreePrice }" class="required money" style="width:60px"/>
				</td>
				<td class="center">
					<a href="javascript:void(0);" onclick="{
							$(this).parent().find('input[name=goodsQty${status.index+1}]').val(Number($(this).parent().find('input[name=goodsQty${status.index+1}]').val())>1?Number($(this).parent().find('input[name=goodsQty${status.index+1}]').val())-1:1);
							changeTotal($(this).parent().find('input[name=goodsQty${status.index+1}]'),'int');return false;
						}">
						<img src="view/resource/skin/pubservice/img/bag_close.gif" />
					</a>
					<input name="goodsQty${status.index+1}" class="required digits amount" id="goodsQty${status.index+1}" value="${shoppingCartItem.goodsQty }" onchange="javascript:changeTotal(this,'int');" style="width:25px;" />
					<a href="javascript:void(0);" onclick="{
							$(this).parent().find('input[name=goodsQty${status.index+1}]').val(Number($(this).parent().find('input[name=goodsQty${status.index+1}]').val())+1);
							changeTotal($(this).parent().find('input[name=goodsQty${status.index+1}]'),'int');return false;
						}">
						<img src="view/resource/skin/pubservice/img/bag_open.gif" />
					</a>
				</td>
				<td>
					<input type="text" name="goodsUnit${status.index+1}" id="goodsUnit${status.index+1}" value="${shoppingCartItem.goods.measureUnit }" style="width:25px"/>
				</td>
				<td class="money">
					￥<span class="money" id="total${status.index+1}"><fmt:formatNumber value="${shoppingCartItem.goodsTotal }" pattern="#,##0.00#"/></span>
					<input type="hidden" name="goodsTotal${status.index+1}" id="goodsTotal${status.index+1}" value="${shoppingCartItem.goodsTotal }">
				</td>
				<td>
					<a id="delRequireItem" href="javascript:void(0);" onclick="removeRequireItem(this);return false;">删除</a>
					<input type="hidden" name="goods${status.index+1}" id="goods${status.index+1}" value="${shoppingCartItem.goods.objId }" />
				</td>
			</tr>
			
			<!-- 礼包 -->
			<c:if test="${!empty shoppingCartItem.cartGoodsGifts && fn:length(shoppingCartItem.cartGoodsGifts) > 0}">
			<tr class="hidden">
				<td colspan="7">
					<div>
					<table>
						<tr>
						<c:forEach items="${shoppingCartItem.cartGoodsGifts}" var="shoppingCartGoodsGift">
						<td>
						   <div style="float:left">
								<img class="goodsImg" src='<c:url value="AttachmentController.do?method=showImg&objId=${shoppingCartGoodsGift.goodsGift.giftPicture}" />' >
							 	<div class="fitting" style="float:left">
							 	${shoppingCartGoodsGift.goodsGift.giftName}<br/>
							 	￥<fmt:formatNumber value="${shoppingCartGoodsGift.giftPrice}" pattern="#,##0.00#"/><br/>
							 	</div>
						   </div>
						</td>
						</c:forEach>
						</tr>
					</table>
					</div>
				</td>
			</tr>
			</c:if>
		</c:forEach>
		
		<!-- 按采购需求生成 -->
		<c:forEach var = "requirement" items="${requirementList}" varStatus="status">
			<tr id="${requirement.objId }tempTR" class="goodsInfo" status="${status.index+1}">
				<td>
					<span id="productName${status.index+1}"></span>
					<span>
					<a href="javascript:void(0);" name="selectGoods" onclick="selectGoods(this);">选择商品</a>
					</span>
					<input type="hidden" id="goodsId${status.index+1}" name="goodsId" value ="">
					<input type="hidden" id="goodsName${status.index+1}" name="goodsName" value ="">
					<input type="hidden" id="goodsRePrice${status.index+1}" name="goodsRePrice" value ="">
					
					<input type="hidden" id="goodsClassId${status.index+1}" name="goodsClassId" value="" />
					<input type="hidden" id="goodsClassName${status.index+1}" name="goodsClassName" value="" />
				</td>
				<td class="money"> 
					<span id="marketPrice${status.index+1}">--</span>
					<input type="hidden" id="marketPrice${status.index+1}" name="marketPrice${status.index+1}" value=""/>
				</td>
				<td class="money">
					<span id="agreePrice${status.index+1}">--</span>
					<input type="hidden" id="agreePrice${status.index+1}" name="agreePrice${status.index+1}" value=""/>
				</td>
				<td class="money"> 
					<input type="text" name="goodsPrice${status.index+1}" id="goodsPrice${status.index+1}" onchange="javascript:changeTotal(this,'float');" value="${requirement.purchaseBudget }" class="required money" style="width:60px"/>
				</td>
				<td class="center">
					<a href="javascript:void(0);" onclick="{
							$(this).parent().find('input[name=goodsQty${status.index+1}]').val(Number($(this).parent().find('input[name=goodsQty${status.index+1}]').val())>1?Number($(this).parent().find('input[name=goodsQty${status.index+1}]').val())-1:1);
							changeTotal($(this).parent().find('input[name=goodsQty${status.index+1}]'),'int');return false;
						}">
						<img src="view/resource/skin/pubservice/img/bag_close.gif" />
					</a>
					<input name="goodsQty${status.index+1}" class="required digits amount" id="goodsQty${status.index+1}" value="${requirement.purchaseQty}" onchange="javascript:changeTotal(this,'int');" style="width:25px;" />
					<a href="javascript:void(0);" onclick="{
							$(this).parent().find('input[name=goodsQty${status.index+1}]').val(Number($(this).parent().find('input[name=goodsQty${status.index+1}]').val())+1);
							changeTotal($(this).parent().find('input[name=goodsQty${status.index+1}]'),'int');return false;
						}">
						<img src="view/resource/skin/pubservice/img/bag_open.gif" />
					</a>
				</td>
				<td>
					<input type="text" name="goodsUnit${status.index+1}" id="goodsUnit${status.index+1}" value="" style="width:25px"/>
				</td>
				<td class="money">
					￥<span class="money" id="total${status.index+1}"><fmt:formatNumber value="${requirement.purchaseQty * requirement.purchaseBudget}" pattern="#,##0.00#"/></span>
					<input type="hidden" name="goodsTotal${status.index+1}" id="goodsTotal${status.index+1}" value="${requirement.purchaseQty * requirement.purchaseBudget}">
				</td>
				<td>
					<a id="delRequireItem" href="javascript:void(0);" onclick="removeRequireItem(this);return false;">删除</a>
					<input type="hidden" name="goods${status.index+1}" id="goods${status.index+1}" value="" />
					<input type="hidden" name="categoryId${status.index+1}" id="categoryId${status.index+1}" value="${requirement.category.objId}" />
					<input type="hidden" name="categoryName${status.index+1}" id="categoryName${status.index+1}" value="${requirement.category.categoryName}" />
				</td>
			</tr>
		</c:forEach>
		
	</tbody>
	</table>
	<div class="r">
		<ul>
			<li class="fullLine">数量总计：<strong><span id="countGoods">0</span></strong>件，金额总计：<strong>￥<span id="totalMoney">0.00</span></strong>元</li>
		</ul>
	</div>
			
	<div class="formLayout form3Pa">
		<h4><span>议价供应商</span></h4>
		<ul id="talkSupplier"></ul>
	</div>
			
	<div class="conOperation">
		<button id="save" type="button"><span>保存</span></button>
		<button id="submit" type="button"><span>提交</span></button>
		<button id="return" name="historyBackBtn" type="button"><span>返回</span></button>
	</div>
</form>
