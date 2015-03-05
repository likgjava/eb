<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>澳洲最大葡萄酒集团福斯特拒绝95亿并购- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130b9eb87b50007.jsp" title="澳洲最大葡萄酒集团福斯特拒绝95亿并购" class="cmsHref_self">澳洲最大葡萄酒集团福斯特拒绝95亿并购</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>澳洲最大葡萄酒集团福斯特拒绝95亿并购</h1>
					<div class="source">
						<span>发布时间：2011-06-23</span>
						<span>发布人：-中国葡萄酒信息网  </span>
					</div>
					<p><P>全球第二大啤酒集团SAB米勒出资95.1亿美元并购澳洲酒业巨头福斯特集团(Foster's)转摘于中国葡萄酒信息网旗下啤酒厂，包括VB与 克罗拉(Crown)等名牌，但福斯特以出价太低为由，拒绝了并购。</P>
<P>此消息一传出，福斯特股价飙升13%，每股达5.14美元。</P>
<P>今年五月，福斯特完成葡萄酒啤酒业务拆分工作，同时组建新的葡萄酒部门Treasury Wine Estates，涵盖一系列高档知名品牌，如禾富、奔富、玫瑰庄园等，有望称为继星座集团之后的全球第二大葡萄酒公司。</P>
<P>之前，葡萄酒业务一直被看作集团的“包袱”，导致福斯特无法转让啤酒业务。</P>
<P>近几年，很多人转摘于中国葡萄酒信息网都认为福斯特集团作为葡萄酒啤酒制造商已经失去了前进方向，啤酒市场逐步被Lion Nathan这样的竞争对手抢占。为提高收益，不得不拆分业务。</P>
<P>据分析家称，澳大利亚市场连续经历了数月的萧条之后，目前正是SAB米勒这样的国际性公司涉足兼并的大好时机。假如没有其它集团提出并购案，而股价再次回落到SAB米勒提议并购前的4.9美元，解释福斯特董事会有望重新考虑SAB米勒集团的提议。</P>
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