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
				<tr class="goodsInfo" id="${giftRecord.gift.objId}">
					<td>
						<img class="goodsImg" src="<c:url value="AttachmentController.do?method=showImg&objId=${giftRecord.gift.picture}" />" >
						${giftRecord.gift.giftName } &nbsp;${giftRecord.gift.giftCode }						
					</td>
									
					<td class="center">						
						<span >1</span>					
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
					<ul>	
						<c:set var="excCount" value="1"/>
							<li>数量总计：<strong><span id="countGoods">${excCount}</span></strong>件</li>
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
<form:form id="virtualGiftRecord" method="post" modelAttribute="virtualGiftRecord">
        <input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="type" id="type" value="<c:out value="${param.type}"/>"/>
	
	<ul>
		<li><label>兑换人：</label>		
		    <span>${giftRecord.createUser.emp.name}</span>
		</li>
		 <c:if test="${giftRecord.dealStatus != '02'}">
		<li><label>使用积分：</label>		
		    <span>${giftRecord.erule.score}</span>		    
		</li>
		 </c:if>		
		<li><label>兑换时间：</label>		
		    <span >
		    <fmt:formatDate value="${giftRecord.createTime}" pattern="yyyy-MM-dd" />
		    </span>
		</li>
		<li>
		<label>接收邮件：</label>			
		    <span>${giftRecord.receiveEmail}</span>
		</li>
		<c:if test="${type eq 'dealed' || type eq 'received' || type eq 'show'}">
			<li>		
			<label>卡号：</label>
			    <span>${giftRecord.cardCode}</span>							
			</li>
			<li>
			<label>密码：</label>
				<span>${giftRecord.password}</span>
				
			</li>
		</c:if>
		<c:if test="${type eq 'deal'}">
		<li>
		
		<label>卡号：</label>
			<input type="text" class="required" id="cardCode" name="cardCode">
		    <span class="eleRequired">*</span>
		</li>
		<li>
		<label>密码：</label>
			<input type="text" class="required" id="password" name="password">
		    <span class="eleRequired">*</span>
		</li>	
		</c:if>	
	</ul>
</form:form>
</div>

<div class="conOperation">
<c:if test="${type eq 'deal'}">
<button  id="giftSave"><span>发货</span></button>
<button  id="giftLess"><span>缺货取消兑换</span></button>
</c:if>
<c:if test="${type eq 'received'}">
<button  id="giftReceived"><span>收货确认</span></button>
</c:if>
<button  id="giftClose"><span>关闭</span></button>
</div>

<script type="text/javascript">
 var virtualGiftForm={};

//显示礼品详情
 virtualGiftForm.showDetail = function(giftId) {
	
 	$.epsDialog({
 		id:"showGoodsDetail",
 		title:"礼品详情",
 		//url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&isShowPic=true&objId="+goodsId
 		url:$("#initPath").val()+"/GiftShowController.do?method=getGiftInfo&objId="+giftId
 	})
 }
 
 $(document).ready(function(){
		$('#virtualGiftRecord').validate();   
		
		//发货提交
		$('#giftSave').click(function(){			
			if(!$('#virtualGiftRecord').valid()){alert('请正确填写表单!');return;}			
			if(confirm('确认已发货？')){	
				$.getJSON($('#initPath').val()+'/VirtualGiftRecordController.do?method=saveDeal&dealStatus=01', formToJsonObject('virtualGiftRecord'), function(json){
					if(json.result)alert(json.result);if(json.failure)return;
	
					$('#conBody').loadPage($('#initPath').val()+"/VirtualGiftRecordController.do?method=toDealList");
	
					$('.epsDialogClose').trigger('click');
					
				});
			}
		});

		//缺货提交
		$('#giftLess').click(function(){
			if(confirm('确认缺货提交？')){				
				$.getJSON($('#initPath').val()+'/VirtualGiftRecordController.do?method=saveDeal&dealStatus=02', formToJsonObject('virtualGiftRecord'), function(json){
					if(json.result)alert(json.result);if(json.failure)return;
	
					$('#conBody').loadPage($('#initPath').val()+"/VirtualGiftRecordController.do?method=toDealList");
	
					$('.epsDialogClose').trigger('click');
					
				});
			}
		});

		//收货确认
		$('#giftReceived').click(function(){
			if(confirm('确认已收货？')){			
				$.getJSON($('#initPath').val()+'/VirtualGiftRecordController.do?method=saveDeal&dealStatus=03', formToJsonObject('virtualGiftRecord'), function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					$('#conBody').loadPage($('#initPath').val()+"/VirtualGiftRecordController.do?method=toDealList");
					$('.epsDialogClose').trigger('click');
				});
			}
		});
		
		//关闭
		$('#giftClose').click(function(){			
			if($("#_dialogID").val() != "")
				$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
			else
				$('.epsDialogClose').trigger('click');
		});

});
 

</script>