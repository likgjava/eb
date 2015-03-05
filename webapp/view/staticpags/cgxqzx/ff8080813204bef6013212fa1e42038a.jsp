<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>北汽福田有意在印度向康明斯采购发动机- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813204bef6013212fa1e42038a.jsp" title="北汽福田有意在印度向康明斯采购发动机" class="cmsHref_self">北汽福田有意在印度向康明斯采购发动机</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>北汽福田有意在印度向康明斯采购发动机</h1>
					<div class="source">
						<span>发布时间：2011-08-29</span>
						<span>发布人：-  </span>
					</div>
					<p><P>综合外电报道，中国最大的商用车制造商北汽福田汽车股份有限公司(Beiqi Foton Motor)日前表示，该公司正考虑在印度市场采购康明斯发动机生产商用车。</P>
<P>据报道，康明斯印度业务董事长兼总经理阿南特·塔劳里卡(Anant Talaulicar)已经确认这一消息，他表示：“我们正就在印度向北汽福田提供发动机与北汽福田洽谈。这将使我们与中国福田汽车公司的关系更进一步。“</P>
<P>据悉，福田汽车公司已经在印度浦那附近购买了约250英亩(约101.2万平方米)土地，用来新建该公司在印度的生产基地。日前，福田汽车公司与印度当地政府签署了一份合作协议。根据该协议，福田汽车公司将于未来5年内，在印度投资167.6亿印度卢比(约合人民币24.6亿元)，新建一座汽车工厂。预计新工厂的汽车年产能为10万辆，将于2013年年初竣工。</P>
<P>在中国市场，福田汽车公司和康明斯已经成立了福田康明斯合资公司，该合资公司由北汽福田和康明斯各控股50%，生产轻型柴油发动机。</P>
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