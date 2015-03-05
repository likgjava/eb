<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>
<div class="formLayout form2Pa">
	<ul>
    	<li class="fullLine">
            <label>收货人姓名：</label>
            <span>${groupBuyer.receiveName}</span>
        </li>
        <li class="fullLine">
            <label>手机号码：</label>
            <span>${groupBuyer.mobilePhone}</span>
        </li>
        <li class="fullLine">
            <label>固定电话：</label>
            <span>${groupBuyer.fixedPhone}</span>
        </li>
        <li class="fullLine">
            <label>电子邮箱：</label>
            <span>${groupBuyer.email}</span>
        </li>
        <li class="fullLine">
          	<label>省份：</label>
          	<span>${groupBuyer.province.name}</span>
   	  	</li>
        <li class="fullLine">
          	<label>详细地址：</label>
          	<span>${groupBuyer.address}</span>
   	  	</li>
    	<li class="fullLine">
            <label>邮政编码：</label>
            <span>${groupBuyer.postCode}</span>
        </li>
        <li class="fullLine">
            <label>支付状态：</label>
			<span>${groupBuyer.payStatusCN}</span>
        </li>
        <li class="fullLine">
          	<label>配送方式：</label>
          	<span>${groupBuyer.shippingMethodCN}</span>
   	  	</li>
    </ul>
</div>
<div class="conOperation">
	<button type="button" id="groupBuyerCloseBut"><span>关闭</span></button>
</div>

<script>
$(document).ready(function(){
	//关闭
	$("#groupBuyerCloseBut").click(function(){
		if($("#_dialogID").val() != "")
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		else
			$('.epsDialogClose').trigger('click');
	});
})
</script>