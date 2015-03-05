<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>内蒙古禁止餐饮单位采购和使用4家企业8种问题产品- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130f7c6a71e6e34.jsp" title="内蒙古禁止餐饮单位采购和使用4家企业8种问题产品" class="cmsHref_self">内蒙古禁止餐饮单位采购和使用4家企业8种问题产品</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>内蒙古禁止餐饮单位采购和使用4家企业8种问题产品</h1>
					<div class="source">
						<span>发布时间：2011-07-05</span>
						<span>发布人：-北方新闻网  </span>
					</div>
					<p><P>昨日（3日），自治区卫生厅转发了国家食品药品监督管理局《关于禁止餐饮服务单位采购和使用含邻苯二甲酸酯类物质问题产品的紧急通知》，要求各地立即对辖区内的餐饮服务单位进行专项监督检查，严禁采购广州市美益香料有限公司等4家企业生产的8种食品和食品添加剂，并加强监管，一经发现，要立即封存并做好相关处置工作。</P>
<P>据了解，针对台湾地区发现违法添加邻苯二甲酸酯类物质情况，有关部门迅速采取措施，进行了监督检查，并在广州市美益香料有限公司生产的番石榴香精，广东省江门市高迪食品有限公司生产的绿茶粉、液态酥油和蛋牛奶香油，江门市展望食品有限公司生产的面包酵素改良剂，杭州溢香源生物科技有限公司生产的桂花香精、绿茶香精、杏仁香精等4家企业8种产品中检出邻苯二甲酸酯类物质。</P>
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