$(document).ready(function(){
	var url = $('#initPath').val()+'/view/srplatform/wflow/status_light.txt';
	var data = {};
	var setting={};
	/**
	 * @url 请求状态数据的url
 	 * @data 请求url附加参数
 	 * @setting 状态值与显示样式对照
	 */
	$('#projectNav').epsStatusLight(url,data,setting);
});
