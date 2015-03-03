/*
 * 协议供货，任务书详细页面
 * author: 杨熙
 * mail: yangx@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var procurementTaskForm={};
procurementTaskForm.taskItem;	
procurementTaskForm.loadFound;
procurementTaskForm.loadorderList;

$(document).ready(function(){
	//加载任务单信息
	$.getJSON($('#initPath').val()+'/ProcurementtaskController.do?method=createOrUpdate',{"objId":$("#objId").val()},function(json){
		if(json.failure){if(json.result)alert(json.result);return;}
		json2Object('procurementForm',json.procurementtask)
   	});
	
	//加载tabs
	$('#epsTabs').tabs(); 
	
	// 任务书条目列表
	procurementTaskForm.taskItem=$('#procurementTaskItem').dataTable( {
		'params':{'procurementtask.objId':$("#objId").val()},
		'singleSelect':false,
		'checkbox':true,
		'queryColumns':'purCategory.categoryName,goodsTotal,goodsQty,finGoodSqty,finGoodTotal,finQty,finTotal',
		'hiddenColumns':'purCategory.objId',
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			//操作
			$(nRow).append(
			'<td><a class="buy" href="javascript:void(0);">选购</a>&nbsp;'
					+		
			'<a class="order" href="javascript:void(0);">相关订单</a></td>');

			$(nRow).find("a.buy").click(function(){
				$.getJSON($('#initPath').val()+'/ProtaskItemController.do?method=setIdInSession',{objId:aData["objId"]},function(json){
					if(json.failure){alert(json.result);return;}
					window.location.href = $('#initPath').val()+"/IndexViewController.do?method=getGoodsClassForSort";
				});
			});
			$(nRow).find("a.order").click(function(){
				//弹出相关订单页面
				 $.epsDialog({
					 	id:"orderItem",
				        title:'相关订单',
						url:$('#initPath').val()+'/view/agreement/order/task/task_order_list.jsp?protaskItemId='+aData.objId
				  }); 
			})
			return nRow;
		},
		"sPaginationType": "full_numbers",
		"bProcessing": true,
		"bServerSide": true,
		"sAjaxSource": $('#initPath').val()+"/ProtaskItemController.do?method=list&procurementtask.objId="+$("#objId").val()
	});
	
	//打印任务书
	$("#printTask").click(function(){
		//弹出商品选择页面
		 $.epsDialog({
		        title:'任务书编号：'+$("#taskNo").text(),
				url:$('#initPath').val()+'/view/order/task/task_print_div.jsp',
				width: '800',
				height: '400'
		  }); 
	})
	
	//创建竞价项目
	$("#createReverseProjectBtn").click(function(){
		var selects = procurementTaskForm.taskItem.dtSelects();
		if(selects.length == 0){
			alert("请至少选中一个任务书条目");return;
		}
		$('#conBody').loadPage($('#initPath').val()+"/BargainProjectController.do?method=toCreateBidProject_1&taskId="+$('#objId').val()+"&taskItemIds="+selects);
	})
	
	//返回
	$("#return").click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/agreement/order/task/procurement_task_list.jsp");
	})
	
});
	
