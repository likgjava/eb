<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>美的、格力、海尔进入中国百强企业- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b7241fe245a.jsp" title="美的、格力、海尔进入中国百强企业" class="cmsHref_self">美的、格力、海尔进入中国百强企业</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>美的、格力、海尔进入中国百强企业</h1>
					<div class="source">
						<span>发布时间：2011-07-15</span>
						<span>发布人：-艾肯空调制冷网  </span>
					</div>
					<p><P>2011年7月13日上午10点，《财富》连续第二年发布中国企业500强。前三排名未变，中国石化以1.91万亿的收入排名第一，中国石油1.47万亿排名第二、中国移动4852.3亿排名第三。今年是我们第二次发布中国500强排行榜。与上一年相比，入围营业收入门槛提高了12亿元。今年，中国500强总收入达18.9万亿，占中国GDP的47%。</P>
<P>在中央空调行业，广东美的电器股份有限公司以745.59亿元的总收入位居总榜单的第43位，较2010年度提升4位。需要说明的是，美的也是中国空调行业中排名最高的企业。紧随其后的是销售收入608.07亿元的珠海格力电器股份有限公司，排名56位，较2010年度下降2位。排名第三的是青岛海尔股份有限公司，销售收入达到605.88亿元，排名总榜单的59位，同比2010年度上升19位。值得一提的是，这三家空调企业中格力的排名虽有降低但是其利润确实最高的，达到42.76亿元，其次是美的的31.27亿元，最后是海尔的20.35亿元。</P>
<P>除去美的、格力和海尔，空调行业中的青岛海信、海信科龙、同方股份、TCL等企业纷纷上榜。</P>
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