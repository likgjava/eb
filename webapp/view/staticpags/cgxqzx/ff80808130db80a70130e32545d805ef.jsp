<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>志高中央空调入选政府采购- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130e32545d805ef.jsp" title="志高中央空调入选政府采购" class="cmsHref_self">志高中央空调入选政府采购</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>志高中央空调入选政府采购</h1>
					<div class="source">
						<span>发布时间：2011-07-01</span>
						<span>发布人：-生活日报  </span>
					</div>
					<p><P>志高中央空调喜讯频传，继大举进驻2011深圳大运会后，又添重彩。近日，“2011年度中央国家机关空调集中采购项目”发布中标公告，志高中央空调榜上有名，成为“商用空调”的指定三大品牌之一。</P>
<P>政府采购对产品技术、性能、品质和服务的要求甚严，中标厂家产品品质必须得到保证。本次志高中标产品CMV-【V】直流变频多联机组采用R410A环保冷媒、高效直流变频涡旋式压缩机、行业尖端正弦波直流变频驱动等技术。产品具有高效节能、环保舒适的特征，以保证提供集低碳、智能、可靠、灵活于一体的全方位空调解决方案。在上海世博会、深圳大运会等重大项目得到广泛应用，并获得甲方和行业的一致好评。</P>
<P>2011年是“十二五”开局之年，随着国家各种重大项目的兴建，政府采购项目将成为国内中央空调企业白热化争夺的新战场。此次，志高中央空调能够在激烈的角逐中脱颖而出，无疑是志高中央空调实力的验证。志高中央空调从建立开始，历经多年的创新和开拓，实现了从无到有、从有到优的蜕变。2011年开始，以做专业的中央空调设备供应商为目标，志高中央空调实现技术变革和管理模式调整，专业步伐越发坚定。</P>
<P>业界人士指出，中标中央国家机关采购项目既是一种肯定，又是一种鞭策，行业繁荣发展需要企业向上力量的推动。志高中央空调政府采购负责人表示，志高中央空调正积极参与全国各方的政府采购招标，努力为政府采购制定各种因地制宜的合理化空调项目方案。</P>
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