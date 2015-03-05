<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>三星LED在韩反诉西门子侵犯LED专利- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308bbd23c112e7.jsp" title="三星LED在韩反诉西门子侵犯LED专利" class="cmsHref_self">三星LED在韩反诉西门子侵犯LED专利</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>三星LED在韩反诉西门子侵犯LED专利</h1>
					<div class="source">
						<span>发布时间：2011-06-14</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据国外媒体报道，三星旗下三星LED(Samsung LED)日前在韩国针对西门子旗下欧司朗(Osram)公司就发光二极管(light-emitting diode)技术提起专利侵权诉讼。</p>
<p>三星LED在公司网站上表示，公司起诉了欧司朗及其在韩国的两家代理销售公司。三星LED指出，诉讼涉及的8项专利其中包括LED及照明所用的封装技术。欧司朗发言人并未立即对此发表评论。</p>
<p>三星LED 6月10日在首尔中央地区法院(Seoul Central District Court)提起该诉讼。欧司朗此前在美国和德国就发光二极管技术对三星提起专利诉讼。三星在6月8日曾表示，公司并未侵犯欧司朗LED照明专利。欧司朗还针对LG电子就其在电视及照明产品中使用的LED技术提起专利诉讼。</p>
<p>三星LED要求欧司朗停止未经授权而使用其知识产权并赔款。三星LED表示，公司计划将尽可能快的在美国及其他海外市场针对欧司朗提起更多诉讼。三星LED在美国拥有约700项专利及专利申请，在韩国拥有2000项专利及专利申请。</p>
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