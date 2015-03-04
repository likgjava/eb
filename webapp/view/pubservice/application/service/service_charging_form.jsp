<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
#serviceChargingForm .fullLine label{width: 250px;}
</style>
<input type="hidden" id="_serviceChargingDialogID"  value="<c:out value="${param.dialogId}"/>"/>
<form:form id="serviceChargingForm" method="post" modelAttribute="serviceCharging">
	<input type="hidden" id="serviceChargingId" name="serviceCharging.objId" value="${serviceCharging.objId}" />
	<div class="formLayout  form2Pa">
		<ul>
			<li class="fullLine">
	            <label>服务名称：</label>
	            <input type="hidden" name="serviceBase.objId" value="${serviceBase.objId}"/>
	            <span>${serviceBase.serviceName}</span>
	        </li>
	    	<li class="fullLine">
	            <label>会员级别：</label>
	            <input type="hidden" id="memberClassObjId" name="memberClass.objId" value="${serviceCharging.memberClass.objId }"/>
				<input type="text" id="memberClassName" class="sysicon siSearch" value="${serviceCharging.memberClass.memberClassName}" size="27"/>
	        </li>
	        <li class="fullLine">
	            <label>标准费用：</label>
	            <input type="hidden" id="servicePrice" value="${serviceBase.servicePrice}"/>
	            <span>￥<fmt:formatNumber value="${serviceBase.servicePrice}" pattern="#,##0.00#" /> 元/${serviceBase.serviceUnitCN}</span>
	        </li>
	        <li class="fullLine">
	            <label>时长（${serviceBase.serviceUnitCN}）：</label>
	            <input type="text" id="duration" name="duration" class="required digits" maxlength="4" size="30" value="${serviceCharging.duration}"/>
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>金额（元）：</label>
	            <input type="hidden" id="amount" name="amount" size="30" value="${serviceCharging.amount}"/>
	            <input style="width: 170px; height: 30px; font-size: 15pt;" type="text" id="showAmount" class="required" size="30" value="<fmt:formatNumber value="${serviceCharging.amount}" pattern="#,##0.00#" />"/>
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>大写金额：</label>
	            <span id="DXAmount" style="font-weight: bold; color: red;"></span>
	        </li>
	        <li class="fullLine">
	          	<label>折扣率（%）：</label>
	          	<input type="text" name="discount" id="discount" class="required floats" max="100" size="30" value="${serviceCharging.discount}"/>
    	  	</li>
	    </ul>
	    
    	<div class="conOperation">
			<button type="button" id="serviceChargingSaveBut" class="next"><span>保存</span></button>
			<button type="button" id="serviceChargingCloseBut" class="next"><span>关闭</span></button>
		</div>
	</div>
</form:form>		

<script>

var ServiceChargingForm={};
ServiceChargingForm.noDiscountAmount = 0; //折扣之前的总金额
ServiceChargingForm.discountAmount = 0; //打折后的总金额
ServiceChargingForm.discount = 0; //折扣率
ServiceChargingForm.globalFlag = true; //全局标记

//保存服务计费
ServiceChargingForm.save=function(){
	if(!$("#serviceChargingForm").valid() || !ServiceChargingForm.globalFlag){ return; }
	
	$("#serviceChargingSaveBut").attr("disabled",true);
	var url = $('#initPath').val()+"/ServiceChargingController.do?method=saveServiceCharging";

   	$('#serviceChargingForm').ajaxSubmit({
   		url:url,
		dataType:'json',
		success:function(json){
			alert("保存成功！");
			$("#addFeeSuccess").val('1'); //修改标记值
   			$("#serviceChargingCloseBut").click();
		},
		error:function(msg){
			alert("保存失败！");
			$("#serviceChargingSaveBut").attr("disabled",false);
		}
	});	
}

//清空指定id的表单域的内容
ServiceChargingForm.clearInputVal = function(inputIds){
	for(var i=0; i<inputIds.length; i++){
		$("#"+inputIds[i]).val("");
	}
}

$(document).ready(function(){
	$("#serviceChargingForm").validate();

	//修改页面
	if($("#serviceChargingId").val()!=null && $("#serviceChargingId").val()!=""){
		var servicePrice = Number($("#servicePrice").val()); //标准计费
		var duration = Number($("#duration").val()); //时长
		
		ServiceChargingForm.noDiscountAmount = servicePrice*duration; //折扣之前的总金额
		ServiceChargingForm.discountAmount = Number($("#amount").val()); //打折后的总金额
		ServiceChargingForm.discount = Number($("#discount").val()); //折扣率
		$("#DXAmount").html(DX(ServiceChargingForm.discountAmount)); //回填大写金额
	}
	
	//保存
	$("#serviceChargingSaveBut").click(function(){
		ServiceChargingForm.save();
	})
	
	//关闭
	$("#serviceChargingCloseBut").click(function(){
		if($("#_serviceChargingDialogID").val() != ""){
			$(document.getElementById($("#_serviceChargingDialogID").val())).find('.epsDialogClose').trigger('click');
		}else{
			$('.epsDialogClose').trigger('click');
		}
	});

	//选择会员级别
	$("input[id=memberClassName]").click(function(){
		var url =$('#initPath').val()+'/view/pubservice/application/member/select_member_class.jsp?dialogId=selectMemberClassDialogId&Hname=memberClassName&Hid=memberClassObjId';
	    $.epsDialog({
		    id:'selectMemberClassDialogId',
	        title:'请选择会员级别',
	        url:url,
	        onClose: function(){
			}
	    });
	})

	//时长输入字符
	$("input[id=duration]").keyup(function(){
		//输入时长无效
		if(!$(this).valid()){
			//清空：金额显示域，金额隐藏域，折扣率
			ServiceChargingForm.clearInputVal(['showAmount','amount','discount']);
			$("#DXAmount").html(''); //清空大写金额
			return ;
		}
		
		var duration = Number($(this).val()); //时长
		var servicePrice = Number($("input[id=servicePrice]").val()); //标准服务费用
		ServiceChargingForm.noDiscountAmount = duration*servicePrice; //折扣之前的总金额
		ServiceChargingForm.discountAmount = duration*servicePrice; //打折后的总金额
		$("#DXAmount").html(DX(ServiceChargingForm.discountAmount)); //回填大写金额
		$("input[id=amount]").val(ServiceChargingForm.discountAmount); //回填服务计费金额（默认值，隐藏域）
		$("input[id=showAmount]").val(formatAmount(ServiceChargingForm.discountAmount,2)); //回填服务计费金额（默认值）
		$("input[id=discount]").val('100'); //回填折扣率（默认值）
		$("input[id=showAmount]").valid();
		$("input[id=discount]").valid();
	});
	
	//金额输入字符
	$("input[id=showAmount]").keyup(function(){
		if(!$(this).valid() || isNaN($(this).val().replace(/,/g,''))){
			//清空：金额显示域，金额隐藏域，折扣率
			ServiceChargingForm.clearInputVal(['showAmount','amount','discount']);
			$("#DXAmount").html(''); //清空大写金额
			return ;
		}

		//小数位多于2位
		if($(this).val().indexOf('.')!=-1 && $(this).val().length-$(this).val().indexOf('.') > 3){
			$(this).val($(this).val().substring(0,$(this).val().length-1));
			return ;
		}

		ServiceChargingForm.discountAmount = Number($(this).val().replace(/,/g,'')); //打折后的总金额
		if(ServiceChargingForm.discountAmount > ServiceChargingForm.noDiscountAmount){ //打折后的金额大于打折钱的金额
			alert("折扣计费：￥"+formatAmount(ServiceChargingForm.discountAmount,2)+" 大于标准计费：￥"+formatAmount(ServiceChargingForm.noDiscountAmount,2));
			ServiceChargingForm.globalFlag = false;
			ServiceChargingForm.discountAmount = ServiceChargingForm.noDiscountAmount;
		}else{
			ServiceChargingForm.globalFlag = true;
		}

		//包含小数点
		if($(this).val().indexOf('.') != -1){
			if($(this).val().indexOf('.') == $(this).val().length-2){
				$(this).val(formatAmount(ServiceChargingForm.discountAmount,1));
			}else if($(this).val().indexOf('.') == $(this).val().length-3){
				$(this).val(formatAmount(ServiceChargingForm.discountAmount,2));
			}
		}else{
			$(this).val(formatAmount(ServiceChargingForm.discountAmount));
		}
		
		$("input[id=amount]").val(ServiceChargingForm.discountAmount);
		ServiceChargingForm.discount = ServiceChargingForm.discountAmount/ServiceChargingForm.noDiscountAmount*100;
		$("input[id=discount]").val(ServiceChargingForm.discount); //回填折扣率
		$("#DXAmount").html(DX(ServiceChargingForm.discountAmount)); //回填大写金额
		$("input[id=discount]").valid();
	}).click(function(){ //金额获得焦点
		if(!$("input[id=duration]").valid()){
			alert("请先输入有效时长！");
			//清空：金额显示域，金额隐藏域，折扣率
			ServiceChargingForm.clearInputVal(['showAmount','amount','discount']);
			$("#DXAmount").html(''); //清空大写金额
			$("input[id=duration]").focus(); //时长获取焦点
			return ;
		}
	});
	
	//折扣率输入字符
	$("input[id=discount]").keyup(function(){
		if(!$("input[id=duration]").valid() || !$(this).valid()){
			//清空：金额显示域，金额隐藏域
			ServiceChargingForm.clearInputVal(['showAmount','amount']);
			$("#DXAmount").html(''); //清空大写金额
			return ;
		}

		ServiceChargingForm.discount = Number($(this).val()); //折扣率
		ServiceChargingForm.discountAmount = ServiceChargingForm.noDiscountAmount*ServiceChargingForm.discount/100;
		$("input[id=amount]").val(ServiceChargingForm.discountAmount); //回填打折后的总金额（隐藏域）
		$("input[id=showAmount]").val(formatAmount(ServiceChargingForm.discountAmount,2)); //回填打折后的总金额（显示域）
		$("#DXAmount").html(DX(ServiceChargingForm.discountAmount)); //回填大写金额
		$("input[id=showAmount]").valid();
	}).click(function(){ //折扣率获得焦点
		if(!$("input[id=duration]").valid()){
			alert("请先输入有效时长！");
			//清空：折扣率
			ServiceChargingForm.clearInputVal(['discount']);
			$("input[id=duration]").focus();
			return ;
		}
	});

})
</script>