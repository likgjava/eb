<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>巢湖首批招标采购监督员持证上岗- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd2a79abe07c5.jsp" title="巢湖首批招标采购监督员持证上岗" class="cmsHref_self">巢湖首批招标采购监督员持证上岗</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>巢湖首批招标采购监督员持证上岗</h1>
					<div class="source">
						<span>发布时间：2011-05-09</span>
						<span>发布人：-  </span>
					</div>
					<p><p>2011年度招标采购特邀监督员聘任会于5月6日举行，巢湖首批30名招投标特邀社会监督员正式持证上岗。</p>
<p>据了解，这30名招投标特邀社会监督员是经过市招管局会同市监察部门，从市人大代表、市政协委员和市政风行风监督员及相关行政主管部门的专业技术人员中选出。根据《巢湖市招标采购特邀监督员工作暂行办法》,在两年的任期内，他们将对招标采购项目的前期准备、文件编制、信息发布、合同签订等情况进行全程检查，对开标、评标活动进行现场监督，独立负责地提出监督意见。对招标人、投标人、招标代理机构、评标委员会在项目操作过程中存在的不规范行为，及时向市招管局提出纠正意见，对严重违规的向市纪检监察机关反映。同时，他们还将协助并监督市招管局对招标采购活动中违法违规行为或相关投诉举报进行查处，并将结合监督工作开展调研，向市委市政府或市招管局、监察局、住建委、财政局等相关部门提出加强招标采购管理工作的意见建议。</p>
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