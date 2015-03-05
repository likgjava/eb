<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中色南方已开建国内最大稀土分离厂- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fc2e541b700e6.jsp" title="中色南方已开建国内最大稀土分离厂" class="cmsHref_self">中色南方已开建国内最大稀土分离厂</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>中色南方已开建国内最大稀土分离厂</h1>
					<div class="source">
						<span>发布时间：2011-05-06</span>
						<span>发布人：-新京报  </span>
					</div>
					<p><p>中国有色集团近期已开建国内最大的稀土分离厂&mdash;&mdash;中色南方稀土(新丰)有限公司(下称中色南方)，预计2013年投产，产能7000吨/年。昨日，中色集团总经理罗涛接受记者采访时介绍上述项目，他还表示，目前稀土价格已经&ldquo;畸高&rdquo;，稀土产业需回归理性。</p>
<p>据了解，中色南方稀土将在韶关新丰县开建。建设周期约3年，计划投入4个多亿。罗涛说，当前稀土价格已经比一年前涨了10倍。但他对于目前稀土的情况并不表示乐观。&ldquo;价格高得离谱了。&rdquo;他认为，现在因为炒作因素将价格炒得过高，不利于产业发展。</p>
<p>罗涛告诉记者，目前，稀土的采矿、分离、应用三个环节，民营企业占据了采矿和分离的绝大部分，在采矿环节无一家为国企，而稀土分离环节真正实现生产的国企也仅有中色的珠江稀土冶炼厂，其余均为民营。</p>
<p>按照发改委、工信部和国土资源部共同制定的2011年稀有金属指令性生产计划，2011年我国对稀土的开采总量控制为9.38万吨，其中大部分稀土生产省份开采配额都不及万吨。</p>
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