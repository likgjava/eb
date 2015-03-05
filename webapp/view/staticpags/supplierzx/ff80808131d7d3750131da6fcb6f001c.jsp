<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>净化政府采购环境 12家不良供应商被罚- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d7d3750131da6fcb6f001c.jsp" title="净化政府采购环境 12家不良供应商被罚" class="cmsHref_self">净化政府采购环境 12家不良供应商被罚</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>净化政府采购环境 12家不良供应商被罚</h1>
					<div class="source">
						<span>发布时间：2011-08-18</span>
						<span>发布人：-  </span>
					</div>
					<p><P>招投标本是促进平等竞争、公正交易的一种交易方式，然而受利益驱使，一些商家在参加招投标过程中存在围标、串标、恶意低价中标后放弃中标资格等种种违法违规问题。为规范招投标行为，净化政府采购市场环境，经开区日前对12家不良供应商予以处罚。</P>
<P>由于政府采购领域竞争日趋激烈和受利益驱动，目前政府采购工作中存在围标、串标、出借资质、多头挂靠、弄虚作假和恶意低价中标后放弃中标资格或不认真履约等违法违规问题。对此，经开区政府采购中心按规定加大治理力度，严厉打击政府采购过程中的不法行为，建立不良行为主体信息库。</P>
<P>日前，经开区政府采购中心对12家不良供应商，视情节轻重分别处以取消交易资格、没收保证金、记入不良行为记录、暂停进入区政府采购交易资格等相应处罚，已没收保证金7.5万元。</P>
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