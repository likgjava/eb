<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>7月25日豆粕市场预测及采购建议- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01315f07623b0010.jsp" title="7月25日豆粕市场预测及采购建议" class="cmsHref_self">7月25日豆粕市场预测及采购建议</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>7月25日豆粕市场预测及采购建议</h1>
					<div class="source">
						<span>发布时间：2011-07-25</span>
						<span>发布人：-博亚和讯  </span>
					</div>
					<p><P>经历双边整理，周五CBOT大豆期货市场小幅收高。8月大豆收盘1380.25美分，和前一日持平;11月大豆收盘1388.25美分，上涨0.25美分。 CBOT豆类产品期货市场大多收高。12月豆油收盘57.48美分/磅，上涨0.1美分;12月豆粕收盘369.3美元/短吨，上涨0.4美元。</P>
<P>现货方面，上周五国内各地区豆粕价格以稳为主。从现货市场的表现来看，近期养殖业需求对豆粕市场有着强有力的支撑，豆粕现货稳中偏强，大多数稳定极个别报价略有微幅上涨，加上养殖业需求对豆粕市场有所恢复，目前油厂因前期合同较多，忙于执行合同而不急于出货，在稳固的基础支撑下，坚挺价格，但是近期油厂挺价心态仍然存在，部分油厂仍存有高报低走迹象，现货跟涨不跟跌的走势延续。不过，近期豆粕的强势主要受需求预期良好的提振，但终端采购意愿的逐渐减弱。豆粕现货市场中尽管油厂挺价依旧，但买盘并不积极，更多贸易商以观望为主，而豆粕销售情况出现了明显好转，市场中主动拿货情绪重新复苏。而经过连续挺价，当前产区油厂压榨利润已经得到了明显复苏。</P>
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