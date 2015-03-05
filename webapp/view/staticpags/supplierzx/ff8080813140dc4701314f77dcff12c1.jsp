<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>诺基亚一年半首亏 第二季度净亏5.21亿- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314f77dcff12c1.jsp" title="诺基亚一年半首亏 第二季度净亏5.21亿" class="cmsHref_self">诺基亚一年半首亏 第二季度净亏5.21亿</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>诺基亚一年半首亏 第二季度净亏5.21亿</h1>
					<div class="source">
						<span>发布时间：2011-07-22</span>
						<span>发布人：-赛迪网  </span>
					</div>
					<p><P>7月22日消息，据国外媒体报道，诺基亚今日发布最新季度财报，财报显示其第二季度净亏损3.68亿欧元(约合5.21亿美元)，而去年同期为盈利2.27亿欧元，这不仅远远超出了业内分析师平均预测的144万欧元净亏损预期，而且也是诺基亚在最近一年半以来首次陷入亏损。</P>
<P>诺基亚第二季度手机销量为8850万部，同比减少了20%，同样也低于业内分析师平均预测的9600万部销量目标。诺基亚首席执行官埃洛普正忙于清空塞班手机，为即将推出的WP手机做准备。</P>
<P>诺基亚表示，公司将加快手机部门的成本削减计划，预计到2013年时，可将营业费用降低10亿欧元。</P>
<P>诺基亚从1998年开始成为全球最大手机厂商，但它的强项一直是功能手机。苹果在2007年推出首款iPhone后，诺基亚就一直落于下风。苹果第二季度的iPhone销量达到了2030万部，帮助苹果将季度利润提高了一倍，达到73.1亿美元。从收入角度来说，苹果在今年第一季度就已经超过诺基亚成为全球最大手机厂商。</P>
<P>截至前一交易日收盘，诺基亚股票今年累计下跌了50%，它目前的市值仅为163亿欧元。</P>
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