<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>河北拟建高密度环京交通圈- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ee243a4012ee5fc20a91293.jsp" title="河北拟建高密度环京交通圈" class="cmsHref_self">河北拟建高密度环京交通圈</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>河北拟建高密度环京交通圈</h1>
					<div class="source">
						<span>发布时间：2011-03-24</span>
						<span>发布人：-  </span>
					</div>
					<p><p>3月21日公布的河北省&ldquo;十二五&rdquo;规划纲要显示：全省高速公路通车里程目前已达4307公里，跃居全国第二；&ldquo;十二五&rdquo;将实现县县通高速公路，所有设区市通高速铁路，形成以石家庄为中心的&ldquo;两小时交通圈&rdquo;、环北京的&ldquo;一小时交通圈&rdquo;。</p>
<p>加之机场密集建成和港口升级，河北&ldquo;海陆空通衢&rdquo;将现。截至去年，大广高速河北段全线贯通后，河北高速公路密度达2.30公里/百平方公里，超过日本、法国等发达国家水平。同时，黄骅综合大港通航，打开冀中南经济发展的新通道、出海口。</p>
<p>河北省交通运输厅有关负责人新近表示，该省计划投资3252亿元人民币用于交通体系建设，到&ldquo;十二五&rdquo;末，全省高速公路通车里程将达6762公里，密度达3.6公里/百平方公里，可超过世界上高速公路密度最大的德国。该厅公路管理局局长王江帅也表示，今年全省公路建设投资将达763亿元，到2015年争取实现公路总里程突破16万公里。</p>
<p>新的五年，该省提出，围绕京津冀交通一体化目标，着力提高公路、铁路、港口、民航保障能力，形成&ldquo;快捷、高效、安全的现代综合交通网络&rdquo;。五年后，铁路通车里程将达8000公里，其中高铁1500公里。秦皇岛港、唐山港、黄骅港三个综合大港吞吐能力达8亿吨。民航机场达7个，航空客货运输能力分别达2000万人次和20万吨以上，其中石家庄正定国际机场以打造&ldquo;第二首都机场&rdquo;为目标，&ldquo;十二五&rdquo;将跨入千万级机场行列。</p>
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