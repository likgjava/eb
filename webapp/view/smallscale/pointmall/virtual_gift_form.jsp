<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="orderManagement">
<c:set var="countGoods" value="0"/>
<c:set var="totalMoney" value="0"/>
<c:set var="totalPoint" value="0"/>

	<table class="frontTableList" id="goodsAndOption">
		<thead>
			<tr>
				<th width="280px">礼品名称</th>								
				<th>数量</th>
				<th>单件消费积分</th>
				<th>单件消费金额</th>			
			</tr>
		</thead>
		<tbody>	 		
				<tr class="goodsInfo" id="${gift.objId}">
					<td>
						<img class="goodsImg" src="<c:url value="AttachmentController.do?method=showImg&objId=${gift.picture}" />" >
						<a href="javascript:void(0);" onclick="virtualGiftForm.showDetail('${gift.objId}');return false;" >${gift.giftName } &nbsp;${gift.giftCode }</a>						
					</td>
					
					<td class="center">						
						<a href="javascript:void(0);" onclick="virtualGiftForm.goodsQtyAddOrReduce(this,-1);return false;"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>						
						<input style="width:25px;"  
						name="itemqty" id="itemqty"  value="${count}" onchange="virtualGiftForm.goodsQtyChange(this)" />						
						<a href="javascript:void(0);" onclick="virtualGiftForm.goodsQtyAddOrReduce(this,1);return false;"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
						
						<input type="hidden" id="eScore" value="${erule.score}">
						<input type="hidden" id="eMoney" value="${erule.amount}">
						
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
<form id="virtualGiftForm" method="post" >
   <input type="hidden" name="giftId" id="giftId" value="${giftId}">

   <input type="hidden" name="giftCount" id="giftCount"  value="${count}">
   <input type="hidden" name="eruleId" id="eruleId" value="${eruleId}">
	<h4><span>收货人信息</span></h4>
	<ul>
		<li><label>接收邮件：</label>
			<input type="text" class="required" id="receiveEmail" name="receiveEmail" >
		    <span class="eleRequired">*</span>
		</li>
				
	</ul>
</form>
</div>

<div class="conOperation">
<button  id="giftSave"><span>兑换</span></button>
</div>

<script type="text/javascript">
 var virtualGiftForm={};

//显示礼品详情
 virtualGiftForm.showDetail = function(giftId) {
	 
 	$.epsDialog({
 		id:"showGoodsDetail",
 		title:"礼品详情",
 		//url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&isShowPic=true&objId="+goodsId
 		
 		url:$("#initPath").val()+"/GiftShowController.do?method=getGiftInfo&isEdit=false&objId="+giftId
 	})
 }

 $(document).ready(function(){

 //数量加减
 virtualGiftForm.goodsQtyAddOrReduce = function(e,value){
	 
 	var itemqty = Number($(e).parent().find("input[name=itemqty]").val()) + value;
 	if(itemqty>0){
 		$(e).parent().find("input[name=itemqty]").val(itemqty);
 		$('#giftCount').attr("value",itemqty);
 	}else{
 		alert("数量不得小于1！");
 	}
 	
 	
 	virtualGiftForm.reCaculator();//重新计算
 	virtualGiftForm.goodsQtyPriceChange(
 			$(e).parent().parent().attr("id"),
 			$(e).parent().parent().find("input[name=itemqty]").val(),
 			$(e).parent().parent().find("input[name=itmeprice]").val(),
 			$(e).parent().parent().find("span[id=goodsTotal]").html()
 	);
    
 }

 //数量更改
 virtualGiftForm.goodsQtyChange = function(e){
 	if(isNaN($(e).val()+'')||Number($(e).val())<1){alert("请输入大于零的整数字");$(e).val("");return;}

 	
 	$('#giftCount').attr("value",Number($(e).val()));
 	
 	virtualGiftForm.reCaculator();//重新计算
 	virtualGiftForm.goodsQtyPriceChange(
 			$(e).parent().parent().attr("id"),
 			$(e).parent().parent().find("input[name=itemqty]").val(),
 			$(e).parent().parent().find("input[name=itmeprice]").val(),
 			$(e).parent().parent().find("span[id=goodsTotal]").html()
 	); 	
 	
 	
 }

//计算金额,数量
 virtualGiftForm.reCaculator = function(){ 	
 	
 	var qty = Number($("#giftCount").val());
 	var amount = qty * Number($("#eMoney").val());
 	var point = qty * Number($("#eScore").val()); 	
 	
 	$('#countGoods').html(qty);
 	
 	$('#totalMoney').html(formatAmount(amount,2));
 	$('#totalPoint').html(point);
 }



		$('#virtualGiftForm').validate();		
		//提交
		$('#giftSave').click(function(){
			var totalScore = Number($("#hasScore").text());
			var useScore = Number($("#totalPoint").text());
			if(totalScore<useScore){
				alert("你的可用积分不足，不能兑换该礼品！");
				return;
			}			
			if(!$('#virtualGiftForm').valid()){alert('请正确填写表单!');return;}			
			
			if(!confirm("确认提交！")){return false;}
			//String giftId,String eruleId,Integer giftCount,String email
			var giftId = $("#virtualGiftForm").find("input[id=giftId]").val();
			var eruleId = $("#eruleId").val();	
			var giftCount = $("#giftCount").val();	
			var receiveEmail = $("#receiveEmail").val();	
			var url = '/VirtualGiftRecordController.do?method=saveExchange&giftId='+giftId+"&eruleId="+eruleId+"&giftCount="+giftCount+"&email="+receiveEmail;
			$.getJSON($('#initPath').val()+url, {}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#sysContent').loadPage($('#initPath').val()+'/VirtualGiftRecordController.do?method=toExchangeList');				
			});
		});

});
 

</script>