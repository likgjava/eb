<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>印度Sonalika集团确认为通用供应发动机- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130e32ce3de0603.jsp" title="印度Sonalika集团确认为通用供应发动机" class="cmsHref_self">印度Sonalika集团确认为通用供应发动机</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>印度Sonalika集团确认为通用供应发动机</h1>
					<div class="source">
						<span>发布时间：2011-07-01</span>
						<span>发布人：-盖世汽车网  </span>
					</div>
					<p><P>综合外电报道，印度Sonalika集团公司下属International Tractors公司常务董事迪帕克·米塔尔(Deepak Mittal)日前确认，该公司已经与通用汽车公司签订合作协议。根据协议，Sonalika集团将为通用旗下雪佛兰Tavera微型面包车供应发动机，以符合印度新的排放标准—Bharat Stage IV(BS IV， 相当于欧IV)。</P>
<P>当地商业报纸援引米塔尔的话报道称，“我们已经与通用汽车公司印度分公司签订了合作协议，为其供应发动机。我们将在荷沙浦尔(Hoshiarpur)生产这些发动机，并且在三个月内开始供货。”</P>
<P>据报道，由于无法将其发动机升级至符合印度Bharat Stage IV排放标准，自去年4月1日起，通用汽车公司已经在印度13个城市停止了雪佛兰Tavera车型的销售活动。这些城市中不乏印度的一些重要城市，如德里、加尔各答、孟买、钦奈和班加罗尔等。</P>
<P>另外，该媒体还报道称，在开始阶段，Sonalika集团将每月向通用供应1500台发动机。</P>
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