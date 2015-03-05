<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>联合采购适用于哪些状况？会遭遇哪些问题？- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/pubservice/help/help_index.jsp" title="客服中心" class="cmsHref_self">客服中心</a>
	<a href="javascript:void(0)" id="/view/staticpags/zjszjq/zjszjq.jsp" title="招竞实战技巧" class="cmsHref_self">招竞实战技巧</a>
	<a href="javascript:void(0)" id="/view/staticpags/zjszjq/ff8080812ea2d882012ea43f8474007f.jsp" title="联合采购适用于哪些状况？会遭遇哪些问题？" class="cmsHref_self">联合采购适用于哪些状况？会遭遇哪些问题？</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f6ee4840128" />
				<div class="frameNews">
					<h1>联合采购适用于哪些状况？会遭遇哪些问题？</h1>
					<div class="source">
						<span>发布时间：2011-03-11</span>
						<span>发布人：-  </span>
					</div>
					<p><p>联合采购适用于：<br />
1)进口管制下发生的紧急采购。<br />
2)卖方市场，买主势单力薄。</p>
<p>联合采购面临下列问题：<br />
1)数量分配不均。<br />
2)报价方式混乱。<br />
3)付款能力不一。<br />
4)提货工作缓慢。</p>
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