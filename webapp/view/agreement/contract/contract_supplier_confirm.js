/*
 * 协议供货，确认合同详细页面
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var ContractSupplierContirm={};
ContractSupplierContirm.oTable;	

//查看订单详情
ContractSupplierContirm.viewOrder=function(objId,orderNo){
	 //弹出订单详情页面
	 $.epsDialog({
	        title:'订单编号：'+orderNo,
			url:$('#initPath').val()+'/OrderController.do?method=toOrderForm&navigate=divDetail&objId='+objId
	  }); 
}

//保存/提交合同
ContractSupplierContirm.saveContract=function(useStatus,buyerConfirmStatus,supplierConfirmStatus){
	if(!$('#contractForm').valid())return;
	
	var josnObj=formToJsonObject($("#contractForm")[0],"jsonToBean");
	josnObj.useStatus = useStatus;
	josnObj.buyerConfirmStatus = buyerConfirmStatus;
	josnObj.supplierConfirmStatus = supplierConfirmStatus;
	josnObj.supplierOption = $("textarea[name=supplierOption]").val();
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
			if(supplierConfirmStatus == "00"){
				alert("合同已经保存成功！");
			}
			else if(supplierConfirmStatus == "01"){
				alert("已将该合同提交给"+$("span[id=buyer.orgName]").html()+"，请等待其确认！");
			}
			
			//返回
			$("button[name=historyBackBtn]").click();
			
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
	
	// 订单详细
	ContractSupplierContirm.oTable=$('#orderList').dataTable( {
		'bPaginate': false,//不分页
		'singleSelect':false,//(false表示可以多选)
		'checkbox':true,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,goodsQty,goodsTotal,createUser.emp.name,createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {},
		'fnDrawCallback':function(oSettings) {
			ContractSupplierContirm.oTable.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
			$("#total").text(goodsAPI.fnSumTotal(oSettings));
		},
		'fnRowCallback': function( nRow, aData, i) {//查询完毕每行的回填事件,i代表行序号	
			$(nRow).append("<td class=\"center\"><a href=\"javascript:void(0);\" onclick=\"ContractSupplierContirm.viewOrder('"+aData.objId+"','"+aData.orderNo+"');return false;\">查看</a></td>");//添加操作连接
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
			$("span[id=buyerOption]").html(json.result[0].buyerOption);//采购人意见
			$('#contractFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=contractFile&attachRelaId='+json.result[0].contractFile);
		});
	}
	
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
					$("button[name=historyBackBtn]").click();
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
		var buyerConfirmStatus = "00";
		if($("#buyerConfirmStatus").val()!=""){
			buyerConfirmStatus = $("#buyerConfirmStatus").val();
		}
		ContractSupplierContirm.saveContract("00",buyerConfirmStatus,"00");//合同状态,采购人确认状态,供应商确认状态
	})
	
	//提交
	$("#submit").click(function(){
		if(window.confirm("确定要提交该合同吗?")){
			ContractSupplierContirm.saveContract("00","00","01");//供应商已确认
		}
	})
	
	//增加订单
	$("#addOrder").click(function(){
		//不在已经选定的订单中
		var aoData=ContractSupplierContirm.oTable.oSettings.aoData;
		var orderIds = "";
		for(var i=0; i<aoData.length; i++){
			orderIds = orderIds + aoData[i]._aData.objId + ",";
		}
		orderIds = orderIds.substring(0,orderIds.length-1);
		
		//弹出挑选订单的页面，条件：指定采购人,供应商
		$.epsDialog({
			id:"chooseOrderDiv",
	        title:'订单信息',
	        url:$('#initPath').val()+"/view/agreement/order/order/order_choose_list.jsp?params="
				+escape("buyer.objId="+$("input[name=buyer.objId]").val()+
				+"supplier.objId="+$("input[name=supplier.objId]").val()+
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
	        		$.getJSON($('#initPath').val()+'/OrderController.do?method=saveOrderContract', {"orderIds":select,"contractId":$("#contractId").val()}, function(json){
	        			if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
	        			ContractSupplierContirm.oTable.fnDraw();
	        			$("#CLOSE").click();
	        		});
	        	})
	        }
	  }); 
	})
	
	//删除订单
	$("#delOrder").click(function(){
		if(ContractSupplierContirm.oTable.oSettings.aoData.length - $("#orderList").dtSelects().split(",").length == 0){
			alert("最少保留一个订单");return;
		}
		var select = $("#orderList").dtSelects();
		if(select.length == 0){
			alert("请至少选中一个订单");return;
		}
		
		//将订单的合同id置为空
		$.getJSON($('#initPath').val()+'/OrderController.do?method=saveOrderContract', {"orderIds":select,"contractId":""}, function(json){
			if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
			ContractSupplierContirm.oTable.fnDraw();
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
	
