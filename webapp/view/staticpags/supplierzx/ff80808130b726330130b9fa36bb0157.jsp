<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>传苹果要求供应商降低iPad 2组件价格- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130b9fa36bb0157.jsp" title="传苹果要求供应商降低iPad 2组件价格" class="cmsHref_self">传苹果要求供应商降低iPad 2组件价格</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>传苹果要求供应商降低iPad 2组件价格</h1>
					<div class="source">
						<span>发布时间：2011-06-23</span>
						<span>发布人：-中关村在线  </span>
					</div>
					<p><P>根据国外媒体的报道，苹果公司近日要求海外供应商进一步降低iPad 2组件订单价格。</P>
<P>据报道，PCB、光学组件、电池模组和触摸屏供应商均面临苹果方面的降价压力。据悉，苹果要求在2011年第二季度的配件报价降低10%。</P>
<P>由于苹果iPad平板在第二季的环比出货量将上涨70%，预计可达到800万台，这回给上游组件供应商带来更大的订单量，而随着订单的增长，苹果要求降低价格。据报道，iPad 2触摸屏面板的成本达到了127美元，占据了总成本很大的一部分，而iPad一代触摸屏的成本则不到100美元。</P>
<P>在今年第三季度，iPad平板的销量预计还会继续增长，苹果可能会要求进一步降价。在本月初，苹果方面表示iPad销量经过14个月的销售后已经达到2500万台。</P>
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