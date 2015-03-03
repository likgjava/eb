<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form name="BuyerRegisterForm" id="BuyerRegisterForm">
	<input type="hidden" id="districtValue" name="districtValue" value=""/>
	
 	<div id="regiGuideBuyerOne" class="regGuide expert">
    <h3><span>采购人注册</span></h3>
    <ol>
      <li><span id="step01">阅读注册条款</span></li>
      <li><span  class="next">&nbsp;&nbsp;>&nbsp;&nbsp;</span></li>
      <li><span id="step02">填写用户信息</span></li>
      <li><span  class="next finish">&nbsp;&nbsp;>&nbsp;&nbsp;</span></li>
      <li><span id="step03" class="finish">完成注册</span></li>
    </ol>
  </div>
  
  <div id="buyerRegisterDivOne" class="formLayout form2Pa regFliiInfo">
    <h3 class="title"><span>申请用户名</span><span class="eleRequired">（带*号的为必填项）</span></h3>
    <ul>
    	<div class="formTips warm hidden" id="tips"></div>    	
    	
        <li class="fullLine">
            <label for="orgName">机构名称：</label>
            <input type="text" id="orgName" name="orgName" maxlength="50" class="required" size="45"/>
            <span class="eleRequired">*</span> 
        </li>
       	<li class="fullLine">
            <label for="orgCode">组织机构代码：</label>
            <input type="text" name="orgCode" id="orgCode" maxlength="50" class="required orgCode" size="45"/>
            <span class="eleRequired">*</span> 
        </li>
        <li class="fullLine">
            <label for="usName">用户名：</label>
            <input type="text" name="usName" id="usName" minlength="6" maxlength="16" class="required uName" size="45"/>
            <span class="eleRequired">*</span>
            <span class="eleRequired">(6-16位字符，只能包括英文、数字和下划线。)</span>
        </li>
    	<li class="fullLine">
            <label for="password">密码：</label>
            <input type="password" name="password" id="password" class="rlogin required" minlength="6" maxlength="20" size="45" />
            <span class="eleRequired">*</span> 
        </li>
    	<li class="fullLine">
            <label for="password_confirm">确认密码：</label>
            <input type="password" name="password_confirm" id="password_confirm" class="required" equalTo="#password" size="45"/>
            <span class="eleRequired">*</span> 
        </li>
        <li class="fullLine">
            <label for="name">联系人姓名：</label>
            <input type="text" name="name" id="name" class="required" maxlength="20" size="45"/>
            <span class="eleRequired">*</span> 
        </li>
    	<li class="fullLine">
            <label>性别：</label>
            <input name="sex" type="radio" checked="checked" value="true" />&nbsp;先生&nbsp;&nbsp;
		    <input name="sex" type="radio" value="false"/>&nbsp;女士
        </li>
        <li class="fullLine">
            <label for="email">电子邮箱：</label>
            <input type="text" name="email" id="email" maxlength="50" class="required email" size="45"/>
	        <span class="eleRequired">*</span> 
        </li>
        <li class="fullLine">
            <label for="mobile">移动电话:</label>
            <input type="text" name="mobile" id="mobile" maxlength="11" class="cnMobile required" size="45"/>
            <span class="eleRequired">*</span> 
        </li>
    </ul>
    
    <div class="conOperation">
      <button id="stepOneBack" type="button" class="back"><span>上一步</span></button>
      <button type="button" id="submitBtn" class="next"><span>提交</span></button>
       <input type="hidden" name="promoterUID" id="promoterUID" value="${param.promoterUID}"/>
    </div>
    
  </div>
 </form>

<script>
var BuyerRegisterForm = {};

$(document).ready(function(){

	var jsonNameObj= {};
	jsonNameObj["id"]="";//新注册ID传空
	jsonNameObj["property"]="orgName";
	jsonNameObj["type"]="buyer";//只验证供应商机构不重复
	$.validator.addMethod("orgNameUnique",function(value,element,param){return bizUniqueHandler("OrgInfoController.do?method=checkOrgUnique&orgName="+native2ascii($("#orgName").val()),jsonNameObj);},'该机构名称已存在');
	
	//唯一性验证
	$.validator.addMethod("userNameUnique",function(value,element,param){return uniqueHandler("User",param,value,"");},'该用户已存在');
	$.validator.addMethod("emailUnique",function(value,element,param){return uniqueHandler("Employee",param,value,"");},'该邮箱地址已存在');

	//组织机构代码验证
	$.validator.addMethod("orgCodeUnique",function(value,element,param){return uniqueHandler("OrgInfo",param,value,"");},'该组织机构代码已存在');
	
	//机构表单验证
	$("#BuyerRegisterForm").validate({
		rules:{
			orgName:{orgNameUnique:"orgName"},
			usName:{userNameUnique:"usName"},
			email:{emailUnique:"email"},
			orgCode:{orgCodeUnique:"orgCode"}
		}
	});
	
	//上一步
	$("#stepOneBack").click(function(){
		$('#regContent').loadPage($('#initPath').val()+'/BuyerController.do?method=toRegisterGatePass&step=1');
    });
	
	/*提交注册信息*/
	$("#submitBtn").click(function(){
		if(!$('#BuyerRegisterForm').valid()){alert('请正确填写表单!');return;}
		
		$("#submitBtn").attr("disabled","disabled");
		
		var orgForm = formToJsonObject('BuyerRegisterForm');
		
		if(window.confirm('是否确定注册为采购人？')){
			$.getJSON($('#initPath').val()+"/BuyerController.do?method=saveBuyerOfRegister", formToJsonObject('BuyerRegisterForm'), function(json){
				if(json.failure){
					alert("注册失败，请与管理员联系！");
					$("#submitBtn").attr("disabled",false);
					return;
				}
				else{
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
        }else {
    			$("#submitBtn").attr("disabled",false);
    			return;
         	  }
    });

	//机构名称验证
	$('#orgName').change(function(){
		if(""==$(this).val()) return;//为空不检测
		$.getJSON($("#initPath").val()+"/OrgInfoController.do?method=checkOrgName",{"orgName":$(this).val()},function(json){
			if(json.success){
				$("#tips").html('<em>注意：</em>'+json.result);
				if(json.sameNameList!=null){
					var sameBrandHtml = '&nbsp;&nbsp;以下机构名称已经存在：';
					$.each(json.sameNameList,function(index,obj){
						if(index==0){
							sameBrandHtml += obj;
						}else{
							sameBrandHtml += '、'+obj;
						}
					})
					if(sameBrandHtml.length>100) {
						sameBrandHtml = sameBrandHtml.substring(0,99) + "...";
					}
					$("#tips").append(sameBrandHtml);
				}
			}else{
				$("#tips").html(json.result+'<input type="hidden" id="samename" value="true"/>');
			}
			$("#tips").show();
		});
	});
});
</script>
