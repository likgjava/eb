<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>江西成品油供应应急预案实施- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201312b562962242c.jsp" title="江西成品油供应应急预案实施" class="cmsHref_self">江西成品油供应应急预案实施</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>江西成品油供应应急预案实施</h1>
					<div class="source">
						<span>发布时间：2011-07-15</span>
						<span>发布人：-今视网-南昌晚报  </span>
					</div>
					<p><P>昨日(14日)，记者从省商务厅获悉，《全省成品油市场供应应急预案》开始实施，将成品油供波动分为四级，并提出了相应的措施，以确保应急状态下全省成品油的供应。</P>
<P>据了解，该办法适用于省、市、县三级商务主管部门，在特定的市场环境下，受国际油品市场、国内资源供应及自然灾害等因素影响，省内成品油市场出现库存不足，部分加油站限量供应或断供、排长队加油，可能影响我省生产生活及社会稳定，但尚未达到《江西省成品油供应中断应急预案》规定级别的市场异常波动状态下的应急管理工作。</P>
<P>据介绍，该办法规定的市场异常波动按照影响范围大小，分为Ⅰ级(特别重大)、Ⅱ级(重大)、Ⅲ级(较大)、Ⅳ级(一般)四个等级。长队加油等现象;成品油主营企业库存不足12天需求，且购进量低于消费量持续10天以上;特殊情况需要划为Ⅱ级市场异常波动的。</P>
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