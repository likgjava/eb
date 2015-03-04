<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">
var goodsPriceDetail = {};
//关闭
goodsPriceDetail.close = function(){
	$("#viewPriceDiv").find('.epsDialogClose').trigger('click');
}
$(document).ready(function(){
})
</script>

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
			<span>${goodsPrice.efctDate }</span>&nbsp;至&nbsp;<span>${goodsPrice.endDate }</span>
		</li>
		<li class="fullLine">
			<label for="">备注：</label>
			<span>${goodsPrice.remarks }</span>
		</li>
       </ul>

</div>

<div class="formLayout form2Pa">
  <h4><span>选配行情</span></h4>
  	<c:forEach var="goodsParam" items="${goods.goodsParamSet}" varStatus="status1">
	  	<c:if test="${!empty goodsParam.goodsOptionalFittingSet}">
		  	<ul>
		  	<li class="fullLine"><h5><strong>${goodsParam.paramName}（${goodsParam.paramValue }） </strong></h5></li>
	  		<c:set var ="status2.index" value="0"></c:set>
	  		<c:forEach var="goodsOptionalFitting" items="${goodsParam.goodsOptionalFittingSet}">
	  		<c:if test="${goodsOptionalFitting.isUse!='02' }">
	  		<li class ="<c:if test ="${status2.index==(fn:length(goodsParam.goodsOptionalFittingSet)-1)&& status2.index%2==0 }">fullLine</c:if>">
		  		<label>${goodsOptionalFitting.optionContent }：</label>
		  		<!--  判断是否保存过  -->
		  		<c:set var="OptPriceId" value=""></c:set>	
		  		<c:set var="OptPriceValue" value=""></c:set>	
	  			<c:forEach var = "goodsOptFitPrice" items = "${goodsPrice.goodsOptFitPriceSet }">
  					<c:if test="${goodsOptFitPrice.goodsOptionalFitting.objId == goodsOptionalFitting.objId}">
	  					<c:set var="OptPriceId" value="${goodsOptFitPrice.objId}"></c:set>	
	  					<c:set var="OptPriceValue" value="${goodsOptFitPrice.relativePrice }"></c:set>	
  					</c:if>
	  			</c:forEach>
  				<span><c:choose>
  				<c:when test="${OptPriceValue!=null && OptPriceValue!=''}">${OptPriceValue}</c:when>
  				<c:otherwise>无选配价格</c:otherwise>
  				</c:choose>
  				</span>
	  		</li>
	  		<c:set var ="status2.index" value="${status2.index+1}"></c:set>
	  		</c:if>
		  	</c:forEach>
		  	</ul>
	  	</c:if>
  	</c:forEach>
</div>

<c:if test="${goodsPrice.opinion!=null}">
<div class="formLayout formPa">
	<h4><span>审核信息</span></h4>
	<ul>
	<li class="fullLine">
	<label for="opinion">审核意见：</label>
	<span>${goodsPrice.opinion }</span>
	</li>
	</ul>
</div>
</c:if>

<div class="conOperation"> 
  <button class="largeBtn" id="return" type="button" onclick="goodsPriceDetail.close()"><span>关闭</span></button>
</div>