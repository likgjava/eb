<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>全球大豆或供不应求 对南美供应依赖增强- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130bf34b60130bf50ce87009a.jsp" title="全球大豆或供不应求 对南美供应依赖增强" class="cmsHref_self">全球大豆或供不应求 对南美供应依赖增强</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>全球大豆或供不应求 对南美供应依赖增强</h1>
					<div class="source">
						<span>发布时间：2011-06-24</span>
						<span>发布人：-  </span>
					</div>
					<p><P>总部位于汉堡的行业刊物《油世界》周二称，2011/12年度全球大豆消费或较产量高出400万吨左右，美国产量下降令市场对南美供应的依赖增强。</P>
<P>《油世界》预计，2011/12年度全球大豆产量或将达到2.6550亿吨，而大豆消费预计为2.697亿吨。</P>
<P>该杂志并预计，2010/11年度全球大豆产量料在2.627亿吨，消费料为2.5772亿吨。</P>
<P>《油世界》称，2011/12年度美国大豆产量可能从2010/11年度的9061万吨降至8850万吨。</P>
<P>美国大豆减产或导致该国2012年8月末库存大幅降至410万吨，2011年8月末库存料为560万吨，《油世界》称。</P>
<P>“北半球其他国家不能弥补美国大豆减产的缺口。而中国大豆产量或连续第三年下降，可能从去年的1420万吨减至1350万吨。”</P>
<P>北半球供应紧张可能意味着南美大豆种植面积的扩大。</P>
<P>《油世界》表示：“我们预计在2011/12年度及之后的年度内，南美种植面积将大幅扩大，产量亦将大幅增加。”</P>
<P>《油世界》初步预测，2012年初巴西大豆产量或增至7350万吨，今年产量已达创纪录的7300万吨。</P>
<P>2012年阿根廷大豆产量料从今年的4920万吨增至5300万吨。</P>
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