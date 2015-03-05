<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<form name="BuyerRegisterForm" id="OrgInfoRegisterForm">
  <div id="regiGuideBuyerTwo" class="regGuide expert">
    <h3><span>采购大使注册</span></h3>
    <ol>
      <li><span id="step01">阅读服务条款</span></li>
      <li><span  class="next">下一步</span></li>
      <li><span id="step02">填写用户信息</span></li>
      <li><span  class="next finish">下一步</span></li>
      <li><span id="step03" class="finish">完成注册</span></li>
    </ol>
  </div>
  <div id="buyerRegisterDivOne" class="formLayout form2Pa"> </div>
</form>
<form name="UserRegisterForm" id="UserRegisterForm">
  <div id="buyerRegisterDivTwo" class="form2Pa formLayout">
    <h3 class="title"><span>填写用户信息</span><span class="eleRequired">（带*号的为必填项）</span></h3>
    <ul>
      <li class="fullLine">
        <label for="usName">用户名：</label>
        <input type="text" name="usName" id="usName" minlength="6" maxlength="16" class="required uName" style="width: 146px;"/>
        <span class="eleRequired">*</span>
        <span class="eleRequired">(6-16位字符，只能包括英文、数字和下划线。)</span>
      </li>
      <li class="fullLine">
        <label for="password">密码：</label>
        <input type="password" name="password" id="password" class="required rlogin" minlength="6" maxlength="20" style="width: 146px;" />
        <span class="eleRequired">*</span> </li>
      <li class="fullLine">
        <label for="password_confirm">确认密码：</label>
        <input type="password" name="password_confirm" id="password_confirm" class="required" equalTo="#password" style="width: 146px;" />
        <span class="eleRequired">*</span> </li>
      <li class="fullLine">
        <label for="name">联系人姓名：</label>
        <input type="text" name="name" id="name" class="required" maxlength="20" style="width: 146px;"/>
        <span class="eleRequired">*</span> </li>
      <li class="fullLine">
        <label>性别：</label>
        <input name="sex" id="sex" type="radio" checked="checked" value="true" />
        &nbsp;先生&nbsp;&nbsp;
        <input name="sex" id="sex" type="radio" class="required" value="false"/>
        &nbsp;女士 </li>
      <li class="fullLine">
        <label>申请成为：</label>
        <input name="promoterType" id="promoterType" type="radio"  value="1" />
        &nbsp;友善大使&nbsp;&nbsp;
        <input name="promoterType" id="promoterType" type="radio" checked="checked" value="2"/>
        &nbsp;诚信大使 &nbsp;&nbsp;
        <input name="promoterType" id="promoterType" type="radio" value="3" />
        &nbsp;爱心大使 </li>
      <li class="fullLine">
        <label for="idCard">身份证号：</label>
        <input type="text" name="idCard" id="idCard" class="required cnIdCard" maxlength="18" size="35"/>
        <span class="eleRequired">*</span> </li>
      <li class="fullLine">
        <label for="email">电子邮件：</label>
        <input type="text" name="email" id="email" maxlength="50" class="required email" size="35"/>
        <span class="eleRequired">*</span> </li>
      <li class="fullLine">
        <label for="mobile">移动电话:</label>
        <input type="text" name="mobile" id="mobile" maxlength="11" class="required cnMobile" size="35"/>
        <span class="eleRequired">*</span> </li>
      <li class="fullLine">
        <label for="telOffice">固定电话：</label>
        <input type="text" name="telOffice" id="telOffice" maxlength="20" class="cnPhone" size="35"/>
      </li>
      <li class="fullLine">
        <label for="fax">传真：</label>
        <input type="text" name="fax" id="fax" class="cnPhone" maxlength="20" size="35"/>
      </li>
      <li class="fullLine">
        <label for="address">通信地址：</label>
        <input type="text" name="address" id="address" maxlength="100" size="35"/>
      </li>
      <li class="fullLine">
        <label for="zipCode">邮政编码：</label>
        <input type="text" name="zipCode" id="zipCode" class="cnZipCode" maxlength="6" size="35"/>
      </li>
    </ul>
    <div class="contentText">
      <h2>诚信大使</h2>
      <p>诚信大使是阳光易购专为职场人士所设。阳光易购平台对职场人士兼职推广的称号是诚信大使。他们是谛造公平、诚信社会的希望。<a class="toPromoter" href="#">详细</a></p>
      <h2>友善大使</h2>
      <p>友善大使是阳光易购平台专为残障人士所设。阳光易购平台对残障人士兼职推广的称号是友善大使，他们是打造和谐社会的支持力量。<a class="toPromoter" href="#">详细</a></p>
      <h2>爱心大使</h2>
      <p>爱心大使是阳光易购平台专为退休人士所设。阳光易购平台对退休人士兼职推广的称号是爱心大使。他们是公平、诚信交易的领导力量。<a class="toPromoter" href="#">详细</a> </p>
    </div>
    <!--.contentText-->
    <div class="conOperation">
      <button id="stepOneBack" type="button" class="back"><span>上一步</span></button>
      <button type="button" id="submitBtn" class="next"><span>提交</span></button>
    </div>
  </div>
</form>
<script>
var BuyerRegisterForm = {};

$(document).ready(function(){	
	//唯一性验证
	$.validator.addMethod("userNameUnique",function(value,element,param){return uniqueHandler("User",param,value,"");},'该用户已存在');
	$.validator.addMethod("emailUnique",function(value,element,param){return uniqueHandler("Employee",param,value,"");},'该邮箱地址已存在');
	$.validator.addMethod("idCardUnique",function(value,element,param){return uniqueHandler("Employee",param,value,"");},'该身份证已注册');
		
	//用户表单验证
	$("#UserRegisterForm").validate({
		rules:{
		    //orgName:{hasOrg:"name"},
			usName:{userNameUnique:"usName"},
			email:{emailUnique:"email"},
			idCard:{idCardUnique:"idCard"}
		}
	});	

	//回到条款内容
	$("#stepOneBack").click(function(){
		$('#regContent').loadPage($('#initPath').val()+'/PromoterRegisterController.do?method=toRegisterPage&step=1');
    });
	
	/*提交注册信息*/
	$("#submitBtn").click(function(){
		if(!$('#UserRegisterForm').valid()){alert('请正确填写表单!');return;}
		$("#submitBtn").attr("disabled","disabled");		
		//var orgForm = formToJsonObject('OrgInfoRegisterForm');
		var userForm = formToJsonObject('UserRegisterForm');	
		if(window.confirm('是否确定注册为采购大使？')){			
			$.getJSON($('#initPath').val()+"/PromoterRegisterController.do?method=savePromoterOfRegister", userForm, function(json){
				if(json.failure){
					alert("注册失败，请与管理员联系！");
					$("#submitBtn").attr("disabled",false);
					return;
				}
				else{
					$('#regContent').loadPage($('#initPath').val()+'/PromoterRegisterController.do?method=toRegisterPage&step=success');

					//获取当前用户
					$.getJSON($('#initPath').val()+"/IndexViewController.do?method=getCurrentUser", {} , function(userjson){
						if(userjson.failure){if(userjson.result)alert(userjson.result);return;}
	
						//修改登录头部信息
						common.getCurrUser(userjson);
					});				
				}
			});
        }else return;
    });

	//跳转到我要推广
	$('.toPromoter').click(function(){
		$('#toPromoterA').click();
	})  
	
});
</script>