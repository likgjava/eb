<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>英特尔2200万美元投资中国三家公司- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081317673560131924619ef1ad7.jsp" title="英特尔2200万美元投资中国三家公司" class="cmsHref_self">英特尔2200万美元投资中国三家公司</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>英特尔2200万美元投资中国三家公司</h1>
					<div class="source">
						<span>发布时间：2011-08-04</span>
						<span>发布人：-  </span>
					</div>
					<p><P>英特尔(NASDAQ:INTC)旗下风投公司英特尔资本正式宣布对六度贸易(上海)有限公司(下称六度贸易)、北京佳视互动科技股份有限公司(下称佳视互动)和上海博康智能网络科技有限公司(下称博康智能)3家公司共同注资2200万美元。资金将用于产品开发和市场拓展。</P>
<P>六度贸易总部位于上海，主要运营时尚B2C网络百货商城耀点100;佳视互动是永新视博的子公司，主要致力于高清智能电视增值业务和广电智能终端解决方案的研发与推广;博康智能则是物联网技术公司，提供涵盖数字安全、公共交通及远程信息处理的方案与服务。</P>
<P>目前英特尔已向中国大陆6家公司投资，金额达4500万美元。2011年，该公司在中国的投资目标设定为12家，主要涉及IT、互联网及云计算等多个领域。投资企业包括电讯盈科、亚信、双威通讯、德信无线 、UT斯达康(NASDAQ:UTSI)、珠海炬力、东软集团、海辉软件、金山软件(3888.HK)等。</P>
<P>8月2日，英特尔投资总裁苏爱文在接受南方都市报记者专访时表示，英特尔投资对中国市场焦点是移动互联领域，十分看好平板电脑和智能手机的应用。</P>
<P>据公开资料显示，英特尔投资1998年开始在中国投资，至今已向中国内地和香港地区9个城市的近100家中国科技公司投入5亿多美金。</P>
<P>自1991年以来，英特尔投资已经向47个国家和地区的1050多家公司投入近100亿美元，投资组合中约240家公司被其它公司收购，另外180家公司在全球多个证券交易所公开上市。</P>
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