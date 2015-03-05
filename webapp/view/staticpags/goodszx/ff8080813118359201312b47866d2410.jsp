<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>渣打:中国猪肉价8月将平复 明年下半年供应过剩- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201312b47866d2410.jsp" title="渣打:中国猪肉价8月将平复 明年下半年供应过剩" class="cmsHref_self">渣打:中国猪肉价8月将平复 明年下半年供应过剩</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>渣打:中国猪肉价8月将平复 明年下半年供应过剩</h1>
					<div class="source">
						<span>发布时间：2011-07-15</span>
						<span>发布人：-新浪财经  </span>
					</div>
					<p><P>伦敦时间7月14日(北京时间7月14日)消息，英国渣打银行今日发布报告从年度同比的角度而言，中国猪肉价格可能在8月份平复，并可能在明年下半年反弹为供应过度。之后这一轮供应过度的形势可能持续18到21个月之久。</P>
<P>该机构认为：中国猪肉价格的提升主要源于宏观经济压力，当然供应短缺也是原因之一。</P>
<P>中国官方公布6月份通货膨胀增长率为6.4%。渣打分析称：短期内，年度猪肉价格同比增长必然成为追高通货膨胀指数的重要因素。按照现行的物价指数计算标准统计，食品占比30%，猪肉又占食品中的10%。这意味着在6月份的通货膨胀指数中，猪肉价格贡献了27%之多，将近三成。</P>
<P>从中国国家统计局的统计数据来看，全国范围内五花肉价格从6月底到7月初的10天内提升了1.7%。渣打认为：从供应层面来说，生猪的养殖成本正在升高。谷类的价格年增长率达到20%，而且并没有价格稳定的迹象。而燃料和交通成本也在以年均14%的比例提升。同时，民工劳工成本年增长率达10%到15%。</P>
<P>渣打银行预计称，五花肉价格将在7月份年度同比上升53%，低于6月份的66%。但是到8月份，猪肉价格应该进入平台期。</P>
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