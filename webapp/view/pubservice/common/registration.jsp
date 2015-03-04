<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="com.gpcsoft.core.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
</head>
<body>
<!--浏览器检测 开始-->
<%@ include file="/view/srplatform/portal/include/check_browser_ie6.jsp" %>
<!--浏览器检测 结束-->
<div id="container">
	<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
	<!--头部容器 开始-->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!--CSS-->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/register.css" />
	<!--头部容器 结束-->
	<!--主要内容容器 开始-->
	<div id="sysContent" class="page">
		<input type="hidden" id="defaultReg" value="${param.defaultReg}"/>
		<div id="contentSub"></div>
		<div id="contentMain" class="index1pa" >
			<div id="regContent">
				<div class="registered">
					<ul>
						<li><a href="javascript:void(0);" id="supplier" title="供应商">供应商</a></li>
						<li><a href="javascript:void(0);" id="buyer" title="采购人">采购人</a></li>
						<li><a href="javascript:void(0);" id="promoter" title="采购大使">采购大使</a></li>
						<li id="lastLi"><a href="javascript:void(0);" id="expert" title="咨询专家">咨询专家</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="contentSupp"></div>
	</div>
	<!--主要内容容器 结束-->
	<!--底部容器 开始-->
	<div class="footer">
		<%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!--底部容器 结束-->
	<!--在线客服开始-->
		<%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
	<!--在线客服结束-->
</div>
<!--扩展用容器，用于与内容无关的装饰性扩展-->
<div id="extraDiv">
	<div id="extraDiv1"></div>
	<div id="extraDiv2"></div>
	<div id="extraDiv3"></div>
	<div id="extraDiv4"></div>
	<div id="extraDiv5"></div>
	<div id="extraDiv6"></div>
</div>
</body>
</html>

<script>
var registration={};

$(document).ready(function(){
	//小额交易供应商注册
	$("#supplier").click(function(){
		$('#regContent').loadPage($('#initPath').val()+'/SupplierController.do?method=toRegisterUserPage&step=1');
	})
	
	//协议供货供应商注册
	$("#supplierXY").click(function(){
		$('#regContent').loadPage($('#initPath').val()+'/SupplierController.do?method=toRegisterPage&step=1');
	})
	
	//小额交易采购人注册
	var promoterUID = "&promoterUID=" + $('#promoterUID').val();
	$("#buyer").click(function(){
		$('#regContent').loadPage($('#initPath').val()+'/BuyerController.do?method=toRegisterGatePass&step=1'+promoterUID);
	})
	
	//协议供货采购人注册
	$("#buyerXY").click(function(){
		$('#regContent').loadPage($('#initPath').val()+'/BuyerController.do?method=toRegisterPage&step=1');
	})
	
	//代理机构注册
	$("#agency").click(function(){
		$('#regContent').loadPage($('#initPath').val()+'/AgencyController.do?method=toRegisterPage&step=1');
	})
    //采购大使注册 -- 暂时用评审专家图标
	$("#promoter").click(function(){
		$('#regContent').loadPage($('#initPath').val()+'/PromoterRegisterController.do?method=toRegisterPage&step=1');
	})
    //咨询专家注册 -- 暂时用评审专家图标
	$("#expert").click(function(){
		$('#regContent').loadPage($('#initPath').val()+'/ExpertInfoController.do?method=toRegisterPage&step=1');
	});

	//加载默认的注册页面
	if($("#defaultReg").val() != "" && $("#"+$("#defaultReg").val())){
		$("#"+$("#defaultReg").val()).click();	
	}
})
</script>