<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>政府采购可吸纳可信计算产品- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a0131735a2a2908b2.jsp" title="政府采购可吸纳可信计算产品" class="cmsHref_self">政府采购可吸纳可信计算产品</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>政府采购可吸纳可信计算产品</h1>
					<div class="source">
						<span>发布时间：2011-07-29</span>
						<span>发布人：-中国质量新闻网  </span>
					</div>
					<p><P>面对层出不穷的非法窃密手段，信息安全问题令人堪忧，各级政府也高度重视，我国在2008年便成立了“中国可信计算工作组”。作为可信计算工作组的核心成员、倡导者，同方电脑更以“安全”作为企业战略核心，并投入巨额研发资金和大量企业资源，打造了一条完善、立体的可信安全产品体系。</P>
<P>可以说，在以同方为代表的众多民族安全厂商共同努力下，中国现在已拥有大量具备独立知识产权的可信安全专利技术，整体设计和研发实力某种程度上处于世界领先水平。为国家信息安全管理的完善提供了实体基础。可相比较而言，中国政府采购对可信安全的重视程度则略显不足。主要体现在政府采购人对可信安全的认识不足和相关执行力度的缺失方面。</P>
<P>如果说以前，信息安全核心技术掌握在“外人”手里，那么现在以同方电脑为代表的一批民族企业的崛起，无疑打消了这种顾虑。相信未来的政府采购会向着更开放的态势发展，本土安全企业的优秀产品也将发挥更大价值。</P>
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