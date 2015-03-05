<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>12家不法红酒供应商被立案查处- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01315f0957fa0012.jsp" title="12家不法红酒供应商被立案查处" class="cmsHref_self">12家不法红酒供应商被立案查处</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>12家不法红酒供应商被立案查处</h1>
					<div class="source">
						<span>发布时间：2011-07-25</span>
						<span>发布人：-中国酒业新闻网  </span>
					</div>
					<p><P>近日，市工商局针对专业红酒市场和红酒批发企业，开展了一次进口红酒专项检查行动，检查发现，无生产日期和批次、未贴中文标签，索证索票不严是进口红酒存在的主要问题。</P>
<P>据了解，宁波市场上销售的进口红酒，除从宁波保税区口岸进口外，还有很大部分来自广州、上海、天津、厦门等城市，检查发现宁波保税区红酒市场管理规范，总体情况较好，但有些酒庄的情况却不容乐观。在慈溪浒山的一家红酒庄，工商人员发现仓库里摆满了各式各样的进口红酒，其中又以“LAFITE”(拉菲)品牌为主，该批红酒的外包装上写满了英文，唯独没有中文标识，商家既拿不出供应商的营业执照复印件，也没有出入境检验检疫部门签发的卫生证书。</P>
<P>市工商局经检处负责人说：“索证文章来源中国酒业新闻网索票制度落实不严格，进口红酒合法来源证明材料不齐备，进口红酒未加贴中文标签是我市红酒市场的主要问题之所在。”工商部门开展进口红酒专项检查行动一月以来，已检查各类红酒经营单位514家，立案查处12家，扣押涉嫌侵权的进口红酒80瓶，封存未粘贴中文标签的进口红酒1143瓶，对相关行政相对人进行行政告诫43次。针对检查过程中发现的各种问题，工商部门表示将分别采取行政指导和行政处罚相结合的方法予以规范。</P>
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