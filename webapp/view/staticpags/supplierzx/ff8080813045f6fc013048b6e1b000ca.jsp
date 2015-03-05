<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>三星提前量产AMOLED面板以满足智能手机需求- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813045f6fc013048b6e1b000ca.jsp" title="三星提前量产AMOLED面板以满足智能手机需求" class="cmsHref_self">三星提前量产AMOLED面板以满足智能手机需求</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>三星提前量产AMOLED面板以满足智能手机需求</h1>
					<div class="source">
						<span>发布时间：2011-06-01</span>
						<span>发布人：-  </span>
					</div>
					<p><p>6月1日消息，据国外媒体报道，三星移动显示公司周二称，它的新工厂已经提前两个月开始大批量生产AMOLED（有源矩阵有机发光二极管）显示屏面板以满足智能手机和平板电脑日益增长的需求。</p>
<p style="text-indent: 2em">&nbsp;</p>
<p style="text-indent: 2em">&nbsp;</p>
<p style="text-indent: 2em">&nbsp;</p>
<p style="text-indent: 2em">三星移动显示公司是三星电子和三星SDI的合资企业。三星移动显示在声明中称，它已经在首尔西南大约90公里的Asan的工厂开始大批量生产5.5代AMOLED显示屏。</p>
<p style="text-indent: 2em">&nbsp;</p>
<p style="text-indent: 2em">&nbsp;</p>
<p style="text-indent: 2em">&nbsp;</p>
<p style="text-indent: 2em">这个工厂有全球最大的AMOLED显示屏面板生产线，是第一个生产5.5代显示屏面板的生产线。这种显示屏面板比上一代显示屏面板大三倍。</p>
<p style="text-indent: 2em">&nbsp;</p>
<p style="text-indent: 2em">&nbsp;</p>
<p style="text-indent: 2em">&nbsp;</p>
<p style="text-indent: 2em">生产更大的显示屏面板能够减少成本和提高生产率。这个新的生产线还有助于该公司成为平板电脑和便携式游戏机的供应商。</p>
<p style="text-indent: 2em">&nbsp;</p>
<p style="text-indent: 2em">&nbsp;</p>
<p style="text-indent: 2em">&nbsp;</p>
<p style="text-indent: 2em">今年第一季度，三星移动显示占全球AMOLED显示屏面板供应量的将近99%。该公司称，它希望把产品组合扩展到手机显示屏以外的产品。</p>
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