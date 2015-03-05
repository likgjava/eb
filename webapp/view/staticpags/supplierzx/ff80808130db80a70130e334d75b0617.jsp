<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>戴姆勒20亿欧元注入北京奔驰- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130e334d75b0617.jsp" title="戴姆勒20亿欧元注入北京奔驰" class="cmsHref_self">戴姆勒20亿欧元注入北京奔驰</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>戴姆勒20亿欧元注入北京奔驰</h1>
					<div class="source">
						<span>发布时间：2011-07-01</span>
						<span>发布人：-京华时报  </span>
					</div>
					<p><P>戴姆勒股份公司与其中国合作伙伴北京汽车集团有限公司签署战略合作框架协议，进一步扩大其在华业务，深化与北汽控股的合作。戴姆勒将向北京奔驰注入20亿欧元以扩大生产规模。</P>
<P>根据协议，双方将进一步扩大在华乘用车的本地化生产，除了在今年实现中型豪华越野车GLK的国产外，还包括另外3款梅赛德斯-奔驰新一代紧凑豪华车型的国产计划;戴姆勒方面表示，北京奔驰全新的发动机工厂将于2013年开始制造4缸汽油发动机，工厂的初始年产能为10万台，最终年产能将达到25万台;戴姆勒在中国新组建的研发中心将主要负责车辆的测试与匹配。</P>
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