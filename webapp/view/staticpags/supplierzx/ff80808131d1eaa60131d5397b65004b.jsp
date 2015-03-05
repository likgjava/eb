<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>雅培投资2.3亿美元中国建厂- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d1eaa60131d5397b65004b.jsp" title="雅培投资2.3亿美元中国建厂" class="cmsHref_self">雅培投资2.3亿美元中国建厂</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>雅培投资2.3亿美元中国建厂</h1>
					<div class="source">
						<span>发布时间：2011-08-17</span>
						<span>发布人：-  </span>
					</div>
					<p><P>洋奶粉巨头美国雅培公司昨日宣布，将在浙江省嘉兴投资2.3亿美元建立营养品制造厂，这是雅培公司迄今为止在中国最大的投资项目。该工厂计划于2013年投入运营，将生产中国婴幼儿高端营养产品。</P>
<P>雅培全球营养品部执行副总裁约翰·兰格拉夫昨日表示，新工厂主要生产覆盖儿科营养领域的产品。中国每年有超过1700万名新生儿。雅培计划在未来三年内推出一系列儿科营养产品，满足中国市场需求。</P>
<P>此外，雅培中国还表示，新厂竣工后将成为雅培在中国的首个拥有全自动喷雾干燥、混合和包装设施的工厂，所生产的产品将与雅培在全球其他工厂所生产的产品使用相同质量的原料，并执行相同的检测标准。</P>
<P>对于新工厂将具体生产哪些产品以及产品原料来源，记者昨日联系雅培中国，雅培中国并未回应，但表示，目前在中国市场销售的智护100系列产品均为100%原装进口。</P>
<P>据悉，雅培目前在中国有3个工厂，分别为上海雅培制药有限公司，是雅培与美优制药共同成立的合资工厂;雅培营养品广州工厂主要用于婴儿配方奶粉产品金装培乐的干粉混合包装，产品专供中国市场;雅培眼力健杭州工厂则专为雅培眼力健事业部生产隐形眼镜和滴眼液等眼科护理产品。</P>
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