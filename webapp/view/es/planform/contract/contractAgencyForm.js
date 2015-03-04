
var contractForm={};
contractForm.payTimes=function(){    //用于点击的时候， 生成所输入的数量的表格 用于填入数据
	$("#payTimesDiv").empty();   //刚开始的时候， 该div
	var obj = $("#payTimes").val();
	var i=0;
	if(parseInt(obj)<0||isNaN(parseInt(obj))){$("#pay").hide();return false;}
	while(i<parseInt(obj)){
		var j=parseInt(i)+1;
		$("#pay").show();
		$("#payTimesDiv").append("<li class='fullLine'><label for=''>"+j+": </label><label for=''><input type='hidden' name='theTimes"+j+"'value='"+j+"'/>支付金额: </label><input type='text' name='thisTimePaymentAmt"+j+"'/><label for=''>支付时间: </label><input type='text' name='applyDate"+j+"' id='applyDate"+j+"'/><span></span></li>");
		$("#applyDate"+j).epsDatepicker();
		i++;
	}
}
$(document).ready(function(){
	contractForm.showSupplierList=function(i){
    	 $.epsDialog({
 	        title:'投标单位列表',
 	        url:$('#initPath').val()+'/view/es/planform/contract/supplierListGrid.jsp?num='+i+'',
 	        width: '800',
 	        height: '420',
 	 	    isReload:true
 			});
     }
	contractForm.showBuyerList=function(i){
   	 $.epsDialog({
	        title:'招标单位列表',
	        url:$('#initPath').val()+'/view/es/planform/contract/buyerListGrid.jsp?num='+i+'',
	        width: '800',
	        height: '420',
	 	    isReload:true
			});
    }
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text()
	});
	$("#div2").hide();
	$("#pay").hide();
	if($("#notDivide").val()=='true'){
		$("#div2").show();
		$("#div1").hide();
	}
	
	$("#contractMethod").change(
			function(){
				if($("#contractMethod").val()=='01'){
					$("#div2").hide();
					$("#div1").show();
				}else{
					$("#div1").hide();
					$("#div2").show();
				}
			}
	)
	$('#contractForm').validate(); 
	$('#contractAcquirerForm').validate();
	$('#contractSupplierForm').validate();
	$('#witnessPartyForm').validate();
     			$("#acquirerSignedTime").epsDatepicker();
     			$("#supplierSignedTime").epsDatepicker();
     			$("#signedTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ContractController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('contractForm', json.contract);
    	});
    }
	//返回
	$('#return').click(function(){
		$("#conBody").empty().loadPage($('#initPath').val()+'/view/es/planform/contract/contractListForAgency.jsp');
	});
	//保存
	$('#contractSave').click(function(){
		$("#contractStatus").val("00");
		if(!$('#contractForm').valid()){alert('请正确填写表单!');return;}
		if(!$('#contractAcquirerForm').valid()){alert('请正确填写表单!');return;}
		if(!$('#contractSupplierForm').valid()){alert('请正确填写表单!');return;}
		if(!$('#witnessPartyForm').valid()){alert('请正确填写表单!');return;}
		var contractPaymentApplyInfo = formToJsonObject('contractPaymentApplyInfoForm','json');
		var contract = formToJsonObject('contractForm','json');
		contract.attachRelaId = $("input[name=attachRelaId]").val();
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
		alert(jsonObj.contractStr);
		$.getJSON($('#initPath').val()+'/ContractController.do?method=saveContractForAgency',jsonObj,function(json){
			if(json.failure){alert(json.result);return;}
			alert("保存成功!");
			$("#conBody").empty().loadPage($('#initPath').val()+'/view/es/planform/contract/contractListForAgency.jsp');
		});
	});
	//提交
	$('#contractSubmit').click(function(){
		$("#contractStatus").val("01");
		if($("#contractMethod").val()=='01'){
			$("input[name='subProj']").each(
					function(i,n){
						if($(this).attr("checked")==true){
							$("#subProjectId").val($(this).val());
						}
					}
			)
		}else{
			var str ='';
			$("input[name='taskPlanSubId']").each(
					function(i,n){
						if($(this).attr("checked")==true){
							str+=$(this).val()+',';
						}
					}
			)
			str = str.substring(1,str.length-1);
			$("#taskPlanSubIds").val(str);
		}
		if(!$('#contractForm').valid()){alert('请正确填写表单!');return;}
		if(!$('#contractAcquirerForm').valid()){alert('请正确填写表单!');return;}
		if(!$('#contractSupplierForm').valid()){alert('请正确填写表单!');return;}
		if(!$('#witnessPartyForm').valid()){alert('请正确填写表单!');return;}
		var contractPaymentApplyInfo = formToJsonObject('contractPaymentApplyInfoForm','json');
		var contract = formToJsonObject('contractForm','json');
		contract.attachRelaId = $("input[name=attachRelaId]").val();
		var contractAcquirer = formToJsonObject('contractAcquirerForm','json');
		var contractSupplier = formToJsonObject('contractSupplierForm','json');
		var witnessParty = formToJsonObject('witnessPartyForm','json');
		$.getJSON($('#initPath').val()+'/ContractController.do?method=saveContract&projectId='+$("#projectId").val(), {"contractString":JSON.stringify(contract),"contractAcquirerString":JSON.stringify(contractAcquirer),"contractSupplierString":JSON.stringify(contractSupplier),"witnessPartyString":JSON.stringify(witnessParty)}, function(json){
			if(json.failure){alert(json.result);return;}
			alert("提交成功!");
			("#conBody").empty().loadPage($('#initPath').val()+'/view/es/planform/contract/contractListForAgency.jsp');
		});
	});
	$("#test").click(
			function(){
				
			}
	)

});
