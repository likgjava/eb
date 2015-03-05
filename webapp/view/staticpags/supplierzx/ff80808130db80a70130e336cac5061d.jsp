<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>强生声明称霉味药品未进入中国市场- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130e336cac5061d.jsp" title="强生声明称霉味药品未进入中国市场" class="cmsHref_self">强生声明称霉味药品未进入中国市场</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>强生声明称霉味药品未进入中国市场</h1>
					<div class="source">
						<span>发布时间：2011-07-01</span>
						<span>发布人：-  </span>
					</div>
					<p><P>6月30日上午消息，强生中国发表声明，称最新的药品召回事件并不涉及中国市场，因为这些药品并没有进入中国。</P>
<P>本周二，美国强生宣布，超强止偏头痛药锭“特效泰诺”因药品存在发霉味道，公司将召回6.09万瓶该类药品。美国麦克尼尔公司在声明中则称，此次召回的药品并非因消费者产生不良反应而采取的行动，而是由于收到少数消费者报告药品气味问题做出的主动召回。</P>
<P>强生中国区称中国消费者目前使用的非处方药品均是由上海强生制药有限公司在上海工厂生产，完全符合中国药品生产质量管理规范(GMP)，质量安全可靠，不存在国外召回的问题，中国消费者可以放心使用。</P>
<P>这次问题又是发生在强生下属公司麦克尼尔，强生中国区新闻发言人吕晶就此回应，称这的确是一个老问题了。</P>
<P>自2009年起，麦克尼尔因药品质量问题先后11次发起召回，其中涉及多种婴幼儿药品。有媒体报道认为，这和2006年强生将辉瑞旗下的消费者保健业务并入麦克尼尔大有关系。<BR><BR>以下为声明原文：</P>
<P>关于美国麦克尼尔公司主动召回特定批次非处方药的声明</P>
<P>美国麦克尼尔公司日前发表声明，由于收到主动召回在美国销售的一个批次的瓶装TYLENOL® Extra Strength Caplets 225 count。</P>
<P>上述召回不是基于消费者产生不良反应而采取的行动， 且均没有进入中国市场。</P>
<P>中国消费者目前使用的非处方药品均是由上海强生制药有限公司在上海工厂生产，完全符合中国药品生产质量管理规范(GMP)，质量安全可靠，不存在国外召回的问题，中国消费者可以放心使用。</P>
<P>上海强生制药有限公司</P>
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