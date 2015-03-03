var templateFileTreeForm={};
var tempFileType = "";
var rootId = "";
templateFileTreeForm.tree;
templateFileTreeForm.isSort=0;
templateFileTreeForm.currentObj;
var insertFile = true;
var isCreateFckedit = false;
var templateFileList = {};
var option = {"parent.objId":rootId,"order":"fileSize","tempFileType":tempFileType};
var filePath = "";

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
						$("textarea[name=fileContent]").val(content.content)
					}
				})
		}else{
			$("#templateFileContentDiv").hide();
			$("#templateFileListDiv").show();
			option = {"parent.objId":id,"order":"fileSize"};
			$("#templateFileGrid").reload();
		}
	});
}

//初始化树
templateFileTreeForm.initTemplateFileTree=function(insertFile){
	templateFileTreeForm.tree=new dhtmlXTreeObject('templateFileTree',"100%","100%",0);
	templateFileTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	templateFileTreeForm.tree.enableDragAndDrop(1);
	templateFileTreeForm.tree.enableThreeStateCheckboxes(true);
	templateFileTreeForm.tree.setOnClickHandler(templateFileTreeForm.nodeClick);
	templateFileTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/TemplateFileController.do?method=getTree&tempFileType='+tempFileType+'&action=listById&order=sortNo&insertFile='+false);//点 + 号触发展开事件
	templateFileTreeForm.tree.loadXML($('#initPath').val()+'/TemplateFileController.do?method=getTree&tempFileType='+tempFileType+'&action=listTop&id=0&isOpen=0&order=sortNo&insertFile='+insertFile,function(){
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

templateFileList.initTreeFromFile=function(){
	
	$.ajax({
		url:$("#initPath").val()+"/TemplateFileController.do?method=getTemplateTree",
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


// 增加模板文件
templateFileList.addTemplateFile = function(){//新增文件
	if(undefined == templateFileTreeForm.currentObj || ""== templateFileTreeForm.currentObj.objId || "-1" == templateFileTreeForm.currentObj.objId){
		alert("请先选择上级目录！");
		return false;
	}
	$("#templateFileContentDiv").show();
	$("#templateFileListDiv").hide();
	$("#templateFileName").empty().append("<input type='text' name='fileName' id='fileName' class='required'/><span class='eleRequired'>*</span>");
	$("textarea[name=fileContent]").val("");
}

templateFileList.addTemplateDirectory = function(){//新增
	if(undefined == templateFileTreeForm.currentObj || ""== templateFileTreeForm.currentObj.objId || "-1" == templateFileTreeForm.currentObj.objId){
		alert("请先选择上级目录！");
		return false;
	}
	$.epsDialog({
		title:'创建目录',
		url:'TemplateFileController.do?method=toCreateOrUpdate&path='+filePath,
		width: '500',
		height: '110',
		hasBg:true,//背景
		fadeTo:80, //透明度
		afterLoad: function(){
			$('#currentRealPath').val(filePath);
			$("#templateFileForm span[name=currentPath]").html("当前上传路径"+filePath.substring(filePath.indexOf(tempFileType,0),filePath.length)+"\\");
		}
	});
	return false;
};

templateFileList.renameDirectoryOrFileName = function(){//重命名
	if(!templateFileList.validation("#templateFileGrid"))return;
	var json = $("#templateFileGrid").flexGetRowJsonById($("#templateFileGrid").getSelect());
	var _name = "重名命模板目录";
	if(json.isLeaf=="1"){
		_name = "重名命模板名称";
	}
	$.epsDialog({
		title:_name,
		url:'TemplateFileController.do?method=toShowView&name='+json.name+'&path='+json.path,
		width: '500',
		height: '110',
		hasBg:true,//背景
		fadeTo:80, //透明度
		afterLoad: function(){
			$('#currentRealPath').val(json.path);
			$("#curFileName").val(json.name);
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
			$('#conBody').loadPage($('#initPath').val()+'/TemplateFileController.do');
		});
	}
}

$(document).ready(function(){	
	//$('#templateFileForm').validate();
	
	// 加载模板树
	templateFileList.initTreeFromFile();
	
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
	
	//监听回车事件
	$('#FCKContent_fileContent').bind("keypress", function(event){
		if (event.keyCode == 13) {								   
			$("#FCKContent_fileContent").focus();
			var sel = document.selection.createRange();
			sel.text="\n";
			return;
		}
	});
	
});

$("#saveFileContent").click(function(){
	//if(!$('#templateFileForm').valid()){return;}
	var content = $("textarea[name=fileContent]").val();
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