<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<c:if test="${isDocPay}">
	您已支付标书费，<a style="color:blue;" onclick="viewPayRecordDetail('${docId}','toDocPayRecordDetail','标书费支付记录');">查看支付记录</a>
</c:if>
<c:if test="${!isDocPay}">
	您还未购买标书，去<a style="color:blue;" onclick="toDocPay('${projId}','${doc_amount}');">购买</a>标书
</c:if>

</br>

<c:if test="${isBailPay}">
	您已支付保证金，<a style="color:blue;" onclick="viewPayRecordDetail('${projId}','toBailPayRecordDetail','保证金支付记录');">查看支付记录</a>
</c:if>
<c:if test="${!isBailPay}">
	您还未支付保证金，去<a style="color:blue;" onclick="toBailPay('${projId}','${bail_amount}');">支付</a>保证金
</c:if>

<script>
//跳转到支付标书费页面
function toDocPay(projId,amount) {
	window.open($('#initPath').val()+"/DocAndBailPayController.do?method=toDocPayView&projId=" + projId+"&v_amount="+amount);
}

//跳转到支付保证金页面
function toBailPay(projId,amount) {
	window.open($('#initPath').val()+"/DocAndBailPayController.do?method=toBailPayView&projId=" + projId+"&v_amount="+amount);
}
//查看支付记录页
function viewPayRecordDetail(pay_business_id,business_method,title) {
	var url = $('#initPath').val()+'/PayController.pay?method='+business_method+'&pay_business_id=' + pay_business_id;
	$.epsDialog({
        title:title,
        width:820,
        height:350,
        url:url
	});
	window.open($('#initPath').val()+"/DocAndBailPayController.do?method=toBailPayView&projId=" + projId+"&v_amount="+amount);
}
</script>