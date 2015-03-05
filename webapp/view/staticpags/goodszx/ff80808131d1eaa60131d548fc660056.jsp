<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>PC仍为DRAM最大需求来源　但成长动能趋缓- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131d1eaa60131d548fc660056.jsp" title="PC仍为DRAM最大需求来源　但成长动能趋缓" class="cmsHref_self">PC仍为DRAM最大需求来源　但成长动能趋缓</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>PC仍为DRAM最大需求来源　但成长动能趋缓</h1>
					<div class="source">
						<span>发布时间：2011-08-17</span>
						<span>发布人：-  </span>
					</div>
					<p><P>展望2011下半年全球DRAM市场需求，DIGITIMESResearch分析师柴焕欣分析，最大来源仍为PC市场，但因桌上型电脑出货量衰退，加上笔记型电脑出货量成长趋缓，纵使桌上型电脑与笔记型电脑单机搭载DRAM位元量仍将逐季成长，PC市场对DRAM位元需求量年成长率亦仅达27.1%，与过去数年动辄超过50%的年成长率相较，来自PC市场对DRAM位元需求量成长动能确实出现趋缓的警讯。</P>
<P>至于来自非PC市场对DRAM需求，柴焕欣分析，随云端技术应用日渐普及，加上以智慧型手机、平板电脑等可携式电子产品对DRAM需求快速成长，让来自包括伺服器与行动装置为主的非PC需求对DRAM市场的影响力亦与日俱增。其中，占非PCDRAM市场需求中最大比重的伺服器市场需求，受惠于云端概念兴起与普及，伺服器单机搭载DRAM位元需求量亦呈现逐季成长，让2011年下半来自伺服器对DRAM位元需求量年成长率预期将达到93.7%。</P>
<P>至于自2010年以来即受市场高度关注的MobileRAM，柴焕欣也说明，主要受惠于智慧型手机单机搭载MobileRAM位元需求量于2011年呈现逐季推升态势，加上智慧型手机于渗透率逐季提升，及平板电脑对MobileRAM需求增加，均推动了MobileRAM市场需求成长，让2011年下半全球MobileRAM市场位元需求量年成长有机会高达135.6%。</P>
<P>以伺服器与MobileRAM为主要成长动力的非PCDRAM市场需求，预估在2011年下半将出现85.4%年成长率，表现远优于来自PC市场对DRAM需求，这亦让非PC市场对DRAM位元需求量占全球DRAM市场位元需求量比重持续攀升，柴焕欣预估，2012年下半来自非PC市场位元需求量占全球DRAM市场位元需求量比重将有机会超过50%，成为DRAM市场主流。</P>
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