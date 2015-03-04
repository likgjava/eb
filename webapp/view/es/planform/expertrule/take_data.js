
$(document).ready(function(){
	
	$('#takeUnit').click(function(){
		$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=writeInfoForWebService&infoType=unit',function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("获取单位信息成功!");
		});
	});
	
	$('#takeCategory').click(function(){
		$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=writeInfoForWebService&infoType=category',function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("获取品目信息成功!");
		});
	});
	
	$('#takeCityCode').click(function(){
		$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=writeInfoForWebService&infoType=cityCode',function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("获取评审地域信息成功!");
		});
	});
	
	$('#takeExpertGroup').click(function(){
		$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=writeInfoForWebService&infoType=expertGroup',function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("获取专家类型信息成功!");
		});
	});
	
	$('#takeEdu').click(function(){
		$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=writeInfoForWebService&infoType=edu',function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("获取专家学历信息成功!");
		});
	});
	
	$('#takeRoom').click(function(){
		$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=writeInfoForWebService&infoType=room',function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("获取评审室信息成功!");
		});
	});

	$('#takeAll').click(function(){
		$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=writeInfoForWebService&infoType=all',function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("获取评审室信息成功!");
		});
	});
	
})