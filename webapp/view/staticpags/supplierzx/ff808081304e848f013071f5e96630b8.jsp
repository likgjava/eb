<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>思科推基于至强E7的下一代UCS服务器- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f013071f5e96630b8.jsp" title="思科推基于至强E7的下一代UCS服务器" class="cmsHref_self">思科推基于至强E7的下一代UCS服务器</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>思科推基于至强E7的下一代UCS服务器</h1>
					<div class="source">
						<span>发布时间：2011-06-09</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据悉，近日思科开始全面打响产品升级的战役，新的Nexus融合交换机已经开始出货。同时，一直希望在服务器领域有所建树的思科，宣布计划投产采用Intel下一代&ldquo;Westmere-EX&rdquo;至强E7高端处理的服务器产品&mdash;&mdash;UCS服务器。</p>
<p>英特尔预计不久将出货至强E7处理器。两周后英特尔将在北京召开IDF大会，相信届时将公布更多关于至强E7的信息。</p>
<p>思科并未透露很多关于至强E7服务器的细节。</p>
<p>但是从国外网站相关获悉，UCS B230和B440刀片服务器以及C460机架服务器将升级采用&ldquo;Westmere-EX&rdquo;芯片，该芯片与现有的&ldquo;Nehalem-EX&rdquo;至强7500处理器是插槽兼容的。</p>
<p>另外，思科还会推出一款新的机架服务器，它混合采用了至强E7处理器和思科自己的内存扩展ASIC以提供更多的DDR3主内存。</p>
<p>Cisco UCS C260 M2服务器这款名为UCS C260 M2的服务器将采用双路至强E7，提供64个内存插槽，使得主内存最大可以达到1TB，同时采用2U机箱设计结构，支持用户采用2.5英寸硬盘或者固态盘。C260 M2中可以接入16个硬盘，采用6个PCIe 2.0插槽、2个万兆端口和4个千兆端口。</p>
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