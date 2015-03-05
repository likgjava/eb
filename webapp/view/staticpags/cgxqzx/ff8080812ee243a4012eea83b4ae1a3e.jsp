<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>山西计划年内开展6个高速公路项目- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ee243a4012eea83b4ae1a3e.jsp" title="山西计划年内开展6个高速公路项目" class="cmsHref_self">山西计划年内开展6个高速公路项目</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>山西计划年内开展6个高速公路项目</h1>
					<div class="source">
						<span>发布时间：2011-03-25</span>
						<span>发布人：-  </span>
					</div>
					<p><p style="text-align: justify">3月23日，记者从山西省交通厅获悉，由山西省交通设计院承担的大营至神池高速公路初步设计已通过评审。截至目前，今年山西省计划开工的11个高速公路项目已有6个项目工程获得批复。</p>
<p style="text-align: justify">大营至神池高速公路是山西省&ldquo;三纵十一横十一环&rdquo;高速公路网第三横的重要组成部分，起于原平市沿沟乡麻地沟村，终于神池县东湖乡东湖村，路线全长64.76公里，采用全封闭、全立交的双向四车道高速公路标准，设计速度为80公里/小时，路基宽度24.5米。</p>
<p style="text-align: justify">到目前为止，今年计划开工的500公里11个高速公路项目有神池-河曲、左权-黎城等6个项目工程已批复，有长治-临汾、黎城-长治拓宽等4个项目的环评，3个项目的水保，6个项目的土地预审和4个项目的地质灾害评估均已批复，其他程序均在按计划加紧推进。今年计划开工的6个预备项目前期工作同时也在有序推进。</p>
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