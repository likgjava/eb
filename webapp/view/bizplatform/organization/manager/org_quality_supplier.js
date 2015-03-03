/*
 * 供应商资质列表(load用)
 * created by yucy
 */

var supplierQualityList={};


//loadlist
supplierQualityList.loadList = function(){
	if(supplierQualityList.oTable==null){
		//加载列表
		supplierQualityList.oTable=$('#QualityList').dataTable( {   
			'params':{"org.objId":$("#orgId").val(),"auditStatus":"00,03","auditStatus_op":"in","useStatus":"00"},
			'singleSelect':true,	
			'checkbox':true,		
			'queryColumns':'qualificationClass.qualityName,qualificationDefine.qualityName,auditStatus,createTime',
			'alias':'qualificationClass.qualityName,qualificationDefine.qualityName,auditStatusCN,createTime',
			'hiddenColumns':'useStatus',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				$(nRow).append('<td class="operation"></td>');
				//修改
				if(aData.useStatus=='00' && aData.auditStatus != '01'){
					$(nRow).find('td:last').append('<a name="modify" href="javascript:void(0);"><span>修改</span></a>');
					$(nRow).find('a[name=modify]').click(function(){
						$.epsDialog({
					        title:'资质信息',
					        url:$('#initPath').val()+'/OrgQualityController.do?method=toCreateOrUpdateOrgQuality&objId='+aData.objId,
					        width: '800',
					        height: '400',
					        onClose: function(){
								$('#selectDivList_tree').slideUp('fast',function(){$('#selectDivList_tree').remove();});//关闭树形下拉框
				        	}
					    });
					})
				}
				
				//删除
				if(aData.auditStatus=='00'||aData.auditStatus=='03'){
					$(nRow).find('td:last').append('&nbsp;<a name="delete" href="javascript:void(0);"><span>删除</span></a>');
					$(nRow).find('a[name=delete]').click(function(){
						if(confirm('确认删除!')){
							$.getJSON($('#initPath').val()+'/OrgQualityController.do?method=removeQualificationInfo',{"quolityIds":aData.objId},function(json){
								if(json.success){
									supplierQualityList.oTable.fnDraw();
								}else{
									alert("删除失败!");
								}
							})
						}
					})
				}
				
				//查看
				$(nRow).find('td:last').append('&nbsp;<a name="detail" title="查看"><span>查看</span></a>');
				$(nRow).find('a[name=detail]').click(function(){
					$.epsDialog({
				        title:'查看资质信息',
				        url:$('#initPath').val()+'/OrgQualityController.do?method=toQualityView&objId='+aData.objId,
				        width: '800',
				        height:'400'
				    });
				})
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/OrgQualityController.do?method=list"
		});
	}else{
		supplierQualityList.oTable.fnDraw();
	}
}


$(document).ready(function(){
	
	//初始化tabs
	$('#epsTabs').tabs({}); 
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				//新建
			$("#addQualityDiv").show();
			$(supplierQualityList.oTable.dataTableSettings).attr("params",{"org.objId":$("#orgId").val(),"auditStatus":"00,03","auditStatus_op":"in","useStatus":"00"});
		}else if(ui.index==1){			//待审核
			$("#addQualityDiv").hide();
			$(supplierQualityList.oTable.dataTableSettings).attr("params",{"org.objId":$("#orgId").val(),"auditStatus":"01","useStatus":"00"});
		}else if(ui.index==2){			//审核通过
			$("#addQualityDiv").hide();
		$(supplierQualityList.oTable.dataTableSettings).attr("params",{"org.objId":$("#orgId").val(),"auditStatus":"02","useStatus":"01"});
	}
		supplierQualityList.loadList();
	});
	
	//loadList
	supplierQualityList.loadList();
	
	//新增资质
	$("#addQuality").click(function(){
	    $.epsDialog({
	    	id:'updateQuality',
	        title:'资质信息',
	        url:$('#initPath').val()+'/OrgQualityController.do?method=toCreateOrUpdateOrgQuality',
	        width: '800',
	        height: '400',
	        onClose: function(){
	    		$('#selectDivList_tree').slideUp('fast',function(){$('#selectDivList_tree').remove();});//关闭树形下拉框
    		}
	    });
	})
});