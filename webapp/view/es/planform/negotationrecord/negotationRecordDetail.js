/*
 * 执行平台，采购申报书明细表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var negotationRecordDetail={};

//展示需求明细信息
negotationRecordDetail.showPreqEntryDetail = function(objId){
	$.epsDialog({
		id:"showPreqEntryDetail",
    	title:'需求条目信息',
    	url:$('#initPath').val()+'/view/es/planform/negotationrecord/lookPreqEntryInfo.jsp?objId='+objId,
    	width: '800',
    	height: '420',
    	isReload:false,
    	onClose: function(){ 	
       }
	});	
}


$(document).ready(function(){
	//附件
	$('#negRecordFile').loadPage($('#initPath').val()+'/view/srplatform/upload/attachmentAjaxFile.jsp',{
		defineSelf:'negRecordFile',//存放关联id的属性名
		attachRelaId:$("#negRecordFile").text(),
		readOnly:'yes'
	});
	//返回
	$('#closeBtn').click(function(){
		$('#epsDialogCloseReload').click();
	})
});
