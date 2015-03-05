<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>瑞风和畅进军政府采购市场 建立MPV新基准- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc47013144ffb7d20322.jsp" title="瑞风和畅进军政府采购市场 建立MPV新基准" class="cmsHref_self">瑞风和畅进军政府采购市场 建立MPV新基准</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>瑞风和畅进军政府采购市场 建立MPV新基准</h1>
					<div class="source">
						<span>发布时间：2011-07-20</span>
						<span>发布人：-燕赵都市报  </span>
					</div>
					<p><P>“MPV制造专家”江淮瑞风近期推出了全新力作——— 瑞风II代新品和畅，全面进军高端MPV市场，瑞风II和畅商务版价格为15.98万元，公务版价格为18.18万元。凭借在MPV领域近十年精心耕耘积淀的技术、制造实力，再加上政府采购新动向，江淮瑞风成为政府采购市场的强劲竞品。</P>
<P>据介绍，瑞风II代新品和畅实现了自主高端MPV在外形、空间、操控性与舒适性的全面超越，以“流”畅外观、“宽”畅空间、“酣”畅动力、“顺”畅操控、“舒”畅体验，建立起高端MPV价值新基准。</P>
<P>在MPV细分市场近十年精心耕耘的基础上，在“MPV制造专家”品质保证下，江淮推出瑞风和畅。瑞风Ⅱ代和畅的推出，不仅符合政府的需求，也符合国家的发展趋势。在公务车市场，近几年国家对民生项目的投入力度越来越大，像卫生、电力、药检等系统，国家投入费用来完善各项基础建设，而完善基层的业务用车就是其中的一个重要内容。这些项目都是通过公开招标采购，每次的采购量都比较大，对企业的吸引力很大，江淮瑞风就是其中最大的受益者。</P>
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