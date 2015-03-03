var currentTabID = 0;
var option={"checkStatus":"00,-1","checkStatus_op":"!in"};
$(document).ready(function(){
	$('#returnUrl').val($('#initPath').val()+'/view/cms/news/news_manager_list.jsp');
	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			currentTabID = ui.tab.id;
			$("#currentTab").val(ui.index); //当前tab的index
			if(ui.index==0){
				$('#newsListPage').empty().loadPage($('#initPath').val() + '/view/cms/news/wait_news_list.jsp');
			}else if(ui.index==1){
				$('#newsListPage').empty().loadPage($('#initPath').val() + '/view/cms/news/pass_news_list.jsp');
			}else if(ui.index==2){
				$('#newsListPage').empty().loadPage($('#initPath').val() + '/view/cms/news/reject_news_list.jsp');
			}
		}
	});
	
	//指定某一个tab被选中，默认值为0
	$tabs.tabs('select', parseInt($("#currentTab").val()));
	
	//tab无法触发第一个选中，所以需要手动加载一次
	if($("#currentTab").val() == "0") {
		$('#newsListPage').empty().loadPage($('#initPath').val() + '/view/cms/news/wait_news_list.jsp');
	}
	
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

