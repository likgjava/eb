<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>HTC起诉苹果产品侵犯三项技术专利- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d7d3750131da55f0d9000b.jsp" title="HTC起诉苹果产品侵犯三项技术专利" class="cmsHref_self">HTC起诉苹果产品侵犯三项技术专利</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>HTC起诉苹果产品侵犯三项技术专利</h1>
					<div class="source">
						<span>发布时间：2011-08-18</span>
						<span>发布人：-  </span>
					</div>
					<p><P>据台湾媒体报道，HTC周二向美国特拉华州地方法院起诉苹果，称Mac、iPhone和iPad等产品侵犯其三项专利。要求禁止苹果向美国进口和出售侵权产品，并要求支付3倍于侵权损失的赔偿金。</P>
<P>HTC表示，涉及侵权的产品包括iPhone、iPad平板电脑、Mac笔记本电脑、苹果台式电脑和iPod音乐播放器。涉嫌侵权相关专利包括将智能手机计算功能和通信功能整合在一起的基础智能机技术。HTC的要求如果得到法院的认可，将对苹果产品产生很大的影响。</P>
<P>最近几个月以来，HTC和苹果的专利战不断在上演。苹果于今年3月首先提起诉讼指责HTC侵犯其专利权。今年7月，美国国际贸易委员会的法官就这一案件作出初步判决，认定HTC在两项专利上涉嫌侵权。HTC随后表示将就这一裁决提起上诉。</P>
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