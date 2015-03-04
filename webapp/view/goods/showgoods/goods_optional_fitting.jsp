<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.tableList th ,.tableList td{
border-color:#ddd;
}
.tableList thead th {
background:none repeat scroll 0 0 #f6f6f6;
color:#883500;
}
.summary{
	float:right;
}
.fittTitle{
	font-size:13px;
	height:40px;
	padding-right:5px;
	padding-left:5px;
	background:url("view/resource/skin/pubservice/img/tu-1.gif") no-repeat scroll left center transparent;
	line-height:38px;
	vertical-align:middle;
	padding-bottom:5px;
}
.fittTitle .name{
	color:RGB(29,92,162);
	float:left;
}
.fittTitle .amount{
	color:#FFFFFF;
	float:right;
	font-weight:bold;
	margin-top:5px;
}
.fittTitle .price{
	color:#FF0000;
	float:right;
	padding-top:3px;
	position:absolute;
	right:280px;
}
</style>
<div class="fittTitle">
	<div class="name"><em>
		<c:set var="goodsName" value="${goods.productName }(${goods.productCode})"></c:set>
		<c:choose>
			<c:when test="${fn:length(goodsName) <= 25}">${goods.productName }(${goods.productCode})</c:when>
			<c:otherwise><span title="${goods.productName }(${goods.productCode})">${fn:substring(goodsName,0,25)}...</span></c:otherwise>
		</c:choose>
	</em></div>
	<span class="price">标配价格：<em>￥<fmt:formatNumber value="${param.price }" pattern="#,##0.00#" /></em>&nbsp;元</span>
	<span class="amount">购买数量：<input style="width:50px;" id="goodsQty" onblur="computerPrice()" value="1"/></span>
</div>	
<input type="hidden" id="goodsPriceId" value="${param.goodsPriceId }" />
<input type="hidden" id="goodsPriceBefor" value="${param.price }" />
<input type="hidden" id="goodsPrice" value="${param.price }" />
<input type="hidden" id="supplierId" value="${param.supplierId }" />
<input type="hidden" name="agreePrice" value="${goodsPrice.prtcPrice }"/>
<input type="hidden" name="memberPrice" value="${goodsPrice.memberPrice }"/>
<input type="hidden" name="marketPrice" value="${goodsPrice.unitPrice }"/>

<div id="optionList" class="formLayout">
<table class="tableList" id="goodsOptionalFittingTable">
	<thead>
		<tr>
			<th style="width:20%">参数名称</th>
			<th style="width:20%">标配</th>
			<th style="width:45%">可选配置</th>
			<th>相对价格</th>
		</tr>
	</thead>
<tbody>
<c:set var="goodsParamSetCount" value="0"></c:set>
<c:forEach var="goodsParam" items="${goods.goodsParamSet}" varStatus="status1">
	  	<c:if test="${!empty goodsParam.goodsOptionalFittingSet}">
			<c:set var="goodsParamSetCount" value="${goodsParamSetCount+1}"></c:set>
	  	<tr>
		  	<td>${goodsParam.paramName }</td>
		  	<td>
		  		<span>${goodsParam.paramValue}</span>
		  	</td>
		  	<td>
	  		<c:forEach var="goodsOptionalFitting" items="${goodsParam.goodsOptionalFittingSet}">
	  			<c:set var="OptPriceValue" value="0"></c:set>	
	  			<c:forEach var = "goodsOptFitPrice" items = "${goodsPrice.goodsOptFitPriceSet }">
  					<c:if test="${goodsOptFitPrice.goodsOptionalFitting.objId == goodsOptionalFitting.objId}">
	  					<c:set var="OptPriceValue" value="${goodsOptFitPrice.relativePrice }"></c:set>	
  					</c:if>
	  			</c:forEach>
  				<span>
  				<input type="hidden" name="optId" value="${goodsOptionalFitting.objId }" />
		  		<input type="radio" name="${goodsParam.objId }" value="${OptPriceValue }" />
		  		</span>
		  		<span>
			  		<c:if test="${goodsOptionalFitting.isUse!='02' }">
			  			${goodsOptionalFitting.optionContent }
			  		</c:if>
		  		</span>
		  	</c:forEach>
		  	</td>
		  	<td><span style="float:right" name="optPriceValue">0 元</span></td>
		  	</tr>
	  	</c:if>
  	</c:forEach>
  	<c:if test="${goodsParamSetCount < 1}">
  	<tr><td colSpan="2">该商品没有选配</td></tr>
  	</c:if>
  	</tbody>
  	<tfoot>
  		<tr>
  			<td><em>商品合计</em></td>
  			<td colspan="3"><div class="summary"><strong>￥&nbsp;<span id="totalPrice"><fmt:formatNumber value="${param.price }" pattern="#,##0.00#" /></span>&nbsp;元</strong></div></td>
  		</tr>
  	</tfoot>
 </table> 	

</div>

<div class="conOperation">
	<button type="button" onclick="addConfirm()"><span>确定</span></button>
	<button type="button" onclick="addShopping()"><span>加入购物车</span></button>
	<button type="button" onclick="closePage()"><span>关闭</span></button>
</div>
<script>
$(document).ready(function(){
	//购买数量，控制不能输入非正整数
	$("#goodsQty").inputFillter({type:'int'});

	// 选择选配
	$('input:radio').click(function() {
		var addHtml = "";
		if($(this).val()>0){
			addHtml = "+"+$(this).val();
		}else if($(this).val()<0){
			addHtml = $(this).val();
		}else {
			addHtml = "0";
		}
		$(this).parent().parent().parent().find("span[name=optPriceValue]").html(addHtml+"&nbsp;元")
		computerPrice()
	});

	//已选选配
	var fittingIds =  $("tr[id="+$("#goodsPriceId").val()+"]").find("input[name=fittingIds]").val();
	if(fittingIds){
		var shoppingCartItem = eval('('+fittingIds+')');
		if(shoppingCartItem.cartGoodsOptions!=null&&shoppingCartItem.cartGoodsOptions.length>0){
			$.each(shoppingCartItem.cartGoodsOptions,function(index,obj){
				$("input[value="+obj.option.objId+"]").parent().find("input[type=radio]").attr("checked",true);
				var cellPrice = $("input[value="+obj.option.objId+"]").next().val();
				$("input[value="+obj.option.objId+"]").parent().parent().parent().find("span[name=optPriceValue]").html((cellPrice>0?'+'+cellPrice:cellPrice)+'元');
			})
		}
		$("input[id=goodsQty]").val(shoppingCartItem.goodsQty);//数量
		computerPrice();// 计算
	}
});
//计算价钱
function computerPrice(){
	if($("#goodsQty").val()==null || $("#goodsQty").val()==""){
		alert("请填写购买数量！");
		return ;
	}
	var totalChenkedPrice = 0;
	$('#goodsOptionalFittingTable').find('input:radio:checked').each(function(){
		totalChenkedPrice = totalChenkedPrice+Number($(this).val());
	});
	$("#goodsPrice").val(Number(totalChenkedPrice)+Number($('#goodsPriceBefor').val()));
	$('#totalPrice').html(formatAmount(Number($("#goodsQty").val()*(Number(totalChenkedPrice)+Number($('#goodsPriceBefor').val()))),2));
}

function addShopping(){
	addShoppingCartItem();
	closePage(); //关闭选配
}

//确定暂存选配参数
function addConfirm(){
	var shoppingCartItem={
			supplier:{objId:$("#supplierId").val()},
			goods:{objId:$('#goodsId').val()},
			goodsQty:$('#goodsQty').val().replace(/,/g,''),
			goodsTotal:$('#totalPrice').html().replace(/,/g,''),
			goodsPrice:$('#goodsPrice').val().replace(/,/g,''),
			agreePrice:$('input[name=agreePrice]').val().replace(/,/g,''),
			marketPrice:$('input[name=marketPrice]').val().replace(/,/g,''),
			goodsUnit:$("span[id=measureUnit]").html()
		}; 
	shoppingCartItem.cartGoodsOptions=[];
	$.each($("#goodsOptionalFittingTable").find("input[type=radio]:checked"),function(index,obj){
		var cartGoodsOption = {};
		cartGoodsOption.option = {objId:$(obj).parent().find("input[name=optId]").val()};
		cartGoodsOption.optPrice = $(obj).val();
		cartGoodsOption.optQty = $('#goodsQty').val().replace(/,/g,'');
		shoppingCartItem.cartGoodsOptions.push(cartGoodsOption);
	})
	var jsonString = JSON.stringify(shoppingCartItem);

	//回填一行数据
	$("tr[id="+$("#goodsPriceId").val()+"]").find("input[name=fittingIds]").val(jsonString);
	$("tr[id="+$("#goodsPriceId").val()+"]").find("input[name=fittingIds]").parent().find("a").html("已选选配");
	$("tr[id="+$("#goodsPriceId").val()+"]").find("input[name=fittingIds]").parent().find("strong").html($('#totalPrice').html());
	$("tr[id="+$("#goodsPriceId").val()+"]").next().find("p[class=price_d] span").html($('#totalPrice').html())
	closePage();
}

//关闭
function closePage(){
	$("#optionalFittionDiv").find('.epsDialogClose').trigger('click');
}




//加入购物车
function addShoppingCartItem(){
		var shoppingCartItem={
			supplier:{objId:$("#supplierId").val()},
			goods:{objId:$('#goodsId').val()},
			goodsQty:$('#goodsQty').val().replace(/,/g,''),
			goodsTotal:$('#totalPrice').html().replace(/,/g,''),
			goodsPrice:$('#goodsPrice').val().replace(/,/g,''),
			agreePrice:($("#isMember").val()!="true"?$('input[name=agreePrice]').val().replace(/,/g,''):$('input[name=memberPrice]').val().replace(/,/g,'')),
			marketPrice:$('input[name=marketPrice]').val().replace(/,/g,''),
			goodsUnit:$("span[id=measureUnit]").html()
		}; 
		shoppingCartItem.cartGoodsOptions=[];
		$.each($("#goodsOptionalFittingTable").find("input[type=radio]:checked"),function(index,obj){
			if($(obj).val()!=0){
				var cartGoodsOption = {};
				cartGoodsOption.option = {objId:$(obj).parent().find("input[name=optId]").val()};
				cartGoodsOption.optPrice = $(obj).val();
				cartGoodsOption.optQty = $('#goodsQty').val().replace(/,/g,'');
				shoppingCartItem.cartGoodsOptions.push(cartGoodsOption);

				cartGoodsOption = null;//闭包内存泄露问题 by yucy
			}
		})
		//加入
		addInShoppingCart(shoppingCartItem);

		shoppingCartItem = null;//闭包内存泄露问题 by yucy
}
</script>