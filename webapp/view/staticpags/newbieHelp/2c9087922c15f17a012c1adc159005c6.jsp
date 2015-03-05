<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>如何成为采购大使- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/newbieHelp/2c9087922c15f17a012c1adc159005c6.jsp" title="如何成为采购大使" class="cmsHref_self">如何成为采购大使</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="402885ef2c14c39e012c14e4692a0084" />
				<div class="frameNews">
					<h1>如何成为采购大使</h1>
					<div class="source">
						<span>发布时间：2010-11-05</span>
						<span>发布人：test-  </span>
					</div>
					<p><P><SPAN style="FONT-SIZE: 13px; COLOR: rgb(0,0,0)">凡是年满１８岁的中华人民共和国公民，登录本站并填写申请信息，经过申请验证真实有效，就可以成为本站的推广人。<BR>&nbsp;&nbsp; 一、已成功注册用户<BR></SPAN></P>
<P><SPAN style="FONT-SIZE: 13px; COLOR: rgb(0,0,0)">1.已经注册用户，点击页面顶部的“我要推广”</SPAN>；</P>
<P>2.从系统跳转的采购大使页面，点击“申请采购大使”；</P>
<P>3.跳转到采购大使信息补充页面上，补充身份证号码，点击“提交”。</P>
<P style="TEXT-ALIGN: center">恭喜您已经成为阳光易购采购平台的采购大使</P>
<P style="TEXT-ALIGN: left"><SPAN style="FONT-SIZE: 13px; COLOR: rgb(0,0,0)">二、未注册用户</SPAN></P>
<P>1.未注册用户，点击首页顶部的“我要推广”；</P>
<P>2.从系统跳转的采购大使页面，点击“注册采购大使”；</P>
<P>3.系统跳转相关的服务条款页面，请您仔细阅读条款后，如果同意，点击“同意”；</P>
<P>4.系统弹出采购大使注册信息页面，包括用户名、姓名、性别、身份证、联系信息等内容，请准确填写后，点击提交。</P>
<P style="TEXT-ALIGN: center">恭喜您已经成为阳光易购采购平台的采购大使</P>
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