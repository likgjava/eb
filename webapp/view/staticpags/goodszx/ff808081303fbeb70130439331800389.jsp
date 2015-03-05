<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>欧盟官员拟赴朝评估粮食需求- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081303fbeb70130439331800389.jsp" title="欧盟官员拟赴朝评估粮食需求" class="cmsHref_self">欧盟官员拟赴朝评估粮食需求</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>欧盟官员拟赴朝评估粮食需求</h1>
					<div class="source">
						<span>发布时间：2011-05-31</span>
						<span>发布人：-  </span>
					</div>
					<p><p>韩国媒体29日报道，欧洲联盟打算于6月初派遣一支团队前往朝鲜，评估粮食供需现状。</p>
<p>韩国联合通讯社当天援引韩国政府消息源报道，欧盟官员打算下月初赴朝，在那里停留大约两个星期，实地走访一些地区，评估粮食需求。</p>
<p>美国国务院负责朝鲜人权事务的特别代表罗伯特&middot;金和美国国际开发署负责对外灾难援助的助理署长乔恩&middot;布劳斯28日结束对朝访问，带走被朝鲜扣留大约半年的美国公民全勇洙。这期间，金率领的代表团与朝方就恢复对朝粮食援助事宜展开磋商。</p>
<p>韩联社以一名未公开姓名韩国政府官员为消息源报道，美国方面将待欧盟完成评估朝鲜粮食需求后，再就是否恢复对朝粮食援助作决定。</p>
<p>联合国世界粮食计划署3月发布评估报告，认为朝方需要大约40万吨粮食援助，供应儿童和孕妇等群体。另外，美国&ldquo;萨玛利亚国际救援&rdquo;等5个民间组织的代表今年2月访朝，报告朝鲜去年冬天至今年春天严寒致农作物严重减产，粮食供给紧张。</p>
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