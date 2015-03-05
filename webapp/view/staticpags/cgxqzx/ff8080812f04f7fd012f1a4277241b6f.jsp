<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>2015年美国政府将仅采购新能源汽车- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f04f7fd012f1a4277241b6f.jsp" title="2015年美国政府将仅采购新能源汽车" class="cmsHref_self">2015年美国政府将仅采购新能源汽车</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>2015年美国政府将仅采购新能源汽车</h1>
					<div class="source">
						<span>发布时间：2011-04-03</span>
						<span>发布人：-盖世汽车网  </span>
					</div>
					<p><p>　　美国总统奥巴马3月30日表示，从2015年开始，美国联邦政府将仅采购纯电动、混合动力或其他新能源汽车作为政府用车。目前，美国政府用车的数量约为60万辆。<br />
<br />
　　巴马在美国乔治城大学发表演讲名为&ldquo;一个有能源保障的未来&rdquo;时透露，美国政府将选择与私人企业合作以提高政府用车中新能源汽车的比例。奥巴马还声称：&ldquo;我们不能日日夜夜都得为能源保障而担忧。&rdquo;<br />
　　据了解，美国政府计划于2025年将美国的进口石油量减少三分之一，目前美国每天需进口1100万桶石油。大量使用新能源汽车和节能车型将是美国政府达成该目标的重要手段之一。<br />
<br />
　　美国能源部曾表示，美国政府计划出台一个新战略，以实现奥巴马总统提出的到2015年拥有100万辆电动汽车的目标。这一目标是降低美国对外部石油依赖战略的重要组成部分，同时还能够确保美国在电动汽车生产行业的领导地位。</p>
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