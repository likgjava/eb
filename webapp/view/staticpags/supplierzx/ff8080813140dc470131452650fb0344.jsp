<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>耐克公司打败对手获得橄榄球联盟提供商- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc470131452650fb0344.jsp" title="耐克公司打败对手获得橄榄球联盟提供商" class="cmsHref_self">耐克公司打败对手获得橄榄球联盟提供商</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>耐克公司打败对手获得橄榄球联盟提供商</h1>
					<div class="source">
						<span>发布时间：2011-07-20</span>
						<span>发布人：-新浪网  </span>
					</div>
					<p><P>7月20日讯 Nike公司与全国足球联赛的产品合作已经是过去一年了，但公司为下一次合作的准备也已在有条不紊的进行中。</P>
<P>根据孟菲斯商业杂志报道，Nike——世界最大的运动鞋服品牌，已经在孟菲斯为400，000平方英尺的工业产权签署了一个为期5年的租赁合同，而只是将其作为一个鞋服装配送中心。</P>
<P>而上述做法使Nike在孟菲斯的厂房增加了260万平方英尺，波兰商业日报的一名记者写道。</P>
<P>在孟菲斯，Nike已经拥有三个配送中心，其中两个为公司所有，另一个是租赁的，Nike将这视为它的美国供应链的关键。它包括一个2008年开放的110万平方英尺的配送中心，另一个130万平方英尺的物流配送中心和一个812，697平方英尺的产品回收中心。</P>
<P>10月份，Nike公司打败了对手Reebok和UnderArmour，获得了从2012年开始独家为NFL(美国职业橄榄球联盟)提供服装的权利。</P>
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