<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>大宇造船研发气体燃料供应系统- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7dbb8546e64.jsp" title="大宇造船研发气体燃料供应系统" class="cmsHref_self">大宇造船研发气体燃料供应系统</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>大宇造船研发气体燃料供应系统</h1>
					<div class="source">
						<span>发布时间：2011-07-05</span>
						<span>发布人：-国际船舶网  </span>
					</div>
					<p><P>韩国大宇造船与海洋近日宣布其首席执行官将Sang-Tae Nam将主持项目，研发高压燃料气体供应系统(HP-FGS)，用于配套MAN公司的ME-GI发动机。</P>
<P>据大宇造船介绍，尽管目前已存在中小型LNG燃料船，但其研发的燃料供应系统是首次配套用于大型商船的双燃料二冲程发动机，将是今后航运业的主流。HP-FGS系统能为发动机高效地供应高压(大约300 bar)天然气。现有传统的气体供应系统运行时需要采用大量的电力和占用很大的甲板面积，而大宇新研发的系统，仅需较小的电力消耗(100kW)，并且结构紧凑。</P>
<P>大宇造船指出，ME-GI发动机于1994年研发，起初是用于电厂，因为缺少高效、高压的气体供应系统，曾被认为难以在船舶领域适用。而此次大宇的新系统将开创LNG燃料船舶推进新纪元。</P>
<P>大宇预计其将很快获取LNG燃料船合同，其LNG燃料推进系统也将在集装箱船、油轮等其它商用船上使用。</P>
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