<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>“邯钢造”打造世界级液压支架- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f0130771e5b5942c3.jsp" title="“邯钢造”打造世界级液压支架" class="cmsHref_self">“邯钢造”打造世界级液压支架</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>“邯钢造”打造世界级液压支架</h1>
					<div class="source">
						<span>发布时间：2011-06-10</span>
						<span>发布人：-  </span>
					</div>
					<p><p>日前，河北钢铁集团邯郸钢铁顺利完成与郑州煤矿机械集团股份有限公司签订的6000吨Q460C低合金高强板合同，经检验，表面质量、低温韧性等完全满足合同要求，合格率100%。产品将全部用于液压支架制造，成为新的创效亮点。</p>
<p>据了解，郑州煤矿机械集团股份有限公司(简称郑煤机)，是中国第一台液压支架生产企业。近年来，随着生产技术水平的不断提高，该公司创造了多项&ldquo;第一&rdquo;：液压支架总产量世界第一、支架工作阻力和最大支护高度世界第一、工业生产总值全行业第一、支架市场占有率国内第一等。</p>
<p>液压支架是一种利用液体压力产生支撑力并实现自动移设来进行顶板支护和管理的一种液压动力装置，是综合机械化采煤不可缺少的配套设备。主要用于水平面和小于等于10度的缓倾斜厚煤层沿底板一次放顶煤采全高开采的长壁综采工作面，也适用于急倾斜特厚煤层水平分层放顶煤综采工作面，产品对钢板的高强性能和低温韧性要求较高。</p>
<p>邯钢依托两条先进的中厚板生产线装备优势，努力开发适合市场各种性能要求的个性化产品。本次为郑煤机供应的产品为Q460C高强板，其规格厚度12至40毫米、宽度2800至3200毫米，属于低合金高强度结构钢板材，生产工艺复杂，轧制负荷要求高，生产难度大。尤其是控轧控温工艺是生产中的难点。</p>
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