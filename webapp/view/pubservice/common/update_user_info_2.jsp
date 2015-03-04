<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/login/sha1.js"></script>
<div id="epsTabs2">
  <ul>
  	<li>
      <a href="#account_2" id = "tabs_account_2"><span>账户信息</span></a>
    </li>
    <li>
      <a href="#password2" id = "tabs_password_2"><span>修改密码</span></a>
    </li>
  </ul>
  <div id="account_2" class="formLayout form2Pa">
	<form id="userForm2" method="post">
		<input type="hidden" id="emp.objId2" name="emp.objId" />
		<div id="companyId2" class="hidden"></div>
	    <ul>
	      <li>
	         <label>帐号：</label>
          	 <div id="usName"></div>
          </li>
          <li>
	        <label>姓名：</label>
	        <input type="text" name="emp.name"  class="required" id="emp.name">
	        <span class="eleRequired">*</span></li><!--
	      <li>
	        <label>所属机构：</label>
	       <div id="emp.company.name"></div>
	      </li>
	      -->
	      <li>
	        <label>性别：</label>
	        <input class="checkboxInput"  type="radio" name="emp.sex" value="1" id="sex_1"/>男
	        <input class="checkboxInput"  type="radio"  name="emp.sex" value="0" id="sex_0"/>女
	      </li>
	      <li>
	        <label>家庭电话：</label>
	        <input type="text" name="emp.telHome" id="emp.telHome" class="cnPhone">
	      </li>
	      <li>
	        <label>身份证：</label>
	        <input type="text" name="emp.idCard"  disabled ="disabled" id="emp.idCard" >	        
	      </li>
	      <li>
	        <label>账户有效期：</label>
	       <div id="usrPeriodOfValidity"></div>
	      </li>
	      <li>
	        <label>电子邮件：</label>
	       <input type="text" name="emp.email"  class="required email">
	       <span class="eleRequired">*</span> 
	      </li>
	      <li>
	        <label>手机号码：</label>
	       <input type="text" name="emp.mobile"  class="required cnMobile">
	       <span class="eleRequired">*</span>
	      </li>
	      <li>
	        <label>办公电话：</label>
	       <input type="text" name="emp.telOffice"  class="cnPhone">
	      </li>
	      <li>
	        <label>传真：</label>
	        <input type="text" name="emp.fax" class="cnPhone">
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
	        <input type="text" name="emp.zipCode" class="cnZipCode"> 
	      </li>
	    </ul>
	    <div class="conOperation">
	      <button type="button" id="submitA2"><span><spring:message code="globe.save"/></span></button>
	    </div>
	</form>
	</div>
  <div id="password2" class="formLayout formPa">
	<form id="pswForm2" method="post">
	    <ul>
	      <li>
	        <label for="input01">旧密码：</label>
	        <input type="password" name="oldPsw" id="oldPsw2" class="required" maxlength="50" minlength="6"/><span class="eleRequired">*</span></li>
	      <li>
	        <label for="input02">新密码：</label>
	        <input type="password" name="newPsw" id="newPsw2" class="required" maxlength="50" minlength="6"/><span class="eleRequired">*</span></li>
	      <li>
	        <label for="input03">新密码确认：</label>
	        <input type="password" name="newPsw2" id="newPsw22" class="required" maxlength="50" minlength="6"/><span class="eleRequired">*</span>
	      </li>
	      
	    </ul>
	    <div class="conOperation">
	      <button type="button" id="submitP2"><span><spring:message code="globe.save"/></span></button>
	    </div>
	</form>
	</div>
</div>
<script>
//加载tabs
$('#epsTabs2').tabs(); 

$('#userForm2').validate();

//获得当前登录用户信息
$.getJSON("UserController.do?method=getCurrentUserInfo",{},function(json){
	jsonObjectToForm($("#userForm2")[0],json.user);
	json2ObjectDiv("userForm2",json.user);
	if(json.user.emp.sex=="true") {
		$('input[id=sex_1]').attr('checked','checked');
	}else {
		$('input[id=sex_0]').attr('checked','checked');
	}
	$("#companyId2").text(json.user.emp.company.objId)
})

 //修改密码
$("#submitP2").click(function(){
	if(!$('#pswForm2').valid()){return;}
	if($("input[name=newPsw]").val()!=$("input[name=newPsw2]").val()){
		alert("两次新密码不一致！");
		return;
	}
	$('#oldPsw2').attr('value',hex_sha1($('#oldPsw2').val()));
	$('#newPsw2').attr('value',hex_sha1($('#newPsw2').val()));
	$.getJSON($('#initPath').val()+'/UserController.do?method=updateMyPsw', formToJsonObject('pswForm2'), function(json){
		if(json.result){alert(json.result,'提示',{inco:'info'})};
		if(json.failure)return;
		$('#oldPsw2').val('');$('#newPsw2').val('');$('#newPsw22').val('');
	});
		
})

//修改账户
$("#submitA2").click(function(){
	if(!$('#userForm2').valid()){return;}
	var user = formToJsonObject('userForm2',"jsonToBean");
	var emp = user.emp;
	emp["company.objId"] = $("#companyId2").text();
	$.getJSON($('#initPath').val()+'/EmployeeController.do?method=save', emp, function(json){
		if(json.failure)return;
		alert("修改成功！");$("#loginUser").text($("#usrName2").val())
	})
})
</script>
