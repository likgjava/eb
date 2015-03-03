<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
.list-view-chart li.list-item {border-bottom: 1px dashed #CCCCCC;float: left;width: 360px;height: 190px;margin: 0 0 22px;overflow: hidden;padding: 0 5px;position: relative;text-align: center;}
.list-view-chart .photo {border: 1px solid #DDDDDD;width: 350px;height: 150px;margin: 0 auto 5px;padding: 2px;position: relative;}
.list-view-chart .photo a {display: table-cell;width: 350px;height: 150px;outline: medium none;text-align: center;vertical-align: middle;}
.list-view-chart .photo a img {vertical-align: middle;width: 350px;height: 150px;}
.list-view-chart .chartname {height: 25px;line-height: 20px;overflow: hidden;text-align: center;}
</style>
<div>
	<ul class="list-view-chart">
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('amount_quantity_compare');">
					<img src="view/resource/images/amount_quantity_compare.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('amount_quantity_compare');">月采购额和采购量同比、环比图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('purcategory_amount_quantity');">
					<img src="view/resource/images/purcategory_amount_quantity.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('purcategory_amount_quantity');">品目月采购额、采购量、节省额趋势图</a>
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
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('ebuymethod_quantity');">
					<img src="view/resource/images/ebuymethod_quantity.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('ebuymethod_quantity');">各采购方式月采购量分布比例图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('purcategory_amount');">
					<img src="view/resource/images/purcategory_amount.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('purcategory_amount');">品目月采购额对比图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('purcategory_quantity');">
					<img src="view/resource/images/purcategory_quantity.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('purcategory_quantity');">品目月采购量对比图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('save_amount');">
					<img src="view/resource/images/save_amount.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('save_amount');">月采购预算、实际额及节省额对比图</a>
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
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('amount_pie');">
					<img src="view/resource/images/amount_pie.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('amount_pie');">各品目年度采购总额分布图</a>
			</div>
		</li>
		<li class="list-item">
			<div class="photo">
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('quantity_pie');">
					<img src="view/resource/images/quantity_pie.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('quantity_pie');">各品目年度采购总量分布图</a>
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
				<a href="javascript:void(0);"  onclick="DataAnalysis.loadPage('ebuymethod_quantity_pie');">
					<img src="view/resource/images/ebuymethod_quantity_pie.png">
				</a>
			</div>
			<div class="chartname">
				<a href="javascript:void(0);" onclick="DataAnalysis.loadPage('ebuymethod_quantity_pie');">各采购方式年度采购量分布图</a>
			</div>
		</li>
	</ul>
</div>

<script>
var DataAnalysis = {};

//加载页面
DataAnalysis.loadPage = function(viewName){
	$('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/buyers/dataanalysis/"+viewName+".jsp");
}

$(document).ready(function(){
	//设置返回页面
	$('#returnUrl').val($('#initPath').val()+'/view/bizplatform/buyers/dataanalysis/data_analysis.jsp');
	
});

</script>
