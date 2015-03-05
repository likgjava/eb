<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>需求不旺价格下移 棉花现货市场不容乐观- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813090cbdd013090fae6e80102.jsp" title="需求不旺价格下移 棉花现货市场不容乐观" class="cmsHref_self">需求不旺价格下移 棉花现货市场不容乐观</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>需求不旺价格下移 棉花现货市场不容乐观</h1>
					<div class="source">
						<span>发布时间：2011-06-15</span>
						<span>发布人：-  </span>
					</div>
					<p><p>2月中旬以来，受新年度植棉预期大幅增加、下游市场需求不旺、国家调控措施等影响，棉花期价运行重心不断下移。尽管近期棉花技术上有反弹的需求，但业内人士认为，当前皮棉现货价格以及纱线价格仍在延续下跌，难以有效提振棉期价。</p>
<p>据悉，国内外棉花期货价格近期不断走弱，国内现货报价也不断下调。具有较强定价能力的国内大型纺织企业魏桥上月4次下调皮棉采购价格，目前四级皮棉采购价降至25000&mdash;26000元/吨，累计降幅为3000元/吨。</p>
<p>棉价下行，棉商销售意愿增强，但下游企业采购谨慎，采购意愿不大，棉企库存几乎成静态走量。</p>
<p>从籽棉收购方面上看，由于近期市场棉籽资源量较少，籽棉收购价继续下降，棉厂已处于基本停收状态。</p>
<p>而下游纺织厂销售不畅，导致库存积压严重，资金回笼压力增大。受原材料和成品库存双重积压的影响，部分纺织厂开始卖棉，同时采取多种手段努力减少棉纱库存，但成交仍不乐观，导致企业停产、限产、转产现象继续存在。</p>
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