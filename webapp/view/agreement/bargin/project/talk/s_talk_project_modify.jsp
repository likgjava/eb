<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/talk/s_talk_project_modify.js"></script>

<form:form id="talkProjectCreateForm" method="post" modelAttribute="project">
<input type="hidden" name="objId" id="objId" value="${project.objId}"/>
<c:set var="goodsId" value=""/>
<div class="formLayout form2Pa">
		<h4>项目信息</h4>
		<ul>
			<li class="fullLine">
				<label>项目名称：</label>
				<input type="text" name="projName" id="projName" value="${project.projName}" class="required" maxlength="100" size="40"/>
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label>议价开始时间：</label>
	        	<input type="text" name="evalStartTime" value="<fmt:formatDate value="${project.evalStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="evalStartTime" class="required" readonly="readonly" style="width: 150px;"/>
	 			<span class="eleRequired">*</span>
			</li>
			<li>
	    		<label>议价结束时间：</label>
	 			<input type="text" name="evalEndTime" value="<fmt:formatDate value="${project.evalEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="evalEndTime" class="required" readonly="readonly" style="width: 150px;"/>
	        	<span class="eleRequired">*</span>
	       </li>
		</ul>
</div>
<br/>
<div class="formLayout form2Pa">
	<h4>需求条目信息</h4>
	<table class="frontTableList" id="requireItemList_goods">
	<thead>
		<tr>
			<th width="300px">商品名称</th>
			<th>市场价(元)</th>
			<th>协议价(元)</th>
			<th>单价(元)</th>
			<th width="100px">商品数量</th>
			<th>计量单位</th>
			<th>金额(元)</th>
			<th class="operation">删除</th>
		</tr>
	</thead>
	<tbody id="goodsAndOption">
		<c:forEach var = "requireItem" items="${requireItemList}" varStatus="status">
		<tr id="${requireItem.objId }" class="goodsInfo" status="${status.index+1}">
			<td>
				<input type="hidden" id="goodsClass_${status.index+1}" name="goodsClass_${status.index+1}" value="${requireItem.goodsClass.objId }" class="required"/>
				<input type="hidden" id="goodsClassName_${status.index+1}" name="goodsClassName_${status.index+1}" value="${requireItem.goodsClass.goodsClassName }"/>
				<input type="hidden" id="purCategory_${status.index+1}" value="${requireItem.goods.purCategory.objId }" />
				<input type="hidden" id="categoryName_${status.index+1}" value="${requireItem.goods.purCategory.categoryName }" />
				
				<input type="hidden" name="objId_${status.index+1}" id="objId_${status.index+1}" value="${requireItem.objId}"/>
				<span id="productName_${status.index+1}">${requireItem.goods.productName }</span>
				<!-- 礼包 -->
				<c:if test="${!empty requireItem.requireGoodsGifts && fn:length(requireItem.requireGoodsGifts) > 0}">
					<a href="javascript:void(0);" title="商品礼包详情" onclick="TalkProjectCreateForm.showOrHideGoodsGift(this);return false;"><img src="view/resource/skin/sysicon/16/goods_gift.png" /></a>
				</c:if>
			</td>
			<td class="money">
				<span id="marketPrice_${status.index+1}"><fmt:formatNumber value="${requireItem.marketPrice }" pattern="#,##0.00#" /></span>
				<input type="hidden" id="marketPrice_${status.index+1}" value="${requireItem.marketPrice}" />
			</td>
			<td class="money"> 
				<span id="agreePrice_${status.index+1}"><fmt:formatNumber value="${requireItem.agreePrice }" pattern="#,##0.00#" /></span>
				<input type="hidden" id="agreePrice_${status.index+1}" value="${requireItem.agreePrice }" />
			</td>
			<td align="center"> 
				<input type="text" name="goodsPrice_${status.index+1}" id="goodsPrice_${status.index+1}" bakup="${requireItem.goodsPrice}" value="${requireItem.goodsPrice }" onchange="javascript:changeTotal_(this,'float');" style="width:60px" class="money"/>
			</td>
			<td align="center">
				<a href="javascript:void(0);" onclick="{
						$(this).parent().find('input[name=goodsQty_${status.index+1}]').val(Number($(this).parent().find('input[name=goodsQty_${status.index+1}]').val())>1?Number($(this).parent().find('input[name=goodsQty_${status.index+1}]').val())-1:1);
						changeTotal_($(this).parent().find('input[name=goodsQty_${status.index+1}]'),'int');return false;
					}">
					<img src="view/resource/skin/pubservice/img/bag_close.gif" />
				</a>
				<input name="goodsQty_${status.index+1}" id="goodsQty_${status.index+1}" bakup="${requireItem.goodsQty}" value="${requireItem.goodsQty }" onchange="javascript:changeTotal_(this,'int');" style="width:25px;" class="amount"/>
				<a href="javascript:void(0);" onclick="{
						$(this).parent().find('input[name=goodsQty_${status.index+1}]').val(Number($(this).parent().find('input[name=goodsQty_${status.index+1}]').val())+1);
						changeTotal_($(this).parent().find('input[name=goodsQty_${status.index+1}]'),'int');return false;
					}">
					<img src="view/resource/skin/pubservice/img/bag_open.gif" />
				</a>
			</td>
			<td align="center">
				<input type="text" name="goodsUnit_${status.index+1}" id="goodsUnit_${status.index+1}" value="${requireItem.goods.measureUnit }" style="width:25px"/>
			</td>
			<td class="money" id="total">
				<span class="money" id="goodsTotal_${status.index+1}"><fmt:formatNumber value="${requireItem.goodsTotal}" pattern="#,##0.00#" /></span>
				<input type="hidden" name="goodsTotal_${status.index+1}" id="goodsTotal_${status.index+1}" value="${requireItem.goodsTotal }">
			</td>
			<td class="operation">
				<a id="delRequireItem" href="javascript:void(0);" onclick="removeRequireItem(this,'${requireItem.objId}');return false;">删除</a>
				<input type="hidden" name="goods_${status.index+1}" id="goods_${status.index+1}" value="${requireItem.goods.objId }" />
			</td>
		</tr>
		
		<!-- 礼包 -->
		<c:if test="${!empty requireItem.requireGoodsGifts && fn:length(requireItem.requireGoodsGifts) > 0}">
		<tr class="hidden">
			<td colspan="8">
				<div>
				<table>
					<tr>
					<c:forEach items="${requireItem.requireGoodsGifts}" var="requireGoodsGift">
					<td>
					   <div style="float:left">
							<img class="goodsImg" src='<c:url value="AttachmentController.do?method=showImg&objId=${requireGoodsGift.goodsGift.giftPicture}" />' >
						 	<div class="fitting" style="float:left">
						 	${requireGoodsGift.goodsGift.giftName}<br/>
						 	￥<fmt:formatNumber value="${requireGoodsGift.giftPrice}" pattern="#,##0.00#"/><br/>
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
	</tbody>
	</table>
</div>
<div class="r">
	<ul>
		<li class="fullLine">数量总计：<strong><span id="countGoods">0</span></strong>件，金额总计：<strong>￥<span id="totalMoney">0.00</span></strong>元</li>
	</ul>
</div>

<div class="conOperation">
	<button id="save" type="button"><span>保存</span></button>
	<button id="submit" type="button"><span>提交</span></button>
	<button id="talkProjectReturnBut" type="button"><span>返回</span></button>
</div>
</form:form>