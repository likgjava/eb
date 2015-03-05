<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>索尼新索双拳出击 逐鹿BD光盘市场- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013182f08927010f.jsp" title="索尼新索双拳出击 逐鹿BD光盘市场" class="cmsHref_self">索尼新索双拳出击 逐鹿BD光盘市场</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>索尼新索双拳出击 逐鹿BD光盘市场</h1>
					<div class="source">
						<span>发布时间：2011-08-01</span>
						<span>发布人：-北京晨报  </span>
					</div>
					<p><P>近年来Blu-ray市场发生了巨大变化，BD产品在中国市场的普及也开始明显加速。据卖场工作人员介绍，目前索尼的BD产品非常受消费者欢迎，其产品具有3秒疾速开机、支持RMVB格式、低耗节能及纤薄设计等多种优势功能，能够为用户提供高水准的视听体验;同时卖场工作人员也提到目前消费者对BD产品关注度不断提高的同时，对于片源少片源贵的担忧也日渐显现。</P>
<P>索尼作为该产业的龙头企业之一，同时也是Blu-ray光盘协会的主要成员，为解决片源难、片源贵的问题，此次特别与新索Blu-Ray光盘零售平台(www.1080T.com)举办联合促销活动：在2011年8月31日前，消费者只需购买索尼Blu-ray/DVD播放器/家庭影院产品，即可马上获赠Blu-ray大片光碟，更可以索尼专区会员身份以优惠价购买多种Blu-ray光碟。索尼本次推出的重磅优惠，不仅为BD爱好者提供了大量优质的BD片源，同时价格却只有市场价格的60%左右，对于广大高清爱好者来说无疑是个好消息。</P>
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