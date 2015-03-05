var sysInfoConfigForm={};

$(document).ready(function(){
	sysInfoConfigForm.vform=$("#SysInfoConfigForm").validate();	
	
	$.getJSON("SysInfoConfigController.do?method=getSysInfo",function(json){
		jsonObjectToForm("SysInfoConfigForm",json.sysInfo);
	})
	
	
	//保存
	$("#submit").click(function(){
		if(sysInfoConfigForm.vform.checkForm()){
			$.getJSON($('#initPath').val()+'/SysInfoConfigController.do?method=setSysInfo', formToJsonObject("SysInfoConfigForm"), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				alert("保存成功！");
			});
		}else{
			sysInfoConfigForm.vform.showErrors();
		}
	})
})
