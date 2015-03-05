<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>酒业评选首页</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/smallscale/vote/vino/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/view/smallscale/vote/vino/vino_index.js"></script>
</head>

<body>
<div id="banner"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/banner.jpg" /></div>
<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />

<div id="nav">
<div class="nav_111"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_01.jpg" /></div>
<div class="nav_111">
	<a href="javascript:void(0);" onclick="vinoIndex.toVinoIndex()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_02.jpg" border="0" /></a>
</div>
<div class="nav_111">
	<a href="javascript:void(0);" onclick="vinoIndex.toVinoHdjs()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_03.jpg" border="0" /></a>
</div>
<div class="nav_111">
	<a href="javascript:void(0);" onclick="vinoIndex.toVinoHdlc()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_04.jpg" border="0" /></a>
</div>
<div class="nav_111">
	<a href="javascript:void(0);" onclick="vinoIndex.toVinoJxsz()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_05.jpg" border="0" /></a>
</div>
<div class="nav_111">
	<a href="javascript:void(0);" onclick="vinoIndex.toVinoPszj()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_06.jpg" border="0" /></a>
</div>
<div class="nav_111">
	<a href="javascript:void(0);" onclick="vinoIndex.toVinoGktp()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_07.jpg" border="0" /></a>
</div>
<div class="nav_111">
	<a href="javascript:void(0);" onclick="vinoIndex.toVinoMtlm()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_08.jpg" border="0" /></a>
</div>
<div class="nav_111">
	<a href="javascript:void(0);" onclick="vinoIndex.toVinoBmfs()"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_09.jpg" border="0" /></a>
</div>
<div class="nav_111"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/nav_img_10.jpg" /></div>
</div>

<div id="conBody">
	<%@ include file="/view/smallscale/vote/vino/vino_default.jsp" %>
</div>

<div id="bottom">关于我们 | 网站导航 | 会员服务 | 广告服务 | 网上调查 联系我们 | 版权所有 <br />
Copyright&copy;2007中国名企排行网 Rrights Reserved京ICP证070104号 北京市公安局海淀分局备案编号
11010818900 <br />
本站网络实名/通用网址：“中国名企排行网”京ICP备09089782号
</div>
<div id="footer">
	<%@ include file="/view/srplatform/portal/include/foot.jsp" %>
</div>

</body>
</html>