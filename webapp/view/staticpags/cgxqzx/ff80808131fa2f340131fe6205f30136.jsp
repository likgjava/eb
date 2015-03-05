<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>四川宜宾财政全面提升政府采购监督管理水平- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131fa2f340131fe6205f30136.jsp" title="四川宜宾财政全面提升政府采购监督管理水平" class="cmsHref_self">四川宜宾财政全面提升政府采购监督管理水平</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>四川宜宾财政全面提升政府采购监督管理水平</h1>
					<div class="source">
						<span>发布时间：2011-08-25</span>
						<span>发布人：-  </span>
					</div>
					<p><P>一是继续完善市级协议供货制度改革，加强对协议供货产品的价格的动态监控，并积极探索本市范围内通用办公设备的区域联动的协议供货机制。</P>
<P>二是进一步完善政府采购方式变更审批，改进进口产品审核工作方式。</P>
<P>三是开展对市级集中采购代理机构的考核工作，促进集中采购代理机构不断完善内部监督制约机制，提高采购效率和服务水平。</P>
<P>四是进一步完善评审专家抽取机制，探索有效的专家考核评议制度，严格约束专家评审行为，进一步加大区域内采购评审专家的征集力度。</P>
<P>五是加强对采购需求的审核监督，防止超标采购、豪华采购等现象的发生。</P>
<P>六是依法开展投诉处理工作，加强对质疑处理工作的监督和指导，加大对相关违法违规行为的处罚和公开曝光力度。继续做好对采购人、集中采购代理机构、区县政府采购监督管理人员和评审专家的宣传培训工作，保证政府采购宣传培训工作取得实效，为政府采购制度改革营造良好氛围。</P>
<P>七是及时动态的提供采购信息统计数据，为领导决策提供依据。</P>
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