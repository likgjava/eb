<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>苹果今年二季度以5.6%市场份额赚66.3%利润- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013182ce281c00eb.jsp" title="苹果今年二季度以5.6%市场份额赚66.3%利润" class="cmsHref_self">苹果今年二季度以5.6%市场份额赚66.3%利润</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>苹果今年二季度以5.6%市场份额赚66.3%利润</h1>
					<div class="source">
						<span>发布时间：2011-08-01</span>
						<span>发布人：-京华时报  </span>
					</div>
					<p><P>上周末，多家全球市场调查公司发布的数据显示，苹果公司在今年第二季度以5.6%的全球手机市场份额，赚得全球手机市场利润总额的近三分之二。</P>
<P>IDC发布的数据显示，今年第二季度，排在全球市场份额前五位的手机品牌分别是诺基亚、三星、LG、苹果和中兴通讯，其中诺基亚虽然仍保有24.2%的市场份额，但较去年同期33.8%的份额相比下滑不少;苹果公司则在去年同期2.6%的市场份额基础上猛增了一倍多，达到5.6%。</P>
<P>虽然诺基亚在市场份额上仍领先苹果较多，但市场研究机构Asymco的报告显示，市场份额仅为5.6%的苹果公司，却占去了二季度全球手机市场利润总额的66.3%，这凸显苹果主打的高端智能手机领域的高利润率。Asymco的分析师认为，未来手机厂商盈利的唯一办法就是将智能手机产品线做强。</P>
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