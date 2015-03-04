<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.userSecurity input,.userSecurity select{background:url(view/resource/skin/skin07/img/button_a.gif) repeat-x left top; height:24px;border:1px solid #ccc;}
.conOperation button,.conOperation .linkButton{background:url(view/resource/skin/skin07/img/tableTh_bg.gif) repeat-x left top; border:#0082a9 1px solid; width:60px; height:27px;margin:10px 10px 20px 5px; margin-right:10px; margin-top:10px;}
.conOperation button:hover{background:url(view/resource/skin/skin07/img/button_a.gif) repeat-x left top;}
.conOperation button span,.conOperation .linkButton span{ background:none;}
</style>
<script>
$(document).ready(function(){
	$('#userSecurityForm').validate();
    $("#createTime").epsDatepicker();
    
	//提交密保信息
	$('#userSecuritySave').click(function(){
		if(!$('#userSecurityForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/UserSecurityController.do?method=addSecurity', formToJsonObject('userSecurityForm'), function(json){
			if(json.failure){
				alert("提交失败");return;
			}else{
				alert("您的密保资料已提交成功！");
				$("#security").remove();
				$("#userSecurityClose").click();
			}
		});
	});

	//关闭
	$("#userSecurityClose").click(function(){
		$('.epsDialogClose').trigger('click');
	})

});
</script>
<div class="formTips attention">
     <ul>
         <li><label><spring:message code="pubservice.userSecurity.question"/></label></li>
     </ul>
</div>
<div class="formLayout formPa userSecurity">
	<form id="userSecurityForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
    	 <ul>
	     	<li>
	     		<label for="userSecurityForm.question"><spring:message code="userSecurityForm.question"/>一：</label>
					 <select name="dictionary1" id="dictionary1">
                        <c:forEach items="${dictionaryList}" var="dictionary">
                        	<option value="${dictionary.objId }">${dictionary.dicName }</option>
                        </c:forEach>
                    </select>
    	    </li>
    	  </ul>
    	  <ul>
	     	<li>
	     		<label for="userSecurityForm.answer"><spring:message code="userSecurityForm.answer"/>一：</label>
					<input type="text" name="answer1" id="answer1" class="required"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
		</ul>
		
		 <ul>
	     	<li>
	     		<label for="userSecurityForm.question"><spring:message code="userSecurityForm.question"/>二：</label>
					<select name="dictionary2" id="dictionary2">
                        <c:forEach items="${dictionaryList}" var="dictionary">
                        	<option value="${dictionary.objId }">${dictionary.dicName }</option>
                        </c:forEach>
                    </select>
    	    </li>
    	  </ul>
    	  <ul>
	     	<li>
	     		<label for="userSecurityForm.answer"><spring:message code="userSecurityForm.answer"/>二：</label>
					<input type="text" name="answer2" id="answer2" class="required"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
		</ul>
		
		 <ul>
	     	<li>
	     		<label for="userSecurityForm.question"><spring:message code="userSecurityForm.question"/>三： </label>
					<select name="dictionary3" id="dictionary3">
                        <c:forEach items="${dictionaryList}" var="dictionary">
                        	<option value="${dictionary.objId }">${dictionary.dicName }</option>
                        </c:forEach>
                    </select>
    	    </li>
    	  </ul>
    	  <ul>
	     	<li>
	     		<label for="userSecurityForm.answer"><spring:message code="userSecurityForm.answer"/>三：</label>
					<input type="text" name="answer3" id="answer3" class="required"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="userSecuritySave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="userSecurityClose" tabindex="20""><span><spring:message code="globe.close"/></span></button>
		   </div>
	</form>
</div>