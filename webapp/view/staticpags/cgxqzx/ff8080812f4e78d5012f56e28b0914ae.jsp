<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>第65届医博会16日在深开幕 600余种新品将上市- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4e78d5012f56e28b0914ae.jsp" title="第65届医博会16日在深开幕 600余种新品将上市" class="cmsHref_self">第65届医博会16日在深开幕 600余种新品将上市</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>第65届医博会16日在深开幕 600余种新品将上市</h1>
					<div class="source">
						<span>发布时间：2011-04-15</span>
						<span>发布人：-  </span>
					</div>
					<p><p>中国经济网深圳4月15日讯(记者杨阳腾 李小芳)中国经济网记者获悉，第65届中国国际医疗器械博览会(简称&ldquo;医博会&rdquo;)将于4月16日至19日在深圳会展中心举办，展出面积达11万平方米，预计超过600种医疗新产品将集中上市，这也是始创于1979年的医博会第六次在深圳举办。</p>
<p>本届展会以&ldquo;基础医疗解决方案&rdquo;为主题，展位达5400个，参展产品包括40大类上万种产品，覆盖医疗器械整条产业链；作为医博会的专业分展区，中国最大的医用影像设备展区产品覆盖放射、超声、核医学以及介入学科领域，产品涉及15大类别，西门子、飞利浦、迈瑞等国内外知名企业将悉数参展。</p>
<p>作为医疗行业的知名设备采购平台，本届医博会吸引了包括港澳台地区在内的各省、市、地区及团体共35个国内展团参展，美国、德国、日本、韩国等24个国家或地区组团参展。预计将有来自100多个国家和地区超过12万人次观众到场参观；截至4月13日，已有来自上百个国家和地区的2万余名专业买家通过网站及电话进行预登记。</p>
<p>本届展会上，超过600余种新产品将在现场集中发布。其中GE医疗将推出十多项创新技术和产品，包括分子影像产品、超声诊断系统和磁共振设备，以及面对基层医疗推出的一系列新产品。</p>
<p>为期4天的医博会期间，还将有20多场涉及多学科、多领域的论坛举行。</p>
<p>据悉，2010年，中国医疗器械市场首次突破千亿大关，并以23%的高增长率成功跃升至世界第二位。专业Frost&amp; Sullivan预测，&ldquo;十二五&rdquo;期间中国整个医疗仪器与设备市场将翻一番左右，达到537亿美元。</p>
<p>&nbsp;</p>
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