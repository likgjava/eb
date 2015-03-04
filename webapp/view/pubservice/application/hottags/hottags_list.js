/*
 * 热门标签列表页面
 * created by yucy
 */
var hottagsList={};
hottagsList.oTable;


//新增标签
hottagsList.newTags = function(){
	$("#conBody").loadPage($("#initPath").val()+"/HotTagsController.do?method=toCreateOrUpDateTags");
}

//删除标签
hottagsList.delTags = function(ids){
	if(ids==null||ids.length<=0){alert("至少选择一行数据！");return }
	if(confirm('确认删除？')){
		$.getJSON($("#initPath").val()+"/HotTagsController.do?method=remove",{"objId":ids},function(json){
			if(json.success){
				hottagsList.getHotTagsList();//刷新列表
			}
		})
	}
}

//取得标签列表
hottagsList.getHotTagsList = function(){
	if(null==hottagsList.oTable){
		hottagsList.oTable = $('#hottagsList').dataTable({   
			"params":{'tagsType':'01'},
			'searchZone':'hottagsSearchForm',
			'singleSelect':false,	
			'checkbox':true,		
			'queryColumns':'tagsName,createTime',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				$(nRow).append('<td align="center"><a name="update" title="修改">修改</a><a name="delete" title="删除">删除</a></td>');
				//修改
				$(nRow).find("a[name=update]").click(function(){
					$("#conBody").loadPage($("#initPath").val()+"/HotTagsController.do?method=toCreateOrUpDateTags&objId="+aData.objId);
				})
				//删除
				$(nRow).find("a[name=delete]").click(function(){
					hottagsList.delTags(aData.objId);
				})
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/HotTagsController.do?method=list"
		});
	}else{
		hottagsList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//初始化tabs
	$('#epsTabs').tabs({}); 
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){ //商品
			$(hottagsList.oTable.dataTableSettings).attr("params",{'tagsType':'01'});
		}else if(ui.index==1){ //供应商
			$(hottagsList.oTable.dataTableSettings).attr("params",{'tagsType':'02'});
		}
//		else if(ui.index==2){ //代理机构
//			$(hottagsList.oTable.dataTableSettings).attr("params",{'tagsType':'03'});
//		}
		else if(ui.index==2){ //行情
			$(hottagsList.oTable.dataTableSettings).attr("params",{'tagsType':'04'});
		}
		else if(ui.index==3){ //商品分类
			$(hottagsList.oTable.dataTableSettings).attr("params",{'tagsType':'05'});
		}
		else if(ui.index==4){ //采购品目
			$(hottagsList.oTable.dataTableSettings).attr("params",{'tagsType':'06'});
		}
		hottagsList.getHotTagsList();
	});

	//加载列表
	hottagsList.getHotTagsList();
	
	//点击查询按钮
	$("#hottagsSearch").click(function(){
		hottagsList.getHotTagsList();
	})
});

