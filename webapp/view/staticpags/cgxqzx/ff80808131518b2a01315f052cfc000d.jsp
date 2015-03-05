<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>PlayBook平板获美国政府采购产品认证- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01315f052cfc000d.jsp" title="PlayBook平板获美国政府采购产品认证" class="cmsHref_self">PlayBook平板获美国政府采购产品认证</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>PlayBook平板获美国政府采购产品认证</h1>
					<div class="source">
						<span>发布时间：2011-07-25</span>
						<span>发布人：-中关村在线  </span>
					</div>
					<p><P>根据国外媒体的报道，RIM公司黑莓PlayBook平板电脑正式获得了FIPS 140-2认证，这意味着PlayBook平板达到了美国政府采购产品标准。</P>
<P>RIM公司表示，PlayBook平板电脑获得了美国FIPS 140-2认证，这也是首款获得此认证的平板电脑。“此认证意味着PlayBook平板达到了美国联邦政府对于计算产品保护敏感信息方面的要求，政府可以放心采购。”RIM公司黑莓安全业务高级副总裁Scott Totzke表示。</P>
<P>FIPS 140-2全称为Federal Information Processing Standard 140-2(联邦信息审核标准140-2认证) ，该认证用于判定手机、服务器、路由器和防火墙等产品的安全性，此标准被美国联邦政府机构广泛应用，比如美国联邦调查局。</P>
<P>苹果的iPhone手机和iPad平板也申请了此认证。RIM公司的一款智能卡阅读器产品也获得了联邦信息审核标准140-2认证。</P>
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