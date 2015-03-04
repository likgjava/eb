var project_agent_light ={};
project_agent_light.didi = function(){
	$('span','#projectNav').click(function(){
		if($(this).parent().attr('id')!=''){
			alert($(this).parent().attr('id'))
		}
	})
}

$(document).ready(function(){
	$('#spaceused').progressBar({showText:false});
	var url = $('#initPath').val()+'/ProjectPlanController.do?method=getStatusLightData';
	var data = {projectId:$("#projectId").val()};
	var setting={};
	/**
	 * @url 请求状态数据的url
 	 * @data 请求url附加参数
 	 * @setting 状态值与显示样式对照
	 */
	$('#projectNav').epsStatusLight(url,data,setting);
	setTimeout('project_agent_light.didi()',500);
	
	
})