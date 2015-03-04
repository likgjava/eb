<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.aTips {
    border: 1px solid #DDDDDD;
    color: #404040;
    float: left;
    line-height: 24px;
    margin-bottom: 10px;
    padding: 2px 10px 2px 23px;
    background-color: #FFFEE2;
    background-position: 0 -972px;
    border-color: #FFCA80;
    float: none;
    overflow: hidden;
}

.relativeTips {
    border: 1px solid #DDDDDD;
    color: #404040;
    float: left;
    line-height: 24px;
    margin-bottom: 10px;
    padding: 2px 10px 2px 13px;
    background-color: #F4F9FD;
    background-position: 0 -972px;
    border-color: #F4CAFD;
    float: none;
    overflow: hidden;
}
</style>

<c:if test="${!empty goodsOptFitPriceList && fn:length(goodsOptFitPriceList) > 0}">
<div class="aTips">
	<!-- 两标记 -->
	<c:set var="goodsPriceFlag" value=""/>
	<c:set var="goodsParamFlag" value=""/>
	<c:set var="goodsOptFitFlag" value=""/>
	<c:forEach var="goodsOptFitPrice" items="${goodsOptFitPriceList}">
		<c:if test="${goodsParamFlag!=goodsOptFitPrice.goodsOptionalFitting.goodsParam.objId}">
		<font color="#FF6501" style="font-weight:bold;">${goodsOptFitPrice.goodsOptionalFitting.goodsParam.paramName}（${goodsOptFitPrice.goodsOptionalFitting.goodsParam.paramValue}）</font>：<c:set var="goodsParamFlag" value="${goodsOptFitPrice.goodsOptionalFitting.goodsParam.objId}"/>
		</c:if>
		<c:if test="${goodsOptFitFlag!=goodsOptFitPrice.goodsOptionalFitting.objId}">
			<c:set var="goodsOptFitFlag" value="${goodsOptFitPrice.goodsOptionalFitting.objId}"/>
			<input onclick="goods_optional_fitting_div.chooseOption('${goodsOptFitPrice.objId }','${goodsParamFlag}',this);" id="${goodsOptFitPrice.objId}" goodsoptid="${goodsOptFitPrice.goodsOptionalFitting.objId}" name="${goodsParamFlag}" type="checkbox"/>${goodsOptFitPrice.goodsOptionalFitting.optionContent}
		</c:if>
		<input type="hidden" optioncontent="${goodsOptFitPrice.goodsOptionalFitting.optionContent}" paramname="${goodsOptFitPrice.goodsOptionalFitting.goodsParam.paramName}" goodspriceid="${goodsOptFitPrice.goodsPrice.objId }" paramid="${goodsParamFlag}" name="${goodsOptFitPrice.goodsOptionalFitting.objId }" value="${goodsOptFitPrice.relativePrice}"/>
	</c:forEach>
	
	<a href="javascript:void(0);" onclick="goods_optional_fitting_div.cancelAll();"><font color="#005AA0">【全部取消】</font></a>
</div>
</c:if>

<script type="text/javascript">
var goods_optional_fitting_div = {}

//全部取消
goods_optional_fitting_div.cancelAll = function(){
	$(".aTips").find("input[type=checkbox]").attr("checked",false);
	goods_optional_fitting_div.caculatePrice();
}

//选配的切换
goods_optional_fitting_div.chooseOption = function(optFitPriceId ,paramId , e){
	$("input[name="+paramId+"]").not("input[id="+optFitPriceId+"]").attr("checked",false);
	goods_optional_fitting_div.caculatePrice();
}

//计算选配价格
goods_optional_fitting_div.caculatePrice = function(){
	//先恢复初值
	$.each( $("strong[id=finalPrice]"),function(index, obj){
		var price = $(obj).parent().parent().attr("price");
		$(obj).attr("price", price );
		$(obj).html( formatAmount(price,2) + "元" );
	} )
	
	//去掉所有tips
	$("tr[name=priceTips]").remove();
	
	//运算
	$.each( $(".aTips").find("input[type=checkbox]:checked"), function(index , obj){
		var goodsParamFlag = $(obj).attr("name");
		var goodsOptId = $(obj).attr("goodsoptid");
		$.each( $("input[paramid="+goodsParamFlag+"][name="+goodsOptId+"]"), function(i , o ){
			var goodspriceid = $(o).attr("goodspriceid");
			var prtcPrice = $("tr[id="+goodspriceid+"]").find("strong[id=finalPrice]").attr("price");
			var relativePrice = $(o).val();
			var sum = Number( relativePrice ) + Number( prtcPrice ) ;
			$("tr[id="+goodspriceid+"]").find("strong[id=finalPrice]").attr("price", sum );
			$("tr[id="+goodspriceid+"]").find("strong[id=finalPrice]").html( formatAmount(sum,2) + "元" );
			//tips
			if( !$("tr[id="+goodspriceid+"priceTips]").html() ){
				$("tr[id="+goodspriceid+"]").after('<tr id="'+goodspriceid+'priceTips" name="priceTips"><td colspan="6"></td></tr>')
			}
			$("tr[id="+goodspriceid+"priceTips]").find("td").append( '<span name="priceTips" class="relativeTips" relativePrice="'+ relativePrice +'" optId="'+$(o).attr("name")+'">'+$(o).attr("paramname")+'换成'+$(o).attr("optioncontent")+( Number( relativePrice )<0?'：<font color="green">省':'：<font color="red">加' )+formatAmount( Math.abs(relativePrice),2)+'元</font></span>&nbsp;' );
		} )
	})
}
</script>
