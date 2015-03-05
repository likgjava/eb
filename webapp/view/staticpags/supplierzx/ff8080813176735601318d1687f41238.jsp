<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>海尔意向收购三洋电机洗衣机和冰箱等家用电器业务- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d1687f41238.jsp" title="海尔意向收购三洋电机洗衣机和冰箱等家用电器业务" class="cmsHref_self">海尔意向收购三洋电机洗衣机和冰箱等家用电器业务</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>海尔意向收购三洋电机洗衣机和冰箱等家用电器业务</h1>
					<div class="source">
						<span>发布时间：2011-08-03</span>
						<span>发布人：-新华网  </span>
					</div>
					<p><P>8月2日电 记者2日从海尔集团了解到，海尔日前与日本三洋电机株式会社就海尔意向收购三洋电机在日本、印度尼西亚、马来西亚、菲律宾和越南的洗衣机、冰箱及其他家用电器业务签署备忘录。</P>
<P>据介绍，海尔此次收购涉及在上述区域的三洋电机洗衣机、冰箱的研发、制造以及所收购公司家用电器的销售和服务业务，以及三洋洗衣机品牌AQUA和相关品牌。</P>
<P>同时，双方就“SANYO”标识的使用达成一致。此次收购完成后，海尔在日本市场将实现“Haier”和“AQUA”双品牌运营;在越南、印度尼西亚、菲律宾和马来西亚市场，海尔在运营“Haier”品牌的同时，将在指定期间内同步运营“SANYO”品牌。</P>
<P>海尔集团表示，海尔的目标是成为全球白色家电行业的引领者。三洋电机在家用电器领域具有卓越的技术积累和人才积累，在日本和东南亚地区有运营良好的研发、制造和销售基地，此次意向收购三洋电机洗衣机、冰箱等家用电器业务是海尔整体发展战略中的重要一步。</P>
<P>海尔集团副总裁杜镜国表示，备忘录的签署标志着海尔在日本以及东南亚地区将形成两个研发中心、四个制造基地以及六个地域的本土化市场营销架构，必将更好地满足日本和东南亚地区日益增长和变化的消费者需求，实现未来可持续的成长。</P>
<P>海尔集团成立于1984年，总部位于山东青岛，是一家跨国性住居生活解决方案提供商。2010年海尔集团全球营业额207亿美元。海尔集团的家用电器和消费电子业务范围涉及冰箱、洗衣机、空调、热水器、厨房电器、彩电、电脑、手机以及U-HOME系列产品的研发、制造和销售。</P>
<P>三洋电机隶属于松下集团，是一家在商用和民用领域提供环境、能源先进技术及产品的国际化企业。</P>
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