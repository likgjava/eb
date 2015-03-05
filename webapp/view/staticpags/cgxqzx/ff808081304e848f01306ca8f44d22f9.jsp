<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>不采购和滥用食品添加剂物质- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f01306ca8f44d22f9.jsp" title="不采购和滥用食品添加剂物质" class="cmsHref_self">不采购和滥用食品添加剂物质</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>不采购和滥用食品添加剂物质</h1>
					<div class="source">
						<span>发布时间：2011-06-08</span>
						<span>发布人：-  </span>
					</div>
					<p><p>在日前江苏常州市卫生监督机构举行的&ldquo;严厉打击食品非法添加和滥用食品添加剂专项整治推进会&rdquo;上，来自常州市总商会餐饮业商会、市烹饪餐饮行业协会、市旅游协会、在常高校后勤研究会、市快餐业商会等组织，联合向全市餐饮企业发出倡议，向社会承诺依法诚信经营，不采购和使用食品添加剂以外的任何可能危害人体健康的物质。</p>
<p>据了解，从今年5月起，常州市开展了严厉打击食品非法添加和滥用食品添加剂专项整治行动，对学校(托幼)食堂、建筑工地食堂、企事业机关食堂、各类餐馆(快餐店、小吃店、饮品店和餐馆附属的甜品站)、集体用餐配送单位和中央厨房等餐饮服务单位进行检查。区域包括这些单位的食品库房，粗加工、切配、烹饪、面点制作、凉菜配制、裱花操作、现榨果蔬汁、腌腊烧烤和火锅底料、自制调味料配制场所或专间等。检查的食品包括餐饮服务单位提供的自制火锅底料、自制饮料、自制调味料(调味品)、粮油、乳及其制品、肉及其制品、水产品及其制品、焙烤食品、冷冻饮品等。</p>
<p>据介绍，目前，常州市卫生监督机构采取&ldquo;分片包干，责任到人，消除监管死角和盲点&rdquo;的方法，对全市餐饮服务单位进行全面摸底调查。特别是认真核查餐饮服务单位落实食品采购索证索票和查验记录制度情况，促使食品添加剂使用做到专人采购、专人保管、专人领用、专人登记、专柜保存&ldquo;五专&rdquo;。</p>
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