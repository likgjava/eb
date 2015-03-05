<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>塑胶供应商苏威拟在华建特种聚合物工厂- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131020955ca0704.jsp" title="塑胶供应商苏威拟在华建特种聚合物工厂" class="cmsHref_self">塑胶供应商苏威拟在华建特种聚合物工厂</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>塑胶供应商苏威拟在华建特种聚合物工厂</h1>
					<div class="source">
						<span>发布时间：2011-07-07</span>
						<span>发布人：-盖世汽车网  </span>
					</div>
					<p><P>综合外电报道，塑胶供应商苏威集团6月27日宣布，为满足亚太区特种聚合物快速增长的需求，该公司将投资1.2亿欧元在中国建厂。新工厂将建在江苏省常熟工业园区，预计可于2014年年初投产。</P>
<P>据了解，中国汽车市场的发展促进了氟橡胶需求的增长。该公司表示，新工厂将负责生产SOLEF聚偏氟乙烯、TECNOFLON氟橡胶等特种聚合物，这些产品主要应用于汽车、航空、石油天然气和能源等行业，这些行业要求密封件在腐蚀性化学介质和高温环境下具有较高纯度和较长的使用寿命。</P>
<P>苏威集团总部设在比利时首都布鲁塞尔，在全球40个国家拥有大约16800名员工，2010年销售额达71亿欧元。</P>
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