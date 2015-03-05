<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>国际核聚变实验堆将在中国采购电源- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc4701314a1d6c9c0ae4.jsp" title="国际核聚变实验堆将在中国采购电源" class="cmsHref_self">国际核聚变实验堆将在中国采购电源</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>国际核聚变实验堆将在中国采购电源</h1>
					<div class="source">
						<span>发布时间：2011-07-21</span>
						<span>发布人：-《科技日报》  </span>
					</div>
					<p><P>7月19日上午，中国国际核聚变能源计划执行中心(ITER中心)在北京召开ITER计划磁体电源设备采购说明会，国内20多家电力设备制造企业参加了会议。ITER中心副主任罗德隆介绍了ITER计划的总体情况和实施进展;相关管理人员和技术专家介绍了我国承担的磁体电源系统采购任务的具体物项和技术要求。</P>
<P>国际热核聚变实验堆计划简称ITER计划于1988年启动。2007年，随着包括中国在内的ITER国际聚变能组织成立，ITER计划进入了装置建造阶段。</P>
<P>根据我国和ITER组织达成的协议，我国将承担ITER计划磁体电源系统中的3个采购包：变流器电源系统采购包、无功补偿及谐波抑制系统采购包，以及脉冲高压变电站设备采购包。我国将承担相关设备的工程设计、生产，以及施工现场的辅助安装、集成和调试共组。</P>
<P>ITER使用的托卡马克装置中，电源系统是非常关键的子系统之一，它不仅产生等离子体、加热等离子体，还控制等离子体的位置和形状。同时，电源系统还要实现对装置磁体系统的安全保护，特别是在超导磁体失超时，电源系统必须快速切断数万安培的电流，并将超导磁体线圈中的能量迅速转移出来。在整个实验堆中，各种电源设备的占地面积达到了一半。</P>
<P>当天召开的说明会，为国内电力设备制造商了解我国承担的ITER计划磁体电源设备采购任务提供了平台，也标志着我国承担的ITER计划磁体电源设备采购制造任务开始进入实施阶段。</P>
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