<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>山东中烟强化供应链管理实施无缝联接- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6d19ae6017f.jsp" title="山东中烟强化供应链管理实施无缝联接" class="cmsHref_self">山东中烟强化供应链管理实施无缝联接</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>山东中烟强化供应链管理实施无缝联接</h1>
					<div class="source">
						<span>发布时间：2011-05-13</span>
						<span>发布人：-  </span>
					</div>
					<p><p>5月4日，山东中烟工业有限责任公司有关领导带领采购中心相关部门负责人到青州卷烟厂调研设备备件及原辅材料管理工作，以强化供应链管理，实现供需双方无缝联接。</p>
<p>座谈中，青州卷烟厂结合开展品牌合作加工后的生产实际，汇报了设备备件及原辅材料管理的基本情况，并从质量、检测标准、即时供应、库存定额等方面反映问题，提出建议。</p>
<p>调研组围绕问题建议，从供应链、寄售制、招投标管理等方面与大家进行了深入讨论，提出了初步解决思路，并从管理方法、管理手段上给予了指导。</p>
<p>公司有关领导强调，采购中心与各卷烟厂要加强沟通、密切协作、紧密配合、无缝联接，针对查找问题、制定改进措施，共同提升工作水平;要按照国家局要求，结合企业实际，充分考虑物资采购性价比因素，进一步完善招投标管理;要从成本、效率入手，强化供应链全过程管理，充分体现出山东中烟的高效率和高效益，促进&ldquo;百万泰山&rdquo;目标及&ldquo;十二五&rdquo;战略规划的顺利实现。</p>
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