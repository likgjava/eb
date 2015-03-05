<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>高铁配套设备供应商面临洗牌- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013163e12fe402bb.jsp" title="高铁配套设备供应商面临洗牌" class="cmsHref_self">高铁配套设备供应商面临洗牌</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>高铁配套设备供应商面临洗牌</h1>
					<div class="source">
						<span>发布时间：2011-07-26</span>
						<span>发布人：-中国证券报  </span>
					</div>
					<p><P>尽管“7·23”甬温线特别重大铁路交通事故调查结论尚未出来，但高铁相关上市公司已开始撇清与事故的关系。业内人士认为，从高铁板块的走势不难看出，在调查结论出炉后，相关上市公司的业绩将会不可避免地受到冲击，配套设备供应商可能会迎来一轮洗牌。</P>
<P>针对公众最为关注的此次事故原因，铁道部新闻发言人王勇平在回答记者提问时表示，初步了解，事故原因是雷击造成设备故障导致的。详细的情况，正在进一步调查分析之中。相关业内人士表示，具体原因要等国家安监总局的结论出来。</P>
<P>中国证券报记者拨打了部分高铁相关上市公司电话，这些公司大多表示，公司产品跟出事动车组没有关系。</P>
<P>据悉，此次出事的两动车均出自中国南车，一列来自南车旗下的青岛四方，一列来自南车与庞巴迪的合资公司四方庞巴迪。方正证券认为，若铁路项目建设进程因事故推后、延缓招标，中国南车等将受直接影响。</P>
<P>与中国南车、中国北车相比，其他配套设备提供商正饱受市场质疑。辉煌科技、世纪瑞尔、佳讯飞鸿等公司周一处在停牌状态。特瑞德周一下午复牌，公司声称，在事故发生后，公司第一时间派售后人员深入现场，进行了设备相关的巡查。公司为该铁路提供的电力设备产品稳定运行，未出现任何质量问题，也未发现公司产品与该动车追尾事故相关。</P>
<P>根据公开资料，辉煌科技主营业务为铁路信号通信领域产品的研制开发、生产及销售;世纪瑞尔主营业务为铁路行车安全监控系统产品的开发、生产、销售;佳讯飞鸿主营业务为通信、信息、控制一体化的指挥调度系统及全面解决方案;鼎汉技术主营业务为轨道交通电源系统的研发、生产、销售、安装和维护;时代新材主营业务之一是为铁路提供高分子材料减振降噪弹性元件。</P>
<P>业内人士表示，铁道部在设备采购等方面的格局可能会发生变化，此次动车事故可能导致有些相关设备提供商退出高铁配套设备供应商队伍，配套设备供应商可能会迎来一波洗牌，这将对部分公司的业绩造成重要影响。</P>
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