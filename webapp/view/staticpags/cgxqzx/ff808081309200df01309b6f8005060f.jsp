<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>国信招标集团首次应用电子化平台公开招标- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309200df01309b6f8005060f.jsp" title="国信招标集团首次应用电子化平台公开招标" class="cmsHref_self">国信招标集团首次应用电子化平台公开招标</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>国信招标集团首次应用电子化平台公开招标</h1>
					<div class="source">
						<span>发布时间：2011-06-17</span>
						<span>发布人：-中国采购与招标网  </span>
					</div>
					<p><p>近日，国家开发银行2011年第一批服务器类设备采购项目公开招标在北京国信招标集团开标室顺利举行。招标活动全程首次应用先进的电子招标模式完成，并获得了业主、评标专家的肯定以及众多投标人的好评。</p>
<p>据电子化招标平台&mdash;&mdash;阳光易购电子采购公共服务平台有关负责人介绍，该平台基于国信招标集团多年的业务数据基础，应用先进的技术手段把招投标活动中的各相关主体联结起来，开发建成的电子采购公共服务平台。与传统意义上的招标相比，阳光易购电子采购公共服务平台为工程、货物、服务、土地及产权交易等多种交易模式进行分类管理和技术支持，具有更加便捷、更高效率、更为绿色、更为低碳、更为阳光等优势。平台对招标、投标、专家抽取、评标等环节进行整体规划串联，有效的实现了从招投标采购需求到合同备案全部交易过程的信息互通共享和服务和管理。</p>
<p>该平台有效的解决了中国电子商务发展的安全和信用问题。对招投标信息内容、开标、评标、定标等现场进行录音录像并刻录存档，为监管部门的后续监管提供完整的历史数据。</p>
<p>阳光易购平台还将&ldquo;泛招标&rdquo;的概念引入电子招标采购实践。平台服务范围覆盖招标与非招标采购业务，具备竞价采购、议价比价采购、直接订购、泛招标和团购等采购功能。</p>
<p>在刚刚结束的第五届招投标领域年度盛典暨CEO峰会上，国信招标集团携手中招、中技、中仪、中机、中化、中电投等全国15家著名的招标代理机构代表全国招标代理机构共同发起推进电子招标采购公共服务平台建设宣言。</p>
<p>国信招标集团作为国内招标行业龙头企业和顶级品牌企业践行宣言，应用第三方平台实现电子招标，对传统招标方式进行创新，对加快电子招标采购步伐有着积极的意义。</p>
<p align="right">中国采购与招标网记者：王海玲</p>
<p align="right">2011年6月16日</p>
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