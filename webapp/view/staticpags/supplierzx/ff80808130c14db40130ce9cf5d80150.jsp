<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>英特尔或2013年推Haswell芯片 超级本或诞生- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130c14db40130ce9cf5d80150.jsp" title="英特尔或2013年推Haswell芯片 超级本或诞生" class="cmsHref_self">英特尔或2013年推Haswell芯片 超级本或诞生</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>英特尔或2013年推Haswell芯片 超级本或诞生</h1>
					<div class="source">
						<span>发布时间：2011-06-27</span>
						<span>发布人：-SEMI  </span>
					</div>
					<p><P>据国外媒体报道，据英特尔在其软件博客上发布的一份技术文档暗示，它可能会在2013年推出其“Haswell”芯片。</P>
<P>英特尔营销主管汤姆柯罗伊(TomKilroy)上个月曾说过，移动版Haswell将会是英特尔专为主流笔记本电脑市场推出的第一款片上系统(System-on-a-chip)。</P>
<P>片上系统是专门针对智能手机和平板电脑应用的一项设计。所有系统的核心处理硅都被整合在一个芯片中，以满足移动设备的空间限制要求。高通的Snapdragon处理器就是当今智能手机和平板电脑所用的主流片上系统的典型例子，WindowsPhone7手机和惠普的TouchPad平板电脑都采用了Snapdragon处理器。苹果iPad中所用的A5芯片也属于片上系统。</P>
<P>据英特尔称，预计到2013年的时候，主流笔记本电脑市场上将会出现一大批超级本(Ultrabook)。这些超级本不但超薄超轻，而且设计多样。目前的超级本的最好例子就是华硕即将推出的AsusUX21和苹果的MacBookAir。</P>
<P>柯罗伊称，到2013年的时候，基于Haswell的超级本将会更加便宜，而且那些超级本无需再配备其他公司如英伟达的显卡芯片。</P>
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