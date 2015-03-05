<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>味千"骨汤门"后续 质监将查"味千"调料供应商- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601319243e5231ad5.jsp" title="味千"骨汤门"后续 质监将查"味千"调料供应商" class="cmsHref_self">味千"骨汤门"后续 质监将查"味千"调料供应商</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>味千"骨汤门"后续 质监将查"味千"调料供应商</h1>
					<div class="source">
						<span>发布时间：2011-08-04</span>
						<span>发布人：-  </span>
					</div>
					<p><P>据《新闻晨报》报道，继市工商部门介入调查味千拉面涉嫌虚假宣传后，记者昨日从市质监部门获悉，将于近期对被指为味千拉面调料供应商的荏原食品(上海)有限公司展开调查。</P>
<P>据调查，味千拉面汤料浓缩液的主要成分是一种“猪骨汤精”，生产企业为山东泰安京日丸善食品工业有限公司。“猪骨汤精”被运到西盖米食品(上海)有限公司后，制作成汤料浓缩液，然后再送往上海领先餐饮管理有限公司并配送到味千拉面门店。</P>
<P>一款名为“荏原浓缩猪骨白汤”的产品又在此时进入人们的视线。目前市面上不少拉面店、火锅店都在使用这种白汤，而下游客户中也有“味千拉面”。这家“荏原食品”公司地址位于松江区新桥镇新庙三路628号。市质监部门表示将于近期对荏原公司开展调查工作。</P>
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