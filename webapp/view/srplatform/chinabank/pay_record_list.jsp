<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript">
var payRecordList={};  //定义文件全局变量 处理方法名重复问题
payRecordList.oTable;	

$(document).ready(function(){
	//加载列表
	payRecordList.oTable=$('#payRecordList').dataTable( {   
		'singleSelect':false,	
		'checkbox':true,		
		'queryColumns':'payNo,payAmount,payTime,payStatus,payBusinessType,payMode,payPersonName',
		'alias':'payNo,payAmount,payTime,payStatusCN,payBusinessTypeCN,payMode,payPersonName',
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			return nRow;
		},
		"sAjaxSource": $('#initPath').val()+"/PayRecordController.do?method=list"
	});
	
});
</script>

<div>
<table class="frontTableList" id="payRecordList">
      <thead>
		<tr>
			<th align="center">支付号</th>
			<th align="right">支付金额(元)</th>
			<th align="center">支付时间</th>
			<th align="center">支付状态</th>
			<th align="center">支付业务类型</th>
			<th align="center">支付卡种</th>
			<th align="center">支付人</th>
		</tr>
      </thead>
      <tbody>
      </tbody>
</table>
</div>