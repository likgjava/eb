<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国最大液晶玻璃基板项目将在深圳投产- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130c14db40130ceaa05550182.jsp" title="中国最大液晶玻璃基板项目将在深圳投产" class="cmsHref_self">中国最大液晶玻璃基板项目将在深圳投产</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>中国最大液晶玻璃基板项目将在深圳投产</h1>
					<div class="source">
						<span>发布时间：2011-06-27</span>
						<span>发布人：-深圳新闻网  </span>
					</div>
					<p><P>中国尺寸最大的液晶玻璃基板将于明年在光明新区投产。这是昨天上午从旭硝子第8.5代TFT-LCD玻璃基板项目开工仪式上获悉的。</P>
<P>旭硝子第8.5代TFT-LCD玻璃基板项目是深圳市2011年重大建设项目之一。省委常委、市委书记王荣宣布项目开工，市长许勤、日本驻广州总领事馆总领事田尻和宏、TCL集团股份有限公司董事长李东生等出席了开工仪式。</P>
<P>据了解，TFT-LCD玻璃基板份额居世界第二位的旭硝子株式会社是日本的百年老企业，属于三菱集团成员公司，目前在全球拥有200多家子公司。旭硝子的平板玻璃、汽车玻璃和等离子面板用玻璃基板市场份额均居世界首位。旭硝子是全球唯一一家采用浮法工艺的生产商，采用的浮法工艺适于大批量生产大尺寸TFT玻璃。</P>
<P>许勤在讲话中说，旭硝子TFT-LCD玻璃基板项目的开工建设，对于完善我市平板显示产业链，加快培育战略性新兴产业具有十分重要的意义。</P>
<P>旭硝子计划投资220亿日元，在光明新区高新技术产业园区新建一条月产量为120K片8.5代TFT-LCD玻璃基板(尺寸为2200mm×2500mm)的精密研磨生产线，将出产中国最大尺寸的第8.5代TFT玻璃，计划在明年秋季建成投产。据介绍，旭硝子的工厂设在TCL投资的华星光电第8.5代液晶面板项目附近，是因为其玻璃基板将专供华星光电。该项目计划于今年8月进入试产。</P>
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