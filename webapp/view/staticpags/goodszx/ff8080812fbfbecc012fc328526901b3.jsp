<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>动漫衍生产品进货慎重侵犯知识产权- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fc328526901b3.jsp" title="动漫衍生产品进货慎重侵犯知识产权" class="cmsHref_self">动漫衍生产品进货慎重侵犯知识产权</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>动漫衍生产品进货慎重侵犯知识产权</h1>
					<div class="source">
						<span>发布时间：2011-05-06</span>
						<span>发布人：-  </span>
					</div>
					<p><p>将动漫形象印到衣服、鞋子、饰品上，最受孩子们青睐，也成了超市卖场必不可少的货品，但是，这方面存在的知识产权侵权行为让人防不胜防。近日，常州市中级人民法院发布的一起知识产权案例受到关注;一大型超市因进货不慎，被&ldquo;灰太狼&rdquo;咬了一口，赔偿2.6万元。</p>
<p>2010年5月，该动漫形象的权利人天洛行公司，在常州某大型超市发现一批印制&ldquo;灰太狼&rdquo;卡通头像的侵权童鞋，诉至法院。法院审理认为，超市在销售带有知名形象的商品时，对知名形象的使用是否合法、是否已获授权，负有较高程度的审查义务，虽然供应商曾作出由其承担知识产权法律风险的承诺，但并不因此免除超市的合理审查义务。最终，该超市被判决向&ldquo;灰太狼&rdquo;形象持有人赔偿经济损失26000元。</p>
<p>承办法官总结该案时说：知识产权维权力度一年比一年大，各类卖场在进货过程中，对于带有名人肖像、动漫形象的商品，一定要认真查验生产商、销售商有没有相应许可，并保存好相关权利证明，不能听信供应商口头保证。否则，一旦构成侵权，即使超市有正规进货渠道，仍将因未尽合理审查义务而必须承担赔偿责任。</p>
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