<p class="abstract">
<font color="blue">
尊敬的客户您好：<br/>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:void(0);" onclick="viewOrderDetail('${orderId!}','${orderNo!}')">${orderNo!}</a>已由${orgName!}作废！
</font> 
</p>
<script type="text/javascript">
viewOrderDetail = function(objId,orderNo){
	 //弹出订单详情页面
	 $.epsDialog({
	        title:'订单编号：'+orderNo,
			url:$('#initPath').val()+'/OrderController.do?method=toOrderForm&navigate=divDetail&objId='+objId
	  }); 
}
</script>
