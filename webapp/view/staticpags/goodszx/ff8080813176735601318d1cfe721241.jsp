<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>业内人士称中国可能加强稀土出口管制- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813176735601318d1cfe721241.jsp" title="业内人士称中国可能加强稀土出口管制" class="cmsHref_self">业内人士称中国可能加强稀土出口管制</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>业内人士称中国可能加强稀土出口管制</h1>
					<div class="source">
						<span>发布时间：2011-08-03</span>
						<span>发布人：-  </span>
					</div>
					<p><P>据华尔街日报报道，中国对稀土价格猛涨造成走私活动猖獗和稀土副产品绕过配额限制流向境外感到担忧，未来可能进一步加强对稀土出口的管制。</P>
<P>报道引述业内人士说法，目前正在讨论的措施包括把更多的稀土副产品纳入配额管理，同时还包括对轻、重稀土分开实施配额管理。</P>
<P>报道还称，有知情人士透露，中国正在考虑将稀土元素含量为30%的Nd-Fe-B永磁合金纳入现行的稀土出口配额。</P>
<P>知情人士还透露，中国政府还在考虑对轻、重稀土分开设立出口配额。最新提交给监管部门讨论的草案建议，应将75%的出口配额分配给镧和铈，25%分配给其余15种稀土矿物。</P>
<P>中国对稀土的出口配额一直为国外所忧虑，认为这会使稀土价格快速上涨，甚至有媒体称中国政府操纵稀土。</P>
<P>但中国有关部门认为，稀土价格上涨是稀土价值本身的回归。其次中国需要对自然资源和环境进行更有效的保护。</P>
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