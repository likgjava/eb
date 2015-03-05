//定义文件全局变量 处理方法名重复问题

var gadgetForm={};

$(document).ready(function(){
	
 	//查询授权资源
	$("input[id=resource.name]").click(function(e){		 
		$.epsDialog({
	        title:'选择关联资源',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=resource&className=Resource',
	        width: '500',
	        height: '400',
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){ }
	    }); 
 	});
	
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/GadgetController.do?method=createOrUpdate',{objId:$('#objId').val(),includedProperties:'resource'},function(json){
    		jsonObjectToForm('gadgetForm', json.gadget);
    	});
    }
	//返回
	$('#gadgetReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/GadgetController.do");
	});
	//保存提交
	$('#gadgetSave').click(function(){
		if(!$('#gadgetForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		//alert(obj2str(formToJsonObject('gadgetForm')));
		$.getJSON($('#initPath').val()+'/GadgetController.do?method=save', formToJsonObject('gadgetForm'), function(json){
			if(json.failure){if(json.result)alert(json.result,{inco:'info'});return;}	
			$('#conBody').loadPage('GadgetController.do');
		});
	});

});
