<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>山东607个产品中标基本药物集中采购 6月完成- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4e78d5012f56c4b021146e.jsp" title="山东607个产品中标基本药物集中采购 6月完成" class="cmsHref_self">山东607个产品中标基本药物集中采购 6月完成</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>山东607个产品中标基本药物集中采购 6月完成</h1>
					<div class="source">
						<span>发布时间：2011-04-15</span>
						<span>发布人：-  </span>
					</div>
					<p><p>记者从山东省卫生厅获悉,自2月24日山东省基本药物集中采购投下全国第一标至今,国家基本药物中的607个通过竞价和议价产生的中标产品已经分三批正式公布。5月1日起,基层医疗卫生机构将按计划开始进行网上采购。省增补基本药物集中采购工作于本月中旬开始,计划于6月底前完成。</p>
<p>从中标的药品生产企业及产品来看,中标的大企业多,产品质量比较好,价格下降幅度也很明显。&ldquo;目前来看,集中采购也面临一些问题。&rdquo;省卫生厅副厅长康永军说,比如独家品种降价难度较大,由于缺乏竞争,此次进行议价的141个独家品种,价格整体下降幅度仅为0.87%。基本药物集中采购采取单一货源承诺的方式(即每种基本药物只选择一家企业采购,实行最低价中标,由中标企业获得供货区域内该药品全部市场份额),山东省是人口大省,基本药物需求量大,对于大输液等用量大的品种,如果只有一家企业中标,可能会导致有的中标企业因生产能力有限,短时间内无法完全满足全省需求。此外,基本药物货款省级统一支付的压力也较大。<br />
&nbsp;</p>
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