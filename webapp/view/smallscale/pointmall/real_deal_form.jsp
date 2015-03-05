<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="orderManagement">
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
						<img class="goodsImg" src="<c:url value="AttachmentController.do?method=showImg&objId=${giftRecord.gift.picture}" />" >
						${giftRecord.gift.giftName } &nbsp;${gift.giftCode }
					</td>
									
					<td class="center">						
						<span ><fmt:formatNumber value="${giftRecord.giftCount}" /></span>					
					</td>
					<td class="center">
						<span ><fmt:formatNumber value="${giftRecord.erule.score}" /></span>						
					</td>
					<td class="center">
						<span ><fmt:formatNumber value="${giftRecord.erule.amount}" pattern="#,##0.00#"/></span>						
					</td>
				</tr>	
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<ul>	<c:set var="excCount" value="${giftRecord.giftCount}"/>
							<li>数量总计：<strong><span id="countGoods">${ excCount}</span></strong>件</li>
							<li>消费积分总计：<strong><span id="totalPoint"><fmt:formatNumber value="${excCount*giftRecord.erule.score}"  /></span></strong></li>
							<li>金额总计：<strong>￥<span id="totalMoney"><fmt:formatNumber value="${excCount*giftRecord.erule.amount}" pattern="#,##0.00#" /></span></strong>元</li>							
					</ul>
				</td>
			</tr>
		</tfoot>
	</table>
</div>

<br />
<div class="formLayout form1Pa">
<form:form id="realGiftRecord" method="post" modelAttribute="realGiftRecord">
        <input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="type" id="type" value="<c:out value="${param.type}"/>"/>
	<h4><span>收货人信息</span></h4>
	<ul>
	
		<li><label>收货人姓名：</label>		
		    <span>${giftRecord.receiverName}</span>
		</li>
		<li><label>联系手机：</label>			
			<span>${giftRecord.receiverTel}</span>
		</li>
		<li>
		<label>收货地址：</label>			
		    <span>${giftRecord.receiverAddress}</span>
		</li>
		<li>
		<label>收货邮编：</label>			
		    <span>${giftRecord.receiverPost}</span>
		</li>
		<c:if test="${type eq 'show' || type eq 'received'}">
			<li>		
			<label>快递方式：</label>
			    <span>${giftRecord.fpostTypeCN}</span>							
			</li>
			<li>
			<label>快递号：</label>
				<span>${giftRecord.fpostNumber}</span>
				
			</li>
		</c:if>
		<c:if test="${type eq 'deal'}">
		<li>
		
		<label>快递方式：</label>
			<html:select selectedValue="${fpostType}" id="fpostType" name="fpostType" code="com.gpcsoft.smallscale.pointmall.domain.Rule.fpostType"></html:select>			
		</li>
		<li>
		<label>快递号：</label>
			<input type="text" class="" id="fpostNumber" name="fpostNumber" >		  
		</li>	
		</c:if>	
	</ul>
</form:form>
</div>

<div class="conOperation">
<c:if test="${type eq 'deal'}">
<button  id="realGiftSave"><span>发货</span></button>
<button  id="realGiftLess"><span>缺货取消兑换</span></button>
</c:if>
<c:if test="${type eq 'received'}">
<button  id="realGiftReceived"><span>收货确认</span></button>
</c:if>
<button  id="realGiftClose"><span>关闭</span></button>
</div>

<script type="text/javascript">
 var realGiftForm={};

//显示礼品详情
 realGiftForm.showDetail = function(giftId) {
 	$.epsDialog({
 		id:"showGoodsDetail",
 		title:"礼品详情",
 		//url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&isShowPic=true&objId="+goodsId
 		url:$("#initPath").val()+"/GiftShowController.do?method=getGiftInfo&objId="+giftId
 	})
 }



 $(document).ready(function(){
		$('#realGiftRecord').validate();   
		
		//发货提交
		$('#realGiftSave').click(function(){			
			if(!$('#realGiftRecord').valid()){alert('请正确填写表单!');return;}			
			if(confirm('确认已发货？')){	
				$.getJSON($('#initPath').val()+'/RealGiftRecordController.do?method=dealExchange&dealStatus=01', formToJsonObject('realGiftRecord'), function(json){
					if(json.result)alert(json.result);if(json.failure)return;
	
					$('#conBody').loadPage($('#initPath').val()+"/RealGiftRecordController.do?method=toDealList");
					$('.epsDialogClose').trigger('click');
				});
			}
		});

		//缺货提交
		$('#realGiftLess').click(function(){
			if(confirm('确认缺货提交？')){				
				$.getJSON($('#initPath').val()+'/RealGiftRecordController.do?method=dealExchange&dealStatus=02', formToJsonObject('realGiftRecord'), function(json){
					if(json.result)alert(json.result);if(json.failure)return;
	
					$('#conBody').loadPage($('#initPath').val()+"/RealGiftRecordController.do?method=toDealList");
	
					$('.epsDialogClose').trigger('click');
					
				});
			}
		});

		//收货确认
		$('#realGiftReceived').click(function(){
			if(confirm('确认已收货？')){			
				$.getJSON($('#initPath').val()+'/RealGiftRecordController.do?method=dealExchange&dealStatus=03', formToJsonObject('realGiftRecord'), function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					$('#conBody').loadPage($('#initPath').val()+"/RealGiftRecordController.do?method=toExchangeList");
					$('.epsDialogClose').trigger('click');
				});
			}
		});


		//关闭
		$('#realGiftClose').click(function(){			
			if($("#_dialogID").val() != "")
				$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
			else
				$('.epsDialogClose').trigger('click');
		});

});
 

</script>