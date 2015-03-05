<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>平板架构之争 采购平板电脑应用细说- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130bc5ca70130bf1e0c67002e.jsp" title="平板架构之争 采购平板电脑应用细说" class="cmsHref_self">平板架构之争 采购平板电脑应用细说</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>平板架构之争 采购平板电脑应用细说</h1>
					<div class="source">
						<span>发布时间：2011-06-24</span>
						<span>发布人：-eNet硅谷动力  </span>
					</div>
					<p><P>2011年平板电脑注定要成为数码爱好者全年关注的热点，对于爱好者在众多平板中如何选择适合自己的产品。</P>
<P>从目前平板的构架来看，主要有安卓架构及X86架构的区别。作为安卓产品线中，摩托罗拉“XOOM”是最新一代的产品，XOOM采用了全新的Honeycomb(蜂窝)系统，谷歌公司专为平板电脑进行了该系统的优化。并配有NVIDIA Tegra 2双核处理器，1G的RAM，性能一时无二。摩托罗拉这款平板电脑还拥有3.5毫米耳机接口、Mini HDMI接口，并提供了强大的无线连接功能，用户可在UMTS、CDMA、802.11b/g/n WiFi无线局域网、LTE多种网络制式中进行选择。但从应用角度来说，安卓构架产品能承载的应用更像是一部性能优越的玩具，更多的软件和企业应用程序是需要单独开发设计后才能应用的。苹果的IOS平台也碰到这种尴尬，苹果产品在中国是用早教婴幼儿来开触动行业发展的。简单的说，一部优秀的可触控平板电脑如果不能依托更多应用，那在娱乐方面才是消费者选择的重点。</P>
<P>X86架构是Intel的大本营，在国内windows系列产品多年的根基，决定了它在众多行业中有大量的软件用户采用了这个平台来日常办公。 索尼P45J/P是个代表产品，处理器采用 intel Z530，内存高达2G，SSD固态硬盘更为数据读写提供更大的安全。由于架构本身，让更多的软件不用单独开发，就可以直接移植。优点显而易见了。在X86架构中同样来自美国的OQO产品也值得一提，全球最小的电脑只有 4.8寸大小，笔者选择美国特种作战司令部应用做简单介绍，由于X86架构的优点，安装了采用生物识别技术的程序后，快速在嫌疑犯中检测出DNA进行比对，及时的把恐怖分子逮捕。</P>
<P>怎么能更方便小巧的应用是X86架构一直的方向，在2002年intel倡导UMPC的概念其实就是现在的平板，早在那个时候SONY、三星、OQO等厂商已经在这个领域里积淀。</P>
<P>其实不论什么平台架构，主要还要看消费者从哪个角度来看，需求是什么很重要，娱乐性能安卓、苹果ios是首选。领袖VIP行业应用，intel的平台看来是非它莫属了。</P>
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