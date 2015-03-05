<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>白酒、食用油、月饼“涨迎”中秋- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131c418af0131cafed99d0029.jsp" title="白酒、食用油、月饼“涨迎”中秋" class="cmsHref_self">白酒、食用油、月饼“涨迎”中秋</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>白酒、食用油、月饼“涨迎”中秋</h1>
					<div class="source">
						<span>发布时间：2011-08-15</span>
						<span>发布人：-  </span>
					</div>
					<p><P>离中秋节还有一个月，老百姓还没有感受到中秋的喜庆，一些企业却已耐不住内心的兴奋，纷纷涨价“喜迎”中秋。白酒、食用油、月饼等无不如此。</P>
<P>12日，经济导报记者在济南市英雄山路某大型超市看到，白酒、食用油、月饼涨声一片。</P>
<P>“今年月饼上游各环节产品全面涨价，加上劳动力和汽油等价格上涨，月饼的生产、运输和包装成本上升，使得今年月饼涨价已成定局。单块月饼的价格预计会上涨5%-10%不等。”正在该超市为其生产的品牌月饼促销的王森伟说。</P>
<P>导报记者注意到，鲁花5L花生油的价格达到129元。据了解，继7月份鲁花花生油价格上涨后，龙大花生油也开始提价，目前新价格已调整完成。根据导报记者从多家超市了解到的情况，胡姬花、金龙鱼花生油12日进行了价格调整，涨价幅度在10%以上。大豆油、玉米油、调和油正酝酿涨价。</P>
<P>过节要喝酒，白酒也是过节涨价的常客。在历下区某大型购物广场，白酒行业具有风向标意义的53度飞天茅台酒最近再次提价，其价格已涨到1499元，与年初贵州茅台董事长袁仁国所说的最高限价959元相比，涨幅达到54%。预计中秋节前，53度飞天茅台还会再次涨价。另一名酒五粮液52度品种的售价目前是980元，也在酝酿涨价，预计涨幅在30%以上。</P>
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