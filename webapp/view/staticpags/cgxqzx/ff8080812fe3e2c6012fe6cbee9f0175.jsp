<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>机械制造业用工需求增最多- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6cbee9f0175.jsp" title="机械制造业用工需求增最多" class="cmsHref_self">机械制造业用工需求增最多</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>机械制造业用工需求增最多</h1>
					<div class="source">
						<span>发布时间：2011-05-13</span>
						<span>发布人：-  </span>
					</div>
					<p><p>记者从市统计局了解到，在对我市443家大中型工业企业2011年一季度生产经营状况及趋势判断专项调查中显示，企业对第二季度生产经营预计总体乐观，用工需求与第一季度相比将明显增加。</p>
<p>据了解，企业接到的产品订货量比上季度增长的企业占31.4%，持平的占49.7%，下降的占19%，增长的企业比例比下降的企业高12.4个百分点。</p>
<p>调查显示，认为今年第二季度生产经营状况好的企业占40.6%，一般的占56.0%，差的占1.8%，看不清的占1.6%，即认为第二季度生产经营状况好及一般的企业占96.6%，看好的企业比例比看差的企业高38.8个百分点，企业对第二季度生产经营预计总体乐观。</p>
<p>在企业用工需求方面，预计第二季度用工需求比第一季度增加的企业占48.5%，持平的占46%，减少的占5.4%，预计增加的企业比例比减少的企业高43.1个百分点。分行业看，机械制造业企业认为第二季度用工需求增加的占65.2%，非金属矿物制品业占54.5%，纺织服装业占54%，食品饮料业占50%，石油化工业占47.4%，皮革、毛皮及其制品业占40.4%，工艺品及其他制造业占32%。</p>
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