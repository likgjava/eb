<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form name="expertRegisterForm" id="expertInfoRegisterForm">
	<div id="regiGuideBuyerTwo" class="regGuide expert">
		<h3><span>咨询专家注册</span></h3>
		<ol>
			<li><span id="step01">阅读服务条款</span></li>
			<li><span  class="next">下一步</span></li>
			<li><span id="step02">填写用户信息</span></li>
			<li><span  class="next finish">下一步</span></li>
			<li><span id="step03" class="finish">完成注册</span></li>
		</ol>
	</div>
</form>

<form name="UserRegisterForm" id="UserRegisterForm">
  <div id="expertRegisterDivTwo" class="form2Pa formLayout regFliiInfo">    
    <h3 class="title"><span>填写用户信息</span><span class="eleRequired">（带*号的为必填项）</span></h3>
    <ul>
    	<li class="fullLine">
            <label>用户名：</label>
            <input type="text" name="usName" id="usName" minlength="6" maxlength="16" class="required uName" size="45"/>
            <span class="eleRequired">*</span>
            <span class="eleRequired">(6-16位字符，只能包括英文、数字和下划线。)</span>
        </li>       
    	<li class="fullLine">
            <label>密码：</label>
            <input type="password" name="password" id="password" class="rlogin required" minlength="6" maxlength="20" size="45" />
            <span class="eleRequired">*</span> 
        </li>
    	<li class="fullLine">
            <label>确认密码：</label>
            <input type="password" name="password_confirm" id="password_confirm" class="required" equalTo="#password" size="45"/>
            <span class="eleRequired">*</span> 
        </li>
    	<li class="fullLine">
            <label>真实姓名：</label>
            <input type="text" name="name" id="name" class="required" maxlength="20" size="45"/>
            <span class="eleRequired">*</span> 
        </li>
    	<li class="fullLine">
            <label>性别：</label>
            <input name="sex" id="sex1" type="radio" checked="checked" value="true" />&nbsp;先生&nbsp;&nbsp;
		    <input name="sex" id="sex2" type="radio" class="required" value="false"/>&nbsp;女士
        </li>
        <li class="fullLine">
            <label>身份证号：</label>
            <input type="text" name="idCard" id="idCard" class="required cnIdCard" maxlength="18" size="45"/>
            <span class="eleRequired">*</span> 
        </li>
    	<li class="fullLine">
            <label>电子邮件：</label>
            <input type="text" name="email" id="email" maxlength="50" class="required email" size="45"/>
	        <span class="eleRequired">*</span> 
        </li>
        <li class="fullLine">
            <label>移动电话:</label>
            <input type="text" name="mobile" id="mobile" maxlength="11" class="required cnMobile" size="45"/>
            <span class="eleRequired">*</span> 
        </li>
    	<li class="fullLine">
            <label>固定电话：</label>
            <input type="text" name="telOffice" id="telOffice" maxlength="20" class="cnPhone" size="45"/>
        </li>
        <li class="fullLine">
            <label>传真：</label>
            <input type="text" name="fax" id="fax" class="cnPhone" maxlength="20" size="45"/>
        </li>
    	<li class="fullLine">
            <label>通信地址：</label>
            <input type="text" name="address" id="address" maxlength="100" size="45"/>
        </li>
        <li class="fullLine">
            <label>邮政编码：</label>
            <input type="text" name="zipCode" id="zipCode" class="cnZipCode" maxlength="6" size="45"/>
        </li>
    </ul>
    
    <div class="conOperation"> 
		<button id="stepOneBack" type="button" class="back"><span>上一步</span></button>
		<button type="button" id="submitBtn" class="next"><span>提交</span></button>
	</div>
  </div>
</form>

<script>
/**
 * 专家注册页面【填写用户信息】
 * create by likg
 */
var ExpertRegisterForm = {};

$(document).ready(function(){	
	//唯一性验证
	$.validator.addMethod("userNameUnique",function(value,element,param){return uniqueHandler("User",param,value,"");},'该用户已存在');
	$.validator.addMethod("emailUnique",function(value,element,param){return uniqueHandler("Employee",param,value,"");},'该邮箱地址已存在');
	$.validator.addMethod("idCardUnique",function(value,element,param){return uniqueHandler("Employee",param,value,"");},'该身份证已注册');
	//用户表单验证
	$("#UserRegisterForm").validate({
		rules:{
			usName:{userNameUnique:"usName"},
			email:{emailUnique:"email"},
			idCard:{idCardUnique:"idCard"}
		}
	});	
	
	//回到条款内容
	$("#stepOneBack").click(function(){
		$('#regContent').loadPage($('#initPath').val()+'/ExpertInfoController.do?method=toRegisterPage&step=1');
    });
	
	//提交注册信息
	$("#submitBtn").click(function(){
		if(!$('#UserRegisterForm').valid()){alert('请正确填写表单!');return;}
		var userForm = formToJsonObject('UserRegisterForm');	
		if(window.confirm('是否确定注册为咨询专家？')){			
			$("#submitBtn").attr("disabled","disabled");		
			$.getJSON($('#initPath').val()+"/ExpertInfoController.do?method=saveExpertInfoOfRegister", userForm, function(json){
				if(json.failure){
					alert("注册失败，请与管理员联系！");
					$("#submitBtn").attr("disabled",false);
					return;
				}
				else{
			  		$('#regContent').loadPage($('#initPath').val()+'/ExpertInfoController.do?method=toRegisterPage&step=success');	

			  		//跳转到注册成功页面
			  		$('#regContent').loadPage($('#initPath').val()+'/BuyerController.do?method=toRegisterGatePass&step=success');	

					//获取当前用户
					$.getJSON($('#initPath').val()+"/IndexViewController.do?method=getCurrentUser", {} , function(userjson){
						if(userjson.failure){if(userjson.result)alert(userjson.result);return;}
	
						//修改登录头部信息
						common.getCurrUser(userjson);
					});				
				}
			});
        }
    });
	
});
</script>
