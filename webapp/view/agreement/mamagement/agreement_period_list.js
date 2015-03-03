/*
 * 协议管理，协议期间页面
 * author: yucy
 * mail: yuchengyang@yeah.net
 */

//定义文件全局变量 处理方法名重复问题
var AgreementPeriodList={};
AgreementPeriodList.oTable;	

//新增
AgreementPeriodList.add=function(){
	$('#conBody').loadPage($('#initPath').val()+"/PeriodController.do?method=toNewPeriod");
}

//删除
AgreementPeriodList.del=function(){
	
	//获取选中的复选框的ID
	var selectIds=[];
	$('#agreementPeriodList tbody td').each(function(){
		var input=$(this).find('input:first');
		if(input.attr('checked'))
			selectIds.push(input.attr('value'));
	});
	
	if(selectIds.length<1){
		alert("请至少选择一行删除");
	}else {
		if(confirm("确定删除?")){
			AgreementPeriodList.delPeriod(selectIds.toString());
		}
	}
}

//删除
AgreementPeriodList.delPeriod = function(selectIds){
	$.getJSON($('#initPath').val()+'/PeriodController.do?method=delPeriod',{"periods":selectIds},function(json){
			alert(json.result);
			AgreementPeriodList.reload();
	})
}

//获取选中多行
AgreementPeriodList.selects=function(){
	alert($('#example').dtSelects());
}

//获取单行数据
AgreementPeriodList.getAllRow=function(){
	var selectId=$('#example').dtSelect();
	alert(obj2str($(AgreementPeriodList.oTable).dtGetRow(selectId)));
}

//获取所有行数据
AgreementPeriodList.getAllRows=function(){
	alert(obj2str($(AgreementPeriodList.oTable).dtGetRows()));
}

//点击刷新
AgreementPeriodList.reload=function(){
	AgreementPeriodList.oTable.fnDraw();
}

//更改选择下拉框
AgreementPeriodList.changeSelect=function(){
	$("#annual").change(function(){
		if("all"==$("#annual").val()){
			$(AgreementPeriodList.oTable.dataTableSettings).attr("params",{});
		}else{
			var annualstart =  $("#annual").val()+"-01-01";
			var annualend = parseInt($("#annual").val())+1+"-01-01";
			
			$(AgreementPeriodList.oTable.dataTableSettings).attr("params",
					{	
						"beginDate":annualstart,
						"beginDate_op":">=",
						"endDate":annualend,
						"endDate_op":"<="
					}
			);
		}
		AgreementPeriodList.oTable.fnDraw();
	});
}

$(document).ready(function(){
	
	//添加更改下拉框监听
	AgreementPeriodList.changeSelect();
	
	//加载列表
	AgreementPeriodList.oTable=$('#agreementPeriodList').dataTable( {
		'singleSelect':false,	
		'checkbox':true,
		'queryColumns':'periodName,beginDate,endDate',
		'hiddenColumns':'',
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {	//查询完毕每行的回填事件	
			$(nRow).append('<td><a href="javascript:void(0);" type="alink"><span>修改</span></a></td>')
			$(nRow).find('a').click(function(){
				$('#conBody').loadPage($('#initPath').val()+"/PeriodController.do?method=toNewPeriod&objId="+aData.objId);
			});
			return nRow;
		},
		"sPaginationType": "full_numbers",
		"bProcessing": true,
		"bServerSide": true,
		"sAjaxSource": $('#initPath').val()+"/PeriodController.do?method=list&order=beginDate"
	});
	
});
	
