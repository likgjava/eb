<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@page import="java.util.*,com.gpcsoft.epp.noticemanage.domain.*" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/noticemanage/receipt_notice.js"></script>
<%Notice notice = (Notice)request.getAttribute("notice");%>

<form id="noticeForm">
    <input type="hidden" id="fromType" value="${param.fromType}">
	<input type="hidden" id="noticeId" name="objId" value="<%=notice.getObjId() %>">
	<input type="hidden" id="receviceStatusId" name="receviceStatus" value="${notice.receiveStatus}">
		
			<span>${notice.contents}</span>
	
</form>

<c:if test="${notice.receiveStatus=='00'}">
	<div class="formLayout formPa">
			<div class="conOperation">
	       	  	<button id="passButton" type="button" tabindex="18"><span>确认 </span></button>
	   	    </div>
	</div>
</c:if>