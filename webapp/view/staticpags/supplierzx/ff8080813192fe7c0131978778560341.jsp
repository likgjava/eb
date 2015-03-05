<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>戴尔服务器为大型企业建高效云平台- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131978778560341.jsp" title="戴尔服务器为大型企业建高效云平台" class="cmsHref_self">戴尔服务器为大型企业建高效云平台</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>戴尔服务器为大型企业建高效云平台</h1>
					<div class="source">
						<span>发布时间：2011-08-05</span>
						<span>发布人：-  </span>
					</div>
					<p><P>随着云计算在全球各地不断的开花结果，服务器市场也日渐繁华了起来。智能计算给我们的生活带来了诸多的便利，从手机通讯、网络购物、视频点播、各种线上交互及游戏平台，再到民航、地铁售票系统等等，无一不依靠于服务器的支持。</P>
<CENTER><IMG border=1 alt="" src="http://tech.ccidnet.com/col/attachment/2011/8/2178993.jpg" width=463 height=316></CENTER>
<P>根据Gartner的研究，2010年全球服务器销售市场有两位数增长，已从经济低迷中逐渐恢复。服务器销售比2009年增长了13.2%。而在服务器的销售中，大型企业依然是采购的主力军。今年我国率先步入云计算的企业是电信和互联网，其强大的数据中心就是支撑云计算的重要基础设施。</P>
<P>戴尔关注于大型企业市场</P>
<P>对于大型企业来说，服务器的需求也是多种多样的。高性能的HPC以及刀片服务器适合于石油勘探、天气预报以及制造行业;而传统x86服务器适合于不同应用场合，是大规模集群不可或缺的组成部分。</P>
<P>目前在大型企业应用领域，戴尔的服务器与存储产品占据着举足轻重的地位。每天，戴尔向180多个国家的用户提供11万台以上的产品，相当于每秒出货量1台以上。戴尔和其技术合作伙伴及客户共同努力打造开放、性能出色和高性价比的解决方案，已经广泛应用于政府、教育、金融、电信、医疗、制造等行业和领域。</P>
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