var BargainProjectModifyForm = {};

BargainProjectModifyForm.save = function(saveType){
	var est = $('#evalStartTime').val();
	var eet = $('#evalEndTime').val();
	
	var jsonObj = formToJsonObject('BargainProjectModifyForm');
	if(saveType == 'save') {
		jsonObj.auditStatus = '00';
		jsonObj.useStatus = '00';
	}else if(saveType == 'submit') {
		jsonObj.auditStatus = '01';
		jsonObj.useStatus = '01';
	}
	
	//轮次对象
	var bargainTurnJson = [];
	$('#barginTurn').find('li[class=item identity temp]').each(function(i,n){
		var index = i+1;
		//拼装json数组对象
		bargainTurnJson[i] = {};
		bargainTurnJson[i].objId=$(n).find('input[id=objId'+index+']').val();//ID
		bargainTurnJson[i].turnNo=$(n).find('input[id=turnNo'+index+']').val();//轮次号
		bargainTurnJson[i].startTime=$(n).find('input[id=startTime'+index+']').val();//开始时间
		bargainTurnJson[i].endTime=$(n).find('input[id=endTime'+index+']').val();//结束时间
		bargainTurnJson[i].project = {};
		bargainTurnJson[i].project.objId = $('#projId').val();
		
		var totalCutType = $(n).find('input:radio:checked').val();//降幅类型
		var totalCut = 0;
		if(totalCutType == '01') { //%
			totalCut = $(n).find('input[id=totalCut_'+index+']').val();//降幅
		} else { //元
			totalCut = $(n).find('input[id=totalCut'+index+']').val();//降幅
		}
		bargainTurnJson[i].totalCutType=totalCutType;
		bargainTurnJson[i].totalCut=totalCut;
	})
	
	jsonObj.turn = JSON.stringify(bargainTurnJson);
	var projId = $('#projId').val();
	//项目规则
	var ruleJson = [];
	var code = "";
	$('#projRuleDiv ul').find('.identity').not(':first').each(function(i,n){
		code = $(n).attr('code');
		ruleJson[i] = {};
		ruleJson[i].objId = $('input[id=id_'+code+']').val();
		ruleJson[i].project = {};
		ruleJson[i].project.objId = projId;
		ruleJson[i].sysItemId = $('input[id=itemid_'+code+']').val();
		ruleJson[i].sysItemName = $(n).attr('title');
		ruleJson[i].code = code;
		ruleJson[i].resValue = $('input[id='+code+']').val();
	})
	jsonObj.rule = JSON.stringify(ruleJson);
	$('button[id=save]').attr('disabled','disabled');
	$.getJSON($('#initPath').val()+'/BargainProjectController.do?method=createTurnAndRule',jsonObj, function(json){
		if(json.failure){if(json.result)alert(json.result);return;}
		alert('保存成功');
		$('button[id=save]').removeAttr('disabled');
		$('#project_info').loadPage($('#initPath').val()+"/BargainProjectController.do?method=toUpdateTurnAndRule&objId="+json.projId);
	})
}
//克隆轮次
BargainProjectModifyForm.cloneTurn = function() {
	var index = parseInt($('#barginTurn').find('li[class=item identity temp]:last').find('b').text()) + 1;
	var newUL = $('#tempUL').clone(true);
	var v = index - 1;
	if(v >= 5) {
		alert("已达到最大轮次数");
		return;
	}
	$('#barginTurn').find('span[id=delTurn'+v+']').addClass('hidden');
	$('#barginTurn').find('span[id=addTurn'+v+']').addClass('hidden');
	
	$(newUL).find('b').attr('id',index+1).text(index);
	//日历控件
	$(newUL).find('input[id=startTime1]').epsDatepicker({ timeShow:true });
	$(newUL).find('input[id=endTime1]').epsDatepicker({ timeShow:true });
	
	$(newUL).find('input[id=startTime1]').attr('id','startTime'+index).attr('name','startTime'+index);
	$(newUL).find('input[id=endTime1]').attr('id','endTime'+index).attr('name','endTime'+index);
	$(newUL).find('span[id=delTurn1]').attr('id','delTurn'+index);
	$(newUL).find('span[id=addTurn1]').attr('id','addTurn'+index);
	//轮次号
	$(newUL).find('input[id=turnNo1]').attr('id','turnNo'+index).attr('name','turnNo'+index).val(index);
	
	$(newUL).find('input[id=totalCutType]').attr('id','totalCutType'+index).attr('name','totalCutType'+index);
	$(newUL).find('input[id=totalCut]').attr('id','totalCut'+index).attr('name','totalCut'+index);
	$(newUL).find('input[id=totalCut_]').attr('id','totalCut_'+index).attr('name','totalCut_'+index);
	
	//追加表单
	$(newUL).removeClass("hidden").attr("id",$(newUL).attr("id")+index);
	
	$("#barginTurn").append(newUL);
}
//删除轮次
BargainProjectModifyForm.delTurn = function(i){
	if(window.confirm('确定删除?')) {
		if($('#barginTurn').find('input[id=objId'+i+']').val()) {
			$.getJSON($('#initPath').val()+'/BargainTurnController.do?method=remove',{"objId":$('#barginTurn').find('input[id=objId'+i+']').val()},function(json){
				if(json.failure){if(json.result)alert(json.result);return;}
			});
		}
		$('#barginTurn').find('li[class=item identity temp]:last').remove();
		if(i==2) {
			$('#barginTurn').find('li[class=item identity temp]:last').find('span[id^=addTurn]').removeClass('hidden');
		} else {
			$('#barginTurn').find('li[class=item identity temp]:last').find('span[id^=delTurn]').removeClass('hidden');
			$('#barginTurn').find('li[class=item identity temp]:last').find('span[id^=addTurn]').removeClass('hidden');
		}
	}
}
$(document).ready(function(){
	$("input[name=startTime]").epsDatepicker({ timeShow:true });
	$("input[name=endTime]").epsDatepicker({ timeShow:true });
	
	//删除轮次
	$('.tempa').click(function(){
		BargainProjectModifyForm.delTurn(parseInt($(this).parent().attr('id').replace('delTurn','')));
	})
	
	$(':radio').click(function(){
		var id = $(this).attr('id');
		var value = $(this).val();
		var title = $(this).attr('title');
		var name = $(this).attr('name');
		$('span[id='+name+'_span]').text(title);
		$('input[id='+name+']').val(value);
	})
	//保存
	$("#save").click(function(){
		var isValue = false;//是否填写
		var isRowValid = '';//同行起止时间比较
		var isColValid = '';//上下行起止时间比较
		$('#barginTurn ul').find('li[class=item identity temp]').each(function(i,n){
			if(!$(n).find('input[id^=startTime]').val() || !$(n).find('input[id^=endTime]').val()) {
				isValue = true;
			}
			if($(n).find('input[id^=startTime]').val() > $(n).find('input[id^=endTime]').val()) {
				isRowValid = isRowValid + (parseInt(i) + 1) + ",";
			}
			if($('input[id=endTime'+i+']').val() > $(n).find('input[id^=startTime]').val()) {
				isColValid = isColValid + parseInt(i) + "-" + (parseInt(i)+1) + ",";
			}
		})
		
		if(isValue) {
			alert('请填写轮次起止时间');
			return;
		}
		if(isRowValid) {
			alert('您第'+isRowValid.substring(0,isRowValid.length-1)+'轮的起止时间输入有误');
			return;
		}
		if(isColValid) {
			alert('您第'+isColValid.substring(0,isColValid.length-1)+'轮的结束时间和下一轮的开始时间输入有误');
			return;
		}
		
		//设置竞价开始时间和结束时间
		$('#evalStartTime').val($('#barginTurn').find('li[class=item identity temp]:first').find('input[id^=startTime]').val())
		$('#evalEndTime').val($('#barginTurn').find('li[class=item identity temp]:last').find('input[id^=endTime]').val())
		
		if($('#evalStartTime').val() >= $('#evalEndTime').val()) {
			alert("竞价开始时间不能大于竞价结束时间");
			return;
		}
		if(window.confirm('确认保存吗?')) {
			BargainProjectModifyForm.save('save');
		}
	});
});

