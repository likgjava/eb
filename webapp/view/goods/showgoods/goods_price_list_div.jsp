<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" id="districtId" name="districtId" value="${param.districtId }">
<input type="hidden" id="goodsId" name="goodsId" value="${param.goodsId }">


<div id="priceDiv">
	<table class="frontTableList" id="goodsPriceListTable">
		<thead>
			<tr>
				<th>供应商</th>
				<th>市场价</th>
				<th>供应商报价</th>
				<th>折扣</th>
			</tr>
		</thead>
		<tbody id="goodsPriceListTbody">
		</tbody>
	</table>
</div>

<div class="conOperation">
	<button type="button" onclick="GoodsPriceList.closeDiv()" ><span>关闭</span></button>
</div>

<script>
var GoodsPriceList = {};
var maxtPrtcPrice = 0;
var mintPrtcPrice = 0;

//关闭
GoodsPriceList.closeDiv = function(){
	$("#priceDiv").find(".epsDialogClose").click();
}

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
}

//取得行情的列表
GoodsPriceList.getPriceList = function(districtId,districtName){
	$("#goodsPriceListTbody").html("");
	
	var param = {"objId":$("#goodsId").val()};
	if(districtId){
		param.districtId=districtId;
	}
	
	$.getJSON($("#initPath").val()+'/GoodsPriceShowController.do?method=getGoodsPriceList',param,function(json){
		if(json.goodsPriceList.length ==0){
			$("#goodsPriceList").html('<div class="sorry">该商品没有行情信息</div>');
			// 如果没有行情，则把商品的协议价取掉
			$('#maxtPrtcPriceLi').addClass("hidden");
		}

		$.each(json.goodsPriceList,function(i,m){
			var row = "<tr id="+m.objId+">";
			row += '<td><img src="view/resource/skin/sysicon/16/award_star_silver_2.png" />' + m.goodsPriceSupplier.supplier.orgName+'<input type="hidden" name="supplierId" value="'+m.goodsPriceSupplier.supplier.objId+'" ></td>';
			row += '<td class="money">' + formatAmount(m.unitPrice,2) + '元</td>';
			row += '<td class="money"><strong>' + formatAmount(m.prtcPrice,2) + '元</strong></td>';
			row += '<td class="amount">' + formatAmount(m.dscuRate,2) + '%</td>';
			row += "</tr>";

			$("#goodsPriceListTbody").append(row);
			
			if(i == 0){
				maxtPrtcPrice = m.prtcPrice;
				mintPrtcPrice = m.prtcPrice;
			}
			if(parseFloat(m.prtcPrice) > maxtPrtcPrice){
				maxtPrtcPrice = m.prtcPrice;
			}
			if(parseFloat(m.prtcPrice) < maxtPrtcPrice){
				mintPrtcPrice = m.prtcPrice;
			}
		})
		
		//设置价格区域
		if(json.goodsPriceList.length !=0){
			$('#maxtPrtcPrice').html("￥"+formatAmount(mintPrtcPrice,2)+"~"+formatAmount(maxtPrtcPrice,2));
		}
	})
}

$(document).ready(function(){
	GoodsPriceList.getPriceList($("input[name=districtId]").val());
});
</script>