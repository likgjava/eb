<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>如何寻找供应商？- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/zjszjq/ff8080812ea2d882012ea43a2fca006f.jsp" title="如何寻找供应商？" class="cmsHref_self">如何寻找供应商？</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f6ee4840128" />
				<div class="frameNews">
					<h1>如何寻找供应商？</h1>
					<div class="source">
						<span>发布时间：2011-03-11</span>
						<span>发布人：-  </span>
					</div>
					<p><p>　　寻找供应商广开来源，不管是自行征求或经由报转介绍，必须主动积极，另外供应商的寻求不应局限于本地或本国，也应该利用外地或国外的供应来源。<br />
<br />
　　寻找供应商由下列各种途径来进行：<br />
　　1)利用现有的资料。从建立合格厂商的档案或名册中甄选。<br />
　　2)公开招标。<br />
　　3)透过同业介绍。<br />
　　4)新闻专业刊物。<br />
　　5)公会或采购专业顾问公司<br />
　　6)参加产品展示会。<br />
&nbsp;</p>
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