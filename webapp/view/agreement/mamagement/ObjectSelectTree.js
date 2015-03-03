var DHtmlTree={}
	
DHtmlTree.edit=function(objId){
	if(objId==" ")
		return;
	if(objId=="-1")
		return;
	else{
		if($.trim($("#_isCheckBox").val())==""||$("#_isCheckBox").val()=="null"){
			
			//指定可选的节点
			if(null!=$("#_nodeId").val()&&""!=$("#_nodeId").val()){
				var nodeId = $("#_nodeId").val();
				var subNode =  DHtmlTree.tree.getAllSubItems(nodeId);
				var nodeStr = nodeId +','+subNode;
				if(nodeStr.indexOf(objId)<0){
					alert("您需要从可选的节点中选择");
					return;
				}
			}
			
			returnValue(objId,DHtmlTree.getData(objId),$("#_Column").val());
		}
		return;
	}
}

//取得数据
DHtmlTree.getData = function(ids){
	var result;
	$.ajax({
		url:$("#initPath").val()+"/"+$("#_className").val()+"Controller.do?method=getObjectQuery",
		data:{
			"objId":ids,
			"objId_op":"in",
			 queryColumns:$("#_Column").val()
		},
		dataType:"json",
		type:"POST",
		async:false,
		success:function(msg){
				result = msg.result;
		}
	})
	return result;
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
		
		$("#_clear").before("<button id='_OK'><span>确定</span></button>")
		
		$("#_OK").click(function(){
			var ids=(DHtmlTree.tree.getAllChecked())
			var names="";
			if(ids == null || ids == "" || ids.split(",").length <= 0){
				alert("请选择相关数据!");
				return;
			}
			
			//判断是否指定的节点
			if(null!=$("#_nodeId").val()&&""!=$("#_nodeId").val()){
				var nodeId = $("#_nodeId").val();
				var subNode =  DHtmlTree.tree.getAllSubItems(nodeId);
				var nodeStr = nodeId +','+subNode;
				
				if(nodeStr.indexOf(ids)<0){
					alert("您需要从可选的节点中选择");
					return;
				}
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
			returnValue(objIds+"",DHtmlTree.getData(objIds+""),$("#_Column").val());
		})
		
	}

	DHtmlTree.tree.setImagePath($("#initPath").val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	DHtmlTree.tree.setXMLAutoLoading($("#initPath").val()+"/"+$("#_className").val()+"Controller.do?method=getTree&action=listById&_isOpen="+$("#_isOpen").val()+"&"+$("#_param").val());

	var url=$("#initPath").val()+"/"+$("#_className").val()+"Controller.do?method=getTree&action=listTop&isOpen="+$("#_isOpen").val()+"&"+$("#_param").val();
	if($('#txtURL').val()!='')
		url=$("#txtURL").val();
	DHtmlTree.tree.loadXML(url,function(){
		//设置某个节点下的节点以及本身可选择.....开始
		if(null!=$("#_nodeId").val()&&""!=$("#_nodeId").val()){
			var nodeId = $("#_nodeId").val();
			DHtmlTree.tree.setItemText(nodeId,DHtmlTree.tree.getItemText(nodeId)+'(可选)',DHtmlTree.tree.getItemText(nodeId));
			DHtmlTree.tree.setItemColor(nodeId,'blue','');
			
			var subNode =  DHtmlTree.tree.getAllSubItems(nodeId);
			for(var i =0;i<subNode.split(",").length;i++){
				//修改节点名称
				DHtmlTree.tree.setItemText(subNode.split(",")[i],DHtmlTree.tree.getItemText(subNode.split(",")[i])+'(可选)',DHtmlTree.tree.getItemText(subNode.split(",")[i]));
				//设置颜色
				DHtmlTree.tree.setItemColor(subNode.split(",")[i],'blue','');
			}
		}
		
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
	
//回填数据
function returnValue(id,Column,Columns){
	
	var defineRetuColums = null;
	//是否指定回填的 表单id
	if(null!=$("#_defineRetuColums").val()&&""!=$("#_defineRetuColums").val()){
		defineRetuColums = $("#_defineRetuColums").val().split(",");
	}
	
	
	
	//指定属性回填
	if(null!=Columns&&""!=Columns){
		var colum= Columns.split(",");
		//先清空
		for(var i=0;i<colum.length;i++){
			$("#"+colum[i]).empty();
		}
		for(var i=0;i<colum.length;i++){
			for(var j=0;j<Column.length;j++){
				
				//按指定回填的 表单id回填
				if(null!=$("#_defineRetuColums").val()&&""!=$("#_defineRetuColums").val()){
					if(j>0){
						$('input[id='+defineRetuColums[i]+']').val($('input[id='+defineRetuColums[i]+']').val()+","+Column[j][colum[i]]);
					}else{
						$('input[id='+defineRetuColums[i]+']').val(Column[j][colum[i]]);
					}
				}else
				//默认按回填列的名称回填
				{
					if(j>0){
						$('input[id='+colum[i]+']').val($("#"+colum[i]).val()+","+Column[j][colum[i]]);
					}else{
						$('input[id='+colum[i]+']').val(Column[j][colum[i]]);
					}
				}
			}
		}
	}
	//关闭弹出层
	$("#floaterDiv").dialog("close");
	var epsDialogId = $("#_DialogId").val();
	$("#"+epsDialogId+"").find('.epsDialogClose').trigger('click');
}

	
