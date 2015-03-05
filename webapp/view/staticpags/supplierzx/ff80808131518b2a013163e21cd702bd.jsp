<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>出事高铁ATP系统供应商和利时称与其产品无关- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013163e21cd702bd.jsp" title="出事高铁ATP系统供应商和利时称与其产品无关" class="cmsHref_self">出事高铁ATP系统供应商和利时称与其产品无关</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>出事高铁ATP系统供应商和利时称与其产品无关</h1>
					<div class="source">
						<span>发布时间：2011-07-26</span>
						<span>发布人：-腾讯财经  </span>
					</div>
					<p><P>北京时间7月25日，和利时自动化技术有限公司(Hollysys AutomationTechnologies Ltd.)周一发表官方声明称，据来自多个信息源的数据分析显示，7月23日晚间在中国温州境内发生的动车D301和D3115次列车追尾事故发生时，该公司为这两辆列车提供的ATP系统均运行正常，无任何迹象表明该系统失灵导致此次事故发生。该公司同时声明，正式结果需要等到中国铁道部官方调查结果要结束后才能公布。</P>
<P>和利时同时表示，高铁信号系统主要包括两种产品：一个是在每列火车车头和尾部安装的ATP(列车自动控制防护系统)，另一个是基于地面沿铁路轨道设置的TCC(列车控制中心系统)。ATP以控制列车运行和接受停止运行信号，ATP接受信号后与地面TCC协同工作确保高铁运作的安全和畅通。</P>
<P>据媒体报道，和利时公司投资者关系负责人对外表示，和利时为出事的D301、D3115两趟列车提供车载设备，该公司已派技术人员至温州现场调查，调查结果显示，车载的列控(即列车控制)设备运作正常。这名负责人同时称，现场调查的情况显示故障原因是与路面控制设备有关，但具体调查结果尚未公开。</P>
<P>据悉，温州高铁追尾事故设施车辆D301、D3115两趟列车均采用的是CTCS-2列车自动控制系统。</P>
<P>据该公司网站介绍，北京和利时集团始创于1993年，主要从事自主设计、制造与应用自动化控制系统平台和行业解决方案，其中高速铁路自动化为其主营业务之一。该公司于2008年在美国纳斯达克上市，股票代码HOLI。</P>
<P>截至美东时间上午12：20，和利时在美国纳斯达克证券交易所上市股票大跌1.88美元至每股8.68美元，跌幅17.81%。</P>
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