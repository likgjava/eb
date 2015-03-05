<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中小企业资金吃紧 水泥龙头或加快并购- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813225280f013227afddee002f.jsp" title="中小企业资金吃紧 水泥龙头或加快并购" class="cmsHref_self">中小企业资金吃紧 水泥龙头或加快并购</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>中小企业资金吃紧 水泥龙头或加快并购</h1>
					<div class="source">
						<span>发布时间：2011-09-02</span>
						<span>发布人：-  </span>
					</div>
					<p><P>近日，据调查显示，受到紧缩货币政策影响，国内的中小水泥生产正面临现金流吃紧的挑战。这为一些大型水泥企业进行并购提供了良机，分析观点认为，在当前的背景下，水泥业的并购步伐将加快。</P>
<P>据悉，国内水泥行业的领军企业上周宣布了许多收购行动。其中，海螺水泥、台泥国际和华润水泥上周宣布，在中国不同地区开展了一些规模较大的收购行动。台泥和海螺分别在重庆和陕西进行了收购，这些收购与它们的现有产能之间可产生良好的协同效应，而对于华润水泥，收购增强了该公司在云南市场的地位。这些并购表明水泥行业的整合正呈现加速的趋势。</P>
<P>研究显示，受政府货币紧缩政策影响，中小水泥生产商(尤其是西部利润率较低地区的生产商)正面临现金流吃紧的挑战。由于此前大型水泥企业已经从华东和华南的行业整合中受益，并且内生性增长受到政策限制，有政府支持并且现金流宽裕的水泥行业领军企业将在行业整合中处于更有利地位。</P>
<P>与此同时，并购活动提速也彰显了行业领军企业对水泥行业前景的信心。尽管现阶段西部的利润率仍然较低，但主要公司相信市场整合将显着提升该地区的水泥价格。</P>
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