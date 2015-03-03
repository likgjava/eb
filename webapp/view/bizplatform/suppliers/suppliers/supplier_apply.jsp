<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="SupplierApplyForm" method="post">
    <input type="hidden" name="objId" id="buyerId"/>
	<input type="hidden" name="orgInfoId" id="orgInfoId" value="${orgInfo.objId}"/>
<div class="formLayout form2Pa">
	<ul>
			<li class="fullLine">
	            <label for="isManufacturer">是否厂家：</label>
	         	<select name="isManufacturer" id="isManufacturer" class="required">
	         		<option value="0">否</option>
	         		<option value="1">是</option>
	         	</select>
				<span class="eleRequired">*</span> 
	    	</li>
	    	<li class="fullLine">
	            <label for="input01">经营地址：</label>
	            <input type="text" id="unitAddress" name="unitAddress" class="required" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label for="input02">投标范围及类别：</label>
	            <input type="text" id="bidForRange_1" readonly="readonly" size="60"/>
	            <input type="hidden" id="bidForRange_2" />
	            <input type="hidden" name="bidForRange" id="bidForRange" class="required" />
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="formTextarea">
	            <label for="input02">主营范围（中）：</label>
	            <textarea name="mainProductsCn" id="mainProductsCn" class="required"></textarea>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="formTextarea">
	           <label for="input02">主营范围（英）：</label>
	           <textarea name="mainProductsEn" id="mainProductsEn"></textarea>
	        </li>
	        <li class="fullLine">
	            <label for="input02">企业性质：</label>
	            <html:select selectedValue="0" styleClass="required" id="entPrpt" name="entPrpt" code="biz.supplier.entprpt"></html:select>
	        </li>
	        <li class="fullLine">
	            <label for="input02">开户银行名称：</label>
	            <input type="text" name="openBank" id="openBank" class="required" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label for="input02">开户银行行号：</label>
	         	<input type="text" name="openAccountNmbr" id="openAccountNmbr" class="required digits" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label for="input02">开户银行账号：</label>
	        	<input type="text" name="openAccount" id="openAccount" class="required digits" size="60"/>
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="fullLine">
	            <label for="input02">网址：</label>
	         	<input type="text" name="webUrl" id="webUrl" class="url" size="60"/>
	            <span class="eleRequired"></span> 
	        </li>
	        <li class="formTextarea">
	            <label for="input02">企业简介（中）：</label>
	          	<textarea name="descCn" id="descCn" class="required"></textarea>	
	            <span class="eleRequired">*</span> 
	        </li>
	        <li class="formTextarea">
	            <label for="input02">企业简介（英）:</label>
	            <textarea name="descEn" id="descEn"></textarea>
	        </li>
	   </ul>
</div>
	<div class="conOperation">
		<button class="largeBtn" type="button" id="saveSupplierBtn"><span>申请</span></button>
	</div>
</form>

<script>
var SupplierApplyForm={};
//投标范围及类别
SupplierApplyForm.toSelectBidRangePage=function(){
	/*弹出选择采购品目的层*/
  $.epsDialog({
      title:'选择品目',
      url:$('#initPath').val()+'/view/base/purcategory/purcategory_select.jsp',
      top:false,//绝对定位上边距
      left:false,//绝对定位左边距
      noBorder:false,//没有边框
      draggable:true,//拖动
      hasBg:true,//背景
      fadeTo:1//透明度          
  })
}
/*保存供应商的修改信息*/
SupplierApplyForm.saveSupplier=function(){
	if($("#SupplierApplyForm").valid()){
		if(window.confirm('确定申请供应商的角色？')){
			$.getJSON($('#initPath').val()+'/SupplierController.do?method=saveApplySupplier',formToJsonObject('SupplierApplyForm'), function(json){
				if(json.success=='true'){
					alert('申请成功，请耐心等待管理员的审核');
					$('#conBody').loadPage($('#initPath').val()+"/OrgInfoController.do?method=toApplyOrgInfo");
				}else{
					alert('操作失败');
				}
			})
		}
	}
}
$(document).ready(function(){
	$('#SupplierApplyForm').validate();

	//保存授权信息
	$("#saveSupplierBtn").click(function(){
		SupplierApplyForm.saveSupplier();
	})
  
  //投标范围及类别
	$("#bidForRange_1").click(function(e){
	    $.epsDialog({
	        title:'选择品目',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=bidForRange_2&NAMES=bidForRange_1&className=PurCategory&action=listTop&isCheckBox=true',
	        onClose: function(){ 
      		$("#bidForRange").val($("#bidForRange_2").val()+"##||##"+$("#bidForRange_1").val());
      	}
	    }); 
	});
})
</script>