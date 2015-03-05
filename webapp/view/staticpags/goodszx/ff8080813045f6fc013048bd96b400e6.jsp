<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>10/11年度 全球糖市供应短缺280万吨- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048bd96b400e6.jsp" title="10/11年度 全球糖市供应短缺280万吨" class="cmsHref_self">10/11年度 全球糖市供应短缺280万吨</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>10/11年度 全球糖市供应短缺280万吨</h1>
					<div class="source">
						<span>发布时间：2011-06-01</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据英国食糖贸易公司Czarnikow在其本周一发布的报告中认为，由于今年反常天气冲击了一些主要产糖国的食糖生产，全球糖市在10/11年度将出现供应短缺，提升了在近几周推动价格上涨至30年高位的供应忧虑。</p>
<p>报告称，估计拟于明年9月份结束的10/11制糖年全球的甘蔗糖产量将从8月份预期的1.379亿吨减至1.345亿吨，食糖市场将面临着连续第三年供给不足，初步估计供给不足量约有280万吨。</p>
<p>尽管09/10年度的种植面积将扩大，之前做出的糖市将重新过剩的预估将不会实现，供应将再度出现缺口。</p>
<p>因此Czarnikow预计价格将维持高位震荡局面。</p>
<p>巴西天气干燥令单产下滑，已经削减该国糖产量前景并给国内糖厂带来压力。</p>
<p>截至10月末，中南部甘蔗压榨较去年同期高6000万吨，但因甘蔗供应不足糖厂目前关闭，Czarnikow下调其对甘蔗供应量的预估到5.65亿吨。</p>
<p>其他产糖国如泰国10/11年度糖产量预估为730万吨，但印尼产量将低于上年度生产的240万吨，俄罗斯产量预估下降至290万吨，较8月预估值低30万吨。</p>
<p>但Czarnikow仍维持对印度产量预估在2300-2650万吨不变。据称，印度面临的主要挑战是产能利用率，此前降雨令本年度收榨推迟，可能令压榨期间缩短。据悉，仍有大量的糖厂因财政状况差将不会运营。</p>
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