<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>苹果取代诺基亚成世界头号智能手机生产商- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013182eb87b4010e.jsp" title="苹果取代诺基亚成世界头号智能手机生产商" class="cmsHref_self">苹果取代诺基亚成世界头号智能手机生产商</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>苹果取代诺基亚成世界头号智能手机生产商</h1>
					<div class="source">
						<span>发布时间：2011-08-01</span>
						<span>发布人：-新华网  </span>
					</div>
					<p><P>一份有关全球手机市场的最新调查报告显示，美国苹果公司在2011年二季度超过芬兰诺基亚公司，成为世界最大的智能手机生产商。</P>
<P>美国“战略分析”市场研究公司29日发表的调查报告说，苹果公司在二季度共卖出iPhone手机2030万部，占智能手机市场的18.5%，在市场份额、营业收入和利润上都居全球智能手机生产商首位。</P>
<P>韩国三星电子位居第二，在全球智能手机市场所占份额为17.5%。而一年前还是世界最大智能手机生产商的诺基亚公司跌至第三位，市场份额从2010年第二季度的38.1%跌至15.1%。</P>
<P>另据国际数据公司28日发布的报告，由于智能手机的普及，全球传统手机市场两年来首次出现萎缩，销量在今年二季度下降了4%。</P>
<P>国际数据公司说，二季度全球手机销量为3.65亿部，与去年同期相比增长11.3%，这低于一季度16.8%的增幅。</P>
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