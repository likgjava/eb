<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>波音在华采购额计划5年翻番- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="采购焦点资讯" class="cmsHref_self">采购焦点资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fde6366012fe19a1e2e001b.jsp" title="波音在华采购额计划5年翻番" class="cmsHref_self">波音在华采购额计划5年翻番</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>波音在华采购额计划5年翻番</h1>
					<div class="source">
						<span>发布时间：2011-05-12</span>
						<span>发布人：-  </span>
					</div>
					<p><p>天津波音复合材料总经理盖瑞&middot;贝克日前接受记者采访时说，波音每年在华采购航空硬件和服务超过2亿美元，预计到2015年，波音在华采购额将至少翻一番。</p>
<p>&ldquo;中国是一个巨大的市场，波音与中国在航空领域合作持续增长。除零部件生产，双方在航空系统训练、交通控制系统、飞机制造、航空生物燃料等领域，都有广阔的合作空间和机遇。&rdquo;贝克说。</p>
<p>天津波音复合材料是美国波音公司和中国航空工业集团公司共同投资的合资企业，其位于滨海新区原厂房旁的新厂房于4月18日竣工，由波音注资2100万美元兴建。</p>
<p>天津波音复合材料为所有波音在产机型生产零部件，包括737、747-8、767、777和787，客户涵盖波音、美国古特里奇公司、奥地利菲舍尔公司、英国的BAE公司、韩国的KAI公司、中国的上海飞机制造厂和西安飞机制造厂等。</p>
<p>&ldquo;目前公司月产能5000件，新厂房将把整体生产能力提升60%，预计到2013年达产时产能实现翻倍增长，并会随航空产业的增长需求而调整产能计划。&rdquo;盖瑞&middot;贝克说。</p>
<p>据了解，波音与中国航空业已合作将近40年，是&ldquo;中国制造&rdquo;飞机零部件的最大客户，每年在中国航空硬件及服务上的直接投入超过2亿美元。</p>
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