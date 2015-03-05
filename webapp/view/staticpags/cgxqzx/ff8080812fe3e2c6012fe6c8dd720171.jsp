<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中汽协有望发布政府市场汽车采购数据- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6c8dd720171.jsp" title="中汽协有望发布政府市场汽车采购数据" class="cmsHref_self">中汽协有望发布政府市场汽车采购数据</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>中汽协有望发布政府市场汽车采购数据</h1>
					<div class="source">
						<span>发布时间：2011-05-13</span>
						<span>发布人：-  </span>
					</div>
					<p><p>近日,中国汽车工业协会常务副会长董扬在接受本报记者独家专访时表示,中汽协正在努力将政府和个人汽车细分市场的采购数据作统计。</p>
<p>董扬说,今年以来,汽车产销形势相比往年而言,呈现低迷走势,其中一个原因是中央加大了对公车购置的管理力度。中汽协希望能够对政府和个人汽车市场的采购数据作出详细的统计,以便更好地对各个细分采购市场对汽车销售的影响作出分析,为国家制定更合理的汽车行业政策提供数据支撑。</p>
<p>对于政府和个人汽车细分市场采购数据的获取,董扬透露,这种数据比较难拿,但是中汽协正在努力。目前,中汽协希望能够通过公安部拿到全国汽车上牌的数据信息来进行统计,但是目前还在协商之中。如果该数据不能从公安部获取,中汽协希望能够通过汽车销售端也就是汽车企业那里获取数据进行统计。</p>
<p>据了解,目前中汽协定期发布的数据包括,汽车产销总体情况数据、乘用车和商用车的市场份额数据、汽车行业景气指数以及汽车行业企业家信心指数等。</p>
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