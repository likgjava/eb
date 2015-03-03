/*
 * 协议供货，确认合同详细页面
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var ContractSupplierView={};
ContractSupplierView.oTable;	

//查看订单详情
ContractSupplierView.viewOrder=function(objId,orderNo){
	 //弹出订单详情页面
	 $.epsDialog({
	        title:'订单编号：'+orderNo,
			url:$('#initPath').val()+'/OrderController.do?method=toOrderForm&navigate=divDetail&objId='+objId
	  }); 
}

//申请作废/确认作废/取消作废合同
ContractSupplierView.invalidContract=function(useStatus,buyerConfirmStatus,supplierConfirmStatus){
	var josnObj={};
	josnObj.objId = $("#objId").val();
	josnObj.useStatus = useStatus;
	josnObj.buyerConfirmStatus = buyerConfirmStatus;
	josnObj.supplierConfirmStatus = supplierConfirmStatus;
	josnObj.supplierOption = $("textarea[name=supplierOption]").val();

	$.ajax({
		url: $("#initPath").val()+"/AgContractController.do?method=invalidContract",
		type:"POST",
		data:{contractStr:JSON.stringify(josnObj),"sureRole":"supplier"},
		dataType:'json',
		success:function(json){
			if(json.failure){
				return;
			}
			if(buyerConfirmStatus == "00"){
				alert("已将该作废申请提交"+$("span[id=buyer.orgName]").text()+"，请等待其确认！");
			}
			else if(useStatus == "02"){
				alert("该合同从即日起已经作废！");
			}else{
				alert("已取消作废申请，该合同仍然有效！");
			}
			$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractSupplierList");
		},
		error:function(msg){
			alert('操作失败');
		}
	})
}

//确认/退回合同
ContractSupplierView.sureContract=function(useStatus,buyerConfirmStatus,supplierConfirmStatus){
	var josnObj={};
	josnObj.objId = $("#objId").val();
	josnObj.useStatus = useStatus;
	josnObj.buyerConfirmStatus = buyerConfirmStatus;
	josnObj.supplierConfirmStatus = supplierConfirmStatus;
	josnObj.supplierOption = $("textarea[name=supplierOption]").val();
	$.ajax({
		url: $("#initPath").val()+"/AgContractController.do?method=auditContract",
		type:"POST",
		data:{contractStr:JSON.stringify(josnObj),"sureRole":"supplier"},
		dataType:'json',
		success:function(json){
			if(json.result){
				alert(json.result,{inco:'info'});
			}
			if(json.failure){
				return;
			}
			if(supplierConfirmStatus == "01"){
				alert("合同已经确认，从"+$("#contractBeginTime").text()+"开始生效！");
			}
			else if(supplierConfirmStatus == "02"){
				alert("已将该合同退回给"+$("span[id=buyer.orgName]").text()+"，请等待其确认！");
			}
			$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractSupplierList");
		},
		error:function(msg){
			alert('操作失败');
		}
	})
}

//获得按钮操作
ContractSupplierView.getOperationBtn = function(){
	var ifstop = $("#useStatus").val()=="01"&&$("#buyerConfirmStatus").val()=="01"&&$("#supplierConfirmStatus").val()=="00";
	//根据操作类型，显示相应的按钮
	if("view" ==$("#type").val()){
		$("#invalid").show();
	}else if("sure" == $("#type").val()&&!ifstop){
		$("#sure").show();$("#back").show();
	}else if("agree"==$("#type").val()||ifstop){
		$("#agree").show();$("#disagree").show();
	}
}


$(document).ready(function(){
	
	// 订单详细
	ContractSupplierView.oTable=$('#orderList').dataTable( {
		'bPaginate': false,//不分页
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,goodsQty,goodsTotal,createUser.emp.name,createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {
			ContractSupplierView.oTable.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
			$("#total").text(goodsAPI.fnSumTotal(oSettings));
		},
		'fnRowCallback': function( nRow, aData, i) {//查询完毕每行的回填事件,i代表行序号	
			$(nRow).append("<td class='center'><a href=\"javascript:void(0);\" onclick=\"ContractSupplierView.viewOrder('"+aData.objId+"','"+aData.orderNo+"');return false;\" >查看</a></td>");//添加操作连接
			return nRow;
		},
		'sAjaxSource': $('#initPath').val()+'/OrderController.do?method=list&contract.objId='+$('#objId').val()
	});
	
	
	// 展示合同主要信息：编号、总金额、采购人、供应商
	if($('#objId').val()!=''){
		var queryColumns=[];
		$('#contractForm').find("ul").find("span,div,input").each(function(i,n){
			if($(n).attr("id"))
				queryColumns.push(n.id);     
		});	
		queryColumns.push("supplierOption");
		$.getJSON($('#initPath').val()+'/AgContractController.do?method=getObjectQuery',{objId:$('#objId').val(), queryColumns:queryColumns.toString()}, function(json){
			json2Object("contractForm",json.result[0]);
			jsonObjectToForm("contractForm",json.result[0]);
			$("span[id=buyerOption]").html(json.result[0].buyerOption);//采购人意见
			$("textarea[name=supplierOption]").val(json.result[0].supplierOption);//供应商意见
			$('#contractFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=contractFile&isSelect=yes&attachRelaId='+json.result[0].contractFile);
			ContractSupplierView.getOperationBtn();
		});
	}else {
		ContractSupplierView.getOperationBtn();
	}
	
	$('#contractForm2').validate();
	
	//确认
	$("#sure").click(function(){
		if(!$('#contractForm2').valid())return;
		if(window.confirm("确定要确认该合同吗?")){
			ContractSupplierView.sureContract("01","01","01"); 
		}
	})
	
	//退回
	$("#back").click(function(){
		if(!$('#contractForm2').valid())return;
		if(window.confirm("确定要退回该合同吗?")){
			ContractSupplierView.sureContract("00","00","02");
		}
	})
	
	//申请作废
	$("#invalid").click(function(){
		if(!$('#contractForm2').valid())return;
		if(confirm('确定要作废该合同吗？')){
			ContractSupplierView.invalidContract("01","00","01"); 
		}
		return false;
	})
	
	//确认作废
	$("#agree").click(function(){
		if(!$('#contractForm2').valid())return;
		if(window.confirm("确定要确认作废该合同吗?")){
			ContractSupplierView.invalidContract("02","01","01"); 
		}
	})
	
	//取消作废
	$("#disagree").click(function(){
		if(!$('#contractForm2').valid())return;
		ContractSupplierView.invalidContract("01","01","01"); 
	})
	
	//打印合同
	$("#printContract").click(function(){
		//弹出打印合同页面
		 $.epsDialog({
		        title:'合同编号：'+$("#contractNo").text(),
				url:$('#initPath').val()+'/view/agreement/contract/contract_print_div.jsp',
				width: '800',
				height: '550'
		  }); 
	})
	
});
	
