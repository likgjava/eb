<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>陶氏化学一季度大中华区销售额达10.4亿美元- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813090cbdd013090fd665d0106.jsp" title="陶氏化学一季度大中华区销售额达10.4亿美元" class="cmsHref_self">陶氏化学一季度大中华区销售额达10.4亿美元</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>陶氏化学一季度大中华区销售额达10.4亿美元</h1>
					<div class="source">
						<span>发布时间：2011-06-15</span>
						<span>发布人：-  </span>
					</div>
					<p><p>6月14日信息 美国化学品公司陶氏化学14日在北京发布的第一季度大中华区业绩报告显示，第一季度该公司在大中华区的销售额达10.4亿美元。</p>
<p>陶氏大中华区总裁石博韬对记者表示，此业绩占公司前3个月亚太地区总销售的42%，占全球销售总额的8.5%。公司一季度在亚太地区的销售额为24.85亿美元。</p>
<p>石博韬未给出同比增长幅度，但数据显示2010年全年陶氏在大中华区的销售额为40.2亿美元，成为仅次于美国的第二大市场。</p>
<p>他说，中国是陶氏化学的重要市场，目前在大中华区共有6个业务中心和18个生产基地。从2000年开始，公司保持年均20%的增长速度，但未来几年增长速度可能会放缓至年均15%左右。照这样的发展速度，大概10年到15年后，中国将取代美国成为陶氏的最大市场。</p>
<p>他说，陶氏今年的发展战略包括与中国公司建立更多的合作关系，并将销售网络拓广到中国的西部和北部争取更多的发展机会。</p>
<p>对于记者问道是否有意向登陆上交所的国际板，石博韬说现阶段公司还未考虑。</p>
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