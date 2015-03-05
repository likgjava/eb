<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>巴陵石化增产成品油力保市场供应- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081302107010130249dd6a50131.jsp" title="巴陵石化增产成品油力保市场供应" class="cmsHref_self">巴陵石化增产成品油力保市场供应</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>巴陵石化增产成品油力保市场供应</h1>
					<div class="source">
						<span>发布时间：2011-05-25</span>
						<span>发布人：-  </span>
					</div>
					<p><p>作为湖南两大炼油企业之一，中石化巴陵石化公司今年来全力增产成品油，确保市场供应。截至今天，公司今年已累计产销优质汽、柴油近40万吨，同比增加近5000吨。</p>
<p>受中东局势动荡、日本大地震等因素影响，国际原油价格不断攀升，国内成品油保供压力不断加大。面对严峻形势，巴陵石化努力消化持续高负荷、原油劣质化等不利因素，优化生产组织，切实加强运行管理，保持炼油装置满负荷生产。到5月24日，炼油装置已连续安全运行679天，比历史最长纪录还多107天。 为了&ldquo;吃干榨尽&rdquo;弥足珍贵的原油资源，巴陵石化持续保持并逐步完善单油种效益测算、结构优化测算、调和成本测算等24项炼油板块全程优化信息平台，通过精细化管理，6项炼油专业考核指标中有4项超过中石化总部年度奋斗目标，汽、柴油成品收率也超总部计划0.81个百分点。</p>
<p>在油品销售中，承担巴陵石化油品外销的公司供销部全力保障市场供应。他们不断与大区公司协调，做好销售计划的衔接;通过与铁路部门的沟通协调，安排好油罐车的进出厂，千方百计增加油品的水运配置，优化了成品油的运输结构。今年来，每月高标号的97号汽油配置比例均超过50%，创历史最好水平。</p>
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