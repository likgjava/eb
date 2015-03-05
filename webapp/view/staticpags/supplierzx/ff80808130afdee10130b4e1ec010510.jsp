<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>雷诺称前五月全球汽车销量同比上涨4.2%- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130afdee10130b4e1ec010510.jsp" title="雷诺称前五月全球汽车销量同比上涨4.2%" class="cmsHref_self">雷诺称前五月全球汽车销量同比上涨4.2%</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>雷诺称前五月全球汽车销量同比上涨4.2%</h1>
					<div class="source">
						<span>发布时间：2011-06-22</span>
						<span>发布人：-  </span>
					</div>
					<p><P>据法国当地媒体网站zonebourse.com援引法国汽车制造商雷诺汽车公司(RenaultSA)负责销售、市场及轻型商务车业务的执行副总裁杰罗姆·斯托尔(JeromeSTOLL)的话报道称，2011年前5个月，得益于全球新兴市场对汽车需求的快速增长抵消了法国本土和欧洲其他国家市场需求的下滑，雷诺汽车公司全球汽车销量同比上涨4.2%。目前，此消息尚未经雷诺官方确认。</P>
<P>据了解，2011年前5个月，雷诺汽车公司在法国本土的汽车销量同比下滑了8%;其在欧洲市场的汽车销量同比下滑了4.7%;而该公司在全球其他地区的汽车销量同比上涨了22%。其中，该公司在土耳其和伊朗市场的汽车销量分别大幅上涨82%和78%。与此同时，其在巴西和俄罗斯市场的汽车销量也快速增长。</P>
<P>报道称，斯托尔认为，雷诺在欧洲市场的汽车销量下滑的原因是，法国购车激励政策结束和日本地震造成零部件供应不足。</P>
<P>据相关数据显示，2010年雷诺汽车公司的累计年销量为262万辆，预计到2013年，其累计年销量将增至300万辆。</P>
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