var channelTreeForm = {};
channelTreeForm.channelDataCount = 0;
channelTreeForm.ChannelList = "";
channelTreeForm.VoteTopicList = "";
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
var fileName = "";
var ftlTemplate = "";
var templateId = "";
// 点击节点事件
templateFileTreeForm.nodeClick=function(id){
	$("#ChannelDataList").empty();
	$('#publicLoadTemplate').attr('disabled',true);
	if("-1"==id || ""==id){
		templateFileTreeForm.currentObj = undefined;
		return false;
	}
	templateId = id;
	var queryColumns=[];
	$.ajax({
		url:$("#initPath").val()+"/TemplateFileController.do?method=createOrUpdate&includedProperties="+queryColumns.toString(),
		type:"POST",
		data:{objId:id},
		async: false,
		dataType:'json',
		success:function(json){
			templateFileTreeForm.currentObj=json.templateFile;
			filePath = json.templateFile.path;
			fileName = json.templateFile.name
			if(json.templateFile.isLeaf=="true"){
				$("div[id=templateFileContentDiv]").show();
				$("div[id=templateFileListDiv]").hide();
				$('#publicLoadTemplate').attr('disabled',false);
				$("#templateFileName").html(json.templateFile.name);
				$.ajax({
						url:$("#initPath").val()+"/TemplateFileController.do?method=getFileContent",
						type:"POST",
						data:{"path":json.templateFile.path},
						async: false,
						dataType:'json',
						success:function(result){
							$("textarea[name=fileContent]").val(result.content)
							
							// 从当前模板路径中截取自定义引用模板的存放路径，用于根据该路径查询该模板定义的数据
							templatePath = result.includeTemplate;
							var temp_path  = filePath.replace(/\\/g,"/");
							ftlTemplate = temp_path.substring(temp_path.indexOf(templatePath)+templatePath.length);
						}
					})
			}else{
				$("div[id=templateFileContentDiv]").hide();
				$("div[id=templateFileListDiv]").show();
				option = {"parent.objId":id,"order":"fileSize"};
				$("#templateFileGrid").reload();
			}
		}
	});
	
	
	// 取得栏目查询数据列表
	var ChannelDataListJson=$.ajax({url: $('#initPath').val()+"/ChannelDataController.do?method=getObjectQuery&queryColumns=objId,name,num,channel,channel.name,channel.objId,display,needLoad,tmpType,dataType,dataVal,dataKey,sortType",data:{"tmpType":"template","ftlTemplate":ftlTemplate}, async: false}).responseText;
	var ChannelDataList = "";
	var ChannelDataSpanList = "";
	var ChannelContentDataList = "";
	var ChannelContentDataSpanList = "";
	var dataFrom = "";
	$(JSON.parse(ChannelDataListJson).result).each(function(i,n){
		var checkedDisplay = "";
		if(n.display=="true"){
			checkedDisplay = "checked";
		}
		var checkedNeedLoad = "";
		if(n.needLoad=="true"){
			checkedNeedLoad = "checked";
		}
		var checkedChannel = "";
		var checkedContent = "";
		var checkedVotopic = "";
		var checkedBulletin = "";
		if("channel" == n.dataType){
			checkedChannel = "selected";
		}else if("content" == n.dataType){
			checkedContent = "selected";
		}else if("voteTopic" == n.dataType){
			checkedVotopic = "selected";
		}else if("bulletin" == n.dataType){
			checkedBulletin = "selected";
		}
		
		ChannelDataList += "<tr><td><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].tmpType' value='template' /><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].objId' value='"+n.objId+"' /><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].dataKey' value='"+n.dataKey+"'/>";
		ChannelDataList += "<select name='ChannelData["+channelTreeForm.channelDataCount+"].dataType' style='width:80px'><option value=''>-请选择-</option><option value='channel' "+checkedChannel+">栏目</option><option value='content' "+checkedContent+">内容</option><option value='bulletin' "+checkedBulletin+">公告</option><option value='voteTopic' "+checkedVotopic+">投票</option></select>";
		ChannelDataList += "<select name='ChannelData["+channelTreeForm.channelDataCount+"].dataVal' style='width:100px'><option value='"+n.dataVal+"' selected style='width:80px'>"+n.dataKey+"</option></select></td>";
		ChannelDataList += "<td><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].sortNo' value='"+channelTreeForm.channelCount+"' size='2'/><input type='text' name='ChannelData["+channelTreeForm.channelDataCount+"].name' value='"+n.name+"' style='width:50px'/></td>";
		ChannelDataList += "<td><input type='text' name='ChannelData["+channelTreeForm.channelDataCount+"].num' value='"+n.num+"' size='2' style='width:20px'/></td>";
		ChannelDataList += "<td>"+channelTreeForm.getSortType(n.sortType)+"</td>";
		ChannelDataList += "<td><input type='checkbox' name='ChannelData["+channelTreeForm.channelDataCount+"].display' value='true' "+checkedDisplay+"></td>";
		ChannelDataList += "<td><a href='javascript:void(0)' class='del' title='删除' name='delete'>删除</a></td>";
		ChannelDataList += "</tr>";
		
		
		channelTreeForm.channelDataCount ++;
		
	});
	if(channelTreeForm.channelDataCount>0){
		channelTreeForm.initChannelDateList("ChannelDataList", channelTreeForm.channelDataCount, ChannelDataList, ChannelDataSpanList);
	}


}

// 初始化树
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
		url:$("#initPath").val()+"/TemplateFileController.do?method=getIncludeTemplateTree",
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
	$("div[id=templateFileContentDiv]").hide();
})


// 增加模板文件
templateFileList.addTemplateFile = function(){//新增文件
	templateId = "";
	$("#ChannelDataList").empty();
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
	if(json.isLeaf=="true"){
		_name = "重名命模板名称";
    }
	$.epsDialog({
		title:_name,
		url:'TemplateFileController.do?method=toShowView&name='+json.name+'&path='+json.path+"&tmpType=template",
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

channelTreeForm.initChannelDataLi = function(n,disabledFlag){
	$(n).find("[name$=.num]").attr("disabled",disabledFlag).end().find("[name$=.sortType]").attr("disabled",disabledFlag);
}

templateFileList.remove = function(){
	if($("#templateFileGrid").isSelectEmpty()){alert('请选择要删除的文件或目录！');return false;}//是否选中
	if(window.confirm("确定删除?")){
		var objIds = $("#templateFileGrid").getSelects();
		$.getJSON($('#initPath').val()+'/TemplateFileController.do?method=deleteDirectoryOrFile&tmpType=template',{objIds:objIds},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+'/TemplateFileController.do?method=toIncludeTemplateList');
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
			 	//var json = $("#templateFileGrid").flexGetRowJsonById(id); 
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

//刷新节点
templateFileTreeForm.reloadTree = function (itemId,text,type){
	//判断是否增加还是修改
	if(!type){
		templateFileTreeForm.tree.setItemText(itemId,text);
		templateFileTreeForm.nodeClick(itemId);
	}else{
		var temp = templateFileTreeForm.tree.getSelectedItemId();
		if(!temp)temp=-1;
		//获取节点状态：0 - 没有子节点或者  节点合拢, 1  节点展开
		if(templateFileTreeForm.tree.hasChildren(temp)&&templateFileTreeForm.tree.getOpenState(temp)==0){
			return false;
		}
		else{
			templateFileTreeForm.tree.insertNewItem(temp,itemId,text,0,0,0,0,'SELECT');
			templateFileTreeForm.tree.setItemImage2(itemId,"leaf.gif","folderOpen.gif","folderClosed.gif");
			templateFileTreeForm.nodeClick(itemId);
		}
	}
}

// 保存模板
$("#saveFileContent").click(function(){
	var content = $("textarea[name=fileContent]").val();
	if($("#templateFileName>input[name=fileName]").length>0 && ""!=$("#templateFileName>input[name=fileName]").val()){
		filePath = filePath+"\\"+$("#templateFileName>input[name=fileName]").val()
	}
	$.ajax({
		url:$("#initPath").val()+"/TemplateFileController.do?method=saveFileContent",
		type:"POST",
		data:{"path":filePath,"content":content, "tmpType":"template", "ChannelDataJSONString":JSON.stringify(formToJsonObject("templateDataForm","json").ChannelData)},
		async: false,
		dataType:'json',
		success:function(result){
			if(result.success=="true"){
				alert("保存成功！");
				if("" != templateId) {
					templateFileTreeForm.reloadTree(templateFileTreeForm.currentObj.objId,templateFileTreeForm.currentObj.name,false);
				}else {
					$('#conBody').loadPage($('#initPath').val()+'/TemplateFileController.do?method=toIncludeTemplateList');
				}
			}else{
				alert("保存模板发生错误！");
			}
		}
	})
});

// 发布模板
$('#publicLoadTemplate').click(function(){
	if(!templateId || templateId == ""){
		return false;
	}
	$(".conOperation button").attr("disabled",true);
	var _button = $(this);
	var spanName = $(_button).find("span").html();
	$(_button).find("span").html("正在"+spanName+"...");
	$.ajax({
		url: $("#initPath").val()+"/TemplateFileController.do?method=publicTemplatePages",
		type:"POST",
		data:{"ids":templateId},
		dataType:'json',
		success:function(msg){
			if(msg.result=="true"){
				alert("发布成功");
			}else{
				alert("发布失败");
			}
			$(".conOperation button").attr("disabled",false);
			$(_button).find("span").html(spanName);
		}
	})
});

// 栏目列表
channelTreeForm.getChannelListJsonObj  = function(){
	channelTreeForm.ChannelListJson=$.ajax({url:"ChannelController.do?method=getObjectQuery&queryColumns=objId,name",data:{"order":"sort"}, async: false}).responseText;
	channelTreeForm.ChannelListJsonObj = JSON.parse(channelTreeForm.ChannelListJson).result;
	return channelTreeForm.ChannelListJsonObj;
}

//获取投票列表
channelTreeForm.getVoteTopicListJsonObj  = function(){
	channelTreeForm.VoteListJson=$.ajax({url:"VoteTopicController.do?method=getObject",data:{"order":"totalCount"}, async: false}).responseText;
	channelTreeForm.VoteTopicListJsonObj = JSON.parse(channelTreeForm.VoteListJson).list;
	return channelTreeForm.VoteTopicListJsonObj;
}

// 添加一行数据
$("#addChannelDataRow").click(function(){
	channelTreeForm.channelDataCount ++;
	channelTreeForm.addChannelDataRow("ChannelDataList",channelTreeForm.channelDataCount,"template");
});

channelTreeForm.getSortType = function(sortType){
	var sortType1 = "";
	var sortType2 = "";
	var sortType3 = "";
	var sortType4 = "";
	if((sortType+"")=="2"){
		sortType2 = "selected";
	}else if((sortType+"")=="3"){
		sortType3 = "selected";
	}else if((sortType+"")=="4"){
		sortType4 = "selected";
	}
	var sortTypeStr = "";
	sortTypeStr += "<select style='width:80px' name='ChannelData["+channelTreeForm.channelDataCount+"].sortType'>";
	sortTypeStr += "<option value='1' "+sortType1+">时间倒序</option>";
	sortTypeStr += "<option value='2' "+sortType2+">时间顺序</option>";
	sortTypeStr += "<option value='3' "+sortType3+">访问量倒序</option>";
	sortTypeStr += "<option value='4' "+sortType4+">访问量顺序</option>";
	sortTypeStr += "</select>";
	return sortTypeStr ;
}

channelTreeForm.addChannelDataRow = function(channelDateType,sort,tmpType){
	var ChannelDataList = "";
	channelTreeForm.channelDataCount ++;
	
	ChannelDataList += "<tr><td><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].tmpType' value='"+tmpType+"' /><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].objId' value='' /><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].dataKey' value=''/>";
	ChannelDataList += "<select name='ChannelData["+channelTreeForm.channelDataCount+"].dataType'  style='width:80px'><option value='' selected='selected'>-请选择-</option><option value='channel'>栏目</option><option value='content'>内容</option><option value='bulletin'>公告</option><option value='voteTopic'>投票</option></select>";
	ChannelDataList += "<select name='ChannelData["+channelTreeForm.channelDataCount+"].dataVal' style='width:100px'><option value=''>-请选择-</option></select></td>";
	ChannelDataList += "<td><input type='hidden' name='ChannelData["+channelTreeForm.channelDataCount+"].sortNo' value='"+sort+"' size='' sytle='width:10px'/><input type='text' name='ChannelData["+channelTreeForm.channelDataCount+"].name' style='width:50px'/></td>";
	ChannelDataList += "<td><input type='text' name='ChannelData["+channelTreeForm.channelDataCount+"].num' value='10' size='2' style='width:20px'/></td>";
	ChannelDataList += "<td>"+channelTreeForm.getSortType("")+"</td>";
	ChannelDataList += "<td><input type='checkbox' name='ChannelData["+channelTreeForm.channelDataCount+"].display' value='true'></td>";
	ChannelDataList += "<td><a href='javascript:void(0)' title='删除' name='delete'>删除</a></td>";
	ChannelDataList += "</tr>";
	
	
	$("#"+channelDateType).append(ChannelDataList).find("td a[name=delete]").click(function(){
		$(this).parent().parent().remove();
	}).end().find("td>a[name=modify]").click(function(){
		alert("修改")
	}).end().find("select[name$=dataType]").change(function(){
		
		if($(this).find("option[selected]").val()=="channel" || $(this).find("option[selected]").val()=="content"){
			if(""==channelTreeForm.ChannelList){
				// 栏目列表
				channelTreeForm.ChannelList = "<option value=''>选择栏目</option>";
				$(channelTreeForm.getChannelListJsonObj()).each(function(i,n){
					channelTreeForm.ChannelList += "<option value='"+n.objId+"'>"+n.name+"</option>";
				})
			}
			$(this).parent().find("select[name$=dataVal]").empty().append(channelTreeForm.ChannelList).end().find("select[name$=dataVal]").change(function(){
				$(this).parent().find("input[name$=dataKey]").val($(this).find("option[selected]").text())
			});
		}else if("voteTopic"==$(this).find("option[selected]").val()){
			if(""==channelTreeForm.VoteTopicList){
				
				// 投票列表
				channelTreeForm.VoteTopicList = "<option value=''>选择投票</option>";
				$(channelTreeForm.getVoteTopicListJsonObj()).each(function(i,n){
					channelTreeForm.VoteTopicList += "<option value='"+n.objId+"'>"+n.title+"</option>";
				})
			}
			$(this).parent().find("select[name$=dataVal]").empty().append(channelTreeForm.VoteTopicList).end().find("select[name$=dataVal]").change(function(){
				$(this).parent().find("input[name$=dataKey]").val($(this).find("option[selected]").text())
			})
			channelTreeForm.initChannelDataLi($(this).parent(),true);
		}else if("bulletin"==$(this).find("option[selected]").val()){
			$(this).parent().find("select[name$=dataVal]").empty().append($("#template select[name=bullType]").html()).end().find("select[name$=dataVal]").change(function(){
				$(this).parent().find("input[name$=dataKey]").val($(this).find("option[selected]").text())
			})
			channelTreeForm.initChannelDataLi($(this).parent(),false);
		}else if(""==$(this).find("option[selected]").val()){
			$(this).parent().find("select[name$=dataVal]").empty().append("<option value=''>--请选择--</option>").end().find("[name$=.num]").val("10").attr("disabled",false).end().find("[name$=.sortType]").attr("disabled",false);
		}

	});
}

// 初始化栏目数据来源
channelTreeForm.initChannelDateList = function(channelDateType,count,ChannelDataList, ChannelDataSpanList){
	
	// 生成数据列表
	$("#"+channelDateType).empty().append(ChannelDataList).find("a[name=delete]").click(function(){
		if(channelTreeForm.removeChannelData($(this).parent().parent().find("input[name$=objId]").val())){
			$(this).parent().parent().remove();
		}
	}).end().find("a[name=modify]").click(function(){
		alert("修改")
	}).end().find("select[name$=dataType]").change(function(){
		
		if($(this).find("option[selected]").val()=="channel" || $(this).find("option[selected]").val()=="content"){
			if(""==channelTreeForm.ChannelList){
				// 栏目列表
				channelTreeForm.ChannelList = "<option value=''>选择栏目</option>";
				$(channelTreeForm.getChannelListJsonObj()).each(function(i,n){
					channelTreeForm.ChannelList += "<option value='"+n.objId+"'>"+n.name+"</option>";
				})
			}
			$(this).parent().find("select[name$=dataVal]").empty().append(channelTreeForm.ChannelList).end().find("select[name$=dataVal]").change(function(){
				$(this).parent().find("input[name$=dataKey]").val($(this).find("option[selected]").text())
			});
		}else if("voteTopic"==$(this).find("option[selected]").val()){
			if(""==channelTreeForm.VoteTopicList){
				
				// 投票列表
				channelTreeForm.VoteTopicList = "<option value=''>选择投票</option>";
				$(channelTreeForm.getVoteTopicListJsonObj()).each(function(i,n){
					channelTreeForm.VoteTopicList += "<option value='"+n.objId+"'>"+n.title+"</option>";
				})
			}
			$(this).parent().find("select[name$=dataVal]").empty().append(channelTreeForm.VoteTopicList).end().find("select[name$=dataVal]").change(function(){
				$(this).parent().find("input[name$=dataKey]").val($(this).find("option[selected]").text())
			});
			channelTreeForm.initChannelDataLi($(this).parent(),true);
		}else if("bulletin"==$(this).find("option[selected]").val()){
			$(this).parent().find("select[name$=dataVal]").empty().append($("#template select[name=bullType]").html()).end().find("select[name$=dataVal]").change(function(){
				$(this).parent().find("input[name$=dataKey]").val($(this).find("option[selected]").text())
			});
			channelTreeForm.initChannelDataLi($(this).parent(),false);
		}else if(""==$(this).find("option[selected]").val()){
			$(this).parent().find("select[name$=dataVal]").empty().append("<option value=''>--请选择--</option>");
		}

	});
}

// 删除对数对象
channelTreeForm.removeChannelData = function(id){
	var falg = false;
	$.ajax({
		url: $("#initPath").val()+"/ChannelDataController.do?method=remove",
		type:"POST",
		data:{"objId":id},
		async:false,
		dataType:'json',
		success:function(msg){
			if(msg.success=="true"){
				falg =  true
			}else{
				alert(msg.result);
				falg =  false;
			}
		}
	})
	return falg;
}