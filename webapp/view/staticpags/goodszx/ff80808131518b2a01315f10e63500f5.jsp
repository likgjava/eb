<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国需求下降担忧 日胶承压挫跌- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01315f10e63500f5.jsp" title="中国需求下降担忧 日胶承压挫跌" class="cmsHref_self">中国需求下降担忧 日胶承压挫跌</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>中国需求下降担忧 日胶承压挫跌</h1>
					<div class="source">
						<span>发布时间：2011-07-25</span>
						<span>发布人：-中国证券报·中证网  </span>
					</div>
					<p><P>因市场担忧中国需求可能下降，最初报告显示中国制造业业一年来首次萎缩。东京工业品交易所(TOCOM)橡胶期货连续第二个交易日下跌，12月交割的TOCOM橡胶期货22日一度下跌2.3%至376.10日圆/千克(约合4，784美元/公吨)，收报381.1日圆/千克。该期货合约本周上涨0.6%。</P>
<P>分析师称，中国是最大的橡胶消费国，所以东京橡胶越来越受到中国市场的影响。对于中国制造业报告的立即反应可能是悲观的，尽管对于轮胎需求的长期前景乐观。汇丰控股21日公布，中国7月制造业采购经理人指数(PMI)初值为48.9，低于6月终值50.1，为2010年7月以来首次低于50，并跌至28个月以来最低水平。显示中国的经济增长正在放缓。</P>
<P>国务院发展研究中心7月11日称，未来10年中国汽车产业的峰值可以达到5000万辆甚至更高。但欧盟各国领导人批准一项计划来解决欧元区主权债务危机，这令市场对经济前景感到乐观。与此同时，市场预计美国总统奥巴马(Barack Obama)可能同意在8月2日之前就提高美国债务上限问题达成一致，亦推动原油价格上扬，限制橡胶的跌幅。</P>
<P>上海天胶期货7月22日震荡回升。主力1201合约收盘36，110元/吨，上涨140元。中国7月汇丰PMI初值下滑至48.9，显示经济增速放缓，但美国和欧盟在债务问题上取得重要进展，令市场担忧情绪缓解。中国6月乘用车销量同比小幅增长，但增长幅度不明显。东南亚地区天气并未影响割胶进程，市场整体供应良好。上周五原油继续走高，日胶下跌，预计近期沪胶将偏多震荡。</P>
<P>消息面上，越南海关总局7月19日公布，今年上半年越南橡胶累计出口28.9万吨，12.6亿美元，同比分别增长19.6%和91.6%。其中，对中国出口17.4万吨，同比增长22.2%，占越南橡胶出口总额的60.2%;对欧盟出口2.55万吨;对马来西亚出口2.15万吨;对韩国出口1.43万吨;对中国台湾出口1.18万吨。</P>
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