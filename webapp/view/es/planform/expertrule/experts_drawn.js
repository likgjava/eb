var sendSperviseInvitation = {};

//选择品目
$("#purchaseName").autocomplete(
	$('#initPath').val() + '/ExpertruleController.do?method=getPurCategory', 
	{
		matchColumn:'categoryName',//作为查询显示, 被选中之后匹配的列
		extraParams:{},
		mustMatch: true,
		formatItem: function(data, i, total) {
			return data.categoryName;
		},
		formatMatch: function(data, i, total) {
			return data.categoryName;
		},
		formatResult: function(data) {
			return data.categoryName;
		}
	}
).result(function(event,data,formatted){
	if(data){
		$("#purchaseName").val(data.categoryName);//回填id
		$("input[id=expert_category_id]").val(data.objId);//回填id
	}
});

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
	$.epsDialog({
        title:name,
        url:$("#initPath").val()+"/ExpertruleController.do?method=getUnit&isAJAX=false&unitType="+unitType,
        width: 600,
        height: 400,
        isReload: false
	});
}

sendSperviseInvitation.saveOrSubmit = function(type){
	if(!$('#resultForm').valid()){alert('请正确填写表单!');return false;}
	var amount = $('#amount').val();
	if(amount==''||amount==null||amount==undefined){//是否为偶数
		alert('正选人数不能为空');
		return false;
	}else if(Number(amount)%2==0){
		alert('只能添奇数个！');
		this.value="";
		return false;
	}
	var flagRoom = '00';
	 $('input[name="roomArr"]:checked').each(function(){//判断是否有评审室
		 flagRoom = '01';
	 });
	if (flagRoom=='00') {
		alert('请选择评审室！');
		return false;
	}
	$('#ageId').val($('#expert_nlStart').val()+'-'+$('#expert_nlEnd').val());//拼写年龄
	var jsonObj = formToJsonObject('resultForm');
	if('01' == $('#useStatusId').val()){
		var workFlowTaskId = $('[id=auditTaskId]').val();
		if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
			workFlowTaskId = $('[id=auditTask_Id]').val();
		}
		jsonObj.auditStatus = 'Y';
		jsonObj.workFlowTaskId = workFlowTaskId;
		jsonObj.projectTaskId = $('#projectTaskId').val();
	}
	$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=saveExpertRule&projectId='+$('#projectId').val(),jsonObj,function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		alert(type+'成功');
		workFlowTaskId = $('[id=auditTaskId]').val();
		if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
			planTemplateTask.refresh($("#projectTaskId").val()+"");
		}else{
			$("#myDesktop").click();
		}
	});
}

sendSperviseInvitation.checkTime=function(){
	if($("#_bid_starttime").val()>=$("#_bid_endtime").val()){
	alert('评审结束时间应大于评审开始时间！');
	return false;
	}
	return true;
}

$(document).ready(function(){

	$('#_applyDate').epsDatepicker();
	$('#_bid_starttime').epsDatepicker();
	$('#_bid_endtime').epsDatepicker();
	
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
		}else if(Number(amount)%2==0){//是否为偶数
			alert('只能添奇数个！');
			this.value="";
			return false;
		}
	})
	
	$("#SaveExpertRule").click(function(){//保存专家规则
		if(sendSperviseInvitation.checkTime()){
			if(window.confirm("确认保存?")){
			$('#useStatusId').val('00');
			sendSperviseInvitation.saveOrSubmit('保存');
			}
		}
	});
	$("#SubmitExpertRule").click(function(){//提交专家规则
		if(sendSperviseInvitation.checkTime()){
			if(window.confirm("确认提交?")){
			$('#useStatusId').val('01');
			sendSperviseInvitation.saveOrSubmit('提交');
			}
		}
	});
	
	var age = $('#ageId').val();
	$("#expert_nlStart").val(age.split('-')[0]);
	$("#expert_nlEnd").val(age.split('-')[1]);
	
	$("#createTakeCondition").click(function(){//创建抽取项目
		if ($('#amount').val()==null||$('#amount').val()==''||$('#amount').val()==undefined) {//判断是否选择正选人数
			alert('请填写正选人数!');
			return false;
		}
		if ($('#expert_category_id').val()==null||$('#expert_category_id').val()==''||$('#expert_category_id').val()==undefined) {//判断是否有品目
			alert('请选择品目!');
			return false;
		}
		$('#countRow').val(Number($('#countRow').val())+1);//行数
		var purchaseName = $('#purchaseName').val();//品目名称
		var expert_category_id = $('#expert_category_id').val();//品目Id
		var html = '';
		html += '<tr id=tName'+$('#countRow').val()+'>';
			html += '<td style="text-align:center;width: 40px;">'+$('#countRow').val()+'</td>';
			html += '<td >评审品目名称：</td>';
			html += '<td style="text-align:center;width: 80px;"><button id="'+$('#countRow').val()+'" onClick="sendSperviseInvitation.delCondition(\''+$('#countRow').val()+'\')">删除</button></td>';
		html += '</tr>';
		if ($('#countRow').val()=='1') {//第一次
			$("#bodyCondition").empty().append(html);
		}else {
			$("#bodyCondition").append(html);
		}
	});
	
})
