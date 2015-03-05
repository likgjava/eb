<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>成为采购大使- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/join/2c9087922ccde195012ccdeb903f0011.jsp" title="成为采购大使" class="cmsHref_self">成为采购大使</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="402885ef2c14c39e012c14e3ff060080" />
				<div class="frameNews">
					<h1>成为采购大使</h1>
					<div class="source">
						<span>发布时间：2010-12-10</span>
						<span>发布人：-  </span>
					</div>
					<p><P>人人都可以注册使用阳光易购平台，人人都可以成为阳光易购的采购大使<SPAN style="FONT-SIZE: 13px; COLOR: rgb(0,0,0)"></SPAN>。通过自己的人脉，介绍采购人使用阳光易购平台，在帮助采购人免费使用平台、降低采购费用的同时，也能增加自己的收益。利人又利己，何乐而不为？</P>
<P>我们致力于将阳光易购平台打造成所有人的成功平台。</P>
<P>申请成为采购大使<SPAN style="FONT-SIZE: 13px; COLOR: rgb(0,0,0)"></SPAN>的三种大使身份之一，并提供采购单位信息（单位名称、采购需求、联系人、联系方式），得到采购人确认之后，可获得相应积分（诚信大使20分，爱心大使24分，友善大使28分）。采购人来阳光易购采购平台完成一次竞价采购，签订采购合同，采购大使即可获得500积分的奖励。此外，采购大使还可以获得采购人累积三年的交易额度0.1%提成奖励。每年我们将按照积分评选出前十名，授予“荣誉大使”称号，同时颁发荣誉证书和物质奖励。</P>
<P><SPAN style="FONT-SIZE: larger">快来加入我们吧！</SPAN></P>
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