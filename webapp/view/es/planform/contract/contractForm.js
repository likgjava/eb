var contractForm={};
contractForm.formatNumber=function(number){
	var number = ""+number;
	if(number.length > 3){
	   var mod = number.length%3;
	   var put = (mod > 0 ? (number.substring(0,mod)) : "");
	   var j=(number.length-mod)/3;
	   for(i=0;i<j;i++){
	    if(mod==0&&i==0){
	     put+=number.substring(mod+3*i,mod+3*i+3);
	    }else if(mod==0&&i!=0){
	     put+=","+number.substring(mod+3*i,mod+3*i+3);
	    }else {
	     put+=","+number.substring(mod+3*i,mod+3*i+3);
	    }
	   }
	   number=put;
	}
	return number;
}
contractForm.showmoney = function(id){
    var a = $("#"+id+"").val();
    var re = /^[0-9]+.?[0-9]*$/;   //判断字符串是否为数字      
    //判断正整数 /^[1-9]+[0-9]*]*$/      
    if (!re.test(a)){    
     	 $("#view"+id).html("<font color='red'>请输入数字</font>");    
     	 $("#"+id+"").focus();    
         return false;    
    }  
    $("#view"+id).html("<font color='red'>￥"+contractForm.formatNumber(a)+".00</font>");
	if($("#view"+id).text()=='￥'){
		$("#view"+id).html("*");
	}
}

contractForm.totalMoney = function(){  //计算合同总金额
	var totalMoney = 0;
	$("input[id^=thisTimePaymentAmt]").each(function(i,n){
		if(''!=$(this).val()){
			var money = parseInt($(this).val());
			totalMoney += money;
		}	
	});
	$("#totalMoeny").val(totalMoney);
}


contractForm.payTimes=function(){    //用于点击的时候， 生成所输入的数量的表格 用于填入数据
	$("#payTimesDiv").empty();   //刚开始的时候， 该div
	var obj = $("#payTimes").val();
	var i=0;
	if(parseInt(obj)<0||isNaN(parseInt(obj))){$("#pay").hide();return false;}
	$("#payTimesDiv").append("<h4 id='pay'><span>合同付款批次</span></h4>");
	while(i<parseInt(obj)){
		var j=parseInt(i)+1;
		$("#pay").show();
		$("#payTimesDiv").append("<li class='fullLine'><label for=''>"+j+": </label><label for=''><input type='hidden' name='theTimes"+j+"'value='"+j+"'/>支付时间: </label><input type='text' name='applyDate"+j+"' id='applyDate"+j+"'/><span></span><label for=''>支付金额: </label><input type='text' name='thisTimePaymentAmt"+j+"' id='thisTimePaymentAmt"+j+"' onkeyup='contractForm.showmoney(\"thisTimePaymentAmt"+j+"\")'  onblur='contractForm.totalMoney()'/><span id='viewthisTimePaymentAmt"+j+"'></span></li>");
		$("#applyDate"+j).epsDatepicker();
		i++;
	}
}
$(document).ready(function(){
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text()
	});
	if($("#contractMethod").val()=='01'){
		$("#div1").show();
		$("#div2").remove();
	}else{
		$("#div2").show();
		$("#div1").remove();
	}
	$('#contractForm').validate(); 
	$('#contractAcquirerForm').validate();
	$('#contractSupplierForm').validate();
	$('#witnessPartyForm').validate();
    $("#acquirerSignedTime").epsDatepicker();
    $("#supplierSignedTime").epsDatepicker();
    $("#signedTime").epsDatepicker();
	//返回
	$('#return').click(function(){
		$("#tabform").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractList&projectId='+$("#projectId").val());
	});
	if($("#taskPlanMSubListSize").val()==0&&$("#contractMethod").val()=='02'){
		alert("采购条目已用完!");
		$('#return').click();
	}
	//保存
	$('#contractSave').click(function(){
		
		var totalMoney = 0;
		$("input[id^=thisTimePaymentAmt]").each(function(i,n){
			if(''!=$(this).val()){
				var money = parseInt($(this).val());
				totalMoney += money;
			}	
		});
		if($("#totalMoeny").val()!=totalMoney){
			alert("合同批次付款总和应该等于合同总金额！");
			return false;
		}
		
		$("#contractStatus").val("00");
		var taskPlanSubIds = '';
		$("input[name='taskPlanSubId']").each(function(i,n){
					if($(this).attr("checked")==true){
						taskPlanSubIds+=$(this).val()+',';
					}
		})
		taskPlanSubIds = taskPlanSubIds.substring(0,taskPlanSubIds.length-1);
		if(!$('#contractForm').valid()||!$('#contractAcquirerForm').valid()||!$('#contractSupplierForm').valid()||!$('#witnessPartyForm').valid()){
			{alert('请正确填写表单!');return;}
		}
		var contractPaymentApplyInfo = formToJsonObject('contractPaymentApplyInfoForm','json');
		var contract = formToJsonObject('contractForm','json');
		contract.attachRelaId = $("input[name=attachRelaId]").val();
		contract.payTimes = $("#payTimes").val();
		contract.subProjectId = $("#subProj").val();
		contract.taskPlanSubIds = taskPlanSubIds;
		var contractAcquirer = formToJsonObject('contractAcquirerForm','json');
		var contractSupplier = formToJsonObject('contractSupplierForm','json');
		var witnessParty = formToJsonObject('witnessPartyForm','json');
		var contractPaymentApplyInfo = formToJsonObject('contractPaymentApplyInfoForm','json');
	    var jsonObj = {};
	    jsonObj.contractString = JSON.stringify(contract);
	    jsonObj.contractAcquirerString = JSON.stringify(contractAcquirer);
	    jsonObj.contractSupplierString=JSON.stringify(contractSupplier);
	    jsonObj.witnessPartyString=JSON.stringify(witnessParty);
	    jsonObj.contractPaymentApplyInfoString=JSON.stringify(contractPaymentApplyInfo);
	    var jsonContracts = new Array();
		for(i=0;i<parseInt($("#payTimes").val());i++){
			var j= i+1;
			var keyname = 'contractStr['+(j)+']';
			var obj = {};
			obj.theTimes=$('input[name="theTimes'+j+'"]').val();
            obj.thisTimePaymentAmt=$('input[name="thisTimePaymentAmt'+j+'"]').val();
            obj.applyDate=$('input[name="applyDate'+j+'"]').val();
			jsonContracts.push(obj);
	    }
		jsonObj.contractStr = JSON.stringify(jsonContracts);
		$.getJSON($('#initPath').val()+'/ContractController.do?method=saveContract&projectId='+$("#projectId").val(),jsonObj,function(json){
			if(json.failure){alert(json.result);return;}
			alert("保存成功!");
			$('#return').click();
		});
	});
	//提交
	$('#contractSubmit').click(function(){
		var totalMoney = 0;
		$("input[id^=thisTimePaymentAmt]").each(function(i,n){
			if(''!=$(this).val()){
				var money = parseInt($(this).val());
				totalMoney += money;
			}	
		});
		if($("#totalMoeny").val()!=totalMoney){
			alert("合同批次付款总和应该等于合同总金额！");
			return false;
		}
		
		
		$("#contractStatus").val("01");
		var taskPlanSubIds = '';
		$("input[name='taskPlanSubId']").each(function(i,n){
					if($(this).attr("checked")==true){
						taskPlanSubIds+=$(this).val()+',';
					}
		})
		taskPlanSubIds = taskPlanSubIds.substring(0,taskPlanSubIds.length-1);
		if(!$('#contractForm').valid()||!$('#contractAcquirerForm').valid()||!$('#contractSupplierForm').valid()||!$('#witnessPartyForm').valid()){
			{alert('请正确填写表单!');return;}
		}
		var contract = formToJsonObject('contractForm','json');
		contract.attachRelaId = $("input[name=attachRelaId]").val();
		contract.payTimes = $("#payTimes").val();
		contract.subProjectId = $("#subProj").val();
		contract.taskPlanSubIds = taskPlanSubIds;
		var contractAcquirer = formToJsonObject('contractAcquirerForm','json');
		var contractSupplier = formToJsonObject('contractSupplierForm','json');
		var witnessParty = formToJsonObject('witnessPartyForm','json');
		var contractPaymentApplyInfo = formToJsonObject('contractPaymentApplyInfoForm','json');
		var jsonObj = {};
	    jsonObj.contractString = JSON.stringify(contract);
	    jsonObj.contractAcquirerString = JSON.stringify(contractAcquirer);
	    jsonObj.contractSupplierString=JSON.stringify(contractSupplier);
	    jsonObj.witnessPartyString=JSON.stringify(witnessParty);
	    jsonObj.contractPaymentApplyInfoString=JSON.stringify(contractPaymentApplyInfo);
	    var jsonContracts = new Array();
		for(i=0;i<parseInt($("#payTimes").val());i++){
			var j= i+1;
			var keyname = 'contractStr['+(j)+']';
			var obj = {};
			obj.theTimes=$('input[name="theTimes'+j+'"]').val();
            obj.thisTimePaymentAmt=$('input[name="thisTimePaymentAmt'+j+'"]').val();
            obj.applyDate=$('input[name="applyDate'+j+'"]').val();
			jsonContracts.push(obj);
	    }
		jsonObj.contractStr = JSON.stringify(jsonContracts);
		$.getJSON($('#initPath').val()+'/ContractController.do?method=saveContract&projectId='+$("#projectId").val(),jsonObj, function(json){
			if(json.failure){alert(json.result);return;}
			alert("提交成功!");
			$('#return').click();
		});
	});
});
