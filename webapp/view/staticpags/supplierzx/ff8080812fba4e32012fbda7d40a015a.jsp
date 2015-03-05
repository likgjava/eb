<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>东莞汽车经销商：车源紧 厂家配车量减少- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fba4e32012fbda7d40a015a.jsp" title="东莞汽车经销商：车源紧 厂家配车量减少" class="cmsHref_self">东莞汽车经销商：车源紧 厂家配车量减少</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>东莞汽车经销商：车源紧 厂家配车量减少</h1>
					<div class="source">
						<span>发布时间：2011-05-05</span>
						<span>发布人：-东莞日报  </span>
					</div>
					<p><p>　　受日本地震及部分厂家产能问题影响，东莞市场上主流的欧系、美系及日系汽车品牌开始受困于车源紧张，部分车型优惠大幅缩水。&ldquo;我们店目前只有一百多台库存，如果想卖，五一假期几天就能卖完，但现在车源紧张，我们不想卖那么快，因为厂家配车量在减少。&rdquo;一位一汽丰田经销商面对记者时感叹。</p>
<p>　　日系车优惠缩水</p>
<p>　　&ldquo;日本地震对零部件的供给会有影响。目前市场上热销的欧系、美系与日系车都是全球采购配件，而全球采购又分为一级供应商、二级供应商与三级供应商。所以，地震对车市的影响有多大很难估计。&rdquo;东莞东风南方莞太专营店总经理樊明辉表示。</p>
<p>　　而记者近期走访经销商了解到，目前市场上日系车车源是相对紧张，诸多热销车型的优惠幅度也大幅缩水。</p>
<p>　　目前，一汽丰田锐志的优惠从原先的5000元缩减到2000元;曾经优惠幅度达到一万多的卡罗拉，现在出现供不应求，除了享受国家3000元节能补贴外，再无其他优惠。东风日产新阳光、逍客、天籁及轩逸部分车型车源持续紧张，五一之后，东风日产旗下车型优惠幅度开始减少，五一期间骊威、天籁、轩逸三款车型订车送1600-2000元油卡的活动也已结束。此外，在今年3月优惠达到2万元的凯美瑞，目前的优惠也只有1万元。而马自达因今年厂家的产能相应提高，所以车源相对其他日系品牌还算充足，目前优惠幅度仍停留在几个月之前，没有增减。</p>
<p>　　采访中，不少经销商表示，造成目前日系车优惠缩水的主要问题点就在于车源，厂家的配车量减少，经销商的库存不多，所以大幅度的优惠促销暂时取消。</p>
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