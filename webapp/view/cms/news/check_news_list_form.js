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
var option={"checkStatus":"00,-1","checkStatus_op":"!in"};
var isLeaf = false;

	// 通过
	cmsNewsList.pass=function(name,grid){
		if(!cmsNewsList.validation(name,grid))return;
		if(window.confirm("确定将该记录通过审核吗？")){
			
			$.getJSON($('#initPath').val()+'/CmsNewsController.do?method=updateCheckStatus', {"operator":"pass","objId":$(grid).getSelect()}, function(json){
				if(json.result=="true"){
					alert("审核成功！")
					$('#conBody').loadPage($('#initPath').val()+'/CmsNewsController.do?method=toCheckNewsListForm');
				}else{
					alert("审核失败！")
				}
			});
		}
	}   
	
	// 退回
	cmsNewsList.back=function(name,grid){
		if(!cmsNewsList.validation(name,grid))return;
		if(window.confirm("确定将该记录退回给上级审核人吗？")){
			$.getJSON($('#initPath').val()+'/CmsNewsController.do?method=updateCheckStatus', {"operator":"back","objId":$(grid).getSelect()}, function(json){
				if(json.result=="true"){
					alert("退回成功！")
					$('#conBody').loadPage($('#initPath').val()+'/CmsNewsController.do?method=toCheckNewsListForm');
				}else{
					alert("退回失败！")
				}
			});
		}
	}   
	
	// 直接退回到编辑
	cmsNewsList.backAll=function(name,grid){
		if(!cmsNewsList.validation(name,grid))return;
		if(window.confirm("确定将该记录直接退回给编辑人吗？")){
			$.getJSON($('#initPath').val()+'/CmsNewsController.do?method=updateCheckStatus', {"operator":"backAll","objId":$(grid).getSelect()}, function(json){
				if(json.result=="true"){
					alert("退回成功！")
					$('#conBody').loadPage($('#initPath').val()+'/CmsNewsController.do?method=toCheckNewsListForm');
				}else{
					alert("退回失败！")
				}
			});
		}
	}   
	
	// 列表操作验证
	cmsNewsList.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择要'+name+'的记录');return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一条记录');return false;}//是否只选中一个
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

