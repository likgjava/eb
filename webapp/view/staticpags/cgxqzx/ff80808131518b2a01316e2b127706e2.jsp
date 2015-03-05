<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>山东滕州市上半年政府采购额实现1.36亿元- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01316e2b127706e2.jsp" title="山东滕州市上半年政府采购额实现1.36亿元" class="cmsHref_self">山东滕州市上半年政府采购额实现1.36亿元</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>山东滕州市上半年政府采购额实现1.36亿元</h1>
					<div class="source">
						<span>发布时间：2011-07-28</span>
						<span>发布人：-枣庄市财政信息网  </span>
					</div>
					<p><P>今年以来，在山东滕州市委、市政府的正确领导和全市上下的配合支持下，滕州市政府采购工作围绕“依法采购、优质服务、规范操作、廉洁高效”的要求，以服务政府工作为中心，以规范采购行为重点，以促进社会经济发展为目标，进一步深化抓基础，规范行为;抓管理、优化服务;抓创新、提升水准的措施。上半年，全市完成采购活动86次，其中：公开招标40次、竞争性谈判23次、询价23次。实现政府采购额13579万元，节约资金2431万元，节支率为15.18%，其中：货物类采购额6114万元、工程类采购额5318万元、服务类采购额2147万元，为服务经济社会发展，促进党风廉政建设做出积极贡献。</P>
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