<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>江苏吴江纺织机械进口需求旺盛- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013067821c6c1696.jsp" title="江苏吴江纺织机械进口需求旺盛" class="cmsHref_self">江苏吴江纺织机械进口需求旺盛</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>江苏吴江纺织机械进口需求旺盛</h1>
					<div class="source">
						<span>发布时间：2011-06-07</span>
						<span>发布人：-  </span>
					</div>
					<p><p>今年以来，吴江地区纺织机械进口量继续保持增长态势。据吴江检验检疫局统计，1~4月，吴江地区共进口纺织设备75批、货值1.28亿美元，同比分别增长53%和31%。主要进口纺织设备包括喷气织机、喷水织机、细纱机、浆纱机、整经机、经编机、针织机等。</p>
<p>纺织业是吴江的传统优势产业，盛虹化纤、恒立化纤等吴江纺织龙头企业积极实施产业转型升级，引进国外最先进的纺丝设备，生产高附加值产品，占领高端用户市场，国际先进的纺织机械设备进口增长显著。同时，2010年以来纺织面料市场景气度增强，化纤面料价格上涨，消费需求旺盛，刺激各纺织生产企业增加纺织设备，喷气织机、喷水织机等纺织和整理设备进口量增长明显。</p>
<p>吴江检验检疫局根据进口纺织机械大幅增长的趋势，提前介入，对重点进口设备实施项目管理，加强进口纺织机械的到货开箱检验、安装调试过程检验、质保期内正常运行等过程的检验监管，针对发现的进口纺织机械质量安全问题，及时与生产企业、进口贸易商、设备提供商沟通，指导企业完成设备整改，确保进口纺织设备安全运行。</p>
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