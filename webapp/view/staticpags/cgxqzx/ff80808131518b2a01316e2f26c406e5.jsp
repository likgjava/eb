<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>江西给酒类产品套"紧箍咒" 严禁采购销售假酒- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01316e2f26c406e5.jsp" title="江西给酒类产品套"紧箍咒" 严禁采购销售假酒" class="cmsHref_self">江西给酒类产品套"紧箍咒" 严禁采购销售假酒</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>江西给酒类产品套"紧箍咒" 严禁采购销售假酒</h1>
					<div class="source">
						<span>发布时间：2011-07-28</span>
						<span>发布人：-新华网江西频道  </span>
					</div>
					<p><P>新华网江西频道7月28日电 从相关部门获悉，从7月26日起，江西将全省范围内开展餐饮服务环节酒类质量安全专项整治工作。严防餐饮服务单位采购、销售和使用来源不明、超过保质期限以及假冒伪劣的酒类产品。</P>
<P>据了解，此次专项整治以农村和城乡结合部为重点区域，以酒吧、大型以上(含大型)餐饮服务单位和旅游景区餐饮服务单位等为重点单位，加大对各类餐饮服务单位采购、自酿或调配、销售酒类情况进行全面监督检查。严格自酿酒和调配酒管理。严防餐饮服务单位采购、销售和使用来源不明、超过保质期限以及假冒伪劣的酒类产品。各级食品药品监督管理部门还将加大餐饮服务环节酒类产品质量安全抽检的范围和频次，强化甲醇、游离二氧化硫、糖精钠、甜蜜素、苯甲酸、色素等重点指标的监督抽检。专项整治工作将持续到年底。</P>
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