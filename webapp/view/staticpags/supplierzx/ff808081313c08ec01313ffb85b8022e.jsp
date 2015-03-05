<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>投入1.2万亿韩元 三星电子拟进军医疗设备业- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313ffb85b8022e.jsp" title="投入1.2万亿韩元 三星电子拟进军医疗设备业" class="cmsHref_self">投入1.2万亿韩元 三星电子拟进军医疗设备业</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>投入1.2万亿韩元 三星电子拟进军医疗设备业</h1>
					<div class="source">
						<span>发布时间：2011-07-19</span>
						<span>发布人：-中国证券报  </span>
					</div>
					<p><P>三星电子公司医疗设备开发部门高级副总裁赵宰汶上周末表示，公司目前正就并购核磁共振成像扫描仪和X光设备厂商事宜进行洽谈，以期进军医疗器械行业，向通用电气和西门子等医疗设备巨头发起挑战。</P>
<P>赵宰汶表示，三星电子正与一些公司进行“联系”，但其并未透露具体收购目标。三星电子此前透露，公司计划在2020年以前投入1.2万亿韩元发展医疗设备业务。</P>
<P>三星电子董事长李健熙计划将医疗设备发展成为一项年销售额达10万亿韩元的业务，三星电子即将展开的收购正是基于这一业务发展计划而进行的。李健熙强调，美国、欧洲和日本等地区老年人口比例每年均在攀升，医疗保健需求的增长将刺激医疗设备销售井喷。</P>
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