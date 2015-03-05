<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>世界黄金协会：2011下半年黄金需求仍然强劲- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a95601308676b1bc0361.jsp" title="世界黄金协会：2011下半年黄金需求仍然强劲" class="cmsHref_self">世界黄金协会：2011下半年黄金需求仍然强劲</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>世界黄金协会：2011下半年黄金需求仍然强劲</h1>
					<div class="source">
						<span>发布时间：2011-06-13</span>
						<span>发布人：-中国新闻网  </span>
					</div>
					<p><p>世界黄金协会(World Gold Council)表示，央行第一季度黄金需求激增，而且此担忧因素将支撑黄金价格。</p>
<p>世界黄金协会常务董事Marcus Grubb接受采访时表示，美国经济和美元的不确定性、欧洲主权债务的持续担忧、全球通胀压力以及中东和北非持续紧张的局势将继续推动投资需求。</p>
<p>Marcus Grubb表示，中国和印度的黄金需求处于健康状态，并称央行继续增加黄金储备。他称，央行第一季度买入129吨黄金，超出2010年前三个季度净买入总量。</p>
<p>他补充道，黄金在近期商品市场波动期间的适应能力证明了全球黄金市场强劲以及黄金拥有独特的需求动力的原因。全球高水平的投资需求，中国和印度的强劲需求，技术行业持续强劲以及央行买入证明了黄金的多方面需求。</p>
<p>Grubb还表示，预计2011年剩余时间黄金需求仍然强劲。</p>
<p>世界黄金协会最新黄金需求趋势报告显示，第一季度黄金需求总计981.3公吨，而2010年同期黄金需求为881.0吨。该协会称，按美元计算，第一季度黄金需求年比增长40%，从314亿美元增至437亿美元。</p>
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