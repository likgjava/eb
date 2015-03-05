<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>棉花价格表现平稳 纱厂适时采购- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131f188870131f40f89920009.jsp" title="棉花价格表现平稳 纱厂适时采购" class="cmsHref_self">棉花价格表现平稳 纱厂适时采购</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>棉花价格表现平稳 纱厂适时采购</h1>
					<div class="source">
						<span>发布时间：2011-08-23</span>
						<span>发布人：-  </span>
					</div>
					<p><p>当前国内棉花现货市场棉花价格表现平稳，随着纺织企业前期采购不积极，多数纺织企业库存已经不足维持生产，急需补库。当前纺织企业积极补库，中高等级资源需求量比较大，但是从市场来看，资源明显匮乏。目前随着新就旧棉花还没有达到有效接洽，因此对于纺织企业而言正是青黄不接的时候。目前不少棉花企业也抓住时机，只要纺织企业有补库需求急需采购，皮棉的报价就会小幅上扬，因此当前纺织企业并没有出现大规模集中补库。在双方僵持下，棉花成交量依旧不高。</p>
<p>当前新花方面，局部地区已经少量采摘及收购，但是随着今年收购价格不及去年，与棉农期望值大相径庭，因此多为有价无市的挂牌收购。目前各地气象条件适宜，棉花长势比较好。今日中国棉花价格指(CCIndex328)19071涨11，2级20575涨10，4级17617涨38，5级16205跌30。</p>
<p>国内棉花现货价格平稳，部分棉商积极消化库存回收资金，纱厂适时采购给棉市带来了企稳迹象。目前，高低等皮棉销售两极分化，高等级皮棉价格相对稳定且成交较好，但占比较大的低等级皮棉库存依旧难以消化，棉市仍存不稳定性。</p>
<p>进口棉中国主港报价总体平稳，部分品种有小幅下跌，澳棉和巴西棉下跌4.25美分，美棉和中亚棉下跌0.75-2.75美分，其他品种价格稳定。据调查，8月中旬以来仅两三家国际棉对外报2010/11年度美棉现货的价格，大部分棉商主要报远期的美棉、澳棉和印度棉的价格，即期报价很少。</p> 
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