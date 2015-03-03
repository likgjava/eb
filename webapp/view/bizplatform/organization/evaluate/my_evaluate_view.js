/*
 * 平台开发  我的评价页面
 * author: yucy
 * mail: yuchengyang@yeah.net
 */
var MyEvaluateForm={};
MyEvaluateForm.oTable;


//按参数加载评价列表
MyEvaluateForm.getEvaluateTable = function(params){
	//加载评价列表
	MyEvaluateForm.oTable = $('#evaluateList').dataTable({   
		'params':params,
		'singleSelect':true,	
		'checkbox':false,		
		'queryColumns':'leval,remark,rater.usName,projectName,evalTime',
		'hiddenColumns':'rateOrg.supplierId,rateOrg.buyerId,rateOrg.agencyId,isAonymous',
		'alias':'',
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			if(aData.leval=='0'){
				$(nRow).find("td:eq(0)").html('<span class="praise">好评</span>');
			}else if(aData.leval=='1'){
				$(nRow).find("td:eq(0)").html('<span class="mediumReview">中评</span>');
			}else{
				$(nRow).find("td:eq(0)").html('<span class="badReview">差评</span>');
			}
			//匿名
			if(aData.isAonymous=="1"){
				$(nRow).find("td:eq(2)").html('<span>匿名</span>');
			}
			return nRow;
		},
		"sAjaxSource": $('#initPath').val()+"/EvaluateController.do?method=list&org.objId="+$("#currentOrgId").val()
	});
}

$(document).ready(function(){
	//初始化tabs
	$('#epsTabs').tabs({});
	
	//统计数据
	$("#totalTable").find("table").find();
	
	//判断默认按加载第一个
	var id = $("#epsTabs").find("ul li").find("a:first").attr("id");
	
	if(id=="supplier"){
		MyEvaluateForm.getEvaluateTable({'rateOrg.supplierId':'null','rateOrg.supplierId_op':'!is'});
	}else if(id=="buyer"){
		MyEvaluateForm.getEvaluateTable({'rateOrg.buyerId':'null','rateOrg.buyerId_op':'!is'});
	}else{
		MyEvaluateForm.getEvaluateTable({'rateOrg.agencyId':'null','rateOrg.agencyId_op':'!is'});
	}
	
	//切换tab事件
	$("#epsTabs").find("ul li").find("a").click(function(){
		if(this.id=="supplier"){//来自供应商的评价
			$(MyEvaluateForm.oTable.dataTableSettings).attr("params",{'rateOrg.supplierId':'null','rateOrg.supplierId_op':'!is'});
		}else if(this.id=="buyer"){//来自采购人的评价
			$(MyEvaluateForm.oTable.dataTableSettings).attr("params",{'rateOrg.buyerId':'null','rateOrg.buyerId_op':'!is'});
		}else{//来自代理机构的评价
			$(MyEvaluateForm.oTable.dataTableSettings).attr("params",{'rateOrg.agencyId':'null','rateOrg.agencyId_op':'!is'});
		}
		MyEvaluateForm.oTable.fnDraw();
	})
	
});
