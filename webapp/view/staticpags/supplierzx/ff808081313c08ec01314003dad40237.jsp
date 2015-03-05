<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>白酒巨头倡议：严守白酒安全底线- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01314003dad40237.jsp" title="白酒巨头倡议：严守白酒安全底线" class="cmsHref_self">白酒巨头倡议：严守白酒安全底线</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>白酒巨头倡议：严守白酒安全底线</h1>
					<div class="source">
						<span>发布时间：2011-07-19</span>
						<span>发布人：-四川在线  </span>
					</div>
					<p><P>在日前由中国酒类流通协会等主办的第四届中国白酒东方论坛上，以五粮液、泸州老窖等全国14家名优白酒骨干企业向业界发出“大家联合起来，严守白酒安全健康的生命底线”的倡导。倡议还提出，任何从事白酒生产、销售的企业与个人，不得生产、销售损害消费者身体健康、危及消费者生命安全的酒类产品。</P>
<P>今年6月，国务院食品安全委员会办公室发出《关于进一步加强酒类质量安全工作的通知》，对加强酒类产品质量安全监管进行了全面部署。通知中明确提出，严厉查处打击使用工业酒精等非食用物质以及滥用甜味剂、色素等食品添加剂违法行为。</P>
<P>业界专家认为，作为拥有数千年历史的传统产业，白酒酿造技艺已非常成熟，不易发生食品安全问题。专家介绍，白酒成分中98%以上是水和乙醇，其余不到2%的成分里是其它的酯、酸、醛、醇类物质，这些物质含量虽少，却是组成白酒各种香味的主要成分，影响着白酒的风格。但目前也有部分中低端白酒产品为吸引消费者，往往会打着纯粮酿造的招牌、通过添加相应物质来调节口感，除添加香精、甜味素、除苦剂，有的还为了挂杯而添加食用甘油，还有个别酒企在酒中添加柠檬酸等，但在产品说明上并未作出说明。</P>
<P>泸州老窖负责人在会上表示，公司多年来践行“让中国白酒的质量看得见”的质量理念，建立了有机管理体系、质量管理体系等在内的八大体系作为管理体系保障。与此同时，泸州老窖20万亩有机高粱基地建设也从源头上保证了产品质量。</P>
<P>7月15日，记者走访成都部分名酒专卖店发现，不管是高档白酒还是低档白酒，其包装上都找不到“食品添加剂”等相关字眼。对此业界专家认为，一直以来，我国白酒中的添加剂标准滞后于产业的发展，没有强制要求在标签上详细标明添加剂的使用情况，以至于一些企业打起了“擦边球”。</P>
<P>业内专家呼吁，白酒企业应对食品添加剂的添加情况如实标明，尊重消费者知情权，同时，相关部门也应制定相应的质量标准，严格规范白酒中的添加剂使用。这也正是众多白酒巨头对行业发出倡议的要旨之一。</P>
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