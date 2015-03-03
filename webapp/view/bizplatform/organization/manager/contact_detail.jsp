<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="ContactForm" method="post" modelAttribute="employee">
	<input type="hidden" name="objId" id="objId" value="${employee.objId}" />
  	<div class="formLayout form2Pa">
  	<h4><span>联系人信息</span></h4>
	    <ul>
	      <li class="fullLine">
	        <label>姓名：</label>
	        <span>${employee.name}</span>
	      </li>
	      <li class="fullLine">
	        <label>性别：</label>
	        <span>
	        	<c:choose>
	        	<c:when test="${employee.sex==true}">
	        		男
	        	</c:when>
	        	<c:when test="${employee.sex==false}">
	        		女
	        	</c:when>
	        	<c:otherwise>
	        	</c:otherwise>
	        	</c:choose>
	        </span>
	      </li>
	      <li class="fullLine">
	        <label>身份证号：</label>
	        <span>${employee.idCard}</span>
	      </li>
	       <li class="fullLine">
	        <label>移动电话：</label>
	        <span>${employee.mobile}</span>
	       </li>
	       
	       <li class="fullLine">
	        <label>办公电话：</label>
	        <span>${employee.telOffice}</span>
	      </li>
	      <li class="fullLine">
	        <label>家庭电话：</label>
	        <span>${employee.telHome}</span>
	      </li>
	      <li class="fullLine">
	        <label>传真：</label>
	        <span>${employee.fax}</span>
	      </li>
	      <li class="fullLine">
	        <label>通信地址：</label>
	        <span>${employee.address}</span>
	      </li>
	      <li class="fullLine">
	        <label>邮编：</label>
	        <span>${employee.zipCode}</span>
	      </li>
	     <li class="fullLine">
	        <label>msn：</label>
	        <span>${employee.msn}</span>
	       </li>
	     <li class="fullLine">
	        <label>qq：</label>
	        <span>${employee.qq}</span>
	     </li>
	     <li class="fullLine">
	     	<label>email：</label>
	     	<span>${employee.email}</span>
	     </li>
	   </ul>
	</div>
	
	<div class="conOperation">
		 <button type="button" onclick="$('.conOperation').closest('.epsDialog').find('.epsDialogClose').trigger('click');" class="largeBtn" id="returnBtn"><span>关闭</span></button>
	</div>
</form:form>


      
      
      
      
      
      
      
      
      
	