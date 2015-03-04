var expertUnit = {};
var unitIds = '';//单位Ids
var unitNames = '';//单位名称
var unitType_Id = '';//类型

expertUnit.backFillCheckBoxValue=function(id,unitType){//将得到的数字拆分并判断选择复选框
	
	if (unitType=='00') {//采购单位
		unitIds = $('#buyerNameIds_Ids').val();
		unitNames = $('#buyerNames_Id').val();
		var names = unitNames.split(',');
		var html='';
		if (unitIds!=null&&unitIds!=""&&unitIds!=undefined) {
			$.each(unitIds.split(','),function(i,n){
				$("#"+id+n).attr("checked",true);
				var name = names[i];
				html += '<span id='+n+'>';
				html += '<span id="name_'+n+'" style="cursor:hand;color: blue" onClick="expertUnit.delUnit('+n+')">'+name+'</span>';
				html += '<span onClick="expertUnit.delUnit('+n+')" class="sysicon siDelete" style="cursor:hand;color: red">&nbsp;&nbsp;&nbsp;&nbsp;</span>';
				html += '</span>';
			});
			$('#unit_Id').val(unitIds);
			$('#unit_Name').val(unitNames);
			$('#chooseUnit').removeClass('styleRC');
			$('#warnId').remove();
			$("#chooseUnit").append(html);
		}
	}else if (unitType=='01') {//回避单位
		unitIds = $('#outBuyerIds_Id').val();
		unitNames = $('#outBuyerNames_Id').val();
		var names = unitNames.split(',');
		var html='';
		if (unitIds!=null&&unitIds!=""&&unitIds!=undefined) {
			$.each(unitIds.split(','),function(i,n){
				$("#"+id+n).attr("checked",true);
				var name = names[i];
				html += '<span id='+n+'>';
				html += '<span id="name_'+n+'" style="cursor:hand;color: blue" onClick="expertUnit.delUnit('+n+')" title="'+name+'">'+name+'</span>';
				html += '<span onClick="expertUnit.delUnit('+n+')" class="sysicon siDelete" style="cursor:hand;color: red" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;</span>';
				html += '</span>';
			});
			$('#unit_Id').val(unitIds);
			$('#unit_Name').val(unitNames);
			$('#chooseUnit').removeClass('styleRC');
			$('#warnId').remove();
			$("#chooseUnit").append(html);
		}
	}
}

expertUnit.delUnit = function(id){//删除一个单位
	var name = $('#name_'+id).text();
	if(window.confirm("确定要删除")){
		expertUnit.checkValueN(id,name);
		$("#unit_"+id).attr("checked",false);//删除显示一行
	}
}

expertUnit.checkValue=function(id,name){//点击CheckBox触发事件
	if ($('input[id="unit_'+id+'"]:checked').val()) {//判断是否选中：选中
		$('#chooseUnit').removeClass('styleRC');
		$('#warnId').remove();
		expertUnit.checkValueY(id,name);
	} else {//没有选中
		expertUnit.checkValueN(id,name);
	}
}

expertUnit.checkValueY = function(id,name){//处理选中的单位
	var unitId = $('#unit_Id').val();
	var unitName = $('#unit_Name').val();
	var chooseId = new Array();
	var chooseName = new Array();
	if (unitId!=null&&unitId!=""&&unitId!=undefined) {
		chooseId.push(unitId);
	}
	chooseId.push(id);
	if (unitName!=null&&unitName!=""&&unitName!=undefined) {
		chooseName.push(unitName);
	}
	chooseName.push(name);
	$('#unit_Id').val(chooseId.toString());
	$('#unit_Name').val(chooseName.toString());
	var html='';
	html += '<span id='+id+'>';
	html += '<span id="name_'+id+'" style="cursor:hand;color: blue" onClick="expertUnit.delUnit('+id+')" title="'+name+'">'+name+'</span>';
	html += '<span onClick="expertUnit.delUnit('+id+')" class="sysicon siDelete" style="cursor:hand;color: red" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;</span>';
	html += '</span>';
	$("#chooseUnit").append(html);
}

expertUnit.checkValueN = function(id,name){//处理取消选中的单位
	var unitId = $('#unit_Id').val();
	var unitName = $('#unit_Name').val();
	var chooseId = new Array();
	var chooseName = new Array();
	$.each(unitId.split(','),function(i,n){//循环已经存在的单位Id
		if (n!=id) {
			chooseId.push(n);
		}
	});
	$.each(unitName.split(','),function(i,n){//循环已经存在的单位Name
		if (n!=name) {
			chooseName.push(n);
		}
	});
	$('#unit_Id').val(chooseId.toString());
	$('#unit_Name').val(chooseName.toString());
	$('#'+id).remove();
	if ($('#unit_Id').val()==""||$('#unit_Id').val()==null||$('#unit_Id').val()==undefined) {//如果没有选中的
		$('#chooseUnit').addClass('styleRC');
		var html='<span id="warnId">暂时没有选择单位</span>';
		$("#chooseUnit").append(html);
	}
}

$(document).ready(function(){
	
	unitType_Id = $('#unitType_Id').val();
	if (unitType_Id=='00') {//采购单位
		expertUnit.backFillCheckBoxValue('unit_',unitType_Id);//默认选中采购单位
	}else if (unitType_Id=='01') {//回避单位
		expertUnit.backFillCheckBoxValue('unit_',unitType_Id);//默认选中回避单位
	}
	
	$('#sureUnit').click(function(){//确定
		var unit_Name = $('#unit_Name').val();//单位名称
		if(unit_Name!=''){
			if(window.confirm("确认所选单位?")){
				var unitType = $('#unitType_Id').val();
				var unit_Id = $('#unit_Id').val();//单位Id
				if (unitType=='00') {//采购单位
					$('#buyerNames_Id').val(unit_Name);
					$('#buyerNameIds_Ids').val(unit_Id);
					$("#closeUnit").click();
				}else {//回避单位
					$('#outBuyerNames_Id').val(unit_Name);
					$('#outBuyerIds_Id').val(unit_Id);
					$("#closeUnit").click();
				}
			}
		}else {
			alert("请选择回避单位！");
			return false;
		}
	});
	$('#sureUnitUp').click(function(){//确定
		if(window.confirm("确认所选单位?")){
			var unitType = $('#unitType_Id').val();
			var unit_Id = $('#unit_Id').val();//单位Id
			var unit_Name = $('#unit_Name').val();//单位名称
			if (unitType=='00') {//采购单位
				$('#buyerNames_Id').val(unit_Name);
				$('#buyerNameIds_Ids').val(unit_Id);
				$("#closeUnit").click();
			}else {//回避单位
				$('#outBuyerNames_Id').val(unit_Name);
				$('#outBuyerIds_Id').val(unit_Id);
				$("#closeUnit").click();
			}
		}
	});
	
	$('#searchUnitId').click(function(){
		var unitType_Id = $('#unitType_Id').val();
		var unitIds = '';
		if (unitType_Id=='00') {//判断是采购单位还是回避单位
			unitIds = $('#buyerNameIds_Ids').val();
		}else if (unitType_Id=='01') {
			unitType_Id = $('#outBuyerIds_Id').val();
		}
		$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=searchUnitByName',{'unitName':$('#unitName_Id').val()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if("[]" != json.unitArr){
    			var html = '';
    			$.each(json.unitArr,function(i,n){
        			html += '<tr>';
        				html += '<td style="text-align:center;"><input type="checkbox" onclick="expertUnit.checkValue(\''+n.column_value+'\',\''+n.column_name+'\')" id="unit_'+n.column_value+'" name="unitArr" value="'+n.column_value+'" column_name="'+n.column_name+'"/></td>';
        				html += '<td>'+n.column_name+'</td>';
        			html += '</tr>';
        		});
    			$("#expertRuleTableId").empty().append(html);
    			$('#chooseUnit').addClass('styleRC');
    			var html='<span id="warnId">暂时没有选择单位</span>';
    			$("#chooseUnit").empty().append(html);
    			$('#unit_Id').val('');
    			$('#unit_Name').val('');
    		}
		});
	});
	
})

//关闭弹出层
$("#closeUnit").click(function(){
	$('#epsDialogCloseNoReload').click();
});
//关闭弹出层
$("#closeUnitUp").click(function(){
	$('#epsDialogCloseNoReload').click();
});