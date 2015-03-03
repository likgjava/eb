var DHtmlTree={}
	
DHtmlTree.edit=function(objId){
	if(objId==" ")
		return;
	if(objId=="-1")
		return;
	else{
		if($.trim($("#_isCheckBox").val())==""||$("#_isCheckBox").val()=="null"){
			returnValue(objId,DHtmlTree.tree.getItemText(objId),$("#_Column").val());
		}
		return;
	}
}

$(document).ready(function(){
	DHtmlTree.tree=new dhtmlXTreeObject('dialogTreeBox',"260px","400px",0); 
	DHtmlTree.tree.enableDragAndDrop(false);
	DHtmlTree.tree.setOnClickHandler(DHtmlTree.edit);
	DHtmlTree.tree.setDragHandler(false);
	if($.trim($("#_isCheckBox").val())!=""&&$("#_isCheckBox").val()!="null"){
		DHtmlTree.tree.enableCheckBoxes(true)
		//初始化按钮不级联选中 0为表示不选中,1为选中状态
		DHtmlTree.tree.enableThreeStateCheckboxes(0);
		
		$("#_clear").before("<button id='_OK'>确定</button>")
		$("#_OK").click(function(){
			var ids=(DHtmlTree.tree.getAllChecked())
			var names="";
			if(ids == null || ids == "" || ids.split(",").length <= 0){
				alert("请选择相关数据!");
				return;
			}
			var objIds=[];
			for(var i=0;i<ids.split(",").length;i++){
				if(ids.split(",")[i]=="-1"){
					continue;
				}
				objIds.push(ids.split(",")[i]);
				names+=(DHtmlTree.tree.getItemText(ids.split(",")[i]))+","
			}
			//查询需要回填的列
			var Column;
			$.getJSON(
				$("#initPath").val()+"/"+$("#_className").val()+"Controller.do?method=getObjectQuery",
				{
					"objId":objIds+"",
					"objId_op":"in",
					queryColumns:$("#_Column").val()
				},
				function(json){
					Column = json.result
					returnValue(objIds+"",Column,$("#_Column").val());
				}
			);
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
	});
	
	$("#_clear").click(function(){
		returnValue("","",$("#_Column").val());
	})
})
	
function returnValue(id,Column,Columns){
	//指定属性回填

	if(null!=Columns&&""!=Columns){
		var colum= Columns.split(",");
		//先清空
		for(var i=0;i<colum.length;i++){
			$("#"+colum[i]).empty();
		}
		for(var i=0;i<colum.length;i++){
			for(var j=0;j<Column.length;j++){
				if(j>0){
					$("#"+colum[i]).val($("#"+colum[i]).val()+","+Column[j][colum[i]]);
				}else{
					$("#"+colum[i]).val(Column[j][colum[i]]);
				}
			}
		}
	}
	//简单回填
	else {
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
	}
	//关闭弹出层
	$("#floaterDiv").dialog("close");
	var epsDialogId = $("#_DialogId").val();
	$("#"+epsDialogId+"").find('.epsDialogClose').trigger('click');
}

	
