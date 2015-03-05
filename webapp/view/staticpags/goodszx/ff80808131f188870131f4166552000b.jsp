<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>惠普18个月内退出个人电脑市场- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/goodszx.jsp" title="供货资讯" class="cmsHref_self">供货资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131f188870131f4166552000b.jsp" title="惠普18个月内退出个人电脑市场" class="cmsHref_self">惠普18个月内退出个人电脑市场</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>惠普18个月内退出个人电脑市场</h1>
					<div class="source">
						<span>发布时间：2011-08-23</span>
						<span>发布人：-  </span>
					</div>
					<p><p>全球电脑市场正在面临一场大的震荡：惠普准备分拆或出售个人电脑业务，而其目前仍然是全球销量第一的电脑厂商。人们不禁要问：谁打败了惠普?</p>
<p>全球最大个人电脑厂商惠普最近宣布了惊人战略转型计划，包括“全部或部分分拆”个人电脑业务，并将放弃起步不久的平板计算机和智能手机业务。按照计划，今年10月开始停产TouchPad平板电脑及品牌智能手机，考虑于12至18个月内分拆或出售个人电脑业务，这意味着到明年年底前后，人们或许在市场上看不到惠普公司的电脑了。</p>
<p>这个消息对于全球电脑业界绝对是个震撼性的消息，因为就在目前，惠普公司仍然是全球销量最大的电脑厂商。来自IDC的统计数据显示，去年惠普的电脑出货超过6400万台，市场占有率为18.5%。</p>
<p>智能手机和平板电脑正在侵蚀传统的台式电脑和笔记本电脑的市场份额，这些产品已经没人需要了吗?一位用户发表评论说：“两个类型吧，当你躺在床上，就想上上网，看看片子时，无疑iPad是最好的选择，平时工作，还是笔记本好。”</p>
<p>昨天记者在中关村卖场看到，在销售电脑及手机的店铺中，将近8成左右都在销售苹果公司的iPhone 4手机或者iPad 2 
平板电脑，有人告诉店员，这些产品就是用来送礼的。</p> 
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