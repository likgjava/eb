<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>7月份化肥产业经济运行平稳- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d557c540220.jsp" title="7月份化肥产业经济运行平稳" class="cmsHref_self">7月份化肥产业经济运行平稳</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>7月份化肥产业经济运行平稳</h1>
					<div class="source">
						<span>发布时间：2011-08-31</span>
						<span>发布人：-  </span>
					</div>
					<p><P>7月份，化肥产业淡季不淡、产销两旺，继续维持平稳向好的势头。一是产品产量稳定增长。7月份，全国共生产化肥536.6万吨(折纯，下同)，同比增长18%。其中生产氮肥366万吨，同比增长12.5%;生产磷肥125.5万吨，同比增长30.7%;生产钾肥44.9万吨，同比增长32.8%。二是产品价格维持高位。7月份，化肥市场淡季不淡，价格维持高位运行，产品销售率约98%，产销衔接较好。氮肥价格同比上涨29.9%，环比上涨4.4%，其中尿素市场均价2300元/吨，创金融危机以来最好水平;磷肥价格同比上涨17.3%，环比上涨0.6%;钾肥价格同比上涨11.2%，环比上涨2.2%。三是资金投向不断优化。1-7月，化肥产业累计完成投资620.1亿元，同比增长24.6%，略低于全社会固定资产投资增幅;施工项目1290个，同比下降13.9%;新开工项目774个，同比下降16.8%。产能严重过剩的磷肥行业投资下降，缓控释肥、专用肥等资源节约、环境友好型复合(混)肥投资比例逐步加大，氮肥行业向技术先进化、装置大型化方向发展的趋势明显。</P>
<P>下一步，有关部门应继续做好农资生产组织工作，加强化肥生产所需煤炭、天然气等原材料供应，进一步提高化肥生产和供给水平，密切关注国内外能源价格变化，以及国际钾肥和硫磺价格动态，综合运用农资直补等措施，积极疏导农资供需双方价格矛盾，保障市场的平稳运行。</P>
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