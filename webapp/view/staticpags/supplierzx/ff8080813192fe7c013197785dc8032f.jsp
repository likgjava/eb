<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>日立与三菱重工合并磋商基本达成一致- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c013197785dc8032f.jsp" title="日立与三菱重工合并磋商基本达成一致" class="cmsHref_self">日立与三菱重工合并磋商基本达成一致</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>日立与三菱重工合并磋商基本达成一致</h1>
					<div class="source">
						<span>发布时间：2011-08-05</span>
						<span>发布人：-  </span>
					</div>
					<p><P>日本日立公司总裁中西宏明4日披露，日立公司和三菱重工业公司就经营合并开始磋商并基本达成一致，并计划于2013年春季成立新公司。</P>
<P>合并业务包括核能、火力发电设备，水处理以及可再生能源，铁路系统等社会基础设施以及信息通讯控制技术等两家公司核心业务。如果获得公平交易委员会的认可，两家公司将于2013年4月合并成立新公司。合并后新公司销售额将达12万亿日元，成为仅次于丰田的日本第二大制造业企业。</P>
<P>日立公司作为综合电机制造厂商在日本排名第一，三菱重工除了起家的造船业外、另有重型机械、飞机、机车车辆生产业务，两家公司在海外城市铁路建设方面已有合作关系。另外，两家公司还与三菱电机在水力发电系统方面实现了业务合并。</P>
<P>福岛核电站发生核泄漏事故之后，本来被看好的核电设施出口蒙上阴影，日元急剧升值导致出口企业经营环境恶化，这些因素推动两家公司加快了合并步伐。</P>
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