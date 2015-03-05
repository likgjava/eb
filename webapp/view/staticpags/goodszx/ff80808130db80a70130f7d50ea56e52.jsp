<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>福建农村药品供应网络全覆盖- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f7d50ea56e52.jsp" title="福建农村药品供应网络全覆盖" class="cmsHref_self">福建农村药品供应网络全覆盖</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>福建农村药品供应网络全覆盖</h1>
					<div class="source">
						<span>发布时间：2011-07-05</span>
						<span>发布人：-人民日报  </span>
					</div>
					<p><P>7月4日电 截至6月底，福建省农村药品供应网络、监督网络已经基本覆盖全省所有乡镇和建制村。药品供应网络覆盖100%的乡镇和99.2%的建制村，药品监督网络覆盖100%的乡镇和99%的建制村，全省农村群众的用药安全状态明显改善，连续10年未出现重大药品安全事件。</P>
<P>在之前的一次全省农村药品市场大检查中，福建省药监部门发现，全省农村医疗机构普遍没有单独药房，药品存放混乱等现象十分常见，甚至还有大量过期药、变质药仍在使用。为规范药品供应渠道，福建省首先对农村医疗机构的药房、药库进行了规范化改造。目前，全省乡镇卫生院药房、药库改造率达到98%以上，村卫生所、乡村个体诊所的药房、药库改造率达到80%以上。</P>
<P>与此同时，福建省率先在全国做出探索，重点填补药品供应“空白村”，采取在“空白村”设立“便民药柜”的方式，解决“老、少、边、岛、渔”地区药品供应问题。现在，全省已设立便民药柜122个，解决了大部分“空白村”的药品供应问题。</P>
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