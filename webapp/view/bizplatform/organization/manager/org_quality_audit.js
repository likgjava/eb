/*
 * 供应商资质页面
 * created by yucy
 */
var orgQuality = {};

orgQuality.oTable;

/*加载供应商资质信息*/
orgQuality.loadSupplier = function(){
	if(null==orgQuality.oTable){
		orgQuality.oTable = $('#QualityList').dataTable( {   
			'params':{"auditStatus":"01","useStatus":"00"},
			'searchZone':'qualitySearchForm',
			'singleSelect':true,	
			'checkbox':false,		
			'queryColumns':'org.orgName,qualificationClass.qualityName,qualificationDefine.qualityName,auditStatus',
			'alias':'org.orgName,qualificationClass.qualityName,qualificationDefine.qualityName,auditStatusCN',
			'hiddenColumns':'useStatus',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				//添加操作
				$(nRow).append('<td class="operation"></td>');
				if(aData.auditStatus=='01'){
					//审核
					$(nRow).find('td:last').append('<a name="audit" title="审核"><span>审核</span></a>');
					$(nRow).find('a[name=audit]').click(function(){
						$.epsDialog({
					        title:'资质信息',
					        url:$('#initPath').val()+'/OrgQualityController.do?method=toAuditQualityView&objId='+aData.objId,
					        width: '800',
					        height:'400'
					    });
					})
				}else{
					//查看
					if(aData.auditStatus=='02'&&aData.useStatus=='01'){
						$(nRow).find('td:last').append('<a name="detail" title="查看"><span>查看</span></a>');
						$(nRow).find('a[name=detail]').click(function(){
							$.epsDialog({
						        title:'查看资质信息',
						        url:$('#initPath').val()+'/OrgQualityController.do?method=toQualityView&objId='+aData.objId,
						        width: '800',
						        height:'400'
						    });
						})
					}
				}
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/OrgQualityController.do?method=list"
		});
	}else{
		orgQuality.oTable.fnDraw();
	}
}

$(document).ready(function(){
	
	//初始化tabs
	$('#epsTabs').tabs({}); 
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				//新建
			$("#addbtnDiv").show();
			$(orgQuality.oTable.dataTableSettings).attr("params",{"auditStatus":"01","useStatus":"00"});
		}else if(ui.index==1){			//变更
			$("#addbtnDiv").hide();
			$(orgQuality.oTable.dataTableSettings).attr("params",{"auditStatus":"02","useStatus":"01"});
		}
		orgQuality.loadSupplier();
	});
	
	orgQuality.loadSupplier();
	
	//单击tab
	$("#loadSupplierBtn").click(function(){
		orgQuality.loadSupplier();
	})
	
	//点击搜索
	$("#qualitySearch").click(function(){
		orgQuality.oTable.fnDraw();
	})
});