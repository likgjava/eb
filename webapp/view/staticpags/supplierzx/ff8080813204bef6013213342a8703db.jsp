<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>三大动力助推工程机械产业发展提速- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813204bef6013213342a8703db.jsp" title="三大动力助推工程机械产业发展提速" class="cmsHref_self">三大动力助推工程机械产业发展提速</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>三大动力助推工程机械产业发展提速</h1>
					<div class="source">
						<span>发布时间：2011-08-29</span>
						<span>发布人：-  </span>
					</div>
					<p><P>"十二五"时期，稳健向上的中国经济将为工程机械行业的发展提供重要而持续的动力，受益于此，加之企业创新能力及综合竞争力的不断提升，我国工程机械行业将继续保持高位运行。湘财证券研究所机械行业小组分析师刘正认为，影响工程机械行业需求的有三大政策动力:</P>
<P>首先，保障性安居工程是工程机械行业发展的重要推动力之一。2011年2月，住房和城乡建设部与各省、自治区、直辖市以及计划单列市签订《保障性住房目标责任书》，需完成今年1000万套保障房的建设任务。依据住房和城乡建设部的数据，2010年保障性安居工程建设规模达到580万套，投资超过8000亿元。而2011年保障性安居工程建设1000万套，新增投资超过6000亿元。以此计算，2011年保障性安居工程投资或将达到1.4万亿元。</P>
<P>其次，铁路投资将超3万亿元，也是工程机械的重要推动力。我国综合运输体系的“十二五”专项规划显示，“十二五”时期将建成4万公里快速铁路网，预计“十二五”时期铁路投资将达3.5万亿元左右。</P>
<P>再者，未来10年水利建设投资将有4万亿元，是工程机械行业发展的第三大推动力。出于对“三农”发展、节能减排等诸多方面的考虑，专家预计，未来10年我国基础设施固定资产投资重点将从铁路、公路转移到水利建设。</P>
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