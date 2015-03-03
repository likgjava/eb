/*
 * 协议供货，确认合同详细页面
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var ContractPurchaserView={};
ContractPurchaserView.oTable;	

//查看订单详情
ContractPurchaserView.viewOrder=function(objId,orderNo){
	 //弹出订单详情页面
	 $.epsDialog({
	        title:'订单编号：'+orderNo,
			url:$('#initPath').val()+'/OrderController.do?method=toOrderForm&navigate=divDetail&objId='+objId
	  }); 
}

//申请作废/确认作废/取消作废合同
ContractPurchaserView.invalidContract=function(useStatus,buyerConfirmStatus,supplierConfirmStatus){
	var josnObj={};
	josnObj.objId = $("#objId").val();
	josnObj.useStatus = useStatus;
	josnObj.buyerConfirmStatus = buyerConfirmStatus;
	josnObj.supplierConfirmStatus = supplierConfirmStatus;
	josnObj.buyerOption = $("textarea[name=buyerOption]").val();
	
	$.ajax({
		url: $("#initPath").val()+"/AgContractController.do?method=invalidContract",
		type:"POST",
		data:{contractStr:JSON.stringify(josnObj),"sureRole":"buyer"},
		dataType:'json',
		success:function(json){
			if(json.failure){
				return;
			}
			if(supplierConfirmStatus == "00"){
				alert("已将该作废申请提交"+$("span[id=supplier.orgName]").text()+"，请等待其确认！");
			}
			else if(useStatus == "02"){
				alert("该合同从即日起已经作废！");
			}else{
				alert("已取消作废申请，该合同仍然有效！");
			}
			$('#conBody').loadPage($('#initPath').val()+"/view/agreement/contract/contract_purchaser_list.jsp");
		},
		error:function(msg){
			alert('操作失败');
		}
	})
}

//确认/退回合同
ContractPurchaserView.sureContract=function(useStatus,buyerConfirmStatus,supplierConfirmStatus){
	var josnObj={};
	josnObj.objId = $("#objId").val();
	josnObj.useStatus = useStatus;
	josnObj.buyerConfirmStatus = buyerConfirmStatus;
	josnObj.supplierConfirmStatus = supplierConfirmStatus;
	josnObj.buyerOption = $("textarea[name=buyerOption]").val();
	$.ajax({
		url: $("#initPath").val()+"/AgContractController.do?method=auditContract",
		type:"POST",
		data:{contractStr:JSON.stringify(josnObj),"sureRole":"buyer"},
		dataType:'json',
		success:function(json){
			if(json.result){
				alert(json.result,{inco:'info'});
			}
			if(json.failure){
				return;
			}
			if(buyerConfirmStatus == "01"){
				alert("合同已经确认，从"+$("#contractBeginTime").text()+"开始生效！");
			}
			else if(buyerConfirmStatus == "02"){
				alert("已将该合同退回给"+$("span[id=supplier.orgName]").text()+"，请等待其确认！");
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
	if("view" == $("#type").val()){
		$("#invalid").show();
	}else if("sure" == $("#type").val()){
		$("#sure").show();
		$("#back").show();
	}else if("agree" == $("#type").val()){
		$("#agree").show();
		$("#disagree").show();
	}
	
	// 订单详细
	ContractPurchaserView.oTable=$('#orderList').dataTable( {
		'bPaginate': false,//不分页
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为false)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':'orderNo,categoryNames,goodsQty,goodsTotal,createUser.emp.name,createTime',//指定要查询的列
		'hiddenColumns':'',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {
			ContractPurchaserView.oTable.oSettings=oSettings;//很重要，将当前的oSettings对象赋给当前table的oSettgins
			$("#total").text(goodsAPI.fnSumTotal(oSettings));
		},
		'fnRowCallback': function( nRow, aData, i) {//查询完毕每行的回填事件,i代表行序号	
			$(nRow).append("<td class='center'><a href=\"javascript:void(0);\" onclick=\"ContractPurchaserView.viewOrder('"+aData.objId+"','"+aData.orderNo+"');return false;\">查看</a></td>");//添加操作连接
			return nRow;
		},
		'sAjaxSource': $('#initPath').val()+'/OrderController.do?method=list&contract.objId='+$('#objId').val()
	});
	
	
	// 展示合同主要信息：编号、总金额、采购人、供应商
	if($('#objId').val()!=''){
		var queryColumns=[];
		$('#contractForm').find("ul").find("span,input,div").each(function(i,n){
			if($(n).attr("id"))
				queryColumns.push(n.id);     
		});	
		queryColumns.push("buyerOption");     
		$.getJSON($('#initPath').val()+'/AgContractController.do?method=getObjectQuery',{objId:$('#objId').val(), queryColumns:queryColumns.toString()}, function(json){
			json2Object("contractForm",json.result[0]);
			$("span[id=supplierOption]").html(json.result[0].supplierOption);//供应商意见
			$("textarea[name=buyerOption]").val(json.result[0].buyerOption);//采购人意见
			$('#contractFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=contractFile&isSelect=yes&attachRelaId='+json.result[0].contractFile);
		});
	}
	
	$('#contractForm2').validate();
	
	//确认
	$("#sure").click(function(){
		if(!$('#contractForm2').valid())return;
		if(window.confirm("确定要确认该合同吗?")){
			ContractPurchaserView.sureContract("01","01","01");
		}
	})
	
	//退回
	$("#back").click(function(){
		if(!$('#contractForm2').valid())return;
		if(window.confirm("确定要退回该合同吗?")){
			ContractPurchaserView.sureContract("00","02","00");
		}
	})
	
	//申请作废
	$("#invalid").click(function(){
		if(!$('#contractForm2').valid())return;
		if(confirm('确定要作废该合同吗？')){
			ContractPurchaserView.invalidContract("01","01","00"); 
		}
		return false;
	})
	
	//确认作废
	$("#agree").click(function(){
		if(!$('#contractForm2').valid())return;
		if(window.confirm("确定要确认作废该合同吗?")){
			ContractPurchaserView.invalidContract("02","01","01"); 
		}
	})
	
	//取消作废
	$("#disagree").click(function(){
		if(!$('#contractForm2').valid())return;
		ContractPurchaserView.invalidContract("01","01","01"); 
	})
	
	//打印合同
	$("#printContract").click(function(){
		//window.open($('#initPath').val()+'/view/agreement/contract/contract_print_div.jsp?objId='+$("#objId").val());
		//弹出打印合同页面
		$.epsDialog({
			 	id:"printContractDiv",
		        title:'合同编号：'+$("#contractNo").text(),
				url:$('#initPath').val()+'/view/agreement/contract/contract_print_div.jsp',
				width: '800',
				height: '550'
		 }); 
	})
});
	
