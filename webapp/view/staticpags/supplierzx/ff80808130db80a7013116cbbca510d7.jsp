<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>李宁十年高增长戛止 库存过高成死穴- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013116cbbca510d7.jsp" title="李宁十年高增长戛止 库存过高成死穴" class="cmsHref_self">李宁十年高增长戛止 库存过高成死穴</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>李宁十年高增长戛止 库存过高成死穴</h1>
					<div class="source">
						<span>发布时间：2011-07-11</span>
						<span>发布人：-  </span>
					</div>
					<p><P>继昨日暴跌15.77%后，李宁今日继续暴跌9.36%。</P>
<P>李宁公司昨日发布盈利预警，2011年销售收入预计同比减少5%左右，李宁公司连续十年复合增长超过30%戛然而止。</P>
<P>存货问题已经成为李宁公司业绩增长的死穴，数据显示，李宁今年应收账款时间比去年同期增加20天左右，存货周转天数可能增加约25天。2010年该公司平均应收贸易款项周期为52天，平均存货周转期为52天。</P>
<P>简单比喻，如果李宁公司的经销商卖出货品并将货款转给李宁公司原来需要2个月的话，现在可能需要3个月。业内人士称，经销商手里的产品卖不掉就成了存货，资金无法回收，自然也没有钱去买李宁的新产品，导致李宁公司新一财年的收入减少。</P>
<P>“李宁等国内运动品依然按照之前的增速制定增长目标，导致存货高企。有些企业的存货与李宁公司相比，有过之而无不及，如果停下来不生产，库存的商品估计需要销售两年。风水轮流转，现在是国内运动品牌该勒紧裤腰带过苦日子的时候。”上述分析人士认为。</P>
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