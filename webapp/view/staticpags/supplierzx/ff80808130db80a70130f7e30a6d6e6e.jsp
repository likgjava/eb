<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>雅诗兰黛旗下多品牌提价近一成- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7e30a6d6e6e.jsp" title="雅诗兰黛旗下多品牌提价近一成" class="cmsHref_self">雅诗兰黛旗下多品牌提价近一成</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>雅诗兰黛旗下多品牌提价近一成</h1>
					<div class="source">
						<span>发布时间：2011-07-05</span>
						<span>发布人：-每日经济新闻  </span>
					</div>
					<p><P>继LVMH(路威酩轩)和欧莱雅两大化妆品集团分别在今年前两轮“提价潮”中集体涨价后，近日《每日经济新闻》获悉，雅诗兰黛集团旗下雅诗兰黛、倩碧、MAC以及悦木之源四个化妆品子品牌已于本月1日起集体上调价格，涨幅大约在10%之内。</P>
<P>针对提价原因，昨日(7月4日)记者多次致电雅诗兰黛中国区公关部负责人，截至记者发稿时，尚未取得回应。</P>
<P>另据媒体援引雅诗兰黛内部人士的话表示：“涨价是公司原材料成本上涨所致，以及研发投入、人力成本、物流成本的提高。”</P>
<P>国内化妆品行业营销专家张兵武向《每日经济新闻》表示，“高端化妆品本身售价不菲，如果说以 ‘原材料成本上升’为由而涨价，其实更多是一种为了涨价的说辞而已。”</P>
<P>“产品即便有了一成的涨幅，对于其消费人群而言也影响不大。”张兵武进一步指出。</P>
<P>至于为什么没有选择在前两轮涨价潮中提价，一位业内人士向每经记者表示，“这就像日系化妆品集团至今仍未有涨价预期一样，都是企业间竞争的策略和商业操作。”</P>
<P>而记者从国内几个规模较大的化妆品代购商方面得到证实，其在雅诗兰黛美国总部进货代购的部分产品售价也已陆续上调，涨价效应已逐步传至国内化妆品代购业。</P>
<P>据悉，此次雅诗兰黛集团提价的品牌暂未涉及旗下两个引入中国的品牌——高端护肤品牌海蓝之谜以及彩妆品牌芭比波朗;而在已进行调价的这四个品牌中，也并非涉及所有产品，只有部分系列的部分产品出现涨价。</P>
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