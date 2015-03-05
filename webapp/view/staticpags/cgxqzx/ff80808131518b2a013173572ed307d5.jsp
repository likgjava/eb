<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中央单位批量采购试点工作进入执行期- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a013173572ed307d5.jsp" title="中央单位批量采购试点工作进入执行期" class="cmsHref_self">中央单位批量采购试点工作进入执行期</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>中央单位批量采购试点工作进入执行期</h1>
					<div class="source">
						<span>发布时间：2011-07-29</span>
						<span>发布人：-中国政府采购报  </span>
					</div>
					<p><P>近日,《关于进一步推进中央单位批量集中采购试点工作的通知》(以下简称《通知》)进入执行期,中共中央直属机关采购中心、中央国家机关政府采购中心和全国人大机关采购中心等三大中央级集中采购部门都已积极着手开展批量集中采购工作。这是记者从上述3个单位了解到的情况。</P>
<P>据悉,中央国家机关机关政府采购中心2011年第一期批量集中采购项目已于近日发出了招标公告。此次招标共有低泄射计算机等4个分包。该中心业务处室的一位负责人告诉记者,在接到《通知》之后,中心在其关于中央国家机关2011年政府集中采购计算机等产品协议供货有关事宜的通知中,就强调了各采购单位采购台式计算机、打印机时应严格按财政部关于批量集中采购的具体执行规定。随后,该中心还就批量采购工作制定了执行方案。与此同时,记者从全国人大机关采购中心了解到,该中心在接到《通知》之后,也在积极筹备批量采购工作,目前进展顺利。</P>
<P>另据记者了解,近日财政部将汇总上来的几家中直单位批量采购台式计算机和打印机的计划通过网络系统发送到了中共中央直属机关采购中心,这标志着该中心批量采购工作的正式启动。</P>
<P>据了解,为了进一步规范政府采购行为、深化集中采购工作,今年6月份财政部下发了该《通知》。其中规定中央单位采购的台式计算机和打印机,原则上全部纳入今年批量集中采购试点范围,并要求各主管部门组织好批量采购计划汇总报送工作,集中采购机构做好批量集中采购的招标等工作。同时,《通知》还提出各主管部门也可根据实际工作需要,制定本部门统一的台式计算机和打印机配置标准。</P>
<P>因特殊需要台式计算机采购项目预算金额在50万元以上、打印机采购项目预算金额在30万元以上的,可以经主管部门同意后,确定本次采购项目的特殊配置标准。在项目执行方面,各主管部门应当于每月5日前将下月的批量采购计划审核汇总后报送至财政部。财政部于每月10日前将各主管部门上报的批量采购计划汇总后送集中采购机构。集中采购机构根据批量采购计划按月组织批量集中采购,于30个工作日内完成采购组织活动,并在中国政府采购网公布采购结果。对于采购方式,集中采购机构可根据每月批量采购计划进行选择,但是要结合中央单位特点,保证采购工作高效完成。</P>
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