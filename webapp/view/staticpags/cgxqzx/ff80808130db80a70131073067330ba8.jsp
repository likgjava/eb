<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>河北石家庄棉价继续下跌 纺企几无采购- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70131073067330ba8.jsp" title="河北石家庄棉价继续下跌 纺企几无采购" class="cmsHref_self">河北石家庄棉价继续下跌 纺企几无采购</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>河北石家庄棉价继续下跌 纺企几无采购</h1>
					<div class="source">
						<span>发布时间：2011-07-08</span>
						<span>发布人：-  </span>
					</div>
					<p><P>尽管这两日郑棉期货行情有所反复，但是河北石家庄的现货市场价格继续呈下跌态势，而纺织企业由于经营困难，基本无采购意愿。</P>
<P>7月7日当地地产4级大包棉报价为21700元/吨(毛重，自提，带票价格)，5级棉报价为20200元/吨，而4级小包棉报价为20000元/吨，5级棉报价为19000元/吨，均较前日下降了200500元/吨。由于部分棉花企业在仓库有质押贷款，在棉花价格日益走低的情况下，银行催还贷款压力增加，棉花报价也比较混乱，更加刺激了市场价格下降的速度。</P>
<P>进入7月份后服装企业订单明显减少，对棉纱需求降低，棉纱棉布销售报价持续走低，销售量继续萎缩，纺织企业库存逐日增加，部分企业因资金周转困难，欲放假停工。</P>
<P>当地一些接有皮棉销售订单的棉花企业继续维持收购工作，4级籽棉收购价格在4.30元/斤(衣分36%，回潮率11%)，5级籽棉收购价格在4.00元/斤，由于炎炎夏季来临，坚持收购的棉企数量有限，棉农交售则比较积极，日收购数量维持在68万斤。当前棉籽销售价格为1.35元/斤，较月初下降0.08元/斤，下降的原因为：一方面是棉籽质量偏弱，棉籽出油率有所降低;二是棉油厂家停机率上升，维持收购的油厂降低价格收购。如果按照2%亏损，加工费用500元/吨计算，4级皮棉生产成本为19738元/吨，5级皮棉生产成本为18072元/吨。尽管皮棉生产成本较低，但是也只有订单在手的棉花企业才敢坚持收购工作。</P>
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