<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/goods/goodsprice/s_goods_price_create.js"/>'></script>

<form id="priceForm">
<div class="formLayout form2Pa">
		<input type="hidden" name="objId" id="objId" value="${goodsPrice.objId }"/>
		<input type="hidden" name="goodsPriceSupplier.objId" id="goodsPriceSupplier.objId" value="${param.goodsPriceSupplierId}"/>
 		<h4><span>商品行情</span></h4>
		<ul>
		<li>
			<label>使用区域名称：</label>
			<input type="hidden" id="district.objId" name="district.objId">
			<input class="sysicon siSearch required" type="text" id="district.name" name="district.name" onclick="goodsPriceCreate.selectDistrict()" readonly="readonly"/>
			<span class='eleRequired'>*</span>
		</li>
		<li>
			<label for="">市场单价（元）：</label>
			<input class="required money" name="unitPrice" id="unitPrice" onchange="goodsPriceCreate.priceChange('unitPrice',this)" value="${goods.referPrice }"/>
			<span class='eleRequired'>*</span>
		</li>
		<li>
			<label for="">折扣（%）：</label>
			<input class="required"  name="dscuRate" id="dscuRate" onchange="goodsPriceCreate.priceChange('dscuRate',this)"/><span class='eleRequired'>*</span>
		</li>
		<li>
			<label for="">协议价（元）：</label>
			<input class="required money" name="prtcPrice" id="prtcPrice"  onchange="goodsPriceCreate.priceChange('prtcPrice',this)"/><span class='eleRequired'>*</span>
		</li>
		<li class="fullLine">
			<label for="">行情生效时间：</label>
			<input class="required" name="efctDate" id="efctDate" />截止至：<input class="required" name="endDate" id="endDate" /><span class='eleRequired'>*</span>
		</li>
		<li class="formTextarea">
			<label for="">备注：</label>
			<textarea name="remarks" id="remarks"></textarea>
		</li>
       </ul>

</div>

<div class="formLayout form2Pa">
  <h4><span>选配行情&nbsp;<strong>请输入相对价格（单位：元）</strong></span></h4>
	<c:forEach var="goodsParam" items="${goods.goodsParamSet}" varStatus="status1">
	  	<c:if test="${!empty goodsParam.goodsOptionalFittingSet}">
			<h5><strong>${goodsParam.paramName}（${goodsParam.paramValue }） </strong></h5>
			<ul>
			<c:set var ="status2.index" value="0"></c:set>
			<c:forEach var="goodsOptionalFitting" items="${goodsParam.goodsOptionalFittingSet}">
				<c:if test="${goodsOptionalFitting.isUse!='02' }">
				  	<li class ="<c:if test ="${status2.index==(fn:length(goodsParam.goodsOptionalFittingSet)-1)&& status2.index%2==0 }">fullLine</c:if>">
				  		<label>${goodsOptionalFitting.optionContent }：</label>
				  		<input type="hidden" name="optId${status1.index }${status2.index }" value="${goodsOptionalFitting.objId }"/>
				  		<input class="money" name="relativePrice${status1.index }${status2.index }" />
				  	</li>
				  	<c:set var ="status2.index" value="${status2.index+1}"></c:set>
			  	</c:if>
		  	</c:forEach>
		  	</ul>
	  	</c:if>
  	</c:forEach>
</div>

<div class="conOperation"> 
  <button class="largeBtn" id="save" type="button" onclick="goodsPriceCreate.savePrice()"><span>保存</span></button>  
  <button class="largeBtn" id="submit" type="button" onclick="goodsPriceCreate.submitPrice()"><span>提交</span></button>  
  <button class="largeBtn" id="return" type="button" onclick="goodsPriceCreate.close()"><span>关闭</span></button>
  <ul></ul>
</div>
</form>
