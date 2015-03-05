<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>担忧中美需求放缓 铜价大幅下挫- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fba4e32012fbddd91f80196.jsp" title="担忧中美需求放缓 铜价大幅下挫" class="cmsHref_self">担忧中美需求放缓 铜价大幅下挫</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>担忧中美需求放缓 铜价大幅下挫</h1>
					<div class="source">
						<span>发布时间：2011-05-05</span>
						<span>发布人：-　中金在线特约　  </span>
					</div>
					<p><p>　　因担忧中国继续收紧货币政策，以及美国令人失望的经济数据，市场担心全球两大消费国的需求将下降，伦敦铜价周三大幅下挫，LME三月期铜收跌226美元至9124美元/吨，创3月以来最大的单日跌幅。</p>
<p>　　中国股市周三大幅下挫至两个多月以来的地位，市场担忧中国将有更多收紧货币政策的举措。另外，据路透最新季度调查显示,中国通胀压力居高不下的情形可能一直延续到明年年中,而今明两年中国经济增速虽料适度放缓,但仍会保持9%以上的较高水平,将为对抗通胀和宏观调控提供政策空间。强烈的紧缩预期不仅压制中国股市，也令市场对中国铜需求前景感到更为悲观。</p>
<p>　　另外，美国供应管理学会(ISM)公布的数据显示，美国4月份ISM非制造业指数录得52.8，低于预期的57.5，也低于3月份数据的57.3。4月ADP民间就业人数增加17.9万人，低于分析师预期的增加19.8万人。数据显示美国服务业活动增速大幅放缓且民间就业人数增幅下降，令市场对周五即将公布的非农就业数据感到悲观。</p>
<p>　　库存方面，LME铜库存周三继续增加125吨至463925吨，触及11个月高位。</p>
<p>　　沪铜周三震荡走低，表现疲软，主力CU1107合约收跌1230元至67900元/吨，从技术上看已形成破位下行的格局。中国紧缩货币政策的预期一日不除，铜价难以反弹。操作上建议空单持有，69000元止损。</p>
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