/*
 * 协议供货.选择订单
 * author: liangxj
 * mail: liangxj@gpcsoft.com
 */
//定义文件全局变量 处理方法名重复问题
var chooseOrder={};
chooseOrder.oTable;	

chooseOrder.viewOrder=function(objId,orderNo){
	 //弹出订单详情页面
	 $.epsDialog({
	        title:'订单编号：'+orderNo,
	        id:"showOrderDetail",
			url:$('#initPath').val()+'/OrderController.do?method=toOrderForm&navigate=divDetail&dialogId=showOrderDetail&objId='+objId
	  }); 
}


$(document).ready(function(){
	var source = $('#initPath').val()+'/OrderController.do?method=list&contract.objId=null&contract.objId_op=is';
	if($("#_PARAMS").val()!=null&&$("#_PARAMS").val().length > 0){
		source += "&"+$("#_PARAMS").val();
	}
	// 订单商品详细
	chooseOrder.oTable=$('#orderChooseList').dataTable( {
		//'bPaginate': false,//不分页
		'singleSelect':false,//(false表示可以多选)
		'checkbox':true,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,goodsQty,goodsTotal,createUser.emp.name,createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {
		},
		'fnRowCallback': function( nRow, aData, i) {//查询完毕每行的回填事件,i代表行序号	
			$(nRow).append('<td class="operation"><a href="javascript:void(0);" onclick="chooseOrder.viewOrder(\''+aData.objId+'\',\''+aData.orderNo+'\');return false;" >查看</a></td>');//添加操作连接
			return nRow;
		},
		'sAjaxSource': source
	});

	//关闭
	$("#CLOSE").click(function(){
		$("#chooseOrderDiv").find('.epsDialogClose').trigger('click');
	})
});
	
