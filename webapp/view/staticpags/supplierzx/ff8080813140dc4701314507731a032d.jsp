<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>苹果将与三星和LG签订显示屏供应大单- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314507731a032d.jsp" title="苹果将与三星和LG签订显示屏供应大单" class="cmsHref_self">苹果将与三星和LG签订显示屏供应大单</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>苹果将与三星和LG签订显示屏供应大单</h1>
					<div class="source">
						<span>发布时间：2011-07-20</span>
						<span>发布人：-中关村在线  </span>
					</div>
					<p><P>北京时间7月19日上午消息，消息人士透露，全球最大的两家液晶显示器(LCD)生产商三星电子和LG Display公司即将获得苹果显示屏大单。</P>
<P>苹果向这两家互为竞争对手的公司下订单，意在降低对三星的依赖程度。目前，苹果产品的关键部件，例如闪存芯片、处理器以及液晶显示屏均由三星代工。</P>
<P>同时，三星乐于通过为苹果生产零部件提升自身实力，它与苹果的关系可以说是亦友亦敌。苹果曾状告三星侵犯其知识产权，声称三星制造的产品模仿iPhone和iPad的外观设计。</P>
<P>据消息人士透露，目前，苹果已经开始在其中国的实验室对三星和LG的液晶显示屏进行质量检测，并要求他们研发质量更好，清晰度更高的液晶显示屏。此项测试预计将于今年第三季度内完成。</P>
<P>消息人士还透露，苹果新款平板电脑iPad 3将配备支持QXGA的显示屏，届时其分辨率达到2048×1536，比例为4:3，可以提供全高清视觉体验。</P>
<P>苹果一直以来有这样的传统：即使升级产品，也会使用同一制造商为某种产品生产的特定部件。在业界人士来看，这种趋势还将延续下去。</P>
<P>不过，苹果对三星OLED(有机发光二极管)显示屏持冷淡态度。虽然三星大力研发OLED显示屏，但由于其使用寿命较短和抗污性差等缺点，电子生产商一直对OLED显示屏不太感冒。目前，OLED显示屏已经开始在一些小型的电子产品中应用，但一般都是5英寸或者更小的屏幕。</P>
<P>业界人士说，OLED显示屏不受欢迎是因为其技术目前还不能实现全高清视觉体验。</P>
<P>苹果与三星、LG即将签下的这笔大单可以确保三星和LG在未来一段时间内坐稳苹果显示屏供应商的头把交椅。</P>
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