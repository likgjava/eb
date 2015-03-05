<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>商务需求是主流 中高级车用户呈年轻化趋势- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b2836500bc.jsp" title="商务需求是主流 中高级车用户呈年轻化趋势" class="cmsHref_self">商务需求是主流 中高级车用户呈年轻化趋势</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>商务需求是主流 中高级车用户呈年轻化趋势</h1>
					<div class="source">
						<span>发布时间：2011-06-01</span>
						<span>发布人：-  </span>
					</div>
					<p><p>特约战略合作咨询机构麦威资讯 随着国内经济快速增长和高收入群的持续增加，中高级车在汽车厂家中的地位越来越重要。同时，由于中低端车市场竞争激烈，趋薄的利润更加凸显出中高端车型的战略性作用。</p>
<p>近几年来，国内B级车市场发展日渐成熟，女性用户逐渐增加，年龄结构呈现年轻化趋势，20-30岁消费群体发展迅速;而以稳重与成功形象著称的B级车群体的选车兴趣点也慢慢转向时尚和运动;大批未婚并且没有子女的消费者加入B级车车主行列;车主受教育程度显著增加;政府机关和企业的中层管理者开始成为消费主导。而根据调查发现，B级车群体家庭年收入仅略有提高，并无显著提升。</p>
<p>B级车用户特征调研数据见图1、2</p>
<p>通过对B级车主的购车原因进行面访后分析，B级车作为&ldquo;地位象征&rdquo;以及由此&ldquo;获得信任&rdquo;的面子消费观念明显下降。更多人购买B级车是因为&ldquo;价格便宜了&rdquo;，以及&ldquo;休闲需要&rdquo;，这说明B级车的整体消费观念变得更加成熟和理性。目前，&ldquo;业务范围扩大&rdquo;、&ldquo;接送家人&rdquo;、&ldquo;休闲需要&rdquo;是B级车用户购车的要原主因，商务气质仍为主流。</p>
<p>B级车用户购车原因调研数据见图3、4</p>
<p>相比以往B级车用户对外观和品牌的关注，现在车主对质量和动力操控的关注度明显上升。目前，位列B级车用户购车主要关注前四位的依次是：安全、价格、外观、质量，说明B级车消费群体正在对汽车本身进行日渐深入的了解。</p>
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