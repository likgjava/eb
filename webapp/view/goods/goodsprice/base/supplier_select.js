/*
 * 版权信息： 政采软件版权所有，未经珠海市政采软件技术有限公司的书面同意，不得拷贝，传播本文件及其相关信息的任何内容。
 * 项目：     政府采购超源业务平台
 * JDK版本：  1.5
 * 版本：     3.0
 * 说明：     本文件包含供应商列表的代码。
 * 修订历史：
 * 序号：     1
 * 日期：     2010-03-25
 * 修改人：   lianggt   
 * 修改说明：（为什么修改，修改了什么）
 */
var supplierList={};

//查询条件过滤
supplierList.before=function(){
    var option={
    	//"supplierId_op":"!is",
    	//"supplierId":null
    	//"supplier.status_op":"in",
        //"supplier.status":"2,3,4"
        orgType:"SUPPLIER",
        status:"3"
    }
    $('#supplierGrid').flexOptions({params:option});
    return true;
}

$('#saveItemBtn').click(function() {
	var srcBtn = $('#srcBtn').val();
	var r = $('#supplierGrid').getRowById($('#supplierGrid').getSelect());
	// 判断是选择代理商还是维护商
	if (srcBtn == "provideSupplierSelectBtn") {
		$('#provideSupplierId').val(r["orgInfoId"]);
		$('#provideSupplierName').val(r["orgName"]);
	}
	if (srcBtn == "modifySupplierSelectBtn") {
		$('#modifySupplierId').val(r["orgInfoId"]);
		$('#modifySupplierName').val(r["orgName"]);
	}
	$('#epsDialogClose').click();
});

/* 关闭窗口 */
$('#cancelItemBtn').click(function() {
	$('#epsDialogClose').click();
});

//加载数据成功之后调用的函数
supplierList.success=function(){

}


$(document).ready(function(){

});