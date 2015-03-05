<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>武汉光纤行业每年采购200万吨涂料- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="采购焦点资讯" class="cmsHref_self">采购焦点资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131fa2f340131fe6600eb013b.jsp" title="武汉光纤行业每年采购200万吨涂料" class="cmsHref_self">武汉光纤行业每年采购200万吨涂料</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>武汉光纤行业每年采购200万吨涂料</h1>
					<div class="source">
						<span>发布时间：2011-08-25</span>
						<span>发布人：-  </span>
					</div>
					<p><P>作为光纤国内第一大生产基地，武汉每年的光纤产量已达到3500万芯公里，占全国产量的40%。但与之形成对比的是，每年所需约200万吨涂料，全部从江浙、北京和国外采购。</P>
<P>“目前武汉光纤行业面对的最大难点，还是产业配套问题”，武汉烽火科技集团人士告诉记者：“不说高精尖的东西，就说光纤外表的涂料，武汉都没有一家上规模的企业能够提供。因此，我们只能向江浙地区或国外进货，这直接导致了运输费用的增加和供货周期的延长。”</P>
<P>据了解，在光纤制造业中，光纤涂料成本仅次于光棒，占原材料总成本的10%左右。以生产1芯公里光纤需要55克涂料计算，仅武汉本土光纤企业，每年就创造出近200亿元的庞大市场。</P>
<P>长飞光纤光缆有限公司人士透露：“早在2008年，就曾经试用过湖北省化学研究院生产的光纤涂料。但由于颗粒较大、杂质较多，最后没有选用。”</P>
<P>曾参与光纤涂料开发的化学研究院佘万能告诉记者：“我们从80年代就研究光纤涂料，生产线投入了上千万元。如果满负荷生产，可以满足本土涂料需求。”</P>
<P>一面是光纤企业的“盈盈期盼”，一面却是涂料研发单位的“有志难伸”。业内人士分析：“要解决产业配套的老大难问题，供需双方都需要作出努力。光纤企业应该给出机会，涂料在技术上已经没有难点，但工业生产中一般需要1-2个月的磨合期;涂料企业也不应被动等市场，而应该主动改进技术抢市场。”</P>
<P>武汉市信息产业办人士介绍：“不少产业园在招商初期都打着产业配套的旗号，而在实际工作中较难落实，原因就在于回报率、税收额等指标盖过了产业配套的需求。”</P>
<P>记者了解到，长飞光纤已经考虑加大与国内涂料商的合作力度。烽火科技人士则表示：“希望政府在招商引资时，多考虑产业配套问题，未来光纤行业的竞争将是产业链的竞争。”</P>
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