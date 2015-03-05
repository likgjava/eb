<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>三星液晶面板并入半导体部门- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7f129c36e76.jsp" title="三星液晶面板并入半导体部门" class="cmsHref_self">三星液晶面板并入半导体部门</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>三星液晶面板并入半导体部门</h1>
					<div class="source">
						<span>发布时间：2011-07-05</span>
						<span>发布人：-巨亨网  </span>
					</div>
					<p><P>韩国三星电子7月1日表示，液晶面板部门将会并入半导体部门，并与零件制造部门整并。此项举动意味着，液晶面板业亏损的日子还会持续一段时间。</P>
<P>去年，液晶面板部门与半导体部门的合并营收占三星整体营收39%，并占营业获利达70%。然而，过去1整年裡，液晶面板业处于景气低潮，今年第1季已呈现亏损情形，第2季亦可能再次亏损。合并部门的立即效应在于把液晶面板业所遭遇的困难隐藏于表现强劲的半导体晶片部门里。</P>
<P>今年首季，三星液晶面板部门共亏损2300亿韩元，分析师预期第2季亏损幅度也将加大。</P>
<P>然而，部门合并的举动也将让三星将面临执行高层较少讨论到的组织架构问题，即：零件部门的客户将与其他自家部门相互竞争。</P>
<P>三星表示，将液晶面板并入半导体部门“是为了在技术发展、生产、採购以及客户管理上，能够强化合作与加强配合”。</P>
<P>合并后的零件制造部门将由原半导体部门总经理KwonOh-hyun带领，原LCD部门总经理ChangWonkie转任三星执行长崔志成(ChoiGee-sung)的助手。</P>
<P>2004年时，由于当时液晶面板市场蓬勃且能获得巨额利润，三星曾把半导体晶片部门与液晶面板部门分拆。</P>
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