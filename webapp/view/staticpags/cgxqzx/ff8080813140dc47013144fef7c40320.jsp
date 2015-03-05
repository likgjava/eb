<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>华为与富士康签署20亿美元采购协议- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc47013144fef7c40320.jsp" title="华为与富士康签署20亿美元采购协议" class="cmsHref_self">华为与富士康签署20亿美元采购协议</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>华为与富士康签署20亿美元采购协议</h1>
					<div class="source">
						<span>发布时间：2011-07-20</span>
						<span>发布人：-C114中国通信网  </span>
					</div>
					<p><P>7月20日早间消息 华为与鸿海集团(富士康)周二签署了一份谅解备忘录(MoU)，这将为后者到2013年底前带来价值20亿美元(578亿新台币)的采购订单。</P>
<P>华为预计将从富士康采购各类通信产品，从印刷电路板(PBC)、终端设备到完整的集成设备，整个交易周期将贯穿2011年至2013年，富士康副总裁程天纵(Terry Cheng)在台湾对外贸易发展协会(TAITRA)组织的签字仪式上表示。</P>
<P>华为还与台湾OEM厂商百一电子签署了一份金额较小的协议，将在未来3年内向该公司采购价值4亿美元的机顶盒(STB)和WiMAX产品。</P>
<P>华为副总裁吴昆红表示，2010年华为向上百家台湾制造商采购，金额高达995亿新台币，预计今年采购金额将增长10%以上至1100亿新台币。</P>
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