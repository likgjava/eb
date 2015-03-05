<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>零部件供应商罢工暴露韩汽车制造商弱点- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813026ae99013029cf32ee00a2.jsp" title="零部件供应商罢工暴露韩汽车制造商弱点" class="cmsHref_self">零部件供应商罢工暴露韩汽车制造商弱点</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>零部件供应商罢工暴露韩汽车制造商弱点</h1>
					<div class="source">
						<span>发布时间：2011-05-26</span>
						<span>发布人：-  </span>
					</div>
					<p><p>韩国的汽车产业就产量而言排在世界第五位。如今，该国的汽车龙头企业认识到它们所能依靠的零部件供应商实在是太少了，这种&ldquo;刚好够用&rdquo;的库存零部件供应体系已经使它们捉襟见肘。这种突然的焦虑发作是在上周三流星(音)企业有限公司爆发非法罢工活动后产生的，罢工一直持续到本周二韩国警方介入才结束。流星企业的罢工活动开始后，韩国各汽车制造商的生产线便纷纷停产。据业内人士透露，大约有180家公司肩负着韩国5家汽车制造商50%以上的零部件供应工作。这5家汽车制造商分别是现代汽车、起亚汽车、通用汽车韩国公司、雷诺三星汽车和双龙汽车。据韩国汽车行业合作协会称，约有10家中小型企业业内专家称，过高的供货比例表明本土汽车业注重以较低的成本和较高的质量购买大量的本土零部件。但国内采购在帮助韩国汽车制造商免受全球供应链影响的同时，也使它们在这些供应商停产时面临本土的影响。</p>
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