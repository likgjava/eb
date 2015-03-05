<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>大众发布新能源计划 混动将是研发重点- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b336d6d041a.jsp" title="大众发布新能源计划 混动将是研发重点" class="cmsHref_self">大众发布新能源计划 混动将是研发重点</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>大众发布新能源计划 混动将是研发重点</h1>
					<div class="source">
						<span>发布时间：2011-06-17</span>
						<span>发布人：-  </span>
					</div>
					<p><p>相比较其他汽车厂商，大众在混合动力领域起步的时间相对较晚，不过近日，大众在新发布的计划中表示，未来旗下所有车型都可以采用混合动力系统。据大众官方称，未来10年将会重点研发插电式混合动力，纯电动技术还需要等到更好的电池研发成功。</p>
<p>据外媒报道，大众集团CEO Martin Winterkorn表示，现阶段他并不认为是首要发展纯电动的最好时期，插电式混合动力才是未来10年的主流。Winterkorn还称，他还看好由混合动力衍生出的系统，如雪佛兰的Volt车型，这启发了公司部门计划采用单杠汽油引擎给电动TT跑车增程的构想。</p>
<p>尽管Winterkorn对现在纯电动驱动技术持有消极态度，但他并不否认这仍然是未来的发展方向，他看好比现在锂离子更有发展前途的锂亚硫电池组，它拥有3倍于锂离子电池的能量，目前正在开发的锂空气电池更是有着五倍的能量储备，但它还要等到2025年才有可能推出，不过其应用前景还是非常可观，毕竟单次充电的续航里程将可以达到800公里。</p>
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