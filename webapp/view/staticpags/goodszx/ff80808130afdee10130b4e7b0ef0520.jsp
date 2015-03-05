<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>零部件供应短缺 5月英国汽车销量有所下降- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130afdee10130b4e7b0ef0520.jsp" title="零部件供应短缺 5月英国汽车销量有所下降" class="cmsHref_self">零部件供应短缺 5月英国汽车销量有所下降</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>零部件供应短缺 5月英国汽车销量有所下降</h1>
					<div class="source">
						<span>发布时间：2011-06-22</span>
						<span>发布人：-中金在线  </span>
					</div>
					<p><P>据英国汽车制造商和交易商协会(SocietyofMotorManufacturersandTraders，简称SMMT)日前公布的数据显示，2011年5月份英国汽车产量同比下滑4.9%，至11万辆。2011年1-5月，英国汽车的累计产量同比上涨3.7%，至59.4万辆。</P>
<P>SMMT首席执行官艾沃特(PaulEveritt)表示：“尽管来自日本的零部件出现供应短缺状况，但是5月份英国的汽车产量仅下滑了4.9%。”</P>
<P>据了解，上个月英国生产的大部分汽车都面向出口市场。5月英国汽车出口总量为8.8万辆，占英国汽车总产量的80.7%，较去年同期上涨8.1%;而同期英国面向国内市场的汽车产量却较去年大幅下滑36.6%，至2.1万辆，占英国汽车总产量的19.3%。</P>
<P>有关数据显示，上月英国生产的11万辆汽车中，乘用车的产量约为9.9万辆，较去年同期下滑4.9%;而上月英国的商用车产量约为1.02万辆，较去年同期下滑5%。</P>
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