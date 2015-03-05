<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>用户验证机制介绍- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/comments/comments.jsp" title="客户意见" class="cmsHref_self">客户意见</a>
	<a href="javascript:void(0)" id="/view/staticpags/comments/402885ef2c14c39e012c14f734ee00fa.jsp" title="用户验证机制介绍" class="cmsHref_self">用户验证机制介绍</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="402885ef2c14c39e012c14e4c709008b" />
				<div class="frameNews">
					<h1>用户验证机制介绍</h1>
					<div class="source">
						<span>发布时间：2010-11-04</span>
						<span>发布人：-  </span>
					</div>
					<p><p><strong>用户名和密码验证机制</strong></p>

<p>用户名唯一性：在用户注册时提供用户唯一性验证；</p>

<p>用户密码：用户密码加密处理，提供密码修改功能。</p>

<p><strong>机构信息验证机制</strong></p>

<p>采购人和供应商注册后，需要进行机构信息验证；</p>

<p>登录后，需要提交机构信息，提交系统管理员进行审核；</p>

<p>审核后机构信息方可生效。</p>

<p><strong>角色信息验证机制</strong></p>

<p>系统提供四类角色信息，角色可以同时存在；</p>

<p>用一类角色注册后，可以申请增加另一类角色；</p>

<p>供应商、采购人角色需要进行机构信息审核；</p>

<p>咨询专家角色需要进行个人信息审核。</p>
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