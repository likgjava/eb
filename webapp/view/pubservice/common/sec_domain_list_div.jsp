<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@page import="com.gpcsoft.bizplatform.organization.domain.OrgInfo"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.gpcsoft.pubservice.common.service.impl.SecDomainServiceImpl"%>
<%
if(SecDomainServiceImpl.getOrgInfoByPassSiteAll()!=null){
	Set<Map.Entry<String, Object>> set = SecDomainServiceImpl.getOrgInfoByPassSiteAll().entrySet();
	for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it.hasNext();) {
	    Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
	    %>
		
		<tr>
			<td><%=((OrgInfo)entry.getValue()).getOrgName()%></td>
			<td><fmt:formatDate value="<%=((OrgInfo)entry.getValue()).getCreateTime()%>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td><%=entry.getKey()%></td>
		</tr>
	    <% 
	}
}
%>