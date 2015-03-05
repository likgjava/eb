<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>联合国下属机构首次参与主办西博会分论坛和采购商大会- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a95601308b9af4ae11d0.jsp" title="联合国下属机构首次参与主办西博会分论坛和采购商大会" class="cmsHref_self">联合国下属机构首次参与主办西博会分论坛和采购商大会</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>联合国下属机构首次参与主办西博会分论坛和采购商大会</h1>
					<div class="source">
						<span>发布时间：2011-06-14</span>
						<span>发布人：-  </span>
					</div>
					<p><p>记者从昨日省政府召开的第十一届西博会签约项目督办暨十二届西博会筹备工作动员会议上获悉，主题为&ldquo;引领国际合作，拓展市场空间&rdquo;的第十二届西博会将于今年10月举行，会议的国际化水平将进一步提升，联合国开发计划署、联合国项目事务服务厅将分别首次参与主办中国西部国际论坛和中国西部国际采购商大会。</p>
<p>截至目前，世界贸易组织总干事帕斯卡尔&middot;拉米、法国前总理让&middot;皮埃尔&middot;拉法兰、联合国开发计划署署长海伦&middot;克拉克、联合国项目事务服务厅总干事麦守信、冰岛共和国总统奥拉维尔&middot;拉格纳&middot;格里姆松等重量级嘉宾都已经初步确认出席本届西博会。</p>
<p>另据记者了解，截至今年5月，第十一届西博会成都市签约项目履约情况良好，项目履约率已提前超额完成全年目标任务。据统计，在第十一届西博会成都市招商引资签约的162个项目中，有160个已履约，履约率达98.77%;124个项目开工，开工率达76.54%。</p>
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