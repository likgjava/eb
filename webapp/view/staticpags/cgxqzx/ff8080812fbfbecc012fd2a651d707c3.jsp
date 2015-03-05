<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>采购商：为水产收购 加工旺季做好准备- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd2a651d707c3.jsp" title="采购商：为水产收购 加工旺季做好准备" class="cmsHref_self">采购商：为水产收购 加工旺季做好准备</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>采购商：为水产收购 加工旺季做好准备</h1>
					<div class="source">
						<span>发布时间：2011-05-09</span>
						<span>发布人：-  </span>
					</div>
					<p><p>昨日(8日)，来自全国各地200多名水产采购商，参加湛江南方国际水产交易中心举办的采购商座谈会，共商水产发展大计，为即将到来的水产收购、加工旺季做好准备。</p>
<p>湛江是海洋大市，水产资源十分丰富，目前已发展成为中国最大的水产产品加工和出口基地。去年，湛江养殖对虾1.81万公顷，产虾3.2万吨，分别比上年增长14.3%和31.6%，对虾产量占广东省的55%、全国的六分之一，创历史最高水平。对虾出口3.6亿美元，水产品加工产值60.7亿元，已经形成非常完善的集育苗、养殖、流通、加工、出口为一体的对虾产业链，水产品行业成为湛江最重要的支柱产业之一。结合湛江的海洋优势，唯有在湛江建立一个联系全球的水产综合交易平台，才能把湛江水产产业越做越大。南方国际水产交易中心因此于去年底成立。南方国际水产交易中心的超强整合能力在加快产业联动的基础上，更直接提升了湛江100多万涉海产业人口的幸福感。目前，南方国际水产交易中心所有商户已入驻，部分已开始经常经营，冰鲜虾交易量达到10万公斤。</p>
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