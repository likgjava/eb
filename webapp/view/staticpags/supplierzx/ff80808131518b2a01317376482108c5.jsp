<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>优质供应商可以进行反向选择- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01317376482108c5.jsp" title="优质供应商可以进行反向选择" class="cmsHref_self">优质供应商可以进行反向选择</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>优质供应商可以进行反向选择</h1>
					<div class="source">
						<span>发布时间：2011-07-29</span>
						<span>发布人：-绍兴县报  </span>
					</div>
					<p><P>国际供应链是一种全球配置资源，在各个环节中，大家都建立一种非常和谐的利益关系。谁掌控资源，谁就掌握了市场渠道，拥有了话语权。国内有一批优质供应商、好的生产企业，可以进行反向选择。很多上游企业都在选择下游企业，如兰精公司建立计划模式，每年分配原料给生产企业、面料企业。</P>
<P>优化供应链要解决一个转变问题。企业要由各自单纯的选择和竞争变为供应链的集成创新。过去企业的供应链平台相对比较封闭，现在比较开放;过去企业只关注生产，现在不但要能生产，还要使产品卖出好价钱。就要求企业既要加强有形产品的创新，又要强化包括商业模式、品牌价值在内的自我创新。</P>
<P>优化供应链有哪些新举措?一是成本消化。成本消化是供应链各环节都要面对的。比方说对于波动的原料市场，大家要想办法怎么样使用金融工具规避原料波动风险。</P>
<P>二是供应链管理。供应商面对品牌商、零售商、采购商集中式采购的这种趋势，能否优化自己，真正提供一站式、整合式的服务，成为一个合格的供应商，这也是一个重要话题。</P>
<P>第三，供应商品牌化问题。品牌不仅仅是对终端服装而言，产业链各个环节都有品牌。供应商的优化，实际上也是品牌供应商的优化，怎样由过去被动、简单的工业模式，向“一站式”的服务模式转变。我们现在很多终端品牌，虽然拥有一个良好基础，但如果细细来看，在自身管理供应商配合等方面存在很多漏洞。</P>
<P>再有就是集成创新模式和供应链管理的绿色化。任何一项工作的进展都不能完全靠自己，应该形成一个从供应链的终端，特别是品牌率先垂范形成一个从终端向上游的倒推体制，落实好这项工作。</P>
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