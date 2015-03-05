<%@ page contentType="text/html;charset=UTF-8" %>

<div id="helpLeaderSysInfo"></div>

<script>
$(document).ready(function(){
	//加载客服热线，客户意见等
	$("#helpLeaderSysInfo").loadPage($('#initPath').val()+"/view/staticpags/load/helpLeaderSysInfo.jsp");
});
</script>