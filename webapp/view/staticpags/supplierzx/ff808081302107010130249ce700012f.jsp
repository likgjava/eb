<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>韩国警方 介入现代起亚零部件供应商罢工- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081302107010130249ce700012f.jsp" title="韩国警方 介入现代起亚零部件供应商罢工" class="cmsHref_self">韩国警方 介入现代起亚零部件供应商罢工</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>韩国警方 介入现代起亚零部件供应商罢工</h1>
					<div class="source">
						<span>发布时间：2011-05-25</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据韩国联合通讯社(Yonhap)本周二(当地时间2011年5月24日)报道，韩国警方日前进入了汽车零部件供应商Yoosung Enterprise公司旗下一家发生罢工活动的工厂内，以驱散工厂内罢工的工人。现代起亚汽车公司表示，汽车零部件供应商Yoosung Enterprise公司旗下的工厂从5月18日开始陷入了停产，该工厂的工人拒绝同意新制定的薪资协议和轮班制度，并且占领了工厂的生产线。</p>
<p>截至目前，Yoosung Enterprise公司的罢工活动已经持续一周。该公司是韩国汽车制造商现代汽车公司及其子公司起亚汽车公司的重要供应商之一，现代汽车公司和起亚汽车公司70%的汽车用活塞环均由该公司供应。</p>
<p>据悉，该工厂的罢工活动已经导致现代汽车公司部分SUV车型和现代蔚山工厂柴油引擎的生产活动中断。</p>
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