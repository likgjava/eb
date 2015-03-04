var factortypeDeList={};
factortypeDeList.objId;
// 加载树
factortypeDeList.initMenuTree = function(objId){
	$("#menuTree").empty();
	factortypeDeList.menutree=new dhtmlXTreeObject("menuTree","100%","100%",0);
	factortypeDeList.menutree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	factortypeDeList.menutree.enableThreeStateCheckboxes(true);
	factortypeDeList.menutree.setOnClickHandler(factortypeDeList.menutreeLeftClick);
	factortypeDeList.menutree.setXMLAutoLoading("FactortypeDeController.do?method=getTree&order=sort&action=listById",function(){
		
	});
	factortypeDeList.menutree.loadXML($("#initPath").val()+"/FactortypeDeController.do?method=getTree&order=sort&action=listTop&id=0",function(){
		factortypeDeList.menutree.selectItem(objId);
	});
}

// 指标分类表单
factortypeDeList.laodFactortypeDeFormView = function(objId,parentId){
	if("-1" == objId){
		objId = "";
	}
	if("-1" == parentId){
		parentId = "";
	}
	if(undefined == parentId){
		parentId = "";
	}
	if(null == objId || "" == objId){
		$("#factor_type_form").empty().loadPage($("#initPath").val()+"/FactortypeDeController.do?method=toCreateFactortypeDeFormView&objId=&parentObjId="+parentId);
	}else{
		$("#factor_type_form").empty().loadPage($("#initPath").val()+"/FactortypeDeController.do?method=toUpdateFactortypeDeFormView&objId="+objId+"&parentObjId="+parentId);
	}
}

// 指标列表
factortypeDeList.laodFactorListView = function(factortypeId){
	if("" == factortypeId || "-1" == factortypeId){
		$("#menu_Form").empty();
		return false;
	}else{
		$.getJSON($("#initPath").val()+"/FactortypeDeController.do?method=getObjectQuery&queryColumns=objId",{"parent.objId":factortypeId},function(json){
			if(json.result.length == 0){
				$("#menu_Form").empty().loadPage($("#initPath").val()+"/FactorDeController.do?method=toList&factortypeId="+factortypeId);
			}else{
				$("#menu_Form").empty();
			}
		})
	}
}
factortypeDeList.menutreeLeftClick = function(id){
	factortypeDeList.objId = id;
	factortypeDeList.laodFactortypeDeFormView(id,"");
	factortypeDeList.laodFactorListView(id);
}

// 新增
$("#newFactortypeDe").click(function(){
	factortypeDeList.laodFactortypeDeFormView("",factortypeDeList.objId);
	factortypeDeList.laodFactorListView("");
})
// 删除
$("#deleteFactortypeDe").click(function(){
	if("" != factortypeDeList.objId && "-1" != factortypeDeList.objId){
		$.getJSON($("#initPath").val()+"/FactortypeDeController.do?method=getObjectQuery&queryColumns=objId",{"parent.objId":factortypeDeList.objId},function(json){
			if(json.result.length == 0){
				$.getJSON($('#initPath').val()+'/FactortypeDeController.do?method=removeFactortypeDe', {"objId":factortypeDeList.objId}, function(json){
					if(json.failure){alert(json.result);return;}
					factortypeDeList.initMenuTree("-1")
					factortypeDeList.laodFactorListView("");
					factortypeDeList.laodFactortypeDeFormView("","");
				});
			}else{
				alert("请先删除叶子节点,在进行此操作.");
			}
		})
	}
})
$(document).ready(function(){
	factortypeDeList.initMenuTree("-1");
	factortypeDeList.laodFactortypeDeFormView("","");
});

