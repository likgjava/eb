<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>亚洲丙烯市场价格开始走低- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/goodszx.jsp" title="供货资讯" class="cmsHref_self">供货资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131c418af0131caf0c736001e.jsp" title="亚洲丙烯市场价格开始走低" class="cmsHref_self">亚洲丙烯市场价格开始走低</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>亚洲丙烯市场价格开始走低</h1>
					<div class="source">
						<span>发布时间：2011-08-15</span>
						<span>发布人：-  </span>
					</div>
					<p><P>据报道，亚洲丙烯市场价格开始对本周初期国际原油价格大幅下跌作出反应而走低，本周国际石油价格的急剧下跌至低于80美元/桶。昨天，亚洲现货丙烯市场FOB韩国价格下跌80美元/吨。8月初亚洲现货丙烯价格还较7月时上涨220美元/吨。8月的第一周，亚洲丙烯价格保持坚挺，从上周五开始出现下跌行情。</P>
<P>经济前景不明朗还在继续影响着石油市场走势，特别是7月份中国的原油进口量下降至七个月来的最低，预计未来几个月中国的原油进口量可能进一步下降，因为三季度国内有总计产能707800桶/日的7家大型炼油厂将停工检修。在下游方面，亚洲和中国PP市场已经产生联动，昨天中国国内PP生产商已下调PP市场价格200-400元人民币/吨(约合31-62美元/吨)。</P>
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