<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>6月7日豆粕市场预测及采购建议- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013067c02c3f1745.jsp" title="6月7日豆粕市场预测及采购建议" class="cmsHref_self">6月7日豆粕市场预测及采购建议</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>6月7日豆粕市场预测及采购建议</h1>
					<div class="source">
						<span>发布时间：2011-06-07</span>
						<span>发布人：-  </span>
					</div>
					<p><p>受天气改善及油价下跌因素打压，周一CBOT大豆期货市场大幅收低。7月大豆收盘1383.25美分，下跌31.25美分;11月大豆收盘1372.75美分，下跌24.25美分。CBOT豆类产品期货市场跟随大豆收低。7月豆油收盘58.03美分/磅，下跌0.7美分;7月豆粕收盘359.4美元/短吨，下跌9美元。</p>
<p>现货方面，上周五国内豆粕现货价格稳中上涨。对于近日市场而言，从目前下游豆粕需求有点好转，但港口库存压力仍处于高位，豆粕上涨空间受到抑制，同时，国内通胀形势仍严峻情况下警惕进一步政策调控政策的出台。国内现货需求仍然低迷不振使其价格难以对大幅度上涨构成强烈回应，短期内现货供大于求的基本面无明显改善。隔夜美豆类全线回落，预计今日国内豆粕价格随外盘稳中下跌，下跌幅度在10-60元/吨。近期需求方及时根据目前状态因素结合自身情况，贸易商可保持振荡思路逢价格回调时可逢低适量补库。</p>
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