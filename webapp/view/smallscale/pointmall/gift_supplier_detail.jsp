<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div id="giftSupplierInfo" class="formLayout form2Pa">
	<ul>			
		<li class="fullLine">
			<label>礼品供货商：</label>
			${giftSupplier.supplierName }
		</li>
		<li class="fullLine left">
			<label>开始时间：</label>
			${giftSupplier.startTime }
		</li>
		<li class="fullLine left">
			<label>结束时间：</label>
			${giftSupplier.endTime }
		</li>
		<li class="fullLine left">
			<label> 是否启用：</label>
			<c:if test="${giftSupplier.isUsed == true}">是</c:if><c:if test="${giftSupplier.isUsed == false}">否</c:if>			
		</li>
	</ul>
	
    <div class="conOperation">	        
        <button class="largeBtn" id="giftSupplierBtnReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
    </div>
</div>

<script>
//返回
$('#giftSupplierBtnReturn').click(function(){
	//自动关闭		
	$('.epsDialogClose').trigger('click');	
});
</script>