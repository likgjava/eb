<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>光明乳业获世泳赛推荐供应商资格- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013163e3358b02be.jsp" title="光明乳业获世泳赛推荐供应商资格" class="cmsHref_self">光明乳业获世泳赛推荐供应商资格</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>光明乳业获世泳赛推荐供应商资格</h1>
					<div class="source">
						<span>发布时间：2011-07-26</span>
						<span>发布人：-上海证券报  </span>
					</div>
					<p><P>“第14届国际泳联世界锦标赛”正在上海如火如荼地进行，光明乳业从众多国内外强劲对手中脱颖而出，成为中国乳业三甲中唯一获得世泳赛“乳制品推荐供应商”资格的公司。从今年7月1日到明年6月底，光明乳业获得的“第14届世泳赛乳制品推荐供应商”资质都被世泳赛组委会许可在产品包装及各类广告宣传中使用。</P>
<P>据光明乳业新闻发言人、公共事务总监龚妍奇介绍，光明乳业高端鲜奶子品牌优倍还特别冠名了“优倍杯”公开水域全国精英赛、“优倍杯”上海市青少年游泳比赛、“优倍杯”男子水球比赛等赛事，泳池、入水台边缘、颁奖背景板和采访区背景板上随处可见光明乳业蓝白相间的奶滴状LOGO。此外，作为第14届世泳赛全程赞助商，光明乳业不仅每天为组委会和运动员提供6000份光明健能AB100优酪乳、红枣酸奶、草莓味酸奶、常温牛奶等优质产品，还专为参赛运动员特制了常温利乐砖包装的脱脂牛奶。</P>
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