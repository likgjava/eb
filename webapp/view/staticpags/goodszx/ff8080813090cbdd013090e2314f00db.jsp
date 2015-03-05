<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>5月粗钢产量刷新单月最高纪录- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813090cbdd013090e2314f00db.jsp" title="5月粗钢产量刷新单月最高纪录" class="cmsHref_self">5月粗钢产量刷新单月最高纪录</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>5月粗钢产量刷新单月最高纪录</h1>
					<div class="source">
						<span>发布时间：2011-06-15</span>
						<span>发布人：-  </span>
					</div>
					<p><p>虽然来势汹汹的限电令对5月钢铁行业影响并未显现，但6月可能出现的需求转淡将对钢市产生不利影响。昨日，国家统计局最新数据显示，5月国内粗钢产量为6025万吨，同比增长7.8%，刷新单月产量最高纪录。日均产量为194.35万吨，环比回落2.45万吨。</p>
<p>据了解，5月粗钢日产量仍属高位，这再次反映出整个5月限电实质性影响不大，大部分钢材品种尚有盈利则是刺激钢厂维持较高开工率的另一主要原因。据中国联合钢铁网成本模型测算，5月螺纹毛利接近400元/吨，热轧170元/吨左右。</p>
<p>业内分析人士认为，理论上来讲，6、7月是钢材传统消费淡季，对于建筑施工企业而言，随着天气炎热程度的加剧，施工率会有所下滑，对建筑钢材的需求自然也会下降。此外，国内宏观环境的不确定性，货币政策的持续紧缩也将给资金密集的钢铁企业带来较大的困难。根据对近10年钢材现货价格数据的季节性分析，只有2003、2004、2009三个年份钢材价格在6、7两月之间上涨，其他年份价格基本处于下跌趋势。</p>
<p>不过也应注意到，目前全球仍处于资源紧张的局面，下半年矿价很可能在160美元左右高位徘徊，这让钢企继续感到较大的成本压力，同时也对钢价形成较强的成本支撑。</p>
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