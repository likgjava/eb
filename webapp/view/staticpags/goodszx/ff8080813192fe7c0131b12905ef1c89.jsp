<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>花茶发力 川内茉莉花供应趋紧- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131b12905ef1c89.jsp" title="花茶发力 川内茉莉花供应趋紧" class="cmsHref_self">花茶发力 川内茉莉花供应趋紧</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>花茶发力 川内茉莉花供应趋紧</h1>
					<div class="source">
						<span>发布时间：2011-08-10</span>
						<span>发布人：-  </span>
					</div>
					<p><P>随着近年来市场销量、价格上涨，省内花茶原料供应渐渐趋紧。省园艺总站站长段新友介绍，目前川内茉莉花茶年消耗茉莉花1.5万吨左右，因为花源紧张，目前我省有很多茶厂都到广西、云南加工。</P>
<P>看中高端花茶发展潜力，我省品牌茶叶企业开始发力：花秋茶业削弱绿茶份额，主推高端花茶，市场“试水”效果颇佳;竹叶青茶业2009年购买了新津的“碧潭飘雪”品牌，重新打造、经营后的第二年，产值就逾千万元。</P>
<P>不断提档升级的茉莉花茶，带动川内原料茉莉花种植面积增加：青神县青竹茶厂去年发展起来600多亩茉莉花种植基地;全省最大的茉莉花种植县犍为县，其茉莉花种植面积达3万多亩……目前省内茉莉花种植面积已有5万亩左右。</P>
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