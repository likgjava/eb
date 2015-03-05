<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>互助县积极推行政府采购管办分离改革- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/supplierzx.jsp" title="供应商资讯" class="cmsHref_self">供应商资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130b9f54b78014c.jsp" title="互助县积极推行政府采购管办分离改革" class="cmsHref_self">互助县积极推行政府采购管办分离改革</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>互助县积极推行政府采购管办分离改革</h1>
					<div class="source">
						<span>发布时间：2011-06-23</span>
						<span>发布人：-青海新闻网  </span>
					</div>
					<p><P>为进一步规范对政府采购工作的管理，加强对采购过程的监督，互助县实行了政府采购管办分离改革，取得了初步成效。</P>
<P>根据省、地关于进一步加强政府采购监督管理工作的文件精神，我县积极开展政府采购管办分离改革，增设了政府采购管理办公室，承担县级政府采购计划下达、采购监管、采购资金拨付等职能，从而彻底改变了以前政府采购中心包办一切的现象，这对加强政府采购工作监督，确保政府采购工作有序合法、廉洁开展起到了非常重要的作用。目前，采购管理办公室已确定了职责分工，明确了工作程序，从6月份开始所有采购活动均在采购办公室的监督下有序开展。采购管理办公室将通过进一步建立健全各种规章制度，稳步推行政府采购预算，建立专家库和供应商库等措施，全面推进政府采购管办分离改革向纵深开展。</P>
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