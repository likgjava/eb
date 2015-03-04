var DHtmlTree={}
	
DHtmlTree.edit=function(objId){
	if(objId==" ")
		return;
	if(objId=="-1")
		return;
	else{
		if($.trim($("#_isCheckBox").val())==""||$("#_isCheckBox").val()=="null"){
			returnValue(objId,DHtmlTree.tree.getItemText(objId));
		}
		return;
	}
}

// 添加选中值
function addCheckedItem(id,name) {
	// 判断是否已经存在
	var isExis = false;
	$('#checkedTreeItems').find("li").each(function() {
		if($(this).find(":checkbox").val() == id){
			isExis = true;
		}
	});
	if($.trim($("#_isCheckBox").val())=="" || $("#_isCheckBox").val()=="null") {
		$('#checkedTreeItems').empty();
	}
	if(!isExis){
		$('#checkedTreeItems').append("<li><input type='checkbox' name='checkedTreeItemBox' value='" + id + "'/>&nbsp;<span>" + name + "</span></li>");
	}
}
	

DHtmlTree.unableCheckIfNotLeaf = function(objId){
	$.each(DHtmlTree.tree.getAllSubItems(objId).split(","),function(index,obj){
		if(DHtmlTree.tree.hasChildren(obj)!=0){
			DHtmlTree.tree.disableCheckbox(obj,1);
		}
	})
}

DHtmlTree.nodeOpen = function(id){
	if(id!=-1&&DHtmlTree.tree.hasChildren(id)!=0){
		DHtmlTree.tree.disableCheckbox(id,0);
	}
}

$(document).ready(function(){
	DHtmlTree.tree=new dhtmlXTreeObject('dialogTreeBox',"100%","100%",0); 
	DHtmlTree.tree.enableDragAndDrop(false);
	DHtmlTree.tree.setOnClickHandler(DHtmlTree.edit);
	
	//只能选子节点 by yucy
	if($("#_childNodeOnly").val()=='true'&&$("#_isCheckBox").val()=='true'){
		DHtmlTree.tree.setOnOpenEndHandler(function(id){
			DHtmlTree.unableCheckIfNotLeaf(id)
		});
	}
	DHtmlTree.tree.setOnOpenEndHandler(DHtmlTree.nodeOpen);
	
	DHtmlTree.tree.setDragHandler(false);
	if($.trim($("#_isCheckBox").val())!=""&&$("#_isCheckBox").val()!="null"){
		DHtmlTree.tree.enableCheckBoxes(true)
		//初始化按钮不级联选中 0为表示不选中,1为选中状态
		DHtmlTree.tree.enableThreeStateCheckboxes(1);
		
		$("#_cancel").before("<button id='_OK'><span>确定</span></button>")
		$("#_OK").click(function(){
			var ids = $('#checkedTreeItems').find("li").find(":checkbox").map(function(){return $(this).val();}).get().join(",") ;
			//var ids=(DHtmlTree.tree.getAllChecked())
			var names = $('#checkedTreeItems').find("li").find(":checkbox").map(function(){return $.trim($(this).parent().find("span").text());}).get().join(",") ;
			returnValue(ids+"",names);
		})
		
	}

	DHtmlTree.tree.setImagePath($("#initPath").val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	DHtmlTree.tree.setXMLAutoLoading($("#initPath").val()+"/"+$("#_className").val()+"Controller.do?method=getTree&action=listById&_isOpen="+$("#_isOpen").val()+"&"+$("#_param").val());

	var url=$("#initPath").val()+"/"+$("#_className").val()+"Controller.do?method=getTree&action=listTop&_isOpen="+$("#_isOpen").val()+"&"+$("#_param").val();
	if($('#txtURL').val()!='')
		url=$("#txtURL").val();
	DHtmlTree.tree.loadXML(url,function(){
		if($.trim($("#_isCheckBox").val())!=""&&$("#_isCheckBox").val()!="null"){
			var objId = $("#_checkValues").val().split(",");
			if(objId != null && objId.length > 0){
				for(var i = 0 ; i < objId.length ; i ++){
					DHtmlTree.tree.setCheck(objId[i],1);
				}
			}
		}
		
		//只能选子节点 by yucy
		if($("#_childNodeOnly").val()=='true'&&$("#_isCheckBox").val()=='true'){
			DHtmlTree.unableCheckIfNotLeaf(-1);
			DHtmlTree.tree.disableCheckbox(-1,1);
		}
	});
	
	$("#_clear").click(function(){
		$('#checkedTreeItems').empty();
	})
	$("#_cancel").click(function(){
		//关闭弹出层
		if($("#_dialogID").val() != "")
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		else
			$('.epsDialogClose').trigger('click');
	})
	
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
		for(var i = 0; i < $.trim(_checkedIds).split(",").length; i++){
			addCheckedItem($.trim(_checkedIds).split(",")[i],$.trim(_checkedNames).split(",")[i]);
		}
	}
	
	// 添加选中值
	$('#addSelectedTreeItems').click(function() {
		var ids=(DHtmlTree.tree.getAllChecked())
		var names="";
		if(ids == null || ids == "" || ids.split(",").length <= 0){
			alert("请选择相关数据!");
			return;
		}
		for(var i=0;i<ids.split(",").length;i++){
			if(DHtmlTree.tree.hasChildren(ids.split(",")[i])!=0||ids.split(",")[i]=="-1"){
				continue;
			}
			names+=(DHtmlTree.tree.getItemText(ids.split(",")[i]))+","
			addCheckedItem(ids.split(",")[i]+"",DHtmlTree.tree.getItemText(ids.split(",")[i]));
		}

	});
	// 移除选中值
	$('#removeSelectedTreeItems').click(function() {
		if($('#checkedTreeItems').find("li").find(":checkbox[checked]").map(function(){return $(this).val();}).get().join(",")==""){
			alert("请选择要移除的数据!");
		}
		$('#checkedTreeItems').find("li").find(":checkbox[checked]").each(function() {
			DHtmlTree.tree.setCheck($(this).val(),0);
			$(this).parent("li").remove();
		})
	});
})
	
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
	}
	else{
		document.getElementById($("#_NAME").val()).value=name;
		$(document.getElementById($("#_NAME").val())).keyup();
	}
	
	$("#_cancel").click();
}


