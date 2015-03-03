<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function(){
	//加载附件
	var resId = $("#agreementFile").attr("value");
	if(resId){
		$('#agreementFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=agreementFile&isSelect=yes&attachRelaId='+resId);
	}else{
		$('#agreementFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=agreementFile&isSelect=yes');
	}
})

</script>
<div id = "addpurchaseAgreement" class="formLayout form2Pa">
	<ul>
		<li id="agreementNameLi" class="<c:if test='${agreement.agreementNo==null}'>fullLine</c:if>">
			<label>协议名称：</label>
			<span>${agreement.name}</span>
		</li>	
		<c:if test="${agreement.agreementNo!=null}">
		<li id="agreementNoLi">
			<label>协议编号：</label>
			<span id="agreementNo">${agreement.agreementNo}</span>
		</li>	
		</c:if>
		<li class="fullLine">
			<label>签订时间：</label>
			<span>${agreement.creTime }</span>
		</li>
		<li>
			<label>甲方(代理机构)：</label>
			<span>${agreement.org.orgName}</span>
		</li>
		<li>
			<label>乙方(经销商)：</label>
			<span>${agreement.supplier.orgName}</span>
		</li>
		<li class="fullLine">
			<label>协议期间：</label>
			<span>${agreement.period.periodName}</span>
		</li>
		<li class="fullLine">
			<label>协议区间：</label>
			<span>${agreement.areaNames }</span>
		</li>
		<li class="fullLine">
			<label>协议文件：</label>
			<div id="agreementFile" class="uploadFile" value="${agreement.agreementFile}"></div>
		</li>
		<li class="fullLine">
			<label>备注：</label>
			<span>${agreement.memo }</span>
		</li>
	</ul>
	
 	<ul>
		<c:forEach var="agreementClassGoods" items="${agreementClassGoodsList}" varStatus="status1">
		<li class="fullLine"><h5><strong>${ agreementClassGoods[0].goodsClass.goodsClassName} </strong></h5></li>
		<c:forEach var="agreementGoods" items="${ agreementClassGoods[1]}" varStatus="status2">
			  	<li class="fullLine">
			  		<label style="width:200px;">商品名称：${agreementGoods.goods.productName}</label>
			  		<label>市场价：${agreementGoods.marketPrice}</label>
			  		<label>折扣率：${agreementGoods.discountRatio}</label>
			  		<label>协议价：${agreementGoods.agreementPrice}</label>
			  	</li>
	  	</c:forEach>
	  	<c:if test="${ agreementClassGoods[1]==null || empty agreementClassGoods[1] }"><li class="fullLine"><label>&nbsp;</label>分类下暂无协议商品！</li></c:if>
  		</c:forEach>
  	
  		<li class="fullLine"><h5><strong>其他单品</strong></h5></li>
  		<c:forEach var="agreementGoods" items="${ agreementGoodsList }" varStatus="status2">
				  	<li class="fullLine">
				  		<label style="width:200px;">商品名称：${agreementGoods.goods.productName}</label>
				  		<label>市场价：${agreementGoods.marketPrice}</label>
				  		<label>折扣率：${agreementGoods.discountRatio}</label>
				  		<label>协议价：${agreementGoods.agreementPrice}</label>
				  	</li>
		 </c:forEach>
  	</ul>
  	
</div>
	
<div class="conOperation center">
	<button id="purchaseAgreementClose" onclick='$("#purchaseAgreementClose").closest(".epsDialog").find(".epsDialogClose").click();' type="button" ><span><spring:message code="globe.close"/></span></button>
</div>
