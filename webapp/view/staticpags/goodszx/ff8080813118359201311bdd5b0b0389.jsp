<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>全球需求走弱 面板价格涨势喊停- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201311bdd5b0b0389.jsp" title="全球需求走弱 面板价格涨势喊停" class="cmsHref_self">全球需求走弱 面板价格涨势喊停</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>全球需求走弱 面板价格涨势喊停</h1>
					<div class="source">
						<span>发布时间：2011-07-12</span>
						<span>发布人：-中华液晶网  </span>
					</div>
					<p><P>群智咨询(Sigmaintell)预估，2011年中国6大电视品牌厂商的液晶电视的出货量将达3720万台，同比去年增长12%。而Wits View认为今年全球液晶电视整体需求量已从年初的2.25亿台、下调到2.15亿台，近期可能再度下修到2.04-2.07亿台，同比去年的增长率也下降到11%左右。</P>
<P>分析今年全球液晶电视市场出货表现不如预期之主要原因，WitsView认为，主要因为日本311地震和节能补贴政策Eco Point Initiative结束，造成日本国内需求减缓，加上全球经济复苏力道不佳、希腊债务危机影响，导致市场信心不足、各品牌原订年度销售目标或将无法达成。</P>
<P>此外，中东及非洲政局动荡，使得原油价格飙涨，而全球气候变迁，又造成玉米、棉花等原物料价格上扬，在全球通货膨胀问题加剧情况下，消费者信心遭受重创。而平板电脑的兴起，在某种程度上，也对其他产品的消费需求产生排挤效应。</P>
<P>由于液晶电视市场表现不如预期，面板价格的涨势，也在今年6月份暂时喊停。WitsView强调，面板厂可能要思考如何有效控制产能，让整体供需达到适度的均衡，才能支撑面板价格，避免面板价格持续下跌。</P>
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