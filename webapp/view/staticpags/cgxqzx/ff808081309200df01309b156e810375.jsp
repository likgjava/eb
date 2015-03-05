<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>同方电脑：批量集中采购可瓦解价格垄断- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b156e810375.jsp" title="同方电脑：批量集中采购可瓦解价格垄断" class="cmsHref_self">同方电脑：批量集中采购可瓦解价格垄断</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>同方电脑：批量集中采购可瓦解价格垄断</h1>
					<div class="source">
						<span>发布时间：2011-06-17</span>
						<span>发布人：-  </span>
					</div>
					<p><p>对于批量集中采购，同方电脑表示非常支持。</p>
<p>通过全面、细致、深入的市场调查，准确地把握用户需求，据此度身打造的产品配置必然能最大限度地满足用户需求。产品质量与服务也将随着规模化的批量采购日趋成熟和完善。对商家而言，能成为办公计算机的供应商定能为企业带来巨大商机和商誉，各商家必然会非常注重产品品质与服务。</p>
<p>此外，办公计算机标准化使采购更公开透明，更具竞争性，有利于打破市场壁垒，形成统一市场，有利于冲击和瓦解行业协定及价格垄断，也能降低企业成本。</p>
<p>和协议供货临时点单式采购相比，批量集中采购使企业在备货、服务等环节计划性更强。以前，由于采购数量、生产数量不确定，商家在上游成本资源争取上不具备优势。政府采购多为高端产品，这对于制造厂商来说，在备货、成本等环节上面临更高的风险。</p>
<p>批量集中采购可能在满足用户的个性需求上优势不足。但是同方电脑认为，从技术上来说，目前计算机产品本身的差异微乎其微。对政府客户的实际需求进行客观调研会发现，目前几乎所有品牌的产品都能很好地满足客户需求。同方电脑一直主张&ldquo;够用就好&rdquo;的原则，支持批量集中采购也是出于对政府用户利益的一种考虑。</p>
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