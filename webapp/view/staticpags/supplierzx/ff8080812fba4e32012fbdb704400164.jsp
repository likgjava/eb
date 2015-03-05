<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>宏达电与亚太移动联盟Conexus签订采购计划- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fba4e32012fbdb704400164.jsp" title="宏达电与亚太移动联盟Conexus签订采购计划" class="cmsHref_self">宏达电与亚太移动联盟Conexus签订采购计划</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>宏达电与亚太移动联盟Conexus签订采购计划</h1>
					<div class="source">
						<span>发布时间：2011-05-05</span>
						<span>发布人：-网易科技报道  </span>
					</div>
					<p><p>　　5月5日消息，经由台湾运营商远传电信牵线，Conexus亚太移动联盟明(6)日将与宏达电签订合作备忘录，这是Conexus首次启动联合采购计划，宏达电为首家合作厂商，抢占每年5000万部智能手机的采购商机。</p>
<p>　　Conexus亚太移动联盟是由亚洲各国移动运营商组成，会员涵盖13个国家及地区，共有11个电信成员，拥有超过3.1亿用户，主要成员包含日本NTT DoCoMo、韩国KT、新加坡StarHub、越南Vinaphone、印度BSNL与MTNL、菲律宾SMART、香港和记电讯等。</p>
<p>　　在远传电信牵线下，宏达电成为Conexus移动联盟第一个签订合作备忘录的智能手机厂商，双方将于6日将举行签约记者会，除Conexus行动联盟主席，印尼第二大电信业者PT Indosat代表Kaizad Heerjee来台，Conexus 所有电信成员也都将派代表参与，远传电信行销事业处执行副总何永生以及宏达电亚太区副总董俊良也将亲自出席。</p>
<p>　　何永生表示，过去Conexus移动联盟成员均积极推动移动网络语音及数据漫游合作，近年随着智能手机热销，Conexus移动联盟董事会也开始讨论联合采购的可能性，虽然各个运营商移动网络技术发展进度不一，联合采购的难度很高，但仍积极促成联合采购计划，可望进一步降低成本。</p>
<p>　　据了解，在Conexus移动联盟内，有几家运营商针对移动网卡联合采购，但联盟全体成员一起采购，则是第一次，预估宏达电智能手机以及平板电脑都可望成为Conexus成员联合采购的重点产品。</p>
<p>　　Conexus移动通讯联盟与宏达电签署全面区域性合作协定后，双方除合作联合采购外，未来将进一步发展深入的定制化手机。</p>
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