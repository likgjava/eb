<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" name="taskItemId" id="taskItemId" value="${param.taskItemId}">

<!-- Tab页 -->
<div id="epsTabs" class="">
  <div id="OrderProtaskListInfo1">
    <!-- 订单列表 -->
    <table class="frontTableList" id="OrderProtaskList1">
      <thead>
      	<tr>
          <th class="center">订单编号</th>
          <th>供应商</th>
          <th class="amount">总数量</th>
          <th class="money">总金额</th>
          <th class="date">创建时间</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
  
</div>


<script type="text/javascript">

//定义文件全局变量 处理方法名重复问题
var OrderProtaskList={};
$(document).ready(function(){
	//加载tabs
	$('#epsTabs').tabs(); 
	
	/**
	 */
	//已确认订单
	OrderProtaskList.oTable1=$('#OrderProtaskList1').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderItem.order.orderNo,orderItem.order.supplier.orgName,orderItem.goodsQty,orderItem.goodsTotal,orderItem.order.createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {	
			OrderProtaskList.oTable1.oSettings=oSettings;
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
			return nRow;
		},
		'params':{"protaskItem.objId":$("#taskItemId").val()},
		'searchZone':'purchaserOrderForm',
		'sAjaxSource': $('#initPath').val()+"/OrderProtaskController.do?method=list"
	});
	
});
	


</script>
        
        

 
