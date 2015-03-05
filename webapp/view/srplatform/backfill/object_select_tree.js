/**
 *  通过属性回填对象
 * 参数:
 * 弹出层url示例：url:$('#initPath').val()+'/TreeController.do?method=toTree&childNodeOnly=true&className=Organization&property=org&params=status:0',
 * 或者url:$('#initPath').val()+'/TreeController.do?method=toTree&className=Organization&id=org.objId&name=org.name',
 * _isCheckBox:是否复选框
 * _childNodeOnly:必须回填子节点
 * _checkValues:已选值
 * _className:实体名称
 * _params：查询参数,这里是一个表达式，为了区分url参数，表达式中间的‘=’用‘#’代替
 * _hiddenID:页面隐藏域id值，回填属性对象的objId
 * _displayId:页面显示域id值，回填属性对象的中文名称
 * _property:对象属性名，这种方式自动回填id为对象属性名.objId和.name的页面域
 */
var DHtmlTree={}
	
DHtmlTree.edit=function(objId){
	if($("#_childNodeOnly").val()=='true'){
			if(DHtmlTree.tree.hasChildren(objId)!=0)
				return;
		}
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

DHtmlTree.unableCheckIfNotLeaf = function(objId){
	$.each(DHtmlTree.tree.getAllSubItems(objId).split(","),function(index,obj){
		if(DHtmlTree.tree.hasChildren(obj)!=0){
			DHtmlTree.tree.disableCheckbox(obj,1);
		}
	})
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
			names=names.substring(0,names.length-1);

			returnValue(objIds+"",names);
		})
		
	}
	var _params = $("#_params").val()?$("#_params").val().replace(new RegExp(':','gm'),'='):'';
	DHtmlTree.tree.setImagePath($("#initPath").val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	DHtmlTree.tree.setXMLAutoLoading($("#initPath").val()+"/"+$("#_className").val()+"Controller.do?method=getTree&action=listById&"+_params);

	var url=$("#initPath").val()+"/"+$("#_className").val()+"Controller.do?method=getTree&action=listTop&"+_params;
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
		}
		
	});
	
	$("#_clear").click(function(){
		returnValue("","");
	})
	$("#_cancel").click(function(){
		//关闭弹出层
		if($("#_dialogID").val() != "")
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		else
			$('.epsDialogClose').trigger('click');
	})
})
	
function returnValue(id,name){
	//回填id
	if("" == $("#_hiddenId").val())
		document.getElementById($("#_property").val()+".objId").value=id;
	else
		document.getElementById($("#_hiddenId").val()).value=id;
	
	//回填name
	if("" == $("#_displayId").val()){
		document.getElementById($("#_property").val()+".name").value=name;
		$(document.getElementById($("#_property").val()+".name")).keyup();
	}
	else{
		document.getElementById($("#_displayId").val()).value=name;
		$(document.getElementById($("#_displayId").val())).keyup();
	}
	
	$("#_cancel").click();
}

	
