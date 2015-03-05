<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>阿根廷限制进口致国内市场供应短缺- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fb4297b012fb8db0c0701a6.jsp" title="阿根廷限制进口致国内市场供应短缺" class="cmsHref_self">阿根廷限制进口致国内市场供应短缺</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>阿根廷限制进口致国内市场供应短缺</h1>
					<div class="source">
						<span>发布时间：2011-05-04</span>
						<span>发布人：-  </span>
					</div>
					<p><p>在阿根廷政府今年加强了对进口产品的限制后，阿国内市场家用电器、手机及生活日用品供应出现短缺，影响了居民生活。</p>
<p>据阿根廷媒体３日报道，为了确保实现贸易顺差，阿根廷政府今年以来扩大了实施非自动进口许可证制度的产品范围，导致大批进口产品在海关受阻。阿政府的目标是希望本国企业利用政府的政策支持来扩大生产，填补进口产品在国内市场上的份额，但由于这是阿政府采取的临时性措施，企业没有足够时间进行设备投资以扩大生产，结果造成市场上许多商品供应短缺。</p>
<p>目前阿根廷市场上中高档手机和家用电器供不应求，因此消费者购买商品后需要等待几个月时间才能收到货。对进口食品的设限导致意大利面条和巴西饼干等阿根廷人日常消费的进口食品从超市货架上消失。此外，对汽车进口配件的限制导致阿根廷汽车配件市场轮胎价格大幅上涨。令阿根廷工业界人士感到担心的是，进口限制甚至导致阿根廷市场螺丝供应不足，使得机器日常维修遇到难题。</p>
<p>分析人士指出，阿根廷政府采取贸易保护措施限制进口产品，希望通过&ldquo;进口替代&rdquo;战略鼓励本国产业发展，但由此造成的后果是阿根廷企业缺乏国际竞争力，高价的本国商品替代进口产品加大了通胀压力。</p>
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