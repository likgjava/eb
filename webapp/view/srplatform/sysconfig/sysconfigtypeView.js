//包组区分JS方法、属性的唯一性
var sysconfigtypeView={};

$(document).ready(function(){
	//alert("调用了JS====sysconfigtypeView.js");
	if($('#objId').val()!='' && $('#objId').val()!='-1'){
    	$.getJSON($('#initPath').val()+'/SysConfigController.do?method=getSysconfigTypeDetail',{objId:$('#objId').val(), includedProperties:'name'},function(json){
    		json2ObjectSpan('sysconfigtypeViewForm', json.sysConfigType);
    		//设置首页默认的公有数据
    		$("#curObjId").val(json.sysConfigType.objId);
    		if(json.sysConfigType.parent && json.sysConfigType.parent.objId){
    			$("#parentObjId").val(json.sysConfigType.parent.objId);
    		}
    		//修改当前系统配置类型对应的类型路径
    		$("#curTypePath").val(json.sysConfigType.typePath);
    	});
    }
	
});