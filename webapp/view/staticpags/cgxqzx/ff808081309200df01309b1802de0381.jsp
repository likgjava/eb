<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>浙江采购人参与政采有限时门槛- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b1802de0381.jsp" title="浙江采购人参与政采有限时门槛" class="cmsHref_self">浙江采购人参与政采有限时门槛</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>浙江采购人参与政采有限时门槛</h1>
					<div class="source">
						<span>发布时间：2011-06-17</span>
						<span>发布人：-  </span>
					</div>
					<p><p>今后，浙江省采购单位在采购项目委托、采购结果确定以及采购资金支付等环节都将受到&ldquo;限时&rdquo;制约。</p>
<p>在浙江省日前出台的《关于进一步创新管理提高政府采购工作效率和服务水平的意见》(以下简称《意见》)中明确指出，要加强对采购人主体的监管，除要求采购人明确政府采购的专司部门和专职人员外，针对采购代理机构和供应商反映较多的不按时委托、不按时确认、不按时支付等问题，从制度上予以明确。</p>
<p>在项目委托上，《意见》要求采购人要及时委托采购项目，在采购计划批复后15个工作日内，必须依法委托采购代理机构或自行组织采购，逾期未委托或实施采购的，将由系统提醒通知;采购计划批复3个月后仍无故未委托或实施采购的，系统将自动冻结该采购计划。</p>
<p>对于确认环节存在的问题，《意见》强调无论是集中采购项目或分散委托采购项目都应当在收到文件之日起的5个工作日内予以确认完毕。</p>
<p>在拖延资金支付方面，《意见》要求采购人要按时支付资金。政府采购诚信指数在3星以上的注册供应商，政府采购首付款比例可提高到合同金额的50%，验收通过后可全额付款;其他供应商的首付款比例应不低于30%，验收通过后的付款比例不低于70%，尾款支付时间最长不超过验收通过后1年。</p>
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