<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>内蒙古服装采购引入盲评- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813021070101302499309a0088.jsp" title="内蒙古服装采购引入盲评" class="cmsHref_self">内蒙古服装采购引入盲评</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>内蒙古服装采购引入盲评</h1>
					<div class="source">
						<span>发布时间：2011-05-25</span>
						<span>发布人：-  </span>
					</div>
					<p><p>近年来，内蒙古自治区政府采购中心(以下简称&ldquo;采购中心&rdquo;)的采购范围不断扩大。自2006年起，采购中心就承担了工商、司法和公安等执法部门的服装采购任务。而在具体的服装采购项目中，最令采购人担忧的就是服装的面料问题。</p>
<p>采购中心主任刘恒斌介绍说，服装采购最突出的问题就是一些供应商提供的面料质次价高，但却很容易打着名牌的旗号来误导专家。</p>
<p>此外，甚至可能出现供应商和评标专家勾结、供应商和采购人私下达成协议等多种问题，而面料的品牌或者标志是出现问题的关键，所有私下勾结都围绕着品牌来展开。有鉴于此，采购中心经过多次论证，开始在服装采购中试行盲评。</p>
<p>&ldquo;所谓盲评，就是将供应商提供的样品面料上的标志去掉，由采购一处负责统一编号，然后将编号以及对应的标志汇总成清单，交给综合处存档，负责组织开标的采购二处不能接触编号清单等信息。&rdquo;刘恒斌介绍说，在去掉标志后，评标专家看到的全是没有标志、只有编号的样品。&ldquo;诸多的保密工作均让这类评标成为了名副其实的盲评。在这种情况下，评委的评判就没有主观因素，只能依靠样品的质量。&rdquo;</p>
<p>刘恒斌认为，去掉面料标志后，专家的评判结果也相对更加公正。评标结束后，采购中心会按照编号对应的标志来确认中标供应商。&ldquo;自从采购中心创造性地发明了盲评办法后，每次服装采购都会得到采购人的高度认可。&rdquo;刘恒斌认为，盲评与服装采购的特点非常契合。&ldquo;当然，任何一项措施都会有其不足之处。&rdquo;刘恒斌表示，采购中心会在今后的工作中进一步完善盲评方法，并进行更多的创新。</p>
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