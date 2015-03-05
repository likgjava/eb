<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>白银价格於六个月内升近一倍 长远需求仍存- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd2b575bb07d5.jsp" title="白银价格於六个月内升近一倍 长远需求仍存" class="cmsHref_self">白银价格於六个月内升近一倍 长远需求仍存</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>白银价格於六个月内升近一倍 长远需求仍存</h1>
					<div class="source">
						<span>发布时间：2011-05-09</span>
						<span>发布人：-  </span>
					</div>
					<p><p>白银价格於六个月内升近一倍，曾经到达49.51美元/盎司的水平。但在过去一周，随著芝加哥商品期货交易所(CME)八天内五次调高白银期货合约的交易保证金，这项措施无疑打击了以大量借款来交易白银的投资者，亦触发大型基金赎回，令银价於周一至四共跌27.65%。</p>
<p>索罗斯在去年曾表示黄金市场「根本上就是泡沫」，直至过去一个月，索罗斯旗下280亿美元的公司及对冲基金疯狂地抛售黄金和白银，更发表报告指长达九个月的飞涨，已经令金银价格进入一个极為危险的区域，言论令贵金属的投资人气受挫。除了索罗斯外，向来看好黄金的Passport资本公司对冲基金经理人布班克近来亦有抛售贵金属的动作，反映金银是次回调是过分升幅所致。</p>
<p>由於黄金价格上升不少，一些投资者便将资金转移到较便宜的白银，而且银价在过去一年涨幅超越黄金，增加了市民对白银的兴趣，令白银的投资需求不断扩大，亦同时令价格变得比以往更加波动。自去年10月，白银ETF存仓量在不断增加，而且增长幅度超越了黄金，显示投资者对投资白银的兴趣更胜於黄金。</p>
<p>除了投资及珠宝需求，工业需求亦影响著白银未来的走势，白银在工业上一直用於半导体和感光材料的製造。随著全球环境问题和能源危机的步步逼近，新能源和低碳技术成為了新的经济增长点;白银作為近年来低碳经济、新能源技术中的最核心原料之一，在可再生能源及新能源、煤的清洁高效利用等方面都是举足轻重的金属原材料。市场预计2010年白银的工业需求将大幅增长20%，预料2011年增长还会继续，所以长远来看，白银的走势依然正面，建议当银价跌至30美元后可吸纳。</p>
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