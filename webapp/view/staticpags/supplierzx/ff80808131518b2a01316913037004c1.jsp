<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>北京将考核公车定点维修供应商- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316913037004c1.jsp" title="北京将考核公车定点维修供应商" class="cmsHref_self">北京将考核公车定点维修供应商</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>北京将考核公车定点维修供应商</h1>
					<div class="source">
						<span>发布时间：2011-07-27</span>
						<span>发布人：-新京报  </span>
					</div>
					<p><P>北京市财政局昨日消息，为了杜绝公车运营过程中，中标商存在不规范行为，北京将对市级行政事业单位公务用车统一定点维修、保险、加油和会议定点供应商实施监督考核。</P>
<P>考核为期两个半月</P>
<P>昨日，北京市财政局发布消息称，为加强对协议采购供应商的监督管理，规范预算单位公务车运行费用和会议费支出行为，7月15日-9月30日，市财政局将对市级行政事业单位公务用车统一定点维修、保险、加油和会议定点供应商实施监督考核。考核工作采取供应商自查和市财政局抽查的方式，检查工作结束后将在政府采购相关媒体公布考核结果，作为新一期协议采购项目评审以及预算单位选择中标供应商的重要参考依据。</P>
<P>据了解，此前，北京市财政局公务用车系统上线试运行。该系统全面反映车辆库管理系统的每一部车的所有信息。通过该系统，可以查询到每个预算单位、每部车的基本信息以及车辆运行中的加油记录、保险、维修记录等。对每一辆车实现了动态管理。</P>
<P>新系统通过对运营数据的采集、分析、对比，从中取得车辆在运营过程中的费用、使用异常等监控数据，为财政监督提供了可靠的技术保障。通过新系统的监控功能，能够在大量的费用信息中快速提取运营异常信息，如加油费用异常、维修情况异常，并能够根据异常情况迅速定位明细信息，提高了预算费用使用的可视化程度。</P>
<P>目的为控制行政经费</P>
<P>市财政局有关负责人表示，此次对公车维修、保险和加油等定点供应商进行考核，也是为了财政资金使用效率更高，避免违规行为的出现。这是加强政府采购协议供货商的考核与管理，控制行政经费的一项措施。</P>
<P>此前，北京也曾出台政策，规范公车加油，防止公车私用、替别人加油等。</P>
<P>据了解，对于这些供应商，北京都有一定条件限制。比如对于加油的投标人资格，要求有社会保障资金缴纳记录，以及参加政府采购活动前三年内，没有重大违法记录等。记者查询看到，以2011-2012年北京市级行政事业单位公务用车统一保险定点服务商为例，中标单位有三家，包括中国人民财产保险股份有限公司、中国太平洋财产保险股份有限公司、中国平安财产保险股份有限公司。</P>
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