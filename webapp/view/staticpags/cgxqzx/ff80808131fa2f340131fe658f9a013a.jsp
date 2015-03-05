<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>河北上收基本药物采购议价权- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131fa2f340131fe658f9a013a.jsp" title="河北上收基本药物采购议价权" class="cmsHref_self">河北上收基本药物采购议价权</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>河北上收基本药物采购议价权</h1>
					<div class="source">
						<span>发布时间：2011-08-25</span>
						<span>发布人：-  </span>
					</div>
					<p><P>河北省将实行新的基本药物招标采购办法,取消各县区二次议价,省集中采购确定的采购价格即为基层医疗卫生机构的实际销售价格,同一药品在全省政府办基层医疗卫生机构执行统一价格。这是记者今天从河北省卫生厅了解到的。</P>
<P>2010年8月,河北省实施了以政府主导的基本药物网上集中招标采购工作,允许各县区通过二次议价的方式确定本地采购价格。制度实施以后,该省药品中标价格比乡镇卫生院实际零售价格平均降低19%,比国家零售指导价格平均降低46%。但也出现了只招不采、二次公关、“中标死”等问题。</P>
<P>河北省卫生厅副厅长李建国介绍说,此次制定的新的招标采购办法取消二次议价,并要求各市、县、区不得再以任何形式选择配送企业,实行省级基本药物集中采购配送,由生产企业对质量和配送统一负责,可以最大限度地减少流通环节、降低流通费用。通过招采合一、量价挂钩,发挥规模优势,大幅降低药品价格和采购成本,同时可以有效遏制乃至杜绝医疗机构二次议价采购中的种种不正之风。</P>
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