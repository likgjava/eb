<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中电信再抛4000万终端采购- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130afdee10130b4c4b83203f2.jsp" title="中电信再抛4000万终端采购" class="cmsHref_self">中电信再抛4000万终端采购</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>中电信再抛4000万终端采购</h1>
					<div class="source">
						<span>发布时间：2011-06-22</span>
						<span>发布人：-21世纪经济报道  </span>
					</div>
					<p><P>6月20日，三大运营商公布最新用户数。今年5月，中国移动(微博)新增3G用户262万户，中国联通新增3G用户净增173.8万户，而中国电信(微博)3G用户增长183万户。目前我国3G用户保有量已突破7000万，渗透率达8.23%。</P>
<P>3G用户的迅速增长，得益于市场上3G终端的不断丰富。</P>
<P>“CDMA终端产业链最困难的时期已经过去。”中国电信集团总经理王晓初在近日召开的“天翼3G手机交易会”上表示。与此同时，中国电信联手中邮普泰、天音等国代商、手机厂商签署了近4000万台的3G终端采购协议。</P>
<P>2009年3G发牌之后，三大运营商纷纷结合各自优势资源开始了3G大比拼。而对手握CDMA网络的中国电信而言，发展3G最大的瓶颈就是终端匮乏。2009年6月，王晓初面对上百家CDMA手机厂商说，电信年初采购的100万台3G终端只拿到了27万台，“想组织一次有力的销售都不可能，想问在座各位是否汗颜”?</P>
<P>不过，当年手机厂商的不积极也情有可原。中国电信从原联通接手CDMA网时，用户总数才2800多万户。同期中移动的用户数已近5亿，差距过于明显。</P>
<P>来自中国电信方面的数据显示，经过两年的发展，电信天翼用户于今年3月底突破1亿户之后，4月已达10310万户。另据统计，2011年1-5月国内市场CDMA手机销量2261万台，较去年同期增长33%。</P>
<P>同时各厂商对CDMA手机的支持也在不断加强。王晓初透露，5月份在售CDMA手机款型总数超过800款，今年CDMA终端计划销售6000万部。</P>
<P>交易会巨大的订单吸引着各家手机厂商。HTC、摩托罗拉等厂商都带来了其高端CDMA手机产品，其中HTC与电信推出首款全球漫游手机。中国电信移动终端管理中心主任马道杰表示，天翼3G定制机原则上要求所有中档以上手机都要支持双模单待与全球漫游。</P>
<P>摩托罗拉移动技术公司大中华区总裁孟樸认为，在中国各种销售模式是百花齐放的，传统的零售渠道在不断增强，同时“运营商集采也在发挥越来越重要的作用”。</P>
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