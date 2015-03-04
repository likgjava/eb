var congruousFactorTypeList={};
$('#next').click(function(){
	var jsonObj = formToJsonObject('congruousFactorTypeForm','jsonUtils');
	var saveObj = new Array();
	var tempObj = new Array();
	$.each(jsonObj.congruousFactorType,function(i,n){
		if(n != undefined && null != n){
			tempObj.push(n);
		}
	})
	var sum = 0;
	$('#congruousFactorTypeForm :checkbox[checked=true]').each(function(i,n){
		var num = n.value*1-1;
		saveObj.push(tempObj[num]);
		if(undefined != tempObj[num].weightCoefficient && null != tempObj[num].weightCoefficient && '' != tempObj[num].weightCoefficient){
			sum += tempObj[num].weightCoefficient * 1
		}else{
			tempObj[num].weightCoefficient = 0;
		}
	})
	if(!$('#congruousFactorTypeForm').valid()){alert('请正确填写表单!');return;}
	if(sum == 100 || sum == 0){
		$.getJSON($('#initPath').val()+'/CongruousFactorTypeController.do?method=updateCongruousFactorType',{"congruousFactorTypes":JSON.stringify(saveObj)}, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			planTemplateTask.clickMethod($('#projectTaskId').val());
		});
	}else{
		alert("当前权重总和为"+sum+",权重总应等于100,请修改权重..");
	}
})
// 维护指标
$('#congruousFactor').click(function(){
	var projectId = $("#project_objId").val();
	$('#projectDoDiv').empty().loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toCongruousFactorListView&projectId="+projectId);
})

// 绑定事件,添加表单
congruousFactorTypeList.bindElementEvent = function(){
	$('#congruousFactorTypeForm input[type=text]').unbind();
	$('#congruousFactorTypeForm input[type=text]').keyup(function(){
		var num = 0;
		$('#congruousFactorTypeForm input[type=text]').each(function(i,n){
			if(null != n.value && '' != n.value){
				num ++;
			}
		})
		if(num*1 == $('#congruousFactorTypeForm input[type=text]').length-1){
			congruousFactorTypeList.addForm();
		}
		var name = $(this).attr("name");
		if(name.replace("typeName","").length < name.length){
			congruousFactorTypeList.controllerAutoRequired(this);
		}
	})
}

// 必填项控制
congruousFactorTypeList.controllerRequired=function(){
	$('#congruousFactorTypeForm input[type=checkbox]').click(function(){
		if(this.checked){
			$(this).parent().parent().find('input[type=text]').addClass('required');
			$(this).parent().parent().find('input[type=text][name$=weightCoefficient]').addClass('digits');
		}else{
			$(this).parent().parent().find('input[type=text]').removeClass('required');
			$(this).parent().parent().find('input[type=text][name$=weightCoefficient]').removeClass('digits');
		}
	})
}
//必填项自动控制
congruousFactorTypeList.controllerAutoRequired = function(obj){
	var value = $(obj).val().toString();
	if(value.length>0){
		if(!$(obj).hasClass('required')){
			$(obj).parent().parent().find('input[type=checkbox]').attr("checked",true);
			$(obj).parent().parent().find('input[type=text]').addClass('required');
			$(obj).parent().parent().find('input[type=text][name$=weightCoefficient]').addClass('digits');
		}
	}else{
		if($(obj).hasClass('required')){
			$(obj).parent().parent().find('input[type=checkbox]').attr("checked",false);
			$(obj).parent().parent().find('input[type=text]').removeClass('required');
			$(obj).parent().parent().find('input[type=text][name$=weightCoefficient]').removeClass('digits');
		}
	}
}
// 添加表单
congruousFactorTypeList.addForm = function(){
	var num = $('#num').val()*1+1;
	var html = '';
	html += '<tr>';
	html += '<td style="text-align: center;"><input type="checkbox" value="'+num+'" /></td>';
	html += '<td>';
	html += '<input type="text" name="congruousFactorType['+num+'].typeName" value="" style="width: 160px;"/>';
	html += '<span class="eleRequired"></span>';
	html += '</td>';
	html += '<td>';
	html += '<input type="text" name="congruousFactorType['+num+'].weightCoefficient" value="" style="width: 60px;" class="digits" min="0" max="100" />';
	html += '<span class="eleRequired"></span>';
	html += '</td>';
	html += '<td>';
	html += '<input type="hidden" name="congruousFactorType['+num+'].objId" value="" />';
	html += '<input type="hidden" name="congruousFactorType['+num+'].sort" value="'+num+'" />';
	html += '<input type="hidden" name="congruousFactorType['+num+'].projId" value="'+$('#project_objId').val()+'" />';
	html += '<input type="hidden" name="congruousFactorType['+num+'].typeCode" value="" />';
	html += '<button onclick="congruousFactorTypeList.upMoveCongruousFactorType(this);" class="sysicon siUp" title="上移 指标分类" option="up"><span></span></button>';
	html += '<button onclick="congruousFactorTypeList.downMoveCongruousFactorType(this);" class="sysicon siDown" title="下移 指标分类" option="down"><span></span></button>';
	html += '<button onclick="congruousFactorTypeList.removceCongruousFactorType(this);" class="sysicon siDelete" title="删除 指标分类" option="delete"><span></span></button>';
	html += '</td>';
	html += '</tr>';
	$('#num').val(num)
	$('#congruousFactorTypeForm table').append(html);
	congruousFactorTypeList.bindElementEvent();
	congruousFactorTypeList.controllerRequired();
	congruousFactorTypeList.order();
}
// 上移
congruousFactorTypeList.upMoveCongruousFactorType = function(obj){
	var thisTr = $(obj).parent().parent();
	var prevTr = $(thisTr).prev('tr');
	congruousFactorTypeList.swapNode(thisTr,prevTr);
}
// 下移
congruousFactorTypeList.downMoveCongruousFactorType = function(obj){
	var thisTr = $(obj).parent().parent();
	var nextTr = $(thisTr).next('tr');
	congruousFactorTypeList.swapNode(thisTr,nextTr);
}
// 删除
congruousFactorTypeList.removceCongruousFactorType = function(obj){
	$(obj).parent().parent().remove();
	congruousFactorTypeList.order();
}
//交换节点位置
congruousFactorTypeList.swapNode = function(thisObj,moveObj){
	var temp = $(thisObj).html();
	$(thisObj).html($(moveObj).html());
	$(moveObj).html(temp);
	congruousFactorTypeList.order();
	congruousFactorTypeList.bindElementEvent();
}
// 列表排序
congruousFactorTypeList.order = function(){
	var size = $('#congruousFactorTypeForm tr').length-1;
	$('#congruousFactorTypeForm tr').each(function(i,n){
		if($(n).find('td').length>0){
			var index = $(n).attr("rowIndex") * 1;
			$(n).find('input[type=checkbox]').val(index);
			$(n).find('input[name$=sort]').val(index);
			if (index == 2) {
				$(n).find('button[option=up]').hide();
			}else if(index == size){
				$(n).find('button[option=down]').hide();
				$(n).find('button[option=delete]').hide();
			}else{
				$(n).find('button').show();
			}
		}
	})
}
// 选中列表
congruousFactorTypeList.checked = function(){
	$('#congruousFactorTypeForm tr').each(function(i,n){
		if($(n).find('td').length>0){
			var objId = $(n).find('input[type=hidden][name$=objId]').val();
			if(undefined != objId && null != objId && '' != objId){
				$(n).find('input[type=checkbox]').attr("checked",true);
				$(n).find('input[type=text]').addClass('required');
				$(n).find('input[type=text][name$=weightCoefficient]').addClass('digits');
			}
		}
	})
}
$(document).ready(function(){
	congruousFactorTypeList.bindElementEvent();// 绑定事件
	congruousFactorTypeList.controllerRequired();// 必填项控制
	congruousFactorTypeList.order();// 列表排序 按钮控制
	congruousFactorTypeList.checked();
})