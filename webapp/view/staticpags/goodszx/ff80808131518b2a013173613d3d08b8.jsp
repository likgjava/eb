<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>钛白市场未来三年需求或再增100万吨- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a013173613d3d08b8.jsp" title="钛白市场未来三年需求或再增100万吨" class="cmsHref_self">钛白市场未来三年需求或再增100万吨</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>钛白市场未来三年需求或再增100万吨</h1>
					<div class="source">
						<span>发布时间：2011-07-29</span>
						<span>发布人：-中国建材网  </span>
					</div>
					<p><P>钛白粉属于钒钛产业钛化工市场重要组成部分，也是目前民营资本进入最多的一个领域。据攀钢研究院钛研所所长杨仰军介绍，钛白粉约占目前钛原料市场的90%左右，涉及到涂料、造纸、油墨等多个行业，在化工上的应用与人们的生活息息相关。</P>
<P>杨仰军还分析，现阶段，中低端领域的硫酸钛白市场竞争非常激烈，但高端的氯化法钛白却在不断进口。</P>
<P>钛白市场究竟有多大?云南大互通工贸有限公司常务副总经理郑照义告诉记者，我国近年来的需求已成倍增长，五年前，市场需求总量大概在30万吨/年，现在，这个数字已达到200多万吨，而且还在上升，预计未来三年将会再增加100万吨左右。</P>
<P>企业竞争方面，攀钢集团总经理余自苏透露，他们将在钛领域打造全产业链。攀钢发展的重点是高端产品，计划建造一条年产10万吨的氯化钛白生产线;金属钛方向上，将与钢铁的技术优势进行联合，进军高端钛材产品。而在产业链的中低端领域，将依靠收购与联合，与已进入该领域民营企业进行资源整合。</P>
<P>与攀钢等大型企业相比，一些中小型企业更专注市场的细分，加快对特种钛白产品的市场占有。记者在四川华铁钒钛科技公司了解，他们今年开发的催化钛白(学名脱销二氧化钛)产品，就属于高端的特种钛白，主要应用于火电站脱销装置中的催化剂原料。</P>
<P>华铁公司总经理胡新平分析，从市场需求来看，我国在催化钛白领域的需求量目前约在2万吨左右，但随着国家的环保标准提高，未来几年将推动所有火电站配套脱销装置，将使催化钛白市场需求量十二五期间大大增加。而要保持企业的持续发展，他们还将在电子钛白、超纯钛白等领域开发新产品。</P>
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