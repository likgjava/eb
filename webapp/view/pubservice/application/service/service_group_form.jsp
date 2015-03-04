<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
#serviceGroupForm .fullLine label{width: 250px;}
</style>
<input type="hidden" id="_serviceGroupDialogID"  value="<c:out value="${param.dialogId}"/>"/>
<!-- 当前主服务的id，供选择搭配服务的过滤使用 -->
<input type="hidden" id="objId" value="${serviceBase.objId}" />
<form:form id="serviceGroupForm" method="post" modelAttribute="serviceGroup">
	<input type="hidden" id="serviceGroupId" name="serviceGroup.objId" value="${serviceGroup.objId}" />
	<div class="formLayout  form2Pa">
		<ul>
			<li class="fullLine">
	            <label>主服务名称：</label>
	            <input type="hidden" name="mainService.objId" value="${serviceBase.objId}"/>
	            <span>${serviceBase.serviceName}</span>
	        </li>
	        <li class="fullLine">
	            <label>会员级别：</label>
	            <input type="hidden" id="memberClassObjId" name="memberClass.objId" value="${serviceGroup.memberClass.objId }"/>
				<input type="text" id="memberClassName" class="sysicon siSearch" value="${serviceGroup.memberClass.memberClassName}" size="27"/>
	        </li>
	    	<li class="fullLine">
	            <label>搭配服务：</label>
	            <input type="hidden" id="appendServiceObjId" name="appendService.objId" value="${serviceGroup.appendService.objId }"/>
				<input type="text" id="appendServiceName" class="sysicon siSearch required" value="${serviceGroup.appendService.serviceName}" size="27"/>
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>搭配服务标准费用：</label>
	            <input type="hidden" id="appendServicePrice" value="${serviceGroup.appendService.servicePrice}"/>
	            <input type="hidden" id="apPrice" value="${serviceGroup.appendService.servicePrice}"/>
	            <input type="hidden" id="apUnitCN" value=""/>
	            <span>￥<span name="apPriceS"><fmt:formatNumber value="${serviceGroup.appendService.servicePrice}" pattern="#,##0.00#" /></span> 元/<span name="apUnitCNS">${serviceGroup.appendService.serviceUnitCN}</span></span>
	        </li>
	        <li class="fullLine">
	            <label>时长（<span name="apUnitCNS">${serviceGroup.appendService.serviceUnitCN}</span>）：</label>
	            <input type="text" id="duration" name="duration" class="required digits" maxlength="4" size="30" value="${serviceGroup.duration}"/>
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>金额（元）：</label>
	            <input type="hidden" id="amount" name="amount" size="30" value="${serviceGroup.amount}"/>
	            <input style="width: 170px; height: 30px; font-size: 15pt;" type="text" id="showAmount" class="required" size="30" value="<fmt:formatNumber value="${serviceGroup.amount}" pattern="#,##0.00#" />"/>
	            <span class="eleRequired">*</span>
	        </li>
	        <li class="fullLine">
	            <label>大写金额：</label>
	            <span id="DXAmount" style="font-weight: bold; color: red;"></span>
	        </li>
	        <li class="fullLine">
	          	<label>折扣率（%）：</label>
	          	<input type="text" name="discount" id="discount" class="required floats" max="100" size="30" value="${serviceGroup.discount}"/>
	          	<span class="eleRequired">*</span>
    	  	</li>
	    </ul>
	    
    	<div class="conOperation">
			<button type="button" id="serviceGroupSaveBut" class="next"><span>保存</span></button>
			<button type="button" id="serviceGroupCloseBut" class="next"><span>关闭</span></button>
		</div>
	</div>
</form:form>		

<script>

var ServiceGroupForm={};
ServiceGroupForm.servicePrice = 0; //搭配服务的标准单价
ServiceGroupForm.noDiscountAmount = 0; //折扣之前的总金额
ServiceGroupForm.discountAmount = 0; //打折后的总金额
ServiceGroupForm.discount = 0; //折扣率
ServiceGroupForm.globalFlag = true; //全局标记

//保存服务计费
ServiceGroupForm.save=function(){
	if(!$("#serviceGroupForm").valid() || !ServiceGroupForm.globalFlag){ return; }
	
	$("#serviceGroupSaveBut").attr("disabled",true);
	var url = $('#initPath').val()+"/ServiceGroupController.do?method=saveServiceGroup";

   	$('#serviceGroupForm').ajaxSubmit({
   		url:url,
		dataType:'json',
		success:function(json){
			alert("保存成功！");
			$("#addFeeSuccess").val('1'); //修改标记值
   			$("#serviceGroupCloseBut").click();
		},
		error:function(msg){
			alert("保存失败！");
			$("#serviceGroupSaveBut").attr("disabled",false);
		}
	});	
}

//清空指定id的表单域的内容
ServiceGroupForm.clearInputVal = function(inputIds){
	for(var i=0; i<inputIds.length; i++){
		$("#"+inputIds[i]).val("");
	}
}

$(document).ready(function(){
	$("#serviceGroupForm").validate();

	//修改页面
	if($("#serviceGroupId").val()!=null && $("#serviceGroupId").val()!=""){
		ServiceGroupForm.servicePrice = Number($("#apPrice").val()); //标准计费
		var duration = Number($("#duration").val()); //时长
		
		ServiceGroupForm.noDiscountAmount = ServiceGroupForm.servicePrice*duration; //折扣之前的总金额
		ServiceGroupForm.discountAmount = Number($("#amount").val()); //打折后的总金额
		ServiceGroupForm.discount = Number($("#discount").val()); //折扣率
		$("#DXAmount").html(DX(ServiceGroupForm.discountAmount)); //回填大写金额
	}
	
	//保存
	$("#serviceGroupSaveBut").click(function(){
		ServiceGroupForm.save();
	})
	
	//关闭
	$("#serviceGroupCloseBut").click(function(){
		if($("#_serviceGroupDialogID").val() != ""){
			$(document.getElementById($("#_serviceGroupDialogID").val())).find('.epsDialogClose').trigger('click');
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

	//选择搭配服务
	$("input[id=appendServiceName]").click(function(){
		var url =$('#initPath').val()+'/view/pubservice/application/service/select_service.jsp?dialogId=selectServiceDialogId&Hname=appendServiceName&Hid=appendServiceObjId&Hprice=apPrice&HunitCN=apUnitCN';
	    $.epsDialog({
		    id:'selectServiceDialogId',
	        title:'选择搭配服务',
	        url:url,
	        onClose: function(){
	        	//清空：金额显示域，金额隐藏域，时长，折扣率
	    		ServiceGroupForm.clearInputVal(['showAmount','amount','duration','discount']);
	    		$("#DXAmount").html(''); //清空大写金额
	        	if($("input[id=appendServiceName]").val()==""){
	        		$('#apPrice').val("");
	        		$('#apUnitCN').val("")
		        }
	    		$("span[name=apPriceS]").html($('#apPrice').val());
	    		$("span[name=apUnitCNS]").html($('#apUnitCN').val());
	    		ServiceGroupForm.servicePrice = Number($('#apPrice').val());
	    		$("input[id=appendServiceName]").valid(); //验证
			}
	    });
	})

	//时长输入字符
	$("input[id=duration]").keyup(function(){
		//输入时长无效
		if(!$(this).valid()){
			//清空：金额显示域，金额隐藏域，折扣率
			ServiceGroupForm.clearInputVal(['showAmount','amount','discount']);
			$("#DXAmount").html(''); //清空大写金额
			return ;
		}
		
		var duration = Number($(this).val()); //时长
		ServiceGroupForm.noDiscountAmount = duration*ServiceGroupForm.servicePrice; //折扣之前的总金额
		ServiceGroupForm.discountAmount = duration*ServiceGroupForm.servicePrice; //打折后的总金额
		$("#DXAmount").html(DX(ServiceGroupForm.discountAmount)); //回填大写金额
		$("input[id=amount]").val(ServiceGroupForm.discountAmount); //回填服务计费金额（默认值，隐藏域）
		$("input[id=showAmount]").val(formatAmount(ServiceGroupForm.discountAmount,2)); //回填服务计费金额（默认值）
		$("input[id=discount]").val('100'); //回填折扣率（默认值）
		$("input[id=showAmount]").valid();
		$("input[id=discount]").valid();
	}).click(function(){ //时长获得焦点
		if(!$("input[id=appendServiceName]").valid()){
			alert("请先选择搭配服务！");
			//清空：金额隐藏域，时长，折扣率
			ServiceGroupForm.clearInputVal(['amount','duration','discount']);
			$("input[id=appendServiceName]").click(); //选择搭配服务
			return ;
		}
	});
	
	//金额输入字符
	$("input[id=showAmount]").keyup(function(){
		if(!$(this).valid() || isNaN($(this).val().replace(/,/g,''))){
			//清空：金额显示域，金额隐藏域，折扣率
			ServiceGroupForm.clearInputVal(['showAmount','amount','discount']);
			$("#DXAmount").html(''); //清空大写金额
			return ;
		}

		//小数位多于2位
		if($(this).val().indexOf('.')!=-1 && $(this).val().length-$(this).val().indexOf('.') > 3){
			$(this).val($(this).val().substring(0,$(this).val().length-1));
			return ;
		}

		ServiceGroupForm.discountAmount = Number($(this).val().replace(/,/g,'')); //打折后的总金额
		if(ServiceGroupForm.discountAmount > ServiceGroupForm.noDiscountAmount){ //打折后的金额大于打折钱的金额
			alert("折扣计费：￥"+formatAmount(ServiceGroupForm.discountAmount,2)+" 大于标准计费：￥"+formatAmount(ServiceGroupForm.noDiscountAmount,2));
			ServiceGroupForm.globalFlag = false;
			ServiceGroupForm.discountAmount = ServiceGroupForm.noDiscountAmount;
		}else{
			ServiceGroupForm.globalFlag = true;
		}

		//包含小数点
		if($(this).val().indexOf('.') != -1){
			if($(this).val().indexOf('.') == $(this).val().length-2){
				$(this).val(formatAmount(ServiceGroupForm.discountAmount,1));
			}else if($(this).val().indexOf('.') == $(this).val().length-3){
				$(this).val(formatAmount(ServiceGroupForm.discountAmount,2));
			}
		}else{
			$(this).val(formatAmount(ServiceGroupForm.discountAmount));
		}
		
		$("input[id=amount]").val(ServiceGroupForm.discountAmount);
		ServiceGroupForm.discount = ServiceGroupForm.discountAmount/ServiceGroupForm.noDiscountAmount*100;
		$("input[id=discount]").val(ServiceGroupForm.discount); //回填折扣率
		$("#DXAmount").html(DX(ServiceGroupForm.discountAmount)); //回填大写金额
		$("input[id=discount]").valid();
	}).click(function(){ //金额获得焦点
		if(!$("input[id=appendServiceName]").valid()){
			alert("请先选择搭配服务！");
			//清空：金额显示域，金额隐藏域，时长，折扣率
			ServiceGroupForm.clearInputVal(['showAmount','amount','duration','discount']);
			$("input[id=appendServiceName]").click(); //选择搭配服务
			return ;
		}
		if(!$("input[id=duration]").valid()){
			alert("请先输入有效时长！");
			//清空：金额显示域，金额隐藏域，折扣率
			ServiceGroupForm.clearInputVal(['showAmount','amount','discount']);
			$("input[id=duration]").focus(); //时长获取焦点
			return ;
		}
	});
	
	//折扣率输入字符
	$("input[id=discount]").keyup(function(){
		if(!$("input[id=duration]").valid() || !$(this).valid()){
			$("#DXAmount").html(''); //清空大写金额
			//清空：金额显示域，金额隐藏域
			ServiceGroupForm.clearInputVal(['showAmount','amount']);
			return ;
		}

		ServiceGroupForm.discount = Number($(this).val()); //折扣率
		ServiceGroupForm.discountAmount = ServiceGroupForm.noDiscountAmount*ServiceGroupForm.discount/100;
		$("input[id=amount]").val(ServiceGroupForm.discountAmount); //回填打折后的总金额（隐藏域）
		$("input[id=showAmount]").val(formatAmount(ServiceGroupForm.discountAmount,2)); //回填打折后的总金额（显示域）
		$("#DXAmount").html(DX(ServiceGroupForm.discountAmount)); //回填大写金额

		$("input[id=showAmount]").valid();
	}).click(function(){ //折扣率获得焦点
		if(!$("input[id=appendServiceName]").valid()){
			alert("请先选择搭配服务！");
			//清空：金额显示域，金额隐藏域，时长，折扣率
			ServiceGroupForm.clearInputVal(['showAmount','amount','duration','discount']);
			$("input[id=appendServiceName]").click(); //选择搭配服务
			return ;
		}
		if(!$("input[id=duration]").valid()){
			alert("请先输入有效时长！");
			//清空：折扣率
			ServiceGroupForm.clearInputVal(['discount']);
			$("input[id=duration]").focus();
			return ;
		}
	});

})
</script>