<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
	<ul>
	   <li class="fullLine" >
	<c:import url="/view/srplatform/auth/attachment.jsp">
          <c:param name="attachRelaId" value="${param.attachRelaId}"/>
          <c:param name="isSingle" value="yes"/>
          <c:param name="isSelect" value="yes"/>
    </c:import>
       </li>
	</ul>