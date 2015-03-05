<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
        
<form id="giftSupplierForm" name="giftSupplierForm" method="post">

	<div id="giftSupplierInfo" class="formLayout form2Pa">
		<h4 class="title"><span>新增礼品供货商</span><span class="eleRequired">（带*号的为必填项）</span></h4>			
		<ul>			
			<li class="fullLine">
				<label>供货商名称：</label>
				<input type="text" name="supplierName" maxlength="50" size="40"  id="supplierNameDialog" class="sysicon siSearch required"/>
				<span class="eleRequired" >*</span>
				<input type="hidden" name="supplierId" id="supplierId" value="" />
				<input type="hidden" name="isUsed" value="true" />
				<input type="hidden" name="" id="saveType" value="${saveType}" />
				<input type="hidden" id="filterVelue" value="${filterVelue}"/>
			</li>
			<li class="fullLine left">
				<label>开始时间：</label>
				<input type="text" name="startTime" id="startTimeDialog" size="37" class="sysicon siDate required" value=""/><span class="eleRequired">*</span>
			</li>
			<li class="fullLine left">
				<label>结束时间：</label>
				<input type="text" name="endTime" id="endTimeDialog" size="37" class="sysicon siDate required" value=""/><span class="eleRequired">*</span>
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

//管理员则选择所属机构
$("input[id=supplierNameDialog]").click(function(){
	var url =$('#initPath').val()+'/view/bizplatform/organization/manager/select_orginfo.jsp?Hname=supplierNameDialog&Hid=supplierId&orgType=s&filterVelue='+$("#filterVelue").val();
    $.epsDialog({
        title:'请选择供货商',
        url:url
    });
})

//返回
$('#giftSupplierBtnReturn').click(function(){
	$('#conBody').loadPage($('#initPath').val()+'/GiftSupplierController.do');	
});


//保存
$('button[id=giftSupplierBtn_save]').click(function(){
	if(!$('#giftSupplierForm').valid()){alert('请正确填写表单!');return;}	
	var giftSupplierName = $('#supplierNameDialog').val();

	//验证供货商名称是否存在
	var jsonObj=$.ajax({url:$("#initPath").val()+"/GiftSupplierController.do?method=isUnique",data:{"giftSupplierName":native2ascii(giftSupplierName)}, async: false }).responseText
	jsonObj = eval("("+jsonObj+")");
	if(jsonObj.isUnique !="true"){		
		alert("该供货商名称已经存在！");
		return false;
	}	
	if(window.confirm("确定保存礼品供货商吗?")){
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
			$('#conBody').loadPage($('#initPath').val()+'/GiftSupplierController.do');
		},
		error:function(msg){
			alert(JSON.stringify(msg));
		}
	});
}	
</script>