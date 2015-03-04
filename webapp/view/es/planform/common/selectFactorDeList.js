var SelectFactorDeList = {};
SelectFactorDeList.selectType;
SelectFactorDeList.initMenuTree = function(){
	$("#menu_tree").empty();
	SelectFactorDeList.menutree=new dhtmlXTreeObject("menu_tree","100%","100%",0);
	SelectFactorDeList.menutree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	SelectFactorDeList.menutree.enableThreeStateCheckboxes(true);
	SelectFactorDeList.menutree.setOnClickHandler(SelectFactorDeList.menutreeLeftClick);
	SelectFactorDeList.menutree.setXMLAutoLoading("FactortypeDeController.do?method=getTree&order=sort&action=listById",function(){
		
	});
	SelectFactorDeList.menutree.loadXML($("#initPath").val()+"/FactortypeDeController.do?method=getTree&order=sort&action=listTop&id=0",function(){
		SelectFactorDeList.menutree.selectItem("-1");
	});
}

SelectFactorDeList.menutreeLeftClick = function(id){
	$("#factor_type_info").empty().loadPage($("#initPath").val()+"/FactortypeDeController.do?method=toFactortypeDeDetailView&objId="+id);
	// 单选
	if(SelectFactorDeList.selectType == "radio"){
		SelectFactorDeList.selectFactorIsRadioList(id);
	// 多选
	}else if(SelectFactorDeList.selectType == "checkbox"){
		SelectFactorDeList.selectFactorIsCheckboxList(id);
	}else{
		$("#selectFactorIsCheckboxList").empty();
	}
}

// 列表选择(一个)
SelectFactorDeList.selectFactorIsRadioList = function(id){
	$("#selectFactorIsCheckboxList").empty();
	if("" == id || "-1" == id){
		return false;
	}
	$("#selectFactorIsRadioList").empty().loadPage($("#initPath").val()+"/FactorDeController.do?method=toFactorDeListView&factortypeId="+id);
}

// 选择框选择(多个)
SelectFactorDeList.selectFactorIsCheckboxList = function(id){
	$("#toSelect").find('option').remove();
	$("#selectFactorIsRadioList").empty();
	if("" == id || "-1" == id){
		return false;
	}
	$.getJSON($("#initPath").val()+"/FactorDeController.do?method=getObjectQuery&queryColumns=objId,factorName",{"factortypeDe.objId":id},function(json){
		var list = json.result;
		if(null != list && '' != list && "null" != list && "[]" != list){
			var alreadyOptions = $("#alreadySelect option");
			$.each(list,function(i,n){
				if(alreadyOptions.length >0){
					var num = 0;
					$.each(alreadyOptions,function(m,o){
						if($(o).val() == n.objId){
							num ++;
						}
					})
					if(num == 0){
						$("#toSelect").append('<option value='+n.objId+'>'+n.factorName+'</option>');
					}
				}else{
					$("#toSelect").append('<option value='+n.objId+'>'+n.factorName+'</option>');
				}
				
			})
		}
	})
}

//回填数据
SelectFactorDeList.returnValue = function(id,name,isReturn){
	if(null != $("#_return_id").val() && "" != $("#_return_id").val()){
		document.getElementById($("#_return_id").val()).value = id;
	}
	if(null != $("#_return_name").val() && "" != $("#_return_name").val()){
		document.getElementById($("#_return_name").val()).value = name;
	}
	if(isReturn){
		if(null != $("#_epsDialog_id").val() && "" != $("#_epsDialog_id").val()){
			$('#'+$("#_epsDialog_id").val()+' .epsDialogCloseReload').click();
		}else{
			$('#epsDialogCloseNoReload').click();
		}
	}
}

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
	SelectFactorDeList.returnValue(value,item,true);
})

//清空
$("#CLEAR").click(function(){
	$("#alreadySelect>option").each(function(i,n){
		$("#toSelect").append('<option value='+$(n).val()+'>'+$(n).text()+'</option>');
		$(n).remove();
	})
	SelectFactorDeList.returnValue("","",false);
})

$(document).ready(function(){
	SelectFactorDeList.selectType = $("#_select_type").val();
	SelectFactorDeList.initMenuTree();
	SelectFactorDeList.menutreeLeftClick("");
});