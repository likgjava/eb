<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>北京市交通委称将暂停采购奥迪斯品牌电梯- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a701310201ebe4051f.jsp" title="北京市交通委称将暂停采购奥迪斯品牌电梯" class="cmsHref_self">北京市交通委称将暂停采购奥迪斯品牌电梯</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>北京市交通委称将暂停采购奥迪斯品牌电梯</h1>
					<div class="source">
						<span>发布时间：2011-07-07</span>
						<span>发布人：-新华网  </span>
					</div>
					<p><P>　　新华网北京7月6日电 北京市交通委6日召开新闻发布会称，造成地铁4号线电梯故障的直接原因是电梯的固定零件损坏，但事发时，自动扶梯防止电梯反向下滑的保护装置并未发生作用，建议相关部门对奥迪斯扶梯设计、制造中可能存在的缺陷进行调查。</P>
<P>　　据北京市特种设备事故调查处理事务中心介绍，造成地铁4号线电梯故障的直接原因是电梯的固定零件损坏，扶梯驱动主机发生位移，造成主驱动链条脱落，导致扶梯下滑。同时，扶梯中设计有防止电梯反向下滑的保护装置，但在该扶梯的故障代码显示中未显示保护装置的代码信息，即保护装置当时并没有发生作用。</P>
<P>　　北京市交通委表示，事故电梯为奥迪斯公司生产，建议北京市质监、安监部门对事故具体原因继续调查，并对奥迪斯扶梯设计、制造中可能存在的缺陷进行调查，必要时应启动召回制度。在调查处理期间，北京市轨道交通建设单位将暂停采购奥迪斯品牌电梯。</P>
<P>　　截至6日5时，北京市已经对地铁线路内的全部扶梯进行了排查。</P>
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