<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>一次有益的服务外包采购尝试- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131db79dc0131df879852023d.jsp" title="一次有益的服务外包采购尝试" class="cmsHref_self">一次有益的服务外包采购尝试</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>一次有益的服务外包采购尝试</h1>
					<div class="source">
						<span>发布时间：2011-08-19</span>
						<span>发布人：-  </span>
					</div>
					<p><p>尽管该项目可以定义为服务类采购，但其服务的内涵及外延与一般的服务类采购项目相去甚远</p>
<p>为顺应国家“十二五”规划关于推进服务外包产业发展的新要求，江苏省国税局于年初做出将江苏国税12366呼叫中心的服务整体外包的决策。从4月立项到6月项目完成，江苏国税12366呼叫中心服务外包公开招标项目经过3个月的紧张实施，达到了预期的目的和效果，为国税系统开展服务外包项目的政采工作作出了一次有益的尝试。</p>
<p>深入分析项目特点</p>
<p>管理理念的创新给政府采购提出了一个全新的课题。首先是采购内容的复杂性。采购对象不再是单纯的货物或某一类服务，而是围绕12366热线运行所需的全部服务和管理流程，涵盖了人员招聘、培训、管理与考核，服务场地及环境的建设和租赁，硬件设备的采购、集成，软件系统的开发、安装、升级和维护，网络与通讯线路的租赁以及工作制度框架的构建和管理等方方面面。尽管该项目可定义为服务类采购，但“服务”的内涵及外延与一般服务类采购相去甚远。</p>
<p>其次是采购结果的延续性和可扩性。以往的项目在服务期满后即完成了合同的履行过程。与之相比，该项目具有长期、动态的特点。作为江苏国税实时了解纳税人需求、给予纳税人及时准确服务的平台，该项目将长期运行，服务还会随着税收业务的完善而扩展。</p>
<p>最后是项目的影响力。一旦热线建成，纳税人将通过每一次拨打体验，直观地感受国税系统服务质量。作为江苏国税纳税服务在全社会面前的一个重要窗口，该项目的影响是深远的，由此所需的费用也是长期持续的。</p>
<p>紧紧扣住核心需求</p>
<p>该项目招标文件紧紧围绕3项核心内容。一是设置有针对性的资质要求，要求投标人必须具备《基础电信业务运营许可证》。二是明确项目需求。采购部门从项目概述、呼叫中心框架、纳税服务系统软硬件、系统改造、服务要求、考核管理等方面，以文字、图表等多种形式表述，并附之国税总局关于纳税服务热线的各类制度规程，确保了投标人对项目需求的明确理解。三是评标方法和标准合理有效。该项目采用综合评分的方法，将评审因素和各比重设定为投标报价20分，服务措施25分，服务能力25分，服务场所20分，业绩10分。</p> 
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