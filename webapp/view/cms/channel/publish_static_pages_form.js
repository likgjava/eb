var channelTreeForm = {};
var templateTreeForm = {};
var tempFileType = "";
var rootId = "";

// 初始化栏目树
channelTreeForm.initChannelTree=function(){
	channelTreeForm.tree=new dhtmlXTreeObject('channelTree',"100%","100%",0);
	channelTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	channelTreeForm.tree.enableCheckBoxes(1);
	channelTreeForm.tree.enableDragAndDrop(1);
	channelTreeForm.tree.setOnClickHandler(channelTreeForm.nodeClick);
	channelTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/ChannelController.do?method=getTree&action=listById&order=sort');//点 + 号触发展开事件
	channelTreeForm.tree.loadXML($('#initPath').val()+'/ChannelController.do?method=getTree&action=listTop&id=0&order=sort&isOpen=1',function(){
	});
}

templateTreeForm.initIncludeTemplateTreeFromFile=function(){
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
				templateTreeForm.initIncludeTemplateTree();//加载树
			}else{
				alert("保存模板发生错误！");
			}
		}
	})
}

// 初始化引用模板树
templateTreeForm.initIncludeTemplateTree=function(){
	templateTreeForm.tree=new dhtmlXTreeObject('includeTemplateTree',"100%","100%",0);
	templateTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	templateTreeForm.tree.enableCheckBoxes(1);
	templateTreeForm.tree.enableDragAndDrop(1);
	//templateTreeForm.tree.enableThreeStateCheckboxes(true);
	templateTreeForm.tree.setOnClickHandler(channelTreeForm.nodeClick);
	templateTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/TemplateFileController.do?method=getTree&tempFileType='+tempFileType+'&action=listById&order=sortNo');//点 + 号触发展开事件
	templateTreeForm.tree.loadXML($('#initPath').val()+'/TemplateFileController.do?method=getTree&tempFileType='+tempFileType+'&action=listTop&id=0&order=sortNo&isOpen=1',function(n){
	});
}

$(document).ready(function(){
	$('#fileTabs').tabs();
	channelTreeForm.initChannelTree();
	templateTreeForm.initIncludeTemplateTreeFromFile();
	
})

// 发布静态页面
$("button[name=createStaticPages]").click(function(){
	$(".conOperation button").attr("disabled",true);
	var _button = $(this);
	$(_button).attr("disabled",true);
	var spanName = $(_button).find("span").html();
	$(_button).find("span").html("正在"+spanName+"...");
	var ids = "";
	var _url = "";
	
	// 如果发布的是自定义引用模板
	if($(_button).attr("isTemplate")=="true"){
		ids = templateTreeForm.tree.getAllChecked().replace("-1","");
		if($(_button).attr("publistAll")=="true"){
			var allTemplateJson=$.ajax({url: $('#initPath').val()+"/TemplateFileController.do?method=getObjectQuery&queryColumns=objId,isLeaf",data:{"tempFileType":tempFileType}, async: false}).responseText;
			ids = "";
			$(JSON.parse(allTemplateJson).result).each(function(i,n){
				if(n.isLeaf == "true")
				ids = ids+n.objId+",";
			})
		}
		_url = $("#initPath").val()+"/TemplateFileController.do?method="+$(_button).attr("id");
	}else{
		ids = channelTreeForm.tree.getAllChecked().replace("-1","");
		_url = $("#initPath").val()+"/ChannelController.do?method="+$(_button).attr("id");
	}
	$.ajax({
		url: _url,
		type:"POST",
		data:{"ids":ids},
		dataType:'json',
		success:function(msg){
			if(msg.result=="true"){
				alert("发布成功");
			}else{
				alert("发布失败");
			}
			$(_button).attr("disabled",false);
			$(".conOperation button").attr("disabled",false);
			$(_button).find("span").html(spanName);
		}
	})
	
});

// 删除静态页面
$("button[name=deleteStaticPages]").click(function(){
	$(".conOperation button").attr("disabled",true);
	var _button = $(this);
	$(_button).attr("disabled",true);
	var spanName = $(_button).find("span").html();
	$(_button).find("span").html("正在"+spanName+"...");
	$.ajax({
		url: $("#initPath").val()+"/TemplateFileController.do?method=deleteStaticPages",
		type:"POST",
		data:{"deleteType":$(_button).attr("id")},
		dataType:'json',
		success:function(msg){
			if(msg.result=="true"){
				alert("删除成功");
			}else{
				alert("删除失败");
			}
			$(_button).attr("disabled",false);
			$(".conOperation button").attr("disabled",false);
			$(_button).find("span").html(spanName);
		}
	})
	
});
