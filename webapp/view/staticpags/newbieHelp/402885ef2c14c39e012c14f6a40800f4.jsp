<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>如何注册和身份验证- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/newbieHelp/newbieHelp.jsp" title="新手帮助" class="cmsHref_self">新手帮助</a>
	<a href="javascript:void(0)" id="/view/staticpags/newbieHelp/402885ef2c14c39e012c14f6a40800f4.jsp" title="如何注册和身份验证" class="cmsHref_self">如何注册和身份验证</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="402885ef2c14c39e012c14e4692a0084" />
				<div class="frameNews">
					<h1>如何注册和身份验证</h1>
					<div class="source">
						<span>发布时间：2010-11-04</span>
						<span>发布人：-  </span>
					</div>
					<p><p><strong>如何进行注册</strong></p>

<p>注册很简单！</p>

<p>1.点击首页顶部左侧的&ldquo;注册&rdquo;；</p>

<p>2.系统弹出的页面，让您选择您要注册的身份，&ldquo;供应商&rdquo;、&ldquo;采购人&rdquo;、&ldquo;推广人&rdquo;、&ldquo;咨询专家&rdquo;，请选择其中一种身份；</p>

<p>3.系统弹出用户注册的页面，请按照页面准确填写自己的信息就可以了，提交；</p>

<p>4.系统提示注册成功，返回首页，恭喜您，注册成功了！</p>

<p>友情提示：</p>

<p>1.如果我想又做供应商又做采购人，又做推广<span style="color: rgb(0,0,0); font-size: 13px">人</span>，怎么办呢，没关系，您选择任何一种身份注册，以后还可以申请其他角色的。</p>

<p>2.注册成功后，可以在首页登录，点击右上角&ldquo;我的商务室&rdquo;，维护自己的信息；其中采购人、供应商和咨询专家是需要系统评审的，请您耐心等候系统的评审，我们会第一时间就您的信息进行审查，并进行邮件提醒。</p>

<p><strong>&nbsp;如何进行身份认证</strong></p>

<p>您点击首页左上角的&ldquo;登录&rdquo;，输入注册时设定的用户名和密码，点击登录即可，系统会就您申请的角色对您进行身份认证。</p>
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