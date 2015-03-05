<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>AMD计划年底淘汰六核羿龙CPU 让位FX系列- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308693c6ca042f.jsp" title="AMD计划年底淘汰六核羿龙CPU 让位FX系列" class="cmsHref_self">AMD计划年底淘汰六核羿龙CPU 让位FX系列</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>AMD计划年底淘汰六核羿龙CPU 让位FX系列</h1>
					<div class="source">
						<span>发布时间：2011-06-13</span>
						<span>发布人：-  </span>
					</div>
					<p><p>北京时间6月13日，据国外媒体报道，AMD公司计划在今年年底淘汰所有六核羿龙II处理器产品线，以便为即将面世FX系列芯片让位。该芯片是基于新的，高性能的Bulldozer架构设计的。</p>
<p>首批遭到淘汰的芯片将是羿龙II X6 1045T，1055T和1065T，销售期最晚将到今年第三季度。其他在羿龙II X6 CPU产品线上的不到五款芯片将紧随其后，在今年年底退出市场。其最晚销售期为2012年的第一季度。这些处理器均基于AMD的Thuban架构，于2010年四月份发布，包括六款K10处理器。</p>
<p>Thuban是AMD首款支持超频技术的CPU架构，可以根据负载情况自动调整内核速度，时钟频率可以达到3.6GHz。</p>
<p>羿龙II X6芯片将被基于Bulldozer架构设计的FX系列处理器代替，该产品将在今年八月或者九月发布，它使用新方法来利用模块化架构。</p>
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