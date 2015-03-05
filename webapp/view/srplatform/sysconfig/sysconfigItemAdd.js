//包组区分JS方法、属性的唯一性
var sysconfigItemAdd={};

$(document).ready(function(){
	$("#sysConfigItemReturn").click(function(){
		$("#sysconfigItemListView").click();
	});
	$("#sysConfigItemSave").click(function(){
		if(!$('#sysConfigTypeItemForm').valid()){
			alert('请正确填写表单!','提示',{icon:'info'});
			return;
		}
		//将默认的路径放到页面中和条目编号组成条目路径
		$("#itemPath").val($("#curTypePath").val()+"__"+$("#code").val());
		formToJsonObject('sysConfigTypeItemForm')
		$.getJSON($('#initPath').val()+'/SysConfigController.do?method=saveSysConfigItem', formToJsonObject('sysConfigTypeItemForm'), function(json){
			if(json.failure){
				alert(json.result);
				return;
			}
			if(json.result){
				alert(json.result,{inco:'info'});
			}
			$("#sysconfigItemListView").click();
		});
	})
	//下拉选择数据类型
	$("#dataType").change(function(){
		if($("#dataType").val() == "List"){
			//如果是枚举类型,则需要手动添加预设值
			$("#preValueLi").show();
			$("#preValuesLi").show();
		}else{
			//清空预设值输入框
			$("#preValueName").val("");
			$("#preValueCode").val("");
			//清空预设值
			$.each($("#preValuesTable tr"),function(i,n){
				//从数据所在行开始删除
				if(i > 0){
					$(n).remove();
				}
			})
			$("#preValueLi").hide();
			$("#preValuesLi").hide();
		}
	});
	//新增预设值
	$("#preValueAdd").click(function(){
		var name = $("#preValueName").val();
		var code = $("#preValueCode").val();
		//添加规则判断
		//非空判断
		var check = true;
		if (name == "" || code == "") {
            alert("[预设置值编码]和[预设置值名称]不能为空值");
            return false;
        }
		//判断是否有数据重复...... start
		$.each($("#preValuesTable tr"),function(i,n){
			$.each($(n).find("td"),function(count,o){
				if(name == $(o).html() || code == $(o).html()){
					alert("该数据已经存在,请重新输入!");
					//清空预设值输入框
					$("#preValueName").val("");
					$("#preValueCode").val("");
					check = false;
					return false;
				}
			})
		})
		
		//判断是否有数据重复...... end
		if(check){
			var $table=$("#preValuesTable tr");
			var len=$table.length;		
            $("#preValuesTable").append("<tr id="+(len+1)+"><td align=\'center\'><input type=\'text\' name=\'preValues["+(len-1)+"&com.gpcsoft.srplatform.sysconfig.domain.SysConfigItemPreValue].name\' value=\'"+name+"\'/></td><td align=\'center\'><input type=\'text\' name=\'preValues["+(len-1)+"&com.gpcsoft.srplatform.sysconfig.domain.SysConfigItemPreValue].code\' value=\'"+code+"\' /></td><td align=\'center\'><a href=\'#\' onclick=\'sysconfigItemAdd.deltr("+(len+1)+")\'>删除</a></td></tr>");
		}
	});
});

//删除预设值
sysconfigItemAdd.deltr = function(index){
	$table=$("#preValuesTable tr");
	if(index>$table.length)
		return;
	else{
		$("tr[id=\'"+index+"\']").remove();	
	}	
}


