$(document).ready(function(){
	//获得页面显示
	$.getJSON($("#initPath").val()+'/ProjectScheduleController.do?method=getProjectSchedule&objId='+$("#objId").val(),function(json){
		//alert(obj2str(json.projectSchedule));
		jsonObjectToForm("ProjectScheduleForm", json.projectSchedule);
		});
	//日期控件
	$("#signUpSTime").epsDatepicker();
	$("#signUpETime").epsDatepicker();
	$("#sellBidDocSTime").epsDatepicker();
	$("#sellBidDocETime").epsDatepicker();
	$("#tenderStartTime").epsDatepicker();
	$("#tenderEndTime").epsDatepicker();
	$("#openBidTime").epsDatepicker();
	
	//时间校验及提交
	$("#submitBtn").click(function(){
		if($("#signUpSTime").val()=='')
		{
			alert('"报名开始时间"为必填项');
		} 
		else if($("#signUpETime").val()=='')
		{
			alert('"报名结束时间"为必填项');
		}	
		else if($("#sellBidDocSTime").val()=='')
		{
			alert('"购买标书开始时间"为必填项');
		}
		else if($("#sellBidDocETime").val()=='')
		{
			alert('"购买标书结束时间"为必填项');
		}
		else if($("#tenderStartTime").val()=='')
		{
			alert('"投标开始时间"为必填项');
		}
		else if($("#tenderEndTime").val()=='')
		{
			alert('"投标结束时间"为必填项');
		}
		else if($("#openBidTime").val()=='')
		{
			alert('"开标时间"为必填项');
		}
		else if (window.confirm('确定要保存当前信息吗？')){
			$.getJSON($('#initPath').val()+'/ProjectScheduleController.do?method=saveOrUpdateProjectSchedule',formToJsonObject('ProjectScheduleForm'),function(json){
				   if(json.result)alert(json.result);if(json.failure)return;
				    
						});	
				}
		})
		
})

