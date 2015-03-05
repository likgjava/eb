<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>沃尔玛与宜家在中国开始大量买地- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a701310216e9330718.jsp" title="沃尔玛与宜家在中国开始大量买地" class="cmsHref_self">沃尔玛与宜家在中国开始大量买地</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>沃尔玛与宜家在中国开始大量买地</h1>
					<div class="source">
						<span>发布时间：2011-07-07</span>
						<span>发布人：-深圳商报  </span>
					</div>
					<p><P>据外电报道，随着租金不断攀升，沃尔玛和宜家等外国零售商放弃十年来的租房战略，在中国大量购买土地。</P>
<P>全球最大零售商沃尔玛去年首次在大连东北部购买店址用地，而宜家部分持股的英特宜家购物中心集团已经投资12亿美元建造51万平方米的购物中心，在中国进一步扩张。</P>
<P>外国零售商之所以选择买地，是因为租金上涨迅猛，Cushman &amp; Wakefield的数据显示，北京市中心王府井地段的租金今年二季度较2007年几乎翻倍，而上海南京西路购物区租金同期上升50%以上。</P>
<P>仲量联行上海分公司中国研究主管迈克尔-克利博纳表示：“由于中国的出租商越来越精明，它们越来越难以获得有利的承租条件。如果你能够拿到地，现在做零售开发的经济效益是非常吸引人的。” 在零售用地需求的推动下，去年中国商业地产投资激增42%，标志着中国进入“购物中心时代”，纽约房地产服务公司如是称。</P>
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