<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>药用辅料业进入景气期 年需求高速增长- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813026ae99013029c6e8da001d.jsp" title="药用辅料业进入景气期 年需求高速增长" class="cmsHref_self">药用辅料业进入景气期 年需求高速增长</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>药用辅料业进入景气期 年需求高速增长</h1>
					<div class="source">
						<span>发布时间：2011-05-26</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据医药产业&ldquo;十二五&rdquo;规划，我国将鼓励企业投资药用辅料行业高速发展：目前国内药用辅料总市场规模达70亿元左右，在&ldquo;十二五&rdquo;期间，其市场需求量将每年增长15%- 20%。不少业内专家都表示，政府的高度重视对药用辅料生产企业而言意味着未来有更多的发展机会。</p>
<p>辅料开发首入科研课题</p>
<p>慧典市场研究认为，随着药品消费市场的不断扩大，未来药用辅料产业的总产值也将会水涨船高。业内预测，到2015年，市场规模将达到22500亿元，到2020年上升至48000亿元，成为世界第二大市场。药用辅料占据制剂成品成本的10%-20%。</p>
<p>根据2010年公布的中国药典，药用辅料就是生产药品和调配处方所使用的赋形剂和附加剂，是除活性成分以外，在安全性方面进行了合理评估并且包含在药物制剂中的物质。</p>
<p>我国现代药用辅料研究和应用起步较晚，由于辅料品种较少、规格不全、质量不稳定，严重影响了我国制剂的质量及研发，也可能会影响到药品的质量、安全性和有效性的重要成分。</p>
<p>正因如此，在科技部公布的&ldquo;十二五&rdquo;科技重大专项中，首次将辅料开发关键技术列入研究课题。此外，在2010版中国药典中，比2005年版新增了62个辅料品种，其数量比2005版中国药典中的辅料品种翻了近一番，并首次将对药用辅料的通用要求放到附录中。《药用原辅材料备案管理规定》并有望在今年开始实施。</p>
<p>除此之外，在《外商投资产业指导目录(征求意见稿)》中，专门列出了&ldquo;新型药用辅料的开发与生产&rdquo;。郭凡礼说，国家这些政策将对药用辅料研制、生产、经营、使用等各环节实施有效监管。对企业而言也意味着未来有更多的发展机会。</p>
<p>国内企业要改变&ldquo;小、散、乱&rdquo;局面</p>
<p>中投顾问医药业研究员郭凡礼在接受南都采访时说：目前我国药用辅料品种大约在500种以上，但是符合药用标准的只占不到30%，A股只有1家上市公司，整个药用辅料行业呈现出&ldquo;小、散、乱&rdquo;的特点。形成这种现状的原因是因为国内药用辅料产业集中度比较低、产业结构不合理、整个行业利润率不高等造成的。</p>
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