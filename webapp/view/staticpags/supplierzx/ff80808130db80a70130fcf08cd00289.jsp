<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>京港地铁：停用4号线奥的斯电梯 进行全面检查- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fcf08cd00289.jsp" title="京港地铁：停用4号线奥的斯电梯 进行全面检查" class="cmsHref_self">京港地铁：停用4号线奥的斯电梯 进行全面检查</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>京港地铁：停用4号线奥的斯电梯 进行全面检查</h1>
					<div class="source">
						<span>发布时间：2011-07-06</span>
						<span>发布人：gpcsoft-《财经网》  </span>
					</div>
					<p><P>据新华社报道，北京京港地铁公司5日16时召开新闻发布会称，地铁4号线动物园站事故造成一名男孩死亡，3名重伤者生命体征稳定，另有27人轻伤，目前已有9名轻伤者出院。</P>
<P>北京京港地铁公司新闻发言人杨苓说，京港地铁公司将尽全力做好伤者的救治工作，负责伤者检查、救治、治疗等相关费用，待事故原因调查清楚后，再对赔偿等问题进行处理。</P>
<P>同时，杨苓表示，出事故的电梯为奥的斯公司生产，该电梯目前仍在质保期内。6月22日，生产厂家刚刚进行了例行检查和保养。目前京港地铁公司已停用4号线全部共10台奥的斯电梯，进行全面检查。</P>
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