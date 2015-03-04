var PutOnProjectRecord = {};

//初始化状态灯
PutOnProjectRecord.showEpsStatusLight = function(){
	var url2 = $('#initPath').val()+'/ProjectPlanController.do?method=getAllSecondStatusLightData';
	var data2 = {projectId:$("#projectId").val()};
	var setting2={ebuyMethodCN:$("#ebuyMethodCN").val()+""};
	$('#projectNav').empty().epsStatusLightOnlyView(url2,data2,setting2);
}

$(document).ready(function(){
	$("#projectInfoTabs").tabs();
	
	$('#modify_monitor_span2').hide();
	//收取左边菜单栏
	
	//初始化状态灯
	PutOnProjectRecord.showEpsStatusLight();
})
