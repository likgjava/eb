<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
        
<form id="giftSupplierForm" name="giftSupplierForm" method="post">

	<div id="giftSupplierInfo" class="formLayout form2Pa">
		<h4 class="title"><span>修改礼品供货商</span><span class="eleRequired">（带*号的为必填项）</span></h4>			
		<ul>			
			<li class="fullLine">
				<label>供货商名称：</label>
				${giftSupplier.supplierName}
				<input type="hidden" name="objId" value="${giftSupplier.objId}" />
				<input type="hidden" name="" id="saveType" value="${saveType}" />
			</li>
			<li class="fullLine left">
				<label>开始时间：</label>
				<input type="text" name="startTime" id="startTimeDialog" class="sysicon siDate required" value="${giftSupplier.startTime}"/><span class="eleRequired">*</span>
			</li>
			<li class="fullLine left">
				<label>结束时间：</label>
				<input type="text" name="endTime" id="endTimeDialog" class="sysicon siDate required" value="${giftSupplier.endTime}"/><span class="eleRequired">*</span>
			</li>
		</ul>
		
	    <div class="conOperation">
	        <button class="largeBtn" id="giftSupplierBtn_save" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
	        <button class="largeBtn" id="giftSupplierBtnReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	    </div>
	</div>
</form>

<script>
var giftSupplierForm={};

$(document).ready(function(){
	//日期表单验证	
	$("#startTimeDialog").epsDatepicker();
	$("#endTimeDialog").epsDatepicker();
});

//返回
$('#giftSupplierBtnReturn').click(function(){
	//自动关闭		
	$('.epsDialogClose').trigger('click');
});


//保存
$('button[id=giftSupplierBtn_save]').click(function(){
	if(!$('#giftSupplierForm').valid()){alert('请正确填写表单!');return;}		
	if(window.confirm("确定修改保存礼品供货商吗?")){
		giftSupplierForm.submit();
	}
});

//表单提交
giftSupplierForm.submit = function(){
	var saveType = $('#saveType').val();
	var url = $('#initPath').val()+'/GiftSupplierController.do?method=saveGiftSupplier&saveType='+saveType;	

	$('#giftSupplierForm').ajaxSubmit({
		url:url,
		dataType:'json',
		success:function(json){
			alert("操作成功");
			//自动关闭		
			$('.epsDialogClose').trigger('click');
			$('#conBody').loadPage($('#initPath').val()+'/GiftSupplierController.do');
		},
		error:function(msg){
			alert(JSON.stringify(msg));
		}
	});
}	
</script>