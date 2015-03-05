<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>冀东油田供应处集中采购降成本- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b18a0750383.jsp" title="冀东油田供应处集中采购降成本" class="cmsHref_self">冀东油田供应处集中采购降成本</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>冀东油田供应处集中采购降成本</h1>
					<div class="source">
						<span>发布时间：2011-06-17</span>
						<span>发布人：-  </span>
					</div>
					<p><p>截至6月15日，冀东油田供应处执行集团公司石油专用管材集中采购累计到货2.042万吨，完成二级物资代储代销招标24个大类1.367万项物资。集中采购，既有效降低了运营成本，又规范了物资采购行为。今年前5个月，这个处物资集中采购度达到98.13%，节约采购资金825.08万元。</p>
<p>强化计划管理。这个处坚持做好大宗物资年度计划编制工作，对各单位生产维修用料加强需求预测，变&ldquo;月度计划为主&rdquo;为&ldquo;季度计划为主&rdquo;，严格控制月度零星计划。</p>
<p>规范基础管理。这个处不断规范基础管理工作，明晰责任，优化业务流程，重新梳理《供应商管理实施细则》等一系列管理制度，推进信息平台、供应商管理、专家库、业务流程、库存储备、物资标准&ldquo;六统一&rdquo;，实现&ldquo;阳光采购&rdquo;。</p>
<p>实施标准化管理。这个处和相关部门紧密结合，大力推进工艺流程、产品设备、设计规范标准化，优选供应商进行定型化生产，降低采购成本，对新产品、专利产品严格市场准入。</p>
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