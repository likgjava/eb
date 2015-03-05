<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>月饼提早一个月上市 原料采购更严苛需时更长- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a95601308b93b6291184.jsp" title="月饼提早一个月上市 原料采购更严苛需时更长" class="cmsHref_self">月饼提早一个月上市 原料采购更严苛需时更长</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>月饼提早一个月上市 原料采购更严苛需时更长</h1>
					<div class="source">
						<span>发布时间：2011-06-14</span>
						<span>发布人：-  </span>
					</div>
					<p><p>端午刚过，月饼便抢闸上市。昨日，趣香月饼提前一个月举行订货会，拉开今年月饼销售帷幕。企业透露，由于原材料上涨压力，今年月饼零售价将上涨10%~15%。而鉴于今年食品安全形势严峻，厂家需要提前采购安排生产，原料采购要求更严格更健全、需时更长，所以特意提早举办订货会。</p>
<p>昨日记者在趣香月饼推介会上看到，双黄纯正白莲蓉750克规格的价格是118元，经典五仁750克规格的价格是128元，同比去年，微涨16元和20元。</p>
<p>广州市趣香食品有限公司总经理助理欧杰辉介绍说，厂家通过提早批量进货，降低生产毛利空间，把今年的月饼价格(比上年)升幅控制在15%~20%之间。</p>
<p>&ldquo;今年食品安全形势比较严峻，我们需要提前采购，原料采购要求更严格更健全&rdquo;，欧杰辉说今年比较早地开订货会，其实也与食品安全有关系。</p>
<p>对于最近的塑化剂风波，欧杰辉表示，明年原料采购还会要求供应商提供塑化剂的检测合格报告。</p>
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