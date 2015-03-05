<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>西藏城投大力发展锂产业- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130361e0401303e7f8b760107.jsp" title="西藏城投大力发展锂产业" class="cmsHref_self">西藏城投大力发展锂产业</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>西藏城投大力发展锂产业</h1>
					<div class="source">
						<span>发布时间：2011-05-30</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据咸阳日报报道，日前，咸阳市委书记千军昌会见了西藏城投等企业的董事长，并介绍了锂产业在当地的发展，双方就在咸阳合作建设锂业公司项目进行了商谈。</p>
<p>此前，由于&ldquo;锂&rdquo;概念而备受关注的西藏城投就发布公告称，公司与关联方联手拿下龙木错盐湖矿区开发控制权，而龙木错盐湖综合开发利用已于2009年7月获西藏阿里地区发改委立项批复文件，立项内容包括2级盐田和老卤盐田，分别建设年产3500吨工业硼酸、2000吨碳酸锂和20000吨氯化钾的加工厂，以及其他配套设施建设。</p>
<p>华泰联合指出，盐湖提锂是未来发展的主要技术路线。我国锂资源储量位居全球前三，但目前锂资源特别是盐湖资源利用度远远落后于其他国家。盐湖提锂仅占我国碳酸锂产量的30%，而国外这一比例高达70%以上。随着我国盐湖提锂技术的成熟，盐湖提锂产业未来几年将迎来大发展。而西藏城投拥有国内目前最大的碳酸锂储量，盐湖综合利用开发的技术成熟，能够实现资源的充分利用。</p>
<p>业内人士指出，如果公司参与碳酸锂提纯、阳极材料等与碳酸锂下游相关产业的产业园建设，能够实现打通锂电池新能源上下游产业链，将为公司打造新能源企业奠定坚实基础。</p>
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