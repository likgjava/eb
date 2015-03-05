<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>生猪供应将增加 猪肉价格将趋稳- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131183592013120fb0a6e0e62.jsp" title="生猪供应将增加 猪肉价格将趋稳" class="cmsHref_self">生猪供应将增加 猪肉价格将趋稳</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>生猪供应将增加 猪肉价格将趋稳</h1>
					<div class="source">
						<span>发布时间：2011-07-13</span>
						<span>发布人：-食品商务网专稿  </span>
					</div>
					<p><P>从春节以来，猪肉价格节节攀升，创历史新高。据国家统计局最新公布的数据显示，6月份，猪肉价格上涨57.1%，影响CPI上涨约1.37个百分点。如此高的猪肉价格不仅影响消费者的日常生活，也损害了整个产业链。热情高涨的猪肉何时才能“冷静”呢?</P>
<P>商务部12日公布的数据显示，上周(7月4日至10日)，猪肉比前一周上涨2.3%，涨幅缩小1.1个百分点，表明后期猪肉价格有趋稳态势。上周商务部重点监测的食用农产品价格涨幅缩小，生产资料价格小幅回升。</P>
<P>农业部调研发现，生猪养殖户补栏积极，生猪存栏连续4个月增长，预计后期生猪价格将逐步趋稳，中秋、国庆期间猪肉市场供给有保障。受养殖亏损影响，生猪存栏自去年2月份开始持续下降，今年3月份起止跌回升。据对全国2000个生猪养殖村定点监测，生猪存栏已连续4个月出现小幅回升，6月底存栏378.6万头，环比增长0.8%，同比增长0.5%。同时，种猪销量增长，仔猪补栏稳步增加。据对全国20个种猪场的数据监测，6月份，共销售纯种母猪10240头、二元母猪18069头，同比分别上涨14.9%和36.7%，养殖场户选购种猪的积极性明显高于去年。</P>
<P>据对全国470个集贸市场的定点监测，6月份全国猪肉、活猪和仔猪平均价格分别为26.71元/公斤、17.54元/公斤和31.11元/公斤，环比分别上涨11.4%、13.0%和16.5%，同比分别上涨66.5%、81.9%和116.2%。生猪价格近期价格涨幅趋缓，7月第1周平均价格的周环比涨幅分别下降0.71、0.94和0.62个百分点，连续两周涨幅收缩。预计今年7、8月后猪价同比涨幅将逐步回落。</P>
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