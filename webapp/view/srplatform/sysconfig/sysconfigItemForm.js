var sysconfigItemAdd={};
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
	$('#dataType').val($('#data_type').val());
	if($('#data_type').val() == 'trst'){
		$('#preValueTable').show();
		$('#dataType').parent().append('<button onClick="addPreValue();" class="sysicon siAdd" type="button" title="添加预设值"><span>添加预设值</span></button>')
	}else{
		$('#preValueTable').hide();
	}
	$("#sysConfigItemReturn").click(function(){
		$("#sysconfigItemListView").click();
	});
	
	$('#dataType').change(function(){
		if(this.value == 'trst'){
			$(this).parent().find('button').remove();
			$(this).parent().append('<button onClick="addPreValue();" class="sysicon siAdd" type="button" title="添加预设值"><span>添加预设值</span></button>')
			$('#preValueTable').show();
		}else{
			$('#preValueTable').hide();
			$(this).parent().find('button').remove();
		}
	})
	
	addPreValue = function(){
		var num = $('#num').val()*1+1;
		var html = '';
		html += '<tr num="'+num+'">';
		html += '<td><input class="required" name="name'+num+'"></input><span class="eleRequired">*</span>';
		html += '<input type="hidden" name="objId'+num+'" value=""></input></td>';
		html += '<td><input class="required" name="code'+num+'"></input><span class="eleRequired">*</span></td>';
		html += '<td><button onClick="deletePreValue(this);" class="sysicon siDelete" type="button" title="删除"><span>删除</span></button></td>';
		html += '</tr>';
		$("#preValuesTable").append(html);
		$('#num').val(num);
	}
	deletePreValue = function(obj){
		$(obj).parent().parent().remove();
	}
	
	$('#code').change(function(){
		validateSysconfigItemCodeexclusive();
	})
});

validateSysconfigItemCodeexclusive = function(){
	var result = false;
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
	
	if(null != $('#code').val() && "" != $('#code').val()){
		var code = $('#parent_tree_path_code').val().toString() + DELIMITER + $('#code').val().toString();
		$.ajax({
			url:$("#initPath").val()+"/SysConfigController.do?method=validateSysconfigItemCodeexclusive",
			type:"POST",
			data:{"configItemCode":code,"objId":$('#objId').val()},
			async:false,
			success:function(json){
				json = eval('('+json+')')
				if('true' == json.validateResult){
					result = true;
					alert("配置条目编号["+$('#code').val()+"]已经存在！");
					$('#code').val('');
				}
			},error:function(e){
				alert('验证编号唯一性异常！');
			}
		})
	}
	return result;
}

// 保存
$("#sysConfigItemSave").click(function(){
	if(!$('#sysConfigTypeItemForm').valid()){alert('请正确填写表单!');return;}
	if(validateCodeISavailability()){
		return false;
	}
	if(validateSysconfigItemCodeexclusive()){
		return false;
	}
	
	if($('#dataType').val() == 'trst'){
		var names = new Array();
		var codes = new Array();
		$("#preValuesTable tr").each(function(i,n){
			names.push($(n).find('td').eq(0).find('input').val());
			codes.push($(n).find('td').eq(1).find('input').val());
		})
		var count = 0;
		$.each(names,function(i,n){
			$.each(names,function(m,o){
				if(o == n){
					count ++;
				}
			})
		})
		if(count > names.length){
			alert("[预设名称]有重复值,请修正..");
			return false;
		}
		count = 0
		$.each(codes,function(i,n){
			$.each(codes,function(m,o){
				if(o == n){
					count ++;
				}
			})
		})
		if(count > codes.length){
			alert("[预设值]有重复值,请修正..");
			return false;
		}
	}
	var data_num = new Array();
	$('#preValuesTable tr').each(function(i,n){
		data_num.push($(n).attr("num"));
	})
	$('[name=data_num]').val(data_num.toString());
	$('#sysConfigTypeItemForm').ajaxSubmit({
		url:$('#initPath').val()+"/SysConfigController.do?method=saveSysConfigItem",
		dataType:'json',
		success:function(json){
		if(json.failure){
			alert(json.result);
			return;
		}
		if(json.result){
			alert(json.result,{inco:'info'});
		}
		$("#sysconfigItemListView").click();
		},
		error:function(msg){
			alert(msg);
			$("#sysconfigItemListView").click();
		}
	});
})