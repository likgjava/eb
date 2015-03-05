//包组区分JS方法、属性的唯一性
var sysconfigTypeUpdate={};
var parentTypePath = "";
var DELIMITER = "";
var validateCodeISavailability = function(){
	var validateResult = false;
	var code = $('#code').val();
	if(null != code && "" != code){
		if("" == DELIMITER){
			$.ajax({
				url:$("#initPath").val()+"/SysConfigController.do?method=getSysConfigCodeDelimiter",
				type:"POST",
				data:{},
				async:false,
				success:function(json){
					json = eval('('+json+')')
					DELIMITER = json.delimiter;
				}
			})
		}
		if(code.replace(DELIMITER,"").length < code.length){
			alert("系统配置编号不能包含非法字符[ "+DELIMITER+" ]")
			validateResult = true;
		}
	}
	return validateResult
}
$(document).ready(function(){
	$('#code').change(function(){
		validateCodeISavailability();
	})
	if($('#objId').val()!='' && $('#objId').val()!='-1'){
    	$.getJSON($('#initPath').val()+'/SysConfigController.do?method=getSysconfigTypeDetail',{objId:$('#objId').val(), includedProperties:'parent'},function(json){
    		jsonObjectToForm('sysConfigTypeUpdateForm', json.sysConfigType);
    		if(json.sysConfigType.parent && json.sysConfigType.parent.typePath){
    			parentTypePath = json.sysConfigType.parent.typePath
    		}
    	});
    }
	//返回
	$('#sysConfigTypeUpdateReturn').click(function(){
		sysconfigtypeList.sysconfigtypeClick($("#curObjId").val());
	});
	$("#sysConfigTypeUpdateSave").click(function(){
		if(!$('#sysConfigTypeUpdateForm').valid()){
			alert('请正确填写表单!','提示',{icon:'info'});
			return;
		}
		if(validateCodeISavailability()){
			return false;
		}
		//将默认的路径放到页面中和条目编号组成条目路径
		if(parentTypePath != ""){//如果父类有路径，则需要拼装当前节点的路径
			parentTypePath += "__";
		}
		$("#typePath").val(parentTypePath+$("#code").val());
		$.getJSON($('#initPath').val()+'/SysConfigController.do?method=saveSysConfigTypeForUpdate', formToJsonObject("sysConfigTypeUpdateForm"), function(json){
			if(json.failure){
				alert(json.result);
				return;
			}else{
				sysconfigtypeList.reLoadSysconfigTypeTree();
			}
		});
	})
});
