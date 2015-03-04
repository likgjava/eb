
var projectInfoForm = {}

//选择业主单位支付时，填写金额
function inputFixAmount() {
	if($('#serviceFeePayType').val()=='02') {
		$('#fixAmountDiv').css("display","block");
		$('#fixAmount').val('');
	} else {
		$('#fixAmountDiv').css("display","none");
		$('#fixAmount').val('');
	}
}

$(document).ready(function(){
	projectInfoForm.formatNumber=function(number){
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
	
	//保证金输入方式
	$('#amount').click(function(){
		$('#bailPercent').val('');
		$('#bailPercent').attr('disabled','disabled');
		$('#bailPercent').removeClass('required');
		$('#bail').addClass('required');
		$('#bail').removeAttr('disabled');
	})
	//保证金输入方式
	$('#percent').click(function(){
		$('#bail').val('');
		$('#bail').attr('disabled','disabled');
		$('#bail').removeClass('required');
		$('#bailPercent').addClass('required');
		$('#bailPercent').removeAttr('disabled');
	})
	//分包的情况
	$('input[id^=amount_]').click(function(){
		var i = $(this).attr('id').replace('amount_','');
		$('input[id=bailPercent_'+i+']').val('');
		$('input[id=bailPercent_'+i+']').attr('disabled','disabled');
		$('input[id=bailPercent_'+i+']').removeClass('required');
		$('input[id=bail_'+i+']').addClass('required');
		$('input[id=bail_'+i+']').removeAttr('disabled');
	})
	$('input[id^=percent_]').click(function(){
		var i = $(this).attr('id').replace('percent_','');
		$('input[id=bail_'+i+']').val('');
		$('input[id=bail_'+i+']').attr('disabled','disabled');
		$('input[id=bail_'+i+']').removeClass('required');
		$('input[id=bailPercent_'+i+']').addClass('required');
		$('input[id=bailPercent_'+i+']').removeAttr('disabled');
	})
	
	projectInfoForm.insertMoney=function(id){
		   var a = $("#"+id+"").val();
		   if(a.indexOf('.')!=-1){ $("#"+id+"view").html("<font color='red'>请输入整数</font>");  $("#"+id+"").val("");return false;  }
	       var re = /^[0-9]+.?[0-9]*$/;   //判断字符串是否为数字      
	       //判断正整数 /^[1-9]+[0-9]*]*$/      
	       if (!re.test(a)){    
	        	 $("#"+id+"view").html("<font color='red'>请输入数字</font>");    
	             $("#"+id+"").focus();    
	             return false;    
	       }  
		   $("#"+id+"view").html("<font color='red'>￥"+projectInfoForm.formatNumber(a)+".00</font>");
	}
	projectInfoForm.showDetail=function(objId){
		$.epsDialog({
	        title:'查看申报书信息',
	        url:$('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+objId+'&type=selectAgent',
	        width: '800',
	        height: '600',
	 	    isReload:true
				});
	}
	$("#tenderForm").validate();
	projectInfoForm.timeChange = function(){//投标结束时间改变
		if ($('#tenderEndTime').val()) {
			$('#openBidStartDate').val($('#tenderEndTime').val());
		}
	}
	$("#signUpSTime").epsDatepicker({ timeShow:true});//报名开始时间
	$("#signUpETime").epsDatepicker({ timeShow:true});//报名结束时间
	$("#tenderStartTime").epsDatepicker({ timeShow:true});//投标开始时间
	$("#tenderEndTime").epsDatepicker({ timeShow:true});//投标结束时间
	$("#openBidStartDate").epsDatepicker({ timeShow:true});//开标开始时间
	$("#evalStartTime").epsDatepicker({ timeShow:true});//评标开始时间
	//对时间进行校验
	projectInfoForm.checkTime=function(){
		if($("#signUpSTime").val()>=$("#signUpETime").val()){alert('报名结束时间应大于报名开始时间！');return false;}
		if($("#signUpETime").val()>=$("#tenderStartTime").val()){alert('投标开始时间应大于报名结束时间！');return false;}
		if($("#tenderStartTime").val()>=$("#tenderEndTime").val()){alert('投标结束时间应大于投标开始时间！');return false;}
		if($("#tenderEndTime").val()>$("#openBidStartDate").val()){alert('开标时间应大于等于投标结束时间！');return false;}
		if($("#openBidStartDate").val()>$("#evalStartTime").val()){alert('评标开始时间应大于等于开标时间！');return false;}
		return true;
	}
	if ($('#openBidStartDate').val()==null||$('#openBidStartDate').val()==undefined||$('#openBidStartDate').val()=="") {
		$('#openBidStartDate').val($('#tenderEndTime').val());
	}
	$('#projectSave').click(function(){
		var str = "";
		if($("#projectType").val()!=04) {
			var totalPriceId = 0 ;
			$("td[id='totalPriceId']").each(function(i,n){
				totalPriceId = +parseInt($(this).text());
			});
			if(parseInt($('#purDocPrice').val())>totalPriceId){
				str = "录入招标文件的价格大于预算数额,";
			}
		}
		if(!$('#tenderForm').valid()){alert('请正确填写表单!');return;}
		if (projectInfoForm.checkTime()) {//验证时间
			if(!$('#subProjectForm').valid()){alert('请正确填写表单!');return;}
			if(confirm(str+"确认提交？")){
				var saveObj = new Array();
				if ($('#subProjectLength').val()!=null&&$('#subProjectLength').val()!=undefined&&$('#subProjectLength').val()>0) {
					var jsonObj = formToJsonObject('subProjectForm','jsonUtils');
					$.each(jsonObj.subProject,function(i,n){
						if(n != undefined && null != n){
							saveObj.push(n);
						}
					});
				}
				var tenderForm = formToJsonObject('tenderForm');
				$.getJSON($('#initPath').val()+'/ProjectController.do?method=saveInputTenderInfo',{"project":JSON.stringify(tenderForm),"subProjects":JSON.stringify(saveObj)}, function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
						planTemplateTask.clickMethod($("#projectTaskId").val()+"");
						planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
					}else {
						$("#myDesktop").click();
					}
				});
			}
		}
	});
	$("input").blur(function(){
		$('#tenderForm').valid();
		$('#subProjectForm').valid();
	});
	$("input").change(function(){
		$('#tenderForm').valid();
		$('#subProjectForm').valid();
	});
});