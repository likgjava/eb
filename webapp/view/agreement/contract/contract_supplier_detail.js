/*
 * 协议供货，确认合同详细页面
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var ContractSupplierDetail={};
ContractSupplierDetail.oTable;	

//查看订单详情
ContractSupplierDetail.viewOrder=function(objId,orderNo){
	 //弹出订单详情页面
	 $.epsDialog({
	        title:'订单编号：'+orderNo,
			url:$('#initPath').val()+'/OrderController.do?method=toOrderForm&navigate=divDetail&objId='+objId
	  }); 
}

$(document).ready(function(){
	
	// 订单详细
	ContractSupplierDetail.oTable=$('#orderList').dataTable( {
		//'bPaginate': false,//不分页
		'singleSelect':false,//(false表示可以多选)
		'checkbox':true,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,goodsQty,goodsTotal,createUser.emp.name,createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {
			ContractSupplierDetail.oTable.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
			$("#total").text(goodsAPI.fnSumTotal(ContractSupplierDetail.oTable.oSettings));
		},
		'fnRowCallback': function( nRow, aData, i) {//查询完毕每行的回填事件,i代表行序号	
			$(nRow).append("<td class=\"center\"><a href=\"javascript:void(0);\" onclick=\"ContractSupplierDetail.viewOrder('"+aData.objId+"','"+aData.orderNo+"');return false;\">查看</a></td>");//添加操作连接
			return nRow;
		},
		'sAjaxSource': $('#initPath').val()+'/OrderController.do?method=list&contract.objId='+$('#objId').val()
	});
	
	//附件
	$('#contractFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=contractFile&isSelect=yes&attachRelaId='+$("input[name=contractFile]").val());

	//打印合同
	$("#printContract").click(function(){
		//弹出打印合同页面
		 $.epsDialog({
			 	id:'printContractDiv',
		        title:'合同编号：'+$("#contractNo").text(),
				url:$('#initPath').val()+'/view/agreement/contract/contract_print_div.jsp',
				width: '800',
				height: '550'
		  }); 
	})
});
	
