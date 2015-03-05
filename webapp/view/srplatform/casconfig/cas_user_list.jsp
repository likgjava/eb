<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.*" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/login/sha1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/casconfig/cas_user_list.js"></script>
 <div class="formLayout ">
  <form id="casUserForm" method="post">
    	<h5><span>用户信息</span></h5>
    	<ul>
		      <li>
		        <label>用户名:</label>
		        <input type="text" name="objId" id="objId" class="required"  />
				 <span class="eleRequired">*</span>
		      </li>
		      <li>
		        <label >中文名:</label>
		        <input type="text" name="ssoUserName" id="ssoUserName"/>
		      </li>
		      <li>
		        <label >密码:</label>
		        <input type="text" name="password" id="password" class="required" />
				<span class="eleRequired">*</span>
		      </li>
		      <li>
		        <label >对应子系统:</label>
		        <select name="sysId" class="required">
		        	<c:forEach var="sys" items="${sysList}" varStatus="state" >
                          <option value='<c:out value="${sys.objId}"></c:out>'><c:out value="${sys.name}"></c:out></option>
                   </c:forEach>
		        </select>
		        <span class="eleRequired">*</span>
		      </li>
		      
	    </ul>
		   <div class="conOperation">
		       <button  id="save" type="button" ><span><spring:message code="globe.save"/></span></button>
		   </div>
    </form>
  </div>
<table id="casUserGrid">
    
  </table>
