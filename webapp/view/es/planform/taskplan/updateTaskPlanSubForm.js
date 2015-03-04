/*
 * 执行平台，采购申报书明细表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanSubForm={};
var a=0.0;
$(document).ready(function(){
	var a= $("#price").val();
	//申报书明细
	$('#taskPlanSubForm').validate();
	//需求明细
	$('#preqEntryForm').validate();
    if($('#planSubId').val()!=''){
    	//获取申报书条目信息
    	$.getJSON($('#initPath').val()+'/TaskPlanSubController.do?method=createOrUpdate',{objId:$('#planSubId').val(),includedProperties:'purchase'},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('taskPlanSubForm', json.taskPlanSub);
    		$("input[id=purchase.name]").val(json.taskPlanSub.purchaseName);
    	});
    	//根据申报书条目ID获取需求条目信息
    	$.getJSON($('#initPath').val()+'/PreqEntryController.do?method=getPreqEntryByTaskPlanSubId',{taskPlanSubId:$('#planSubId').val(),includedProperties:'purchase'},function(json){
			jsonObjectToForm('preqEntryForm', json.preqEntry);
			//加载附件
			if(json.preqEntry && json.preqEntry.attachRelaId){
				//$("#attachRelaId").empty().loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=attachRelaId&attachRelaId='+json.preqEntry.attachRelaId);
				//附件
				$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
					defineSelf:'attachRelaId',//存放关联id的属性名
					attachRelaId:json.preqEntry.attachRelaId
				});
			}else{
//				$("#attachRelaId").empty().loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=attachRelaId');
				//附件
				$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
					defineSelf:'attachRelaId',//存放关联id的属性名
					attachRelaId:''
				});
			}
    	});
    }else{
//    	$("#attachRelaId").empty().loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=attachRelaId');
    	//附件
		$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
			defineSelf:'attachRelaId',//存放关联id的属性名
			attachRelaId:''
		});
    }
    
    //选择品目
	 $("input[id=purchase.name]").click(function(e){
    	$.epsDialog({
    		id:"selectPurCategory",
            title:'选择品目',
            url:$('#initPath').val()+'/TreeController.do?method=toTree&property=purchase&dialogId=selectPurCategory&className=PurCategory',
            width: '500',
            height: '400',
            onOpen: function(){ },
            afterLoad: function(){ },
            onClose: function(){ }
        }); 
    	})
    
	//提交
	$('#taskPlanSubSave').click(function(){
		var taskPlanSub = formToJsonObject('taskPlanSubForm','json');
		var preqEntry = formToJsonObject('preqEntryForm','json');
		preqEntry.attachRelaId=$("input[name=attachRelaId]").val();
		taskPlanSub.purchaseName = $("input[id=purchase.name]").val();
		if(!$('#taskPlanSubForm').valid()){
			alert('请正确填写表单!');
			return false;
		}
		if(!$('#preqEntryForm').valid()){
			alert('请正确填写表单!');
			return false;
		}
		$("#taskPlanSubSave").attr("disabled","disabled");
		$.getJSON($('#initPath').val()+'/TaskPlanSubController.do?method=saveTaskPlanSub&taskPlanId='+$("#objId").val(),{"taskPlanSubString":JSON.stringify(taskPlanSub),"preqEntryString":JSON.stringify(preqEntry)}, function(json){
			if(json.result)alert(json.result);if(json.failure)return false;
			$("#moneySub").val(Number($("#totalPrice").val())-Number(a)+Number($("#moneySub").val()));
			$('#epsDialogCloseReload').click();
		});
	});
    taskPlanSubForm.formatNumber=function(number){
		var number = ""+number;
		var docPosition = number.indexOf(".")==-1?number.length:number.indexOf(".");
		
		var zs = number.substring(0,docPosition);
		var xs = number.substring(docPosition+1,number.length);
		if(zs.length > 3){
		   var mod = zs.length%3;
		   var put = (mod > 0 ? (zs.substring(0,mod)) : "");
		   var j=(zs.length-mod)/3;
		   for(i=0;i<j;i++){
		    if(mod==0&&i==0){
		     put+=zs.substring(mod+3*i,mod+3*i+3);
		    }else if(mod==0&&i!=0){
		     put+=","+zs.substring(mod+3*i,mod+3*i+3);
		    }else {
		     put+=","+zs.substring(mod+3*i,mod+3*i+3);
		    }
		   }
		   zs=put;
		}
		if(xs.length > 3){
		   var mod = xs.length%3;
		   var j=(xs.length-mod)/3;
		   var tem = "";
		   for(i=0;i<j;i++){
		     if(i==j-1&&mod==0){
		     tem+=xs.substring(3*i,3*i+3);
		    }else{
		     tem+=xs.substring(3*i,3*i+3)+",";
		    }   
		   }
		   xs = tem+xs.substring(xs.length-mod,xs.length)
		}
		if(docPosition+1>=number.length) return zs+".00";
		else return zs+"."+xs;
	}
    taskPlanSubForm.showmoney = function(){
        var a = $("#totalPrice").val();
        var re = /^[0-9]+.?[0-9]*$/;   //判断字符串是否为数字      
        //判断正整数 /^[1-9]+[0-9]*]*$/      
        if (!re.test(a)){    
         	 $("#totalPriceToMoney").html("<font color='red'>请输入数字</font>");    
              $("#totalPrice").focus();    
              return false;    
        }  
 	   $("#totalPriceToMoney").html("<font color='red'>￥"+taskPlanSubForm.formatNumber(a)+"</font>");
 	   if($("#totalPriceToMoney").text()=='￥'){
 		   $("#totalPriceToMoney").html("*");
 	   }
    }
    taskPlanSubForm.valid  = function(){
    	if($("#totalPrice").val()==""){
    		$("#totalPriceToMoney").html("");
    	}
    }
	//关闭
	$('#taskPlanSubClose').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
});
