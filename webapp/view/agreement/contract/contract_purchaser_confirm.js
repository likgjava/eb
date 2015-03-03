/*
 * 协议供货，确认合同详细页面
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var ContractPurchaserConfirm={};
ContractPurchaserConfirm.oTable;	

//查看订单详情
ContractPurchaserConfirm.viewOrder=function(objId,orderNo){
	 //弹出订单详情页面
	 $.epsDialog({
	        title:'订单编号：'+orderNo,
			url:$('#initPath').val()+'/OrderController.do?method=toOrderForm&navigate=divDetail&objId='+objId
	  }); 
}

//保存/提交合同
ContractPurchaserConfirm.saveContract=function(useStatus,buyerConfirmStatus,supplierConfirmStatus){
	if(!$('#contractForm').valid())return;
	
	var josnObj=formToJsonObject($("#contractForm")[0],"jsonToBean");
	josnObj.useStatus = useStatus;
	josnObj.buyerConfirmStatus = buyerConfirmStatus;
	josnObj.supplierConfirmStatus = supplierConfirmStatus;
	josnObj.buyerOption = $("textarea[name=buyerOption]").val();
	
	$(josnObj).find("maxSize").remove();
	
	$.ajax({
		url: $("#initPath").val()+"/AgContractController.do?method=saveContract",
		type:"POST",
		data:{contractStr:JSON.stringify(josnObj)},
		dataType:'json',
		success:function(json){
			if(json.result){
				alert(json.result,{inco:'info'});
			}
			if(json.failure){
				return;
			}
			if(buyerConfirmStatus == "00"){
				alert("合同已经保存成功！");
			}
			else if(buyerConfirmStatus == "01"){
				alert("已将该合同提交给"+$("span[id=supplier.orgName]").html()+"，请等待其确认！");
			}
			$('#conBody').loadPage($('#initPath').val()+"/view/agreement/contract/contract_purchaser_list.jsp");
		},
		error:function(msg){
			alert('操作失败');
		}
	})
}

$(document).ready(function(){
	//根据操作类型，显示相应的按钮
	if("edit" == $("#type").val()){
		$("#save").show();
		$("#submit").show();
	}

	//签订日期:时间控件
	$("#contractSignedTime").epsDatepicker();
	
	//生效日期:时间控件
	$("#contractBeginTime").epsDatepicker();
	
	//失效日期:时间控件
	$("#contractEndTime").epsDatepicker();
	
	// 订单商品详细
	ContractPurchaserConfirm.oTable=$('#orderList').dataTable( {
		'bPaginate': false,//不分页
		'singleSelect':false,//(false表示可以多选)
		'checkbox':true,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,goodsQty,goodsTotal,createUser.emp.name,createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {
			ContractPurchaserConfirm.oTable.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
			$("#total").text(goodsAPI.fnSumTotal(oSettings));
		},
		'fnRowCallback': function( nRow, aData, i) {//查询完毕每行的回填事件,i代表行序号	
			$(nRow).append("<td class=\"center\"><a href=\"javascript:void(0);\" onclick=\"ContractPurchaserConfirm.viewOrder('"+aData.objId+"','"+aData.orderNo+"');return false;\">查看</a></td>");//添加操作连接
			return nRow;
		},
		'sAjaxSource': $('#initPath').val()+'/OrderController.do?method=list&contract.objId='+$('#objId').val()
	});
	
	
	// 展示合同主要信息：编号、总金额、采购人、供应商
	if($('#objId').val()!=''){
		var queryColumns=[];
		$('#contractForm').find("ul").find("span,input,textarea,div").each(function(i,n){
			if($(n).attr("id"))
				queryColumns.push(n.id);     
		});	
		$.getJSON($('#initPath').val()+'/AgContractController.do?method=getObjectQuery',{objId:$('#objId').val(), queryColumns:queryColumns.toString()}, function(json){
			json2Object("contractForm",json.result[0]);
			jsonObjectToForm("contractForm",json.result[0]);
			if(json.result[0].contractFile)
				$('#contractFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=contractFile&attachRelaId='+json.result[0].contractFile);
			else
				$('#contractFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=contractFile');
		});
	}
	
	//返回
	$("#return").click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/agreement/contract/contract_purchaser_list.jsp");
	})
	
	//取消
	$("#cancel").click(function(){
		if(confirm('确定要取消该合同吗？')){
			$.ajax({
				url: $("#initPath").val()+"/AgContractController.do?method=remove",
				type:"POST",
				data:{objId:$("#objId").val()},
				dataType:'json',
				success:function(json){
					if(json.result){
						alert(json.result,{inco:'info'});
					}
					if(json.failure){
						return;
					}
					$('#conBody').loadPage($('#initPath').val()+"/view/agreement/contract/contract_purchaser_list.jsp");
				},
				error:function(msg){
					alert('操作失败');
				}
			})
		}
		return false;
	})
	
	//保存
	$("#save").click(function(){
		var supplierConfirmStatus = "00";
		if($("#supplierConfirmStatus").val()!=""){
			supplierConfirmStatus = $("#supplierConfirmStatus").val();
		}
		ContractPurchaserConfirm.saveContract("00","00",supplierConfirmStatus);//草稿状态
	})
	
	//提交
	$("#submit").click(function(){
		if(window.confirm("确定要提交该合同吗?")){
			ContractPurchaserConfirm.saveContract("00","01","00");//采购人已确认
		}
	})
	
	//增加订单
	$("#addOrder").click(function(){
		//弹出挑选订单的页面，条件：指定供应商，并且订单的创建人是当前人
		var aoData=ContractPurchaserConfirm.oTable.oSettings.aoData;
		var orderIds = "";
		for(var i=0; i<aoData.length; i++){
			orderIds = orderIds + aoData[i]._aData.objId + ",";
		}
		orderIds = orderIds.substring(0,orderIds.length-1);
		
		$.epsDialog({
			id:"chooseOrderDiv",
	        title:'订单信息',
	        url:$('#initPath').val()+"/view/agreement/order/order/order_choose_list.jsp?params="
					+escape("supplier.objId="+$("input[name=supplier.objId]").val()+
					"&createUser.objId="+PlatForm.user.objId+
					"&objId_op=!in&objId="+orderIds),
			width: '800',
			height: '550',
	        afterLoad: function(){ 
	        	//确定
	        	$("#OK").click(function(){
	        		var select = $("#orderChooseList").dtSelects();
	        		if(select.length == 0){
	        			alert("请至少选中一个订单");return;
	        		}
	        		
	        		//将合同id保存到订单中
	        		$.getJSON($('#initPath').val()+'/OrderController.do?method=saveOrderContract', {"orderIds":select,"contractId":$("#objId").val()}, function(json){
	        			if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
	        			ContractPurchaserConfirm.oTable.fnDraw();
	        			$("#CLOSE").click();
	        		});
	        	})
	        }
	  }); 
	})
	
	//删除订单
	$("#delOrder").click(function(){
		if(ContractPurchaserConfirm.oTable.oSettings.aoData.length - $("#orderList").dtSelects().split(",").length == 0){
			alert("最少保留一个订单");return;
		}
		var select = $("#orderList").dtSelects();
		if(select.length == 0){
			alert("请至少选中一个订单");return;
		}
		
		//将订单的合同id置为空
		$.getJSON($('#initPath').val()+'/OrderController.do?method=saveOrderContract', {"orderIds":select,"contractId":""}, function(json){
			if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
			ContractPurchaserConfirm.oTable.fnDraw();
		});
	})
	
	//打印合同
	$("#printContract").click(function(){
		//弹出打印合同页面
		 $.epsDialog({
		        title:'合同编号：'+$("#contractNo").val(),
				url:$('#initPath').val()+'/view/agreement/contract/contract_print_div.jsp',
				width: '800',
				height: '550'
		  }); 
	})
});
	
