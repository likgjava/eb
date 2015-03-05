<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>政府采购标准化建设应从三方面着手- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="采购焦点资讯" class="cmsHref_self">采购焦点资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd79135870c9a.jsp" title="政府采购标准化建设应从三方面着手" class="cmsHref_self">政府采购标准化建设应从三方面着手</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>政府采购标准化建设应从三方面着手</h1>
					<div class="source">
						<span>发布时间：2011-05-10</span>
						<span>发布人：-  </span>
					</div>
					<p><p>&ldquo;在政府采购标准化建设的命题上，韩国从电子化建设入手、美国以法制化为突破口、台湾则建立了供应商资格审查标准。因此，中国的政府采购标准化建设也应科学制定，强制推行。&rdquo;在日前举行的&ldquo;中国政府采购标准化建设业务研讨&rdquo;活动上，国际关系学院院长刘慧提出了关于我国政府采购标准化建设的建议。</p>
<p>刘慧表示，政府部门和政府采购监管机构是标准化推行的权威性机构，只有共同推进，这项工作才可能做好。为此，她提出政府采购标准化建设应从以下3个方面着手：</p>
<p>一是由专业机构研究，科学制定标准。应当确保标准制定的质量，确保其经过了科学、严肃的制定过程。美国在制定产品安全标准时十分详细和专业，并形成监管权威。</p>
<p>二是要用法律的程序规范，强制推广执行。标准的制定和执行是一个通过有关机构，特别是权威机构推广、发布的过程，它的法律程序和合法性非常重要。</p>
<p>三是政府采购的标准化应由政府协调治理，综合配套执行。当政府采购的法律法规还不够完备时，需要政府采购部门的执行机构发挥主动性制定标准。</p>
<p>不规范采购案例屡被曝光、政府采购改革已进入关键时期。</p>
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