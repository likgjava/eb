
//定义文件全局变量 处理方法名重复问题
var OperatorHistoryList={};
OperatorHistoryList.oTable;	


$(document).ready(function(){
	OperatorHistoryList.oTable=$('#operaotrHistoryList').dataTable( {
		'singleSelect':false,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'operator.name,operateDate,opinion',//指定要查询的列
		'hiddenColumns':'objId',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			$(nRow).find("td[name=operateDate]").addClass("center");
			return nRow;
		},
		"sPaginationType": "full_numbers",
		"bProcessing": true,
		"bServerSide": true,
		"sAjaxSource": $('#initPath').val()+"/view/agreement/order/common/operator.json"
	});
});
	
