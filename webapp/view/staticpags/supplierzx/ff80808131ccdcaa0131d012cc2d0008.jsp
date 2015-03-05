<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>诺基亚否认遭遇渠道商集体抵制- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131ccdcaa0131d012cc2d0008.jsp" title="诺基亚否认遭遇渠道商集体抵制" class="cmsHref_self">诺基亚否认遭遇渠道商集体抵制</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>诺基亚否认遭遇渠道商集体抵制</h1>
					<div class="source">
						<span>发布时间：2011-08-16</span>
						<span>发布人：-  </span>
					</div>
					<p><P>昨天(15日)，针对业内关于大批国内代理商拒绝从诺基亚进货的传言，诺基亚中国公司表示情况并不属实，诺基亚渠道的健康状况良好。而有国内渠道商也告诉记者，诺基亚的渠道情况并没有那么糟糕。</P>
<P>昨天，业内有消息称，因为产品吸引力下滑，库存积压严重，从今年4月开始，国内各地代理商有史以来第一次集体对诺基亚“说不”，拒绝再从诺基亚进货，诺基亚在中国市场陷入史无前例的渠道崩盘。“少的赔几十万，多的赔几百万，几乎找不出几家是挣钱的”。有媒体引用诺基亚内部人士的话如此形容诺基亚代理商们目前的处境。</P>
<P>对此，诺基亚官方昨天发布声明予以反驳，“所谓中国的经销商拒绝进货诺基亚手机是不实的。事实上，我们对最近和经销商及零售商之间的良好合作的进展表示满意，我们的销售合作伙伴对与诺基亚的合作充满信心，总体库存已回到正常水平，渠道的健康状况良好。”</P>
<P>某国内大型手机连锁卖场高层告诉记者，诺基亚在渠道上的状况没有传说中那么严重。虽然在高端智能机上，诺基亚份额不断下滑，但在中低端市场上，诺基亚还是很有市场号召力的，其产品也是很受渠道商欢迎的。另外，该卖场负责人还透露，近来诺基亚在面对渠道商的态度上变得比以往谦和了很多，压货的情况也比以前好了很多，应该不会出现大批渠道商抵制的情况。</P>
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