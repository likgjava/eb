<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>40大矿业巨头去年利润过千亿- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01307723060542ed.jsp" title="40大矿业巨头去年利润过千亿" class="cmsHref_self">40大矿业巨头去年利润过千亿</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>40大矿业巨头去年利润过千亿</h1>
					<div class="source">
						<span>发布时间：2011-06-10</span>
						<span>发布人：-  </span>
					</div>
					<p><p>普华永道最新报告《矿业：竞争已发生改变》(Mine:Thegamehaschanged)指出，受业绩强劲支撑，去年全球最大的40家矿业公司的净利润达到令人吃惊的1100亿美元，同比增长156%。同时，受中国为主的新兴市场推动，全球矿业行业并购将更加频繁，预计2011年仅来自全球40大矿业巨头的并购金额就高达1200亿美元。</p>
<p>数据显示，几乎所有重要金属的平均价格在最近几年内都有所上涨，2010年的平均上涨幅度在26%至49%之间;受急剧攀升的矿产品价格以及2010年产量增加5%的推动，全世界前40大矿业公司的收入在去年猛增32%，达到创纪录的4350亿美元。普华永道全球矿业主管金鑫(TimGoldsmith)表示：&ldquo;采矿行业已发生了根本性的变化，进入了一个新的时代，来自新兴市场不断增长的产品需求将会推动该行业的发展，供应将会是未来面临的最大挑战。&rdquo;</p>
<p>根据这份报告，成本上升抑制了前40大矿业公司的综合股本回报率。普华永道全球矿业负责人TimGoldsmith表示：&ldquo;尽管受益于创纪录的产品价格，但前40大矿业公司的成本已显著上升，主要是因为劳动力短缺所至，以股本回报率为例，利润率低于历史峰值;在没有迹象表明通胀压力有所缓和以及全球金融环境仍有所动荡的情况下，维持成本控制对于矿业而言依然是极其重要的。&rdquo;</p>
<p>普华永道中国矿业主管合伙人苏启元表示：&ldquo;中国对原材料的需求继续成为推动全球矿业迈入投资和增长新时代的强劲动力。&rdquo;</p>
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