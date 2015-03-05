<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>台湾考虑禁止采购华为核心网设备- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130f7c186db6e0e.jsp" title="台湾考虑禁止采购华为核心网设备" class="cmsHref_self">台湾考虑禁止采购华为核心网设备</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>台湾考虑禁止采购华为核心网设备</h1>
					<div class="source">
						<span>发布时间：2011-07-05</span>
						<span>发布人：-C114中国通信网  </span>
					</div>
					<p><P>北京时间7月4日下午消息(高德仁)据《台北时报》报导，台湾国家通讯传播委员会(以下简称NCC)在六月底表示，由于国家安全问题，电信运营商不得使用华为科技所生产的电信核心网络设备。</P>
<P>一些台湾电信服务提供商，包括亚洲-太平洋电信(Asia-Pacific Telecom)，威宝电信，台湾大哥大电信以及远传电信，都有采购华为的设备，进口产品的范围用于部分本地区域网络和核心网络。</P>
<P>据了解，部分进品产品仍然被台湾海关扣押。</P>
<P>自印度和美国对涉及中国公司的交易进行封锁后，台湾对中国间谍能力的恐惧提高，认为其产品危害国家安全，对电信业能否使用华为的产品也产生了争议。</P>
<P>NCC委员会发言人陈正仓表示，行政院已要求他以一个合法和谨慎的态度处理此事。 他说，计划采购电信核心网络设备的电信公司，必须得到NCC和调查局两个政府机构的批准。由于国家安全问题，电信公司可以考虑其他提供类似技术的供应商。</P>
<P>他还建议，经济部应明确列出所有禁止从中国进口的电信产品，使电信公司能够遵守列表，从而避免任何混淆。</P>
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