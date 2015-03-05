<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>山煤集团两座整合煤矿开工奠基- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813008ca4701301f6b33a30500.jsp" title="山煤集团两座整合煤矿开工奠基" class="cmsHref_self">山煤集团两座整合煤矿开工奠基</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>山煤集团两座整合煤矿开工奠基</h1>
					<div class="source">
						<span>发布时间：2011-05-24</span>
						<span>发布人：-  </span>
					</div>
					<p><p>近日，山西煤炭进出口集团兼并重组的蒲县豹子沟煤矿和万家庄煤矿正式开工奠基。</p>
<p>豹子沟煤矿和万家庄煤矿是山煤集团在临汾成功重组的煤矿，年设计生产能力为90万吨和120万吨，预计2012年底投产。两矿的开工建设为山煤集团向着煤炭产能5000万吨的目标迈出了重要一步。去年，该集团先后有5座重组整合煤矿投入生产，年产煤炭756万吨。</p>
<p>作为本轮煤炭资源整合的主体企业之一，山煤集团先后在大同、忻州、晋中、临汾、长治、晋城等地成功整合58座煤矿，形成了21座主体矿井，产能3000万吨的动力煤、炼焦煤、无烟煤、半无烟煤四大煤炭生产基地，实现了从煤炭贸易向煤炭产销一体化转变。&ldquo;十二五&rdquo;期间，山煤集团将进一步发挥企业优势，打造物流链、信息链、资金链、价值链合一的煤炭全供应链，打造高端装备制造服务商，打造现代融资孵化平台，力求跻身世界500强之列。</p>
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