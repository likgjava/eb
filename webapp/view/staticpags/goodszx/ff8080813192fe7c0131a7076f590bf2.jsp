<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>日本纺织进口商开拓秘鲁市场 激起巨大期望- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131a7076f590bf2.jsp" title="日本纺织进口商开拓秘鲁市场 激起巨大期望" class="cmsHref_self">日本纺织进口商开拓秘鲁市场 激起巨大期望</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>日本纺织进口商开拓秘鲁市场 激起巨大期望</h1>
					<div class="source">
						<span>发布时间：2011-08-08</span>
						<span>发布人：-  </span>
					</div>
					<p><P>日本纺织进口商协会(JTIA)的代表访问秘鲁并参观了Creditex和Nettalco的设施，这是两家垂直整合的纺织公司，位于首都利马。</P>
<P>据秘鲁出口商协会(ADEX)说，日本代表的访问激起秘鲁纺织品和服装生产厂家的巨大期望，尤其是3月11地震后日本经济快速复苏，以及两国签署的自由贸易协定(FTA)刚刚生效。</P>
<P>目前日本85%的纺织品和服装进口来自于中国，剩余的15%进口来自于其他南亚国家。为了探索从其他国家进口的可能性，日本纺织进口商协会(JTIA)每年选择访问一个地区。</P>
<P>去年，日本企业家访问了东欧，今年他们正在访问中美洲和南美洲。秘鲁已被列入被访问国家的名单，日本代表团正在考察棉花、羊驼毛的质量，以及纺织品和服装的后整理。</P>
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