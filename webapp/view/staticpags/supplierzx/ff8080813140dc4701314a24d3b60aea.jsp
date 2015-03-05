<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>林洋电子打造电工仪器仪表金牌供应商- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314a24d3b60aea.jsp" title="林洋电子打造电工仪器仪表金牌供应商" class="cmsHref_self">林洋电子打造电工仪器仪表金牌供应商</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>林洋电子打造电工仪器仪表金牌供应商</h1>
					<div class="source">
						<span>发布时间：2011-07-21</span>
						<span>发布人：-上海证券报  </span>
					</div>
					<p><P>林洋电子今日刊登招股书，拟首次公开发行不超过7500万股，本次募集资金将主要用于智能电能表建设项目、智能用电信息管理终端建设项目、智能电能表零部件配套项目和技术和服务中心建设项目。借助此次发行上市，逐步把公司建成在智能电能表及智能电网相关产品领域的全球金牌供应商。　<BR><BR>林洋电子主营电子式电能表、用电信息管理系统及终端产品和其他电工仪器仪表产品的研发、生产和销售，主要产品主要包括电子式电能表、用电信息管理系统及终端产品。目前，公司已形成了年产850万台单相电子式电能表和70万台三相电子式电能表的生产能力，是国内规模最大的电子式电能表生产厂家。据中国仪器仪表行业协会电工仪器仪表分会资料统计，2007年至2009年，公司电子式电能表产品在国内市场占有率一直保持第一。</P>
<P>作为我国规模最大的电子式电能表生产企业，公司是中国电子式电能表标准的参与制订者，也是中国电子式电能表市场的先行者。公司建有“国家博士后科研工作站”、“国家级电能表检测与校准实验室”等高规格、高水平研发平台，先后承担过多项国家、省级科技攻关项目。</P>
<P>公司预计，随着本次募投项目的逐步建成投产，公司在上市后两到三年内，将不断调整与优化产品结构，并力争实现主营产品电子式电能表年销量达到1500万台，用电信息管理系统及终端产品年销量达到30万台(套)，进一步提升智能电能表产业链的配套能力。</P>
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