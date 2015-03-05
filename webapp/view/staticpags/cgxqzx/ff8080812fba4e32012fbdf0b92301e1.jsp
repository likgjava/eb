<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>五一期间 家具建材品牌冷热不均- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="采购焦点资讯" class="cmsHref_self">采购焦点资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbdf0b92301e1.jsp" title="五一期间 家具建材品牌冷热不均" class="cmsHref_self">五一期间 家具建材品牌冷热不均</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>五一期间 家具建材品牌冷热不均</h1>
					<div class="source">
						<span>发布时间：2011-05-05</span>
						<span>发布人：-新京报  </span>
					</div>
					<p><p>　　家居流通企业的竞争激烈，家居品牌商也对五一寄予厚望。不过在楼市调控政策的影响下，上游商品房和二手房交易量骤减，让家装市场萎靡态势并未散去。而面临通货膨胀的压力，原材料、卖场租金迅速上涨，人工成本激增，让家装、家具和建材品牌促销相比卖场略显疲软。</p>
<p>　　■ 细节扫描</p>
<p>　　家装 签单量未达到预期数量</p>
<p>　　家装业内人士反映，小长假三天期间咨询家装的客流一般，没有明显增多。东易日盛北京分公司总经理陈瑞表示，三天假期市场自然客流较少，消费者主要以购买产品为主，参与体验馆的邀约客户数量基本与平时周末活动持平。</p>
<p>　　由于五一期间各品牌家装公司的促销力度较大，家装签单量较平时还是有所增加，不过其中新房签单量较去年有明显下降。阔达装饰营销总监夏勃表示，今年五一同比去年客流量下降，签单量好于平时。业之峰装饰北京公司副总经理郜亮说，业之峰新开了西四环12000平方米峰格汇卖场，工程加主材的模式吸引了不少消费者，客流量较平时明显增加，但签单客户也基本与去年同期持平。天盛装饰总经理孙颢说，今年五一新推出了36900套餐，涵盖了装修中会发生的所有基础项目和一部分日常必备家电，部分报价7.7折优惠的力度也超出往年。客流同比去年五一有明显增长，更看重环保、工艺和性价比，签单量约有超过25%的增长。</p>
<p>　　家具 实木平淡，板式略有增长</p>
<p>　　今年五一假期家具集中采购的需求减弱。圣华家具总经理葛永明表示，五一客流与3&middot;15期间没有明显增加，销售和去年同期基本持平。猫王家具营销总监刘爱华说，五一期间居然之家北四环店客流量约为平时2-3倍，促销力度极大的红星美凯龙两店面客流约有20%增长，其余十几个店面客流量较平时周末并无明显增加。</p>
<p>　　记者综合市场数据发现，实木家具比去年同期没有出现明显增长，而板式家具销售整体约有10%以上的增加。联邦家具北京公司总经理王建国表示，小长假期间实木家具销售比去年同期下降10%以上，而且10万元以上整屋配套的消费减少，单个空间家具购买相对增加。飞美家具总经理周凯军则表示，得益于去年店面投入的增加，今年五一期间销售约有20%的增长。</p>
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