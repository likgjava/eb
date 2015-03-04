
var taskPlanDetailForm={};
//统计所有金额
taskPlanDetailForm.changeQuantity = function(){
	if(isNaN($("#superiorApp").val())||isNaN($("#localApp").val())||isNaN($("#ownerApp").val())||isNaN($("#otherApp").val())){
		$('#quantity_span').empty().append('');
		return;
	}
	$("#superiorApp2").html("");$("#localApp2").html("");$("#ownerApp2").html("");$("#otherApp2").html("");
	$("#quantity").val(Number($("#localApp").val())+Number($("#otherApp").val())+Number($("#ownerApp").val())+Number($("#superiorApp").val()));
	$('#quantity_span').empty().append($('#quantity').val());
}
$(document).ready(function(){
    if($('#planDetailId').val()!=''){
    	$.getJSON($('#initPath').val()+'/TaskPlanDetailController.do?method=createOrUpdate',{objId:$('#planDetailId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('taskPlanDetailForm', json.taskPlanDetail);
    	});
    }
    taskPlanDetailForm.formatNumber=function(number){
    	var number = ""+number;
    	var str= new Array();  
    	str = number.split(".");
    	number = str[0];
    	if(str.length >1){
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
    		if(str[1]==""){
    		    number = number + ".00";
    		}else{
    			number = number + "."+str[1];
    		}
    	}else{			
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
    	number = number + ".00";
    	}
    	return number;
	}
    taskPlanDetailForm.showmoney = function(obj){
	   var a = $("#"+obj).val();
	   var re = /^[0-9]+.?[0-9]*$/;   //判断字符串是否为数字      
	   //判断正整数 /^[1-9]+[0-9]*]*$/      
	   if (!re.test(a)){    
	    	 $("#"+obj+"2").html("<font color='red'>请输入数字</font>");    
	         $("#"+obj).focus();    
	         return false;    
	   }  
	   $("#"+obj+"2").html("<font color='red'>￥"+taskPlanDetailForm.formatNumber(a)+"</font>");
	   if($("#"+obj+"2").text()=='￥'){
		   $("#"+obj).html("*");
	   }
	}
	//返回
	$('#taskPlanDetailReturn').click(function(){
		$("#epsDialogCloseNoReload").click();
	});
	
	
	//提交
	$('#taskPlanDetailSave').click(function(){
		if(isNaN($("#superiorApp").val())||isNaN($("#localApp").val())||isNaN($("#ownerApp").val())||isNaN($("#otherApp").val())){
			alert("所填金额必须为数字!");
			return;
		}
		if(!$('#taskPlanDetailForm').valid()){alert('请正确填写表单!');return;}
		//将页面所有金额汇总到“总金额”栏
		taskPlanDetailForm.changeQuantity();
		var quantity = $('#quantity').val();
		if(quantity=='0'||quantity=='0.0'||quantity=='0.00'){
			alert('请填写资金!');
			return false;
		}
		$("#moneyDetail").val(Number($("#quantity").val())+Number($("#moneyDetail").val()));
		$("#taskPlanDetailSave").attr("disabled","disabled");
		$.getJSON($('#initPath').val()+'/TaskPlanDetailController.do?method=saveTaskPlanDetail&taskPlanId='+$("#objId").val(), formToJsonObject('taskPlanDetailForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$("#epsDialogCloseReload").click();
		});
	});
		
	$('#taskPlanDetailForm .money').blur(function(){
		if ('' == this.value) {
			this.value = '0.00';
		}
	})
	//选择批准文号
	$("#approvalNumber").autocomplete(
		$('#initPath').val() + '/TaskPlanController.do?method=getTaskPlanDetailByQueryObject&queryColumns=approvalNumber&taskPlanId='+$("#objId").val()+'', 
		{
			matchColumn:'approvalNumber',//作为查询显示, 被选中之后匹配的列
			extraParams:{},
			mustMatch: false,
			formatItem: function(data, i, total) {
				return data.approvalNumber;
			},
			formatMatch: function(data, i, total) {
				return data.approvalNumber;
			},
			formatResult: function(data) {
				return data.approvalNumber;
			}
		}
	).result(function(event,data,formatted){
			dateObj = data;
	});
});
