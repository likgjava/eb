<%@ page pageEncoding="UTF-8"%>

<script type="text/javascript">
var confrim_result_view = {};

//显示复选框
confrim_result_view.showCheck = function(){
	$.each( $("#minPriceRecordTable").find(".col_title") ,function( index,obj ){
		var href = $(obj).find("a[name*=supplier]");

		if(!$(href).parent().prev("div").find("input[name*=eliminateSupplier]").attr("disabled") && $(href).parent().parent().next("td").html().indexOf("暂无报价")<=0 ){
			href.before('<input type="checkbox" name="confirmResult" id="check'+ href.attr("name").replace("supplier","") +'">');
		}
	})
}

//确定
confrim_result_view.checkResult = function(){
	//是否至少三家
	if($("#threeMust").val()=='true' && $("#minPriceRecordTable").find("tr").length < 3 ){
		alert("该项目设定至少3家供应商！");
		return;
	}
	var dealSupplier = [];
	var lostSupplier = [];

	if( $("input[name=confirmResult]:checked").html()==null ){
		alert("请保证一家以上供应商成交,否则请放弃");
		return;
	}
	$.each( $("input[name=confirmResult]") ,function( index , obj ){
		if($(obj).attr("checked")){
			dealSupplier.push( $(obj).attr("id").replace("check","") );
		}else{
			lostSupplier.push( $(obj).attr("id").replace("check","") );
		}
	})
	
	//是否选择了推荐的那个
	var minSupplierId = $("td.recommend").find("input[name=eliminateSupplier]")!=null?$("td.recommend").find("input[name=eliminateSupplier]").val():'';
	var isSelectMin =  ( dealSupplier.length>0 && dealSupplier.toString().indexOf(minSupplierId)>=0 );
	
	$.epsDialog({
		title:"报价明细确认",
		url:$("#initPath").val()+"/BuyResultXyghController.do?method=toConfirmDetailView&projectId="+$("#projectId").val()+"&dealSupplier="+dealSupplier.toString()+"&lostSupplier="+lostSupplier.toString()+"&isSelectMin="+isSelectMin
	})
}

//放弃成交
confrim_result_view.toGiveUp = function(){
	$.epsDialog({
		title:"放弃成交",
		url:$("#initPath").val()+"/view/agreement/bargin/buyresult/confrim_giveup_view.jsp"
		,height:300
	})
}

//稍后确认
confrim_result_view.checkLater = function(){
	$("#operationDiv").html(null);
	$("input[name=confirmResult]").remove();
}

//获取推荐的供应商
confrim_result_view.getRecommend = function(){
	var minTotalPrice = Number.MAX_VALUE; //最低总报价
	var supplierIds = []; //报价供应商id
	$.each($("span[name=cellTotal]"), function(index, obj){
		supplierIds.push( $(obj).attr("id") );
		//计算最低报价金额
		var bPrice = Number( $(obj).html().replace(/,/g,'') );
		if(bPrice<minTotalPrice && bPrice>0 ){
			minTotalPrice = bPrice;
		}
	})
	
	//无供应商报价
	if(supplierIds.length <= 0){
		$('#confirmDealBut').hide(); //屏蔽‘确认成交’按钮
		return;
	}
	
	//获取供应商评价信息
	$.getJSON($("#initPath").val()+"/EvaluateController.do?method=getEvaluateByOrgId", {"orgIds":supplierIds.toString()}, function(json){
		if(json.success){
			var minSupplierId; //最低报价的供应商id
			var maxScore = -1; //最高得分
			$.each(json.evaluateInfoList, function(index,obj){
				var bidPrice = Number($("span[id="+obj[0]+"]").html().replace(/,/g,'')); //供应商报价
				var evaluateScore = (obj[1]==0 ? 5 : obj[1]); //评价得分(为评分的设置为中间分5分)
				var score = confrim_result_view.evalScore(minTotalPrice,bidPrice,evaluateScore,60,40);
				if(score > maxScore){
					maxScore = score;
					minSupplierId = obj[0];
				}
			})
			//推荐的那个
			$("input[name=confirmResult][id=check"+minSupplierId+"]").parent().parent().addClass("recommend");
		}
	})
}

//打分公式：为供应商打分(参考供应商的报价、评价)
//basePrice:基准价	bidPrice:报价	evaluateScore:评价得分	priceProportion:价格比重	evaluateProportion:评价比重
confrim_result_view.evalScore = function(basePrice,bidPrice,evaluateScore,priceProportion,evaluateProportion){
	return basePrice/bidPrice*priceProportion + evaluateScore/10*evaluateProportion;
}

$(document).ready(function(){

	//显示复选框
	confrim_result_view.showCheck();

	//获取推荐的供应商
	confrim_result_view.getRecommend();
});
</script>

<div class="conOperation">
	<button id="confirmDealBut" onclick="confrim_result_view.checkResult();"><span>确认成交</span></button>
	<button onclick="confrim_result_view.toGiveUp();"><span>放弃成交</span></button>
	<button onclick="confrim_result_view.checkLater();"><span>稍后确认</span></button>
</div>