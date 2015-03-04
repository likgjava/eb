
var purchaseDocList={};
purchaseDocList.auditMethodName = '';//controller审核方法名


//查询条件过滤
purchaseDocList.before=function(){
	var option={
		'fileType':$('#fileType').val(),
		'useStatus':'01',
		'auditStatus':$('#auditStatus').val(),
		'userType':$('#userType').val()
	}
	$('#purchaseDocGrid').flexOptions({params:option});
	return true;
}

//加载数据成功之后调用的函数
purchaseDocList.success=function(){
	var auditStatus =$('#auditStatus').val();
	var useStatus = $('#useStatus').val();
	if(auditStatus=='00'&& useStatus=='00'){
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">提交</a></span>' : function(btn,rowId,obj){
				 btn.click(function(){
					 purchaseDocList.modify(rowId);
				 }).appendTo(obj);
			}
		});
	}else if(auditStatus=='00'){
	$("#purchaseDocGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">确认</a></span>' : function(btn,rowId,obj){
			 btn.click(function(){
				 purchaseDocList.auditByBuyer(rowId);
			 }).appendTo(obj);
		}
	});
	}else if(auditStatus=='04'){
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">修改</a></span>' : function(btn,rowId,obj){
				 btn.click(function(){
					 purchaseDocList.modify(rowId);
				 }).appendTo(obj);
			}
		});
	}else if(auditStatus=='06'){//终审
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">审核</a></span>' : function(btn,rowId,obj){
				 btn.click(function(){
					 purchaseDocList.audit(rowId);
				 }).appendTo(obj);
			},
			'<span><a href="#" class="abtn">优易端审核</a></span>' : function(btn,rowId,obj){
				btn.click(function(){//isFinal=true表示是否终审
					var jsonStr=$.ajax({url:$('#initPath').val()+'/PurchaseDocController.do?method=toOpenUeForAudit',data:{"docId":rowId,"isFinal":"true"}, async: false }).responseText
					var json = JSON.parse(jsonStr)
					window.open(json.protocal);
				}).appendTo(obj);
			}
		});
	}
	else if(auditStatus=='05'){//监察局审核
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">审核</a></span>' : function(btn,rowId,obj){
				 btn.click(function(){isFinal=true表示是否终审
					 purchaseDocList.auditForJCJ(rowId);
				 }).appendTo(obj);
			},
			'<span><a href="#" class="abtn">优易端审核</a></span>' : function(btn,rowId,obj){
				btn.click(function(){//监察局审核不是终审
					var jsonStr=$.ajax({url:$('#initPath').val()+'/PurchaseDocController.do?method=toOpenUeForAudit',data:{"docId":rowId,"isFinal":"false"}, async: false }).responseText
					var json = JSON.parse(jsonStr)
					window.open(json.protocal);
				}).appendTo(obj);
			}
		});
	}
}

//待确认的采购文件
purchaseDocList.auditByBuyer=function(rowId){
	if($("#fileType").val()==08){
		$('#conBody').loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toClarificationDocPageForBuyer&fromType=fromDesk&objId='+rowId);
	}else if($("#fileType").val()==07){
		$('#conBody').loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocConfig&fromType=fromDesk&objId='+rowId);
	}
}
//待修改的采购文件
purchaseDocList.modify=function(rowId){
	if($("#fileType").val()==08){
		$('#conBody').loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toUpdateClarificationDoc&fromType=fromDesk&objId='+rowId);
	}else if($("#fileType").val()==07){
		$('#conBody').loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toUpdatePurchaseDocPage&objId='+rowId+'&fromType='+$('#param_type').val());
	}
}
	
//采购办审核    
purchaseDocList.audit=function(rowId){
	if($("#fileType").val()==08){
		$('#conBody').loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toClarificationDocForOffice&fromType=fromDesk&objId='+rowId);
	}else if($("#fileType").val()==07){
		$('#conBody').loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocDeptAudit&fromType=fromDesk&objId='+rowId);
	}
	
}


//监察局审核
purchaseDocList.auditForJCJ = function(rowId){
	if($("#fileType").val()==08){
		$('#conBody').loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toClarificationDocPageForJCJ&fromType=fromDesk&objId='+rowId);
	}else if($("#fileType").val()==07){
		$('#conBody').loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocDeptAudit&fromType=fromDesk&objId='+rowId);
	}
}	
//批量审核
purchaseDocList.batchAudit = function(name,grid){
	var ids = $(grid).getSelects().split(",");
	if(ids == ""){
		alert("请至少选择一个文件!");
		return false;
	}else{
		var json = {};
		json['idsP']='ids';
		json['opinionP']='opinion';
		json['ids'] =ids;
		json['passAction'] = 'PurchaseDocController.do?method='+purchaseDocList.auditMethodName+';auditStatus=Y';
		json['noPassAction'] = 'PurchaseDocController.do?method='+purchaseDocList.auditMethodName+';auditStatus=N';
		$.epsDialog({
					title:'批量确认',
					url:$('#initPath').val()+'/view/srplatform/batch/audit.jsp?json='+obj2str(json), 
					width: 500,
					height: 200,
					afterLoad: function(){}, //加载完url后调用
					onClose: function(){$(grid).reload()} //关闭后调用
				});	
	}
}
$(document).ready(function(){
	var title = '文件列表';
	var auditStatus =$('#auditStatus').val();
	var fileType = $("#fileType").val();
	
	var buttons = [];
	var checkbox = false;
	if(auditStatus=='00'&&fileType=='08'){//批量确认澄清文件
		purchaseDocList.auditMethodName = 'saveConfirmClarificationDocAudit';
		title = '澄清文件列表';
		checkbox = false;
		/*
		 * buttons = [{name: '批量确认', bclass: 'enable', onpress : purchaseDocList.batchAudit}]
		 */
	}
	else if(auditStatus=='00'&&fileType=='07'){//批量确认招标文件
		purchaseDocList.auditMethodName = 'saveConfirmPruchaseDocAudit';
		title = '招标文件列表';
		checkbox = false;
		/*
		 * buttons = [{name: '批量确认', bclass: 'enable', onpress : purchaseDocList.batchAudit}]
		 */
	}
	else if(auditStatus=='06'&&fileType=='07'){//批量审核招标文件
		purchaseDocList.auditMethodName = 'saveConfirmPruchaseDocAudit';
		title = '招标文件列表';
		checkbox = false;
		/*
		 * buttons = [{name: '批量审核', bclass: 'enable', onpress : purchaseDocList.batchAudit}]
		 */
	}
	else if(auditStatus=='00'&&fileType=='09'){//批量确认询价文件
		purchaseDocList.auditMethodName = '';//待扩展
		title = '询价文件列表';
		checkbox = false;
		/*
		 * buttons = [{name: '批量确认', bclass: 'enable', onpress : purchaseDocList.batchAudit}]
		 */
	}
	else if(auditStatus=='00'&&fileType=='10'){//批量确认单一来源文件
		purchaseDocList.auditMethodName = '';//待扩展
		title = '单一来源文件列表';
		checkbox = false;
		/*
		 * buttons = [{name: '批量确认', bclass: 'enable', onpress : purchaseDocList.batchAudit}]
		 */
	}
	
	
	var grid = {
		minGridHeight:140,	
		height:277,
 		url: $('#initPath').val()+'/PurchaseDocController.do?method=searchPurchaseDocByQueryObject',
 		rp:10,
 		checkbox:checkbox,
 		searchZone:'purchaseDocSearchZone',
 		onSuccess:purchaseDocList.success,
 		onSubmit:purchaseDocList.before,
 		params:{},
  		title: title,//列表title
 		queryColumns:'',//列表查询属性
 		colModel : [
 			{display: '项目编号',hide:false, name : 'project.projCode', width : 100, sortable : true, align: 'center'},
 			{display: '招标名称', hide:false,name : 'project.projName', width : 180, sortable : true, align: 'left'},
 			{display: '采购方式', hide:false,name : 'project.ebuyMethodCN', width : 180, sortable : true, align: 'left'},
 			{display: '关键字', name : 'keyWord', width : 100, sortable : true, align: 'left'},
 			{display: '摘要', name : 'content', width : 100, sortable : true, align: 'left'}
 			],//列结构
 		buttons : buttons
 	}
	
	$("#purchaseDocGrid").flexigrid(grid)
			

})

