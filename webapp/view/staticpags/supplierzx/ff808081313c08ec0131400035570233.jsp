<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>飞利浦业绩遭受严重打击 二季度亏13亿欧元- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec0131400035570233.jsp" title="飞利浦业绩遭受严重打击 二季度亏13亿欧元" class="cmsHref_self">飞利浦业绩遭受严重打击 二季度亏13亿欧元</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>飞利浦业绩遭受严重打击 二季度亏13亿欧元</h1>
					<div class="source">
						<span>发布时间：2011-07-19</span>
						<span>发布人：-网易  </span>
					</div>
					<p><P>7月19日消息，据国外媒体报道，飞利浦(Philips)作为全球最大的照明设备公司，全球三大医疗设备制造商之一，以及欧洲最大的消费性电子产品生产商，最近由于原材料成本上升、消费者信心下降、建筑市场的不景气，以及政府在医疗保健部门预算的削减等原因而受到了严重的打击。</P>
<P>据报道，飞利浦于18日宣布了一个出乎意料的财报，公司第二季度净损失为13亿欧元(18亿美元)。而这仅仅是在飞利浦两个关键性部门发出盈利警告后的几周时间内。</P>
<P>周一的消息为接下来的两个季度蒙上了惨淡的前景，同时降低了2013年公司三个核心业务的利润目标。飞利浦表示，将会削减5亿欧元的成本支出。这一措施可能帮助恢复投资者对于飞利浦的信心。四月份接管飞利浦的首席执行官万豪敦(FransvanHouten)并没有第一时间回应飞利浦的此次亏损。持有飞利浦股份的DeltaLloydAssetManagement资产管理人CharlesdeKock称，公司的削减成本措施为投资者带来了信心。他还预测，飞利浦已经完成了内部审查过程，回购20亿欧元股份和削减开支的措施是积极的。</P>
<P>万豪敦在周一的一份声明中表示，飞利浦并不期望经营风险和问题的同时存在，以及在经济环境并不确定的时候，能够在短期内进行改进。飞利浦表示，亏损13亿欧元反映了较弱的市场行情，公司将对四个业务部门降低盈利预期。</P>
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