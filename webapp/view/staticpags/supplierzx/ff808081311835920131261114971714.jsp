<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>阿迪达斯供应商被指排放有毒废水- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081311835920131261114971714.jsp" title="阿迪达斯供应商被指排放有毒废水" class="cmsHref_self">阿迪达斯供应商被指排放有毒废水</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>阿迪达斯供应商被指排放有毒废水</h1>
					<div class="source">
						<span>发布时间：2011-07-14</span>
						<span>发布人：-  </span>
					</div>
					<p><P>有国际环保组织昨日在京发布报告，指责阿迪达斯、李宁、美特斯邦威、雅戈尔等服装品牌的两家供应商，将含有有毒有害物质的工业废水直接排入中国江河，对人体健康和生态系统会造成严重影响。</P>
<P>发布会上该组织称去年春天起，曾数次对位于宁波的雅戈尔纺织工业城和位于中山的中山国泰染整有限公司所排的废水进行取样，并将样本分别送至英国和荷兰的实验室进行检测。结果发现，废水中的主要有毒有害物质为壬基酚(NP)、全氟辛烷磺酸(PFOS)和全氟辛酸(PFOA)。这些物质干扰生物的内分泌，对生殖系统具有毒性，并对免疫系统和肝脏有影响。据了解，欧盟、美国已对上述有毒有害物质进行了管制，我国也于今年1月将壬基酚列入《中国严格限制进出口的有毒化学品目录》。</P>
<P>据称，与此次被查出排毒的两家纺织工厂存在供应关系的知名服装品牌包括阿迪达斯、CK、 Converse、H&amp;M、Lacoste、李宁、美特斯邦威、PVH、Puma和雅戈尔等14家著名品牌。</P>
<P>对于纺织工厂的排毒问题，宁波雅戈尔纺织工业城负责人明确对早报记者表示，会于今日给予公开答复。而雅戈尔宣传部一余姓经理对媒体表示，公司的废水排放符合国家标准，对上述环保报告持质疑态度。</P>
<P>早报记者昨天也分别致电上述部分品牌，但截稿时，除李宁表示将在数日内由专人回复这一问题外，阿迪达斯等均无回应。中山市环保局则在向早报记者索取材料后未予回应。</P>
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