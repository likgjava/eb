<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>日本大地震导致用于汽车的专业涂料短缺- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081317673560131880ef3e80b1b.jsp" title="日本大地震导致用于汽车的专业涂料短缺" class="cmsHref_self">日本大地震导致用于汽车的专业涂料短缺</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>日本大地震导致用于汽车的专业涂料短缺</h1>
					<div class="source">
						<span>发布时间：2011-08-02</span>
						<span>发布人：-  </span>
					</div>
					<p><P>全球多家汽车制造商25日宣布，因缺乏一种产自日本的专业涂料，多种颜色车型生产受阻。</P>
<P>这种涂料名为Xirallic，可使汽车更显光亮，由德国默克化学产品制造公司旗下一家位于日本小名浜市的工厂独家生产，供应全球多数车企。受日本地震、海啸及核泄漏危机影响，工厂现已停产，尚不清楚何时复工。</P>
<P>美国克莱斯勒汽车公司当天通知经销商，限制订购旗下10种颜色车型，包括2种黑色衍生色、3种红色衍生色、青铜色、灰褐色、草绿色、乳白色和钢坯银色。公司发言人凯蒂·赫普勒说，限购是“预防措施”，不影响生产。</P>
<P>美国福特汽车公司同时宣布，旗下多款汽车黑色车型暂时无法订货。公司发言人托德·尼森说，正寻求替代产品并与默克公司协商异地生产。</P>
<P>日本丰田汽车公司发言人迈克·戈斯说，汽车使用Xirallic，但尚未限购。丰田汽车先前已暂停国内全部12家组装厂生产，一些北美工厂将减产。</P>
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