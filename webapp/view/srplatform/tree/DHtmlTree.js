var SearchTree={};

//点击节点
SearchTree.clickNode = function(objId){
	if(objId==null || $.trim(objId)=='' || objId=="-1"){return ;}
	
	//只能选择叶子节点
	if($("#_childNodeOnly").val()=='true' && SearchTree.tree.hasChildren(objId)!=0){
		alert("请选择叶子节点（白色节点）！"); return;
	}
	
	//单选
	if($.trim($("#_isCheckBox").val())=="" || $("#_isCheckBox").val()=="null"){
		var whileId = objId;
		var names = SearchTree.tree.getItemText(whileId);
		
		//选择所有父节点的id,name
		if($("#_andAllParent").val()=='true'){
			while( SearchTree.tree.getParentId(whileId)!='-1' ){
				whileId = SearchTree.tree.getParentId(whileId);
				names =  SearchTree.tree.getItemText( whileId ) + ',' + names ;
				objId = whileId + ',' + objId;
			}
		}
		$('#selectedResultList').empty();
		addCheckedItem(objId, names);
	}
}

SearchTree.unableCheckIfNotLeaf = function(objId){
	
	$.each(SearchTree.tree.getAllSubItems(objId).split(","),function(index,obj){
		if(SearchTree.tree.hasChildren(obj)!=0){
			SearchTree.tree.disableCheckbox(obj,1);
		}
	})
	
}
	
SearchTree.nodeOpen = function(id){
	$.each(SearchTree.tree.getAllSubItems(id).split(","),function(index,obj){
		if(id!=-1&&SearchTree.tree.hasChildren(obj)<=0){
			SearchTree.tree.disableCheckbox(id,0);
			var parentId = id;
			while(SearchTree.tree.getParentId(parentId)!='-1'){
				parentId = SearchTree.tree.getParentId(parentId);
				SearchTree.tree.disableCheckbox(parentId,0);
			}
			return false;
		}
	});
}

//添加目录树中的选中值
function addCheckedItem(id,name) {
	if(!hadInList(id)){
		var htmlStr = '<option value="'+id+'">'+name+'</option>';
		$('#selectedResultList').append(htmlStr);
	}
}

//判断是否在已选结果集中
function hadInList(newId){
	var hadExist = false;
	$("#selectedResultList option").each(function(index, domE){
		var id = $(domE).val();
		if(newId == id){
			hadExist = true;
			return true;
		}
	});
    return hadExist;
}

//添加搜索结果中的选中值
function addToList() {
	//单选
	if($.trim($("#_isCheckBox").val())==""||$("#_isCheckBox").val()=="null"){
    	$("#selectedResultList").empty();
	}
	
	$("#searchResultList option:selected").each(function(){
		 if(!hadInList($(this).val())) {
			 $("#selectedResultList").append('<option value="'+$(this).val()+'">'+$(this).text()+'</option>');
		 }
	});
}

//从选中列表中移除
function removeFromList(obj) {
	$("#selectedResultList option:selected").remove();
}

//回填结果
function returnValue(id,name){
	//回填id
	if("" == $("#_ID").val())
		document.getElementById($("#_property").val()+".objId").value=id;
	else
		document.getElementById($("#_ID").val()).value=id;
	
	//回填name
	if("" == $("#_NAME").val()){
		document.getElementById($("#_property").val()+".name").value=name;
		$(document.getElementById($("#_property").val()+".name")).keyup();
	}else{
		document.getElementById($("#_NAME").val()).value=name;
		$(document.getElementById($("#_NAME").val())).keyup();
	}
	
	$("#_cancel").click();
}

$(document).ready(function(){
	SearchTree.tree=new dhtmlXTreeObject('dialogTreeBox',"100%","100%",0); 
	SearchTree.tree.enableDragAndDrop(false);
	SearchTree.tree.setOnClickHandler(SearchTree.clickNode);
	SearchTree.tree.setDragHandler(false);
	
	//只能选子节点
	if($("#_childNodeOnly").val()=='true' && $("#_isCheckBox").val()=='true'){
		SearchTree.tree.setOnOpenEndHandler(function(id){
			SearchTree.unableCheckIfNotLeaf(id)
		});
		
		//打开时可以由父节点级联选择
		SearchTree.tree.setOnOpenEndHandler(SearchTree.nodeOpen);
	}
	
	//多选
	if($.trim($("#_isCheckBox").val())!="" && $("#_isCheckBox").val()!="null"){
		SearchTree.tree.enableCheckBoxes(true)
		//初始化按钮不级联选中 0为表示不选中,1为选中状态
		if($("#_checkStatus").val() == "") {
			SearchTree.tree.enableThreeStateCheckboxes(0);
		}else {
			SearchTree.tree.enableThreeStateCheckboxes($("#_checkStatus").val());
		}
	}
	//单选
	else{
		//隐藏‘=>’按钮
		$("#addSelectedTreeItems").hide();
	}

	SearchTree.tree.setImagePath($("#initPath").val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	SearchTree.tree.setXMLAutoLoading($("#initPath").val()+"/"+$("#_className").val()+"Controller.do?method=getTree&action=listById&_isOpen="+$("#_isOpen").val()+"&"+$("#_param").val());

	var url=$("#initPath").val()+"/"+$("#_className").val()+"Controller.do?method=getTree&action=listTop&_isOpen="+$("#_isOpen").val()+"&"+$("#_param").val();
	if($('#txtURL').val()!='')
		url=$("#txtURL").val();
	SearchTree.tree.loadXML(url,function(){
		if($.trim($("#_isCheckBox").val())!=""&&$("#_isCheckBox").val()!="null"){
			var objId = $("#_checkValues").val().split(",");
			if(objId != null && objId.length > 0){
				for(var i = 0 ; i < objId.length ; i ++){
					SearchTree.tree.setCheck(objId[i],1);
				}
			}
		}
		
		//只能选子节点 by yucy
		if($("#_childNodeOnly").val()=='true' && $("#_isCheckBox").val()=='true'){
			SearchTree.unableCheckIfNotLeaf(-1);
			SearchTree.tree.disableCheckbox(-1,1);
		}
		
		SearchTree.tree.disableCheckbox(-1,1);//根节点不可选
	});
	
	//清空选中的结果
	$("#_clear").click(function(){
		$('#selectedResultList').empty();
	})
	
	//关闭弹出层
	$("#_cancel").click(function(){
		if($("#_dialogID").val() != "")
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		else
			$('.epsDialogClose').trigger('click');
	})
	
	//确定
	$("#_OK").click(function(){
		var ids = '';
		var names = '';
		$('#selectedResultList').find("option").each(function(index){
			if(index != 0){
				ids += ",";
				names += ",";
			}
			ids += $(this).val();
			names += $(this).html();
		});
		returnValue(ids+"", names);
	})
	
	//显示旧值
	var _checkedIds = "";
	var _checkedNames = "";
	//获取已经选中的ID
	if("" == $("#_ID").val()){
		_checkedIds = document.getElementById($("#_property").val()+".objId").value;
	}else {
		_checkedIds = document.getElementById($("#_ID").val()).value;
	}
	//获取已经选中的name
	if("" == $("#_NAME").val()){
		_checkedNames = document.getElementById($("#_property").val()+".name").value;
	}else{
		_checkedNames = document.getElementById($("#_NAME").val()).value;
	}
	if($.trim(_checkedIds) != ""){
		//单选
		if($.trim($("#_isCheckBox").val())==""||$("#_isCheckBox").val()=="null"){
			addCheckedItem($.trim(_checkedIds), $.trim(_checkedNames));
		}
		//多选
		else{
			for(var i = 0; i < $.trim(_checkedIds).split(",").length; i++){
				addCheckedItem($.trim(_checkedIds).split(",")[i], $.trim(_checkedNames).split(",")[i]);
			}
		}
	}
	
	//添加目录树中的选中值
	$('#addSelectedTreeItems').click(function() {
		var ids = SearchTree.tree.getAllChecked();
		var idsArray = ids.split(",");
		
		//未选中数据
		if(ids==null || ids=="" || idsArray.length<=0){alert("请选择相关数据!"); return;}
		
		//循环添加选中的节点
		for(var i=0; i<idsArray.length;i++){
			//根节点
			if(idsArray[i]=="-1"){ continue; }
			
			//只选子节点，并且有复选框
			if($("#_childNodeOnly").val()=='true' && $("#_isCheckBox").val()=='true' && SearchTree.tree.hasChildren(idsArray[i])){
				continue;
			}
			
			addCheckedItem(idsArray[i]+"", SearchTree.tree.getItemText(idsArray[i]));
		}
	});
	
	//搜索
	$('#searchButton').click(function() {
		var keyWord = $('#keyWord').val();
		if(keyWord==null || keyWord==''){ alert('请输入搜索关键字！'); return ; }
		var className = $("#_className").val();
		var paramObj = {keyWord:keyWord};
		
		//只选择叶子节点
		if($("#_childNodeOnly").val()=='true'){
			paramObj.isLeaf = true;
		}
		
		//获取模糊查询结果
		var url = $("#initPath").val()+"/"+className+"Controller.do?method=searchTreeList";
		$.getJSON(url, paramObj, function(json){
			if(json.failure){
				alert("操作失败！");
				return;
			}else{
				$("#searchResultList").empty();
				if(json.objList.length <= 0){
					alert('找不到和您的查询 "'+keyWord+'" 相符的内容或信息。\n建议：请尝试其他的查询关键字。');
					return ;
				}
				$.each(json.objList, function(index,obj){
					var htmlStr = '<option value="'+obj[0]+'">'+obj[1]+'</option>';
					$("#searchResultList").append(htmlStr);
				})
			}
		});
	});
})
