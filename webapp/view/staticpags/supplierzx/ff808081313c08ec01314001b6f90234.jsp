<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>黄酒快速发展凸显人才“短板”- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01314001b6f90234.jsp" title="黄酒快速发展凸显人才“短板”" class="cmsHref_self">黄酒快速发展凸显人才“短板”</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>黄酒快速发展凸显人才“短板”</h1>
					<div class="source">
						<span>发布时间：2011-07-19</span>
						<span>发布人：-中国酒业新闻网  </span>
					</div>
					<p><P>目前，黄酒行业的市场营销较白酒、啤酒、葡萄酒等酒种相对滞后。黄酒营销专业的设立，为黄酒行业培养专门的销售人才，这对今后黄酒业的发展将起到一定的推动作用。</P>
<P>据了解，目前黄酒行业营销从业人员主要来自两个层面：一是企业内部生产管理岗位，二是行业外原来从事其它酒种的营销人才。大部分营销从业人员没有接受过有关黄酒的系统营销知识培训，致使其在与客户沟通时不能很好地担当起“产品宣传员”的角色。</P>
<P>随着近年来各大黄酒企业的相继扩产，黄酒产能急剧提升，黄酒产业进入良性发展阶段，人才这一“短板”也日益凸显。“传统酿造行业师徒传授的培养模式已经不能满足黄酒业现有的发展要求，企业对既懂理论又懂实践的应用型人才的需求更显迫切。”浙文章来源华夏酒报江绍兴当地一位黄酒企业负责人向《华夏酒报》特约记者表示。</P>
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