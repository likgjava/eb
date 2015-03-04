<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<!-- 列表开始 -->
<div id="concernListInfo">	
	<table class="frontTableList" id="ConcernList">
		<thead>
			<tr>
				<th class="left">客户名称</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
<!-- 列表结束 -->
<div class="conOperation">
	<button id="OK" type="button" tabindex="18"><span>关闭</span></button>
	<button id="CLEAR" type="button" tabindex="19"><span>清空</span></button>
</div>

<script>
var ConcernList={};
ConcernList.oTable;	

//获取列表数据，如果列表没有，则创建，否则刷新数据
ConcernList.getTableList = function() {
	if(!ConcernList.oTable) {		
		ConcernList.oTable=$('#ConcernList').dataTable( {
			'searchZone':'concernListForm',
			'singleSelect':true,
			'checkbox':true,
			'queryColumns':'orgInfo.orgName',
			'alias':'',
			'hiddenColumns':'objId,belongsUser.objId,orgInfo.objId,orgInfo.supplierId',
			'fnInitComplete':function(oSettings) {},
			'fnDrawCallback':function(oSettings) {ConcernList.oTable.oSettings=oSettings;},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				 $(nRow).append('<td><a href="javascript:void(0);" name="select"><span>选择</span></a></td>');
				 $(nRow).find('a[name=select]').click(function(){
					 $("#selected_supplier_list").empty().append(aData['orgInfo.orgName']);
					 $("#selectedSupplierId").val(aData['orgInfo.objId']);
					 
					 $("#selectSupplierDiv").find('.epsDialogClose').trigger('click');
				 });
				return nRow;
			},
			'params':{'belongsUser.objId':$('#loginUser').find('a').attr('id'),"orgInfo.supplierId":null,"orgInfo.supplierId_op":"!="},
			"sAjaxSource": $('#initPath').val()+'/ConcernController.do?method=list'
		});
	}else{		
		ConcernList.oTable.fnDraw();
	}
}

//清空
$("#CLEAR").click(function(){
	$("#selected_supplier_list").empty();
	 $("#selectedSupplierId").val('');
	 $("#selectSupplierDiv").find('.epsDialogClose').trigger('click');
})

//关闭
$("#OK").click(function(){
	 $("#selectSupplierDiv").find('.epsDialogClose').trigger('click');
})

//刷新
ConcernList.reload = function(){
	ConcernList.oTable.fnDraw();
}

$(document).ready(function(){
	ConcernList.getTableList();
});
</script>
