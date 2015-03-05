<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>标普调低诺基亚评级 长期企业信用降至BBB- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081317673560131924759fc1ada.jsp" title="标普调低诺基亚评级 长期企业信用降至BBB" class="cmsHref_self">标普调低诺基亚评级 长期企业信用降至BBB</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>标普调低诺基亚评级 长期企业信用降至BBB</h1>
					<div class="source">
						<span>发布时间：2011-08-04</span>
						<span>发布人：-  </span>
					</div>
					<p><P>评级机构美国标准普尔公司2日调低手机行业巨头诺基亚公司的评级，原因归咎于这家芬兰企业销售业绩惨淡、战略转型困难。</P>
<P>标普把诺基亚的长期企业信用从BBB+降至BBB。标普分析师马蒂亚斯·拉布说：“这一评级调整反映出诺基亚智能手机市场份额持续下降。”</P>
<P>拉布说，如果诺基亚的营业利润在今后6个月到9个月内得不到改善，标普今年可能会进一步调低对这家企业的评级。</P>
<P>诺基亚先前发布数据，今年第二季度净亏损3.68亿欧元(约合5.2亿美元)，销售额下降7.3%。它同时拒绝给出预期的第三季度收益。</P>
<P>一些分析师预计，诺基亚第三季度可能继续下滑。“我们预计，诺基亚的设备与服务领域2011年收益将下降大约20%，”标普说，“到2013年，将恢复至2010财政年度的水平。”</P>
<P>另外两家国际评级机构穆迪和惠誉国际已把诺基亚的评级降为“负面展望”。</P>
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