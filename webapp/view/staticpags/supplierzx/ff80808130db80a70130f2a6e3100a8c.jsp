<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>预计2012年之前建筑涂料市场有大幅上升- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f2a6e3100a8c.jsp" title="预计2012年之前建筑涂料市场有大幅上升" class="cmsHref_self">预计2012年之前建筑涂料市场有大幅上升</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>预计2012年之前建筑涂料市场有大幅上升</h1>
					<div class="source">
						<span>发布时间：2011-07-04</span>
						<span>发布人：-中国建材一网  </span>
					</div>
					<p><P>作为涂料品牌市场中份额和使用量最大的一部分，建筑涂料的需求比例占所有涂料总量的一半。因此中国房地产和住宅业市场也对涂料市场起着至关重要的作用。中国的房地产业作为美国建筑涂料市场最大的消费和需求产业，预计将在2012年之前出现一次居民住宅领域的市场反弹，相信建筑涂料也会受此影响，出现一个比较明显的拉升。</P>
<P>调查还显示，内饰涂料的需求将高于室外涂料的需求，因为后者受到未来新材料发展的制约。但是涂料中进行纤维增强技术的出现，将会带来可涂性新材料的应用增加，这会拉动室外涂料的需求增长。</P>
<P>另外在汽车涂料领域，该行业将继续扮演涂料需求大户的角色，随着中国和全球汽车业的再次复兴，汽车涂料的需求量也将有所增加。</P>
<P>金属加工和表面处理是另外一个快速发展的产业，这得益于中国金属建筑材料和建筑市场的回暖，这也将拉动该类涂料的生产数量。</P>
<P>占涂料总需求量的防腐蚀和特种涂料领域，将随着中国高速公路、铁路以及桥梁产业的高速发展而得到发展。随着中国对桥梁安全和维护保养的不断投入，预计未来桥梁和公路涂料将出现大规模的需求增加情形。</P>
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