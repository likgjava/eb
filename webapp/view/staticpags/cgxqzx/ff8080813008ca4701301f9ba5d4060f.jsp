<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>石家庄严禁医疗机构违法采购中药饮片- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813008ca4701301f9ba5d4060f.jsp" title="石家庄严禁医疗机构违法采购中药饮片" class="cmsHref_self">石家庄严禁医疗机构违法采购中药饮片</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>石家庄严禁医疗机构违法采购中药饮片</h1>
					<div class="source">
						<span>发布时间：2011-05-24</span>
						<span>发布人：-  </span>
					</div>
					<p><p>从本月起，石市有关部门将加强对中药饮片使用环节的监管燕赵都市报，进一步规范医疗机构对饮片的管理工作，严禁医疗机构违法采购中药饮片调剂使用。</p>
<p>近年来，各级监督管理部门加强监管，规范了中药饮片的生产、经营和使用行为，使中药饮片质量水平有所提高。然而中药饮片生产、经营和使用等环节还存在一些不规范的问题，个别生产企业存在着不按《药品生产质量管理规范》(GMP)要求生产，甚至外购散装饮片，加工包装等行为;部分经营企业和医疗机构存在着从不具有资质的生产经营企业采购和使用中药饮片等问题。</p>
<p>按照有关通知的要求，各级卫生行政和中医药管理部门将加强对中药饮片使用环节的监管，进一步规范医疗机构对饮片的管理工作。医疗机构从中药饮片生产企业采购，必须要求企业提供资质证明文件及所购产品的质量检验报告书;从经营企业采购的，除要求提供经营企业资质证明外，还应要求提供所购产品生产企业的《药品GMP证书》以及质量检验报告书。</p>
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