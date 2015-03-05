<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供应被操控 铁矿石价难下- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081312d69ed01313ab85e65020f.jsp" title="供应被操控 铁矿石价难下" class="cmsHref_self">供应被操控 铁矿石价难下</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>供应被操控 铁矿石价难下</h1>
					<div class="source">
						<span>发布时间：2011-07-18</span>
						<span>发布人：-香港文汇报　  </span>
					</div>
					<p><P>作为全球第二大经济体，中国几乎是所有大宗商品的头号买家，中国经济的起跌牵动着大宗商品价格走势，近期有关中国经济可能“硬着陆”的言论甚嚣尘上，加上中国上月份进口铁矿石比5月减少221万吨或4.3%，至5,109万吨，更加强了市场对铁矿石价格向下调整的心理预期。</P>
<P>分析料内地需求增长将下降</P>
<P>导致中国进口下降的因素有很多，包括银根被收紧、本土产量增加等，但即使如此，不少分析仍认为这印证了中国对铁矿石需求下降。早前麦肯锡就指出，中国居住类房地产对钢铁需求的复合增长率，将从2005-2010年的9.4%，降至2010-2015年的4%。</P>
<P>产能过剩的问题亦不断被提出来讨论，花旗便表示，铁矿石市场将在2014年出现约5,000万吨的过剩供应，即使淡水河谷(6210)及后把2015年铁矿石产量目标下调10%，该行仍认为铁矿石供过于求的情况或于2015年出现。</P>
<P>不过，淡水河谷财务总监Guilherme Cavalcanti本月初就指出，中国紧缩货币周期的高峰已经结束，未来对铁矿石的需求将升温，未来5年铁矿石的价格将高于每吨150美元。</P>
<P>三大矿山控制发货提升矿价</P>
<P>分析员指出，三大矿山会控制发货量以提升矿价，另外又会进行现货招标，透过相互竞价推高价格。事实上，内地铁矿石价格由前6个月的平均每吨161美元，略增至每吨168美元。有分析就质疑，上月数据下跌是由于三大矿山控制发货量，7月份的数据可能会继续下跌，但价格走势却始终向上。</P>
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