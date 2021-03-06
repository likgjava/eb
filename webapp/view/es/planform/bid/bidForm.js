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
			$("#factorListInfo").empty().loadPage($("#initPath").val()+"/BidController.do?method=loadFactorInfo&packIds="+checkedPackIds.toString()+"&bidId="+objId+'&projectId='+$('[name=project.objId]').val());
			$('#bid_items_table').empty().loadPage($("#initPath").val()+'/BidController.do?method=toBidItemsView&packIds='+checkedPackIds.toString()+'&bidId='+objId);
		}else{
			$("#factorListInfo").empty();
			$('#bid_items_table').empty();
		}
	})
}
 
//判断时间是否在投标期限内
bidForm.isCanBid = function(){
	var nowDate = new Date().format("yyyy-MM-dd hh:mm:ss.0");
	var tenderStartTime = $("#tenderStartTime").val();
	var tenderEndTime = $("#tenderEndTime").val();
	if (nowDate>tenderEndTime) {
		$("#bidmessage").empty().append("<font color='red'>投标时间已过,不允许投标！</font>");
		$("#bidForm").find('input,textarea,button').attr("disabled",true);
	}else if (nowDate<tenderStartTime) {
		$("#bidmessage").empty().append("<font color='red'>投标时间还未到,不允许投标！</font>");
		$("#bidForm").find('input,textarea,button').attr("disabled",true);
	}
}




$(document).ready(function(){
	bidForm.isCanBid();
	
	//附件
	$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attachRelaId:$("#attachRelaId").text()
	});
	var objId = $("#bidForm").find('input[id=objId]').val();
	if("true" == $("#isShowFactor").val()){
		isShowFactor = true; 
		$("#factorListInfo").empty().loadPage($("#initPath").val()+"/BidController.do?method=loadFactorInfo&bidId="+objId+'&projectId='+$('[name=project.objId]').val());
	}else{
		$('#factorListInfo').remove();
	}
	bidForm.initCheckBoxEvent();
	
	// 项目没有分包加载需求明细
	if('' == $("#isDividePack").val() || $("#isDividePack").val()=='false'){
		$('#bid_items_table').empty().loadPage($("#initPath").val()+'/BidController.do?method=toBidItemsView&packIds='+$('[name=project.objId]').val()+'&bidId='+objId);
	}
	
	$('#bidForm').validate();
    $("#createTime").epsDatepicker();
    $("#modifyTime").epsDatepicker();

	//返回
	$('#bidReturn').click(function(){
		$('#bidFormView').empty().loadPage($('#initPath').val()+"/BidController.do");
	});
	
	//提交
	$('#bidSave').click(function(){
		var jsonObj = formToJsonObject('bidForm','jsonUtils');
		$('[name=bidItem]').val(JSON.stringify(jsonObj.bidItems));
		$("#isUploadFile").val("false");
		if($('#form_one'))$('#form_one').remove();//移除附件form否则validator会出错
		if(!$('#bidForm').valid()){
			alert('请正确填写表单!');
			//附件
			$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
				defineSelf:'attachRelaId',//存放关联id的属性名
				attachRelaId:$("[name=attachRelaId]").val()
			});
			return false;
		}
		
		if($("#isDividePack").val()=='true'){//项目已经分包
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
		}else { //项目没分包
			$("[name=PACK_IDS]").val($("input[name='project.objId']").val());
		}
		// 计算总报价
		var bidQuoteSum = 0;
		$('#bid_items_table input[type=text].money').each(function(i,n){
			bidQuoteSum += n.value * 1;
		})
		$('#bidQuoteSum').val(bidQuoteSum);
		
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
		var attrId = $("[name=attachRelaId]").val();
		if (undefined == attrId || null == attrId || "" == attrId) {
			alert("请上传投标文件!");
			//附件
			$('#attachRelaId').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
				defineSelf:'attachRelaId',//存放关联id的属性名
				attachRelaId:$("[name=attachRelaId]").val()
			});
			return false;
		}
		if(confirm('确定已核实投标文件？')){
			$('#bidForm').ajaxSubmit({
				url:$('#initPath').val()+"/BidController.do?method=saveBid&isAJAX=true&isAjaxSubmit=true",
				dataType:'json',
					success:function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					if($("#fromDiv").val() == 'yes'){
						$('#epsDialogCloseReload').click();
					}else{
						$("#tabform5").click();
					}
				},
				error:function(msg){
					alert(JSON.stringify(msg))
					alert(msg);
					if($("#fromDiv").val() == 'yes'){
						$('#epsDialogCloseNoReload').click();
					}
				}
			});
		}
	}
});
