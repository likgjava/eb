<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国将成太阳能电池板主导生产国- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013107678bd00c12.jsp" title="中国将成太阳能电池板主导生产国" class="cmsHref_self">中国将成太阳能电池板主导生产国</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>中国将成太阳能电池板主导生产国</h1>
					<div class="source">
						<span>发布时间：2011-07-08</span>
						<span>发布人：-百方网  </span>
					</div>
					<p><P>国际评级机构标准普尔(Standard&amp;Poor’s)预测，中国太阳能面板制造商市场份额将提升，这是由于产能过剩和价格下跌已迫使弱势外国竞争对手倒闭或寻求并购。</P>
<P>标普信用分析师Swami Venkataraman在一份研究报告中表示，诸如英利绿色能源控股有限公司(Yingli Green Energy Holding Co.)和天合光能有限公司(Trina SolarLtd.)等受益于政府支持的中国制造商正在压低光伏电池板价格、压缩利润率，以施压于面临更高成本的欧洲竞争对手。</P>
<P>他表示，中国已成为太阳能电池板主导生产国。高成本、单机面板制造商的退出将在未来几年内成为必然，除非需求暴增并使所有生产企业茁壮成长。</P>
<P>但Venkataraman表示，美国亚利桑那州的第一太阳能公司(First SolarInc.)很可能仍然是市场的领导者，因为其碲化镉薄膜太阳能电池的成本与中国制造多晶硅太阳能电池板相比至少低30%。</P>
<P>他表示，即使多晶硅价格大幅下跌至低于当前现货价格50美元/公斤，第一太阳能公司仍然较成本最低的中国制造商有着明显优势。</P>
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