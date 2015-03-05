<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>纺企采购意愿淡漠 棉花报价大幅下跌成交低迷- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd78dcf470c98.jsp" title="纺企采购意愿淡漠 棉花报价大幅下跌成交低迷" class="cmsHref_self">纺企采购意愿淡漠 棉花报价大幅下跌成交低迷</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>纺企采购意愿淡漠 棉花报价大幅下跌成交低迷</h1>
					<div class="source">
						<span>发布时间：2011-05-10</span>
						<span>发布人：-  </span>
					</div>
					<p><p>上周末山东魏桥纺织再次下调皮棉采购价500元/吨，一周之内累计下调2000元/吨。现货市场看空气氛浓厚，棉商调低棉花报价，但是纺织厂采购仍未动。纺织厂库存继续积压，纯棉纱销售不畅，涤棉纱销售相对较好，纺厂观望情绪继续增加，下游接单情况也不乐观。</p>
<p>周末北方大部分地区迎来降水，缓解了一部分地区的旱情，但是也有地区未在降雨区域，旱情仍在持续，长江流域湖北、安徽、江苏均受到旱情影响，棉花无法移栽至大田，如果棉花不能按农时移栽至大田，对后期棉花生长将有所影响。今日中国棉花价格指(CCIndex328)25655元/吨，跌421元，527级棉到厂均价23587元/吨，跌566元。</p>
<p>国内棉花现货市场依然是颓靡不振的格局，纺企采购意愿淡漠，市场成交继续低迷，皮棉到厂报价大幅下跌，纺企仍无明显补库意愿，一些小纺织厂延长放假时间，缓解开工压力，屯棉企业报价继续下调，挥泪卖棉，纺织厂观望情绪浓厚，整个市场进入萧条。</p>
<p>除澳棉价格稳定以外，其余外棉的中国主港报价普遍有1美分以上的跌幅，美国E/MOT棉和皮马棉下跌了3-5美分。随着外棉报价的步步下跌，内外价差逐渐缩小，近日进口企业对外棉的兴趣略有提升，采购重点主要是港口寄售棉和低价品种。不过，现阶段棉花需求远未恢复，大跌之后的市场能否复苏还需时间检验。</p>
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