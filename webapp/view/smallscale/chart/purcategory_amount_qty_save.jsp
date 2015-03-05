<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/smallscale/css/chart.css"/>

<input type="hidden" id="userType" name="userType" value="${userType }" />
<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="purcategoryAmountQtySaveForm">
		<ul >
			<li>
				<label>选择品目：</label>
				<c:forEach var="purCategory" items="${purCategoryList}" varStatus="status">
					<input type="radio"" name="purCategoryId" value="${purCategory[0]}" <c:if test="${status.index == 0}">checked="checked"</c:if> />${purCategory[1]}
				</c:forEach>
			</li>
			<li>
				<label>选择时间：</label>
				<a href="javascript:void(0);" onclick="PurcategoryAmountQtySave.modifyYear('-','year');"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>
				<input type="text" name="year" id="year" readonly="readonly" style="width:35px;" value="2011" disabled="disabled" />
				<a href="javascript:void(0);" onclick="PurcategoryAmountQtySave.modifyYear('+','year');"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<div id="purcategoryAmountQtySaveDiv"></div>

<div class="conOperation">
	<button type="button" name="historyBackBtn"><span><spring:message code="globe.return"/></span></button>
	<button type="button" id="exportPurcategoryAmountQtySaveToExcel"><span>导出</span></button>
</div>

<script>
var PurcategoryAmountQtySave = {};

//修改年份
PurcategoryAmountQtySave.modifyYear = function(oper,yerarId){
	var year = Number($("#"+yerarId).val());
	if(oper == "+"){
		year = year + 1;
	}else{
		year = year - 1;
	}
	$("#"+yerarId).val(year);
}

//加载图表和列表数据
PurcategoryAmountQtySave.loadChartAndTableData = function(){
	var purCategoryId = $("#purcategoryAmountQtySaveForm").find("input[type=radio]:checked").val();
	if(purCategoryId == null){
		$("#purcategoryAmountQtySaveDiv").html('<div class="formTips attention"><ul><li>提示：暂无交易信息！</li></ul></div>');
		$("#exportPurcategoryAmountQtySaveToExcel").hide();
		return ;
	}
	var url = $('#initPath').val()+'/ChartController.do?method=getPurcategoryAmountQtySaveData&year='+$("#year").val()+"&purCategoryId="+purCategoryId+"&userType="+$("#userType").val();
	$("#purcategoryAmountQtySaveDiv").loadPage(url);
}

$(document).ready(function(){
	//加载图表和列表数据
	PurcategoryAmountQtySave.loadChartAndTableData();
	
	//查询
	$("#query").click(function(){
		PurcategoryAmountQtySave.loadChartAndTableData();
	});

	//导出数据
	$("#exportPurcategoryAmountQtySaveToExcel").click(function(){
		var purCategoryId = $("#purcategoryAmountQtySaveForm").find("input[type=radio]:checked").val();
		var url = $("#initPath").val()+'/ChartController.do?method=exportPurcategoryAmountQtySaveToExcel&year='+$("#year").val()+"&purCategoryId="+purCategoryId+"&userType="+$("#userType").val();
		window.location.href = url;
	});
})
</script>