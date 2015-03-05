<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>粗钢日产量首次突破200万吨- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a701310757d8eb0bf0.jsp" title="粗钢日产量首次突破200万吨" class="cmsHref_self">粗钢日产量首次突破200万吨</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>粗钢日产量首次突破200万吨</h1>
					<div class="source">
						<span>发布时间：2011-07-08</span>
						<span>发布人：-第一财经日报  </span>
					</div>
					<p><P>钢材产量再创新高。</P>
<P>中国钢铁工业协会昨天公布的数据显示，6月下旬全国76家重点企业粗钢产量为1692.31万吨，日均产量为169.23万吨，较上一旬增产3.21%;3家企业未报产量。据此估算，当旬全国生产粗钢2018.05万吨，日均产量为201.8万吨，首次突破200万吨。</P>
<P>此外，6月份全国累计粗钢日均产量为195.4万吨。“板材市场不好，由于长材市场较好，说明长材企业的生产积极性非常高。”中国联合钢铁网分析师胡艳平昨天告诉《第一财经日报》。</P>
<P>今年上半年，钢厂开工率一直处于较高水平，钢铁产量也因此再上新台阶。钢厂开工率在进入二季度以来一直处于较高水平，粗钢日产量始终高于190万吨。</P>
<P>5月初，建筑用钢价格表现抢眼，之后则在大环境以及工业景气度持续下滑等因素影响下，直至6月末。胡艳平认为，当本轮价格跌至成本线附近时将受到支撑，同时主要用钢行业在7月末或8月份触及年内景气度低点后再度转强，需要为下一个生产旺季备料，需求有望因此放量，再加上保障房开工高峰期的到来，均将提振钢市。</P>
<P>我国钢铁研究中心分析师刘慧峰认为，未来1~2个月为下游主要用钢行业的消费淡季，加之近期由于通胀压力过大，仍可能继续出台紧缩政策，因此7月底之前钢价下行概率较大。</P>
<P>不过刘慧峰同时认为，到三季度后期，随着保障房的集中开工将会带来约2500万吨~3000万吨的建筑钢材需求，钢价重心上移可能性较大。另一方面，自6月下旬开始，丰田等日系车企开始大规模招募临时工，以确保产能。因此，预计三季度以后汽车产量将逐步恢复，届时板材的需求或将有所好转。</P>
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