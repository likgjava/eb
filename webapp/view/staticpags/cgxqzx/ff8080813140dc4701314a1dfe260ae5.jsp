<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>雨润农产品全球采购中心开建- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc4701314a1dfe260ae5.jsp" title="雨润农产品全球采购中心开建" class="cmsHref_self">雨润农产品全球采购中心开建</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>雨润农产品全球采购中心开建</h1>
					<div class="source">
						<span>发布时间：2011-07-21</span>
						<span>发布人：-中国产经新闻报  </span>
					</div>
					<p><P>日前，由江苏雨润集团投资建设的雨润农产品全球采购中心项目，在石家庄鹿泉市寺家庄村开工建设，计划总投资102.09亿元，总建筑面积256.65万平方米，目前一期已经准备就绪。</P>
<P>石家庄雨润农产品全球采购中心是河北省重点项目，由江苏雨润集团投资建设，总投资102.09亿元，总建筑面积256.65万平方米。项目集农副产品展示展销、批发交易、电子拍卖、仓储冷藏、加工配送、检验检测以及配套服务等功能于一体。建成运营后可直接提供就业岗位2万个，年可实现交易额200亿-250亿元、税收10亿-15亿元，将对于提升河北省的农产品流通水平，打造辐射华北地区的国际化采购平台，保障市场供应和食品安全，有十分重要的意义。</P>
<P>目前，已经准备就绪，具备进场施工条件，一期项目总投资45亿元，建筑面积78.2万平方米，项目分三期建设，预计2016年3月竣工。</P>
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