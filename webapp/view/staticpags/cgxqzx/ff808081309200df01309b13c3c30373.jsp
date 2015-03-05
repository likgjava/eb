<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>浙江引入成交控制价防高价采购- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b13c3c30373.jsp" title="浙江引入成交控制价防高价采购" class="cmsHref_self">浙江引入成交控制价防高价采购</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>浙江引入成交控制价防高价采购</h1>
					<div class="source">
						<span>发布时间：2011-06-17</span>
						<span>发布人：-  </span>
					</div>
					<p><p>浙江省允许采购单位在办理委托时,在预算执行确认金额内再提出一个成交控制价作为中标成交的最高限价,如果最终中标价格超过该控制价,采购单位可以不予确认。</p>
<p>为进一步创新管理、完善制度,解决当前政府采购执行中存在的价格虚高问题,浙江省于日前出台了《关于进一步创新管理提高政府采购工作效率和服务水平的意见》(以下简称《意见》),提出要明确采购项目的成交控制价格,建立采购前专家审查机制,防止个别采购单位通过采购需求的倾向性抬高政府采购最终成交价格。</p>
<p>《意见》规定,采购单位在办理委托时,允许在预算执行确认金额内再提出一个成交控制价(或市场暂估价),采购代理机构可将该成交控制价作为中标成交的最高限价予以控制,如果推荐的中标成交供应商报价超过该控制价,评审小组应当予以特别说明,否则采购单位可以不予确认。同时,采购代理机构对采购项目的需求进行审查后,还可以根据有关适用情形,组织评审专家对采购项目需求进行审查,并可将专家建议价格作为项目的预算控制价,防止个别采购单位通过采购需求的倾向性抬高政府采购最终成交价格。</p>
<p>同时,为了有效解决政府采购价格虚高问题,浙江省还在《意见》中明确规定要从严把好采购预算编制关,并引入第三方加强对协议供货产品价格的监控,从多方面入手,注重管理与市场双管齐下,控制采购价格。此外,对部分协议价格居高不下的商品,浙江省下一步将探索实行多品牌竞价。</p>
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