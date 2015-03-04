//改变项目投资类型时触发
changeType = function(o){
	if(o.value=='01'&& $(o).attr('checked')==true){
		$.each($(":checkbox[name='moneyUseModel']"),function(i,n){
			$(n).attr('checked',false);
		});
		$('#moneyModel').val("");
		
		$.each($("#central :text[name='moneySource']"),function(i,n){
			$(n).val("0");
		});
		$('#centralInvestmentMoney').val("0");
	}
}
//输入资金来源时触发
computeMoney = function(o){
	var money = /^(0|[1-9]\d*|(0|[1-9]\d*)\.\d{0,6})$/;
	if(!money.test(o.value)){
		var value = o.value;
		$(o).val(value.substr(0,value.length-1));
		return false;
	}
	var centralInvestmentMoney = 0;
	var notCentralInvestmentMoney = 0;
	$.each($("#central :text[name='moneySource']"),function(i,n){
		centralInvestmentMoney = parseFloat(n.value==''?0:n.value)+parseFloat(centralInvestmentMoney);
	});
	$.each($("#notCentral :text[name='moneySource']"),function(i,n){
		notCentralInvestmentMoney = parseFloat(n.value==''?0:n.value)+parseFloat(notCentralInvestmentMoney);
	});
	$('#centralInvestmentMoney').val(centralInvestmentMoney);
	$('#notCentralInvestmentMoney').val(notCentralInvestmentMoney);
}

//设置为委托人
setCommissioner = function(src){
	if(src=="budget"){//修改采购人为委托人
		/*
		$('#yzdw_id_commissionUnitNameId').val($('#yzdw_id').val());
		$('#yzdw_name_commissionUnitName').val($('#yzdw_name').val());
		$('#commissionedUnitLinker').val($('#budgetLinker').val());
		$('#commissionedUnitTel').val($('#budgetLinkerTel').val());
		*/
		
		$("#yzdw_id_commissionUnitNameId").val($("#yzdw_id").val());
		$("#yzdw_name_commissionUnitName").val($("#yzdw_name").val());
		
		if($("#budgetLinker").val()==null&&$("#budgetLeaderId").val()!=""){
			$("#commissionedUnitLinker").val($("#budgetLeaderId").find('option:selected').text());
		}else{
			$("#commissionedUnitLinker").val($("#budgetLinker").val());
		}
		$("#commissionedUnitTel").val($("#budgetLinkerTel").val());
		
		
	}else if(src=="department"){//修改专业公司为委托人
		/*
		$('#yzdw_id_commissionUnitNameId').val($('#yzdw_id_parentid').val());
		$('#yzdw_name_commissionUnitName').val($('#yzdw_name_parentname').val());
		$('#commissionedUnitLinker').val($('#departmentLinker').val());
		$('#commissionedUnitTel').val($('#departmentLinkerTel').val());
		*/
		$("#yzdw_name_commissionUnitName").val($("#yzdw_name_parentname").val());
	 	 $("#commissionedUnitLinker").val($("#departmentLinker").val());
	 	 $("#commissionedUnitTel").val($("#departmentLinkerTel").val());
		
		$.getJSON($('#initPath').val()+'/OrgInfoController.do?method=getObjectQuery&queryColumns=objId', {orgName:$("#yzdw_name_parentname").val()}, function(json){
			 if(json.result[0].objId){ 
			   $("#yzdw_id_commissionUnitNameId").val(json.result[0].objId);
			 }
		});
		
	}
}

var resProjectInfo = {};
//保存
resProjectInfo.save = function(){
	if(!$('#resProjectInfoForm').valid()){alert('请正确填写表单!');return;}
	var centralInvestmentMoney = $('#centralInvestmentMoney').val();
	var notCentralInvestmentMoney = $('#notCentralInvestmentMoney').val();
	var amt = $('#amt').val();
	if($('#centralType').attr('checked')==true && centralInvestmentMoney==0){
		alert("中央投资金额不能为0！");
		return false;
	}
	if(parseFloat(amt*1000000)-parseFloat(centralInvestmentMoney*1000000)-parseFloat(notCentralInvestmentMoney*1000000)!=0){
		alert("中央投资金额与非中央投资金额之和不等于项目总投资！");
		return false;
	}
	
	var moneyModel = "";
	$.each($moneyUseModels = $(":checkbox[name='moneyUseModel']"),function(i,n){
		if($(n).attr('checked')==true){
			if(moneyModel==""){
				moneyModel = n.value;
			}else{
				moneyModel = moneyModel+","+n.value
			}
		}
	});
	$('#moneyModel').val(moneyModel);
	
	var moneySourceArray = new Array();
	$.each($moneySources = $(":text[name='moneySource']"),function(i,n){
		moneySourceArray.push("{'categoryId':'"+$(n).attr('id')+"','money':'"+n.value+"'}");
	});
	var resProjectInfoForm = formToJsonObject('resProjectInfoForm');
	 resProjectInfoForm.agenty = {objId:$('#agentyId').val()};
	 resProjectInfoForm.agentyLeader = {objId:$('#agentyLeaderId').val()};
	 resProjectInfoForm.budget = {objId:$('#yzdw_id').val()};
	 resProjectInfoForm.budgetLeader = {objId:$('#budgetLeaderId').val()};
	 resProjectInfoForm.department = {objId:$('#yzdw_id_parentid').val()};
	 resProjectInfoForm.commissionedUnit = {objId:$('#yzdw_id_commissionUnitNameId').val()};
	 resProjectInfoForm.parent = {objId:$('#parentId').val()};
	 
	var url = $('#initPath').val()+'/ResProjectController.do?method=saveResProjectInfo';
	
	$.getJSON(url,{"resProjectStr":JSON.stringify(resProjectInfoForm),"moneySourceArray":"["+moneySourceArray+"]"},function(json){
		if(json.result)alert(json.result);
        if(json.failure){
            return;
        }
        if($('#parentId').val()==''){
    		$('#conBody').loadPage($('#initPath').val()+'/ResProjectController.do?method=loadResProjectManagePage');
    	}else{
    		 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadDisassemblePage&resProjectId="+$('#parentId').val());
    	}
	});	 
};
resProjectInfo.back = function(){
	if($('#parentId').val()==''){
		$('#conBody').loadPage($('#initPath').val()+'/ResProjectController.do?method=loadResProjectManagePage');
	}else{
		 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadDisassemblePage&resProjectId="+$('#parentId').val());
	}
}
$(document).ready(function(){
	//扩展验证规则，对万元为单位的金额进行验证
	jQuery.validator.addMethod("bigMoney", function(value, element) { 
	    var money = /^([1-9]\d*|(0|[1-9]\d*)\.\d{0,6})$/; 
	    return money.test(value) || this.optional(element); 
	}, "请输入正确的金额(小数位数保留6为以内)");
	
	//扩展验证规则，对万元为单位的金额进行验证
	jQuery.validator.addMethod("linkPhone", function(value, element) { 
	    var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	    var phone = /^(\d{3,4}-?)?\d{7,9}$/;
	    return (phone.test(value) || mobile.test(value)) ||this.optional(element); 
	}, "请输入正确的手机号码或电话号码");
	
	//业主单位
	$("#yzdw_name").click(function(){
		$.epsDialog({
	        title:'选择',
	        url:$("#initPath").val()+'/view/bizplatform/organization/manager/select_orginfo.jsp?orgType=y&Hname=yzdw_name&Hid=yzdw_id&OrgId=true',
	        onClose: function(){ 
				var OrgId=$("#OrgId").val();
				$.getJSON($('#initPath').val()+'/EmployeeController.do?method=getObjectQuery&queryColumns=objId,name',{'company.objId':OrgId},function(json){
					var html = '<select name="budgetLeaderId" id="budgetLeaderId" class="required"><option value="">请选择</option>';
					$.each(json.result,function(i,n){
						html += '<option value='+n.objId+'>'+n.name+'</option>';
					})
					html += '</select>';
					$('#budgetLinkerDiv').html(html);
				});
			}
	       
	    })
	});
	
	$("#yzdw_name_parentname").click(function(){ //所属专业公司
		$.epsDialog({
	        title:'选择',
	        url:$("#initPath").val()+'/view/bizplatform/organization/manager/select_orginfo.jsp?orgType=y&Hname=yzdw_name_parentname&Hid=yzdw_id_parentid&OrgId=true',
	        onClose: function(){ 
				var OrgId=$("#OrgId").val();
				$.getJSON($('#initPath').val()+'/EmployeeController.do?method=getObjectQuery&queryColumns=objId,name',{'company.objId':OrgId},function(json){
					var html = '<select name="budgetLeaderId" id="budgetLeaderId" class="required"><option value="">请选择</option>';
					$.each(json.result,function(i,n){
						html += '<option value='+n.objId+'>'+n.name+'</option>';
					})
					html += '</select>';
					$('#budgetLinkerDiv').html(html);
				});
			}
	       
	    })
	});
	
	
	$('#save').click(resProjectInfo.save);
	$('#back').click(resProjectInfo.back);
});