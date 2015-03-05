<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>传统日用玻璃制品满足多样需求- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131db79dc0131df9014210243.jsp" title="传统日用玻璃制品满足多样需求" class="cmsHref_self">传统日用玻璃制品满足多样需求</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>传统日用玻璃制品满足多样需求</h1>
					<div class="source">
						<span>发布时间：2011-08-19</span>
						<span>发布人：-  </span>
					</div>
					<p><p>适用于电烤箱的高耐温玻璃制品既安全又环保，形态万千的琉璃艺术品美轮美奂。近日，2011年中国国际妇幼婴童产业展览会暨轻工精品展完美落幕，10多家日用玻璃参展商展出的一些新型玻璃制品和玻璃工艺品获得了参展观众的追捧，传统玻璃制品绽放出新生命力。</p>
<p>在本届展会上，中国日用玻璃协会组织参展商展出了一些尚未面市的新品。其中，济南台有玻璃制品有限公司带来了专门为内销而设计的耐热玻璃奶瓶，山东黑山玻璃集团带来了原有产品的升级产品。这些具有安全、健康、绿色、环保特性的产品参展首日就获得了不少订单。</p>
<p>此外，业内知名玻璃艺术名家也将自己的艺术精品搬到了展会，包括上海大学美术学院玻璃艺术系教授庄小蔚的《方尖碑系列》等。在大师的雕琢下，普通且易碎的日用玻璃被演绎成各式各样的美：或灵动或笼统，或宏观或微观。一位参展商表示，玻璃艺术具有美感并且具有极大的可塑空间，它的潜在适用性将有助于满足多层次消费需求，带动传统日用玻璃行业的创新、可持续发展。</p> 
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