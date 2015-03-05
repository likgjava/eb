<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
.weightfont {
	color:red;
	font-weight:bold;
}
</style>
<div class="PointsMallCol firstCol newsInfo">
  <h2>礼品系列</h2>
  <ul id="series">
  	<c:forEach var="firstSeries" items="${giftSeriesFirstList}">
		<li><a href="javascript:gift_series_list.clickFirstSeries('${firstSeries.objId}');" style="font-weight:bold;"><span id="${firstSeries.objId}">${firstSeries.name}</span></a></li>
		<c:forEach var="secondSeries" items="${firstSeries.children}">
			<li id="${firstSeries.objId}">&nbsp;&nbsp;&nbsp;<a href="javascript:gift_series_list.clickSecondSeries('${secondSeries.objId}');"><span id="${secondSeries.objId}" <c:if test="${param.seriesId==secondSeries.objId}">class="weightfont"</c:if> >${secondSeries.name}</span></a></li>
		</c:forEach>
  	</c:forEach>
  </ul>
</div>

<script type="text/javascript">
var gift_series_list = {};

//点击系列
gift_series_list.clickCss = function(seriesId){
	$("a").find("span").removeClass("weightfont");
	$("span[id="+seriesId+"]").addClass("weightfont");

	$("#seriesId").val(seriesId);//填充值
	show_list.makeSearchData(seriesId);//刷新列表
}

//打开顶级系列
gift_series_list.clickFirstSeries = function(firstId){
	$("li[id="+firstId+"]:eq(0)").hasClass("hidden")?$("li[id="+firstId+"]").removeClass("hidden"):$("li[id="+firstId+"]").addClass("hidden");
	gift_series_list.clickCss(firstId)
}

//过滤二级系列
gift_series_list.clickSecondSeries = function(secondId){
	gift_series_list.clickCss(secondId)
}

$(document).ready(function(){

	//是否选中
	$.each($("ul[id=series] span"),function(index,obj){
		if($(obj).attr("id")==$("#seriesId").val()){
			$("li[id="+$(obj).parent().parent().attr("id")+"]").removeClass("hidden");
		}
	})

})
</script>
