var sendSperviseInvitation = {};

$("input[id=purcategory.name]").click(function(e){
	$.epsDialog({
        title:'选择品目',
        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=purcategory&className=ExpertRulePurchaseCategory',
        width: '500',
        height: '400',
        onOpen: function(){ },
        afterLoad: function(){ },
        onClose: function(){ }
    }); 
	})

sendSperviseInvitation.getRadioValue = function(eventId,returnValueId){//循环Radio值以数组方式回填到指定位置
	$("#"+eventId).find('input[type=radio]').click(function(){
		$("#"+eventId).find('input[type=radio][checked=true]').each(function(i,n){
			$('#'+returnValueId).val(n.value);
		})
	});
}

sendSperviseInvitation.getCheckBoxValue = function(eventId,returnValueId){//循环Checkbox值以数组方式回填到指定位置
	$("#"+eventId).find('input[type=checkbox]').click(function(){
		var checkedIds = new Array();
		$("#"+eventId).find('input[type=checkbox][checked=true]').each(function(i,n){
			checkedIds.push(n.value);
		})
		$('#'+returnValueId).val(checkedIds.toString());
	});
}

sendSperviseInvitation.getAllCheckBoxValue = function(eventId,returnValueId) {//循环Checkbox值以数组方式回填到指定位置全选
	var checkedIds = new Array();
$("#"+eventId).find('input[type=checkbox][checked=true]').each(function(i,n){
	checkedIds.push(n.value);
})
$('#'+returnValueId).val(checkedIds.toString());
}

sendSperviseInvitation.checkAll=function(btnId,checkName){//全选与反选
	$('#'+btnId).toggle(function(){
        $("input[name='"+checkName+"']").each(function(){
            $(this).attr('checked',true);
            if(btnId=='chooseAllCityCode') {
            	sendSperviseInvitation.getAllCheckBoxValue("city_codeArr_li","city_codeId");//区域
            }else if(btnId=='chooseAllExpertGroup') {
            	sendSperviseInvitation.getAllCheckBoxValue("expert_group_li","expert_groupId");//专家类型
            }else if(btnId=='chooseAllTopEduc') {
            	sendSperviseInvitation.getAllCheckBoxValue("top_educ_li","top_educId");//学历
            }
        });
        $('#'+btnId).attr('value','反选');
    },function(){
        $("input[name='"+checkName+"']").each(function(){
            $(this).attr('checked',false);
            if(btnId=='chooseAllCityCode') {
            	sendSperviseInvitation.getAllCheckBoxValue("city_codeArr_li","city_codeId");//区域
            }else if(btnId=='chooseAllExpertGroup') {
            	sendSperviseInvitation.getAllCheckBoxValue("expert_group_li","expert_groupId");//专家类型
            }else if(btnId=='chooseAllTopEduc') {
            	sendSperviseInvitation.getAllCheckBoxValue("top_educ_li","top_educId");//学历
            }
        });
        $('#'+btnId).attr('value','全选');
    });
}

sendSperviseInvitation.backFillCheckBoxValue=function(arrId,id){//将得到的数字拆分并判断选择复选框
	$.each(arrId.split('@'),function(i,n){
		$("#"+id+n).attr("checked",true);
	});
}

sendSperviseInvitation.getUnit = function(name,unitType){
	var unitIds = '';
	if (unitType=='00') {//判断是采购单位还是回避单位
		unitIds = $('#buyerNameIds_Ids').val();
	}else if (unitType=='01') {
		unitIds = $('#outBuyerIds_Id').val();
	}
	$.epsDialog({
        title:name,
        url:$("#initPath").val()+"/ExpertruleController.do?method=getUnit&isAJAX=false&unitType="+unitType+"&unitIds="+unitIds,
        width: 670,
        height: 500,
        isReload: false
	});
}

sendSperviseInvitation.saveOrSubmit = function(type){
	if(!$('#resultForm').valid()){alert('请正确填写表单!');return false;}
	var jsonObj = formToJsonObject('resultForm');
	var flagRoom = '00';
	$('input[name="roomArr"]:checked').each(function(){//判断是否有评审室
		flagRoom = '01';
		jsonObj.bidRoom=$(this).val();
	 });
	if (flagRoom=='00') {
		alert('请选择评审室！');
		return false;
	}
	
	if ($('#_applyDate').val()==undefined||$('#_applyDate').val()==null||$('#_applyDate').val()=='') {
		delete jsonObj.assembleTime;
	}
	$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=saveExpertRule&projectId='+$('#projectId').val(),jsonObj,function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		  } else {
			$("#myDesktop").click();
		}
	});
}

sendSperviseInvitation.checkTime=function(){
	if($("#_bid_starttime").val()>=$("#_bid_endtime").val()){
		alert('评审结束时间应大于评审开始时间！');
		return false;
	}
	if($('#openBidStartDate').val()>=$("#_bid_starttime").val()){
		alert("评审开始时间应大于开标时间！");
		return false;
	}
	return true;
}

$(document).ready(function(){

	$('#_applyDate').epsDatepicker({timeShow:true});
	$('#_bid_starttime').epsDatepicker({timeShow:true});
	$('#_bid_endtime').epsDatepicker({timeShow:true});
	
	sendSperviseInvitation.getCheckBoxValue("city_codeArr_li","city_codeId");//区域
	sendSperviseInvitation.getCheckBoxValue("expert_group_li","expert_groupId");//专家类型
	sendSperviseInvitation.getCheckBoxValue("top_educ_li","top_educId");//学历
	sendSperviseInvitation.getRadioValue("room_Arr_li","bidRoom_Id");//评审室
	//全选
	sendSperviseInvitation.checkAll("chooseAllCityCode","city_codeArr");//地域
	sendSperviseInvitation.checkAll("chooseAllExpertGroup","expert_groupArr");//专家类型
	sendSperviseInvitation.checkAll("chooseAllTopEduc","top_educArr");//学历
	//回填选中
	var city_codeId = $('#city_codeId').val();
	sendSperviseInvitation.backFillCheckBoxValue(city_codeId,'city_code_');//地域
	var expert_groupId = $('#expert_groupId').val();
	sendSperviseInvitation.backFillCheckBoxValue(expert_groupId,'expert_group_');//专家类型
	var top_educId = $('#top_educId').val();
	sendSperviseInvitation.backFillCheckBoxValue(top_educId,'top_educ_');//学历
	var bidRoom_Id = $('#bidRoom_Id').val();
	sendSperviseInvitation.backFillCheckBoxValue(bidRoom_Id,'bidRoom_');//评审室
	$("#amount").change(function(){
		var amount = $('#amount').val();
		if(amount==''||amount==null||amount==undefined){
			alert('正选人数不能为空');
			return false;
		}
	})
	
	$("#SaveExpertRule").click(function(){//保存专家规则
		if(!$('#resultForm').valid()){alert('请正确填写表单!');return;}
		if(sendSperviseInvitation.checkTime()){
			if(window.confirm("确认保存?")){
			$('#useStatusId').val('00');
			sendSperviseInvitation.saveOrSubmit('保存');
			}
		}
	});
	//打印
	$('#printExpertRule').click(function(){
		window.open($('#initPath').val()+'/ExpertruleController.do?method=toPrint&projectId='+$('#projectId').val());
	});
	$("#SubmitExpertRule").click(function(){//提交专家规则
		if(!$('#resultForm').valid()){alert('请正确填写表单!');return;}
		if ($('#isSubRuleId').val()=='no') {
			alert('请先创建抽取规则!');
			return false;
		}
		if(sendSperviseInvitation.checkTime()){
			if(window.confirm("确认提交?")){
			$('#useStatusId').val('01');
			var jsonObj = formToJsonObject('resultForm');
			if ($('#_applyDate').val()==undefined||$('#_applyDate').val()==null||$('#_applyDate').val()=='') {
				delete jsonObj.assembleTime;
			}
			$('input[name="roomArr"]:checked').each(function(){//给评标室赋值
				jsonObj.bidRoom=$(this).val();
			 });
				$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=createTakeProject&isAjax=true&takeExpertRuleId='+$('#takeExpertRuleId').val(),jsonObj,function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
						planTemplateTask.clickMethod($("#projectTaskId").val()+"");
						planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
					  } else {
						$("#myDesktop").click();
					}
				});
			}
		}
	});
	
	if ($('#useStatusId').val()=='00') {//判断如果保存过显示保存按钮和创建规则表单
		$('#subExpertruleId').removeAttr("style"); 
		$('#expertConditionId').empty().loadPage($('#initPath').val()+'/ExpertruleController.do?method=toExtractionCondition&takeExpertRuleId='+$('#takeExpertRuleId').val());
	}else if ($('#useStatusId').val()=='01') {
		$('#expertConditionId').empty().loadPage($('#initPath').val()+'/ExpertruleController.do?method=toExtractionCondition&disabled='+escape("disabled=true")+'&takeExpertRuleId='+$('#takeExpertRuleId').val());
		$("#resultForm").find('input,textarea,button').attr("disabled",true);
	}
	
	$("#createTakeCondition").click(function(){//创建抽取项目
		var amount = $('#amount').val();
		var endAge = $('#expert_nlEnd').val();
		var startAge = $('#expert_nlStart').val();
		if ($('input[name=purcategory.objId]').val()==null||$('input[name=purcategory.objId]').val()==''||$('input[name=purcategory.objId]').val()==undefined) {//品目是否为空
			alert('请选择品目!');
			return false;
		}else if (amount==''||amount==null||amount==undefined) {
			alert('正选人数不能为空');
			return false;
		}
		else if (startAge!=''&&endAge!=''&&startAge>=endAge) {//判断年龄
			alert('请正确填写年龄的起止时间!');
			return false;
		}else if (endAge!=''&&(startAge==''||startAge==null||startAge==undefined)) {
			$('#ageId').val('-'+endAge);
		}else if (startAge!=''&&(endAge==''||endAge==null||endAge==undefined)) {
			$('#ageId').val(startAge+'-');
		}else if (startAge!=''&&endAge!='') {
			$('#ageId').val(startAge+'-'+endAge);
		}
		$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=saveExpertRuleCondition',formToJsonObject('subExpertruleForm'),function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#projectTaskId") && $("#projectTaskId").val() != ""){
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		   	}
		});
		
	});
	
	$('#resetCondition').click(function(){//清空
		$('.purcategoryId').val('');//品目Id
		$('.siSearch').val('');//品目名称
		$('#expert_level_1').attr('checked',true);//抽取类型
		$('#specialty_year').val('');//专业工龄
		$('#amount').val('');//正选人数
		$('#sub_Amount').val('');//备选人数
		$('#expert_nlStart').val('');//起
		$('#expert_nlEnd').val('');//止
		$('#ageId').val('');//起-止
		$('#city_codeId').val('');//评审地域
		$('#expert_groupId').val('');//专家类型
		$('#top_educId').val('');//学历
		$('#takeExpertRuleItemId').val('');//学历
		$('input[type="checkbox"]').each(function(){
			$(this).attr('checked',false);
		 });
	});
})
