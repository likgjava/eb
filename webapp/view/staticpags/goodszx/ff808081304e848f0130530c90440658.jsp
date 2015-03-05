<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>全球PC供应链7月或迎“最艰难时刻”- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130530c90440658.jsp" title="全球PC供应链7月或迎“最艰难时刻”" class="cmsHref_self">全球PC供应链7月或迎“最艰难时刻”</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>全球PC供应链7月或迎“最艰难时刻”</h1>
					<div class="source">
						<span>发布时间：2011-06-03</span>
						<span>发布人：-  </span>
					</div>
					<p><p>日本3月发生的灾难迄今影响有限，但情况可能很快就会起变化。</p>
<p>英国《金融时报》2日报道，6月1日，宏碁(Acer)总裁翁建仁(Jim Wong)出席台北国际电脑展时警告，在日本地震和海啸造成破坏之后，今年7月将是全球技术供应链真正承受压力的时期。</p>
<p>按发货量计算，宏碁是世界第二大个人电脑生产商，仅次于惠普。</p>
<p>翁建仁表示，日本3月发生的灾难迄今之所以影响有限，是因为制造商和供应商一直在用多余库存供货。此外，从3月到5月也是个人电脑行业传统意义上的淡季，这也是原因之一。</p>
<p>翁建仁说，但到了7月份，该行业将真正&ldquo;依赖工厂的真实产能&rdquo;。</p>
<p>然而，分析师和其他行业高管普遍认为，灾难的影响比最初担忧的要温和得多。对此类观点，翁建仁表示，个人电脑厂商位于一个长而复杂的供应链的最末端，这个供应链涉及数百个供应商和制造商。&ldquo;我们与离自己最近的上游制造商谈过，迄今为止它们感觉没有什么问题。它们还问过离它们最近的供应商，也没有问题。但是&hellip;&hellip;没有人知道再往上三到五层是否有问题&rdquo;。</p>
<p>翁建仁表示，日本特有的另一个因素是日本人的保守天性，&ldquo;他们总是尽最大努力解决问题，因此他们会跟你说，&lsquo;我们正在努力解决，截至目前没有任何问题&rsquo;，但最终或许还是会出现问题。&rdquo;</p>
<p>今年4月，宏碁将其第二季度个人电脑发货量预期从此前认为的与前一季度持平下调至减少10%，理由是企业重组、库存调整和PC行业的季节性放缓。</p>
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