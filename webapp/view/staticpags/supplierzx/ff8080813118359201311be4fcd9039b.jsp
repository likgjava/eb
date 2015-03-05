<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>飞利浦宣布正式牵手奔腾- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201311be4fcd9039b.jsp" title="飞利浦宣布正式牵手奔腾" class="cmsHref_self">飞利浦宣布正式牵手奔腾</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>飞利浦宣布正式牵手奔腾</h1>
					<div class="source">
						<span>发布时间：2011-07-12</span>
						<span>发布人：-每日经济新闻  </span>
					</div>
					<p><P>飞利浦收购奔腾一事终获确认。昨日(7月11日)，荷兰皇家飞利浦电子公司正式宣布，已经同意收购奔腾电器(上海)有限公司，该收购预计将于2011年第四季度完成。</P>
<P>对此，中国家电商业协会营销委员会副理事长洪仕斌认为，飞利浦想借势奔腾电器，实现其精品小家电与生活小家电的双拳策略。</P>
<P>奔腾电器总裁张勇涛昨日在接受 《每日经济新闻》记者采访时表示，收购完成后，奔腾现有团队、品牌等不会改变，只是更换一个大股东而已，张勇涛表示自己并不清楚具体的收购金额。</P>
<P>洪仕斌认为，当前，奔腾电器已经走过“做蛋糕”过程，不得不和美的、苏泊尔等品牌进行全面竞争。</P>
<P>飞利浦在精品小家电方面具有强势地位，但是生活小家电却是其短板，奔腾“洋嫁”飞利浦后，两者能更充分发挥互补作用。</P>
<P>不过，飞利浦收购奔腾后，意味着小家电领域又失去一个中资品牌。</P>
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