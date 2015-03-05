<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>全球2011-12年铜需求增速或放缓至8.4%- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130361e0401303e6afe25002b.jsp" title="全球2011-12年铜需求增速或放缓至8.4%" class="cmsHref_self">全球2011-12年铜需求增速或放缓至8.4%</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>全球2011-12年铜需求增速或放缓至8.4%</h1>
					<div class="source">
						<span>发布时间：2011-05-30</span>
						<span>发布人：-  </span>
					</div>
					<p><p>国际锻造铜委员会表示，预计2011-12年全球铜需求增速或放缓至8.4%，该组织成员为铜线和铜管制造商等铜加工商。</p>
<p>综合媒体5月30日报道，国际锻造铜委员会(International Wrought Copper Council，IWCC)称，估计2011-12年全球铜需求增速或放缓至8.4%，而2005-2010年全球铜需求平均增速为16.4%。</p>
<p>IWCC周日在一份报告中称，今年中国铜需求增速料为7%，而2010年中国铜需求录得两位数的增幅。</p>
<p>IWCC称，短期内铜价走势仍充满不确定性，因铜价处于高位令终端消费商削减库存水平，消费商不愿意持有多余的库存。</p>
<p>该报告称，目前的高铜价已经令部分领域的消费商或者转向铜的替代品--铝或这减少铜的使用量。</p>
<p>中国为全球最大的铜消费国，每年的铜需求约为全球的40%。</p>
<p>IWCC的秘书长Lovevitt称，中国消费商是否在今年下半年重建库存仍不太确定。</p>
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