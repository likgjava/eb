<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>全球海贸活动蓬勃 集箱需求依然殷切- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd79fd9510cdb.jsp" title="全球海贸活动蓬勃 集箱需求依然殷切" class="cmsHref_self">全球海贸活动蓬勃 集箱需求依然殷切</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>全球海贸活动蓬勃 集箱需求依然殷切</h1>
					<div class="source">
						<span>发布时间：2011-05-10</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据全球最大租箱公司──美国Textainen集团控股公司表示，全球海贸活动蓬勃，对集箱需求依然很殷切，租新箱与二手箱仍然处于活跃期。</p>
<p>Textainen目前拥有150万箱，供应全球400个客户，一位董事表示，&ldquo;公司收到客户查询需求实在太多，相信在今年第三季，班轮运输市场会有改善&rdquo;。</p>
<p>目前，该公司收费是每箱日租金介乎0.8至超过1美元。总的来说，租期长租金低。至于二手箱每标箱售价在1700美元，标准二手箱箱龄在10年，平均使用筹命在12年，但保养得法，使用期可延长。</p>
<p>其竞争对手GESeaCo公司拥有100万箱，绝大部分已出租。公司副总裁(亚太区)Routledge表示从货物需求来说，公司已订购12万新箱，大部分已被客户订购。航商09年大部分录得亏损，尽管去年都扭亏为盈，但面对高资本投入，船商都小心审慎处理购买。</p>
<p>中远太平洋表示该公司库存的168.57万箱的98%，已被租出。今年首季，该公司重订一批新箱共6.3万箱，比上年同期2000箱量增幅30.5倍。</p>
<p>至于Seacube租箱公司已耗资2.079亿美元订购一批新箱，今年内交付，86%箱量已被出租。</p>
<p>胜狮货柜企业市场推广副总裁陈国梁表示，即使班轮公司不愿买集箱，高价集箱对于租箱者来说仍然可稳定市场。</p>
<p>他说：&ldquo;假如船公司以高价订购集箱，他们至少可拥有这些箱10年以上，直至报废拆解。&rdquo;&ldquo;但航商从租箱公司租赁，一旦合约期满仍须还箱。&rdquo;</p>
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