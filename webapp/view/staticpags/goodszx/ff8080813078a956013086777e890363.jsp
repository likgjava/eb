<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>大豆库存大于市场实际需求 美豆粕涨势受抑- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a956013086777e890363.jsp" title="大豆库存大于市场实际需求 美豆粕涨势受抑" class="cmsHref_self">大豆库存大于市场实际需求 美豆粕涨势受抑</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>大豆库存大于市场实际需求 美豆粕涨势受抑</h1>
					<div class="source">
						<span>发布时间：2011-06-13</span>
						<span>发布人：-中证网  </span>
					</div>
					<p><p>近月合约因现货市场上扬而持坚，但大豆市场下跌抑制了豆粕期货的升幅。芝加哥期货交易所(CBOT)豆粕期货10日涨跌互现，其中CBOT 7月豆粕期货收涨0.1%，报收于373.30美元/短吨。</p>
<p>7月豆粕期货因价差交易而上涨，因市场对现货豆粕需求增加。密苏里河流域洪灾导致铁路出现物流困难，人们纷纷撤消已在CBOT注册的即将发货的豆粕仓单。 分析师表示，因缺乏新的利好支持高价，交易商修正了市场上的风险溢价。周末来临和美元走强也是促使交易商的获利了结的因素。</p>
<p>因海外买家转向采购低价的南美大豆，最近数月美国的出口需求放缓，但2011年产量和播种面积存在不确定性继续限制跌势。美国农业部(USDA)在9日发布的月度作物报告中预计，美国2010/2011年度大豆期末库存为1.80亿蒲式耳，较5月份的预期增加1,000万蒲式耳。农业部下调其出口预期1,000万蒲式耳，维持压榨量预期不变。</p>
<p>大连豆粕期货10日走势偏弱。主力1201合约收于3358元/吨，下跌14元/吨。USDA公布农作物供需报告，报告对大豆影响偏空。中国南方旱区普降大雨，天气题材已暂时消退。生猪价格持续上扬反映出市场上商品猪供应量偏少，导致豆粕需求难以有效启动，令期价上涨乏力。上周五美豆类整体承压，预计近期连豆粕上涨阻力将加大，关注国内5月宏观数据及政策方面变化。</p>
<p>海关周五公布，中国5月进口大豆456万吨，较上月的388万吨上升17.5%。同比增长4.3%。不过近日市场讨论大豆融资进口问题，在国内信贷紧缩的背景下催生大宗商品融资进口市场，据市场人士认为中国近20%的大豆进口量为融资进口需求，而不能反映真实的需求水平。这将令国内大豆库存大于市场实际需求。</p>
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