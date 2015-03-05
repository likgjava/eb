<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>东芝大举并购 核电危机下的转型之举- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308bc00aba12fd.jsp" title="东芝大举并购 核电危机下的转型之举" class="cmsHref_self">东芝大举并购 核电危机下的转型之举</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>东芝大举并购 核电危机下的转型之举</h1>
					<div class="source">
						<span>发布时间：2011-06-14</span>
						<span>发布人：-  </span>
					</div>
					<p><p>5月中下旬，一周之内，东芝接连击出几记重拳：5月19日，宣布斥资1900亿日元(约合23亿美元)全资收购了瑞士智能电表制造商Landis Gyr;23日，对韩国风电设备公司Unison进行战略投资;5月24日，东芝宣称，未来3年内将向环境和能源领域投资7000亿日元(约合85.7亿美元)。</p>
<p>这是否意味着在核电危机下，全球核电领域领先厂家东芝开始考虑转型问题呢?</p>
<p>新能源领域投资</p>
<p>据对东芝株式会社执行役社长佐佐木则夫的采访获悉，东芝对核电的态度并未改变，现在东芝首先关注各国核电政策会如何变化。所幸的是，此前接到订单的国家，比如中国、美国等，发展核电的基本态度没有改变;东芝正在洽谈的国家，比如英国、芬兰、立陶宛、越南、土耳其等国，核电政策的基本态度也没变。</p>
<p>当然，福岛事故发生之后，各国会将此次事故的经验纳入新的安全标准中去，核电的发展可能会稍有回落。</p>
<p>对于新能源领域的投入，东芝是想涉足所有不同的能源领域，从而达到能响应各国不同需求。即使是在发展核电的国家里，也有反对核电的人士，他们希望大力推进可再生能源，东芝要对这些人士的呼声做出应答。东芝在水力发电、太阳能发电、风力发电、地热发电等方面都有涉足。能源是一个国家工业竞争力的源泉。如果能源成本高，则利用该能源生产的产品，竞争力就会降低，一个国家能源政策的制定必须综合考虑。</p>
<p>为何收购兰吉尔</p>
<p>东芝现在努力推进智能电网、智能社区事业。我们有一种称做&ldquo;功率控制单元&rdquo;的硬件设施，该设施在家庭中、楼宇中、工厂中的应用已有固定模式，而能使其能与电网结合一体的技术，就是智能电表和使用智能媒体的信息系统。&ldquo;智能社区&rdquo;最基础的电量正是通过智能电表实时反映并实现电网和用户的相互反馈，进而可以实现对电的掌控，智能电表在这里相当于&ldquo;接点&rdquo;，是非常重要的关键部件。</p>
<p>兰吉尔(Landis Gyr)就是提供智能电表的专门公司，在世界30个国家开展业务，行业内排名世界第一，今后，将成为东芝开展智能社区业务的关键性组成部分，这是收购的原因。</p>
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