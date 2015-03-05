<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>童装市场需求持续扩容- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131a6d93b770af0.jsp" title="童装市场需求持续扩容" class="cmsHref_self">童装市场需求持续扩容</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>童装市场需求持续扩容</h1>
					<div class="source">
						<span>发布时间：2011-08-08</span>
						<span>发布人：-  </span>
					</div>
					<p><P>国内童装产业处于快速上升期，未来市场需求还将持续扩容。昨日，中国服装协会副秘书长谢青在“关注童装安全行动启动仪式”上表示，童装的内需增速达到20%左右，社会各界必须重视童装安全。</P>
<P>谢青表示，目前国内有6000多家童装品牌企业，童装产量占服装总量的6%。在童装市场快速扩容之际，童装安全不仅是生产企业的问题，更是印染、加工等供应链的问题，“所以关注童装安全，要关注整个产业链的安全。”</P>
<P>启动仪式上，中国网络童装知名品牌绿盒子公司总裁吴芳芳向社会承诺，将投入500多万元用于童装面料与成衣检测，检测内容包含甲醛含量、纺织品pH值、耐水色牢度、耐酸汗渍色牢度、耐干摩擦色牢度、可分解芳香胺染料等十几个类别，今年秋冬上市的童装均有面料、成衣、针检报告。</P>
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