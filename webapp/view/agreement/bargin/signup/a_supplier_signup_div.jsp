<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<input type="hidden" name="cardIdFlag" >

<input type="hidden" name="companyId" value="${signUprecord.supplier.company.objId}">

<form id="signUprecordForm">

<input type="hidden" name="signUprecord.project.objId" value="${signUprecord.project.objId }">

<!-- 以下用来判断是否应该新增一条联系人信息 -->
<input type="hidden" name="oldLinker" id="oldLinker" value="${signUprecord.linker}" />
<input type="hidden" name="oldIdCard" id="oldIdCard" value="${signUprecord.idCard}" />
<input type="hidden" name="currentEmpId" id="currentEmpId" value="${currentEmpId}" />

<div class="formTips warm hidden" id="tips"></div>
<div class="formLayout form2Pa">
		<ul>
		<li class="fullLine">
			<h1>
		      <label>当前招标项目：</label>
		      <span>${signUprecord.project.projName }</span>
		  	</h1>
		</li>
		<li class="fullLine">
			<label>报名供应商：</label>
			<input type="hidden" name="signUprecord.supplier.objId" value="${signUprecord.supplier.objId }" size="30">
			<span>${signUprecord.supplierName }</span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>><a href="javascript:void(0);" onclick="suppliersignUpdiv.chooseLinker();return false;" name="chooseLinker">点击选择联系人</a>
		</li>
		<li>
			<label>联系人：</label>
			<input name="linker" id="linker" value="${signUprecord.linker}" class="required" size="20">
			<span class='eleRequired'>*</span>
		</li>
		<li>
			<label>移动电话：</label>
			<input name="linkerTel" id="linkerTel" value="${signUprecord.linkerTel }" maxlength="11" class="required cnMobile" size="11">
			<span class='eleRequired'>*</span>
		</li>
		<li>
			<label>身份证号：</label>
			<input name="idCard" id="idCard" value="${signUprecord.idCard }" maxlength="18" onchange="suppliersignUpdiv.cardIdChange()" size="20" class="required cnIdCard">
			<span class='eleRequired'>*</span>
		</li>
		<li>
			<label>邮编：</label>
			<input name="zipCode" id="zipCode" value="${signUprecord.zipCode }" class="cnZipCode" maxlength="6" size="11">
		</li>
		<li class="fullLine">
			<label>联系地址：</label>
			<input name="address" id="address" value="${signUprecord.address }" class="required" size="60">
			<span class='eleRequired'>*</span>
		</li>
		<li class="formTextarea">
			<label>备注：</label>
			<textarea name="memo" id="memo" maxlength="100" ></textarea>
		</li>
		<li class="fullLine">
			<label>报名文件</label>		
			<div id="attachRelaId" class="uploadFile" style="width:400px"></div>			
		</li>
       </ul>
       
       
</div>

<div class="conOperation"> 
  <button class="largeBtn" id="saveSignUp" type="button"><span>提交报名</span></button>  
  <button class="largeBtn" id="closeDiv" type="button"><span>关闭</span></button>
</div>

</form>

<script type="text/javascript">
var suppliersignUpdiv = {};

//身份证号唯一性的校验
suppliersignUpdiv.cardIdChange = function(){
	var cardId = $("#idCard").val();
	var projectId = $("input[name=signUprecord.project.objId]").val();
	$.getJSON($("#initPath").val()+"/SupplierSignupController.do?method=checkedIdCard",{"cardId":cardId,"projectId":projectId},function(json){
		if(json.failure){
			$("div[id=tips]").html('<ul><em>已有相同身份证号报名！</em></ul>');
			$("input[name=cardIdFlag]").val('0');
			$("div[id=tips]").show();
		}else{
			$("input[name=cardIdFlag]").val('1');
			$("div[id=tips]").hide();
		}	
	});
}

//提交报名
suppliersignUpdiv.saveRecord = function(){
	if(!$('#signUprecordForm').valid()||$("input[name=cardIdFlag]").val()=='0'){alert('请正确填写表单!');return;}
	var priceJson = formToJsonObject("signUprecordForm");
	if(confirm("确认提交报名信息？")){
		//如果联系人姓名和身份证号都发生变化新增联系人否则更新联系人
		var url = $("#initPath").val()+"/SupplierSignupController.do?method=saveSupplierSignUp";
		if($('#oldLinker').val()!=$('#linker').val() && $('#oldIdCard').val()!=$('#idCard').val()) {
			url += "&type=create";
		}else {
			url += "&type=update";
		}
		$.getJSON(url,priceJson,function(json){
			if(json.success){
				alert("报名成功！");
				$('#signUpSuccess').val('1'); //标记报名成功
				$('.epsDialogClose').trigger('click');
			}
		});
	}
}

//选择联系人 
suppliersignUpdiv.chooseLinker = function(){
    $.epsDialog({
        id:"linkerSelect",
        title:'请选择联系人',
        url:$('#initPath').val()+'/view/agreement/bargin/signup/a_linker_list.jsp',
        width:800,
        height:500
    }); 
}

$(document).ready(function(){
	
	//预验证
	$('#signUprecordForm').validate();

	//身份证校验
	suppliersignUpdiv.cardIdChange();

	//附件
	$('div[id=attachRelaId]').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=attachRelaId');

	//点击保存
	$("#saveSignUp").click(function(){
		suppliersignUpdiv.saveRecord();
	})

	//点击关闭
	$("#closeDiv").click(function(){
		$('.epsDialogClose').trigger('click');
	})
})
</script>