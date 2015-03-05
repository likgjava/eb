<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>英特尔联手比亚迪共推中国新能源及智能汽车- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7e456446e70.jsp" title="英特尔联手比亚迪共推中国新能源及智能汽车" class="cmsHref_self">英特尔联手比亚迪共推中国新能源及智能汽车</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>英特尔联手比亚迪共推中国新能源及智能汽车</h1>
					<div class="source">
						<span>发布时间：2011-07-05</span>
						<span>发布人：-中国新闻网  </span>
					</div>
					<p><P>7月4日电 英特尔(中国)有限公司4日和比亚迪公司共同启动“信息科技加速创新”活动，双方将在新能源汽车及零部件和智能汽车等领域，通过先进信息科技的应用，加速创新步伐，引领汽车电子产业从“中国制造”向“中国创造”加速发展。</P>
<P>据悉，双方将在国内10个产业园区陆续开展“信息科技加速创新”项目，借助英特尔在智能汽车设计及数字化制造能力方面的信息技术支撑，加速汽车制造的创新步伐。</P>
<P>比亚迪相关人士在启动仪式上表示，英特尔在智能计算和汽车电子领域的经验，配合比亚迪电动车独有的i系统，将实现电动车行业内的又一创新发展。</P>
<P>中国机械工业联合会此前提出“绿色为先”的机械工业发展战略，绿色与节能已成为推动汽车工业发展的两大重要主题。2003年正式进军汽车行业的比亚迪，以原有的工业基础和创新精神，已经发展成为最具自主创新能力的中国汽车品牌之一。</P>
<P>此次双方的合作内容，包括用于计算机辅助设计、分析、仿真的英特尔至强服务器产品和第二代智能酷睿处理器家族、用于汽车智能娱乐系统的英特尔凌动处理器平台，以及优秀的英特尔软件和服务团队与比亚迪在新能源汽车领域的优势相结合，全面提升比亚迪绿色节能汽车的智能化水平。同时，将邀请相关零部件供应链合作伙伴，探讨如何在信息技术的强力支撑下更好地设计出新能源汽车和智能汽车产品，带动产业链创新能力的快速提高。</P>
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