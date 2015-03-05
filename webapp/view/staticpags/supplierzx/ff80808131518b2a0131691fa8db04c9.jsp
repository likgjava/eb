<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>格力电器半年净利增四成 同比增长60.03%- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a0131691fa8db04c9.jsp" title="格力电器半年净利增四成 同比增长60.03%" class="cmsHref_self">格力电器半年净利增四成 同比增长60.03%</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>格力电器半年净利增四成 同比增长60.03%</h1>
					<div class="source">
						<span>发布时间：2011-07-27</span>
						<span>发布人：-大洋网-广州日报  </span>
					</div>
					<p><P>格力电器昨日晚间公布半年报，报告期内，格力实现营业总收入402.39亿元，同比增长60.03%;归属于母公司的净利润22.08亿元，同比增长40.37%;每股收益0.78元，同比增长40.37%。分析认为，这一成绩单超出市场此前预期，空调企业“双寡头”的局面越趋明显。</P>
<P>格力昨日指出，增长主要得益于国内外市场需求强劲，产品销售收入大幅增长;同时公司具有自主知识产权的新产品销售大幅增长提高了公司的盈利能力。</P>
<P>报告显示，上半年格力空调实现销售收入372.04亿元，同比增长61.86%，其中格力已明显调整了产品结构，知情人透露，格力变频空调的销量已经超过总销量的40%。</P>
<P>不过为扩大销量，格力上半年也明显加大了促销力度，销售费用较上年同期增长31.12%。</P>
<P>格力表示，下半年将完善空调产品线和优化产品结构、加大中央空调的推广力度和自有品牌的出口力度等举措。</P>
<P>半年报数据显示，空调营业收入占据格力家电制造营业收入近98%，而小家电等业务的收入占比则不到1.6%。</P>
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