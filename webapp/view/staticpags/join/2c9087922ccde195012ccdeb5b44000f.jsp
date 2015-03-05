<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>成为咨询专家- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/join/join.jsp" title="加入我们" class="cmsHref_self">加入我们</a>
	<a href="javascript:void(0)" id="/view/staticpags/join/2c9087922ccde195012ccdeb5b44000f.jsp" title="成为咨询专家" class="cmsHref_self">成为咨询专家</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="402885ef2c14c39e012c14e3ff060080" />
				<div class="frameNews">
					<h1>成为咨询专家</h1>
					<div class="source">
						<span>发布时间：2010-12-10</span>
						<span>发布人：-  </span>
					</div>
					<p><p>您在某个领域有独到的视角，有丰富的采购经验，有丰富的施工经验，有更多一线实施或者施工的经验，无论是哪个方面的特长，都来加入我们吧，阳光易购平台为您提供展示个人才能，发挥个人特长的平台。加入我们您可以享受如下的服务：</p>

<p>1.免费注册成为专家：平台提供免费的专家注册，审核通过后，您可以成为我们平台的专家；</p>

<p>2.有偿咨询服务：成为平台的专家后，采购单位根据自己的需要会联系您，有偿为采购单位提供咨询服务；</p>

<p>3.扩展人脉关系：根据您咨询的经验，得到采购单位的认可，您可以有更多的机会为采购单位所熟悉，为更多的采购单位提供咨询服务。</p>

<p style="text-align: center">快来加入我们吧，发挥您的特长！</p>
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