<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>禁止采购使用台湾问题企业产品- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013052fd9647063c.jsp" title="禁止采购使用台湾问题企业产品" class="cmsHref_self">禁止采购使用台湾问题企业产品</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>禁止采购使用台湾问题企业产品</h1>
					<div class="source">
						<span>发布时间：2011-06-03</span>
						<span>发布人：-  </span>
					</div>
					<p><p>6月2日电(记者 胡浩)国家食品药品监督管理局2日下发通知，禁止餐饮服务单位采购和使用《暂停进口台湾食品及食品添加剂生产企业名单》中企业生产的食品和食品添加剂。</p>
<p>近日，台湾地区发现部分食品添加剂生产企业在其生产的起云剂中违法添加邻苯二甲酸酯类非食用物质，并导致部分下游食品生产企业的食品受到污染。6月1日，国家质检总局公布了《暂停进口台湾食品及食品添加剂生产企业名单》。</p>
<p>国家食品药品监督管理局通知要求，为防止相关食品和食品添加剂流入餐饮服务环节，餐饮服务单位不得采购和使用名单所公布的企业生产的食品和食品添加剂。对于已采购相关食品和食品添加剂的，应立即停止销售和使用，并积极配合有关部门做好问题产品召回工作。</p>
<p>通知说，地方各级监管部门要立即通知辖区内餐饮服务单位禁止采购和使用该名单中企业所生产的食品和食品添加剂，并加大监督检查力度，发现相关问题，要及时将信息上报当地政府，并通报相关部门。</p>
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