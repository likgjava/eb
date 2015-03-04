
var signUprecordList={};


//查询条件过滤
signUprecordList.before=function(){
	var option={'auditStatus':$('#paramId').val()}
	$('#signUprecordG').flexOptions({params:option});
	return true;
}

//加载数据成功之后调用的函数
signUprecordList.success=function(){
	var auditStatus =$('#paramId').val();
	if(auditStatus=='00'){
	$("#signUprecordG").flexAddOptionStr({
		'<span><a href="#" class="abtn">审核</a></span>'  : function(btn,rowId,obj){
			 btn.click(function(){
				 signUprecordList.audit(rowId);
			 }).appendTo(obj);
		}
	});
	}else if(auditStatus=='02'){
		$("#signUprecordG").flexAddOptionStr({
		'<span><a href="#" class="abtn">修改</a></span>'  : function(btn,rowId,obj){
			 btn.click(function(){
				 signUprecordList.modify(rowId);
			 }).appendTo(obj);
		}
		});
	}
}

signUprecordList.audit=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/SignUprecordController.do?method=getSupplierForAudit&objId='+rowId);
}

signUprecordList.modify=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/SignUprecordController.do?method=toViewSignupPage&objId='+rowId);
}