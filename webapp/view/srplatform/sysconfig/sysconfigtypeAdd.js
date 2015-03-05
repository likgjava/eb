//包组区分JS方法、属性的唯一性
var sysconfigtypeAdd={};
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
	$("#sysConfigTypeSave").click(function(){
		if(!$('#sysConfigTypeForm').valid()){
			alert('请正确填写表单!','提示',{icon:'info'});
			return;
		}
		
		if(validateCodeISavailability()){
			return false;
		}
		
		$.getJSON($('#initPath').val()+'/SysConfigController.do?method=saveSysConfigType', formToJsonObject('sysConfigTypeForm'), function(json){
			if(json.failure){
				alert(json.result);
				return;
			}
			if(json.result){
				alert(json.result,{inco:'info'});;
			}
			// 保存成功后清除页面信息,并刷新树  start
			// sysconfigtypeTree是 view/srplatform/sysconfig/sysconfigtypeList.js 中定义的树
			sysconfigtypeTree.insertNewItem(sysconfigtypeTree.getSelectedItemId(),json.sysConfigType.objId,json.sysConfigType.name,0,0,0,0,'SELECT');
			sysconfigtypeTree.setItemImage2(sysconfigtypeTree.getSelectedItemId(),"node.gif","folderOpen.gif","folderClosed.gif");
			//选中当前焦点   start
			sysconfigtypeList.sysconfigtypeClick(json.sysConfigType.objId)
			//重置TAB页上的公有数据
			//parentObjId
			//curObjId
			//curTypePath
			//保存成功后清除页面信息,并刷新树  end
		});
	})
	$("#sysConfigTypeReturn").click(function(){
		sysconfigtypeList.sysconfigtypeClick($("#curObjId").val());
	});
});
