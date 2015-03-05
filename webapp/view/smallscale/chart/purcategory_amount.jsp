<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/smallscale/css/chart.css"/>

<input type="hidden" id="userType" name="userType" value="${userType }" />
<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="purcategoryAmountForm">
		<ul >
			<li>
				<label>选择品目：</label>
				<c:forEach var="purCategory" items="${purCategoryList}" varStatus="status">
					<input type="checkbox" name="purCategoryId" value="${purCategory[0]}" <c:if test="${status.index < 3}">checked="checked"</c:if> />${purCategory[1]}
				</c:forEach>
			</li>
			<li>
				<label>选择时间：</label>
				<a href="javascript:void(0);" onclick="PurcategoryAmount.modifyYear('-','year');"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>
				<input type="text" name="year" id="year" readonly="readonly" style="width:35px;" value="2011" disabled="disabled" />
				<a href="javascript:void(0);" onclick="PurcategoryAmount.modifyYear('+','year');"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<div id="purcategoryAmountDiv"></div>

<div class="conOperation">
	<button type="button" name="historyBackBtn"><span><spring:message code="globe.return"/></span></button>
	<button type="button" id="exportPurcategoryAmountToExcel"><span>导出</span></button>
</div>

<script>
var PurcategoryAmount = {};

//修改年份
PurcategoryAmount.modifyYear = function(oper,yerarId){
	var year = Number($("#"+yerarId).val());
	if(oper == "+"){
		year = year + 1;
	}else{
		year = year - 1;
	}
	$("#"+yerarId).val(year);
}

//加载图表和列表数据
PurcategoryAmount.loadChartAndTableData = function(){
	var purCategoryId = "";
	$("#purcategoryAmountForm").find("input[type=checkbox]:checked").each(function(index, domE){
		purCategoryId += $(domE).val() + ",";
	});
	if(purCategoryId != ""){purCategoryId = purCategoryId.substring(0,purCategoryId.length-1)}
	else{alert("至少选择一个采购品目进行对比！"); return;}
	
	var url = $('#initPath').val()+'/ChartController.do?method=getPurcategoryAmountData&year='+$("#year").val()+"&purCategoryId="+purCategoryId+"&userType="+$("#userType").val();
	$("#purcategoryAmountDiv").loadPage(url);
}

$(document).ready(function(){
	//选择品目
	$("#purcategoryAmountForm input[name=purCategoryId]").click(function(e){
		if($("input[name=purCategoryId]:checked").length > 3){
			alert("最多可以选择三个品目进行对比！");
			return false;
		}
	});

	//加载图表和列表数据
	PurcategoryAmount.loadChartAndTableData();
	
	//查询
	$("#query").click(function(){
		PurcategoryAmount.loadChartAndTableData();
	});

	//导出数据
	$("#exportPurcategoryAmountToExcel").click(function(){
		var purCategoryId = "";
		$("#purcategoryAmountForm").find("input[type=checkbox]:checked").each(function(index, domE){
			purCategoryId += $(domE).val() + ",";
		});
		if(purCategoryId != ""){purCategoryId = purCategoryId.substring(0,purCategoryId.length-1)}
		
		var url = $("#initPath").val()+'/ChartController.do?method=exportPurcategoryAmountToExcel&year='+$("#year").val()+"&purCategoryId="+purCategoryId+"&userType="+$("#userType").val();
		window.open(url);
	});
})
</script>