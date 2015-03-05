<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>微软收购企业管理软件供应商Prodiance- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f013071ea1c58308b.jsp" title="微软收购企业管理软件供应商Prodiance" class="cmsHref_self">微软收购企业管理软件供应商Prodiance</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>微软收购企业管理软件供应商Prodiance</h1>
					<div class="source">
						<span>发布时间：2011-06-09</span>
						<span>发布人：-  </span>
					</div>
					<p><p>北京时间6月8日消息，据国外媒体报道，微软今日宣布收购企业风险管理软件供应商Prodiance。收购的具体价格和细节没有透露。据悉，微软将把Prodiance的技术与未来版本的Office软件整合。</p>
<p>根据Prodiance网站&ldquo;常见问题&rdquo;栏目的说法，微软收购Prodiance是为了给Office软件增加更多的依存性功能(compliance functionality)。微软计划将Prodiance的技术整合进未来的Office和SharePoint版本中。相关路线图和许可证将稍后公布。</p>
<p>根据微软官方博客的说法，Prodiance现在是微软的合作伙伴，收购完成后将成为微软的全资子公司。微软还表示，期待Prodiance的技术能加强未来Office和SharePoint的功能，包括增强安全性、在电子表格中对关键商业信息进行控制、综合审计跟踪;以及通过自动化风险评估，提供政策执行、可持续文档监控、风险消减等。微软称，目前有超过2000家公司在使用Prodiance的技术。</p>
<p>目前尚不清楚微软会在哪个版本的Office中整合Prodiance的技术。现在微软正在开发Office 15，预计这个版本将在2013年上市。</p>
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