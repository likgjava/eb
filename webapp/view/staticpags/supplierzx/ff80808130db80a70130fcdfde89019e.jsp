<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>飞力达：长三角较领先的IT供应链服务商- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fcdfde89019e.jsp" title="飞力达：长三角较领先的IT供应链服务商" class="cmsHref_self">飞力达：长三角较领先的IT供应链服务商</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>飞力达：长三角较领先的IT供应链服务商</h1>
					<div class="source">
						<span>发布时间：2011-07-06</span>
						<span>发布人：-东北证券  </span>
					</div>
					<p><P>公司主营业务由综合物流服务和基础物流服务构成。综合物流服务包括VMI(库存管理)、DC(分拨中心)、备品备件管理等;基础物流服务包括货运代理、国内运输以及相关延伸增值服务。公司最初是由基础物流服务起家发展，然后延伸至综合物流服务，VMI与DC业务是公司目前重点拓展的业务方向。</P>
<P>2008-2010年，公司来自IT制造业的物流服务收入比重分别为92.15%、93.85%、92.12%。公司与全球知名品牌商宏碁、联想、华硕、索尼和爱立信建立了良好的合作关系，成功融入品牌商的一体化产业链管理体系，公司五大品牌商客户对收入的贡献2010年已达到51.16%以昆山地区为核心的长三角地区是公司收入最重要来源，2008-2010年，该区域实现的收入比重为98.06%、96.35%和95.48%。公司经营的17万平方米仓库中11万平方米位于昆山地区。公司仓库绝大部分来自租赁。</P>
<P>随着公司业务延伸及向综合物流服务拓展，公司业绩较快增长。</P>
<P>2008-2010年，公司收入分别增长28%、-7%、64%，其中毛利率较高的综合物流收入分别增长53%、34%、64%，净利润分别增长-4%、19%、96%，其中2010年受益于IT业复苏，增长尤为显著。</P>
<P>公司的募投项目为昆山综合保税区物流园、昆山现代物流中心项目以及现代物流运营网点拓展，项目达产后将新增固定资产19961万元和无形资产6096万元，新增年销售收入约19944万元，年折旧摊销额约新增1945万元，将有望成为公司新的利润来源。</P>
<P>预计2011年公司业绩继续较快增长，每股收益1.09元，以目前物流业平均20-25倍PE测算，公司合理估值区间为21.00-27.00元。</P>
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