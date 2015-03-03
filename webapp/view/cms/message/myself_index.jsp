<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
.avatar .modify {
	background-color: #FFFFFF;
	bottom: 0;
	display: none;
	left: 0;
	opacity: 0.75;
	position: absolute;
	right: 0;
	text-align: center;
	width: 160px;
	
}
.avatar-hover .modify, .avatar:hover .modify {
	display: block;
}
</style>
<div id="userInfoTabs">
  <ul>
  	<li>
      <a href="#userInfo_avatar" id = "tabs_avatar"><span>用户头像</span></a>
    </li>
  	<li>
      <a href="#userInfo_account" id = "tabs_account"><span>用户信息</span></a>
    </li>
    <li>
      <a href="#userInfo_password" id = "tabs_password"><span>修改密码</span></a>
    </li>
  </ul>
  <div id="userInfo_avatar" class="formLayout form2Pa">
	  <div id="J_Avatar" class="avatar">
	 	 <img width="160" height="160" src="cms/fileico/defautAvator.png">
 		 <a href="javascript:void(0);" class="modify" id="addPic">添加头像</a>
	  </div>
  </div>
  <div id="userInfo_account" class="formLayout form2Pa">
	<form id="userInfo_userForm" method="post">
		<input type="hidden" id="emp.objId" name="emp.objId" />
		<input type="hidden" id="userHeadPic" name="avatar" />
		<div id="companyId" class="hidden"></div>
	    <ul>
	      <li>
	         <label>帐号：</label>
          	 <div id="usName"></div>
          </li>
          <li>
	        <label>姓名：</label>
	        <input type="text" name="emp.name"  class="required" id="usrName">
	        <span class="eleRequired">*</span></li><!--
	      <li>
	        <label>所属机构：</label>
	       <div id="emp.company.name"></div>
	      </li>
	      -->
	      <li>
	        <label>身份证：</label>
	       <div id="emp.idCard"></div>
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
	       <input type="text" name="emp.mobile"  class="required cnMobile"><span class="eleRequired">*</span>
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
	      <button type="button" id="saveAccountBtn"><span><spring:message code="globe.save"/></span></button>
	      <button type="button" name="close"><span><spring:message code="globe.close"/></span></button>
	    </div>
	</form>
	</div>
  <div id="userInfo_password" class="formLayout">
	<form id="userInfo_pswForm" method="post">
	    <ul>
	      <li>
	        <label for="input01">旧密码：</label>
	        <input type="password" name="oldPsw" id="oldPsw" class="required" maxlength="50" minlength="6"/><span class="eleRequired">*</span></li>
	      <li>
	        <label for="input02">新密码：</label>
	        <input type="password" name="newPsw" id="newPsw" class="required" maxlength="50" minlength="6"/><span class="eleRequired">*</span></li>
	      <li>
	        <label for="input03">新密码确认：</label>
	        <input type="password" name="newPsw2" id="newPsw2" class="required" maxlength="50" minlength="6"/><span class="eleRequired">*</span>
	      </li>
	      
	    </ul>
	    <div class="conOperation">
	      <button type="button" id="savePasswordBtn"><span><spring:message code="globe.save"/></span></button>
	      <button type="button" name="close"><span><spring:message code="globe.close"/></span></button>
	    </div>
	</form>
	</div>
</div>
<script>
//加载tabs
$('#userInfoTabs').tabs(); 

$('#userInfo_userForm').validate();

//获得当前登录用户信息
$.getJSON("UserController.do?method=getCurrentUserInfo",{},function(json){
	jsonObjectToForm($("#userInfo_userForm")[0],json.user);
	json2ObjectDiv("userInfo_userForm",json.user);
	if(json.user.avatar){
		$("#J_Avatar img:eq(0)").attr("src",$('#initPath').val()+'/AttachmentController.do?method=showImg&objId='+json.user.avatar);
	}
	$("#companyId").text(json.user.emp.company.objId)
})

 //修改密码
$("#savePasswordBtn").click(function(){
	if(!$('#userInfo_pswForm').valid()){return;}
	if($("input[name=newPsw]").val()!=$("input[name=newPsw2]").val()){
		alert("两次新密码不一致！");
		return;
	}
	$('#oldPsw').attr('value',hex_sha1($('#oldPsw').val()));
	$('#newPsw').attr('value',hex_sha1($('#newPsw').val()));
	$.getJSON($('#initPath').val()+'/UserController.do?method=updateMyPsw', formToJsonObject('userInfo_pswForm'), function(json){
		if(json.result){alert(json.result,'提示',{inco:'info'})};
		if(json.failure)return;
		$('#oldPsw').val('');$('#newPsw').val('');$('#newPsw2').val('');
	});
		
})

//修改账户
$("#saveAccountBtn").click(function(){
	if(!$('#userInfo_userForm').valid()){return;}
	var user = formToJsonObject('userInfo_userForm',"jsonToBean");
	var emp = user.emp;
	emp["company.objId"] = $("#companyId").text();
	$.getJSON($('#initPath').val()+'/EmployeeController.do?method=save', emp, function(json){
		if(json.failure)return;
		alert("修改成功！");$("#loginUser").text($("#usrName").val())
	})
})
//上传头像
	$("#addPic").click(function(){
		$.epsDialog({
	        title:'上传头像',
	        url:$('#initPath').val()+'/UserController.do?method=toUploadAvatar&userHeadPic='+$('#userHeadPic').val(),
	        width: '560',
	        height: '230'
	    }); 
	    return false;
	})
//关闭
$("button[name=close]").click(function(){
	$('.epsDialogClose').trigger('click');
})
</script>
