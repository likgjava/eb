<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>申沃接到北美电动客车采购大订单- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4e78d5012f56c3224f145b.jsp" title="申沃接到北美电动客车采购大订单" class="cmsHref_self">申沃接到北美电动客车采购大订单</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>申沃接到北美电动客车采购大订单</h1>
					<div class="source">
						<span>发布时间：2011-04-15</span>
						<span>发布人：-林劲榆  </span>
					</div>
					<p><p>昨天，上汽集团透露，申沃电动客车已接到来自北美市场的大宗订单，订单总量达到500辆，总价值接近5亿元人民币，这是国内汽车企业获得的来自海外的最大一笔电动车采购合同。</p>
<p>据有关人士透露，该笔订单将在三年内完成，订购方为北美一大型整车生产企业，通过改装，500台电动申沃客车将进入北美公共汽车市场。由于这批客车的价格与北美当地的传统客车采购价已相当接近，而北美几乎没有同类型产品，因此申沃客车此次进入北美市场没有竞争对手。</p>
<p>在2010年上海世博会上，申沃生产的新能源客车被选定为世博会指定车，在世博园区内实现了交通&ldquo;零排放。据悉，正在运行的337辆申沃新能源车中，150辆为混合动力，120辆为电动车，还有67辆超级电容车，示范运行为申沃新能源汽车量产提供了重要的基础。上汽方面表示，今年申沃新能源车将大规模登陆海外市场，除了已经登陆我国香港的超级电容车、登陆美国的500辆电动车外，包括东南亚、欧洲在内多笔订单都在谈判中。</p>
<p>&nbsp;</p>
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