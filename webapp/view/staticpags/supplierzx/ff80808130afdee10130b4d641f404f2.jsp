<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>惠普联想推平板电脑 夏季加入热战- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130afdee10130b4d641f404f2.jsp" title="惠普联想推平板电脑 夏季加入热战" class="cmsHref_self">惠普联想推平板电脑 夏季加入热战</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>惠普联想推平板电脑 夏季加入热战</h1>
					<div class="source">
						<span>发布时间：2011-06-22</span>
						<span>发布人：-经济日报  </span>
					</div>
					<p><P>全球笔电(NB)龙头惠普的新款平板计算机“TouchPad”预计7月上市，笔电四哥的联想，也将有三款平板计算机陆续上市，第三季平板计算机大战一触即发。</P>
<P>新平板计算机产品相继出笼，索尼平板计算机“S1”与“S2”有机会在9月上市外，Panasonic也预计在第四季推出Toughbook系列的平板计算机，主打坚固耐用的特点。</P>
<P>在非苹果阵营的平板产品陆续上市，台湾NB代工厂可望受惠。如独家取得TouchPad订单的英业达，同时获得联想代工订单的仁宝、广达，均可望挹注6月及第三季营收。</P>
<P>联想集团总裁兼首席营运长里德(Rory Read)接受媒体采访表示，预计夏天在美国推出两款Android平板计算机，并于今年稍晚时，会推出一款微软Windows系统平板计算机。</P>
<P>联想这两款平板计算机均为10寸，价格可能在450美元到900美元。</P>
<P>联想预估，未来三年平板计算机占整体计算机市场的15%，甚至将取代小笔电。</P>
<P>IDC认为，第三季在非苹果品牌的带动下，单季平板计算机的销售量将明显比前两季大幅增加，但可能影响到NB的销售。里德表示，整体个人计算机(PC)市场应该会继续成长，因为价格的下降，让新兴市场消费者也能买得起。</P>
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