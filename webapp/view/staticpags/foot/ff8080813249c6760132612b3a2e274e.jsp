<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>帮助中心- 中国权威的电子采购与招标第三方公共服务平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/web.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/skin07/css/seller.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/listDetail.css" />
<link type="text/css" rel="stylesheet" href="/cms/resbase/foot/help.css" />
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
			<div class="bottom_menu">
		<div class="menu">
			<h2>帮助中心</h2>
			<ul>
				<li class="current_li"><a href="#">网站注册</a></li>
				<li><a href="foot_register_cgr.jsp" target="_self">采购人常见问题</a></li>
				<li><a href="foot_register_gys.jsp" target="_self">供应商常见问题</a></li>
			</ul>
		</div><!--.menu-->
		
	
		<div class="content">
			<ul class="breadcrumb">
				<li><a href="">首页</a></li>
				<li><span class="song">&gt;</span>帮助中心</li>
			</ul>
			<div class="clearfloat"></div>
			<h1>网站注册常见问题</h1>
			<h3>一、如何注册成为阳光易购会员？</h3>
			<p>1、点击“左上角”或者“右上角”的免费注册。</p>
			<p><img src="http://www.ebid360.com/cms/resbase/foot//A1.jpg"></p>
		    <p>2、注册供应商或采购人：点击“供应商”或“采购人”。</p>
			<p><img src="http://www.ebid360.com/cms/resbase/foot//A2.jpg"></p>
			<p>3、填写“机构名称”、“机构代码”、“经营范围”、“用户名”、“密码”、“联系人”、“邮箱”、“联系电话”等，然后点击提交。</p>
			<p><img src="http://www.ebid360.com/cms/resbase/foot//A4.jpg"></p>
			<p><img src="http://www.ebid360.com/cms/resbase/foot//A4-1.jpg"></p>
			<p><img src="http://www.ebid360.com/cms/resbase/foot//A4-2.jpg"></p>
			<p>4、之后注册完成。然后点击“完善资料”进行企业信息完善，否则您注册的账号无法正常享受网站服务。</p>
			<p><img src="http://www.ebid360.com/cms/resbase/foot//A5.jpg"></p>
			<p>5、填写完整的企业信息，然后点击“提交”，以便我们更好的为您服务。</p>
			<p><img src="http://www.ebid360.com/cms/resbase/foot//A7.jpg"></p>
			<p><img src="http://www.ebid360.com/cms/resbase/foot//A7-4.jpg"></p>
			<p>6、恭喜您成为阳光易购电子采购平台会员!</p>
    

		
		</div><!--.content-->
	</div><!--.bottom_menu-->
 

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
