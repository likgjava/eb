<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form name="SupplierRegisterForm" id="OrgInfoRegisterForm">
	<input type="hidden" id="districtValue" name="districtValue" value=""/>
	<!-- 是否厂商字段默认为0(经销商) -->
	<input type="hidden" id="isManufacturer" name="isManufacturer" value="0"/>
	
	 <div id="regiGuideSupplierOne" class="regGuide supplier">
	    <h3><span>供应商注册</span></h3>
	    <ol>
	      <li><span id="step01">阅读服务条款</span></li>
	      <li><span  class="next">&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
	      <li><span id="step02">填写机构和角色信息</span></li>
	      <li><span  class="next finish">&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
	      <li><span id="step03" class="finish">填写基本信息</span></li>
	      <li><span  class="next finish">&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
	      <li><span id="step04" class="finish">完成注册</span></li>
	    </ol>
	  </div>
	  <div id="regiGuideSupplierTwo" class="regGuide supplier">
	    <h3><span>供应商注册</span></h3>
	    <ol>
	      <li><span id="step01">阅读服务条款</span></li>
	      <li><span  class="next">下一步</span></li>
	      <li><span id="step02">选择机构角色</span></li>
	      <li><span  class="next">下一步</span></li>
	      <li><span id="step03">填写用户信息</span></li>
	      <li><span  class="next finish">下一步</span></li>
	      <li><span id="step04" class="finish">完成注册</span></li>
	    </ol>
	  </div>
	  <div id="supplierRegisterDivOne" class="formLayout form2Pa">
	    <h3 class="title"><span>填写机构信息</span><span class="eleRequired">（带*号的为必填项）</span></h3>
	    <ul>
			<div class="formTips warm hidden" id="tips"></div>

	    	<li class="fullLine">
	            <label for="orgName">机构名称：</label>
	            <input type="text" id="orgName" name="orgName" maxlength="50" class="required" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label for="orgCode">机构代码：</label>
	            <input type="text" id="orgCode" name="orgCode" maxlength="50" class="required" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	           <label for="tel">联系手机：</label>
	           <input type="text" id="mobilePhone" name="mobilePhone" maxlength="11" class="required cnMobile" size="60"/>
	           <span class="eleRequired">*</span> 
	        </li>
	    	<li class="fullLine">
	           <label for="tel">联系电话：</label>
	           <input type="text" id="tel" name="tel" maxlength="20" class="cnPhone" size="60"/>
	        </li>
	        <li class="fullLine">
	            <label for="town">行政区域名称：</label>
	         	<select name="province.objId" id="province"></select>
	            <select name="city.objId"  id="city" ><option value="">请选择</option></select>
	            <select name="town.objId"  id="town" class="required"><option value="">请选择</option></select>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	           <label for="orgAddress">详细通信地址：</label>
	           <input type="text" id="orgAddress" name="orgAddress" maxlength="100" size="60"/>
	        </li>
	        <li class="fullLine">
	           <label for="postCode">邮编：</label>
	           <input type="text" id="postCode" name="postCode" maxlength="6" class="cnZipCode" size="60"/>
	        </li>
	    	
	    </ul>
		<h3 class="title"><span>填写详细信息</span><span class="eleRequired">（带*号的为必填项）</span></h3>
		<ul>
          <li class="fullLine">
          	<label for="belongIndustry">所属行业：</label>
          	<input type="text" id="belongIndustry_display" readonly="readonly" value="" size="60"/>
          	<input type="hidden" name="belongIndustry.objId" id="belongIndustry" value=""/>
    	  </li>
    	  
    	  <li>
             <label for="purSbjct">企业类型：</label>
             <html:select selectedValue="0" id="entPrpt" name="entPrpt" code="biz.orgInfo.entprpt"></html:select>
          </li>
          <li>
             <label for="purSbjct">企业规模：</label>
             <html:select selectedValue="0" id="unitScape" name="unitScape" code="biz.orgInfo.unitScape"></html:select>
          </li>
          <li class="fullLine">
             <label for="purSbjct">开业日期：</label>
             <input type="text" name="begainDate" id="begainDate" value="" class="required" size="40" readonly="readonly"/>
             <span class="eleRequired">*</span> 
          </li>
          <li class="fullLine">
             <label for="purSbjct">企业网址：</label>
             <input type="text" name="webUrl" id="webUrl" class="url" value="" size="60"/> （请以http:// 开头）
          </li>
          <li class="formTextarea">
             <label for="purSbjct">经营范围：</label>
             <textarea name="bidForRange_1" id="bidForRange_1" maxlength="500" readonly="readonly" class="required"></textarea>
             <input type="hidden" id="bidForRange_2" />
             <input type="hidden" name="bidForRange" id="bidForRange" value=""/>
             <span class="eleRequired">*</span> 
          </li>
          <li class="formTextarea">
             <label for="purSbjct">主营产品：</label>
             <textarea name="mainProducts" id="mainProducts" maxlength="500"></textarea>
          </li>
          <li class="formTextarea">
             <label for="purSbjct">企业产能：</label>
             <textarea name="entCapacity" id="entCapacity" maxlength="500"></textarea>
          </li>
    	  <li class="formTextarea">
             <label for="input02">企业简介：</label>
         	 <textarea name="descCn" id="descCn" maxlength="500"></textarea>
		  </li>
	   </ul>
	    <div class="conOperation">
	      <button id="stepOneBack" type="button" class="back"><span>上一步</span></button>
	      <button type="button" id="supplierRegisterNext"  class="next"><span>下一步</span></button>
	    </div>
	  </div>
	  </form>
	  <form name="SupplierRegisterForm" id="UserRegisterForm">
	    <div id="supplierRegisterDivTwo" class="form2Pa formLayout">
	    <h3 class="title"><span>填写用户信息</span><span class="eleRequired">（带*号的为必填项）</span></h3>
	    <ul>
	    	<li class="fullLine">
	            <label for="usName">用户名：</label>
	            <input type="text" name="usName" id="usName" minlength="6" maxlength="16" class="required uName" size="60"/>
	            <span class="eleRequired">*(只能包括英文、数字和下划线)(16位以内不少于6位)</span> 
	        </li>
	    	<li class="fullLine">
	            <label for="password">密码：</label>
	            <input type="password" name="password" id="password" class="rlogin" minlength="6" maxlength="20"  size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	    	<li class="fullLine">
	            <label for="password_confirm">重复输入密码：</label>
	            <input type="password" name="password_confirm" id="password_confirm" class="required" equalTo="#password" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	    	<li class="fullLine">
	            <label for="name">联系人姓名：</label>
	            <input type="text" name="name" id="name" maxlength="20" class="required" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	    	<li class="fullLine">
	            <label for="sex">性别：</label>
	            <input name="sex" id="sex" type="radio" checked="checked" value="true" />&nbsp;先生&nbsp;&nbsp;
		        <input name="sex" id="sex" type="radio" class="required" value="false"/>&nbsp;女士
	        </li>
	        <li class="fullLine">
	            <label for="idCard">身份证号：</label>
	            <input type="text" name="idCard" id="idCard" class="cnIdCard" maxlength="18" size="60"/>
	        </li>
	    	<li class="fullLine">
	            <label for="email">电子邮件：</label>
	           	<input type="text" name="email" id="email" maxlength="50" class="required email" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label for="mobile">移动电话：</label>
	            <input type="text" name="mobile" id="mobile" maxlength="11" class="cnMobile" size="60"/>
	        </li>
	    	<li class="fullLine">
	            <label for="telOffice">固定电话：</label>
	            <input type="text" name="telOffice" id="telOffice" maxlength="12" class="cnPhone" size="60"/>
	        </li>
	        <li class="fullLine">
	            <label for="fax">传真：</label>
	            <input type="text" name="fax" id="fax" class="cnPhone" maxlength="12" size="60"/>
	        </li>
	    	<li class="fullLine">
	            <label for="address">通信地址：</label>
	            <input type="text" name="address" id="address" maxlength="100" size="60"/>
	        </li>
	        <li class="fullLine">
	            <label for="zipCode">邮政编码：</label>
	            <input type="text" name="zipCode" id="zipCode" class="cnZipCode" maxlength="6" size="60"/>
	        </li>
	    </ul>
	    <div class="conOperation">
	      <button id="stepTwoBack" type="button" class="back"><span>上一步</span></button>
	      <button type="button" id="submitBtn" class="next"><span>提交</span></button>
	      
	    </div>
	 </div>
</form>

<script>
var SupplierRegisterForm = {};

$(document).ready(function(){
	$("#begainDate").epsDatepicker();
	
	//隐藏下一步输入页面
	$("#supplierRegisterDivTwo").hide();
	$("#regiGuideSupplierTwo").hide();
	
	//唯一性验证
	$.validator.addMethod("userNameUnique",function(value,element,param){return uniqueHandler("User",param,value,"");},'该用户已存在');
	$.validator.addMethod("orgCodeUnique",function(value,element,param){return uniqueHandler("OrgInfo",param,value,"");},'该机构代码已存在');
	$.validator.addMethod("orgNameUnique",function(value,element,param){return uniqueHandler("OrgInfo",param,value,"");},'该机构名称已存在');
	$.validator.addMethod("emailUnique",function(value,element,param){return uniqueHandler("Employee",param,value,"");},'该邮箱地址已存在');
	
	//机构表单验证
	$("#OrgInfoRegisterForm").validate({
		rules:{
			orgCode:{orgCodeUnique:"orgCode"},
			orgName:{orgNameUnique:"orgName"}
		}
	});	
	//用户表单验证
	$("#UserRegisterForm").validate({
		rules:{
			usName:{userNameUnique:"usName"},
			email:{emailUnique:"email"}
		}
	});	
	
	//行政区域联动
	var option = {parameter:"objId",isfirstselect:"yes"};  //默认值:{datatype:"json",textfield:"name",valuefiled:"objId"};
	var provinceDatas=$('#province').FillOptions($('#initPath').val()+'/DistrictController.do?method=findTopDistrict',option);
    $('#province').CascadingSelect($('#city'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});
    $('#city').CascadingSelect($('#town'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});
    
    
    //显示页面二的填写
	$("#supplierRegisterNext").click(function(){
		if(!$('#OrgInfoRegisterForm').valid()){alert('请正确填写表单!');return;}
		if($("#town").val()=="请选择"){
			alert("请选择行政区域!");
			return;
		}
		$("#supplierRegisterDivOne").hide();
		$("#regiGuideSupplierOne").hide();
		$("#supplierRegisterDivTwo").show();
		$("#regiGuideSupplierTwo").show();
	});

	//选择所属行业
 	$("#belongIndustry_display").click(function(e){
	    $.epsDialog({
	        title:'选择所属行业',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=belongIndustry&NAMES=belongIndustry_display&className=Industry&action=listTop'
	    }); 
 	});
	
	//选择品目（投标范围及类别）
 	$("#bidForRange_1").click(function(e){
	    $.epsDialog({
	        title:'选择品目',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=bidForRange_2&NAMES=bidForRange_1&className=PurCategory&action=listTop&isCheckBox=true&checkStatus=1',
	        onClose: function(){ 
        		$("#bidForRange").val($("#bidForRange_2").val()+"##||##"+$("#bidForRange_1").val());
        	}
	    }); 
 	});
 	
	//显示第一步内容填写页面
	$("#stepTwoBack").click(function(){
		$("#supplierRegisterDivOne").show();
		$("#regiGuideSupplierOne").show();
		$("#supplierRegisterDivTwo").hide();
		$("#regiGuideSupplierTwo").hide();
	});
	
	//返回条款页面
	$("#stepOneBack").click(function(){
		$('#regContent').loadPage($('#initPath').val()+'/SupplierController.do?method=toRegisterPage&step=1');
    });
	
	//提交注册信息
	$("#submitBtn").click(function(){
		if(!$('#UserRegisterForm').valid()){alert('请正确填写表单!');return;}
		$("#submitBtn").attr("disabled","disabled");
		$("#districtValue").val($("#province").val() + "," + $("#city").val() + "," + $("#town").val() + "##||##" + $('#province').getSelectedText() + "," + $('#city').getSelectedText() + "," + $('#town').getSelectedText());
		var orgForm = formToJsonObject('OrgInfoRegisterForm');
		var userForm = formToJsonObject('UserRegisterForm');
		if(window.confirm('是否确定注册供应商？')){
			$.getJSON($('#initPath').val()+"/SupplierController.do?method=saveSupplierOfRegister", $.extend(orgForm,userForm), function(json){
				if(json.failure){if(json.result)alert(json.result);$("#submitBtn").attr("disabled",false);return;}
				else{
					if(json.result=='faile'){
						alert('注册失败，请与管理员联系');
						$("#submitBtn").attr("disabled",false);
					}
			  		if(json.result=='success'){
			  			alert('恭喜您,注册成功');
			  			$('#regContent').loadPage($('#initPath').val()+'/SupplierController.do?method=toRegisterPage&step=success');
			  		}
				}				
			});
	    }else {
    			$("#submitBtn").attr("disabled",false);
    			return;
          	  }
     });

	//机构名称提示
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
