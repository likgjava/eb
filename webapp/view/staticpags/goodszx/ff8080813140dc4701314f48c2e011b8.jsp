<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>雷诺三星寻求摆脱日本零部件供应商影响- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314f48c2e011b8.jsp" title="雷诺三星寻求摆脱日本零部件供应商影响" class="cmsHref_self">雷诺三星寻求摆脱日本零部件供应商影响</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>雷诺三星寻求摆脱日本零部件供应商影响</h1>
					<div class="source">
						<span>发布时间：2011-07-22</span>
						<span>发布人：-盖世汽车网  </span>
					</div>
					<p><P>日前，法国汽车制造商雷诺公司(Renault SA)旗下韩国分部雷诺三星(Renault Samsung)表示，将更多地寻求韩国当地零部件供应路径，从而摆脱日本零部件供应商状态不佳所带来的负面影响。</P>
<P>原先雷诺三星较大程度上依赖日本零部件供应，而今年3月11日发生日本地震和海啸后，日本区域供应链在4月份中断，雷诺三星汽车公司不得不减少其在釜山的工厂20%的产量。到了5月份，由于日本局部危机有所缓解，其生产又得以恢复。但是，包括零部件供应缩减在内的各方面因素，直接导致该公司上半年国内市场和海外市场的销量下跌了9%。在这之后，在韩国的经销商计划更多地依靠当地的企业来获取汽车零部件。</P>
<P>雷诺三星汽车公司首席执行官兼代表总监Jean-Marie Hurtiger表示：“如果韩国供应商有能力的话，我们将一如既往地致力于企业在韩国的本土化。这是我们的当务之急。”</P>
<P>此外，法国制造商正考虑采用一套新的轮班工作制度，以便在提高产量的同时，增强对下半年销售的乐观态度。位于釜山的工厂具备年产30万辆的能力，其生产的汽车贴上雷诺或日产汽车的品牌后出口中东、中国、俄罗斯、南美洲和欧洲。其产品包括日产的Sunny和Almera以及雷诺的Fluence和Latitude。</P>
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