<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>IHC Merwede扩大绞车供应范围- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812f9cf3c5012fb3927d1e0536.jsp" title="IHC Merwede扩大绞车供应范围" class="cmsHref_self">IHC Merwede扩大绞车供应范围</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>IHC Merwede扩大绞车供应范围</h1>
					<div class="source">
						<span>发布时间：2011-05-03</span>
						<span>发布人：-  </span>
					</div>
					<p><p><font size="3">&nbsp;</font></p>
<div align="center"><font size="3"><img src="http://www.eworldship.com/news/UploadFiles2/2011538496396.jpg" alt="" /></font></div>
<div align="center">&nbsp;</div>
<div align="center"><img src="http://www.eworldship.com/news/UploadFiles2/20115384917104.jpg" alt="" /></div>
<div id="div-2">2011年3月24日，荷兰IHC Merwede新成立下属IHC Hytop Winches公司，作为旗下重要的生产车间，将能够为海工及挖泥市场建造复杂的绞车系统，扩大了供应的绞车系列范围。</div>
<div id="div-2">据悉，在过去的10年里，客户要求量身定制的绞车日益增长，海工及挖泥市场所需绞车的需求也日益增长。为此IHC Merwede决定新建造一家车间，占地1500平方米。在开业仪式上，IHC Hytop Winches展示了Riser Installation Winch，该绞车具有800米长的线缆、500吨拉力能力，这是全球最大的绞车类型。</div>
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