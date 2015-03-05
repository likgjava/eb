<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>光伏需求不佳时晶圆代工业务填补空缺- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b3e80d00c0.jsp" title="光伏需求不佳时晶圆代工业务填补空缺" class="cmsHref_self">光伏需求不佳时晶圆代工业务填补空缺</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>光伏需求不佳时晶圆代工业务填补空缺</h1>
					<div class="source">
						<span>发布时间：2011-06-01</span>
						<span>发布人：-  </span>
					</div>
					<p><p>台湾老牌6吋晶圆代工厂茂硅最近接单畅旺，近期产能利用率冲上满载，订单能见度达第三季。与此同时，茂硅规划进军8吋晶圆代工业务，最快明年第一季装机试产。</p>
<p>茂硅现阶段光伏电池与晶圆代工营收比约6比4，太阳能产能利用率力保在六成，若太阳能第三季维持目前状态，没有恶化，第三季业绩将会较第二季成长一成。茂硅表示，晶圆代工除现有生产绝缘栅双极晶体管(IGBT)，也投入研发更先进的高压晶体管(SuperJunctionMosfet)，利润更好，目前晶圆代工产能利用率已达满载，订单能见度至第三季。</p>
<p>茂硅湖口太阳能新厂日前投产，该厂区最大产能可达200MW，现已装设100MW机台量产，加上竹科厂区60MW产能，总产能为160MW，目标年底透过制程提升，在不增加机台前提下，将总产能增至200MW。</p>
<p>茂硅董事长陈民良表示，近期太阳能市场需求较弱，但这是产业短期的修正，长期仍看好。茂硅除练好太阳能电池基本功，将平均转换率提升至16.7%外，近期晶圆代工业务接单旺，适时填补太阳能需求不佳的缺口。</p>
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