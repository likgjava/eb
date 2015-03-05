<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>国内大宗商品需求强劲- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a95601308ba50b8e1204.jsp" title="国内大宗商品需求强劲" class="cmsHref_self">国内大宗商品需求强劲</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>国内大宗商品需求强劲</h1>
					<div class="source">
						<span>发布时间：2011-06-14</span>
						<span>发布人：-  </span>
					</div>
					<p><p>6月10日，中国公布的贸易数据显示，中国进口在5月出现了大幅上升，同比上涨28.4%，远高于上月的21.8%和市场预期值22.0%。这表明中国国内的大宗商品需求保持强劲。</p>
<p>在这样的情形下，商品货币的表现也将受到明显的支撑。从商品种类来看，原油以及铁矿石的进口量与上月基本持平，但大豆的进口量却出现了大幅度的上升，升至456万吨。</p>
<p>与进口超预期表现不同，中国的出口则出现了一定的减速迹象。5月出口同比增长了19.4%，与上月相比出现了大约10个百分点的下降，略低于市场预期。其中，中国对美国以及欧洲的出口增速均出现了较大幅度的下滑，增长率分别为7.2%以及13.2%，远低于上月的25.0%以及27.0%。</p>
<p>中国出口出现一定的减速，也符合市场的预期。由于近期发达经济体出现了一定的经济减速迹象，因此，在未来几个月内，中国的出口增速很可能将进一步降低。但由于发达经济体的复苏仍在持续，同时中国出口商品仍保持着较高的竞争力，我们认为中国出口出现严重减速的可能性不大。</p>
<p>与此同时，中国进口保持强劲，也表明国内需求仍然较为乐观。这也能够降低外需减速对于中国经济的下行风险。</p>
<p>此外，尽管5月的贸易顺差弱于市场预期，但我们并不认为这将减缓人民币的升值趋势以及步伐。我们仍维持人民币兑美元在2011年升值的基本预测，并认为中国人民银行可能进一步扩大人民币兑美元的交易区间。从历史经验来看，六七月份往往是人民币汇率政策出现变动的敏感时间窗口。</p>
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