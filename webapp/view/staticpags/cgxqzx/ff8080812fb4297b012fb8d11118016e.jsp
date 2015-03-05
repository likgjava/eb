<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>海淀区北部15个重大产业项目2011年将开建- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fb4297b012fb8d11118016e.jsp" title="海淀区北部15个重大产业项目2011年将开建" class="cmsHref_self">海淀区北部15个重大产业项目2011年将开建</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>海淀区北部15个重大产业项目2011年将开建</h1>
					<div class="source">
						<span>发布时间：2011-05-04</span>
						<span>发布人：-  </span>
					</div>
					<p><p>中国人寿研发中心二期项目、华为研发中心四期项目、中科院计算所龙芯产业园二期项目、中关村壹号一期&hellip;&hellip;海淀北部15个重大产业项目今年将陆续开工建设。笔者日前来到中关村环保园，高高的起重机在空中忙碌着，繁忙的建设场景在实创股份所属科技园区中处处可见。</p>
<p>据了解，海淀北部科技园区今年新开工项目15项，其中产业化项目12项，加速器项目3项，项目总投资93.7亿元，预计本年度完成投资21.31亿元。除了新开工项目，今年复工项目18项，其中产业化项目15项，加速器项目2项，配套项目1项，预计项目总投资110亿元。</p>
<p>&ldquo;今年是海淀北部科技园区大规模建设的第二年，也是关键一年。&rdquo;海淀区相关负责人介绍，今年北部新开工和复工项目共33项，规划建设总面积达280万平方米。按照&ldquo;三年完成基础设施和公共配套设施建设，五年基本完成规划建设任务&rdquo;的规划，未来，海淀北部地区将发展成为&ldquo;生态良好、产业集群、设施配套、就业充分、生活便利、富有活力&rdquo;的新区域。</p>
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