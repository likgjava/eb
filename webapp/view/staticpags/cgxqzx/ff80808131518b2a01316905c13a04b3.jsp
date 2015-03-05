<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>阳光易购---节约采购成本的开始- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01316905c13a04b3.jsp" title="阳光易购---节约采购成本的开始" class="cmsHref_self">阳光易购---节约采购成本的开始</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>阳光易购---节约采购成本的开始</h1>
					<div class="source">
						<span>发布时间：2011-07-27</span>
						<span>发布人：-房地产门户-搜房网  </span>
					</div>
					<p><P>阳光易购电子采购平台预期可以达到提供公平的竞争平台、缩短销售周期、降低采购成本、提高营销效率、增强企业市场竞争能力、搭建良好的双向沟通渠道等应用价值。</P>
<P>近日，北京国信创新科技有限公司通过阳光易购电子采购公共服务平台成功采购一批笔记本电脑，比总预算节省了11050元。</P>
<P>据采购方负责人介绍，本次采购包括ThinkPadX220(4287-A12)1台、X2207(4298 3JC)1台、E5201443-3QC 7台，采购预算为7万元。2011年7月11日在阳光易购平台上发布了采购需求，7月15日就完成了采购。本次采购共有8家企业参与了竞价，实际合同采购金额是58950元，比采购预算节约11050元，比京东商城价62792元还节省了3842元。预算节资率为15.78%，市场节资率为6.5%，节约采购成本的效果非常明显。</P>
<P>记者了解到，竞价采购是阳光易购电子采购公共服务平台的五大功能模块之一。采购人可以通过发布采购需求预告和竞价公告，查阅商品品牌、价格、性能和参数，通过在线谈判与竞价，选择最佳合作伙伴，处理订单和合同以及实现采购管理的电子化和信息化。</P>
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