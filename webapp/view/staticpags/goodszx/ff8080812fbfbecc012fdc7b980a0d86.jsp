<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>5月份全球棕榈油需求料持续上升- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fdc7b980a0d86.jsp" title="5月份全球棕榈油需求料持续上升" class="cmsHref_self">5月份全球棕榈油需求料持续上升</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>5月份全球棕榈油需求料持续上升</h1>
					<div class="source">
						<span>发布时间：2011-05-11</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据汉堡5月10日消息，总部位于德国汉堡行业刊物《油世界》周二表示，5月时全球棕榈油需求可能持续增加，因其和包括豆油在内的植物油具有竞争优势。</p>
<p>《油世界》称，&ldquo;全球棕榈油进口需求从4月开始回升，且在5月继续扩张。这将在马来西亚和印尼出口增加中得到体现。&rdquo;</p>
<p>《油世界》数据显示，在出口大国印尼，5月船期的棕榈油价格较巴西和阿根廷的豆油出口价每吨便宜30-50美元。</p>
<p>&ldquo;棕榈油较豆油等其他植物油的竞争力增强。我们预计中国、印度、中东、欧洲和其他国家将加大采购，因消费者开始转向更具价格吸引力的棕榈油上。&rdquo;</p>
<p>印尼和马来西亚出口数量是敲定全球棕榈油价格的重要因素。但船运机构周二表示，5月迄今为止马来西亚棕榈油出口疲弱。</p>
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