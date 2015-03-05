<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>味千汤料原料供应商浮出水面 来自日方伙伴- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013173736a7308c4.jsp" title="味千汤料原料供应商浮出水面 来自日方伙伴" class="cmsHref_self">味千汤料原料供应商浮出水面 来自日方伙伴</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>味千汤料原料供应商浮出水面 来自日方伙伴</h1>
					<div class="source">
						<span>发布时间：2011-07-29</span>
						<span>发布人：-新京报  </span>
					</div>
					<p><P>身陷“骨汤门”的味千拉面骨汤原料供应商渐渐浮出水面。记者昨日从味千招股书及财报中了解到，味千汤料主要来源于一家名叫重光产业株式会社的日本公司，味千(中国)控股去年将营业额的0.85%交给这家公司作为特许费和技术使用费等。</P>
<P>此前味千拉面宣称汤底是经过20多个小时熬煮制成，被指虚假宣传。味千先是承认宣传扩大，再承认骨汤非现场熬制而是兑制，不过，味千(中国)备受争议的汤料来源谜底依然未解。对于关注度很高的骨汤原料到底由哪家企业生产的问题，味千方面一直回避。</P>
<P>对此，味千方面表示，味千拉面的汤料一直是由日本总部提供。此前，为了扩大规模日本总部在中国也开设了工厂，而味千拉面中国所有店面的汤料正是来自这间工厂。</P>
<P>昨日记者多次致电味千公司投资者关系部负责人浩雄，其同事表示他一直在开会，无法接受采访。味千控股的招股说明书中显示，日资企业“重光企业为此独特汤底及调味料的唯一供应商”。</P>
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