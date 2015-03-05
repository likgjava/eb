<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>水泥行业：八月上半月水泥需求仍在放缓- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/goodszx.jsp" title="供货资讯" class="cmsHref_self">供货资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131d7d3750131da73ebde001e.jsp" title="水泥行业：八月上半月水泥需求仍在放缓" class="cmsHref_self">水泥行业：八月上半月水泥需求仍在放缓</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>水泥行业：八月上半月水泥需求仍在放缓</h1>
					<div class="source">
						<span>发布时间：2011-08-18</span>
						<span>发布人：-  </span>
					</div>
					<p><P>八月上半月水泥需求仍在放缓，整体景气仍然处于低位:雨季、高温、农忙和在建项目由于资金影响而放缓等因素使得需求仍然处于低位。不过大多数调研对象预计景气会在8月底出现回升。</P>
<P>大多数地区水泥价格保持平稳，一些地区水泥价格继续下调:鄂西水泥价格下调45-50元，但在预期之中，主要是由于竞争激烈。预计8月下旬会好转，价格有可能小幅上调20-30元。内蒙古下调20元左右，福州也下调20元左右。 广西限电影响明显，我们调研的广西的一家水泥厂(中等规模)反映目前是实打实的限电，其中柳州限电最为严重，桂林和南宁也在限电，产能受限电影响而出现明显减少，产能是此前的四分之一左右，但价格基本平稳，主要是限电存在区域性，如果全部都严格限电，水泥价格将可能出现大幅上涨。</P>
<P>需求放缓导致目前整体库存较高:调研对象平均库容比处在60-80%左右的水平，有个别调研对象库容比接近100%水平。</P>
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