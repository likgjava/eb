<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国液态乳制品需求将增四成- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131767356013187f3af7a0b0b.jsp" title="中国液态乳制品需求将增四成" class="cmsHref_self">中国液态乳制品需求将增四成</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>中国液态乳制品需求将增四成</h1>
					<div class="source">
						<span>发布时间：2011-08-02</span>
						<span>发布人：-中国畜牧兽医报  </span>
					</div>
					<p><P>尽管最近有关乳业新国标的争议不断，不过作为中国常温奶市场发展受益者的瑞士食品包装巨头利乐公司仍继续“唱好”中国乳业。据该公司日前发布的预测显示，未来十年中国将成增长最快的乳业市场之一，2013年中国液态乳制品需求量有望比2009年激增40%以上。</P>
<P>利乐公司日前发布的第四期《利乐乳业指数》报告预测，在2010年至2020年间，由于经济发展、城市化加速以及亚洲中产阶级购买力增强等因素，全球牛奶与其他液态乳制品的消费量有望增长30%左右。其中，亚洲市场消费量将增长45%，占全球半壁江山，而中国将成为未来十年世界增长最快的市场之一。据预计，2020年全球白奶及包括风味奶、饮用型酸奶、加糖炼乳、乳酸饮料、婴幼儿牛奶在内的其他液态乳制品的需求总量预计将会达到3500亿升左右，比2010年增加800亿升。</P>
<P>利乐方面表示，2013年中国液态乳制品需求量有望从约250亿升增长到约350亿升。其中乳酸饮料与婴幼儿牛奶消费分别上涨60%与50%以上，强劲带动乳制品需求的增长。而印度作为全球最大牛奶消费国，其人口总量将不断增长，潜在市场巨大。到2020年，中印两国的液态乳制品消费量将占全球总消费量的三分之一，而亚太地区的消费量将会继续领先于其他市场总和。指数报告说，去年发展中国家消费的白奶中约51%是散装出售，而预计到2014年，包装牛奶的消费比例将上升到55%，至2020年，这一数字更有望突破70%。</P>
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