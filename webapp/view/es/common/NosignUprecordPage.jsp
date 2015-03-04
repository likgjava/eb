<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<c:if test="${judgeSignUprecord=='02'}">
您还没有报名，请先报名！
</c:if>
<c:if test="${judgeSignUprecord=='01'}">
请您耐心等待审核！
</c:if>