<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>华润雪花掷3亿巩固辽宁大本营- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f0130771c223442ba.jsp" title="华润雪花掷3亿巩固辽宁大本营" class="cmsHref_self">华润雪花掷3亿巩固辽宁大本营</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>华润雪花掷3亿巩固辽宁大本营</h1>
					<div class="source">
						<span>发布时间：2011-06-10</span>
						<span>发布人：-  </span>
					</div>
					<p><p>作为目前国内市场发展速度最快的啤酒企业，华润雪花近年来为巩固局部市场，不断地收编各地中小啤酒厂，以壮自身实力。昨日，雪花啤酒宣布，投资3亿元收购北方绿色食品股份有限公司旗下清河墨尼啤酒分公司80.1%股权，并成立华润雪花啤酒(铁岭)有限公司。只是此次，雪花把要巩固的市场搬回了其大本营。</p>
<p>据了解，清河墨尼啤酒分公司年产能为19万千升，是辽北地区很具影响力的啤酒企业，其主打品牌为岛城啤酒，雪花控股后将投入4000万元资金进行项目改造。</p>
<p>对此，中投顾问食品行业研究员周思然分析称，对于雪花来讲，此次收购更重要的是完成了其以沈阳为核心的全省布局，届时雪花在辽宁省的总产能超过230万千升，销售网络覆盖全省，由此而形成的啤酒战斗群将提升雪花在行业中的整体竞争力，进一步抢占市场份额，行业结构将进一步调整。</p>
<p>从沈阳啤酒厂发展起来的雪花啤酒，1994年与华润集团成立华润雪花，从辽宁走向全国市场，目前东北三省市场已经成为华润雪花牢固的根据地，辽宁市场主要份额也已多年都在雪花手中。</p>
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