<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.goodsGiftGroup {background:#F2F2F2; height:135px; margin:0;padding:5px;overflow:hidden;}
.goodsGiftList{float:left; width:500px; } 
.goodsGiftList li{float:left; width:80px;background: url("view/resource/images/bg_group.gif") no-repeat scroll -111px 36px;padding-left: 23px;}
.goodsGiftList ul {overflow:hidden; background:#fff; border:#c4c4c4 2px solid; height:120px;padding: 11px 17px 0;white-space: nowrap;}
.goodsGiftGroup .goodsGiftList .pic_choice { background:url("view/resource/images/bg_group.gif")no-repeat scroll -111px 36px transparent;}
.goodsGiftGroup .goodsGiftList .pic_choice a.pic{ border:#fcc199 1px solid; display:block; padding:0;overflow:hidden;}

.goodsGiftGroup .goodsGiftList .pic_choice_g { background:none;}
.goodsGiftGroup .goodsGiftList .pic_choice_g a.pic{ border:#fcc199 1px solid; display:block; padding:0;overflow:hidden;}

.group_result{ margin-left:510px;}
.group_result .tips {background: url("view/resource/images/bg_group.gif") no-repeat scroll -158px 2px transparent; padding-left:10px;border-bottom: 1px dotted #878787; font-size:14px; margin:5px 0;height:29px; line-height:29px;}
.group_result .price_d span {color: #CC3300;font-size: 16px;font-weight: bold;}

/*#priceDis{font-size:12px;padding-bottom:10px;}
#priceDis li{padding-right:5px;float:left;border-bottom:none;}
#priceDis li a{color:blue;}
caption {background-color:#eee;font-weight:bold;}

.goodsGiftGroup {background-color: #F2F2F2;border: 1px solid #FFFFFF;float: left; margin: 1px 1px 0; padding: 11px 13px 11px 21px;}
.goodsGiftGroup .goodsGiftList ul {background-color: #FFFFFF;border: 2px solid #C4C4C4;float: left;height: 120px;padding: 11px 17px 0;
white-space: nowrap;}
.goodsGiftGroup .goodsGiftList {float: left; height: 135px; width: 530px;}
.goodsGiftGroup .goodsGiftList li {background: url("view/resource/images/bg_group.gif") no-repeat scroll -111px 36px transparent;
float: left;list-style-type: none;padding-left: 23px;width: 80px;}
.goodsGiftGroup .goodsGiftList .none {padding: 0;}
.goodsGiftGroup .goodsGiftList .pic_choice { background-position: -134px 36px;}
.goodsGiftGroup .goodsGiftList li .pic { background-color: #FFFFFF;display: block;height: 80px;overflow: hidden;padding: 1px;
text-align: center;width: 80px;}
.goodsGiftGroup .goodsGiftList li input {margin: 6px 1px 0 0;vertical-align: top;}
.goodsGiftGroup .goodsGiftList li label {display: inline-block;line-height: 16px;margin-top: 6px;}
.goodsGiftGroup .goodsGiftList label a {color: #404040;display: inline-block;font-size: 10px; height: 28px;
 overflow: hidden;width: 72px;}
.goodsGiftGroup .goodsGiftList label a:hover { color: #FF6600;}
.goodsGiftGroup .goodsGiftList .pic_choice a.pic {border: 1px solid #FCC199;display: block;padding: 0;}
.group_result {background: url("view/resource/images/bg_group.gif") no-repeat scroll -158px 10px transparent; margin-left:540px;}

.group_result .tips, .group_result .price_d { padding-left: 7px;}
.group_result .tips {border-bottom: 1px dotted #878787; font-size: 14px;height: 29px; line-height:29px;}
.group_result .price_d {margin-top: 5px;}
.group_result .price_d span {color: #CC3300;font-size: 16px;font-weight: bold;}
.clearfix:after {clear: both;content: ""; display: block;font-size: 0;height: 0;visibility: hidden;}
.clearfix {}
.none { background: none repeat scroll 0 0 transparent !important;border: medium none !important;}

#goodsGiftDiv_0{ zoom:1;}*//*解决ie6兼容问题*/

</style>

<div id="optChooseDiv">
<%@ include file="/view/goods/showgoods/goods_optional_fitting_div.jsp" %>
</div>

<ul id="priceDis">
	<c:forEach var="hotTags" items="${hotTagsList}">
			<a style="color:#035AA0;font-weight:bold;" href="javascript:void(0);" onclick="GoodsPriceList.getPriceList('${hotTags[0]}','${hotTags[1]}');return false;">${hotTags[1] }</a>:<span class="eleRequired">
			<c:choose>
				<c:when test="${param.isMember=='true' && hotTags[4]!=null && hotTags[5]!=null }">${hotTags[4] }~${hotTags[5]}</c:when>
				<c:otherwise>${hotTags[2] }~${hotTags[3]}</c:otherwise>
			</c:choose>
			</span> |
			<input type="hidden" name="tagsId" value="${hotTags[0] }">
			
	</c:forEach>
	<span><a style="color:#035AA0;font-weight:bold;" href="javascript:void(0);" onclick="GoodsPriceList.getPriceList('','所有地区');return false;" >所有地区报价</a></span>
</ul>
<div id="priceDiv">
	<table class="frontTableList" id="goodsPriceListTable">
	<caption><span><span id="districtName"></span>供应商报价</span></caption>
		<thead>
			<tr>
				<th>供应商</th>
				<th>区域</th>
				<th>市场价</th>
				<th>供应商报价</th>
				<th>折扣</th>
				<th>选购</th>
			</tr>
		</thead>
		<tbody id="goodsPriceListTbody">
		</tbody>
	</table>
</div>


<br>
<div id="priceChart"><%@ include file="/view/goods/goodsprice/goods_sell_qty_chart.jsp"%></div>

<script>
var GoodsPriceList = {};
//更改选配
GoodsPriceList.changOpt = function(goodsPriceId,prtcPrice,supplierId){
	var param = "&goodsPriceId="+goodsPriceId+
				"&price="+prtcPrice+
				"&supplierId="+supplierId+
				"&goodsId="+$("#goodsId").val();
	$.epsDialog({
		id:"optionalFittionDiv",
		title:"选配费用计算",
		height:500, 
		width:800, 
		url:$("#initPath").val()+"/GoodsPriceShowController.do?method=toGoodsOptionalFittingView"+param
	})
	
	param = null;//闭包内存泄露问题 by yucy
}

//加入购物车
GoodsPriceList.addToShoppingCart = function(supplierId,price,markt,priceId){
	var shoppingCartItem={
			supplier:{objId:supplierId},
			goods:{objId:$('#goodsId').val()},
			goodsQty:1,
			goodsTotal:price,
			goodsPrice:price,
			agreePrice:price,
			marketPrice:markt,
			goodsUnit:$("span[id=measureUnit]").html()
	}; 

	if(priceId){
		var itemparams = $("#"+priceId).find("input[name=fittingIds]").val();
		if(itemparams){
			shoppingCartItem = eval('('+itemparams+')');
		}
	}

	//选配
	if($("tr[id="+priceId+"priceTips]").html() ){
		shoppingCartItem.goodsTotal = $("tr[id="+priceId+"]").find("strong[id=finalPrice]").attr("price");
		shoppingCartItem.goodsPrice = shoppingCartItem.goodsTotal;
		shoppingCartItem.cartGoodsOptions=[];
		$.each($("tr[id="+priceId+"priceTips]").find("span[name=priceTips]") ,function(index,obj){
			var cartGoodsOption = {};
			cartGoodsOption.option = {objId:$(obj).attr("optId")};
			cartGoodsOption.optPrice = $(obj).attr("relativePrice");
			cartGoodsOption.optQty = 1 ;
			shoppingCartItem.cartGoodsOptions.push(cartGoodsOption);
		})
	}
	
	//加入
	addInShoppingCart(shoppingCartItem);

	shoppingCartItem = null;//闭包内存泄露问题 by yucy
}

//我要留言
GoodsPriceList.addNote = function(supplierId){
	var param = "&receive="+supplierId+"&goodsId="+$("#goodsId").val();
	$.epsDialog({
		title:'我要留言',
		id:'noteFormDialog',
		url:$("#initPath").val()+"/NoteController.do?method=toNoteForm&type=00" + param
	})
	param = null;//闭包内存泄露问题 by yucy
}
//挑选商品礼包
GoodsPriceList.selectGoodGift = function(obj, index, supplierId, goodsPriceId){
	var currentGoodsGiftDivObj = $('#goodsGiftDiv_'+index);
	if($(obj).attr('title')=='挑选礼包'){
		var url = $('#initPath').val()+'/GoodsGiftPriceController.do?method=loadGoodsGiftPriceListView&maxSize=4';
		url += "&goodsPriceSupplierId="+supplierId+"&goodsPriceId="+goodsPriceId+"&goodsGiftDiv=goodsGiftDiv_"+index;
		$(currentGoodsGiftDivObj).loadPage(url,function(){
			$(obj).parent().parent().next().find("p[class=price_d]").find("span").html($(obj).parent().parent().find("strong").html());
		});
		$(currentGoodsGiftDivObj).parent().parent().show();
		$(obj).attr('title','隐藏礼包');
	}else if($(obj).attr('title')=="隐藏礼包"){
		$(currentGoodsGiftDivObj).parent().parent().hide();
		$(obj).attr('title','挑选礼包');
	}
}

//挑选商品零配件
GoodsPriceList.selectGoodAccess = function(obj, index, supplierId, goodsPriceId){
	var currentGoodsGiftDivObj = $('#goodsGiftDiv_'+index);
	if($(obj).attr('title')=='挑选零配件'){
		var url = $('#initPath').val()+'/GoodsAccessoriesPriceController.do?method=loadGoodsAccessPriceList&maxSize=4';
		url += "&goodsPriceSupplierId="+supplierId+"&goodsPriceId="+goodsPriceId+"&goodsGiftDiv=goodsGiftDiv_"+index;
		$(currentGoodsGiftDivObj).loadPage(url);
		$(currentGoodsGiftDivObj).parent().parent().show();
		$(obj).attr('title','隐藏零配件');
	}else if($(obj).attr('title')=="隐藏零配件"){
		$(currentGoodsGiftDivObj).parent().parent().hide();
		$(obj).attr('title','挑选零配件');
	}
}

//查看商品礼包详情
GoodsPriceList.viewGoodsGift = function(goodsGiftId) {
	var url = $('#initPath').val()+'/GoodsGiftController.do?method=toGoodsGiftDetailView&goodsGiftId='+goodsGiftId;
	$.epsDialog({
		id:'goodsGiftDetailDiv',
        title:'商品礼包详细信息',
        url:url,
        width: '500',
        height: '300'
    });
}

//查看商品零配件详情
GoodsPriceList.viewGoodsAccess = function(goodsAccessId) {
	var url = $('#initPath').val()+'/GoodsAccessoriesController.do?method=toGoodsAccessDetail&goodsAccessId='+goodsAccessId;
	$.epsDialog({
		id:'goodsAccessDetailDiv',
        title:'商品零配件详细信息',
        url:url,
        width: '500',
        height: '300'
    });
}

//选择或取消商品礼包
GoodsPriceList.selectOrCancleGoodsGift = function(obj) {
	var goodsGiftDiv = $(obj).attr("goodsGiftDiv");
	var goodsGiftPrice = parseFloat($(obj).attr("goodsGiftPrice"));
	var totalPriceObj = $("#"+goodsGiftDiv).find("span[name=totalPrice_"+goodsGiftDiv+"]");
	var totalPrice = parseFloat($(totalPriceObj).text().replace(/,/g,''));
	
	var packCountObj = $("#"+goodsGiftDiv).find("span[name=packCount_"+goodsGiftDiv+"]");
	var packCount = parseInt($(packCountObj).text());
	
	if($(obj).attr("checked")){
		$(obj).parent().parent().addClass("pic_choice");
		totalPrice += goodsGiftPrice;
		packCount++;
	}else{
		$(obj).parent().parent().removeClass("pic_choice");
		totalPrice -= goodsGiftPrice;
		packCount--;
	}
	
	$(totalPriceObj).text(formatAmount(totalPrice,2)+'元');
	$(packCountObj).text(packCount);
}
//选择或取消零配件
GoodsPriceList.selectOrCancleGoodsAccess = function(obj) {
	var goodsGiftDiv = $(obj).attr("goodsGiftDiv");
	var goodsGiftPrice = parseFloat($(obj).attr("goodsGiftPrice"));
	var totalPriceObj = $("#"+goodsGiftDiv).find("span[name=totalPrice_"+goodsGiftDiv+"]");
	var totalPrice = parseFloat($(totalPriceObj).text().replace(/,/g,''));
	
	var packCountObj = $("#"+goodsGiftDiv).find("span[name=packCount_"+goodsGiftDiv+"]");
	var packCount = parseInt($(packCountObj).text());
	
	if($(obj).attr("checked")){
		$(obj).parent().parent().addClass("pic_choice");
		totalPrice += goodsGiftPrice;
		packCount++;
	}else{
		$(obj).parent().parent().removeClass("pic_choice");
		totalPrice -= goodsGiftPrice;
		packCount--;
	}
	
	$(totalPriceObj).text(formatAmount(totalPrice,2));
	$(packCountObj).text(packCount);
}

//把商品和商品礼包添加到购物车
GoodsPriceList.addGG2SC = function(goodsGiftDiv){
	var cartItemObj = $("#"+goodsGiftDiv).parent().parent().prev();
	var supplierId = $(cartItemObj).attr("supplierId");
	var price = ( $("#isMember").val()=="true" && $(cartItemObj).attr("menberprice")>0 )?$(cartItemObj).attr("menberprice"):$(cartItemObj).attr("price");//是商圈会员的话就会员价
	var markt = $(cartItemObj).attr("markt");
	var giftNum = 0;
	$("#"+goodsGiftDiv).find("input[type=checkbox]").each(function(i, domEle){
		if($(domEle).attr("checked")){
			giftNum++;
		}
	});
	if(giftNum == 0){//没有选择商品礼包
		GoodsPriceList.addToShoppingCart(supplierId,price,markt);
		return ;
	}

	//购物车条目
	var shoppingCartItem={
			supplier:{objId:supplierId},
			goods:{objId:$('#goodsId').val()},
			goodsQty:1,
			goodsTotal:price,
			goodsPrice:price,
			agreePrice:price,
			marketPrice:markt,
			goodsUnit:$("span[id=measureUnit]").html()
	};

	var itemparams = $(cartItemObj).find("input[name=fittingIds]").val();
	if(itemparams){
		shoppingCartItem = eval('('+itemparams+')');
	}

	shoppingCartItem.cartGoodsGifts=[];
	
	$("#"+goodsGiftDiv).find("input[type=checkbox]").each(function(i, domEle){
		if($(domEle).attr("checked")){
			var goodsGiftId = $(domEle).attr("goodsGiftId");
			var giftPrice = $(domEle).attr("goodsGiftPrice");

			var cartGoodsGift = {};
			cartGoodsGift.goodsGift = {objId:goodsGiftId};
			cartGoodsGift.giftPrice = giftPrice;
			shoppingCartItem.cartGoodsGifts.push(cartGoodsGift);

			cartGoodsGift = null;
		}
	});
	
	//加入购物车
	addInShoppingCart(shoppingCartItem);
	shoppingCartItem = null;
}

//把商品和商品零配件添加到购物车
GoodsPriceList.addAA2SC = function(goodsAccessDiv){
	//选择的零配件数量
	var checkLength = $("#"+goodsAccessDiv).find("input[type=checkbox]:checked").length;
	
	var jsonObj = {};
	var goodsAccessCartItem = [];
	
	//购物车条目
	var cartItemObj = $("#"+goodsAccessDiv).parent().parent().prev();
	var supplierId = $(cartItemObj).attr("supplierId");
	var price = $(cartItemObj).attr("price");
	var markt = $(cartItemObj).attr("markt");

	if(checkLength == 0){//没有选择零配件
		GoodsPriceList.addToShoppingCart(supplierId,price,markt);
		return ;
	}
	
	var shoppingCartItem={
			supplier:{objId:supplierId},
			goods:{objId:$('#goodsId').val()},
			goodsQty:1,
			goodsTotal:price,
			goodsPrice:price,
			agreePrice:price,
			marketPrice:markt,
			goodsUnit:$("span[id=measureUnit]").html()
	};
	
	shoppingCartItem.cartGoodsAccessories=[];//零配件set集合
	
	$("#"+goodsAccessDiv).find("input[type=checkbox]").each(function(i, domEle){
		if($(domEle).attr("checked")){
			var goodsGiftId = $(domEle).attr("goodsGiftId");//零配件id
			var accessPrice = $(domEle).attr("goodsGiftPrice");
			
			var cartGoodsAccessories = {};
			cartGoodsAccessories.goodsAccess = {objId:goodsGiftId};
			cartGoodsAccessories.accessPrice = accessPrice;
			shoppingCartItem.cartGoodsAccessories.push(cartGoodsAccessories);

			cartGoodsAccessories = null;
		}
	});
	
	//加入购物车
	addInShoppingCart(shoppingCartItem);
	shoppingCartItem = null;
}

//取得头部选配选择div
GoodsPriceList.getOptChooseList = function( districtId ){
	$("#optChooseDiv").loadPage( $("#initPath").val() + "/GoodsPriceShowController.do?method=toOptPriceChooseListView&districtId="+districtId+"&goodsId="+$("#goodsId").val() );
}

//取得行情的列表
GoodsPriceList.getPriceList = function(districtId,districtName){

	//改变头部选配选择div
	GoodsPriceList.getOptChooseList(districtId);
	
	$("#goodsPriceListTbody").html("");
	$("#districtName").html(districtName);
	
	var param = {"objId":$("#goodsId").val()};
	if(districtId){
		param.districtId=districtId;
	}
	
	$.getJSON($("#initPath").val()+'/GoodsPriceShowController.do?method=getGoodsPriceList',param,function(json){
		if(json.goodsPriceList.length ==0){
			$("#goodsPriceList").html('<div class="sorry">该商品没有行情信息</div>');
			// 如果没有行情，则把商品的协议价取掉
			//$('#maxtPrtcPriceLi').addClass("hidden");
		}

		$.each(json.goodsPriceList,function(i,m){
			var row = "<tr id="+m.objId+" supplierId="+m.goodsPriceSupplier.supplier.objId+" price="+m.prtcPrice+" markt="+m.unitPrice+" menberPrice="+m.memberPrice+">";
			row += '<td><img src="'+$("#initPath").val()+'/view/resource/skin/sysicon/16/award_star_silver_2.png" />' + m.goodsPriceSupplier.supplier.orgName+'<input type="hidden" name="supplierId" value="'+m.goodsPriceSupplier.supplier.objId+'" ></td>';
			row += '<td>'+m.district.name+'</td>';
			row += '<td class="money">' + formatAmount(m.unitPrice,2) + '元</td>';
			row += '<td class="money"><strong style="font-weight:bold;color:red;" id="finalPrice" price="'+m.prtcPrice+'">' + formatAmount(( ($("#isMember").val()=='true'&&m.memberPrice>0)?m.memberPrice:m.prtcPrice),2) + '元</strong><br/><img src="'+$("#initPath").val()+'/view/resource/skin/sysicon/16/calculator.png" /><!--<a href="javascript:void(0);" onclick="GoodsPriceList.changOpt(\''+m.objId+'\',\''+(($("#isMember").val()=='true'&&m.memberPrice>0)?m.memberPrice:m.prtcPrice)+'\',\''+m.goodsPriceSupplier.supplier.objId+'\');return false;">更换选配</a>--><input type="hidden" name="fittingIds"></td>';
			row += '<td class="amount">' + formatAmount(m.dscuRate,2) + '%</td>';
			row += '<td class="center">';

			/**小额交易*/
			if(m.opinion>0){//借用opinion字段判断是否有没有礼包
				row += '<a href="javascript:void(0);" title="挑选礼包" onclick="GoodsPriceList.selectGoodGift(this,'+i+',\''+m.goodsPriceSupplier.supplier.objId+'\',\''+m.objId+'\');return false;" ><img src="'+$("#initPath").val()+'/view/resource/skin/sysicon/16/goods_gift.png" /></a>&nbsp;';
			}

			/**协议供货
			row += '<a href="javascript:void(0);" title="挑选零配件" onclick="GoodsPriceList.selectGoodAccess(this,'+i+',\''+m.goodsPriceSupplier.supplier.objId+'\',\''+m.objId+'\');return false;" ><img src="view/resource/skin/sysicon/16/goods_access.png" /></a>&nbsp;';
			*/
			
			row += '<img src="'+$("#initPath").val()+'/view/resource/skin/sysicon/16/cart.png" /><a href="javascript:void(0);" onclick="GoodsPriceList.addToShoppingCart(\''+m.goodsPriceSupplier.supplier.objId+'\',\''+(($("#isMember").val()=='true'&&m.memberPrice>0)?m.memberPrice:m.prtcPrice)+'\',\''+m.unitPrice+'\',\''+m.objId+'\');return false;" >加入购物车</a>';
			row += '<img src="'+$("#initPath").val()+'/view/resource/skin/sysicon/16/add.png" /><a href="javascript:void(0);" onclick="GoodsPriceList.addNote(\''+m.goodsPriceSupplier.supplier.objId+'\');return false;" >我要留言</a></td>';
			row += "</tr>";
			row += "<tr class=hidden><td colspan='6'><div id='goodsGiftDiv_"+i+"'></div></td></tr>";

			$("#goodsPriceListTbody").append(row);
			row = null;//闭包内存泄露问题 by yucy
		})
	})
	param = null;//闭包内存泄露问题 by yucy
}

$(document).ready(function(){
	GoodsPriceList.getPriceList($("#priceDis").find("input:eq(0)").val()?$("#priceDis").find("input:eq(0)").val():"",$("#priceDis").find("a:eq(0)").html());
});
</script>