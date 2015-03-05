<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
.list-view-chart li.list-item {border-bottom: 1px dashed #CCCCCC;float: left;width: 327px;height: 190px;margin: 0 0 22px;overflow: hidden;position: relative;text-align: center;}
.list-view-chart .photo {border: 1px solid #DDDDDD;width: 317px;height: 150px;margin: 0 auto 5px;padding: 2px;position: relative;}
.list-view-chart .photo a {display: table-cell;width: 317px;height: 150px;outline: medium none;text-align: center;vertical-align: middle;}
.list-view-chart .photo a img {vertical-align: middle;width: 317px;height: 150px;}
.list-view-chart .chartname {height: 25px;line-height: 20px;overflow: hidden;text-align: center;}
</style>
<div>
	<ul class="list-view-chart">
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="SaleDataAnalysis.loadPage('amount_qty_compare');">
					<img src="view/resource/images/sale_amount_qty_compare.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="SaleDataAnalysis.loadPage('amount_qty_compare');">月销售额和销售量同比、环比图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="SaleDataAnalysis.toPage('toPurcategoryAmountQtySaveView');">
					<img src="view/resource/images/sale_purcategory_amount_qty.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="SaleDataAnalysis.toPage('toPurcategoryAmountQtySaveView');">品目月销售额、销售量趋势图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="SaleDataAnalysis.toPage('toPurcategoryAmountView');">
					<img src="view/resource/images/sale_purcategory_amount.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="SaleDataAnalysis.toPage('toPurcategoryAmountView');">品目月销售额对比图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="SaleDataAnalysis.toPage('toPurcategoryQtyView');">
					<img src="view/resource/images/sale_purcategory_qty.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="SaleDataAnalysis.toPage('toPurcategoryQtyView');">品目月销售量对比图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="SaleDataAnalysis.loadPage('purcategorys_amount_pie');">
					<img src="view/resource/images/sale_purcategorys_amount_pie.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="SaleDataAnalysis.loadPage('purcategorys_amount_pie');">各品目年度销售总额分布图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="SaleDataAnalysis.loadPage('purcategorys_qty_pie');">
					<img src="view/resource/images/sale_purcategorys_qty_pie.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="SaleDataAnalysis.loadPage('purcategorys_qty_pie');">各品目年度销售总量分布图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="SaleDataAnalysis.loadPage('during_goods_amount_pie');">
					<img src="view/resource/images/during_goods_amount_pie.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="SaleDataAnalysis.loadPage('during_goods_amount_pie');">时间段内各商品销售额分布图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="SaleDataAnalysis.loadPage('during_goods_qty_pie');">
					<img src="view/resource/images/during_goods_qty_pie.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="SaleDataAnalysis.loadPage('during_goods_qty_pie');">时间段内各商品销售量分布图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="SaleDataAnalysis.loadPage('bidding_winner_records_column');">
					<img src="view/resource/images/bidding_winner_records_column.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="SaleDataAnalysis.loadPage('bidding_winner_records_column');">各季度中标情况</a>
			</div>
		</li>
	</ul>
</div>

<script>
var SaleDataAnalysis = {};

//加载JSP页面
SaleDataAnalysis.loadPage = function(viewName){
	$('#conBody').loadPage($('#initPath').val()+"/view/smallscale/chart/"+viewName+".jsp?userType=supplier");
}

//跳转页面
SaleDataAnalysis.toPage = function(methodName){
	$('#conBody').loadPage($('#initPath').val()+"/ChartController.do?method="+methodName+"&userType=supplier");
}

$(document).ready(function(){
	//设置返回页面
	$('#returnUrl').val($('#initPath').val()+'/view/smallscale/chart/sale_data_analysis.jsp');
	
});

</script>
