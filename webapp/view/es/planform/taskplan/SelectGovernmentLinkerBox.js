var SelectBox={}

$(document).ready(function(){
	var orgObjId = $("#_param").val();
    $.getJSON($("#initPath").val()+"/UserApiController.do?method=getEmpList&queryColumns=objId,name",{"department.objId":orgObjId},function(json){
    	var list = json.empList;
    	if(null != list && '' != list && "null" != list && "[]" != list){
    		$.each(list,function(i,n){
    			$("#toSelect").append('<option value='+n.objId+'>'+n.name+'</option>');
    		})
    	}
    })
	
	//添加
	$("#ADD").click(function(){
		var value = $('#toSelect').val();
		if(!value){
			alert("请至少选择一项！");
			return;
		}
		for(var i=0; i<value.length; i++){
			if(!$("#alreadySelect").isExistItem(value[i])){
				$("#alreadySelect").append('<option value='+value[i]+'>'+$("#toSelect option[value='"+value[i]+"']").text()+'</option>');
				$("#toSelect").find('option[value="'+value[i]+'"]').remove();
				var optionObj = $("#toSelect").find('option');
				if(optionObj.length>0){
					$(optionObj[0]).attr("selected",true);
				}
			}
		}
	})
	
	//删除
	$("#DELETE").click(function(){
		var value = $('#alreadySelect').val();
		if(!value){
			alert("请至少选择一项！");
			return;
		}
		for(var i=0; i<value.length; i++){
			$("#toSelect").append('<option value='+value[i]+'>'+$("#alreadySelect option[value='"+value[i]+"']").text()+'</option>');
			$("#alreadySelect").removeItem(value[i]);
			var optionObj = $("#alreadySelect").find('option');
			if(optionObj.length>0){
				$(optionObj[0]).attr("selected",true);
			}
		}
	})
	
	// 双击删除
	document.getElementById('alreadySelect').ondblclick = function(){
		var value = $('#alreadySelect').val();
		$("#toSelect").append('<option value='+value+'>'+$("#alreadySelect option[value='"+value+"']").text()+'</option>');
		$("#alreadySelect").removeItem(value);
		var optionObj = $("#alreadySelect").find('option');
		if(optionObj.length>0){
			$(optionObj[0]).attr("selected",true);
		}
	}
	
	// 双击添加
	document.getElementById('toSelect').ondblclick = function(){
    	var value = $('#toSelect').val();
    	$("#alreadySelect").append('<option value='+value+'>'+$("#toSelect option[value='"+value+"']").text()+'</option>');
		$("#toSelect").find('option[value="'+value+'"]').remove();
		var optionObj = $("#toSelect").find('option');
		if(optionObj.length>0){
			$(optionObj[0]).attr("selected",true);
		}
    }
	
	//确定
	$("#OK").click(function(){
		var value = "";
		var item = "";
		var options = $("#alreadySelect option");
		for(var i=0; i<options.length; i++){
			value += options[i].value + ",";
			item += options[i].text + ",";
		}
		if(options.length > 0){
			value = value.substring(0,value.length-1);
			item = item.substring(0,item.length-1);
		}	
		returnValue(value,item);
	})
	
	//清空
	$("#CLEAR").click(function(){
		$("#alreadySelect>option").each(function(i,n){
			$("#toSelect").append('<option value='+$(n).val()+'>'+$(n).text()+'</option>');
			$(n).remove();
		})
		returnValue("","");
	})
})
	
function returnValue(id,name){
	$("#govLinkerSpan").empty().append(name);
	$("#govLinker").val(name);
	//关闭弹出层
	$('#select_linker .epsDialogCloseReload').click();
}

	
