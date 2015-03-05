<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中石油中石化力保“三夏”油品供应- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813045f6fc013048bee20400ea.jsp" title="中石油中石化力保“三夏”油品供应" class="cmsHref_self">中石油中石化力保“三夏”油品供应</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>中石油中石化力保“三夏”油品供应</h1>
					<div class="source">
						<span>发布时间：2011-06-01</span>
						<span>发布人：-  </span>
					</div>
					<p><p>全国粮食主产区近日大面积开镰，&ldquo;三夏&rdquo;生产全面展开。我国两大成品油供应商中国石油(601857)天然气集团公司和中国石油化工集团公司31日表示，将保质、保量、保供、保运，力保今年&ldquo;三夏&rdquo;农业用油。</p>
<p>针对今年年初以来我国干旱地区多、农业用油量大和抗旱形势严峻的特点，中石油和中石化已提前对今年&ldquo;三夏&rdquo;保供工作做了工作部署，要求下属相关企业全力保障&ldquo;三夏&rdquo;农业用油。</p>
<p>据统计，中石化5月共向河北、山西、山东、河南、江苏、安徽、湖北调配柴油资源249.57万吨，同比增长16.4%。中石油1至5月供应河北、山西、湖北、安徽、河南、山东、江苏、四川、陕西九省&ldquo;三夏&rdquo;重点地区柴油资源1078万吨，较去年同期增加约131万吨，同比增长13.8%。</p>
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