<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>朗盛新产品应用于卡车外部组件- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01307702b8dd4235.jsp" title="朗盛新产品应用于卡车外部组件" class="cmsHref_self">朗盛新产品应用于卡车外部组件</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>朗盛新产品应用于卡车外部组件</h1>
					<div class="source">
						<span>发布时间：2011-06-10</span>
						<span>发布人：-  </span>
					</div>
					<p><p>日前获悉，朗盛为大型卡车外部组件研发了一种新的产品&mdash;Pocan C 1202，它是聚对苯二甲酸丁二醇酯和聚碳酸酯(PBT+PC)的混合物。该产品被用于生产FM系列FMX建筑卡车的无涂层格栅，其配备了沃尔沃卡车公司专门针对恶劣的建筑工地运行条件而开发的全新前端截面。</p>
<p>该格栅是一个面积很大的注塑部件。因此，相关模具采用多级注塑技术进行填充。卡扣和卡座也可以牢固地复制在流动路径的末尾。此外，优良的流动性仅产生最低限度的变形，并确保精细的皮纹图案得以精确复制。除出色的加工性能外，该材料还可以轻松脱模并具有较低的收缩率。为了削减成本，格栅不需喷涂。PBT混合物符合沃尔沃标准的紫外线稳定性要求并具有良好的耐化学性，该材料还对车辆每天遇到的情况具有良好的抵抗力。</p>
<p>朗盛半结晶产品业务部亚太区负责人卫业克说：&ldquo;我们的材料可以为组件提供具有细纹图案的高耐候性优质表面，且无需涂敷涂层。此外，该材料还具有极高的韧性，使格栅可以耐受石屑和其它机械应力。&rdquo;</p>
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