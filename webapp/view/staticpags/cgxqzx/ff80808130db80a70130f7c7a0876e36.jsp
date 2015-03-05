<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>无锡并单采购破解价格垄断- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130f7c7a0876e36.jsp" title="无锡并单采购破解价格垄断" class="cmsHref_self">无锡并单采购破解价格垄断</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>无锡并单采购破解价格垄断</h1>
					<div class="source">
						<span>发布时间：2011-07-05</span>
						<span>发布人：-中国政府采购报  </span>
					</div>
					<p><P>2011年上半年，无锡市政府采购通过在台式计算机、投影机、空调等品目的品牌竞价中采取分档管理模式，并引入并单采购，最终使成交价比协议供货价平均节约了30%-40%。</P>
<P>据无锡市财政局政府采购管理处处长华静娴介绍，并单采购是为争取更大的批量优惠、降低单位项目采购费用，由无锡市集中采购机构将多个部门、单位的零星计划合并，对采购人申请的采购数量在10台以上或金额在10万元以上的台式计算机、便携式计算机、投影机、显示器、空调等部分品目，不再随报随采，原则上每月集中批量竞价采购一次，以发挥集中采购规模效应。</P>
<P>同时，在无锡市现有的协议供货品目中，对单价较大的PC服务器、复印机、速印机等品目，以金额进行切分，10万元以下的零星小额采购，由采购人在协议供货范围内进行询价协商;10万元-50万元的，由采购中心进行并单采购;50万元以上的，则采用公开招标等方式采购。如果单价较低，就以台数进行切分，分为10台以下(如台式计算机)、10台-200台、200台以上等3档，具体操作办法对应上述的金额切分的做法。</P>
<P>此外，对于下一步工作思路，华静娴表示无锡市政府采购将在2011年年内开发网上竞价项目系统，实现对协议供货项目的电子议价和电子反拍。</P>
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