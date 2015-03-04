var SelectFactorDeList = {};
SelectFactorDeList.selectType;
SelectFactorDeList.num = 0;
var scoreNum = 0;
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
			$('#epsDialogCloseReload').click();
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
			SelectFactorDeList.addFactorForm(value[i],$("#toSelect option[value='"+value[i]+"']").text());
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
		SelectFactorDeList.removeFactorForm(value[i]);
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
	if(undefined != value && null != value && '' != value){
		$("#toSelect").append('<option value='+value+'>'+$("#alreadySelect option[value='"+value+"']").text()+'</option>');
		SelectFactorDeList.removeFactorForm(value);
		$("#alreadySelect").removeItem(value);
		var optionObj = $("#alreadySelect").find('option');
		if(optionObj.length>0){
			$(optionObj[0]).attr("selected",true);
		}
	}
}

// 双击添加
document.getElementById('toSelect').ondblclick = function(){
	var value = $('#toSelect').val();
	if(undefined != value && null != value && '' != value){
		$("#alreadySelect").append('<option value='+value+'>'+$("#toSelect option[value='"+value+"']").text()+'</option>');
		SelectFactorDeList.addFactorForm(value,$("#toSelect option[value='"+value+"']").text());
		$("#toSelect").find('option[value="'+value+'"]').remove();
		var optionObj = $("#toSelect").find('option');
		if(optionObj.length>0){
			$(optionObj[0]).attr("selected",true);
		}
	}
}

// 添加指标表单
SelectFactorDeList.addFactorForm = function(factorId,factorName){
	var html = '';
	html += '<tr id="'+factorId+'">';
	html += '<td><input type="hidden" name="congruousFactor['+SelectFactorDeList.num+'].factorName" value="'+factorName+'"/>'+factorName+'</td>';
	if(scoreNum > 0){
		html += '<td><input type="text" name="congruousFactor['+SelectFactorDeList.num+'].score" style="width:60px;" class="required floats"/><span class="eleRequired">*</span>';
	}
	html += '<td><input type="text" name="congruousFactor['+SelectFactorDeList.num+'].remark" style="width:180px;" />';
	html += '<input type="hidden" name="congruousFactor['+SelectFactorDeList.num+'].factorType.objId" value="'+$("#factorTypeId").val()+'" />';
	html += '</td>';
	html += '</tr>';
	$('#congruousFactorTable').append(html);
	SelectFactorDeList.num ++;
}
// 删除指标表单
SelectFactorDeList.removeFactorForm = function(factorId){
	$('#congruousFactorTable tr[id='+factorId+']').remove();
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
	if("" == value){
		SelectFactorDeList.returnValue(value,item,true);
	}else{
		var packIds = new Array();
		if($("#SUB_PROJ_CHECK_TD input[type=checkbox]").length > 0){
			if($("#SUB_PROJ_CHECK_TD input[type=checkbox][checked=true]").length == 0){
				alert("请选择适用包组！");
				return false;
			}
			$.each($("#SUB_PROJ_CHECK_TD input[type=checkbox][checked=true]"),function(i,n){
				packIds.push(n.value);
			})
		}else{
			packIds.push($('#PROJ_ID_').val());
		}
		if(packIds.length>0){
			if(!$('#importCongruousFactorForm').valid()){alert('请正确填写指标分值！');return;}
			var scoreSum = 0;
			$('#congruousFactorTable tr>td>input[name$=score]').each(function(i,n){
				var score = $(n).val() * 1;
				if (score >= 0) {
					scoreSum += score;
				}
			})
			if((scoreNum-scoreSum) >= 0){
				var jsonObj = formToJsonObject('importCongruousFactorForm','jsonUtils');
				$.getJSON($("#initPath").val()+"/CongruousFactorController.do?method=saveImportFactors&",{"packIds":packIds.toString(),"isNeed":$("[name=isNeed][checked=true]").val(),"congruousFactors":JSON.stringify(jsonObj.congruousFactor)},function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					SelectFactorDeList.returnValue("","",true);
				})
			}else{
				alert("当前分值总和为"+scoreSum+"，不能新增指标。\n若需要新增指标，请先修改指标分值。\n指标分值总和应该为"+scoreNum+"。");
			}
		}
	}
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
	scoreNum = $('#score_num').val();
});