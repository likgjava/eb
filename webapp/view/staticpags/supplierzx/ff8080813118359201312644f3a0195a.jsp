<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>LG发布D2000裸眼LCD显示器 带眼球跟踪功能- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312644f3a0195a.jsp" title="LG发布D2000裸眼LCD显示器 带眼球跟踪功能" class="cmsHref_self">LG发布D2000裸眼LCD显示器 带眼球跟踪功能</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>LG发布D2000裸眼LCD显示器 带眼球跟踪功能</h1>
					<div class="source">
						<span>发布时间：2011-07-14</span>
						<span>发布人：-腾讯科技  </span>
					</div>
					<p><P>LG今天展示了世界首款无需3D眼镜的显示器产品D2000，这款20英寸3D显示器可以让用户无需眼镜在近距离和观看到3D影响，由于3D图像通常都只能在一个较小的角度内被看到，这非常不便，因此LG采用了一个特殊的相机传感器连接到显示屏，检测用户眼球的位置变化，然后计算观众的角度和位置并自动调整为最佳的3D效果，这让用户的可视范围和活动空间增大了。</P>
<P>LG已经将3D技术加入3D电视、笔记本电脑、投影仪甚至智能手机，D2000晚些时候将在韩国开始提供，并在2011年底销往其他国家。</P>
<CENTER><IMG border=1 alt=LG裸眼3D眼镜D2000 src="http://img00.hc360.com/av/201107/201107140849097740.jpg" width=500 height=476></CENTER>
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