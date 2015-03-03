/*
 * 协议供货，确认合同详细页面
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var ContractPurchaserDraft={};
ContractPurchaserDraft.oTable;	

//查看订单详情
ContractPurchaserDraft.viewOrder=function(objId,orderNo){
	 //弹出订单详情页面
	 $.epsDialog({
	        title:'订单编号：'+orderNo,
			url:$('#initPath').val()+'/OrderController.do?method=toOrderForm&navigate=divDetail&objId='+objId
	  }); 
}

//计算合同总额
ContractPurchaserDraft.sumTotal=function(){
	var aoData=ContractPurchaserDraft.oTable.oSettings.aoData;
	var total = 0;
	for(var i=0; i<aoData.length; i++){
		//*1可以将字符串转换成数字
		total = eval(total*1 + aoData[i]._aData.goodsTotal*1);
	}
	return formatAmount(total,2);
}

//保存
ContractPurchaserDraft.saveContract=function(useStatus,buyerConfirmStatus,supplierConfirmStatus){
	if(!$('#contractForm').valid())return;
	
	var josnObj=formToJsonObject($("#contractForm")[0],"jsonToBean");
	
	josnObj.useStatus = useStatus;
	josnObj.buyerConfirmStatus = buyerConfirmStatus;
	josnObj.supplierConfirmStatus = supplierConfirmStatus;
	
	var aoData=ContractPurchaserDraft.oTable.oSettings.aoData;
	var orderIds = "";
	for(var i=0; i<aoData.length; i++){
		orderIds = orderIds + aoData[i]._aData.objId + ",";
	}
	orderIds = orderIds.substring(0,orderIds.length-1);
	$.ajax({
		url: $("#initPath").val()+"/AgContractController.do?method=saveContract",
		type:"POST",
		data:{contractStr:JSON.stringify(josnObj)},
		dataType:'json',
		success:function(msg){
			//将合同id保存到订单中
    		$.getJSON($('#initPath').val()+'/OrderController.do?method=saveOrderContract', {"orderIds":orderIds,"contractId":msg.contract.objId}, function(json){
    			if(msg.result){
    				alert(json.result,{inco:'info'});
    			}
    			if(msg.failure){
    				alert("更新订单失败!")
    				return;
    			}
    			if(buyerConfirmStatus == "00"){
    				alert("合同已经保存成功！");
    			}
    			else if(buyerConfirmStatus == "01"){
    				alert("已将该合同提交给"+$("#supplierName").html()+"，请等待其确认！");
    			}
    			$("button[name=historyBackBtn]").click();
    		});
		},
		error:function(msg){
			alert('操作失败');
		}
	})
}

$(document).ready(function(){
	//附件
	$('#contractFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=contractFile');
	
	//签订日期:时间控件，默认当前时间
	$("#contractSignedTime").epsDatepicker().val(new Date().Format('yyyy-MM-dd'));
	
	//生效日期:时间控件
	$("#contractBeginTime").epsDatepicker().val(new Date().Format('yyyy-MM-dd'));
	
	//失效日期:时间控件
	$("#contractEndTime").epsDatepicker().val(new Date().Format('yyyy-MM-dd'));
	
	// 订单商品详细
	ContractPurchaserDraft.oTable=$('#orderList').dataTable( {
		'bPaginate': false,//不分页
		'singleSelect':false,//(false表示可以多选)
		'checkbox':true,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,goodsQty,goodsTotal,createUser.emp.name,createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {
			ContractPurchaserDraft.oTable.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
			$("#total").text(goodsAPI.fnSumTotal(oSettings));
		},
		'fnRowCallback': function( nRow, aData, i) {//查询完毕每行的回填事件,i代表行序号	
			$(nRow).append("<td class=\"center\"><a href=\"javascript:void(0);\" onclick=\"ContractPurchaserDraft.viewOrder('"+aData.objId+"','"+aData.orderNo+"');return false;\">查看</a></td>");//添加操作连接
			return nRow;
		},
		'params':{"objId":$('#orderIds').val(),"objId_op":"in"},
		'sAjaxSource': $('#initPath').val()+'/OrderController.do?method=list'
	});

	//保存
	$("#save").click(function(){
		ContractPurchaserDraft.saveContract("00","00","00");//合同状态,采购人确认状态,供应商确认状态
	})
	
	//提交
	$("#submit").click(function(){
		if(window.confirm("确定要提交该合同吗?")){
			ContractPurchaserDraft.saveContract("00","01","00");//合同状态,采购人确认状态,供应商确认状态
		}
	})
	
	//增加订单
	$("#addOrder").click(function(){
		//弹出挑选订单的页面，条件：指定供应商，并且订单的创建人是当前人
		$.epsDialog({
			id:"chooseOrderDiv",
	        title:'订单信息',
	        url:$('#initPath').val()+"/view/agreement/order/order/order_choose_list.jsp?params="
			+escape("supplier.objId="+$("#supplierId").val()+
					"&tabsType=toSupdone&orgType=buyer"+
					"&objId_op=!in&objId="+$("#orderIds").val()),
	        afterLoad: function(){
	        	//确定
	        	$("#OK").click(function(){
	        		var select = $("#orderChooseList").dtSelects();
	        		if(select.length == 0){
	        			alert("请至少选中一个订单");return;
	        		}
	        		$("#orderIds").val($("#orderIds").val()+","+select);
	        		$("#CLOSE").click();
	        		$(ContractPurchaserDraft.oTable.dataTableSettings).attr('params',{"objId":$('#orderIds').val(),"objId_op":"in"});
	        		ContractPurchaserDraft.oTable.fnDraw();
	        	})
	        }
	  }); 
	})
	
	//删除订单
	$("#delOrder").click(function(){
		if(ContractPurchaserDraft.oTable.oSettings.aoData.length - $("#orderList").dtSelects().split(",").length == 0){
			alert("最少保留一个订单");return;
		}
		var select = $("#orderList").dtSelectArray();
		if(select.length == 0){
			alert("请至少选中一个订单");return;
		}
		$.each(select, function(i,n){
			$("#orderIds").val($("#orderIds").val().replace(","+n,"").replace(n+",","").replace(n,""));
		});
		$(ContractPurchaserDraft.oTable.dataTableSettings).attr('params',{"objId":$('#orderIds').val(),"objId_op":"in"});
		ContractPurchaserDraft.oTable.fnDraw();
	})
});
	