<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>3月空调采购凸显政策功能- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7ae5ba012f7b2b2fb000e9.jsp" title="3月空调采购凸显政策功能" class="cmsHref_self">3月空调采购凸显政策功能</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>3月空调采购凸显政策功能</h1>
					<div class="source">
						<span>发布时间：2011-04-22</span>
						<span>发布人：-  </span>
					</div>
					<p><p>进入3月,尽管全国空调采购项目并未出现很多超级大单,但是大量的民生项目、新能源空调项目和文体中心建设所需空调采购等项目,为本月平稳的市场表现注入了大量的政策功能元素。</p>
<p>从本月空调采购整体情况来看,大标项目相比其他月份要少一些,位于前10位的大项目最后2项均在百万元以下,这在月度统计中是不多见的。但总体采购项目及规模相对表现平稳。</p>
<p>民生项目表现抢眼</p>
<p>综观3月空调采购,在众多空调采购项目中,民生项目依旧出现了几笔大单,地方财政向民生项目的倾斜政策效果凸显。其中,广东省中山市东凤镇文体艺术中心空调设备采购及安装项目,以630万元的中标金额位居本月政府采购电梯类十大中标项目前列。此外,河南省夏邑县高级中学空调采购项目,也以120万元的中标金额成为本月十大中标项目之一。</p>
<p>地源热泵引领空调新能源</p>
<p>据记者的不完全统计,在本月的空调采购项目中,江苏省阜宁县人民医院扩建病房楼及裙楼地源热泵空调系统采购及安装、调试等相关服务项目,以1808万元的中标金额领跑3月份政府采购电梯类十大中标项目。</p>
<p>记者了解到,地源热泵中央空调分为水源热泵和土壤热交换器地源热泵两种形式。其利用可再生能源高效节能,运行费用低、节水省地、运行安全稳定、可靠性高,有着常规空调技术无可比拟的优势。相关专家表示,现在我国对建筑节能的要求越来越高。近几年来,大中城市也在为改善大气环境大力推广使用包括可再生能源的清洁能源。地源热泵空调技术的完善和推广,将很好地满足这些需求。此次江苏省阜宁县为其人民医院采购地源热泵空调系统,进一步促进了新能源技术的应用,很好地发挥了政府采购的政策功能。<br />
&nbsp;</p>
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