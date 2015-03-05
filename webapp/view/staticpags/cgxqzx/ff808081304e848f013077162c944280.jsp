<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中关村新兴产业全年采购任务已完成过半- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013077162c944280.jsp" title="中关村新兴产业全年采购任务已完成过半" class="cmsHref_self">中关村新兴产业全年采购任务已完成过半</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>中关村新兴产业全年采购任务已完成过半</h1>
					<div class="source">
						<span>发布时间：2011-06-10</span>
						<span>发布人：-  </span>
					</div>
					<p><p>截至目前，北京市2011年度已有涉及轨道交通、信息基础设施、智能交通管理、新能源汽车等战略性新兴产业领域的86个项目使用了中关村产品，采购金额达32亿元，已完成北京市政府设定的全年60亿元采购任务的53%。</p>
<p>这是记者从日前举行的2011年中关村新技术新产品政府采购第二次签约大会暨北京市首批工程研究中心和工程实验室授牌大会、共建技术转移中心签约大会上获悉的。记者在会议现场了解到，第二批政府采购项目共61项，采购金额16.4亿元，项目主要集中在轨道交通、环境保护等战略性新兴产业领域。</p>
<p>中关村管委会副主任李石柱表示，自从政府采购新技术、新产品工作开展以来，北京市相关部门积极整合资源，进行了多项重点工作，一是会同有关部门、各区县和示范区企业，加强政策宣传和培训，提高采购主体的政策意识，落实政府采购新技术新产品、各项扶持政策。二是围绕首都战略性新兴产业发展，开展重点行业领域调研工作，深入了解重点产业新技术新产品创新水平及应用情况，为进一步推动新技术新产品在首都基础设施建设中的广泛应用及向全国推广奠定基础。三是搭建平台，开展供需对接。先后组织召开了信息化项目、医疗器械和诊断试剂、教学设备暨教育信息化、轨道交通建设、环保监测、指挥通信等6个领域产品推介会等。</p>
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