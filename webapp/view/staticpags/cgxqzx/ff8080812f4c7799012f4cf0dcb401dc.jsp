<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>老区改造开始采购主材- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4c7799012f4cf0dcb401dc.jsp" title="老区改造开始采购主材" class="cmsHref_self">老区改造开始采购主材</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>老区改造开始采购主材</h1>
					<div class="source">
						<span>发布时间：2011-04-13</span>
						<span>发布人：-  </span>
					</div>
					<p><p>20个老区&ldquo;内外兼修&rdquo;，13万居民&ldquo;翘首企盼&rdquo;。老区改造材料都用的啥，质量咋样，近日，带着这些疑问记者来到了指挥部。</p>
<p>记者了解到，老居住区改造主材采购工作现在已经开始，与其它工程不同的是，老居住区改造中涉及的部分主材由指挥部亲自采购，这样可以减少采购环节、节约资金，更可以提高材料的性价比。</p>
<p><br />
&ldquo;主材的采购将以招投标的形式进行。&rdquo;近日，老居住区环境综合整治指挥部的工作人员告诉记者，老居住区改造涉及到的材料种类特别多，而且量也很大，因此，现在指挥部正在抓紧对各种材料进行性价比分析，尽量节省资金，找到性价比较高的产品。</p>
<p><br />
指挥部采购的材料包括：供热、供水管线、阀门、井盖、保温板、玻璃钢楼体雨排管、防水卷材、外墙涂料、单元门、荷兰砖、围栏等。在大庆市建设局网上有相关的招投标信息，市民可以浏览、监督；有意提供质优价廉材料的单位也可以进行查询。</p>
<p>这些材料到位以后，市民、监督员、施工方可以对材料进行检验，替老区改造把好&ldquo;材料关&rdquo;。</p>
<p><br />
&nbsp;</p>
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