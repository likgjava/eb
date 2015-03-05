<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="orderManagement">
<c:set var="countGoods" value="0"/>
<c:set var="totalMoney" value="0"/>
<c:set var="totalPoint" value="0"/>

	<table class="frontTableList" id="goodsAndOption">
		<thead>
			<tr>
				<th width="280px">礼品名称</th>
				<th>参考价</th>				
				<th>数量</th>
				<th>单件消费积分</th>
				<th>单件消费金额</th>			
			</tr>
		</thead>
		<tbody>	 		
				<tr class="goodsInfo" id="${gift.objId}">
					<td>
						<img class="goodsImg" src="<c:url value="AttachmentController.do?method=showImg&objId=${gift.picture}" />" >
						<a href="javascript:void(0);" onclick="realGiftForm.showDetail('${gift.objId}');return false;" >${gift.giftName } &nbsp;${gift.giftCode }</a>						
					</td>
					<td class="center">
						<span id="referPrice"><fmt:formatNumber value="${gift.goods.referPrice }" pattern="#,##0.00#" /></span>						
					</td>					
					<td class="center">						
						<a href="javascript:void(0);" onclick="realGiftForm.goodsQtyAddOrReduce(this,-1);return false;"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>						
						<input style="width:25px;"  
						name="itemqty" id="itemqty"  value="${count}" onchange="realGiftForm.goodsQtyChange(this)" />						
						<a href="javascript:void(0);" onclick="realGiftForm.goodsQtyAddOrReduce(this,1);return false;"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
						
						<input type="hidden" id="eScore" value="${erule.score}">
						<input type="hidden" id="eMoney" value="${erule.amount}">
						<input type="hidden" id="totalCount" value="${gift.exchangeCount}">
						
						<c:set var="totalPoint" value="${count*erule.score}"/>
						<c:set var="totalMoney" value="${count*erule.amount}"/>
					</td>
					<td class="center">
						<span ><fmt:formatNumber value="${erule.score}" /></span>
						
					</td>
					<td class="center">
						<span ><fmt:formatNumber value="${erule.amount}" pattern="#,##0.00#"/></span>						
					</td>
				</tr>	
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<ul>
							<li>当前可用积分：<strong><span id="hasScore">${hasScore}</span></strong>分</li>					
							<li>数量总计：<strong><span id="countGoods">${count}</span></strong>件</li>
							<li>消费积分总计：<strong><span id="totalPoint"><fmt:formatNumber value="${totalPoint}" /></span></strong></li>
							<li>金额总计：<strong>￥<span id="totalMoney"><fmt:formatNumber value="${totalMoney}" pattern="#,##0.00#" /></span></strong>元</li>							
					</ul>
				</td>
			</tr>
		</tfoot>
	</table>
</div>

<br />
<div class="formLayout form1Pa">
<form id="realGiftRecord" method="post" >
   <input type="hidden" name="giftId" value="${giftId}">

   <input type="hidden" name="giftCount" id="giftCount"   value="${count}">
   <input type="hidden" name="eruleId" value="${eruleId}">
	<h4><span>收货人信息</span></h4>
	<ul>
		<li><label>收货人姓名：</label>
			<input type="text" class="required" id="receiverName" name="receiverName" value="${user.emp.name}">
		    <span class="eleRequired">*</span>
		</li>
		<li><label>联系手机：</label>
			<input type="text" class="required cnMobile" id="receiverTel" name="receiverTel" value="${user.emp.mobile}">
			<span class="eleRequired">*</span>
		</li>
		<li>
		<label>收货地址：</label>
			<input type="text" class="required" id="receiverAddress" name="receiverAddress" value="${user.emp.address}">
		    <span class="eleRequired">*</span>
		</li>
		<li>
		<label>收货邮编：</label>
			<input type="text" class="required cnZipCode" id="receiverPost" name="receiverPost" value="${user.emp.zipCode}">
		    <span class="eleRequired">*</span>
		</li>		
	</ul>
</form>
</div>

<div class="conOperation">
<button  id="realGiftSave"><span>兑换</span></button>
</div>

<script type="text/javascript">
 var realGiftForm={};

//显示礼品详情
 realGiftForm.showDetail = function(giftId) {
 	$.epsDialog({
 		id:"showGoodsDetail",
 		title:"礼品详情",
 		//url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&isShowPic=true&objId="+goodsId
 		url:$("#initPath").val()+"/GiftShowController.do?method=getGiftInfo&isEdit=false&objId="+giftId
 	})
 }

 $(document).ready(function(){

 //数量加减
 realGiftForm.goodsQtyAddOrReduce = function(e,value){
	 
 	var itemqty = Number($(e).parent().find("input[name=itemqty]").val()) + value;
 	if(itemqty>0){
 		$(e).parent().find("input[name=itemqty]").val(itemqty);
 		$('#giftCount').attr("value",itemqty);
 	}else{
 		alert("数量不得小于1！");
 	} 	
 	
 	realGiftForm.reCaculator();//重新计算
 	realGiftForm.goodsQtyPriceChange(
 			$(e).parent().parent().attr("id"),
 			$(e).parent().parent().find("input[name=itemqty]").val(),
 			$(e).parent().parent().find("input[name=itmeprice]").val(),
 			$(e).parent().parent().find("span[id=goodsTotal]").html()
 	);
    
 }

 //数量更改
 realGiftForm.goodsQtyChange = function(e){
 	if(isNaN($(e).val()+'')||Number($(e).val())<1){alert("请输入大于零的整数字");$(e).val("");return;}

 	if(Number($(e).val())>Number($('#totalCount'))){alert("兑换礼品数量不能大于库存数量");$(e).val("");return;}
 	
 	$('#giftCount').attr("value",Number($(e).val()));
 	
 	realGiftForm.reCaculator();//重新计算
 	realGiftForm.goodsQtyPriceChange(
 			$(e).parent().parent().attr("id"),
 			$(e).parent().parent().find("input[name=itemqty]").val(),
 			$(e).parent().parent().find("input[name=itmeprice]").val(),
 			$(e).parent().parent().find("span[id=goodsTotal]").html()
 	); 	
 	
 	
 }

//计算金额,数量
 realGiftForm.reCaculator = function(){
 	
 	
 	var qty = Number($("#giftCount").val());
 	var amount = qty * Number($("#eMoney").val());
 	var point = qty * Number($("#eScore").val());
 	
 	
 	
 	$('#countGoods').html(qty);
 	
 	$('#totalMoney').html(formatAmount(amount,2));
 	$('#totalPoint').html(point);
 }



		$('#realGiftRecord').validate();		
		//提交
		$('#realGiftSave').click(function(){

			if(Number($("#itemqty").val())>Number($('#totalCount').val())){alert("兑换礼品数量不能大于库存数量");return;}
			
			var totalScore = Number($("#hasScore").text());
			var useScore = Number($("#totalPoint").text());
			if(totalScore<useScore){
				alert("你的可用积分不足，不能兑换该礼品！");
				return;
			}			
			if(!$('#realGiftRecord').valid()){alert('请正确填写表单!');return;}			
			
			if(!confirm("确认提交！")){return false;}
				
			$.getJSON($('#initPath').val()+'/RealGiftRecordController.do?method=saveExchange', formToJsonObject('realGiftRecord'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#sysContent').loadPage($('#initPath').val()+'/RealGiftRecordController.do?method=toExchangeList');				
			});
		});

});
 

</script>