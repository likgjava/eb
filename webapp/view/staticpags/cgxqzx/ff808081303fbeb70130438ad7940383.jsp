<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>江苏如东棉价小幅回升 纺企采购量增- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081303fbeb70130438ad7940383.jsp" title="江苏如东棉价小幅回升 纺企采购量增" class="cmsHref_self">江苏如东棉价小幅回升 纺企采购量增</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>江苏如东棉价小幅回升 纺企采购量增</h1>
					<div class="source">
						<span>发布时间：2011-05-31</span>
						<span>发布人：-  </span>
					</div>
					<p><p>近日，江苏省如东县的皮棉价格小幅回升，5月28日，3级地产棉成交价在25500-26000元/吨，弱3级在25300-25500元/吨，4级在24800-25000元/吨，均较上周上涨500元/吨。纺企皮棉采购积极性较高，采购量有所增加，由原来一次采购10-20吨增至100-200吨，以高等级地产棉为主。据业内人士反映，目前当地200型棉企已无存棉，400型棉企仅有两家有存棉，对高等级棉存在惜售情绪，对低等级棉则随行就市销售。</p>
<p>此外，当天棉纱价格也有所上涨，其中纯棉普梳40S价格在32000-33000元/吨，较上周上涨2000元/吨。据当地某纺企反映，目前纺企生产白纱利润在1000-2000元/吨，色纱利润在4000-6000元/吨，当地纺企大都将皮棉和棉纱库存量保持在一周左右。</p>
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