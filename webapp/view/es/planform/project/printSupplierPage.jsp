<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%
	pageContext.setAttribute("gpc", request.getContextPath());
	String content = request.getSession().getAttribute("content")+"";
%>
<script type="text/javascript" src='${gpc }/view/resource/plug-in/jquery/jquery.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>//view/es/common/RequestContentPrint.js"></script>
<style type="text/css" media="print">
.noprint{display : none }
</style>
	<div class="noprint" style="text-align: left;">
		<button type="button" id="print_info" ><span><span id="submitSpan">打印</span></span></button>
		<button type="button" id="print_view_info" ><span><span>预览</span></span></button>
		<button type="button" id="exp_info" ><span><span>导出Word</span></span></button>
	</div>
<div id="checkRequestInfo" class="frameReport">
<input type="hidden"  id="projectId" value="${param.projectId}">
	<p >参与报名的投标单位：</p>
	<table style="width: 60%">
    	<tr >
	        <th align="center">序号</th>
	        <th align="center">投标单位</th>
	        <th align="center">联系电话</th>
	        <th align="center">联系人</th>
	        <th align="center">报名时间</th>
      	</tr>
      	<c:set value="0" var="signupListCount" />
      	<c:forEach items="${signupList}" var="signUprecord">      
      	<tr >
	        <td   align="center" width="5%"><c:set value="${signupListCount+1 }" var="signupListCount" />${signupListCount }</td>
	        <td align="center" width="20%">${signUprecord.supplier.orgName}</td>
	        <td align="center" width="20%">${signUprecord.linkerTel}</td>
	        <td align="center" width="20%">${signUprecord.linker}</td>
	        <td align="center" width="20%">${fn:substring(signUprecord.createTime,0,19)}</td>
      	</tr>
      	</c:forEach>
	</table>
</div>
<OBJECT id='wb' name='wb' classid='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2' height='0' width='0' ></OBJECT>
