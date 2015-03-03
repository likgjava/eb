var cmsNewsList={};
cmsNewsList.rows=null;//列表查询的结果集
var channelTreeForm={};
channelTreeForm.tree;
channelTreeForm.isSort=0;
channelTreeForm.currentObj;
channelTreeForm.channelCount = 0;
channelTreeForm.contentCount = 0;
channelTreeForm.modelSelect = "";
channelTreeForm.ChannelList = "";
var channelId = $("#channelId").val();
var option={"channel.objId":channelId,"checkStatus":"00"};
var isLeaf = false;

// 点击节点事件
channelTreeForm.nodeClick=function(id){
	if(""==id || "-1"==id){
		option = {"checkStatus":"00"};
		$("#cmsNewsGrid").reload();
		return false;
	}
	channelId = id;
	$.getJSON($('#initPath').val()+'/ChannelController.do?method=createOrUpdate',{objId:id},function(json){
		channelTreeForm.currentObj=json.channel;
		if(json.channel.isLeaf=="true"){
			isLeaf = true;
			option = {"channel.objId":id,"checkStatus":"00"};
			$("#cmsNewsGrid").reload();
		}else{
			isLeaf = false;
			option = {"channel.parent.objId":id,"checkStatus":"00"};
			// 如果是首页则加载全部
			if(json.channel.chnlCode == "index"){
				option = {"checkStatus":"00"};
			}
			$("#cmsNewsGrid").reload();
		}
	})
}

// 初始化树
channelTreeForm.initChannelTree=function(){
	channelTreeForm.tree=new dhtmlXTreeObject('channelTree',"100%","100%",0);
	channelTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	channelTreeForm.tree.enableDragAndDrop(1);
	channelTreeForm.tree.enableThreeStateCheckboxes(true);
	channelTreeForm.tree.setOnClickHandler(channelTreeForm.nodeClick);
	channelTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/ChannelController.do?method=getTree&action=listById&order=sort&roleType=01');//点 + 号触发展开事件
	channelTreeForm.tree.loadXML($('#initPath').val()+'/ChannelController.do?method=getTree&action=listTop&id=0&order=sort&isOpen=1&roleType=01',function(){
		
		if("" != channelId){
			channelTreeForm.nodeClick(channelId);
			channelTreeForm.tree.selectItem(channelId);
		}
	});
}

// 新增
cmsNewsList.add=function(name,grid){
	if(!isLeaf){
		alert("父栏目下不允许发布内容！");
		return false;
	}
	if(""==channelId || "-1"==channelId){
		alert("请选择要发布的栏目！");
		return false;
	}
	$('#returnUrl').val($('#initPath').val()+"/CmsNewsController.do?method=toCreateOrUpdate&channelId="+channelId);
	// 跳转到新增页面
	$('#conBody').loadPage($('#initPath').val()+"/CmsNewsController.do?method=toCreateOrUpdate&channelId="+channelId+"&returnSelf=true");
}   


cmsNewsList.importTest=function(name,grid){
	$.getJSON($('#initPath').val()+'/CmsNewsController.do?method=testImportNews',{},function(json){
	});
}



// 修改
cmsNewsList.update=function(name,grid){
	if(!cmsNewsList.validation(name,grid))return;
	if(""==channelId || "-1"==channelId){
		var json = $("#cmsNewsGrid").flexGetRowJsonById($(grid).getSelect());
		channelId = json["channel.objId"];
	}
	$('#returnUrl').val($('#initPath').val()+"/CmsNewsController.do?method=toCreateOrUpdate&channelId="+channelId);
	// 跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+'/CmsNewsController.do?method=toCreateOrUpdate&channelId='+channelId+'&objId='+$(grid).getSelect()+'&returnSelf=true');
}   

// 删除
cmsNewsList.remove=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择公告'+name);return false;}//是否选中
	if(window.confirm('确定'+name+'?')){
		$.getJSON($('#initPath').val()+'/CmsNewsController.do?method=remove',{objId:$(grid).getSelectArray()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

// 列表操作验证
cmsNewsList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择公告'+name);return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请选择一个公告'+name);return false;}//是否只选中一个
	return true;
}

// 查询条件过滤
cmsNewsList.before=function(){
	$('#cmsNewsGrid').flexOptions({params:option});
	return true;
}

// 加载数据成功之后调用的函数
cmsNewsList.success=function(){
	$("#cmsNewsGrid").flexGetColByName({
		 'title' : function(id,t){
		 	var json = $("#cmsNewsGrid").flexGetRowJsonById(id); 
			//$(t).html("<a href='"+json.url+"' target='_blank'>"+$(t).html()+"</a>");
		 	//为不同的系统订制不同的打开方式
			$(t).html("<a href='javascript:void(0)' >"+$(t).html()+"</a>").find("a").click(function(){
				window.open($('#initPath').val()+json['url']);
			})
		},
		'channel.name' : function(id,t){
			var json = $("#cmsNewsGrid").flexGetRowJsonById(id); 
			//$(t).html("<a href='"+json['channel.url']+"' target='_blank'>"+$(t).html()+"</a>");
			//为不同的系统订制不同的打开方式
			$(t).html("<a href='javascript:void(0)' >"+$(t).html()+"</a>").find("a").click(function(){
				window.open($('#initPath').val()+json['channel.url']);
			})
		}
	});
}

$(document).ready(function(){
	
	channelTreeForm.initChannelTree();//加载树
	$("#cmsNewsGrid").reload();
	$("#cmsNewsSearchZone [id=channel.name]").autocomplete(
			$('#initPath').val() + '/ChannelController.do?method=getObjectQuery&queryColumns=objId,name', 
			{
				matchColumn:'channel.name',//作为查询显示, 被选中之后匹配的列
				extraParams:{},
				minChars: 0,
				max: 8,
				autoFill: true,
				mustMatch: false,
				scrollHeight: 220,
				formatItem: function(data, i, total) {
					return data.name;
				},
				formatMatch: function(data, i, total) {
					return data.name;
				},
				formatResult: function(data) {
					return data.name;
				}
			}
		).result(function(event,data,formatted){
			if(data){
				$("#cmsNewsSearchZone [name=channel.objId]").val(data.objId);//回填id
			}
		});
});

