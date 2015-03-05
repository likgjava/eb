<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>欧盟批准软小麦出口量达五周最高水平- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fc33a20e001e5.jsp" title="欧盟批准软小麦出口量达五周最高水平" class="cmsHref_self">欧盟批准软小麦出口量达五周最高水平</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>欧盟批准软小麦出口量达五周最高水平</h1>
					<div class="source">
						<span>发布时间：2011-05-06</span>
						<span>发布人：-  </span>
					</div>
					<p><p>5月5日消息，周四公布的官方数据显示，欧盟本周批准出口软小麦305,000吨，为五周以来最高水平，2010/11年度(7月-次年6月)至今总计批准出口1680万吨。</p>
<p>欧盟本周批准出口软小麦305,000吨，为五周以来最高水平，2010/11年度(7月-次年6月)至今总计批准出口1680万吨。</p>
<p>2010/11年度至今批准出口总量远高于上年度同期的1500万吨。</p>
<p>因俄罗斯等小麦主产国产量不佳，且一些进口国担心食品通胀及国内动乱而进口小麦，对欧盟小麦的需求，尤其是对优质法国小麦的需求强劲。</p>
<p>数据显示，本周批准出口总量中包含297,710吨法国小麦。</p>
<p>欧盟本周批准出口总量128,000吨的大麦，其中德国大麦89,275吨，法国大麦32,791吨。2010/11年度以来，欧盟大麦出口总量为420万吨，高于上年同期的878,000吨。</p>
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