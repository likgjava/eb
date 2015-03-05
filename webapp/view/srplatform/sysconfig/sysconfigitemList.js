//包组区分JS方法、属性的唯一性
var sysConfigItemList={};
sysConfigItemList.rows=null;//列表查询的结果集
sysConfigItemList.add=function(name,grid){
	var sysConfigTypeId = $("#curObjId").val();
	$("#tabform").empty().loadPage($('#initPath').val()+"/SysConfigController.do?method=toSysconfigItemFormView&sysconfigTypeId="+sysConfigTypeId+"&objId=");
}    
sysConfigItemList.remove=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择系统配置条目'+name);return false;}
	if(window.confirm('确定'+name+'?')){
		$.getJSON($('#initPath').val()+'/SysConfigController.do?method=delSysItemByIds',{objIds:$(grid).getSelects()},function(json){
			if(json.result)alert(json.result);
			if(json.failure)return;
			$(grid).reload();
		});
	}
}
sysConfigItemList.exportStaticClass = function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择系统配置条目'+name);return false;}
	location.href($('#initPath').val()+'/SysConfigController.do?method=exportStaticClass&type=item&objIds='+$(grid).getSelects());
}
sysConfigItemList.exportSQL = function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择系统配置条目'+name);return false;}
	location.href($('#initPath').val()+'/SysConfigController.do?method=exportSql&type=item&objIds='+$(grid).getSelects());
}
sysConfigItemList.exportPropertysFile = function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择系统配置条目'+name);return false;}
	location.href($('#initPath').val()+'/SysConfigController.do?method=exportPropertysFile&type=item&objIds='+$(grid).getSelects());
}
sysConfigItemList.addValue=function(name,grid){
	if(!sysConfigItemList.validation(name,grid))return;
	var json = $(grid).getRowById($(grid).getSelect()); 
	$("#tabform").empty().loadPage($('#initPath').val()+"/SysConfigController.do?method=toItemValueAdd&itemId="+$(grid).getSelect()+"&dataType="+json.dataType);
}  
sysConfigItemList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择系统配置条目'+name);return false;}
	if(!$(grid).isSelectOne()){alert('请选择系统配置条目'+name);return false;}
	return true;
}

sysConfigItemList.initSysConfigItem = function(name,grid){
	var sysConfigTypeId = $("#curObjId").val();
	$.getJSON($('#initPath').val()+'/SysConfigController.do?method=initSysConfigItem',{"sysConfigTypeId":sysConfigTypeId},function(json){
		$(grid).reload();
	});
}

sysConfigItemList.before=function(){
	var option={
		
	}
	$('#sysConfigItemGrid').flexOptions({params:option});
	return true;
}
sysConfigItemList.success=function(){
	$("#sysConfigItemGrid").flexAddOptionStr({
		'<button class="sysicon cog" title="配置条目数据" type="button"><span>配置条目数据</span></button>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#sysConfigItemGrid").getRowById(rowId); 
				$("#tabform").empty().loadPage($('#initPath').val()+"/SysConfigController.do?method=toItemValueAdd&itemId="+rowId+"&dataType="+json.dataType);
			}).appendTo(obj);
		},
		'<button class="sysicon siModify" title="修改" type="button"><span>修改</span></button>' : function(btn,rowId,obj){
			btn.click(function(){
				$("#tabform").empty().loadPage($('#initPath').val()+"/SysConfigController.do?method=toSysconfigItemFormView&objId="+rowId+"&sysconfigTypeId=");
			}).appendTo(obj);
		},
		'<button class="sysicon siDelete" title="删除" type="button"><span>删除</span></button>' : function(btn,rowId,obj){
			btn.click(function(){
				if(window.confirm('确定删除吗?')){
					$.getJSON($('#initPath').val()+'/SysConfigController.do?method=delSysItemByIds',{"objIds":rowId},function(json){
						if(json.result)alert(json.result);
						if(json.failure)return;
						$("#sysConfigItemGrid").reload();
					});
				}
			}).appendTo(obj);
		},
		'<button class="sysicon siDownBtn" title="导出常量类" type="button"><span>导出常量类</span></button>' : function(btn,rowId,obj){
			btn.click(function(){
				location.href($('#initPath').val()+'/SysConfigController.do?method=exportStaticClass&type=item&objIds='+rowId);
			}).appendTo(obj);
		},
		'<button class="sysicon siDownGray" title="导出属性文件" type="button"><span>导出属性文件</span></button>' : function(btn,rowId,obj){
			btn.click(function(){
				location.href($('#initPath').val()+'/SysConfigController.do?method=exportPropertysFile&type=item&objIds='+rowId);
			}).appendTo(obj);
		},
		'<button class="sysicon report_go" title="导出SQL" type="button"><span>导出SQL</span></button>' : function(btn,rowId,obj){
			btn.click(function(){
				location.href($('#initPath').val()+'/SysConfigController.do?method=exportSql&type=item&objIds='+rowId);
			}).appendTo(obj);
		}
	});
}