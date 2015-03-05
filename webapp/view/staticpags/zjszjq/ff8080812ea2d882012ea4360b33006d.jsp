<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>如何更好的采购到满意的商品？- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/pubservice/help/help_index.jsp" title="客服中心" class="cmsHref_self">客服中心</a>
	<a href="javascript:void(0)" id="/view/staticpags/zjszjq/zjszjq.jsp" title="招竞实战技巧" class="cmsHref_self">招竞实战技巧</a>
	<a href="javascript:void(0)" id="/view/staticpags/zjszjq/ff8080812ea2d882012ea4360b33006d.jsp" title="如何更好的采购到满意的商品？" class="cmsHref_self">如何更好的采购到满意的商品？</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f6ee4840128" />
				<div class="frameNews">
					<h1>如何更好的采购到满意的商品？</h1>
					<div class="source">
						<span>发布时间：2011-03-11</span>
						<span>发布人：-  </span>
					</div>
					<p><div class="caugou">　　专业、细致的采购需要关注以下三点：<br />
A 质量<br />
质量的传统解释是好、或优良，对采购人员而言，质量的定义应是：符合买卖约定的要求或规格就是好的质量。故采购人员应设法了解供应商对本身商品质量了解的程度，管理制度较完善的供应商应有下列有关质量的文件：<br />
　　● 质量合格证<br />
　　● 商检合格证<br />
采购人员应向供应商取得以上的资料，以利未来的交易。在我国商品的产品执行标准有国家标准、专业（部）标准及企业标准，其中又分为强制性标准和推荐性标准。但通常在买卖的合同或订单上，质量是以下列方法的其中一种来表示的：<br />
　　● 市场上商品的等级<br />
　　● 品牌<br />
　　● 商业上常用的标准<br />
　　● 物理或化学的规格<br />
　　● 性能的规格<br />
　　● 工程图<br />
　　● 样品（卖方或买方）<br />
　　● 以上的组合<br />
采购人员在采购时应首先与供应商对商品的质量达成互相同意的质量标准，在可能的情况下，对一些产品应要求供应商提供样品封存，以避免以后的纠纷或法律诉讼。对于瑕疵品或在仓储运输过程损坏的商品，采购人员在采购时应要求退货或退款。</div>
<div class="caugou"><br />
B价格<br />
　　除了质量与包装之外，价格是所有采购事项中最重要的项目。高质量低价格，若采购人员对任何所拟采购的商品，以进价加上合理的毛利后，连自已都判断该价格无法吸引客户的购买时，就不应向该供应商采购。<br />
　　在采购之前，采购人员应事先调查市场价格，不可凭供应商片面之词，如果没有相同商品的市价可查，应参考类似商品的市价。在采购价格时，采购人员最好先分析成本或价格；数家供应商进行竞标时，采购人员应选择两、三家较低标的供应商，再分别与他们采购，求得公平而合理的价格。但在使用竞标方式时，采购人员切勿认为，能提供最低价格的供应商即为最好的供应商。我们必须综合选择一个在其送货、售后服务、营销支持、其他赞助等方方面面能提供支持的供应商。所以有时候我们会放弃与提供极低价格给我们的大批发商的合作，而选择不愿意提供极低价格给我们的制造商或生产厂商与我们合作，因为后者的制造商通常在产品质量、货源保证、售后服务、促销活动及其他赞助上会有更多的营销费用支持。</div>
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