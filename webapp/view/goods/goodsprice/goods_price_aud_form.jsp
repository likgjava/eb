<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/goods/goodsprice/goods_price_aud_form.js"/>'></script>
<form id="goodsPriceForm">
<div class="formLayout form2Pa">
		<input type="hidden" name="objId" id="objId" value="${goodsPrice.objId }"/>
		<input type="hidden" name="goodsPriceSupplier.objId" id="goodsPriceSupplier.objId" value="${goodsPrice.goodsPriceSupplier.objId }"/>
 		<h4><span>商品行情</span></h4>
		<ul>
		<li>
			<label>使用区域名称：</label>
			<span>${goodsPrice.district.name }</span>
		</li>
		<li>
			<label for="">市场单价（元）：</label>
			<span>${goodsPrice.unitPrice }</span>
		</li>
		<li>
			<label for="">折扣率（%）：</label>
			<span>${goodsPrice.dscuRate }</span>
		</li>
		<li>
			<label for="">协议价（元）：</label>
			<span>${goodsPrice.prtcPrice }</span>
		</li>
		<li class="fullLine">
			<label for="">行情生效时间：</label>
			<span>${goodsPrice.efctDate }</span>到：<span>${goodsPrice.endDate }</span>
		</li>
		<li class="fullLine">
			<label for="">备注：</label>
			<span>${goodsPrice.remarks }</span>
		</li>
       </ul>

</div>

<!-- 选配行情 -->
<div class="formLayout form3Pa">
  <h4><span>选配行情</span></h4>
  <c:forEach var="goodsOptFitPrice" items="${goodsPrice.goodsOptFitPriceSet }">
	<ul>
	<li><label>选配内容：</label><span>${goodsOptFitPrice.goodsOptionalFitting.optionContent }</span></li>
	<li><label>规格型号描述：</label><span>${goodsOptFitPrice.goodsOptionalFitting.specification }</span></li>
	<li><label>相对价（元）：</label><span>${goodsOptFitPrice.relativePrice }</span></li>
	</ul>
  </c:forEach>
</div>

<div class="formLayout formPa">
	<h4><span>审核信息</span></h4>
	<ul>
	<li class="formTextarea">
	<label for="opinion">审核意见：</label>
	<textarea name="opinion">${goodsPrice.opinion }</textarea>
	</li>
	</ul>
</div>
</form>

<div class="conOperation"> 
  <button class="largeBtn" id="save" type="button" onclick="goodsPriceAuditForm.audit('pass')"><span>通过</span></button>  
  <button class="largeBtn" id="submit" type="button" onclick="goodsPriceAuditForm.audit('nopass')"><span>不通过</span></button>  
  <button class="largeBtn" id="return" type="button" onclick="goodsPriceAuditForm.close()"><span>关闭</span></button>
</div>