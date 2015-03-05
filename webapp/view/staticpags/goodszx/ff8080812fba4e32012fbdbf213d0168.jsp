<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>海口食品药品监管局严禁医疗机构违法采购中药饮片- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/goodszx.jsp" title="供货资讯" class="cmsHref_self">供货资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fba4e32012fbdbf213d0168.jsp" title="海口食品药品监管局严禁医疗机构违法采购中药饮片" class="cmsHref_self">海口食品药品监管局严禁医疗机构违法采购中药饮片</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>海口食品药品监管局严禁医疗机构违法采购中药饮片</h1>
					<div class="source">
						<span>发布时间：2011-05-05</span>
						<span>发布人：-国际旅游岛商报  </span>
					</div>
					<p><p>　　海口市食品药品监督管理局即日起开展中药饮片专项整治行动，行动中发现中药饮片生产、流通及使用环节存在违法生产、采购和使用的，一律依法查处。</p>
<p>　　该局要求，生产中药饮片必须持有《药品生产许可证》、《药品GMP证书》。批发零售中药饮片必须持有《药品经营许可证》、《药品GSP证书》，必须从持有《药品GMP证书》的生产企业或持有《药品GSP证书》的经营企业采购。批发企业销售给医疗机构、药品零售企业和使用单位的中药饮片，应随货附加盖单位公章的生产、经营企业资质证书及检验报告书(复印件)。严禁生产企业外购中药饮片半成品或成品进行分包装或改换包装标签等行为。严禁经营企业从事饮片分包装、改换标签等活动;严禁从中药材市场或其他不具备饮片生产经营资质的单位或个人采购中药饮片。</p>
<p>　　医疗机构必须按照《医院中药饮片管理规范》的规定使用中药饮片，保证在储存、运输、调剂过程中的饮片质量。严禁医疗机构从中药材市场或其他没有资质的单位和个人，违法采购中药饮片调剂使用。医疗机构如加工少量自用特殊规格饮片，应将品种、数量、加工理由和特殊性等情况向该局备案。</p>
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