<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>西门子百万美元签约爱达荷太阳能项目- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b7316b6245c.jsp" title="西门子百万美元签约爱达荷太阳能项目" class="cmsHref_self">西门子百万美元签约爱达荷太阳能项目</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>西门子百万美元签约爱达荷太阳能项目</h1>
					<div class="source">
						<span>发布时间：2011-07-15</span>
						<span>发布人：-英大网  </span>
					</div>
					<p><P>在旧金山Intersolar北美展会上，西门子公司宣布与互联太阳能发展责任有限公司签订了一笔百万美元的协议，向爱达荷州20兆瓦的光伏项目提供技术支持。该项目建成后将成为爱达荷州首个接入国家电网的商用太阳能发电工程。</P>
<P>西门子团队展示了对我们项目目标的正确理解，该团队已经投入大量的时间和技术将太阳能带个爱达荷州的电力用户，”互联太阳能发展创立者BillPiske说。</P>
<P>据西门子方面消息，他们的太阳能方案能够提供最低的每度电力成本，同时其专业变流器的转换率预计将在98%以上。该爱达荷州太阳能项目也将使用西门子的PV-WinCCSCADA软件。</P>
<P>西门子太阳能垂直一体化管理总监RichardMyers补充道：“互联太阳能选择我们作为合作伙伴让我们感到非常自豪。另外，我们为他们提供20年的客户服务协议，用我们的服务和支持保证整个项目开发和运行的时间和效率。”</P>
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