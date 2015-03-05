<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>豆浆机国标正式实施 1/3豆浆机品牌面临淘汰- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130531689090745.jsp" title="豆浆机国标正式实施 1/3豆浆机品牌面临淘汰" class="cmsHref_self">豆浆机国标正式实施 1/3豆浆机品牌面临淘汰</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>豆浆机国标正式实施 1/3豆浆机品牌面临淘汰</h1>
					<div class="source">
						<span>发布时间：2011-06-03</span>
						<span>发布人：-  </span>
					</div>
					<p><p>记者昨天(2日)了解到，由国家标准化管理委员会等权威机构共同制定的《豆浆机国家标准》从6月1日起正式实施。业内人士称，标准实施后，大约有三分之一的豆浆机品牌面临淘汰。</p>
<p>豆浆机国标首次明确了豆浆机的分类和命名方式，针对豆浆机的粉碎装置、粉碎度等重要技术指标，制定了详细的参数要求和试验方法，对豆浆机噪声、安全、粉碎度、浓度、蛋白含量以及出渣率等硬性指标均作出了明确界定。</p>
<p>中怡康公司的零售监测显示，截至2010年，国内可监测销量的豆浆机品牌有116个，因缺乏行业标准，豆浆机质量良莠不齐。有业内人士昨天告诉记者，此次国家标准的出台将对行业起到引导作用，也会对企业的产品结构提出新的要求，这在某种程度上将提高行业门槛，迫使企业进行技术升级，约有三分之一的豆浆机品牌面临淘汰。</p>
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