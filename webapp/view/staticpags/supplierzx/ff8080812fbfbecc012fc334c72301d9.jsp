<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>大众C级轿车迫切进入公务车 商务车市场- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fc334c72301d9.jsp" title="大众C级轿车迫切进入公务车 商务车市场" class="cmsHref_self">大众C级轿车迫切进入公务车 商务车市场</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>大众C级轿车迫切进入公务车 商务车市场</h1>
					<div class="source">
						<span>发布时间：2011-05-06</span>
						<span>发布人：-  </span>
					</div>
					<p><p>上海大众最高端的车型是B级车新帕萨特，而在中国市场，公务用车和商务用车都对C级轿车有巨大需求，因此上海大众也迫切需要一款C级轿车来占领这块市场。</p>
<p>大众汽车与上汽集团日前正式签署协议。该款针对中国市场开发的C级豪华车将悬挂大众VW标识。</p>
<p>上海汽车集团与大众汽车在德国签署的三项文件中写明，中德投资双方将面向中国市场开发一款C级别的高端轿车，并基于德国大众最先进平台及发动机技术，该产品将成为大众汽车品牌产品体系内首款C级别的高端产品。双方还将共同支持上海大众在新能源技术领域的发展，积极推动上海大众电动车产品，包括自主品牌电动车的研发，加速上海大众电动车的产业化进程。</p>
<p>上海汽车与大众汽车签署的三项协议中包括了《基于最新MQB平台新一代车型之框架合作协议》，大众C级车也将基于该平台生产，而全新的MQB平台通过实现生产的模块化，能够推出跨越多领域产品。据了解，该项目已经在早先正式启动，上海大众已经派驻包括工程师、市场人员在内的项目组成员前往德国，负责协调C级车的前期预开发工作。</p>
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