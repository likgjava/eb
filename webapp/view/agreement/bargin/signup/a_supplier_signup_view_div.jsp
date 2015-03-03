<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<form id="signUprecordForm">

<div class="formLayout form2Pa">
		<ul>
		<li class="fullLine">
			<label>招标项目名称：</label>
			<span>${signUprecord.project.projName }</span>
		</li>
		<li class="fullLine">
			<label for="">供应商名称：</label>
			<input type="hidden" name="signUprecord.supplier.objId" value="${signUprecord.supplier.objId }">
			<span>${signUprecord.supplierName }</span>
		</li>
		<li>
			<label for="">联系人：</label>
			<span>${signUprecord.linker}</span>
		</li>
		<li>
			<label for="">联系电话：</label>
			<span>${signUprecord.linkerTel }</span>
		</li>
		<li class="fullLine">
			<label for="">身份证号：</label>
			<span>${signUprecord.idCard }</span>
		</li>
		<li>
			<label for="">联系地址：</label>
			<span>${signUprecord.address }</span>
		</li>
		<li>
			<label for="">邮编：</label>
			<span>${signUprecord.zipCode }</span>
		</li>
		<li class="fullLine">
			<label>报名文件</label>		
			<input id="attachRelaId" type="hidden" value="${signUprecord.attachRelaId }">
			<div id="attachRelaId" class="uploadFile"></div>			
		</li>
		<li class="fullLine">
			<label for="">备注：</label>
			<span>${signUprecord.memo }</span>
		</li>
       </ul>
       
       
</div>

<div class="conOperation"> 
  <button class="largeBtn" id="return" type="button" onclick="suppliersignUpdiv.close()"><span>关闭</span></button>
</div>

</form>

<script type="text/javascript">
var suppliersignUpdiv = {};


suppliersignUpdiv.close = function(){
	$('.epsDialogClose').trigger('click');
}

$(document).ready(function(){
	
	//附件&isSelect=yes&attachRelaId='+resId
	$('div[id=attachRelaId]').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=attachRelaId&isSelect=yes&attachRelaId='+$("input[id=attachRelaId]").val());
})
</script>