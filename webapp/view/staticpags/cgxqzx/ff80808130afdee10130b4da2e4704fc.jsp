<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>全球酒业品牌50强出炉 中国茅台排第九- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130afdee10130b4da2e4704fc.jsp" title="全球酒业品牌50强出炉 中国茅台排第九" class="cmsHref_self">全球酒业品牌50强出炉 中国茅台排第九</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>全球酒业品牌50强出炉 中国茅台排第九</h1>
					<div class="source">
						<span>发布时间：2011-06-22</span>
						<span>发布人：-新华网  </span>
					</div>
					<p><P>俄罗斯伏特加品牌无一跻身美国权威行业杂志推出的世界酒业排行榜5 0强。俄罗斯酒业品牌虽然在国内销量可观，在外国也相当知名，可是价值不到1.2 5亿美元。专家认为，没有入选国际排行榜的俄罗斯著名“红牌”和“绿牌”伏特加价值分别是2 0亿美元和9亿美元，未能上榜的原因主要是酒业市场透明度不高。</P>
<P>评估品牌价值时考评的是生产厂家5年来的财务数据、中期发展前景、产量和预期收入。</P>
<P>排名前三的早在人们意料之中。最有价值品牌是尊尼获加(英国帝亚吉欧酒业集团制造)，价值21.6亿美元;其次是百加得，价值19.1亿美元;再次是皇冠，价值17亿美元。</P>
<P>中国4个品牌跻身世界酒业品牌50强。尤其是位于第9位的茅台，价值10亿美元以上。50个上榜的酒类品牌总价值高达286亿美元。</P>
<P>虽说俄罗斯被认为是世界上最大的酒业市场之一，但是本土品牌全都落榜。这对国内市场而言确实是令人十分不快的事情。俄罗斯行业协会会长瓦季姆·德罗比兹认为，评选结果明显存在歧视。他说：“我国有很多品牌价值在1亿美元以上。要说"红牌"，价值大约是20亿美元。俄罗斯不管怎么说也是全世界伏特加的故乡，主要对手不想让俄罗斯产品参与竞争。写着"俄罗斯制造"的任何伏特加在西方品牌面前都有竞争优势。”</P>
<P>而销售专家斯坦尼斯拉夫·考夫曼认为：“我不相信有什么暗箱操作。最重要的是某个品牌的市场透明度。可俄罗斯谈不上市场透明度。”他还说，这种评比很有实用性，在西方，向银行申请贷款时品牌往往是一种担保。而俄罗斯银行从来不认为酒业公司的商业品牌可作担保。俄罗斯根本不对酒业品牌进行客观而公正的评估。</P>
<P>市场缺乏沟通，也不利于真实评估品牌的价值。比如说，2 0 1 0年一家咨询公司认为“绿牌”价值730 0万美元，而另一家公司的估价是8.8亿美元。</P>
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