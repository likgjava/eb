<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>农产品拟定增募资25亿以夯实主业- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fc32c8f9201bd.jsp" title="农产品拟定增募资25亿以夯实主业" class="cmsHref_self">农产品拟定增募资25亿以夯实主业</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>农产品拟定增募资25亿以夯实主业</h1>
					<div class="source">
						<span>发布时间：2011-05-06</span>
						<span>发布人：-  </span>
					</div>
					<p><p>4月28日起停牌的农产品今日披露，拟定向增发募集资金25亿元，主要投入天津和广西的国际物流项目，以夯实主业。大股东深圳国资局及其子公司远致投资承诺，将分别认购本次发行总数的21.52%和5.22%。</p>
<p>根据定向增发预案，农产品本次拟发行不超过1.51亿股，发行底价为16.56元/股，停牌前股价报收18.7元。</p>
<p>农产品拟募集资金不超过25亿元投入3个项目。其一，向天津海吉星农产品物流有限公司增加投资，用于天津海吉星农产品国际物流园项目，拟投入募集资金不超过16亿元;其二，向广西海吉星国际农产品物流有限公司增加投资，用于广西海吉星农产品国际物流中心项目，拟投入募集资金不超过4亿元;另外5亿元用于偿还银行贷款。</p>
<p>其中，投资额最大的天津海吉星农产品国际物流园项目位于天津静海县静海新城北侧北环工业园，规划面积1068.2亩，建设内容主要包括蔬菜、水果、副食品、酒店用品等品种的批发交易区，以及物流仓储区和综合配套服务区等。广西海吉星农产品国际物流中心项目立足广西、流通西南、面向全国、辐射东盟，项目位于南宁市江南区核心位置，总占地约606亩，与南宁国际保税物流园区一并被广西确定为南宁市重点向南、桥接东盟的支柱物流业重点发展项目。</p>
<p>农产品表示，实施本次非公开发行，不仅可提高公司资本实力、市场竞争力和风险抵御能力，还可以进一步做大做强公司主业，提升核心业务盈利能力。</p>
<p>目前，农产品的大股东深圳市国资局持有公司21.52%的股份，其子公司远致投资持有5.22%的股份。为保证定向增发后控股权不被稀释，深圳国资局及远致投资承诺，将分别认购本次定向发行股份总数的21.52%和5.22%。从募集总额推算，两家公司将合计掏出6.685亿元参与认购。</p>
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