<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>北京工程建设政府采购将优先使用自主创新产品- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131c418af0131cadedfda0012.jsp" title="北京工程建设政府采购将优先使用自主创新产品" class="cmsHref_self">北京工程建设政府采购将优先使用自主创新产品</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>北京工程建设政府采购将优先使用自主创新产品</h1>
					<div class="source">
						<span>发布时间：2011-08-15</span>
						<span>发布人：-  </span>
					</div>
					<p><P>今后，本市工程建设将通过政府采购使用更多自主创新的建材和产品。市住建委日前发布《关于做好2011年我市工程建设领域政府采购自主创新产品有关工作的通知》。</P>
<P>市住建委相关负责人介绍，《北京市自主创新产品目录》和《北京市首购自主创新产品目录》是市科委、市发改委、市住建委、市经信委、中关村管委联合发布的。目前《北京市首购自主创新产品目录》已发布9批，共计60余项首购产品;《北京市自主创新产品目录》已发布12批，共计4600余项自主创新产品，内容涉及电子信息、环保、现代交通、新能源与节能、农业、生物医药、新材料、先进制造等领域。</P>
<P>各区、县住房城乡建设委和市住房城乡建设委各直属事业单位在预算编制工作中，应优先考虑购买自主创新产品。受市政府委托履行城市工程建设职能，使用市区两级财政性资金全额投资或部分投资的出资、建设和管理单位(业主单位)，在北京市城市快速路和主干道建设项目、高速公路建设项目、轨道交通建设项目及有关基础设施建设项目中应优先安排采购《北京市自主创新产品目录》中的产品。工程建设领域各开发建设、施工、监理企业在使用市区两级财政性基金采购时应优先采购《北京市自主创新产品目录》中的产品。</P>
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