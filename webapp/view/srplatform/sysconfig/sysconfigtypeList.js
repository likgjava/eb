//包组区分JS方法、属性的唯一性
var sysconfigtypeList={};
var sysconfigtypeTree;

//鼠标左键点击事件
sysconfigtypeList.sysconfigtypeClick = function(id){
	if(!id || id == -1){
		$("#sysconfigItemListViewLi").hide();
	}else{
		$("#sysconfigItemListViewLi").show();
	}
	//将公有的"当前对象ID"赋值
	$("#curObjId").val(id);
	//第一个TAB页选中效果
	$('#tabInfo').tabs('select', 0);
	$("#tabform").empty().loadPage("SysConfigController.do?method=toSysconfigTypeDetailView&objId="+id);
}

//新增系统配置类型页面
sysconfigtypeList.addSysConfigTypeForm = function(parentObjId){
	//将公有的"当前对象的父类ID"赋值
	$("#parentObjId").val(parentObjId);
	$("#tabform").empty().loadPage("SysConfigController.do?method=toSysconfigTypeAdd&parentObjId="+parentObjId);
}

//删除系统配置类型操作
sysconfigtypeList.delSysConfigTypeForm = function(objId){
	$.getJSON($('#initPath').val()+'/SysConfigController.do?method=deleteSysConfigType&objId='+objId,null, function(json){
		if(json.failure){
			alert(json.result);
			return;
		}
		if(json.result){
			alert(json.result,{inco:'info'});;
		}
		// 保存成功后清除页面信息,并刷新树  start
		// sysconfigtypeTree是 view/srplatform/sysconfig/sysconfigtypeList.js 中定义的树
		sysconfigtypeTree.deleteItem(objId,true);
		sysconfigtypeTree.setItemImage2($("#parentObjId").val(),"node.gif","folderOpen.gif","folderClosed.gif");
		//保存成功后清除页面信息,并刷新树  end
	});
}

//修改系统配置类型操作
sysconfigtypeList.updateSysConfigTypeForm = function(objId){
	//跳转到修改页面
	$("#tabform").empty().loadPage($('#initPath').val()+"/SysConfigController.do?method=toSysconfigTypeUpdate&objId="+objId);
	
}


//右键菜单,e为兼容FireFox
sysconfigtypeList.rightKeyTree = function(id,e){
	//选中当前焦点   start
	var node=sysconfigtypeTree._globalIdStorageFind(id);
	sysconfigtypeTree._selectItem(node,e);
	//选中当前焦点   end
	//调用选中事件
	sysconfigtypeList.sysconfigtypeClick(id);
	$('.epsContentMenu').epsContentMenu(id,{
      menuEv: {

        'add': function(t,treeid) {
			sysconfigtypeList.addSysConfigTypeForm(id);
        },

        'authorize': function(t,treeid) {
        	sysconfigtypeList.updateSysConfigTypeForm(id);
        },
        
        'del': function(t,treeid) {
        	sysconfigtypeList.delSysConfigTypeForm(id);
        },
        'exportStaticClass': function(t,treeid) {
        	location.href($('#initPath').val()+'/SysConfigController.do?method=exportStaticClass&type=config&objIds='+id);
        },
        'exportPropertysFile': function(t,treeid) {
        	location.href($('#initPath').val()+'/SysConfigController.do?method=exportPropertysFile&type=config&objIds='+id);
        },
		'exportSQL': function(t,treeid) {
			location.href($('#initPath').val()+'/SysConfigController.do?method=exportSql&type=config&objIds='+id);
		}
      }
    },e);
}

//加载系统配置类型树
sysconfigtypeList.loadSysconfigTypeTree = function(){
	sysconfigtypeTree = new dhtmlXTreeObject("sysConfigTypeTree","100%","100%",0);
	sysconfigtypeTree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	sysconfigtypeTree.enableThreeStateCheckboxes(false);
	sysconfigtypeTree.setOnClickHandler(sysconfigtypeList.sysconfigtypeClick);
	sysconfigtypeTree.setOnRightClickHandler(sysconfigtypeList.rightKeyTree);
	sysconfigtypeTree.loadXML($("#initPath").val()+"/SysConfigController.do?method=getSysconfigtypeTree&objId="+$("#objid").val(), function(){
		sysconfigtypeTree.selectItem("-1");
	});
	sysconfigtypeTree.setXMLAutoLoading("SysConfigController.do?method=getSysconfigtypeTree");
}

sysconfigtypeList.reLoadSysconfigTypeTree = function(){
	$("#sysConfigTypeTree").empty();
	sysconfigtypeList.loadSysconfigTypeTree();
	//默认选中根节点
	sysconfigtypeList.sysconfigtypeClick("-1");
}

$(document).ready(function(){
	if(!$("#curObjId").val() || $("#curObjId").val() == '-1' ){
		$("#sysconfigItemListViewLi").hide();
	}else{
		$("#sysconfigItemListViewLi").show();
	}
	//选择配置类型事件

	$("#sysconfigTypeDetailView").click(function(){
		$("#tabform").empty().loadPage("SysConfigController.do?method=toSysconfigTypeDetailView&objId="+$("#curObjId").val());
	})

	//查看条目信息
	$("#sysconfigItemListView").click(function(){
		if(!$("#curObjId").val()){
			alert("请选择相关的配置类型,才允许查看条目信息!");
		}else{
			$("#tabform").empty().loadPage("SysConfigController.do?method=toSysconfigItemListView&sysConfigTypeId="+$("#curObjId").val());
		}
	})
	$('#tabInfo').tabs();
	
	//初始化系统配置类型树
	sysconfigtypeList.loadSysconfigTypeTree();
	
	//默认点击
	$("#sysconfigTypeDetailView").click();
})


