<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>亚洲现货橡胶价格走高 因需求强劲- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081303fbeb70130439082b80387.jsp" title="亚洲现货橡胶价格走高 因需求强劲" class="cmsHref_self">亚洲现货橡胶价格走高 因需求强劲</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>亚洲现货橡胶价格走高 因需求强劲</h1>
					<div class="source">
						<span>发布时间：2011-05-31</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据新加坡5月30日消息，亚洲现货橡胶价格周一大多上涨，一新加坡交易商称，因预期需求量超过最大出口国泰国的供应量。</p>
<p>他称：&ldquo;生产商正寻求提高价格，不过消费者仍愿意承受，因需求维持强劲。&rdquo;其并称，8月船期的SIR20等级橡胶报价为每吨4,665美元。</p>
<p>6月/7月装船的泰国3号烟片胶RSS3报每公斤520美分，上一交易日报每公斤510美分。</p>
<p>6月/7月装船的泰国轮胎级标准胶STR20报每公斤475美分，上一交易日报每公斤468-470美分。</p>
<p>7月/8月装船的印尼轮胎级标准胶SIR20报每公斤466美分，上一交易日报每公斤464-465美分。</p>
<p>7月/8月装船的马来西亚轮胎级标准胶SMR20报每公斤472美分，上一交易日报每公斤466美分。</p>
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