<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>麻城建成年产12万吨聚乙烯塑料管道基地- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ee243a4012ee5b8b1d0112d.jsp" title="麻城建成年产12万吨聚乙烯塑料管道基地" class="cmsHref_self">麻城建成年产12万吨聚乙烯塑料管道基地</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>麻城建成年产12万吨聚乙烯塑料管道基地</h1>
					<div class="source">
						<span>发布时间：2011-03-24</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据报道，日前，年产12万吨新型环保聚乙烯塑料管道的湖北屯仓管业生产基地在湖北麻城经济开发区举行了奠基仪式。</p>
<p>湖北屯仓管业生产基地由海南高能投资有限公司注资5亿元人民币，是麻城市继滨海天联、金红苏管业之后又一重大新型管材项目，基地总占地面积为500亩，建筑面积为18万多平方米，计划于今年11底建成投产，年设计生产新型环保聚乙烯塑料管道12万吨，年产值可达16.8亿元人民币。</p>
<p>屯仓管业基地力图通过降低能耗和运输成本来占据有利市场，开发生产高科技含量、高附加值的聚乙烯（PE）复合瓦斯管和石油管等项目，实现&ldquo;绿色、环保、低碳&rdquo;的技术密集型产业群。</p>
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