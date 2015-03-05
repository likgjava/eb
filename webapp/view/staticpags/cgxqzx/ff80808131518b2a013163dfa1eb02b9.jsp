<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>LED显示屏采购的常见误区解析- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a013163dfa1eb02b9.jsp" title="LED显示屏采购的常见误区解析" class="cmsHref_self">LED显示屏采购的常见误区解析</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>LED显示屏采购的常见误区解析</h1>
					<div class="source">
						<span>发布时间：2011-07-26</span>
						<span>发布人：-OFweek半导体照明网  </span>
					</div>
					<p><P>一、寿命10万小时</P>
<P>LED材料厂家出具的技术资料表明LED发光体的寿命为理想状态下10万小时。理想状态指在实验室中恒压恒流状态下LED发光体从发光到完全不发光的时间，10万小时折合11年。</P>
<P>一个木桶的盛水的多少是由最低的木板决定的，LED显示屏目前使用的为民品级别的器件，使用寿命不超过8年。作为显示屏的功能是观看，当显示屏亮着只有晚上才能看清楚时是无法说明它是合格的、具备使用价值的。</P>
<P>一辆汽车可以开15年，如果闲置3年则报废。使用的环境和方法对产品的寿命影响很大。</P>
<P>二、 遵守国标</P>
<P>LED显示屏通用规范为1995年的部颁标准。至今还有许多公司号称符合国家标准，在科技发展的8年以后再看当时的标准，已经不是标准了。比方说失控点,国标为万分之三，以φ3.75室内双基色显示屏为例。一般做640x480标准分辨率的显示屏为7平米，每平米为43264点,按国标可以有90个失控点。这样的显示屏在今天谁还买单。</P>
<P>三、 软件全免费</P>
<P>显示屏行业普遍存在着中国企业的通病——只生产不研发。目前只有少数企业拥有正版的软件。现在使用盗版是违法的。</P>
<P>四、 价格低廉</P>
<P>要看性能价格比而不是单纯看价格。</P>
<P>五、 灰度等级</P>
<P>作为双基色和全彩色显示屏的灰度是一个重要指标。目前市场上充斥着许多1 6级和64级灰度的显示屏冒充256级灰度。其控制成本只有256级灰度的控制的1/5。最简单的方法是播放一个比较激烈的运动场面的VCD查看LED显示屏上是否能够看清楚。</P>
<P>六、 要买就要最好的</P>
<P>一切购买力来源于需要，满足需要并有一定的超前。盲目的追求将浪费很多资金购买了自己不需要的功能。</P>
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