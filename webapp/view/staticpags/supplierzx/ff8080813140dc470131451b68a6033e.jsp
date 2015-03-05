<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>苹果或联姻中移动 开拓中国市场- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc470131451b68a6033e.jsp" title="苹果或联姻中移动 开拓中国市场" class="cmsHref_self">苹果或联姻中移动 开拓中国市场</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>苹果或联姻中移动 开拓中国市场</h1>
					<div class="source">
						<span>发布时间：2011-07-20</span>
						<span>发布人：-国际金融报  </span>
					</div>
					<p><P>7月18日，据知情人士透露，苹果与中国移动的协商已接近尾声。这或许意味着苹果将与中国移动联姻，从而进一步开拓中国市场。</P>
<P>尽管苹果全球销量的38%在美国市场，然而中国市场已成为其全球销售增长最快的地区。数据显示，截至3月26日，苹果在包括中国香港和中国台湾在内的大中华区的营收较前一年同期增长近四倍，达到近50亿美元。</P>
<P>据悉，苹果日常运营代理负责人兼COO蒂姆·库克(Tim Cook)上个月拜访过中国移动，中国移动也承认了两家公司在讨论iPhone的相关事宜。</P>
<P>作为苹果最受欢迎的产品之一，iPhone在中国是由中国联通于2009年开始销售的。不过相较于联通不足2亿的注册用户，作为世界最大的移动运营商，中国移动则有6亿的注册量。有分析师指出，苹果或将在接下来一年时间内向中国移动提供iPhone。不过，还得看苹果是否愿意支持中国的本土技术标准TD-SCDMA，但是也不排除苹果在中国移动推出4G网络，即TD-LTE之后进一步展开合作的可能。目前中国移动只在少数选定的城市内开展了4G网络的测试。</P>
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