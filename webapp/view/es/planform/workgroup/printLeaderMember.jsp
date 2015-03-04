<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<%
	pageContext.setAttribute("gpc", request.getContextPath());
	String content = request.getSession().getAttribute("content")+"";
%>
<script type="text/javascript" src='${gpc }/view/resource/plug-in/jquery/jquery.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>//view/es/common/RequestContentPrint.js"></script>

<style type="text/css" media="print"> 
.noprint{display : none }
.frameReport{ border-style: none }
.frameReport table{border-style: none; }
</style>
	<div class="noprint" style="text-align: left;">
		<button type="button" id="print_info" ><span><span id="submitSpan">打印</span></span></button>
		<button type="button" id="print_view_info" ><span><span>预览</span></span></button>
		<button type="button" id="exp_info" ><span><span>导出Word</span></span></button>
	</div>
	
<div class="frameReport" id="checkRequestInfo">
    <p class="headInfo">&nbsp;</p>      	
    <h1 align="center"><span>${project.projName}</span></h1>
    <h2 align="center"><span>领导组</span></h2>
 	<p class="headInfo">&nbsp;</p> 
 	
 	<form:form method="post" modelAttribute="workgMember">
    <input type="hidden" id="subOrProjectId" value="${projectId}"/>
    
        <table  style="width:70%; margin-left: 300px;">
	        <tr>
			   <td colspan="2"><h2 ><span>组长:</span></h2></td>
			</tr>
			<tr>
			   <td style="width:50%;"><h3><span>工作单位：</span></h3></td>
			   <td style="width:20%;"><h3><span>姓名：</span></h3></td>
			 </tr>
			<c:forEach items="${leaderList}" var="leader">
			 <tr>
			   <td style="width:50%;">${leader.workgmCompany}</td>
			   <td style="width:20%;">${leader.workgmName}</td>
			 </tr>
			</c:forEach>
		</table>
		<br>
		<br>
		<table style="width:70%; margin-left: 300px;">
			<tr>
			   <td colspan="2"><h2><span>副组长:</span></h2></td>
			</tr>
			<tr>
			   <td style="width:50%;"><h3><span>工作单位：</span></h3></td>
			   <td style="width:20%;"><h3><span>姓名：</span></h3></td>
			 </tr>
			<c:forEach items="${deputyList}" var="deputy">
			 <tr>
			   <td style="width:50%;">${deputy.workgmCompany}</td>
			   <td style="width:20%;">${deputy.workgmName}</td>
			 </tr>
			</c:forEach>
		</table>
		<br>
		<br>		
		<table style="width:70%; margin-left: 300px;">
			<tr>
			   <td colspan="2"><h2><span>成员:</span></h2></td>
			</tr>
			<tr>
			   <td style="width:50%;"><h3><span>工作单位：</span></h3></td>
			   <td style="width:20%;"><h3><span>姓名：</span></h3></td>
			 </tr>
			<c:forEach items="${workerList}" var="worker">
			 <tr>
			   <td style="width:50%;">${worker.workgmCompany}</td>
			   <td style="width:20%;">${worker.workgmName}</td>
			 </tr>
			</c:forEach>
	   </table>
    </form:form>
</div>

<OBJECT id='wb' name='wb' classid='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2' height='0' width='0' ></OBJECT>