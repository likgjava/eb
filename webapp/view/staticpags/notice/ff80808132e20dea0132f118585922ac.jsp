<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>会议酒店预订公告- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/notice/notice.jsp" title="网站公告" class="cmsHref_self">网站公告</a>
	<a href="javascript:void(0)" id="/view/staticpags/notice/ff80808132e20dea0132f118585922ac.jsp" title="会议酒店预订公告" class="cmsHref_self">会议酒店预订公告</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="402883d429a14e050129a1871ed1044c" />
				<div class="frameNews">
					<h1>会议酒店预订公告</h1>
					<div class="source">
						<span>发布时间：2011-10-11</span>
						<span>发布人：-  </span>
					</div>
						<p><img src="/cms/newscontent/contentupload/image/2011-10/8c39d58a_4407_4758_9a3e_55f1097ccc12.jpg"></p>
					<p><div><font size="4">本公司需要定会议酒店</font></div><span style="font-size: large; ">要求：在北京周边1个小时行程，酒店星级不限，500平米的多功能厅的酒店,内部要求高度4米以上。</span><br><span style="font-size: large; ">会议时间：2011年11月14-16日（14日搭建，15、16会议两天），15，16两天会议需要午餐，不住宿。</span><br><div><font size="4">请满足要求的酒店联系。</font></div><span style="font-size: large; ">联系人：张女士</span><br><div><font size="4">联系电话：68357702-8668</font></div> 
 
 
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