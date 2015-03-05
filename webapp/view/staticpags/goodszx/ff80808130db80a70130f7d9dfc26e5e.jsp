<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>昆生活必需品供应充足 生活消费比去年高31.8%- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f7d9dfc26e5e.jsp" title="昆生活必需品供应充足 生活消费比去年高31.8%" class="cmsHref_self">昆生活必需品供应充足 生活消费比去年高31.8%</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>昆生活必需品供应充足 生活消费比去年高31.8%</h1>
					<div class="source">
						<span>发布时间：2011-07-05</span>
						<span>发布人：-昆明日报  </span>
					</div>
					<p><P>本周云南省主要生活必需品价格小幅上涨，虽然昆明市11大类生活必需品市场供应充足，但是当前平均价格与上周相比上涨2.75%，和去年同期相比则上涨幅度达31.8%。</P>
<P>昨日下午，省商务厅通报省、市最新生活必需品市场价格情况。据6月27日至7月3日商务厅对沃尔玛、家乐福、玉溪百信、滇东、大理海春、金平荣联等160家大型超市、批发市场、农贸市场、屠宰企业的连续监测数据显示，当前我省大米、面粉、猪肉、食用油、鸡蛋、白条鸡、白糖、蔬菜等11大类城市生活必需品市场供应充足，市场平均价格比上周涨1.49%</P>
<P>其中，昆明市的生活必需品市场价格上涨幅度高于全省平均。根据昆明市40家生活必需品监测样本企业的统计数据显示，昆明11大类生活必需品市场供应充足，平均价格与上周相比涨2.75%，比去年同期则上涨达31.8%。</P>
<P>从昆明市的具体情况来看，蔬菜、白条鸡、面粉、大米、桶装食用油、白糖、鸡蛋和鲜猪肉8种生活必需品价格不同程度上涨，比上周分别上涨8.91%、4.14%、2.85%、2.05%、0.70%、0.55% 、0.53%和0.05%。</P>
<P>蔬菜是当前昆明生活必需品中价格上涨最快的品种，当前平均批发价格为2.51元/公斤，比上周上涨达8.91%。与周边省会城市相比，昆明市的蔬菜平均价格高于贵阳和成都，低于南宁和重庆。在昆明上市销售的蔬菜中，叶菜类环比涨幅最大的是油菜，上涨42.27%;根茎类环比涨幅最大的是白萝卜，上涨19.85%;果实类环比涨幅最大的是黄瓜，上涨71.16%。</P>
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