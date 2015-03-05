<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>为降iPad3成本 苹果寻找新的IC供应商- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316918b0cd04c4.jsp" title="为降iPad3成本 苹果寻找新的IC供应商" class="cmsHref_self">为降iPad3成本 苹果寻找新的IC供应商</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>为降iPad3成本 苹果寻找新的IC供应商</h1>
					<div class="source">
						<span>发布时间：2011-07-27</span>
						<span>发布人：-赛迪网  </span>
					</div>
					<p><P>7月27日消息，据国外媒体报道，一开始，苹果公司就为iPad做了较低的定价。为此，其竞争对手们也相继努力以同等价格提供同样性能的平板电脑，但都未能赶上。比如，基于Tegra 2的Honeycomb系列平板电脑有多次都未能打赢与iPad2的价格战，为了保持领先地位，据Digitimes网站消息，苹果公司正探寻新的中国台湾地区集成电路供应商进行合作。</P>
<P>据业内消息，苹果公司已与中国台湾联咏科技 (Novatek Microelectronics Corp.) 公司、中国台湾凌耀科技股份有限公司(Capella Microsystems)和IntegratedMemoryLogic(iML)公司就供应iPad3元件的相关细节进行了磋商，而且为了与将在下半年推出的下一代安卓平板电脑的竞争中获胜，苹果还对价格体系进行了调整。</P>
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