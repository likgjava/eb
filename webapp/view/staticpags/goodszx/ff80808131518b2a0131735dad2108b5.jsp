<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供给大于需求 服装里料市场路在何方- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a0131735dad2108b5.jsp" title="供给大于需求 服装里料市场路在何方" class="cmsHref_self">供给大于需求 服装里料市场路在何方</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>供给大于需求 服装里料市场路在何方</h1>
					<div class="source">
						<span>发布时间：2011-07-29</span>
						<span>发布人：-中国服装辅料网  </span>
					</div>
					<p><P>近期，中国轻纺城涤纶长纤类服装里料需求不足，订单发货不多，部分中小经营户里料现货成交显现不足，销量连日呈震荡下滑走势。</P>
<P>就整体市场而言，喷水涤塔夫、轻盈纺和喷水色丁、喷水舒美缎、喷水五枚缎、喷水八枚缎局部性仍有批量动销，但部分上市量大面广的里料坯布及成品染色里料现货局部性尚有积压。近期轻纺城市场涤纶长丝里辅料现货上市小幅下降，上下游成交周转时显断续，价格稳中有跌，成交多显缩减。</P>
<P>喷水涤塔夫、轻盈纺和喷水色丁、喷水舒美缎、喷水五枚缎、喷水八枚缎成品染色里料小批量现货成交和订单价格亦有小幅下跌。受气温连续上升影响，至目前轻纺城整体长丝里料连日成交继续震荡下跌。</P>
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