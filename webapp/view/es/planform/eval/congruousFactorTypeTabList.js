var congruousFactorTypeList={};
var congruousFactorTypeTree;
congruousFactorTypeList.congruousFactorTypeClick = function(id){
	//将公有的"当前对象ID"赋值
	$("#curObjId").val(id);
	if(id==-1){
		if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
	    	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
	   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	    }
		return false;
	}
	//统计项目金额
	$.getJSON($('#initPath').val()+'/CongruousFactorTypeController.do?method=getCongruousFactorTypeById&objId='+id,{},function(json){
		$("#reset").click();
		$("#typeCode").val(json.CongruousFactorType.typeCode);
		$("#typeName").val(json.CongruousFactorType.typeName);
		$("#remark").val(json.CongruousFactorType.remark);
		if(json.CongruousFactorType.isLeaf=='true'){
			$("#isLeaf").val(1);
		}else{
			$("#isLeaf").val(0);
		}
		$("#sort").val(json.CongruousFactorType.sort);
		$("#level").val(json.CongruousFactorType.level);
		$("#objId").val(json.CongruousFactorType.objId);
		$("#weightCoefficient").val(json.CongruousFactorType.weightCoefficient);
		$("#cur_weightCoefficient").val(json.CongruousFactorType.weightCoefficient);
	});
	$.getJSON($('#initPath').val()+'/CongruousFactorTypeController.do?method=getParentId&objId='+id,{},function(json){
		$("#cur_parentId").val(json.parentId);
	});
	var typeId = $("#curObjId").val();
	if(typeId==""||typeId==-1){alert("根节点不能维护指标 !");return false;}
	var isShowScore = $("#cur_weightCoefficient").val();
	var isJBR = $("#isJBR").length;
	if(isJBR>0){
		$('#treeRight').empty().loadPage($('#initPath').val()+"/CongruousFactorController.do?factorTypeId="+typeId+"&projectId="+$('#project_objId').val()+"&isShowScore="+isShowScore);
	}else{
		$('#treeRight').empty().loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toCongruousFactorList2&factorTypeId="+typeId+"&projectId="+$('#project_objId').val()+"&isShowScore="+isShowScore);
	}
	
}
$(document).ready(function(){
	$('#tabs').tabs();
	congruousFactorTypeList.loadCongruousFactorTypeTree = function(typeCode){
		congruousFactorTypeTree = new dhtmlXTreeObject("congruousFactorTypeTree","100%","100%",0);
		congruousFactorTypeTree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
		congruousFactorTypeTree.enableThreeStateCheckboxes(false);
		congruousFactorTypeTree.setOnClickHandler(congruousFactorTypeList.congruousFactorTypeClick);
		congruousFactorTypeTree.loadXML($("#initPath").val()+"/CongruousFactorTypeController.do?method=getCongruousFactorTypeTree&projectId="+$("#project_objId").val()+"&typeCode="+typeCode+"&objId="+$("#objid").val(), function(){
			congruousFactorTypeTree.selectItem("-1");
		});
		congruousFactorTypeTree.setXMLAutoLoading("CongruousFactorTypeController.do?method=getCongruousFactorTypeTree");
	}
	congruousFactorTypeList.loadCongruousFactorTypeTree();
	$("#newRes").click(function(){
		$("#objId").val("");
		$("#typeCode").val("");
		$("#typeName").val("");
		$("#weightCoefficient").val("");
		$("#remark").val("");
		$("#sort").val("");
		if($("#curObjId").val()==-1){
			$("input[id='parent.objId']").val("");
		}else{
			$("input[id='parent.objId']").val($("#curObjId").val());
		}
		$("#projId").val($("#project_objId").val());
		$("#isLeaf").val(1);
	})
	$("#saveResource").click(function(){
		$("#projId").val($("#project_objId").val());
		if($("#objId").val()!=""&&$("#objId").val()!=null){
			$("input[id='parent.objId']").val($("#cur_parentId").val());
		}
		$.getJSON($('#initPath').val()+'/CongruousFactorTypeController.do?method=checkTypecodeUnique', {"typeCode":$("#typeCode").val(),"projectId":$("#projId").val(),"update":"false"}, function(json){
			if(json.notUnique == 'true'){
				$("#typeCodeCheck").text("编号不得重复，请重新输入！");
			}else{
				$("#typeCodeCheck").text("*");
				if(!$("#congruousFactorTypeForm").valid()){
					alert("请正确填写表单!");return false;
				}
				$.getJSON($('#initPath').val()+'/CongruousFactorTypeController.do?method=saveCongruousFactorType', formToJsonObject('congruousFactorTypeForm'), function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					alert("保存成功！");
					$("#congruousFactorTypeTree").empty();
					congruousFactorTypeList.loadCongruousFactorTypeTree();
					congruousFactorTypeList.congruousFactorTypeClick("-1");
				});
			}
		})
	
	})
	$("#weihu").click(function(){
		var typeId = $("#curObjId").val();
		if(typeId==""||typeId==-1){alert("根节点不能维护指标 !");return false;}
		var isShowScore = $("#cur_weightCoefficient").val();
		$('#treeRight').empty().loadPage($('#initPath').val()+"/CongruousFactorController.do?factorTypeId="+typeId+"&projectId="+$('#project_objId').val()+"&isShowScore="+isShowScore);		
	})
	$("#deleteResource").click(function(){
		var objId = $("#curObjId").val();
		if(objId==""||objId==-1){alert("根节点不能删除 !");return false;}
		$.getJSON($('#initPath').val()+'/CongruousFactorTypeController.do?method=removeCongruousFactorType&objId='+objId,{}, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			alert("删除成功！");
			$("#congruousFactorTypeTree").empty();
			congruousFactorTypeList.loadCongruousFactorTypeTree();
			congruousFactorTypeList.congruousFactorTypeClick("-1");
		})
	})
})