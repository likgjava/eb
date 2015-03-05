<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>甲醇产能全国居首　专家看好河南煤化工业- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813204bef601321336ccae03e2.jsp" title="甲醇产能全国居首　专家看好河南煤化工业" class="cmsHref_self">甲醇产能全国居首　专家看好河南煤化工业</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>甲醇产能全国居首　专家看好河南煤化工业</h1>
					<div class="source">
						<span>发布时间：2011-08-29</span>
						<span>发布人：-  </span>
					</div>
					<p><P>煤不仅能制成柴油、汽油，还能制成天然气。8月27日上午，作为“2011年河南省承接产业和技术转移合作交流洽谈会”的重要活动之一，由省科技厅主办的“河南省煤化工技术创新高层论坛”在郑州国际会展中心举行。</P>
<P>专家们表示，河南是煤炭大省，近几年，河南省煤化工产业已初步形成了煤——煤气化——化工系列产品和煤——焦化——副产品深加工两条产业链。其中，以甲醇为原料的煤化工技术路线是重点。</P>
<P>“2010年，我省甲醇产能近480万吨，居全国第一位。”中石化洛阳石化工程公司梁龙虎教授说，石油、煤炭、天然气是今后很长一段时间的消费能源，如今油价上涨，石油资源日益短缺，我省煤化工发展有着广阔前景。</P>
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