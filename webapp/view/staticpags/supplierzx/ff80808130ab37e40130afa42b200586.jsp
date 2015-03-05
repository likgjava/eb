<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>收购资产 海尔进入整体上市通道- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130afa42b200586.jsp" title="收购资产 海尔进入整体上市通道" class="cmsHref_self">收购资产 海尔进入整体上市通道</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>收购资产 海尔进入整体上市通道</h1>
					<div class="source">
						<span>发布时间：2011-06-21</span>
						<span>发布人：-青岛新闻网-青岛早报  </span>
					</div>
					<p><P>青岛海尔17日公告称，公司及其全资子公司海尔股份(香港)有限公司(以下简称“海尔香港”)将使用现金收购海尔集团及其全资子公司海尔电器第一控股 (BVI)有限公司(以下简称“海尔电器第一控股”)所持10家公司股权，收购价格18.8亿元，被收购的10家公司主要涉及家电上游配套资产。</P>
<P>岛城证券业内人士认为，本次收购可谓青岛海尔整体上市之举。除了本次受让的家电上游配套资产外，青岛海尔在未来5年还将继续收购集团的其他白电资产，以及彩电、家居等资产和业务。</P>
<P>“在全球股市低迷、振荡的背景下，青岛海尔通过积极的资本运作，主动出击，充分展示了打造白色家电上市旗舰的强烈意图。”青岛海尔证券部刘先生这样表示。</P>
<P>早报记者注意到，海尔集团在今年1月作出承诺，将把青岛海尔作为旗下家电业务整合平台，自2011年起，在5年内拟通过资产注入、股权重组等多种方式支持青岛海尔，以解决同业竞争、减少关联交易，实现做大做强。</P>
<P>那么此次白电上游产业被注入上市公司之后，海尔集团未来还有哪些资产将被注入?根据海尔集团今年年初的承诺，其持有的其他白色家电资产、业务和相关股权也将被注入到上市公司。</P>
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