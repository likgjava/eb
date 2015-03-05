<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>比亚迪拟14.82亿元增资旗下四公司- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01315f19dc4b0101.jsp" title="比亚迪拟14.82亿元增资旗下四公司" class="cmsHref_self">比亚迪拟14.82亿元增资旗下四公司</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>比亚迪拟14.82亿元增资旗下四公司</h1>
					<div class="source">
						<span>发布时间：2011-07-25</span>
						<span>发布人：-经济观察网  </span>
					</div>
					<p><P>比亚迪拟斥资14.83亿元注资旗下四子公司。日前，比亚迪发布公告称，拟以募集资金1.3亿元对全资子公司深圳市比亚迪锂电池有限公司增资，全部作为注册资本，增资后，比亚迪锂电池注册资本由3000万元变更为1.6亿元，公司持有比亚迪锂电池的股权比例保持100%。</P>
<P>比亚迪拟对全资子公司深圳市比亚迪汽车有限公司增资1.8亿美元(约合人民币11.62亿元)，全部作为注册资本。增资后，深圳比亚迪汽车注册资本由2.68亿美元变更为4.48亿美元，比亚迪持有深圳比亚迪汽车的股权比例由54.94%增至73.05%。</P>
<P>比亚迪拟以募集资金1亿元对控股子公司比亚迪汽车有限公司增资，全部作为注册资本，增资后，比亚迪汽车注册资本由12.5亿元变更为13.51亿元，比亚迪汽车的另一股东陕西省投资(集团)有限公司同比例增资101.01万元。增资后，比亚迪持有比亚迪汽车的股权比例保持99%。</P>
<P>此外，比亚迪决定以自有资金对扎布耶锂业进行增资，增资金额为9000万元，全部作为注册资本，增资完成后，公司持有扎布耶锂业的股权比例保持18%。</P>
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