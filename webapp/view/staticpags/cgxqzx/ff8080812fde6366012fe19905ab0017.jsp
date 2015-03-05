<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>蔬菜供应商采购1万公斤包心菜- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fde6366012fe19905ab0017.jsp" title="蔬菜供应商采购1万公斤包心菜" class="cmsHref_self">蔬菜供应商采购1万公斤包心菜</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>蔬菜供应商采购1万公斤包心菜</h1>
					<div class="source">
						<span>发布时间：2011-05-12</span>
						<span>发布人：-  </span>
					</div>
					<p><p>滨海菜农的卖菜难问题再一次得到缓解。昨天上午，在绍兴县教育服务中心副主任金国琴的带领下，4家学校食堂蔬菜定点配送供应商，冒着细雨来到滨海飞跃闸村，收割、采购当地村民滞销的新鲜包心菜1万公斤。他们计划下周再采购一次。绍兴县教育服务中心昨天还发函，倡导学校一周吃一次&ldquo;爱心菜&rdquo;。</p>
<p>本报前段时间连续报道的绍兴县滨海菜农蔬菜滞销一事，引起了社会的极大关注，绍兴县教育体育局局长许义平得知此消息后，当即要求该县教育服务中心主动联系菜农，把部分滨海种菜大户滞销的新鲜包心菜配送给学校食堂。昨天上午，在绍兴县教育服务中心的牵线下，绍兴县学校食堂的4家蔬菜定点配送供应商共40人一起到滨海收割包心菜。</p>
<p>由滨海菜农组成的永丰蔬菜专业合作社负责人吴浙宁告诉记者，他们今年有30亩包心菜，总产7.5万公斤。&ldquo;看着这些菜销不出去就要烂掉了，我们正着急呢，没想到绍兴县教育部门牵线搭桥雪中送炭来了。</p>
<p>据悉，昨天参与采购的4家供应商采购的包心菜，包括绿油油蔬菜专业合作社5000公斤，宏大蔬菜瓜果专业合作社2500公斤，钱兴蔬菜专业合作社1500公斤，信盛蔬菜专业合作社1000公斤，均以市场价收购。</p>
<p>记者还了解到，昨天绍兴县教育体育局还向各高中、初中、小学、幼儿园发了一份&ldquo;关于倡导学校采购爱心菜&rdquo;的函，建议每所学校在近期每周尽可能安排一餐&ldquo;爱心菜&rdquo;。</p>
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