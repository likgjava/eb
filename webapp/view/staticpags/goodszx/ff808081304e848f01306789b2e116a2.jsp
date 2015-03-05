<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>我国仪器仪表国外市场占有率呈上升趋势- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01306789b2e116a2.jsp" title="我国仪器仪表国外市场占有率呈上升趋势" class="cmsHref_self">我国仪器仪表国外市场占有率呈上升趋势</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>我国仪器仪表国外市场占有率呈上升趋势</h1>
					<div class="source">
						<span>发布时间：2011-06-07</span>
						<span>发布人：-  </span>
					</div>
					<p><p>国家发改委消息，今年前5个月仪器仪表及文办机械制造业投资额达116亿元，同比增长39%。海关数据显示，仪器仪表一季度出口额为94.41亿美元，同比增长40.2%。一季度进口额179.70亿美元，同比增长35%。</p>
<p>产量增长较快的产品有数码照相机、成分分析仪器、照相机，同比增长率分别为401%、3965%、3477%。</p>
<p>虽然我国仪器仪表行业的整体技术水平与国外发达国家相比并不强，但近年来一些产品在国外的市场份额不断扩大。数据显示，去年我国仪器仪表行业出口交货值同比增长39.2%，出口值超过了总产值的四分之一。这些数据表明全行业的快速增长受出口拉动较为明显，但同时，国际市场的变化对全行业的影响也在加大。</p>
<p>出口方面，以电度表、水表为代表的劳动密集型产品仍是亮点，目前我国已成为这两类产品的最大出口国。</p>
<p>从行业财务数据来看，由于我国经济的快速发展，带动了仪器仪表行业需求的快速增长，我国的仪器仪表行业收入和利润已经连续五年实现了20%以上的快速增长。预计在经济仍将快速发展的背景下，今明两年仪器仪表行业整体增长仍然保持在20%以上。</p>
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