
var purchaseDocDetail={};
//查看建筑工程采购文件
function viewJZGCPurchaseDoc(){
	var url = $('#initPath').val() + "/PurchaseDocController.do?method=viewJZGCPurchaseDoc&purchaseDocId="+$("#purchaseDocId").val();
	window.open(url, "_blank");
}
$(document).ready(function(){
	$("#congrousFactor").hide();
		//下载招标文件下载记录
		purchaseDocDetail.oTable = $('#downRecordList').dataTable({
			'singleSelect' : true,
			'checkbox' : false,
			'queryColumns' : 'downOrg.orgName,downUser.emp.name,downDate,downIP',
			'fnInitComplete' : function(oSettings) {
			},
			'fnDrawCallback' : function(oSettings) {
				purchaseDocDetail.oTable.oSettings = oSettings;
			},
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				return nRow;
			},
			"params" : {"fileId":$('#purchaseDocId').val()},
			"sAjaxSource" : $('#initPath').val()+ "/ProcFileDownRecController.do?method=list"
		});
		
		
		$('a[name=downFile]').click(function(){
			window.open($('#initPath').val()+"/AttachmentController.do?method=downLoadFile&objId="+$(this).attr('id')+"&pKey=epp.projectAttaPath");
		})
		
		$("#downLoad").click(
					function(){
						window.location.href=''+$("#downAddr").text()+'';
					}
			)
			$("#downLoad2").click(
					function(){
						window.location.href=''+$("#downAddr2").text()+'';
					}
			)
			
		
	$('#congrousFactor').empty().loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toFactorTabShow&projectId="+$("#projectId").val());
	$('#congrousFactor').show();	
		
	purchaseDocDetail.delFile = function(){
		if (window.confirm('确认删除？')) {
			$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=delFile',{'fileId':$('#purchaseDocId').val()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			});
		}
	}
});
