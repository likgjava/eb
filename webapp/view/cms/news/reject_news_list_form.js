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
var option={"checkStatus":"-1"};
var isLeaf = false;



// 修改
cmsNewsList.update=function(name,grid){
	if(!cmsNewsList.validation(name,grid))return;
	if(""==channelId || "-1"==channelId){
		
		var json = $("#cmsNewsGrid").flexGetRowJsonById($(grid).getSelect());
		channelId = json["channel.objId"];
	}
	
	// 跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+'/CmsNewsController.do?method=toCreateOrUpdate&addType=reject&channelId='+channelId+'&objId='+$(grid).getSelect());
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
			$(t).html("<a href='"+json.url+"' target='_blank'>"+$(t).html()+"</a>")
		},
		'channel.name' : function(id,t){
			var json = $("#cmsNewsGrid").flexGetRowJsonById(id); 
			$(t).html("<a href='"+json['channel.url']+"' target='_blank'>"+$(t).html()+"</a>")
		}
	});
}

$(document).ready(function(){
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

