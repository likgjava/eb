<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>三大钢厂集体降价 分析称需求减弱是主因- 【阳光易购】</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/web.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/skin07/css/seller.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/listDetail.css" />
<!--JS-->
<script type="text/javascript" src='/view/srplatform/portal/include/common.js'></script>
</head>

<body>

<div id="container">
	<!-- 头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!-- 头部结束-->
    <!--主要内容 开始-->
	<div id="sysContent" class="page">
		<div id="contentSub" class="index3pa">
			<%@ include file="/view/staticpags/load/left_cms.jsp" %>
		</div>
		<div id="contentMain" class="index3pa">
			<div id="conTitle">
				<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/supplierzx.jsp" title="供应商资讯" class="cmsHref_self">供应商资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b229f270391.jsp" title="三大钢厂集体降价 分析称需求减弱是主因" class="cmsHref_self">三大钢厂集体降价 分析称需求减弱是主因</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>三大钢厂集体降价 分析称需求减弱是主因</h1>
					<div class="source">
						<span>发布时间：2011-06-17</span>
						<span>发布人：-  </span>
					</div>
					<p><p>武钢昨日下调7月份钢铁产品出厂价格，最大降幅500元/吨。至此，宝钢、鞍钢、武钢三大龙头钢企已全部下调了7月出厂价。钢价5月初至今一直走弱，业内人士分析，钢市的下行趋势将持续。</p>
<p>根据武钢最新价格，其大部分热轧下调100元/吨，不同型号的无取向硅钢下调200-400元/吨，冷板卷下调150-500元/吨，酸洗钢下调200元/吨。</p>
<p>此前，宝钢也下调了7月钢价，大部分产品下调100-500元/吨。鞍钢在14日出台政策，其冷热轧产品普遍下调80-100元/吨。</p>
<p>生产成本最高的冷轧则在最近一波降价中，降得&ldquo;最狠&rdquo;，一个多月间跌幅超过120元/吨。</p>
<p>&ldquo;我的钢铁网&rdquo;咨询总监徐向春认为，此前钢价从3月中旬至5月初，一直是反弹走稳的势头，此轮回调主要受需求减弱的影响，&ldquo;下行趋势会持续一段时间。&rdquo;</p>
<p>中联钢分析师胡艳平告诉记者，钢市下行以来，跌幅最明显的就是板材，而下跌主因在于近期工业继续转弱，其中汽车行业5月份产销连续第二个月负增长。</p>
</p>
				</div>
			</div>
		</div>
	</div>
	<!--主要内容 结束-->
	<!-- 脚开始 -->
	<div class="footer">
	    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!-- 脚结束 -->
</div>
</body>

</html>

<script>
	// 为左边栏设定选中样式,判断用于解决点击左边栏后样式被覆盖问题
	var id = $("#channelId").val();
	if(null != $('.subnav>li[id='+id+']').html()) {
		$('.subnav>li').removeClass('selected');
		$('.subnav>li[id='+id+']').addClass('selected');
	}
</script>