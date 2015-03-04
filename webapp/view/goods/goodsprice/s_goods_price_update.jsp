<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/goods/goodsprice/s_goods_price_update.js"/>'></script>
<form id="priceForm">
<div class="formLayout form2Pa">
	
		<input type="hidden" name="objId" id="objId" value="${goodsPrice.objId }"/>
		<input type="hidden" name="goodsPriceSupplier.objId" id="goodsPriceSupplier.objId" value="${goodsPrice.goodsPriceSupplier.objId }"/>
 		<h4><span>商品行情</span></h4>
		<ul>
		<li>
			<label>使用区域名称：</label>
			<input type="hidden" id="district.objId" name="district.objId" value="${goodsPrice.district.objId }">
			<input class="sysicon siSearch required" type="text" id="district.name" name="district.name" value="${goodsPrice.district.name }" onclick="goodsPriceCreate.selectDistrict()" readonly="readonly"/>
			<span class='eleRequired'>*</span>
		</li>
		<li>
			<label for="">市场单价（元）：</label>
			<input class="required money" name="unitPrice" id="unitPrice" value="${goodsPrice.unitPrice }" onchange="goodsPriceCreate.priceChange('unitPrice',this)" />
			<span class='eleRequired'>*</span>
		</li>
		<li>
			<label for="">折扣率（%）：</label>
			<input class="required"  name="dscuRate" id="dscuRate" value="${goodsPrice.dscuRate }" onchange="goodsPriceCreate.priceChange('dscuRate',this)"/><span class='eleRequired'>*</span>
		</li>
		<li>
			<label for="">协议价（元）：</label>
			<input class="required money" name="prtcPrice" id="prtcPrice" value="${goodsPrice.prtcPrice }"  onchange="goodsPriceCreate.priceChange('prtcPrice',this)"/><span class='eleRequired'>*</span>
		</li>
		<li class="fullLine">
			<label for="">行情生效时间：</label>
			<input class="required" name="efctDate" id="efctDate" value="${goodsPrice.efctDate }"/>到：<input class="required" name="endDate" id="endDate" value="${goodsPrice.endDate }" /><span class='eleRequired'>*</span>
		</li>
		<li class="formTextarea">
			<label for="">备注：</label>
			<textarea name="remarks" id="remarks">${goodsPrice.remarks }</textarea>
		</li>
       </ul>
</div>

<div class="formLayout form2Pa">
	<h4><span>选配行情&nbsp;<strong>请输入相对价格（单位：元）</strong></span></h4>
  	<c:forEach var="goodsParam" items="${goods.goodsParamSet}" varStatus="squ1">
	  	<c:if test="${!empty goodsParam.goodsOptionalFittingSet}">
		  	<h5><strong>${goodsParam.paramName}（${goodsParam.paramValue }） </strong></h5>
		  	<ul>
		  	<c:set var ="squ2.index" value="0"></c:set>
			<c:forEach var ="goodsOptionalFitting" items="${goodsParam.goodsOptionalFittingSet}" varStatus="squ2">
			<c:if test="${goodsOptionalFitting.isUse!='02' }">
				<c:set var="optpriceId" value="" scope="page"></c:set>
				<c:set var="optpriceValue" value="" scope="page"></c:set>
				<li class ="<c:if test ="${squ2.index==(fn:length(goodsParam.goodsOptionalFittingSet)-1)&& squ2.index%2==0 }">fullLine</c:if>" >
					<label>${goodsOptionalFitting.optionContent }：</label>
					<c:forEach var="goodsOptFitPrice" items="${goodsPrice.goodsOptFitPriceSet}">
						<c:if test="${goodsOptFitPrice.goodsOptionalFitting.objId == goodsOptionalFitting.objId}">
							<c:set var="optpriceId" value="${goodsOptFitPrice.objId}"></c:set>
							<c:set var="optpriceValue" value="${goodsOptFitPrice.relativePrice}"></c:set>
						</c:if>
					</c:forEach>
					<input type="hidden" id="optpriceId${squ1.index }${squ2.index }" name="optpriceId${squ1.index }${squ2.index }" value="${optpriceId }"/>
					<input type="hidden" id="optId${squ1.index }${squ2.index }" name="optId${squ1.index }${squ2.index }" value="${goodsOptionalFitting.objId }"/>
					<input class="money" id="relativePrice${squ1.index }${squ2.index }" name="relativePrice${squ1.index }${squ2.index }" value="${optpriceValue }"/>
				</li>
				<c:set var ="squ2.index" value="${squ2.index+1}"></c:set>
			</c:if>
			</c:forEach>
			</ul>
	  	</c:if>
  	</c:forEach>
</div>


<div class="conOperation"> 
  <button type="button" id="goodsPriceSave" onclick="goodsPriceCreate.saveOrSubmit('save')"><span>保存</span></button>  
  <button type="button" id="goodsPriceSubmit" onclick="goodsPriceCreate.saveOrSubmit('submit')"><span>提交</span></button>  
  <button type="button" onclick="goodsPriceCreate.close()"><span>关闭</span></button>
</div>
</form>

