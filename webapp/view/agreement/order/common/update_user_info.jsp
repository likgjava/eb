<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div id="epsTabs">
  <ul>
  	<li>
      <a href="#account" id = "tabs_account"><span>账户信息</span></a>
    </li>
    <li>
      <a href="#password" id = "tabs_password"><span>修改密码</span></a>
    </li>
  </ul>
  <div id="account" class="formLayout form2Pa">
	<form id="userForm" method="post">
		<input type="hidden" id="emp.objId" name="emp.objId" />
	    <ul>
	      <li>
	         <label>帐号：</label>
          	 <div id="usName"></div>
          </li>
          <li>
	        <label>姓名：</label>
	        <input type="text" name="emp.name"  class="required" id="usrName"><span class="eleRequired">*</span></li>
	      <li>
	        <label>所属机构：</label>
	       <div id="emp.company.name"></div>
	       <div id="companyId" class="hidden"></div>
	      </li>
	      <li>
	        <label>账户有效期：</label>
	       <div id="usrPeriodOfValidity"></div>
	      </li>
	      <li>
	        <label>电子邮件：</label>
	       <input type="text" name="emp.email"  class="required email"><span class="eleRequired">*</span>
	      </li>
	      <li>
	        <label>手机号码：</label>
	       <input type="text" name="emp.mobile"  class="required digits"><span class="eleRequired">*</span>
	      </li>
	      <li>
	        <label>办公电话：</label>
	       <input type="text" name="emp.telOffice"  class="telephone">
           <span class="requisite"> </span>
	      </li>
	      <li>
	        <label>传真：</label>
	        <input type="text" name="emp.fax">
	      </li>
	      <li>
	        <label>MSN：</label>
	       <input type="text" name="emp.msn">
	      </li>
	      <li>
	        <label>QQ：</label>
	        <input type="text" name="emp.qq">
	      </li>
	      <li>
	        <label>联系地址：</label>
	        <input type="text" name="emp.address">
	      </li>
	      <li>
	        <label>邮政编码：</label>
	        <input type="text" name="emp.zipCode">
	      </li>
	    </ul>
	    <div class="conOperation">
	      <button type="button" id="submitA"><span><spring:message code="globe.save"/></span></button>
		  <button type="button" name="close"><span><spring:message code="globe.close"/></span></button>
	    </div>
	</form>
	</div>
  <div id="password" class="formLayout">
	<form id="pswForm" method="post">
	    <ul>
	      <li>
	        <label for="input01">旧密码：</label>
	        <input type="password" name="oldPsw" id="oldPsw" class="required" maxlength="50"/><span class="eleRequired">*</span></li>
	      <li>
	        <label for="input02">新密码：</label>
	        <input type="password" name="newPsw" id="newPsw" class="required" maxlength="50"/><span class="eleRequired">*</span></li>
	      <li>
	        <label for="input03">新密码确认：</label>
	        <input type="password" name="newPsw2" id="newPsw2" class="required" maxlength="50"/><span class="eleRequired">*</span>
	      </li>
	      
	    </ul>
	    <div class="conOperation">
	      <button type="button" id="submitP"><span><spring:message code="globe.save"/></span></button>
				<button type="button" name="close"><span><spring:message code="globe.close"/></span></button>
	    </div>
	</form>
	</div>
</div>
<script>
//加载tabs
$('#epsTabs').tabs(); 

//获得当前登录用户信息
 if(PlatForm.user.objId!=""&&PlatForm.user.objId!="null"){
	$.getJSON("UserController.do?method=createOrUpdate",{objId:PlatForm.user.objId,includedProperties:"emp,emp.company"},function(json){
		jsonObjectToForm($("#userForm")[0],json.user);
		json2ObjectDiv("userForm",json.user);
		$("#companyId").text(json.user.emp.company.objId)
	})
} 

 //修改密码
$("#submitP").click(function(){
	if(!$('#pswForm').valid()){return;}
	if($("input[name=newPsw]").val()!=$("input[name=newPsw2]").val()){
		alert("两次新密码不一致！");
		return;
	}
	$('#oldPsw').attr('value',hex_sha1($('#oldPsw').val()));
	$('#newPsw').attr('value',hex_sha1($('#newPsw').val()));
	$.getJSON($('#initPath').val()+'/UserController.do?method=updateMyPsw', formToJsonObject('pswForm'), function(json){
		if(json.result){alert(json.result,'提示',{inco:'info'})};
		if(json.failure)return;
		$('#oldPsw').val('');$('#newPsw').val('');$('#newPsw2').val('');
	});
		
})

//修改账户
$("#submitA").click(function(){
	if(!$('#userForm').valid()){return;}
	var user = formToJsonObject('userForm',"jsonToBean");
	var emp = user.emp;
	emp["company.objId"] = $("#companyId").text();
	$.getJSON($('#initPath').val()+'/EmployeeController.do?method=save', emp, function(json){
		if(json.failure)return;
		alert("修改成功！");$("#loginUser").text($("#usrName").val())
	})
})

//关闭
$("button[name=close]").click(function(){
	$('.epsDialogClose').trigger('click');
})
</script>
