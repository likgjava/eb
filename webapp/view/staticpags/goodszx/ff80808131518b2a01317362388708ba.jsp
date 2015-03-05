<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>数码相机市场需求趋缓- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01317362388708ba.jsp" title="数码相机市场需求趋缓" class="cmsHref_self">数码相机市场需求趋缓</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>数码相机市场需求趋缓</h1>
					<div class="source">
						<span>发布时间：2011-07-29</span>
						<span>发布人：-北京娱乐信报  </span>
					</div>
					<p><P>中国电子商会消费电子产品调查办公室昨日发布的《2011年中国数码相机市场消费需求状况调研报告》显示，今年中国数码相机市场需求量增长放缓，上半年销售量约为500万台，较去年同期下降10.7%，预计全年销售量与去年持平。</P>
<P>中国电子商会副秘书长陆刃波表示，上半年数码相机消费市场延续激烈的竞争态势，上市的数码相机价格集中在1000-2000元，其中1000-3000元的中高端数码相机占据近75%的市场份额，成为市场的主流消费品。调查显示，上半年，消费者对佳能、索尼和尼康品牌的预期购买率位列前三名，分别为37%、16%、13%，富士与三星并列第四，松下、奥林巴斯和柯达的关注度略有下降。</P>
<P>报告强调，从发展趋势看，无反光镜、可更换镜头的数码相机逐渐占有一定的市场份额，需求量迅速上升。同时，消费者对数码相机的功能要求更加具体化，促使了数码相机市场的细分。</P>
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