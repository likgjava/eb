<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>涂料市场在2011年还将有一次大范围提升- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01306cb2a34a230b.jsp" title="涂料市场在2011年还将有一次大范围提升" class="cmsHref_self">涂料市场在2011年还将有一次大范围提升</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>涂料市场在2011年还将有一次大范围提升</h1>
					<div class="source">
						<span>发布时间：2011-06-08</span>
						<span>发布人：-  </span>
					</div>
					<p><p>作为涂料品牌市场中份额和利用量最大的一部门，修建涂料的需要比例占全部涂料总量的一半。因而中国房地产和住房业市场也对涂料市场起着至关紧张的感化。中国的房地财产作为美国修建涂料市场最大的消耗和需要财产，估计将在2012年之前涌现一次住民住房范畴的市场反弹，信赖修建涂料也会受此影响，涌现一个比力显着的拉升。</p>
<p>观察还表现，内饰涂料的需要将高于室外涂料的需要，由于后者遭到将来新质料生长的制约。然而涂猜中举行纤维加强技能的涌现，将会带来可涂性新质料的运用增长，这会拉动室外涂料的需要增加。</p>
<p>别的在汽车涂料范畴，该行业将连续饰演涂料需要小户的脚色，跟着中国和环球汽车业的再次再起，汽车涂料的需要量也将有所增长。</p>
<p>金属加工和外貌处置惩罚是别的一个快捷生长的财产，这得益于中国金属修建质料和修建市场的回暖，这也将拉动该类涂料的临盆数目。</p>
<p>占涂料总需要量的防腐化和特种涂料范畴，将跟着中国高速公路、铁路以及桥梁财产的高速生长而失去生长。跟着中国对桥梁宁静和保护调养的不停投入，估计将来桥梁和公路涂料将涌现大范围的需要增长情况。</p>
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