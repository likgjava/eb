<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>IBM研发石墨烯高速电路板：取代CMOS晶体管？- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a9560130869381ed042d.jsp" title="IBM研发石墨烯高速电路板：取代CMOS晶体管？" class="cmsHref_self">IBM研发石墨烯高速电路板：取代CMOS晶体管？</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>IBM研发石墨烯高速电路板：取代CMOS晶体管？</h1>
					<div class="source">
						<span>发布时间：2011-06-13</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据国外媒体报道，IBM研发人员周四表示，他们已经使用石墨烯薄片(grapheme)材料设计出高速电路板。石墨烯是一种超薄材料，被广泛应用于高带宽通讯以及新一代的低成本智能手机和电视屏幕。</p>
<p>石墨烯作为一种特殊形态的石墨，主要由一层蜂窝状格网结构的碳原子构成，可用于提高传导速度。</p>
<p>美国《科学》杂志刊登了IBM的这一研究成果。在该电路板的设计中，研究人员在硅晶片中内置了一个宽带混频器。该电路板被广泛应用于通讯设备的设计中，作用是在不同的频段之间转换信号。在此前的研究中，IBM曾开发出单独石墨烯晶体管，但是还从未研发出完整的电路板。</p>
<p>但这种新材料还无法取代传统的CMOS晶体管，后者目前是消费电子产品系统中微处理器和电脑内存普遍使用的材质。石墨烯还不完全具备半导体材料所具有的物理特性，因此很难实现传统晶体管的工作机理。</p>
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