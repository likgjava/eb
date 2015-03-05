<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>零部件供应不足 雷诺斯洛文尼亚工厂裁员520人- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081312d69ed01313ab43de10208.jsp" title="零部件供应不足 雷诺斯洛文尼亚工厂裁员520人" class="cmsHref_self">零部件供应不足 雷诺斯洛文尼亚工厂裁员520人</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>零部件供应不足 雷诺斯洛文尼亚工厂裁员520人</h1>
					<div class="source">
						<span>发布时间：2011-07-18</span>
						<span>发布人：-盖世汽车网  </span>
					</div>
					<p><P>综合外电报道，法国汽车制造商雷诺汽车公司日前表示，由于来自日本的重要汽车零部件短缺，该公司不得不削减斯洛文尼亚东部Novo Mesto工厂的产能，并在该工厂进行裁员。目前，Novo Mesto工厂约有3000名工人，而该公司此次计划裁员人数达520人。</P>
<P>据报道称，雷诺斯洛文尼亚分公司Revoz表示，受日本3.11特大地震和海啸灾害影响，雷诺Novo Mesto工厂生产活动所需的汽车电子零部件供应不足，从而影响了该工厂的生产活动。</P>
<P>该公司于2011年5月4日声明：“雷诺斯洛文尼亚分公司Revoz无法维持其工厂的现有汽车产能，将自5月23日起暂停Novo Mesto工厂的夜班生产活动。”</P>
<P>Revoz工会发言人Slavko Pungersic向斯洛文尼亚通讯社(STA)表示，一旦来自日本的零部件供应恢复正常，该公司预计还会召回这些工人。</P>
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