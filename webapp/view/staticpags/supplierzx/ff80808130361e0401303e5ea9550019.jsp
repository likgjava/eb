<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>佛吉亚为日产美国工厂供应汽车座椅- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130361e0401303e5ea9550019.jsp" title="佛吉亚为日产美国工厂供应汽车座椅" class="cmsHref_self">佛吉亚为日产美国工厂供应汽车座椅</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>佛吉亚为日产美国工厂供应汽车座椅</h1>
					<div class="source">
						<span>发布时间：2011-05-30</span>
						<span>发布人：-  </span>
					</div>
					<p><p>综合外电报道，法国汽车零部件供应商佛吉亚(Faurecia)公司日前表示，该公司在美国密西西比州新建的麦迪逊(Madison)工厂已经投入运营，并将为日产汽车公司旗下的一家美国汽车组装工厂供应汽车座椅。</p>
<p>据悉，该工厂占地面积18万平方英尺(约合1.67万平方米)，将为日产汽车公司密西西比州的汽车组装厂坎顿工厂(Canton)供应汽车座椅。日产旗下美国坎顿工厂负责生产日产Altima轿车、Armada运动型多功能车(SUV)、Titan皮卡以及日产NV商用车型。</p>
<p>2011年5月25日，佛吉亚公司北美汽车座椅业务总裁Rich Kolpasky表示，目前主要汽车制造商都在向美国东南部扩张业务，而该公司新建的麦迪逊工厂也是佛吉亚公司在美国东南部地区增长战略的一部分。</p>
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