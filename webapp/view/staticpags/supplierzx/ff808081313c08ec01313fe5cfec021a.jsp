<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>家具协会为达芬奇开脱：出口再进口合法就行- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313fe5cfec021a.jsp" title="家具协会为达芬奇开脱：出口再进口合法就行" class="cmsHref_self">家具协会为达芬奇开脱：出口再进口合法就行</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>家具协会为达芬奇开脱：出口再进口合法就行</h1>
					<div class="source">
						<span>发布时间：2011-07-19</span>
						<span>发布人：-财经网  </span>
					</div>
					<p><P>据《东方早报》报道，中国家具协会理事长朱长岭在谈及民众对“达芬奇”先出口再进口的质疑时表示，其实不光是家具，现在很多其他商品都是这样一个路径。如果法律允许的话，就是可以的。</P>
<P>他指出，这种情况叫负进口。负进口产品，即产地在中国，运出去重新进关，获得一个国际品牌身份，以此卖高价。但这不能说是“原装进口”，应告知消费者产品的产地。对于消费者来说，应该辨别家具产品的产地，进口未必好，国产也不差。目前，中国家具制造水平完全能够达到国外的水平，甚至高于国外水平。</P>
<P>他表示，达芬奇不是生产厂家，而是代理商。大家的质疑其实存在一个常识问题。现在很多国际性品牌所生产的产品，产地都来源于世界不同的地方，只要标明产地就可以了。</P>
<P>朱长岭认为，“达芬奇”造假事件反映出行业内个别企业还存着一定问题，但不具有普遍性。不能说国内所卖的进口家具都是以假乱真。</P>
<P>他表示，整体来看，达芬奇事件涉及三个关键问题，一是是否为意大利本地生产。二是产品质量究竟有无问题。三是有没有告知消费者家具的产地，欺骗消费者。</P>
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