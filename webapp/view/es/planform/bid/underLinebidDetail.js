var bidForm={};
var isShowFactor = false;
/**
* 验证浮点数
*/
function checkTextDataForFLOAT(strValue)
{
var regTextFloat = /^(-)?(\d)*(\.)?(\d)*$/;
return regTextFloat.test(strValue);
}

/**
 * 获得当前时间函数
 */
 Date.prototype.format = function(format) //author: meizz
 {
 var o = {
 "M+" : this.getMonth()+1, //month
 "d+" : this.getDate(), //day
 "h+" : this.getHours(), //hour
 "m+" : this.getMinutes(), //minute
 "s+" : this.getSeconds(), //second
 "q+" : Math.floor((this.getMonth()+3)/3), //quarter
 "S" : this.getMilliseconds() //millisecond
 }
 if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
 (this.getFullYear()+"").substr(4 - RegExp.$1.length));
 for(var k in o)if(new RegExp("("+ k +")").test(format))
 format = format.replace(RegExp.$1,
 RegExp.$1.length==1 ? o[k] :
 ("00"+ o[k]).substr((""+ o[k]).length));
 return format;
 } 

//判断时间是否已经开标
bidForm.isCanBid = function(){
//	alert($('#openBidSTime').val());
	if ($('#openBidSTime').val()=='false'){
		$("#bidmessage").empty().append("<font color='red'>还未开标，不能录入！</font>");
	}
}
$(document).ready(function(){
	bidForm.isCanBid();
	//附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSel:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text(),
		readOnly:'yes'
	});
	var objId = $("#bidForm").find('input[id=objId]').val();
	if("true" == $("#isShowFactor").val()){
		$("#factorListInfo").empty().loadPage($("#initPath").val()+"/BidController.do?method=toBidFactorResponseDetail&bidId="+objId+'&projectId='+$('[name=project.objId]').val());
	}else{
		$('#factorListInfo').remove();
	}
	var checkedPackIds = new Array();
	$("#bidForm").find('input[type=checkbox][checked=true]').each(function(i,n){
		checkedPackIds.push(n.value);
	})
	$('#bid_items_table').empty().loadPage($("#initPath").val()+'/BidController.do?method=toBidItemsDetail&packIds='+checkedPackIds.toString()+'&bidId='+objId);
	
	
	
	$('#bidForm').validate();
    $("#createTime").epsDatepicker();
    $("#modifyTime").epsDatepicker();

	
});
