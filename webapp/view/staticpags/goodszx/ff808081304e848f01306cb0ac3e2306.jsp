<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>铅价料因供应吃紧而持续上扬- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01306cb0ac3e2306.jsp" title="铅价料因供应吃紧而持续上扬" class="cmsHref_self">铅价料因供应吃紧而持续上扬</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>铅价料因供应吃紧而持续上扬</h1>
					<div class="source">
						<span>发布时间：2011-06-08</span>
						<span>发布人：-  </span>
					</div>
					<p><p>&ldquo;血铅事件&rdquo;发生后，中国政府开始严打涉铅污染产业，大批不符合环保要求的电池制造企业被勒令关闭，这在一定程度上打击了市场对铅的需求。然而，有分析人士指出，这种现象不会长期持续，同时铅供应的吃紧或继续推升铅价上扬。</p>
<p>有统计显示，中国浙江及广东省5月份超过300家铅酸电池厂因接受安全检查而关闭，而电池生产约占中国铅消耗量的七成。因此，有市场人士担心，这在短期内将影响对铅的需求。但中国产业界消息人士表示，这种情况在2011年余下的时间内料不会持续下去，因此长期而言，关闭电池厂不会大幅削减中国对铅的需求。</p>
<p>在供给方面，加拿大矿商Ivernia Inc 4月表示，其位于澳大利亚的Magellan铅矿将无限期全数进行维修。这在一定程度上，加大了铅供应趋紧的预期。</p>
<p>据路透社报道，在澳大利亚大铅矿无限期关闭，及铅酸电池替代品远未能完全取代的情况下，分析师预计，今年第四季度铅价将攀升至每吨2700-3000美元间。</p>
<p>业内人士指出，与多数其他基本金属相较之下，铅的需求较不易受到景气循环的影响。约有四至五成的铅用于制造替换式铅蓄电池，较易受到气候而非景气的影响。</p>
<p>&ldquo;铅在金融危机期间表现坚韧，这种情况将持续下去，冀望用于新车上的新电池普遍增加，会增强这股情势。&rdquo;法国农业信贷银行分析师Robin Bhar指出，&ldquo;铅的需求多数来自铅酸电池生产商，迄今尚无便宜的替代品，所以还是不能没有铅。&rdquo;</p>
<p>巴克莱资本分析师Gayle Berry看好铅在今年下半年的表现，并预计今年第四季度铅均价将在每吨3000美元。</p>
<p>截至本周一中午，伦敦金属交易所(LME)三个月期铅每吨报2475美元左右，而上周五收在2432美元。</p>
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