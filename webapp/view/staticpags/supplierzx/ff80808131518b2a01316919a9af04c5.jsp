<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>台媒称华为为降价左右台湾供应商出货量- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316919a9af04c5.jsp" title="台媒称华为为降价左右台湾供应商出货量" class="cmsHref_self">台媒称华为为降价左右台湾供应商出货量</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>台媒称华为为降价左右台湾供应商出货量</h1>
					<div class="source">
						<span>发布时间：2011-07-27</span>
						<span>发布人：-  </span>
					</div>
					<p><P>据精实新闻报道，大陆系统厂商在光通讯产业重要性日益提升，华为、中兴通讯居全球前五大光纤系统采购商，因此全球市场里，中系厂近年来具有举足轻重地位。台湾业者判断，华为去年底以来已持续累积不少光元件库存，想对供应链旧型元件重谈调整价格，所以近期缩减订单以争取谈判筹码，所以台湾相关光纤元件厂商预期，7、8月份营收偏淡，9月份比较有复苏机会。</P>
<P>华为近年在光纤系统设备出货市占率约在25%以上，2009年单季市占率也曾达27%，是全球光纤系统商最大业者，因此其动向影响到海外与国内光纤元件供应商的业绩。</P>
<P>全球在光纤通讯系统重要厂商，有华为、Alcatel-Lucent(ALU.US)、Ciena(CIEN.US)、中兴通讯、Fujitsu(6702.JP)、Tellabs(TLAB.US)等。</P>
<P>向大陆系统商争取订单的台湾光纤元件厂商有上诠(3363)、华星光(4979)、光环(3234)等，而代工业者则有联钧(3450)。</P>
<P>今年第二季光通讯占联钧营收比重接近6成，因为大陆光通讯业者近期拉货减少，自然影响了联钧6月份营运动能;联钧6月营收2.28亿元、仅约跟4、5月份表现持平。法人预估，联钧的7月、8月光通讯接单不容易明显成长，不过9月份比较有机会回复力道。</P>
<P>据业界评估，因为华为近三季以来，已累积不少元件库存;华为本身为了争取更好的毛利率与利润空间，华为近期刻意降低出货，就是希望各家供应商能够对原有元件成品降价。今年第一季底，华为先针对欧美等元件厂商降低出货，并把当时订单转向台湾，也让台湾业者第一季底到第二季初营收表现颇佳，不少台湾业者4月、5月份营收皆创下今年新高。</P>
<P>不过，华为同样想对台系光纤元件厂降价，所以近期也减少对台系厂下单，这也影响了台厂6、7月份接单情况。台湾业者表示，等到大陆业者价格商谈告一段落之后，8月下旬至9月份光纤客户订单才会比较明朗。</P>
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