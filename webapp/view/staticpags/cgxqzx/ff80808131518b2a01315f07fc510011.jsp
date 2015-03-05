<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>达芬奇巧舌如簧 怕顾客混淆标注“全球采购”- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01315f07fc510011.jsp" title="达芬奇巧舌如簧 怕顾客混淆标注“全球采购”" class="cmsHref_self">达芬奇巧舌如簧 怕顾客混淆标注“全球采购”</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>达芬奇巧舌如簧 怕顾客混淆标注“全球采购”</h1>
					<div class="source">
						<span>发布时间：2011-07-25</span>
						<span>发布人：-  </span>
					</div>
					<p><P>2011年7月24日，为何达芬奇在销售贴牌产品时不注明产地为中国，而是要标注为全球采购?</P>
<P>黄志新：这是一个传统。比如“汤玛斯”的产品在全球有很多代工厂，一套餐桌，桌子可能是菲律宾产的，而椅子又是中国产的，我们怕混淆了，就统一标注为“全球采购”。</P>
<P>成都商报：我觉得您的回答，可能缺乏说服力。</P>
<P>黄志新：虽然我知道大家希望我们承认，我们没有标明产地，是有其他不好的想法，但我们确实一开始就是这样想和做的。我很清楚我们现在的处境，不负责任的说法都可能带来新一轮的危机，所以以上说法确实是我们的真实想法。</P>
<P>成都商报：达芬奇家具为何未标明材质?</P>
<P>黄志新：标签上产地和材质不规范的行为让大家产生了误会，我们已经着手改正。</P>
<P>这些只是个案，不代表所有产品。我觉得现在的关键是要等政府的核查结果。</P>
<P>成都商报：达芬奇家居的质量问题，现在是最大焦点之一。前几天，达芬奇上海仓库内“卡布丽缇”品牌的两个床头柜被上海市有关部门检测认定为不合格商品，您对此如何评价?</P>
<P>黄志新：这些只是个案，不代表所有产品。我觉得现在的关键是要等核查结果，要由政府来鉴定我们的产品到底有没有问题。</P>
<P>成都商报：目前有顾客退货吗?</P>
<P>黄志新：这个问题，我不好回答你。7月25日，我们将开通与客户的沟通电话。</P>
<P>成都商报：你们先起诉北京唐女士拖欠货款，后她又反诉你们欺诈消费者。为何在22日，你们双方分别申请撤回起诉?</P>
<P>黄志新：我们觉得所有事情，还是以协调来解决最好吧。</P>
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