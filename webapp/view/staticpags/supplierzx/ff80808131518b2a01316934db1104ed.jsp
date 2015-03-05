<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>卫浴市场竞争悄然改变 方式逐渐明朗- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316934db1104ed.jsp" title="卫浴市场竞争悄然改变 方式逐渐明朗" class="cmsHref_self">卫浴市场竞争悄然改变 方式逐渐明朗</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>卫浴市场竞争悄然改变 方式逐渐明朗</h1>
					<div class="source">
						<span>发布时间：2011-07-27</span>
						<span>发布人：-机电在线  </span>
					</div>
					<p><P>竞争也不断加剧，我国现代卫浴发展迄今20多年，卫浴产品由最初的满足功能性，到现在满足主人品位需要、文化需要;卫浴购买由少数人的奢侈品到现在县乡级市场的普及。随着卫浴市场的逐渐成熟，卫浴产品也由最初的在档次上的大群体区隔，到现在以性别因素细分市场;从产品仅仅是满足共性需求，到现在满足个性化求美、求新需求。卫浴市场细分开始细化，竞争也由产品竞争、价格竞争转向定位竞争、市场细分竞争。</P>
<P>浴室是居家空间不可缺少的一部分，但对其需求最强烈的莫过于注重沐浴享受的女性，女性对卫浴的选购要求也大大高过男性，如何从女性的角度出发，研发设计出满足女性审美需要的卫浴产品成为商家新的市场机会，2009年一些女性主张的浴室空间频频受到女性消费者的热捧，成为2009年卫浴的一道亮点。</P>
<P>在位于中国陶瓷产业总部基地的全友卫浴总部展厅，笔者看到令人惊艳的粉红色浴室柜，最合适营造女性天性爱粉色的浪漫浴室空间;蕴含高贵气质的紫色浴室柜，在玫瑰花的点缀下更是极大限度的满足现代都市女性求美、求新的消费潮流。</P>
<P>不仅是全友卫浴开始注重女性需求的设计，东鹏陶瓷“花样年华”系列，大唐合盛“九品女人”系列，也都给人呈现出“她”时代的到来。</P>
<P>这是一种趋势，除了按性别因素进入市场细分中的子市场外，随着卫浴市场的更加成熟，未来通过职业因素、个性因素、人口因素，甚至是民族因素进入越来越细的市场细分未尝不是没有可能的，这样才能抓住不同的卫浴消费群体。</P>
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