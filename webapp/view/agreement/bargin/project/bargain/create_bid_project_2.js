var CreateProjectForm2 = {};

//克隆轮次
CreateProjectForm2.cloneTurn = function() {
	var trSize;
	if($('#CreateProjectForm2').find('div[id^=turnDiv]').length < 1) {
		trSize = 1;
	}else{
		trSize = $('#CreateProjectForm2').find('div[id^=turnDiv]').length+1;
	}
	if(trSize > 5) {
		alert("已到达最大轮次数");
		return;
	}
	var newDiv = $('#cloneDiv1').clone(true);
	$('div[id=turnDiv'+(trSize-1)+']').find('a[id=delTurn'+(trSize-1)+']').addClass('hidden');
	$(newDiv).attr('id','turnDiv' + trSize);
	//修改原始id和name
	$(newDiv).find('span[id=turnNo1]').text(trSize).attr('id','turnNo'+trSize);
	$(newDiv).find('a[id=delTurn1]').attr('id','delTurn'+trSize);
	$(newDiv).find('input[id=startTime1]').epsDatepicker({ timeShow:true }).attr('id','startTime'+trSize).attr('name','startTime'+trSize);
	$(newDiv).find('input[id=endTime1]').epsDatepicker({ timeShow:true }).attr('id','endTime'+trSize).attr('name','endTime'+trSize);
	
	$(newDiv).find('input[id=totalCutType]').attr('id','totalCutType'+trSize).attr('name','totalCutType'+trSize);
	$(newDiv).find('input[id=totalCut]').attr('id','totalCut'+trSize).attr('name','totalCut'+trSize);
	$(newDiv).find('input[id=totalCut_]').attr('id','totalCut_'+trSize).attr('name','totalCut_'+trSize);
	
	$(".bd_post_form").append(newDiv);
	//缓慢显示
	$('div[id=turnDiv'+trSize+']').show('slow');
}
//删除需求条目-修改轮次号
function removeTurn(i){
	if(window.confirm('确定删除?')) {
		$('div[id=turnDiv'+(i-1)+']').find('a[id=delTurn'+(i-1)+']').removeClass('hidden');
		$('#CreateProjectForm2').find('div[id=turnDiv'+i+']').remove();
	}
}

$(document).ready(function(){
	$("#CreateProjectForm2").validate();

	//初始化时间
	$("#evalStartTime").epsDatepicker({ timeShow:true });
	$("#evalEndTime").epsDatepicker({ timeShow:true });
	$("#startTime1").epsDatepicker({ timeShow:true });
	$("#endTime1").epsDatepicker({ timeShow:true });

	//规则选择
	$(':radio').click(function(){
		$('span[id='+$(this).attr('name')+'_span]').text($(this).attr('title'));
	})

	//删除需求条目
	$('a[id^=delTurn]').click(function(){
		removeTurn($(this).attr('id').replace('delTurn',''));
	})

	//保存-去设置轮次和规则
	$('#bd_post_submit_btn').click(function(){
		if(!$('#CreateProjectForm2').valid()){alert('请正确填写表单!');return;}
		if(window.confirm('确定保存?')) {
			CreateProjectForm2.save();
		}
	});
})

//保存
CreateProjectForm2.save = function(){
	/**
	 * 轮次的时间校验
	 */
	var isRowValid = '';//同行起止时间比较
	var isColValid = '';//上下行起止时间比较
	$('div[id^=turnDiv]').each(function(i,n){
		if($(n).find('input[id^=startTime]').val() > $(n).find('input[id^=endTime]').val()) {
			isRowValid = isRowValid + (parseInt(i) + 1) + ",";
		}
		if($('input[id=endTime'+i+']').val() > $(n).find('input[id^=startTime]').val()) {
			isColValid = isColValid + parseInt(i) + "-" + (parseInt(i)+1) + ",";
		}
	})
	if(isRowValid) {
		alert('您第'+isRowValid.substring(0,isRowValid.length-1)+'轮的起止时间输入有误');
		return;
	}
	if(isColValid) {
		alert('您第'+isColValid.substring(0,isColValid.length-1)+'轮的结束时间和下一轮的开始时间输入有误');
		return;
	}

	//设置竞价开始时间和竞价结束时间
	$('#evalStartTime').val($('div[id^=turnDiv]:first').find('input[id^=startTime]').val())
	$('#evalEndTime').val($('div[id^=turnDiv]:last').find('input[id^=endTime]').val())
	
	if($('#evalStartTime').val() >= $('#evalEndTime').val()) {
		alert("竞价开始时间不能大于竞价结束时间");
		return;
	}
	//所有条件验证完毕更换按钮样式
	$('#submitDiv').addClass('hidden')
	$('#submittingDiv').removeClass('hidden');
	//轮次
	var turnJson = [];
	$('div[id^=turnDiv]').each(function(i,n){
		var index = $(n).attr('id').replace('turnDiv','');
		//拼装json数组对象
		turnJson[i] = {};
		turnJson[i].turnNo=$(n).find('span[id=turnNo'+index+']').text();//轮次号
		turnJson[i].startTime=$(n).find('input[id=startTime'+index+']').val();//开始时间
		turnJson[i].endTime=$(n).find('input[id=endTime'+index+']').val();//结束时间
		turnJson[i].project = {};
		turnJson[i].project.objId = $('#projId').val();
		
		var totalCutType = $(n).find('input:radio:checked').val();//降幅类型
		var totalCut = 0;
		if(totalCutType == '01') { //%
			totalCut = $(n).find('input[id*=totalCut]').val();//降幅
		} else { //元
			totalCut = $(n).find('input[id*=totalCut_]').val();//降幅
		}
		turnJson[i].totalCutType=totalCutType;
		turnJson[i].totalCut=totalCut;
	})
	//规则
	var ruleJson = [];
	var code = "";
	$('#projRuleDiv ul').find('.identity').each(function(i,n){
		code = $(n).attr('code');
		ruleJson[i] = {};
		ruleJson[i].project = {};
		ruleJson[i].project.objId = $('#projId').val();
		ruleJson[i].sysItemId = $('input[id=itemid_'+code+']').val();
		ruleJson[i].sysItemName = $(n).attr('title');
		ruleJson[i].code = code;
		ruleJson[i].resValue = $(n).find(':radio:checked').val();
	})
	
	var params = {};
	params.projId = $('#projId').val();
	params.turn = JSON.stringify(turnJson);
	params.rule = JSON.stringify(ruleJson);
	params.evalStartTime=$('#evalStartTime').val();
	params.evalEndTime=$('#evalEndTime').val();
	
	$.getJSON($('#initPath').val()+'/BargainProjectController.do?method=createTurnAndRule',params, function(json){
		if(json.failure){if(json.result)alert(json.result);return;}
		
		//跳转至创建设置竞价规则页面
		window.location.href=$('#initPath').val()+'/BargainProjectController.do?method=toCreateBidProject_3&projId='+json.projId;
	})
}