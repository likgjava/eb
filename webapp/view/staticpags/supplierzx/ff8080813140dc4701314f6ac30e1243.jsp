<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>华润雪花三下河南收购蓝牌 挑战金星- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314f6ac30e1243.jsp" title="华润雪花三下河南收购蓝牌 挑战金星" class="cmsHref_self">华润雪花三下河南收购蓝牌 挑战金星</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>华润雪花三下河南收购蓝牌 挑战金星</h1>
					<div class="source">
						<span>发布时间：2011-07-22</span>
						<span>发布人：-每日经济新闻  </span>
					</div>
					<p><P>7月21日，华润雪花啤酒(中国)有限公司(以下简称华润雪花)正式宣布：华润雪花已全资收购河南商丘蓝牌啤酒公司(以下简称蓝牌啤酒)。</P>
<P>这是继2010年4月收购驻马店悦泉啤酒，2011年1月份收购奥克啤酒郑州、漯河、安阳三间工厂后，华润雪花在河南市场第三次出手收购。业内人士对此分析认为，华润雪花的3次收购让其实际产能几乎可以与河南啤酒市场霸主金星啤酒势均力敌。</P>
<P>据了解，蓝牌啤酒在河南省属于较有影响力的区域啤酒品牌之一，产能约1.5亿升，在河南商丘及周边地区保有接近60%的市场份额，具有相当的竞争优势。</P>
<P>河南历来有啤酒大省之称，华润雪花一年间3次出手收购，可见其对于河南市场的重视。据苏赛特杨青春介绍，由于华润雪花与河南金星啤酒的产品定位较为接近，二者之间除河南市场外，在四川、贵州、山西、江苏等地曾有过多次正面交锋。</P>
<P>河南排名前三的啤酒企业中，维雪啤酒为百威英博收购，奥克已进入华润雪花，唯独排名第一的金星依然与华润雪花处于鏖战之中。杨青春分析认为，此次华润雪花收购蓝牌啤酒，有意通过步步紧逼的方式蚕食金星在河南南部的市场份额。而业内人士也普遍认为，华润雪花与金星在河南市场的正面交锋已无可避免。</P>
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