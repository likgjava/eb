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


// 初始化复选框触发事件
bidForm.initCheckBoxEvent = function(){
	$("#bidForm").find('input[type=checkbox]').click(function(){
		var checkedPackIds = new Array();
		$("#bidForm").find('input[type=checkbox][checked=true]').each(function(i,n){
			checkedPackIds.push(n.value);
		})
		if(checkedPackIds.length>0 && true == isShowFactor){
			var objId = $("#bidForm").find('input[id=objId]').val();
			$("#factorListInfo").empty().loadPage($("#initPath").val()+"/NegotiateResultController.do?method=loadFactorInfo&packIds="+checkedPackIds.toString()+"&bidId="+objId);
		}else{
			$("#factorListInfo").empty();
		}
	})
}
 
//判断时间是否在投标期限内
bidForm.isCanBid = function(){
	// 判断时间是否在投标期限内
	var nowDate = new Date().format("yyyy-MM-dd hh:mm:ss.0");
	var tenderStartTime = $("#tenderStartTime").val();
	var tenderEndTime = $("#tenderEndTime").val();
	if (nowDate>tenderEndTime) {
		$("#bidForm>h5>span").empty().append("<font color='red'>投标时间已过,不允许投标！</font>");
		// 禁用页面元素
		$("#bidForm").find('input,textarea,button').attr("disabled",true);
		return false;
	}else if (nowDate<tenderStartTime) {
		$("#bidForm>h5>span").empty().append("<font color='red'>投标时间还未到,不允许投标！</font>");
		// 禁用页面元素
		$("#bidForm").find('input,textarea,button').attr("disabled",true);
		return false;
	}
}

$(document).ready(function(){
	bidForm.isCanBid();
	//附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/ftp_attachment.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text()
	});
	
	if("true" == $("#isShowFactor").val()){
		isShowFactor = true;
	}
	bidForm.initCheckBoxEvent();
	var objId = $("#bidForm").find('input[id=objId]').val();
	if(undefined != objId && "" != objId){
		if(isShowFactor){
			$("#factorListInfo").empty().loadPage($("#initPath").val()+"/NegotiateResultController.do?method=loadFactorInfo&bidId="+objId);
		}
	}
	
	$('#bidForm').validate();
    $("#createTime").epsDatepicker();
    $("#modifyTime").epsDatepicker();

	//返回
	$('#bidReturn').click(function(){
		$('#bidFormView').empty().loadPage($('#initPath').val()+"/NegotiateResultController.do");
	});
	
	//提交
	$('#bidSave').click(function(){
		$("#isUploadFile").val("false");
		if($('#form_ftp'))$('#form_ftp').remove();//移除附件form否则validator会出错
		if(!$('#bidForm').valid()){
			alert('请正确填写表单!');
			//附件
			$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/ftp_attachment.jsp',{
				defineSelf:'attachRelaId',//存放关联id的属性名
				attachRelaId:$("#attachRelaId").text()
			});
			return false;
		}
		
		var checkeds = $("#bidForm").find('input[type=checkbox][checked=true]');
		if(undefined == checkeds || checkeds.length<1){
			alert("请选择投标包件！");
			return false;
		}else{
			var packIds = new Array();
			$.each(checkeds,function(i,n){
				packIds.push(n.value);
			})
			$("[name=PACK_IDS]").val(packIds.toString());
		}
		
		// 更新文件空状态
		$("#factorListInfo").find('input[type=file]').each(function(i,n){
			if(n.value == ""){
				$(n).next('input').val("false");
			}else{
				$(n).next('input').val("true");
			}
		})
		
		bidForm.saveBid();
	});
	
	bidForm.saveBid = function(){
		var length = $('#ftp_atta_display_ul').find('li').length;
		if (length==0) {
			alert("请上传投标文件!");
			//附件
			$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/ftp_attachment.jsp',{
				defineSelf:'attachRelaId',//存放关联id的属性名
				attachRelaId:$("#attachRelaId").text()
			});
			return false;
			
		}
		if(confirm('确定已核实谈判响应文件？')){
			$('#bidForm').ajaxSubmit({
				url:$('#initPath').val()+"/NegotiateResultController.do?method=saveOrUpdateBid",
				dataType:'json',
				success:function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					if($("#fromDiv").val() == 'yes'){
						$('#epsDialogCloseReload').click();
					}else{
						$("#tabform10").click();
					}
				},
				error:function(msg){
					alert(msg);
					if($("#fromDiv").val() == 'yes'){
						$('#epsDialogCloseNoReload').click();
					}
				}
			});
		}
	}
});
