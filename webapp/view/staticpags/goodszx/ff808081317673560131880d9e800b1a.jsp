<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>6月全球芯片销售额247亿美元 环比降1.2%- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081317673560131880d9e800b1a.jsp" title="6月全球芯片销售额247亿美元 环比降1.2%" class="cmsHref_self">6月全球芯片销售额247亿美元 环比降1.2%</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>6月全球芯片销售额247亿美元 环比降1.2%</h1>
					<div class="source">
						<span>发布时间：2011-08-02</span>
						<span>发布人：-腾讯科技  </span>
					</div>
					<p><P>北京时间8月2日消息，据国外媒体报道，据半导体行业协会(SIA)称，6月份全球芯片销售额为247亿美元，较5月份的250亿美元下降1.2%，较去年同期的248亿美元下降了0.4%。</P>
<P>半导体行业协会表示，第二季度全球芯片销售额比第一季度减少了2%，但今年上半年芯片销售额较去年上半年增长了3.7%。总体来说，今年全球芯片销售额仍有望实现5.4%的增长率预期。</P>
<P>半导体行业协会称，6月份，由企业PC升级、智能手机需求增长、信息技术基础设施投资增长和中国市场增长等因素带来的增长被消费者需求下滑所抵消。</P>
<P>对于今年全年来说，预计除日本外的所有其他地区的芯片销售额都将比去年有所增长，日本仍处于地震后的恢复阶段。半导体行业协会补充说，就行业而言，除汽车行业之外的所有其他行业的芯片销售额都将实现增长。</P>
<P>半导体行业协会的成员包括英特尔、AMD、德州仪器和IBM。</P>
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