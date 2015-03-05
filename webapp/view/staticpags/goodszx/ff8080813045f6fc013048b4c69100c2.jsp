<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>需求维持强劲 日胶维持升势- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b4c69100c2.jsp" title="需求维持强劲 日胶维持升势" class="cmsHref_self">需求维持强劲 日胶维持升势</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>需求维持强劲 日胶维持升势</h1>
					<div class="source">
						<span>发布时间：2011-06-01</span>
						<span>发布人：-  </span>
					</div>
					<p><p>因日本、美国和中国需求维持强劲，可能加速采购橡胶补充库存的乐观预期，东京工业品交易所(TOCOM)橡胶期货1月以来首次月度增长，TOCOM 11月交割的橡胶期货31日一度上涨0.9%至393.7日圆/千克(4,828美元/公吨)，收报390.7日圆/千克。该主力合约5月份上涨0.4%。</p>
<p>分析师称，橡胶市场受到美国和日本强劲需求的支撑，而且中国的低库存也引发市场预期，中国或不久将重建库存。上海期货交易所5月27日称，中国天然橡胶库存下降3,655吨至10,291吨，为2003年1月以来的最低水平。分析师表示，对于中国汽车销售增长的乐观情绪对市场构成支撑，但欧洲债务危机担忧令市场承压。</p>
<p>泰国南部降雨持续，橡胶供应仍然受到限制。天然橡胶生产国协会表示，主要橡胶生产国天然橡胶产量2011年可能不及预期，因印尼和菲律宾橡胶供应放缓。该协会称，其成员国2011年国橡胶产量预计增长4.9%至994万公吨，低于4月份增长5.8%的预期。该协会称，预计其成员国第二季度橡胶产量可能增长5.8%，低于4月预期的10.5%。</p>
<p>上海天胶期货31日减仓小涨。主力1109合约收盘32,850元/吨，上涨100元。供应面下降，美元指数疲软及油价高企支撑橡胶走势，但新胶批量上市、下游市场交投冷清仍然限制着胶价上行。且据传5月份的CPI可能更高，央行不会放松货币政策，对市场构成压力。隔夜原油收于三周高位，日胶继续上涨，预计近期沪胶将维持震荡走强，或冲击35000一线压力位。</p>
<p>现货方面，昆明云垦橡胶有限公司对于云南产国标一级品的成交价为35500元/吨，较上一交易日小幅上涨300元/吨，国标二级品市场成交价格为33500元/吨，较上一交易日上涨500元/吨。四川天然橡胶现货市场中，云南产国标一级品的市场报价为35800元/吨，二级品的报价为34500元/吨，市场中到货量不是很多，下游企业开工率较低，市场成交较为清淡。</p>
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