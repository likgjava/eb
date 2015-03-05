<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>市政协召开政府采购工作民主监督座谈会- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813090cbdd013090e97bf300ea.jsp" title="市政协召开政府采购工作民主监督座谈会" class="cmsHref_self">市政协召开政府采购工作民主监督座谈会</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>市政协召开政府采购工作民主监督座谈会</h1>
					<div class="source">
						<span>发布时间：2011-06-15</span>
						<span>发布人：-  </span>
					</div>
					<p><p>昨日(14日)上午，市政协召开政府采购工作民主监督座谈会。市财政局、市采购办负责人向市政协委员汇报了我市实施政府采购工作的基本情况和流程，并进行了面对面交流。市政协副主席俞国庆出席会议并讲话。</p>
<p>杭州市自1998年实施政府采购制度以来，采购规模由最初的0.16亿元，扩大到2010年的45.37亿元。如何管好用好这一笔钱，成为市政协委员们共同关心的话题。在民主监督会上，有10名市政协委员先后发言，还有多名委员递交了书面材料。委员们充分肯定政府采购工作取得的成绩，也直言不讳地指出了我市政府采购工作存在的不足，并提出了自己的意见和建议。</p>
<p>郑昶委员建议，要扩大政府采购范围，全程公开采购招标信息，建立供应商诚信记录机制。沈国民委员指出，在政府采购中要引入市人大代表和政协委员的监督，提高政府采购的透明度。吴卫星委员提出，要充实专家库，加强专家力量。钱永祥委员提出，要防止竞标单位&ldquo;低价中标，高价结算&rdquo;的行为，强化对竞标单位的资质审查。任旭荣委员则认为，政府采购重视程序，在效率、灵活性和降低采购成本方面存在不足，可以借鉴民营企业的做法，增加灵活度，提高采购效率，降低采购成本。</p>
<p>俞国庆充分肯定了政府采购工作所取得的成绩。他指出，政协民主监督的主要目的是帮助和支持政府采购部门查找问题、改进工作、提高效率。政府采购部门要高度重视政协委员提出的意见建议，加强学习，提高素质，不断完善采购机制，以高度责任心和事业心，管好用好&ldquo;纳税人&rdquo;的每一分钱。</p>
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