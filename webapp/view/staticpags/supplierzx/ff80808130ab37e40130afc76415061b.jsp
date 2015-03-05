<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>三星将为日本运营商KDDI提供LTE设备- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130afc76415061b.jsp" title="三星将为日本运营商KDDI提供LTE设备" class="cmsHref_self">三星将为日本运营商KDDI提供LTE设备</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>三星将为日本运营商KDDI提供LTE设备</h1>
					<div class="source">
						<span>发布时间：2011-06-21</span>
						<span>发布人：-搜狐IT  </span>
					</div>
					<p><P>北京时间6月21消息，三星电子将为日本电信运营商KDDI提供LTE设备，帮助KDDI在明年提供LTE服务。</P>
<P>LTE是第四代移动通信技术，传输数据比现有技术快7倍。KDDI计划跟随NTTDocomo的步伐，在今年晚期推出LTE服务。</P>
<P>三星提供的设备可以允许KDDI对无线互联网流量进行优化管理。与此同时，在一些边远地区通信信号不良，通过三星设备KDDI可以提供更好的通信质量。</P>
<P>此前三星已经在美国和中东市场达成相似采购协议。</P>
<P>三星电子通信系统业务执行副总裁KimYoung-ky说：“我们希望自己提供的解决方案不只对客户有利，还对电信公司对客户有利。我们将继续努力，强化自己在LTE市场的地位。”</P>
<P>2008年，三星电子成为首家可以展示LTE技术的电子公司。目前三星已经与全球与30家公司展开LTE合作。</P>
<P>今年7月开始，韩国运营商SK电讯将在首尔商用LTE技术，竞争对手LGUplus也会推出相关服务。(Aria)</P>
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