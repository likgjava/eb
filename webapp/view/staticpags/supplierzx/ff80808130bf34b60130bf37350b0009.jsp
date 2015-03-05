<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>把控上游原料 霸王欲发力凉茶市场- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bf34b60130bf37350b0009.jsp" title="把控上游原料 霸王欲发力凉茶市场" class="cmsHref_self">把控上游原料 霸王欲发力凉茶市场</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>把控上游原料 霸王欲发力凉茶市场</h1>
					<div class="source">
						<span>发布时间：2011-06-24</span>
						<span>发布人：-北京商报  </span>
					</div>
					<p><P>在凉茶市场征战了一年有余的霸王集团日前透露，将加大对中草药等上游原材料的把控力度，并重点发展凉茶业务。但是业内专家对于霸王看重其凉茶业务的行为均不看好，认为作为日化企业的霸王发力凉茶市场的举动显得过于激进。</P>
<P>霸王方面日前表示，在中药材等原料价格大幅上涨的情况下，霸王将整合上游原材料，加大中草药种植基地建设。霸王集团首席执行官万玉华还透露，其占地192亩的凉茶中药养生饮品工业园将于明年投产，届时该公司的凉茶产能将得以释放，而目前霸王凉茶的生产全是委托加工的。她表示，霸王凉茶产品主要定位于中药养生。</P>
<P>对此，业内专家表示，霸王希望用完善产业链的形式提高自身的市场竞争力，抢占消费市场。中投顾问食品行业研究员周思然分析称，在王老吉、和其正两大凉茶品牌市场地位日益巩固的格局下，霸王此举虽在上游具备一定的优势，但依然未跳出中药配方概念的圈子，创新性略显不足，抢占市场的难度仍然很大。</P>
<P>其实，霸王凉茶于去年4月才上市，2010年的销售收入为0.6975亿元，占霸王总收入的4.7%，而消费者所熟知的红罐王老吉去年销售额达160亿元。虽然落差悬殊，但万玉华认为其凉茶业务“还算OK”，并表示凉茶等中药养生饮料正成为霸王重点发展的新品类，期望凉茶能成为公司新的增长点。</P>
<P>对此，快消品行业营销专家穆峰称，消费者对“霸王”二字的联想产品只是洗护用品，根本无法联想到凉茶，“霸王”作为日化品牌对饮料品牌的“霸王”产生了极大的冲击，该企业使自身两个业务在品牌上出现了同性竞争。</P>
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