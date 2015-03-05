<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>商务部公布《汽车品牌销售管理实施办法》- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fba4e32012fbda9ceac015c.jsp" title="商务部公布《汽车品牌销售管理实施办法》" class="cmsHref_self">商务部公布《汽车品牌销售管理实施办法》</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>商务部公布《汽车品牌销售管理实施办法》</h1>
					<div class="source">
						<span>发布时间：2011-05-05</span>
						<span>发布人：-深圳特区报  </span>
					</div>
					<p><p>　　25日，商务部公布了《汽车品牌销售管理实施办法》。对当初颇受争议的进口车销售网络和国产销售网络能否合一的问题，新的《办法》并没有明确规定。</p>
<p>　　据商务部有关负责人介绍，该《办法》将于4月1日正式实施。《办法》着重对实施汽车品牌销售的车型范围和时间，汽车生产企业建立完善的汽车品牌销售服务体系，汽车供应商、品牌经销商的资质条件、设立程序、行为规范以及政府部门的监督管理等作出规定。</p>
<p>　　根据《办法》中的规定，&ldquo;一汽车品牌的网络规划一般由一家境内企业制定和实施。境内汽生产企业可直接制定和实施网络规划，也可授权境内汽车总经销商制定和实施网络规划;境外生产企业在境内销售汽车，须授权境内企业按国家有关规定在境内设立企业作为其汽车总经销商，制定和实施网络规划。&rdquo;但经销商认为，这意味着有关&ldquo;两网分营&rdquo;的门正在慢慢开启。</p>
<p>　　对于近两年来汽车品牌专卖店建设数量过多、过滥的问题，新《办法》规定，&ldquo;汽车品牌销售和与其配套的配件供应、售后服务网点相距不得超过150公里&rdquo;。另外，&ldquo;除授权合同另有约定外，汽车供应商在汽车品牌经销商授权销售区域内不得向用户直接销售汽车。&rdquo;</p>
<p>　　对于敏感的汽车价格，新《办法》要求&ldquo;汽车品牌经销商应当在经营场所明示所经营品牌汽车的价格和各项收费标准&rdquo;。因此，经销商以后针对市场销售情况，再擅自调整价格可能不会那么容易。</p>
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