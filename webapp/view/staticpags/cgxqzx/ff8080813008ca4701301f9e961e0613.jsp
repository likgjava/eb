<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>20家企业赴山东直接采购大蒜- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813008ca4701301f9e961e0613.jsp" title="20家企业赴山东直接采购大蒜" class="cmsHref_self">20家企业赴山东直接采购大蒜</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>20家企业赴山东直接采购大蒜</h1>
					<div class="source">
						<span>发布时间：2011-05-24</span>
						<span>发布人：-  </span>
					</div>
					<p><p>22日，由中商流通三十人论坛(G30)和济宁市政府组织&ldquo;农超对接&rdquo;活动，包括北京物美集团、北京新发地批发市场等在内的20家企业签订大蒜直采合作协议。其中沃尔玛超市还在金乡建立大蒜基地，从金乡蒜田收获的新鲜有机大蒜可以直达沃尔玛超市，实现田间到超市无缝对接。</p>
<p>一位金乡大蒜商户称，去年&ldquo;蒜你狠&rdquo;今年&ldquo;蒜你贱&rdquo;，目前正值大蒜收获期，但&ldquo;中国蒜都&rdquo;山东金乡的蒜农却犯了愁，因为田间大蒜卖价已降到1.1元-1.3元/斤，不少收购商仍嫌贵，而去年同期最低从未低于2元/斤。针对市民未感觉市场价下降的问题，该蒜商表示，农产品要经过收购环节、运输环节、批发环节，再通过&ldquo;中间贩子&rdquo;和&ldquo;小贩子&rdquo;，才能到消费者手上，农产品价格上涨主要是在流通环节。</p>
<p>连锁经营协会副秘书长陈烁表示，农超对接有着很深层次的社会意义，首先一条就是有利于抑制物价快速上涨。他介绍，农超对接的交易价平均比批发市场低4.83%，门店销售毛利率平均为14.1%，相比个体商户20%-30%的平均毛利率，显然更有利于抑制通胀平抑物价。</p>
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