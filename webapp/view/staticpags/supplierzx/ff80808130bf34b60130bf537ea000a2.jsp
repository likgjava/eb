<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>酒鬼酒剥离房地产业务 深耕白酒主业- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bf34b60130bf537ea000a2.jsp" title="酒鬼酒剥离房地产业务 深耕白酒主业" class="cmsHref_self">酒鬼酒剥离房地产业务 深耕白酒主业</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>酒鬼酒剥离房地产业务 深耕白酒主业</h1>
					<div class="source">
						<span>发布时间：2011-06-24</span>
						<span>发布人：-长春晚报  </span>
					</div>
					<p><P>房地产前景的暗淡，加之白酒市场的火热，酒鬼酒似乎没有理由再次错过发展主业的大好机遇。公司决定剥离房地产业务，专攻白酒主业。</P>
<P>酒鬼酒18日披露，拟将其所持的中铁金桥世纪山水置业有限公司20%的股权转让给中铁大桥局集团武汉置业发展有限公司，交易价格6700万元。本次转让后，酒鬼酒将不再持有中铁世纪山水股权。</P>
<P>此次并非酒鬼酒第一次剥离房地产资产。去年11月，公司以1499.19万元作价，将控股子公司湖南利新源房地产开发有限公司75%股权一举转让给成都新合能房地产开发有限公司，获得投资收益800万元。</P>
<P>本次剥离房地产业务，酒鬼酒旗下已不再有房地产业务资产，而且增加公司投资收益700万元左右。酒鬼酒同时表示，此举是为了盘活企业资产，优化产业结构，做大做优做强主业。</P>
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