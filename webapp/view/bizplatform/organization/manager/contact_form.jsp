<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="ContactForm" method="post" modelAttribute="employee">
	<input type="hidden" name="objId" id="objId" value="${employee.objId}" />
	<input type="hidden" name="company.objId" id="company.objId" value="${param.companyId}">
	
  	<div class="formLayout form2Pa">
  	<h4><span>联系人信息</span></h4>
	    <ul>
	      <li class="fullLine">
	        <label>姓名：</label>
	        <input type="text" name="name" id="name" value="${employee.name}" maxlength="20" class="required" size="60"/>
	        <span class="eleRequired">*</span> 
	      </li>
	      <li class="fullLine">
	        <label>性别：</label>
	         <c:if test="${employee.sex==true}">
	         	<input class="checkboxInput"  type="radio" name="sex" id="sex_0" value="1" checked="checked"/>男
	        	<input class="checkboxInput"  type="radio"  name="sex" value="0" id="sex_1"/>女
	         </c:if>
	         <c:if test="${employee.sex==false}">
	         	<input class="checkboxInput"  type="radio" name="sex" value="1" id="sex_0"/>男
	        	<input class="checkboxInput"  type="radio"  name="sex" value="0" id="sex_1" checked="checked"/>女
	         </c:if>
	         <c:if test="${employee.sex==null}">
	         	<input class="checkboxInput"  type="radio" name="sex" value="1"  checked="checked" id="sex_0"/>男
	        	<input class="checkboxInput"  type="radio"  name="sex" value="0" id="sex_1"/>女
	         </c:if>
	      </li>
	      <li class="fullLine">
	        <label>身份证号：</label>
	        <input type="text" name="idCard" id="idCard" value="${employee.idCard}" class="cnIdCard" size="60"/>
	      </li>
	       <li class="fullLine">
	        <label>移动电话：</label>
	        <input type="text" name="mobile" value="${employee.mobile}" id="mobile" class="required cnMobile" size="60"/>
	        <span class="eleRequired">*</span> 
	       </li>
	       
	       <li class="fullLine">
	        <label>办公电话：</label>
	        <input type="text" name="telOffice" value="${employee.telOffice}" class="cnPhone" id="telOffice" size="60"/>
	      </li>
	      <li class="fullLine">
	        <label>家庭电话：</label>
	        <input type="text" name="telHome" value="${employee.telHome}" class="cnPhone" id="telHome" size="60"/>
	      </li>
	      <li class="fullLine">
	        <label>传真：</label>
	        <input type="text" name="fax" value="${employee.fax}" class="cnPhone" id="fax" size="60"/>
	      </li>
	      <li class="fullLine">
	        <label>通信地址：</label>
	        <input type="text" name="address" value="${employee.address}" id="address" maxlength="50" size="60"/>
	      </li>
	      <li class="fullLine">
	        <label>邮政编码：</label>
	        <input type="text" name="zipCode" value="${employee.zipCode}" id="zipCode" class="cnZipCode" size="60"/>
	      </li>
	     <li class="fullLine">
	        <label>msn：</label>
	        <input type="text" name="msn" value="${employee.msn}" id="msn" maxlength="20" size="60"/>
	       </li>
	     <li class="fullLine">
	        <label>qq：</label>
	        <input type="text" name="qq" value="${employee.qq}" id="qq" maxlength="20" size="60"/>
	     </li>
	     <li class="fullLine">
	     	<label>email：</label>
		    <input type="text" name="email" id="email" value="${employee.email}" maxlength="50" class="required email" size="60"/>
		    <span class="eleRequired">*</span> 
	     </li>
	   </ul>
	</div>
	
	<div class="conOperation">
		 <button type="button" class="largeBtn" id="saveContactBtn"><span>保存</span></button>
		 <button type="button" class="largeBtn" id="returnBtn" onclick="$('.conOperation').closest('.epsDialog').find('.epsDialogClose').trigger('click');"><span>关闭</span></button>
	</div>
</form:form>

<script>
var ContactForm={};
$(document).ready(function(){
	//唯一性验证
	$.validator.addMethod("emailUnique",function(value,element,param){return uniqueHandler("Employee",param,value,$('#objId').val());},'该邮箱地址已存在');
	$.validator.addMethod("idCardUnique",function(value,element,param){return uniqueHandler("Employee",param,value,$('#objId').val());},'该身份证已注册');
	//用户表单验证
	$("#ContactForm").validate({
		rules:{
			email:{emailUnique:"email"},
			idCard:{idCardUnique:"idCard"}
		}
	});
	
	$("#ContactForm").validate();
	
	//保存
	$('#saveContactBtn').click(function(){
		if(!$('#ContactForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		$.getJSON($('#initPath').val()+'/OrgInfoController.do?method=saveContact', formToJsonObject('ContactForm'), function(json){
			if(json.result) alert(json.result,'提示',{inco:'info'});;
			if(json.failure) return;
			if($("#objId").val() != null && $("#objId").val() != ""){
				alert("修改成功!");
			}else{
				alert("保存成功!");
			}
			$('.conOperation').closest('.epsDialog').find('.epsDialogClose').trigger('click');
			EmployeeList.oTable.fnDraw();
		});
	});
});
</script>

      
      
      
      
      
      
      
      
      
	