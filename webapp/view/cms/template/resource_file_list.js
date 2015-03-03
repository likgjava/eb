
var templateFileTreeForm={};
templateFileTreeForm.tree;
templateFileTreeForm.isSort=0;
templateFileTreeForm.currentObj;
var tempFileType = "";
var rootId = "";
var insertFile = false;
var isCreateFckedit = false;
var templateFileList = {};
var option = {"parent.objId":rootId,"order":"fileSize","tempFileType":tempFileType};
var filePath = "";
var htmlEditor;

// 点击节点事件
templateFileTreeForm.nodeClick=function(id){
	if("-1"==id || ""==id){
		templateFileTreeForm.currentObj = undefined;
		return false;
	}
	var queryColumns=[];
	$.getJSON($('#initPath').val()+'/TemplateFileController.do?method=createOrUpdate&includedProperties='+queryColumns.toString(),{objId:id},function(json){
		templateFileTreeForm.currentObj=json.templateFile;
		filePath = json.templateFile.path;
		if(json.templateFile.isLeaf=="true"){
			$("#templateFileContentDiv").show();
			$("#templateFileListDiv").hide();
			$("#templateFileName").html(json.templateFile.name);
			$.ajax({
					url:$("#initPath").val()+"/TemplateFileController.do?method=getFileContent",
					type:"POST",
					data:{"path":json.templateFile.path},
					async: false,
					dataType:'json',
					success:function(content){
						$("#fileContent").val(content.content);
						if(isCreateFckedit){
							htmlEditor.setValue(content.content+" ");
						}else{
							//加载ExtJs的HTML编辑器
							htmlEditor = new Ext.form.HtmlEditor({
								height: 240,
								enableFont: false,
								anchor: '100%'
							});
							new Ext.panel.Panel({
							    renderTo: 'htmlEditor',
							    layout: 'anchor',
							    border : false,
							    items: [htmlEditor]
							});
							htmlEditor.setValue(content.content+" ");
						}
						isCreateFckedit = true;
					}
				})
		}else{
			$("#templateFileContentDiv").hide();
			$("#templateFileListDiv").show();
			option = {"parent.objId":id,"order":"fileSize","tempFileType":tempFileType};
			$("#templateFileGrid").reload();
		}
	});
}

// 初始化树
templateFileTreeForm.initTemplateFileTree=function(){
	templateFileTreeForm.tree=new dhtmlXTreeObject('templateFileTree',"100%","100%",0);
	templateFileTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	templateFileTreeForm.tree.enableDragAndDrop(1);
	templateFileTreeForm.tree.enableThreeStateCheckboxes(true);
	templateFileTreeForm.tree.setOnClickHandler(templateFileTreeForm.nodeClick);
	templateFileTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/TemplateFileController.do?method=getTree&tempFileType='+tempFileType+'&action=listById&isLeaf=0&order=sortNo');//点 + 号触发展开事件
	templateFileTreeForm.tree.loadXML($('#initPath').val()+'/TemplateFileController.do?method=getTree&tempFileType='+tempFileType+'&action=listTop&id=0&isOpen=0&order=sortNo',function(){
	});
	option = {"parent.objId":rootId,"order":"fileSize","tempFileType":tempFileType};
	$("#templateFileGrid").reload();
}

// 列表操作验证
templateFileList.validation=function(grid){
	if($(grid).isSelectEmpty()){alert('请选择要重命名的文件或目录！');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请选择一个文件或目录！');return false;}//是否只选中一个
	return true;
}

templateFileList.validationEmpty=function(grid){
	if($(grid).isSelectEmpty()){alert('请选择要重命名的文件或目录！');return false;}//是否选中
	return true;
}

templateFileList.initTreeFromFile=function(){
	$.ajax({
		url:$("#initPath").val()+"/TemplateFileController.do?method=getResourceTree",
		type:"POST",
		data:{},
		async: false,
		dataType:'json',
		success:function(result){
			if(result.success=="true"){
				tempFileType = result.tempFileType;
				rootId = result.objId;
				$("#templateFileTree").empty();	
				templateFileTreeForm.initTemplateFileTree();//加载树
			}else{
				alert("保存模板发生错误！");
			}
		}
	})
}

$('#reflushTemplateFile').click(function(){//刷新目录树
	$("#templateFileTree").empty();	
	templateFileList.initTreeFromFile();
	$("#templateFileContentDiv").hide();
	$("#templateFileListDiv").show();
	option = {"parent.objId":rootId,"order":"fileSize","tempFileType":tempFileType};
	$("#templateFileGrid").reload();
})


// 上传资源
templateFileList.uploadFile = function(){
	if(undefined == templateFileTreeForm.currentObj || ""== templateFileTreeForm.currentObj.objId || "-1" == templateFileTreeForm.currentObj.objId){
		alert("请先选择上级目录！");
		return false;
	}
	$.epsDialog({
		title:'上传资源',
		url:'TemplateFileController.do?method=toUpLoadFileForm&path='+filePath,
		width: '600',
		height: '380',
		hasBg:true,//背景
		fadeTo:80, //透明度
		afterLoad: function(){
			$('#currentRealPath').val(filePath);
			$("#uploadFileForm span[name=currentPath]").html("当前上传路径"+filePath.substring(filePath.indexOf(tempFileType,0),filePath.length)+"\\");
		}
	});
	return false;
	
}
templateFileList.addTemplateDirectory = function(){//新增
	if(undefined == templateFileTreeForm.currentObj || ""== templateFileTreeForm.currentObj.objId || "-1" == templateFileTreeForm.currentObj.objId){
		alert("请先选择上级目录！");
		return false;
	}
	$.epsDialog({
		title:'新建目录',
		url:'TemplateFileController.do?method=toCreateOrUpdate&path='+filePath,
		width: '500',
		height: '110',
		hasBg:true,//背景
		fadeTo:80, //透明度
		afterLoad: function(){
			$('#currentRealPath').val(filePath);
			$("#templateFileForm span[name=currentPath]").html("当前路径"+filePath.substring(filePath.indexOf(tempFileType,0),filePath.length)+"\\");
		}
	});
	return false;
};
templateFileList.renameDirectoryOrFileName = function(){//重命名
	if(!templateFileList.validation("#templateFileGrid"))return;
	var json = $("#templateFileGrid").flexGetRowJsonById($("#templateFileGrid").getSelect());
	$.epsDialog({
		title:"重命名",
		url:'TemplateFileController.do?method=toShowView&name='+json.name+'&path='+json.path,
		width: '500',
		height: '110',
		hasBg:true,//背景
		fadeTo:80, //透明度
		afterLoad: function(){
			$('#currentRealPath').val(json.path);
			$('#crrFileName').val(json.name);
		}
	});
	return false;

};

templateFileList.remove = function(){
	if($("#templateFileGrid").isSelectEmpty()){alert('请选择要删除的文件或目录！');return false;}//是否选中
	if(window.confirm("确定删除?")){
		var objIds = $("#templateFileGrid").getSelects();
		$.getJSON($('#initPath').val()+'/TemplateFileController.do?method=deleteDirectoryOrFile',{objIds:objIds},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			templateFileList.initTreeFromFile();
			option = {"parent.objId":rootId,"order":"fileSize","tempFileType":tempFileType};
			$("#templateFileGrid").reload();
		});
	}
}

templateFileList.viewFilePath = function(){
	if(!templateFileList.validation("#templateFileGrid"))return;
	var json = $("#templateFileGrid").flexGetRowJsonById($("#templateFileGrid").getSelect());
	var currentRealPath = json.path;
	var currentPath = currentRealPath.substring(currentRealPath.indexOf(tempFileType,0)-4,currentRealPath.length);
	$.epsDialog({
		title:'查看路径',
		url:'view/cms/template/view_file_path.jsp',
		width: '500',
		height: '110',
		hasBg:true,//背景
		fadeTo:80, //透明度
		afterLoad: function(){
			if(json.isLeaf=="true"){
				$('#currentPath').html("<a href="+$('#initPath').val()+"/"+currentPath+" target='_blank' title='"+json.name+"'>"+currentPath+"</a>");
			}else{
				$('#currentPath').html(currentPath);
			}
		
		}
	});
	return false;
}



//查询条件过滤
templateFileList.before=function(){
	$('#templateFileGrid').flexOptions({params:option});
	return true;
}
templateFileList.success=function(){
	$("#templateFileGrid").flexGetColByName({
		'fileIco' : function(id,t){
		$(t).html("<img src='"+$(t).html()+"'/>")
	}
	});
}


$(document).ready(function(){	
	templateFileList.initTreeFromFile();
	$('#templateFileForm').validate();
	
	
});

$("#saveFileContent").click(function(){
	if(!$('#templateFileForm').valid()){return;}
	$("#fileContent").val(htmlEditor.getValue());
	var content = $("#fileContent").val();
	if($("#templateFileName>input[name=fileName]").length>0 && ""!=$("#templateFileName>input[name=fileName]").val()){
		filePath = filePath+"\\"+$("#templateFileName>input[name=fileName]").val()
	}
	$.ajax({
		url:$("#initPath").val()+"/TemplateFileController.do?method=saveFileContent",
		type:"POST",
		data:{"path":filePath,"content":content},
		async: false,
		dataType:'json',
		success:function(result){
			if(result.success=="true"){
				$('#conBody').loadPage($('#initPath').val()+'/TemplateFileController.do');
			}else{
				alert("保存模板发生错误！");
			}
		}
	})
});