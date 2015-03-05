<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中央和地方软件正版化采购额达3.7亿元- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130afdee10130b4c6020703f4.jsp" title="中央和地方软件正版化采购额达3.7亿元" class="cmsHref_self">中央和地方软件正版化采购额达3.7亿元</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>中央和地方软件正版化采购额达3.7亿元</h1>
					<div class="source">
						<span>发布时间：2011-06-22</span>
						<span>发布人：-科技日报  </span>
					</div>
					<p><P>6月21日电 记者从国家版权局获悉，截至目前，中央和地方在软件正版化检查整改工作中，共计采购操作系统软件、办公软件和杀毒软件381807套(许可数)，采购金额达37714万元。</P>
<P>据悉，135家中央和国家机关已于今年5月底完成软件正版化检查整改工作。按照国务院要求，省、市(地)、县级政府机关将于今年10月底前完成软件正版化检查整改工作。目前各级政府机关普遍完成软件摸查工作，已进入软件采购谈判阶段。山东青岛、济南，新疆克拉玛依，安徽淮南，四川成都，江苏苏州等市政府和上海市市级政府机关率先完成软件正版化检查整改工作，北京、河北、辽宁、安徽、河南、广东、新疆等省区相关政府机关已完成部分软件采购，其他省级政府机关、省会城市和计划单列市政府推进较快。</P>
<P>135家中央和国家机关共采购操作系统软件、办公软件和杀毒软件176763套(许可数)，采购金额14091万元。已完成的地方7市和相关政府机关共采购操作系统软件、办公软件和杀毒软件205044套(许可数)，采购金额23623万元。据介绍，国产软件凭借其良好的性价比，在此次采购中份额有较大提升，但从整个采购统计来看，微软在操作系统软件和办公软件采购中仍优势明显。</P>
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