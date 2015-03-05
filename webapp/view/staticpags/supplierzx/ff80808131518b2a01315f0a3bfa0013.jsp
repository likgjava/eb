<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>萨博汽车附属公司供应商宣布破产- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01315f0a3bfa0013.jsp" title="萨博汽车附属公司供应商宣布破产" class="cmsHref_self">萨博汽车附属公司供应商宣布破产</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>萨博汽车附属公司供应商宣布破产</h1>
					<div class="source">
						<span>发布时间：2011-07-25</span>
						<span>发布人：-盖世汽车网  </span>
					</div>
					<p><P>据路透社报道，处于危机当中的瑞典萨博汽车公司近期又收到了一个不好的消息，该公司旗下附属公司Saab Automobile Tools的供应商SwePart Verktyg AB将宣布破产。</P>
<P>瑞典Vanersborg地区法院在官方文件中对以上消息进行了确认，该法院已经收到了SwePart Verktyg AB公司提交的破产申请。法院提供的一份文件中显示，SwePart Verktyg AB公司未能偿还约80万美元的债务。</P>
<P>据了解，Saab Automobile Tools公司为萨博汽车旗下的全资附属公司，萨博汽车公司以及其母公司瑞典汽车公司未对以上消息进行回应。</P>
<P>瑞典萨博汽车公司曾于不久前宣布，该公司位于瑞典的汽车组装工厂的停产日期将延长至2011年8月底或之后，目前萨博汽车公司正努力同供应商协商以解决零部件供应问题。</P>
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