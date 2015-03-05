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
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('amount_qty_compare');">
					<img src="view/resource/images/amount_quantity_compare.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('amount_qty_compare');">月采购额和采购量同比、环比图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.toPage('toPurcategoryAmountQtySaveView');">
					<img src="view/resource/images/purcategory_amount_quantity.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.toPage('toPurcategoryAmountQtySaveView');">品目月采购额、采购量、节省额趋势图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('ebuymethod_amount');">
					<img src="view/resource/images/ebuymethod_amount.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('ebuymethod_amount');">各采购方式月采购额分布比例图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('ebuymethod_qty');">
					<img src="view/resource/images/ebuymethod_qty.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('ebuymethod_qty');">各采购方式月采购量分布比例图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.toPage('toPurcategoryAmountView');">
					<img src="view/resource/images/purcategory_amount.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.toPage('toPurcategoryAmountView');">品目月采购额对比图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.toPage('toPurcategoryQtyView');">
					<img src="view/resource/images/purcategory_quantity.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.toPage('toPurcategoryQtyView');">品目月采购量对比图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('plan_actual_save_amount');">
					<img src="view/resource/images/plan_actual_save_amount.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('plan_actual_save_amount');">月采购预算、实际额及节省额对比图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('save_amount_compare');">
					<img src="view/resource/images/save_amount_compare.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('save_amount_compare');">月采购节省总额同比、环比图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('purcategorys_amount_pie');">
					<img src="view/resource/images/purcategorys_amount_pie.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('purcategorys_amount_pie');">各品目年度采购总额分布图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('purcategorys_qty_pie');">
					<img src="view/resource/images/purcategorys_qty_pie.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('purcategorys_qty_pie');">各品目年度采购总量分布图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('ebuymethod_amount_pie');">
					<img src="view/resource/images/ebuymethod_amount_pie.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('ebuymethod_amount_pie');">各采购方式年度采购额分布图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('ebuymethod_qty_pie');">
					<img src="view/resource/images/ebuymethod_qty_pie.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('ebuymethod_qty_pie');">各采购方式年度采购量分布图</a>
			</div>
		</li>
	</ul>
</div>

<script>
var DataAnalysis = {};

//加载JSP页面
DataAnalysis.loadPage = function(viewName){
	$('#conBody').loadPage($('#initPath').val()+"/view/smallscale/chart/"+viewName+".jsp?userType=buyer");
}

//跳转页面
DataAnalysis.toPage = function(methodName){
	$('#conBody').loadPage($('#initPath').val()+"/ChartController.do?method="+methodName+"&userType=buyer");
}

$(document).ready(function(){
	//设置返回页面
	$('#returnUrl').val($('#initPath').val()+'/view/smallscale/chart/data_analysis.jsp');
	
});

</script>
