<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>今年平板电脑出货6320万台 内存需求增9倍- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/goodszx.jsp" title="供货资讯" class="cmsHref_self">供货资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a9560130867892c6036c.jsp" title="今年平板电脑出货6320万台 内存需求增9倍" class="cmsHref_self">今年平板电脑出货6320万台 内存需求增9倍</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>今年平板电脑出货6320万台 内存需求增9倍</h1>
					<div class="source">
						<span>发布时间：2011-06-13</span>
						<span>发布人：-赛迪网  </span>
					</div>
					<p><p>6月13日消息，据国外媒体报道，据市场研究公司IHS iSuppli最新发表的研究报告称，由于包括亚马逊在内的许多硬件厂商都在设法推出自己的iPad杀手，2011年平板电脑对DRAM内存芯片的需求将增长9倍。</p>
<p>这篇报告称，2011年全球平板电脑出货量将比2010年增长1700万台，达到6320万台。2012年的平板电脑出货量将达到1.139亿台。</p>
<p>因此，2011年平板电脑消费的DRAM内存数量将从2010年的3700万GB增加到3.337亿GB。在2012年，这个数字将增长到11亿GB，到2015年将增长到58亿GB。</p>
<p>IHS iSuppli负责DRAM内存和存储的主要分析师Mike Howard称，由于出货量的大幅度增长，平板电脑在DRAM内存市场的重要性正在提高。</p>
<p>苹果iPad平板电脑在2011年和以后的几年里将占大部分DRAM内存的需求。然而，与iPad竞争的产品正在进入这个市场，从而推动DRAM内存的需求。</p>
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