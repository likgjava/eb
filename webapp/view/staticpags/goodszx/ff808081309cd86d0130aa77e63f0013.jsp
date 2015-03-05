<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>国内啤酒业下半年涨价预期强烈- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081309cd86d0130aa77e63f0013.jsp" title="国内啤酒业下半年涨价预期强烈" class="cmsHref_self">国内啤酒业下半年涨价预期强烈</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>国内啤酒业下半年涨价预期强烈</h1>
					<div class="source">
						<span>发布时间：2011-06-20</span>
						<span>发布人：-中国经济网  </span>
					</div>
					<p><P>虽然在消费旺季各啤酒企业对于提价都是慎之又慎，但是业内人士普遍认为，目前啤酒行业利润太低，今年下半年整个啤酒行业都会进行提价。</P>
<P>在日前举办的“2011中国(首届)啤酒高峰论坛”上，苏赛特商业数据有限公司董事长李保均在预测啤酒价格时表示，今年下半年非餐饮地区的主流价格将从现在的3元涨至4-5元，餐饮地区的价格会达到7-8元。</P>
<P>据了解，今年4月，在消费品价格一路攀高的情况下，发改委约谈各行业协会及企业代表，表达出稳定物价的决心。不过当时有消息称，发改委约谈青岛、雪花、燕京和百威英博几大啤酒巨头时，对于啤酒业原材料价格上涨及行业整体利润较低的情况表示出理解，而对于白酒企业不断提价的举动则提出批评。有数据显示，白酒行业的毛利率高达60%-80%，而啤酒企业一箱啤酒的净利润只有一块钱左右，难怪有业内人士称“啤酒利润比纸还薄”。</P>
<P>对于可能导致提价的原因，企业均称是原材料价格的上涨压缩了企业的利润。中国酿酒协会啤酒原料专业委员会秘书长、北京农学院教授白普一也表示，我国啤酒产业链的短板就是主要原料的大麦依赖进口，国际市场的变动对整个国内啤酒行业都会产生极大影响。“2010年11月起，大麦价格持续走高，到2011年3月，进口大麦价格同比上升43.5%。大麦价格的变化必将影响到2011年啤酒企业的盈利，所以必须重视生产成本大幅度升高的预期。”</P>
<P>而另一方面，李保均也直言，原材料成本上升只是中国啤酒业涨价的一方面原因，更主要的原因是近五年刮起“血战到底”的竞争模式。他表示，企业所有的成本开支中，原材料成本、人力成本及通胀压力导致的成本不太多，但是砸在市场上的钱是一个天文数字。“买店垄断、促销投入、价格战争……企业一年砸在销售上的钱占了营业额很大的比例，所以最终利润很少。绝大部分啤酒企业已经承受不了，涨价已经成为必然。”</P>
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