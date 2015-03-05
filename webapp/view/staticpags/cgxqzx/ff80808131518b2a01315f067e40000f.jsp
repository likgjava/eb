<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>上半年政府采购规模83亿创新高 自主创新产品获政府首购- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01315f067e40000f.jsp" title="上半年政府采购规模83亿创新高 自主创新产品获政府首购" class="cmsHref_self">上半年政府采购规模83亿创新高 自主创新产品获政府首购</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>上半年政府采购规模83亿创新高 自主创新产品获政府首购</h1>
					<div class="source">
						<span>发布时间：2011-07-25</span>
						<span>发布人：-无锡日报　  </span>
					</div>
					<p><P>上半年我市政府采购规模达83亿余元，为历年同期最高!这是7月24日从市财政局获悉的。“政府采购积极支持产业结构升级，上半年采购合同的95.7%授予了中小企业，对自主创新产品也实施了政府首购政策，今后将进一步加大扶持力度。”有关人士说。</P>
<P>近几年来，我市政府采购范围不断扩大，从学校用品、医疗设备到市政环卫绿化养护、控源截污工程、老新村整治等一批涉及民生的重大项目，都纳入了政府采购范畴。政府采购品目已从1999年的10几个发展到现在的150多个。</P>
<P>扶持中小企业，政府采购当好“助推器”。我市于去年起，在采购过程中取消了对竞标中小企业的注册资金、销售规模等的门槛限制，更关注其产品的质量、价格的合理性。对于自主创新产品，政府采购也予以大力扶持和激励。如，对拥有自主知识产权的“北大众志”网络一体计算机等自主创新产品实行政府首购，仅今年上半年就首购了850台，产品价廉物美，满足了中小学电子阅览室建设之需。</P>
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