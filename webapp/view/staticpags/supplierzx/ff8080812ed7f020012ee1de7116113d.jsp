<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>联相光电开始向印度客户供货- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812ed7f020012ee1de7116113d.jsp" title="联相光电开始向印度客户供货" class="cmsHref_self">联相光电开始向印度客户供货</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>联相光电开始向印度客户供货</h1>
					<div class="source">
						<span>发布时间：2011-03-23</span>
						<span>发布人：-北极星电力网新闻中心  </span>
					</div>
					<p><p>近日，台湾著名薄膜太阳能电池生产商联相光电获得了印度客户30兆瓦订单后，与近期开始供货，这些产品主要用于印度的太阳能电厂。于2010年6月量产的140瓦叠层硅基薄膜组件拥有9.1%的转换效率，因此在薄膜电池市场上获得了认可。</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>联相光电还宣布其多结薄膜太阳能组件通过了挪威船级社&mdash;&mdash;全球领先的专业风险管理服务机构&mdash;&mdash;的产品碳足迹认证，此外还通过了pas&nbsp;2050及国际标准组织/14067-1标准查证的产品碳足迹认证。此前该公司还在2009年7月加入PV&nbsp;Cycle组织。</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>联相光电一直注重自身工厂的建设，选择了由IEC首批认可的有害物质过程管理系统验证机构及清洁发展机制计划的最大认证组织挪威船级社进行的第三者独立查证。</p>
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