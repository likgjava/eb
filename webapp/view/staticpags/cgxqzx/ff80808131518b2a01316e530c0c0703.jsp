<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>我国太阳能发电成本不断下降- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01316e530c0c0703.jsp" title="我国太阳能发电成本不断下降" class="cmsHref_self">我国太阳能发电成本不断下降</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>我国太阳能发电成本不断下降</h1>
					<div class="source">
						<span>发布时间：2011-07-28</span>
						<span>发布人：-科技日报  </span>
					</div>
					<p><P>中国工程院院士、清华大学教授金涌7月22日在接受本报记者采访时说，随着切片、储能等新技术的发展，风能、太阳能等可再生能源的发电成本不断下降，发展前景看好。</P>
<P>金涌在“第二届中国城市科学发展高层论坛”上说，我国风能发展很快，2010年风电装机容量增加了18.9吉瓦，全球排名第一;累计装机44.73吉瓦，也是全球排名第一。如此高速发展缘于“风电技术过关，成本也接近过关”。</P>
<P>金涌分析说，建设一个火电厂投资约为3500万元到4000万元人民币，而风能发电的建设费比火电高一倍。随着技术发展，风电成本已开始下降，前景乐观。</P>
<P>光电与风电的情况类似。金涌说，我国光电产能每年翻一番，成本在下降。原来1千瓦光电发电成本约为4万元人民币，目前已降到2万元以下。光电主要原料是硅片，把硅片切得越薄，价钱就降得越多。当前能做到的最薄的硅片是微米膜。随着薄膜电池的发展，光电发电成本会越来越低，发展前景很好。</P>
<P>“风电和光电的最大缺点就是说来就来，说没有就没有了。云把太阳遮住，光电发电量减少10倍;没有风，就一点电都发不出来。两者发电波动很大，对电网的冲击太大，因此，最理想的使用方法是分散使用。”金涌说，“根据可再生能源的特性，储能技术变得非常重要，把电能储藏起来稳定地送到需要的地方去。目前，清华大学正在开发液流电池，也就是在电池里面放入钒溶液，高温下可储存大量电能，该技术还在研发阶段”。</P>
<P>“空气储能也是一种储能技术。即做一个很大的容器，有电时把空气压缩到大容器里。美国人认为可以把地下大岩洞封闭起来压缩空气。电能有富余，储存进去，没电时就放出，推动发电机发电。包括液流电池、空气储能在内的各种储能技术都在迅速发展中。”金涌说。</P>
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