<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>东芝将向福特供应混合动力车用逆变器- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813204bef601321300ea9c03c3.jsp" title="东芝将向福特供应混合动力车用逆变器" class="cmsHref_self">东芝将向福特供应混合动力车用逆变器</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>东芝将向福特供应混合动力车用逆变器</h1>
					<div class="source">
						<span>发布时间：2011-08-29</span>
						<span>发布人：-  </span>
					</div>
					<p><P>综合外电报道，日本东芝公司于当地时间2011年6月20日宣布，该公司将自2012年4月起为美国福特汽车公司供应混合动力车和插电式混合动力车用逆变器。</P>
<P>东芝公司表示，将在其位于日本三重县朝日町的三重工厂启动逆变器生产线，该生产线的逆变器年产能为15万件。该公司表示，在取得福特的最终资格认定等条件达成后，该工厂可于2012年4月份开始向福特供货。据悉这是东芝首次获得车用逆变器订单。</P>
<P>2010年6月份，东芝与福特曾签署了一份供应协议，根据该协议，东芝宣布将向福特汽车公司位于美国德克萨斯州的工厂供应混合动力车和插电式混合动力车用牵引电机，并将自2012年起向福特供货。</P>
<P>东芝公司预计，到2020财年，全球汽车逆变器市场的市场规模将由2010财年的1800亿日元上升至1.5万亿日元。此外，该公司还称，将加速推动其全球业务发展，到2015财年，该公司旗下电机、逆变器和可充电电池产品等电力、电子业务的净销售额目标为8000亿日元。</P>
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