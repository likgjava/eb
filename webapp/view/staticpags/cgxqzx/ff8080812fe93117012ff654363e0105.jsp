<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>存储采购：应用为中心的存储- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe93117012ff654363e0105.jsp" title="存储采购：应用为中心的存储" class="cmsHref_self">存储采购：应用为中心的存储</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>存储采购：应用为中心的存储</h1>
					<div class="source">
						<span>发布时间：2011-05-16</span>
						<span>发布人：-  </span>
					</div>
					<p><p>存储系统很多提供的信息都反映网络存储系统特性的细节，即速度和提供能力。这已成为常态，如果这些信息没有被明确地提供，很多人都会认为供应商在试图掩盖他们产品的缺陷。</p>
<p>但是时代不同了，那些负责存储采购的IT人士需要考虑不同的因素。</p>
<p>可能我需要解释一下做决定有何不同，以及为何会产生变化。首先，让我们看看其原因。总的来说，不管他们是否还被称作存储经理人，这种专业人士的数量大幅减少了，只有在较大的IT部门中才能找到只负责存储的专业人士。</p>
<p>这就导致了存储专家更少，主要是由于人员配备的减少。剩下的IT人员不得不身兼数职。另外，存储和已有方案对细节管理和控制要求更少，使得没有存储专家也可以成功地实施和管理存储。</p>
<p>人员配备上的变化带来了采购存储系统时的决策上的变化。现在主要的注意力通常集中在为一个应用配备网络存储系统来满足业务问题的需求。在这种场景中，存储系统做为一个应用的方案集成在环境中。它以那个应用的特定需求而实施并管理。存储如何快速地满足应用的需求是最重要的考量。</p>
<p>对速度和供应能力等信息的需求还是存在的，但是那对IT人士的评估来说不是最重要的指标，至少不应该是。还有些产品(以及产品的推广)继续集中在那个信息上。那些能解决业务问题并迅速满足应用需求的系统会有更多的机会。</p>
<p>这里会发生某种形式的自然选择。理解并可以按客户的需求来展示他们的产品的供应商最终会更加成功。那些固守成规的人日子会有些难过。</p>
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