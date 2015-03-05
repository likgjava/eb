<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/casconfig/cas_system_list.js"></script>

 <div class="formLayout ">
  <form id="casSystemForm" method="post">
  		<input type="hidden" name="objId" name="objId">
    	<h5><span>子系统信息</span></h5>
    	<ul>
		      <li>
		        <label>系统URL:</label>
		        <input type="text" name="url" id="url" class="required" class="required" />
				 <span class="eleRequired">*</span>
		      </li>
		      <li>
		        <label >系统名称:</label>
		        <input type="text" name="name" id="name" class="required" class="required"/>
				<span class="eleRequired">*</span>
		      </li>
		      
	    </ul>
		   <div class="conOperation">
		       <button  id="save" type="button" ><span><spring:message code="globe.save"/></span></button>
		   </div>
    </form>
  </div>
<table id="casSystemGrid">
    
  </table>
