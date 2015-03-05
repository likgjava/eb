<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>东日本大地震严重冲击全球零部件供应链- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f29300910a48.jsp" title="东日本大地震严重冲击全球零部件供应链" class="cmsHref_self">东日本大地震严重冲击全球零部件供应链</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>东日本大地震严重冲击全球零部件供应链</h1>
					<div class="source">
						<span>发布时间：2011-07-04</span>
						<span>发布人：-中国经济网  </span>
					</div>
					<p><P>北京7月3日讯 据日本雅虎新闻7月1日报道：据日本政府1日发表的《2011年度中小企业白皮书》显示：东日本大地震使得青森、岩手、宫城、福岛4个县沿海地区的中小企业超过5成全部损毁，4成企业损毁一半或一部分。</P>
<P>以中小企业加盟的全国商工会联合会的报告为基础整理出的受灾情况显示，4个县的沿岸地区的6142家公司中，有3344家全部损毁，占总体的54.4%。损毁一半的企业有783家，占总体的12.7%。损毁一部分的有1763家，占总体的28.7%。4个县中没有未受任何损失的企业。</P>
<P>受灾地区的工业发货金额在2008年的调查数据中显示为116500亿日元，占全国的3.8%。其中集成电路占7.1%，其他的电子零部件、元件、电子回路占到全国的8.1%。《白皮书》强调，日本此次地震导致中小企业受灾，这将冲击全国的零部件供应链。《白皮书》同时强调了工厂设备等支援对策的重要性。</P>
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