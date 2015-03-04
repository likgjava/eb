<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<script language="javascript">
function init(){
	ocx.IsShowPreviewBtn = false;
    ocx.IsShowWndBtn     = false;
    ocx.lVideoWindNum    = 1;
	ocx.LoginDeviceEx(document.getElementById("openBidRommIP").value, document.getElementById("openBidRommPort").value, document.getElementById("openBidRommUserName").value, document.getElementById("openBidRommPassword").value, 0);
	openMain();
}

function openAll(){
	ocx.ConnectAllChannle();
}

function openMain(){
	ocx.ConnectRealVideo(0, 0);
}

function openAid(){
	ocx.ConnectRealVideo(1, 0);
}
</script>
</head>
<body onload="init()" style="width: 100%;height: 300px;border: 0px solid;">
<input type="hidden" id="openBidRommIP" value="<%=request.getParameter("ip")%>"/>
<input type="hidden" id="openBidRommPort" value="<%=request.getParameter("port")%>"/>
<input type="hidden" id="openBidRommUserName" value="<%=request.getParameter("username")%>"/>
<input type="hidden" id="openBidRommPassword" value="<%=request.getParameter("password")%>"/>
<center>
	<!--  <input type="button" value="全部" onclick="openAll()">-->
	<input type="button" value="主摄像头" onclick="openMain()">
	<input type="button" value="辅摄像头" onclick="openAid()">
	<br>
	<OBJECT id=ocx onreadystatechange=iniocx()
		codeBase="webrec.cab#version=2,1,7,21"
		classid=CLSID:108D3206-846A-4A93-BACB-F0572D043ED7 width="400px" height="300px"
		>
		<PARAM NAME="_Version" VALUE="65536">
		<PARAM NAME="_ExtentX" VALUE="16033">
		<PARAM NAME="_ExtentY" VALUE="12779">
		<PARAM NAME="_StockProps" VALUE="0">
		<PARAM NAME="SetLanguage" VALUE="101">
		<PARAM NAME="SetHostPort" VALUE="37777">
		<PARAM NAME="lVideoWindNum" VALUE="1">
		<PARAM NAME="VideoWindBGColor" VALUE="">
		<PARAM NAME="VideoWindBarColor" VALUE="">
		<PARAM NAME="VideoWindTextColor" VALUE="">
		<PARAM NAME="IsShowPreview" VALUE="0">
		<PARAM NAME="IsShowWndBtn" VALUE="0">
	</OBJECT>
</center>
</body>
</html>