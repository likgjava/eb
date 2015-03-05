<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>光明称洽购澳食品商已进尾声- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d1eaa60131d539212d004a.jsp" title="光明称洽购澳食品商已进尾声" class="cmsHref_self">光明称洽购澳食品商已进尾声</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>光明称洽购澳食品商已进尾声</h1>
					<div class="source">
						<span>发布时间：2011-08-17</span>
						<span>发布人：-  </span>
					</div>
					<p><P>拥有大白兔、冠生园等众多品牌的上海光明食品(集团)有限公司昨日向本报记者证实，正在与澳大利亚品牌食品企业Manassen Foods 洽谈并购事宜，目前谈判已经进入尾声，正在就条款细节进行沟通。这也是在海外并购上屡败屡战的光明食品的又一次出手。</P>
<P>昨日光明食品集团相关负责人表示，光明食品集团目前正在洽购澳洲品牌食品公司Manassen Foods75%股权，但未透露交易金额及最终的签约时间。此次收购数月前开始接触，目前谈判已经进入尾声，正在就条款细节进行沟通，但最终结果仍取决于两国政府审批。</P>
<P>此前据外电报道，光明食品将斥资约4.16亿美元(4亿澳元)收购Manassen的75%股权，该公司资产规模约5.30亿欧元。公开资料显示，Manassen Foods是澳大利亚知名食品公司之一，产品遍及全球，主要有奶酪、饼干、蛋糕和糖果以及各种包装食品和冷冻食品等。</P>
<P>近年来，光明集团一直积极寻求海外并购的机会。自去年初至今，仅有媒体报道的，光明集团就已进行过五次海外收购行动，包括试图收购法国奶酪生产商优诺等企业，但均告失败。唯一一次成功的海外并购，是光明乳业收购新西兰信联乳业有限公司51%股权。</P>
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