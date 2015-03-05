var giftCommentList={};
giftCommentList.oTable;
giftCommentList.currentTabId = 'tabs_manager';
	
	//刷新
	giftCommentList.reload = function(){
		giftCommentList.oTable.fnDraw();
	}
	
	//查询
	$("#giftCommentSearch").click(function(){
		var params = {};
		if($("#startDate").val()&&$("#endDate").val()){
			params = {"createTime":$("#startDate").val()+","+$("#endDate").val(),"createTime_op":"bt"};
		}else if($("#startDate").val()){
			params = {"createTime":$("#startDate").val(),"createTime_op":">="};
		}else if($("#endDate").val()){
			params = {"endDate":$("#endDate").val(),"createTime_op":"<="};
		}
		$(giftCommentList.oTable.dataTableSettings).attr("params",params);
		giftCommentList.oTable.fnDraw();
	});
	
	//添加操作字符串
	giftCommentList.getOperationStr = function(objId){		
		return '<td><a href="javascript:giftCommentList.viewGiftComment(\''+objId+'\')" ><span>查看<span></a>  <a href="javascript:giftCommentList.removeGiftComment(\''+objId+'\')" ><span>删除</span></a></td>';
	}
	
	//新增
	$("#addGiftComment").click(function(){
		$.epsDialog({
			title:'新增礼品点评',
			url:$('#initPath').val()+'/GiftCommentController.do?method=toGiftCommentAddORModify',
			onclose:function(){giftCommentList.reload();}
		});
	});
	
	//查看详情
	giftCommentList.viewGiftComment = function(objId){		
		$.epsDialog({
			title:'礼品点评查看',
			url:$('#initPath').val()+'/GiftCommentController.do?method=viewGiftComment&objId='+objId,
			onclose:function(){giftCommentList.reload();}
		});
	}	
	
	//删除
	giftCommentList.removeGiftComment = function(objId){
		if(confirm('删除将不能恢复,确定删除吗？')){s
			$.getJSON($('#initPath').val()+'/GiftCommentController.do?method=removeGiftComment',{objId:objId},function(json){
				if(json.failure) {
					alert(json.result);
					return;
				}
				if(json.giftComment=="success"){
					alert("删除成功");
					giftCommentList.reload();
				}
			});
		}		
	}
	
$(document).ready(function(){
	
    //开始时间
    $("#startDate").epsDatepicker();  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker();  //增加开始时间的规则
	
	//加载tab页
	$('#epsTabs').tabs();
	
	//tab页点击事件
	$('li a.refreshData').click(function(){
		giftCommentList.currentTabId = $(this).attr('id');
		
		if(giftCommentList.currentTabId=='tabs_manager'){
			$(giftCommentList.oTable.dataTableSettings).attr('params',{});
			giftCommentList.oTable.fnDraw();
		}
	});
	
	//填充广告列表
	giftCommentList.oTable = $('#giftCommentList').dataTable({
		'singleSelect':true,
		'checkbox':false,
		'searchZone':'giftCommentSearchForm',
		'sAjaxSource':$('#initPath').val()+"/GiftCommentController.do?method=list",
		 params:{},
		'queryColumns':'gift.giftName,title,comment,rateName,createTime',
		'hiddenColumns':'objId,gift.objId',
		'alias':'',
		'fnInitComplete':function(oSettings){},
		'fnDrawCallback':function(oSettings){
			giftCommentList.oTable.oSettings = oSettings;
		},
		'fnRowCallback':function(nRow,aData,iDisplayIndex){
			$(nRow).append(giftCommentList.getOperationStr(aData.objId));
			return nRow;
		}
	});
});