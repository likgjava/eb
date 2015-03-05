<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>棉花现货报价滞涨 纺企不敢贸然采购- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013052fff50f0644.jsp" title="棉花现货报价滞涨 纺企不敢贸然采购" class="cmsHref_self">棉花现货报价滞涨 纺企不敢贸然采购</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>棉花现货报价滞涨 纺企不敢贸然采购</h1>
					<div class="source">
						<span>发布时间：2011-06-03</span>
						<span>发布人：-  </span>
					</div>
					<p><p>由于国际市场对于欧洲债务危机前景的忧虑犹在，大宗商品具有一定的上行压力，而国内近期大部分地区持续干旱的恶劣天气状况影响了新年度棉花的生长，提振了国内棉花的现货价格，但纺企不敢贸然采购，倾向于观望等待。部分地区纺织厂生产明显放缓，下游购纱热情也不高，造成了棉纱价格的疲软。</p>
<p>大部分地区现货报价滞涨，个别报价继续小幅上调，成交方面变化不大。从安徽地区市场了解到，目前当地轧花厂库存百吨左右的，千吨左右的都有，棉花地产的、新疆的都有。从安徽池州地区了解到，近期纺织厂采购的需求是马克隆值高的棉花，所以地产棉依旧很难动销，新疆棉有少量销售，纺织厂采购量依旧不大，都是一点一点的在采购。据了解当地农发行实行了黑名单制度，如果企业没有按时归还贷款，即将计入黑名单，企业还贷压力依旧较大。当地旱情依然严重，棉农都期盼老天能多下雨，如果6月当地仍将是干旱天气为主的话，棉花后期生长难言乐观。今日中国棉花价格指(CCIndex328)24558元/吨，涨63元，527级棉到厂均价22634元/吨，涨56元。</p>
<p>进口棉中国主港报价普遍上涨2美分左右。现阶段，天气因素炒作、短期补库行为和棉商赌市对棉花价格构成支撑。从纺织产业的实际运作看，棉纱的销售略微复苏并不是生产恢复正常的信号，而是中小企业筹措资金的无奈之举，原料低库存和产品低库存成为当前纺织产业链条的特征。此外，经过几天的上涨之后，国际棉价也可能随时回调。</p>
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