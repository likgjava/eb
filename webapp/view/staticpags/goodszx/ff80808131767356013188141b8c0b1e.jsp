<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>2011年上半年吹塑机出口增幅大于进口- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131767356013188141b8c0b1e.jsp" title="2011年上半年吹塑机出口增幅大于进口" class="cmsHref_self">2011年上半年吹塑机出口增幅大于进口</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>2011年上半年吹塑机出口增幅大于进口</h1>
					<div class="source">
						<span>发布时间：2011-08-02</span>
						<span>发布人：-雅式工业专网  </span>
					</div>
					<p><P>据中国海关统计，2011年6月，中国出口吹塑机(海关商品编号：847730)629台，同比减少9.1%;出口金额1161万美元，同比增长13.4%。同期，中国进口吹塑机25台，同比增长13.6%;进口金额1294万美元，同比减少61%。</P>
<P>上半年，中国累计出口吹塑机3458台，进口154台，金额分别为6774万美元和1.3亿美元，累计逆差约为6558万美元。</P>
<P>就细分类别来看，吹塑机分为挤出吹塑机、注射吹塑机、其他吹塑机三个类别。上半年，挤出吹塑机出口789台，金额2146万美元，同期进口52台，金额4165万美元;出口注射吹塑机233台，金额618万美元，进口18台，金额963万美元;出口其他吹塑机2436台，金额4009万美元，进口84台，金额8204万美元。</P>
<P>注：本次统计包括：挤出吹塑机、注塑吹塑机、其他吹塑机。</P>
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