var GroupBuyingClassList = {};
GroupBuyingClassList.oTable;
GroupBuyingClassList.currentPage = ''; //当前页数
GroupBuyingClassList.totalPage = ''; //总页数

//修改团购分类
GroupBuyingClassList.updateGroupBuyingClass = function(objId){
	var url = $('#initPath').val()+"/GroupBuyingClassController.do?method=toCreateOrUpdateView&objId="+objId;
	$("#conBody").loadPage(url);
}

//删除团购分类
GroupBuyingClassList.deleteGroupBuyingClass = function(objId){
	if(confirm("确认删除吗？")){
		$.getJSON($('#initPath').val() + "/GroupBuyingClassController.do?method=remove&objId="+objId, function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			GroupBuyingClassList.getGroupBuyingClassList(); //刷新列表
		});
	}
}

//加载团购分类列表
GroupBuyingClassList.getGroupBuyingClassList = function(){
	if(null==GroupBuyingClassList.oTable){
		GroupBuyingClassList.oTable = $('#groupBuyingClassList').dataTable({
			'queryColumns' : 'name,goodsClass.goodsClassName,isShowIndex',
			'hiddenColumns': '',
			'fnDrawCallback' : function(oSettings) {
				GroupBuyingClassList.oTable.oSettings = oSettings;
				var totalRecords = oSettings._iRecordsTotal;
				GroupBuyingClassList.currentPage = oSettings._iDisplayStart;
				var pageSize = oSettings._iDisplayLength;
				var totalPage = totalRecords % pageSize == 0 ? totalRecords/pageSize : totalRecords/pageSize+1;
				GroupBuyingClassList.totalPage = parseInt(totalPage);
			   
				//第一页
				if(GroupBuyingClassList.currentPage==1){
					$("#groupBuyingClassList tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
				}
				//最后一页
				if(GroupBuyingClassList.currentPage==GroupBuyingClassList.totalPage){
					$("#groupBuyingClassList tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
				}
			},
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				$(nRow).find("td[name=isShowIndex]").html(aData.isShowIndex=='true' ? '是' : '否');
				$(nRow).append('<td class="operation"><a name="up" href="javascript:void(0);" onclick="GroupBuyingClassList.upClick(this);return false;"><span class="sysicon siUp">&nbsp;</span></a><a name="down" href="javascript:void(0);" onclick="GroupBuyingClassList.downClick(this)"><span class="sysicon siDown">&nbsp;</span></a></td>');
				
				var operStr = '<td class="operation">';
				operStr += '<a href="javascript:void(0);" onclick="GroupBuyingClassList.updateGroupBuyingClass(\''+aData.objId+'\');return false;">修改</a>';
				operStr += '<a href="javascript:void(0);" onclick="GroupBuyingClassList.deleteGroupBuyingClass(\''+aData.objId+'\');return false;">删除</a>';
				operStr += '</td>';
				$(nRow).append(operStr);
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+"/GroupBuyingClassController.do?method=list",
			"params":{"order":"sort"},
			'searchZone':'groupBuyingClassSearchForm'
		});
	}else{
		GroupBuyingClassList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//加载列表
	GroupBuyingClassList.getGroupBuyingClassList();
	
	//新增团购分类
	$('#addGroupBuyingClass').click(function(){
		var url = $('#initPath').val()+"/GroupBuyingClassController.do?method=toCreateOrUpdateView";
		$("#conBody").loadPage(url);
	});
})

//设置上下移动的样式
GroupBuyingClassList.drawUpAndDownCss=function(){
	$("#groupBuyingClassList tr a[name=up]>span").removeClass("siUpGray").addClass("siUp");
	$("#groupBuyingClassList tr a[name=down]>span").removeClass("siDownGray").addClass("siDown");
	
    //顶级节点 
	if(GroupBuyingClassList.currentPage==1){
		$("#groupBuyingClassList tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
	}
	if(GroupBuyingClassList.currentPage==GroupBuyingClassList.totalPage){
		$("#groupBuyingClassList tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
	}
}

//向上
GroupBuyingClassList.upClick=function(obj){
	if($(obj).find("span").hasClass("siUpGray")) {
		return false;
	}
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).prev();
	var id = $(curRow).attr("objid");
	
	//更新到数据库
	$.getJSON($('#initPath').val()+"/GroupBuyingClassController.do?method=updateSort",{"objId":id,"isToUp":'true'},function(json){
		if(json.success){
			var firstRow = $("#groupBuyingClassList tr").eq(1);
			var firstObjId = $(firstRow).attr("objid");
			if(firstObjId == id){ //如果移动的是第一行，则重画列表。
				GroupBuyingClassList.oTable.fnDraw();
			}else{
				$(targetRow).before(curRow); //把当前行放到目标行之前
				GroupBuyingClassList.drawUpAndDownCss(); //重画向上向下的样式
			}
		}
	})
};

//向下
GroupBuyingClassList.downClick=function(obj){
	if($(obj).find("span").hasClass("siDownGray")) {
		return false;
	}
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).next();
	var id = $(curRow).attr("objid");
	
	//更新到数据库
	$.getJSON($('#initPath').val()+"/GroupBuyingClassController.do?method=updateSort",{"objId":id,"isToUp":"false"},function(json){
		if(json.success){
			var lastRow = $("#groupBuyingClassList tr:last");
			var lastObjId = $(lastRow).attr("objid");
			
			if(lastObjId == id){ //如果移动的是最后一行，则重画列表。
				GroupBuyingClassList.oTable.fnDraw();
			}else{
				$(targetRow).after(curRow); //把当前行放到目标行之后
				GroupBuyingClassList.drawUpAndDownCss(); //重画向上向下的样式
			}
		}
	})
};