<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@page import="java.util.*,com.gpcsoft.epp.noticemanage.domain.*" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/noticemanage/draft_notice.js"></script>
<%Notice notice = (Notice)request.getAttribute("notice");%>

<form id="noticeForm">
	<input type="hidden" name="objId" id="noticeObjId" value="${notice.objId}">
	<input type="hidden" name="useStatus" id="useStatusId" value="${notice.useStatus}">
	<input type="hidden" name="sendStatus" id="sendStatusId" value="${notice.sendStatus}">
	<input type="hidden" name="receiveStatus" id="receiveStatusId" value="${notice.receiveStatus}">
	<input type="hidden" name="noticeTitle" id="noticeTitle" value="${notice.noticeTitle}">
	<input type="hidden" name="noticeType" id="noticeType" value="${notice.noticeType}">
	<input type="hidden" name="winnerId" id="winnerId" value="${winnerId}">
	<input type="hidden" name="subproject" id="subproject" value="${notice.project.objId}">
	<div class="formLayout form2Pa">  
		<ul>
				<li>
			      	<label class="short">标题：</label>
			      	<span><%=notice.getNoticeTitle() %></span>
			      </li>
				<li>
			      	<label class="short">结果：</label>
			      	<span><%=notice.getNoticeTypeCn() %></span>
			      </li>
		</ul>
	</div>
<div>
	<textarea name="contents" id="contents" class="required hidden"/></textarea>
</div>
<c:if test="${notice.useStatus=='00'}">
	<div class="conOperation">									
			<%--
			<button id="submitNotice" type="button" tabindex="18" title="发送"><span>发送</span></button>
			--%>
			<button id="saveNotice" type="button" tabindex="18"><span>保存</span></button>
			<button id="closeNotice" type="button" tabindex="18"><span>关闭</span></button>
	</div>
</c:if>
<textarea id="contentsBack" style="display:none;"><%=notice.getContents()==null?"":notice.getContents()%></textarea>	
		
</form>