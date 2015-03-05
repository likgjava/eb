<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>青海通过政府采购机制引导农机购买方向- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813026ae99013029bd4040000f.jsp" title="青海通过政府采购机制引导农机购买方向" class="cmsHref_self">青海通过政府采购机制引导农机购买方向</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>青海通过政府采购机制引导农机购买方向</h1>
					<div class="source">
						<span>发布时间：2011-05-26</span>
						<span>发布人：-  </span>
					</div>
					<p><p>记者从青海省财政厅获悉，近年来，青海省财政根据全省实际调整补贴重点，着重加强对新技术、新机具用省级补贴资金叠加补贴，通过政府采购机制，积极引导农民将购买的重点向先进适用、性能安全、节能环保方向发展，大蒜收获机、微耕机、马铃薯种植机具等补贴比例由30%提高到50%。</p>
<p>自2005年实施农机购置补贴政策以来，青海省在中央财政的大力支持下，平均每年以66%增长速度增加农机购置补贴资金，到2011年3月，中央财政累计下达青海省农机购置补贴资金3亿元。</p>
<p>5月20日，记者在青海省三江源农机市场看到，市场里各种农用机械一应俱全，前来购买农机的群众络绎不绝。来自青海省互助土族自治县的农民张启龙在了解农机补贴购置补贴政策之后仔细挑选并购买了一台农用车。</p>
<p>农机购置补贴政策有力地促进了农机科技成果的转化，帮助农牧民实现了买得起、用得好、有效益的愿望，带动了青海省农牧区农机化新技术推广应用，使青海省农机装备得到进一步改善，农牧业生产效率进一步提高，农牧机械在农牧业生产中的作用有效发挥，促进了青海省农牧业增效和农牧民增收。</p>
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