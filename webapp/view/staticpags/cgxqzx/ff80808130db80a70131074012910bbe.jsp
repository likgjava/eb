<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>消息称苹果年底内计划供应2500万台iPhone5- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70131074012910bbe.jsp" title="消息称苹果年底内计划供应2500万台iPhone5" class="cmsHref_self">消息称苹果年底内计划供应2500万台iPhone5</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>消息称苹果年底内计划供应2500万台iPhone5</h1>
					<div class="source">
						<span>发布时间：2011-07-08</span>
						<span>发布人：-凤凰网科技  </span>
					</div>
					<p><P>7月7日消息，外国媒体华尔街日报援引消息报道指，有匿名苹果供应商透露了即将上市的iPhone5的供应量，预计苹果年内会提供大约2500万台iPhone5。</P>
<P>不久前，苹果最大的产品合作组装商和零件提供商台湾鸿海公司收到iPhone5的组装订单，被告知要在8月底前完成组装，并等待告知出货时间。鸿海公司要帮助苹果公司完成年底2500万台的出货目标，难度不小。头几个月最少要完成300多万台的目标。</P>
<P>有业内人士认为，年内2500万的出货量是不是有点不自量力，简直视其他竞争对手不存在。但是对于苹果公司来说，顶尖的技术加上一支业界最优秀的销售团队，没有什么不能做到。先前iPhone手机的销量都十分理想，iPhone3GS的出货量都有1865万台，可见iPhone在智能手机的影响力非同小可。</P>
<P>从鸿海公司还确认了iPhone5的一些细节，有不愿透露姓名的知情人士说，iPhone5的无线基带会使用高通芯片，而不是像之前传言那样，采用和iPhone4一样的英飞凌的基带芯片。</P>
<P>还有消息指在iPhone5发布的前三个星期，首先会发布一款名为iPhone4S的iPhone手机来补充市场空缺。</P>
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