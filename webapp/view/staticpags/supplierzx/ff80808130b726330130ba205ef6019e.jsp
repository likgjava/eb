<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>AMD公布CPU+GPU融合进程 2014年搞定- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130ba205ef6019e.jsp" title="AMD公布CPU+GPU融合进程 2014年搞定" class="cmsHref_self">AMD公布CPU+GPU融合进程 2014年搞定</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>AMD公布CPU+GPU融合进程 2014年搞定</h1>
					<div class="source">
						<span>发布时间：2011-06-23</span>
						<span>发布人：-驱动之家  </span>
					</div>
					<p><P>在华盛顿州贝尔维尤召开的第一届Fusion开发者峰会上，AMD向与会的700多名开发者和PC业界高管们公开介绍了Fusion系统架构的发展路线图，透露了未来几年的一些深入融合步骤。</P>
<P>目前正在陆续推出的第一批FusionAPU处理器只是初步把CPU、GPU整合到了一块芯片上，二者之间的互连互通还比较少。根据AMD的设计理念，CPU、GPU最终将彻底融合，成为统一的处理引擎，全面支持新的编程语言和OpenCL、DirectCompute等加速计算接口。</P>
<P>AMD公司院士PhilRogers表示，AMD正在已有产品基础上进一步推动Fusion融合架构的进化，使软件程序员可以将CPU、GPU等作为一个统一的处理单元使用，具体的演进步骤有：</P>
<P>-支持C++的功能，更充分地利用GPU的并行处理性能;</P>
<P>-用户模式调度实现CPU和GPU之间更低延迟的任务派发;</P>
<P>-CPU和GPU共享统一的内存地址空间以及完全一致的内存，实现CPU和GPU的无缝协作运行。</P>
<P>AMD预计，整个融合过程要到2014年才会基本完成。</P>
<P>AMD还宣布将会公布一套详细的规范，阐述FusionAPU架构所需要的的功能、特性。</P>
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