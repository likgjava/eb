/**
 * 礼品管理js文件 by yucy 2011.1.7
 */
var GiftManageList = {};
GiftManageList.currentTabID = "tabs_newGift";

//修改或新增
GiftManageList.toCreateOrUpdate = function(giftId){
	GiftManageList.setBackUrl();//设置返回路径
	$("#conBody").loadPage($("#initPath").val()+"/GiftController.do?method=toCreateOrUpdateGift&objId="+(giftId==null?"":giftId));
}

//删除
GiftManageList.deleteGift = function(giftIds){
	if(!giftIds){alert("至少选中一行数据操作！");return;}
	giftIds = giftIds.split(",");
	if(confirm("确认删除？")){
		$.getJSON($("#initPath").val()+"/GiftController.do?method=remove",{objId:giftIds},function(json){
			if(json.success){
				alert("操作成功！");
				GiftManageList.oTable.fnDraw();//刷新
			}
		});
	}
}

//设为推荐礼品
GiftManageList.recommendGift = function (giftIds,isRecommended){
	if(!giftIds){alert("至少选中一行数据操作！");return;}
	giftIds = giftIds.split(",");
	if(confirm("确认操作？")){
		$.getJSON($("#initPath").val()+"/GiftController.do?method=updateGiftReCommond",{objIds:giftIds,isRecommended:isRecommended},function(json){
			if(json.success){
				GiftManageList.oTable.fnDraw();//刷新
			}
		});
	}
}

//设置返回路径
GiftManageList.setBackUrl = function(){
	$("#returnUrl").val($("#initPath").val()+"/GiftController.do?method=toGiftListbyManager");
}

//创建或刷新列表数据
GiftManageList.getTableList = function() {
	//加载礼品列表
	if(!GiftManageList.oTable) {
		GiftManageList.oTable = $('#giftManageList').dataTable({
			'singleSelect' : false,
			'checkbox' : true,
			'queryColumns' : 'picture,giftName,giftSeries.name,giftType,exchangeCount,isUsed,createTime',
			'alias' : 'picture,giftName,giftSeries.name,giftTypeCN,exchangeCount,isUsedCN,createTime',
			'hiddenColumns':'isRecommended',
			'fnInitComplete' : function(oSettings) {
			},
			'fnDrawCallback' : function(oSettings) {
				GiftManageList.oTable.oSettings = oSettings;
			},
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				//图片
				$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["picture"]+'" CURSOR="hand" width="60px" height="60px"/>');

				//添加操作按钮
				$(nRow).append('<td class="operation"><a href="javascript:void(0);" name="modify"><span>修改</span></a></td>');
				$(nRow).find("a[name=modify]").click(function(){
					GiftManageList.setBackUrl();//设置返回路径
					GiftManageList.toCreateOrUpdate(aData.objId);
				});
				
				//查看
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="detail"><span>查看</span></a>');
				$(nRow).find("a[name=detail]").click(function(){
					$.epsDialog({
						title:"查看礼品信息",
						url:$("#initPath").val()+"/GiftController.do?method=toGiftDetail&objId="+aData.objId
					})
				});
				
				//删除
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="delete"><span>删除</span></a>');
				$(nRow).find("a[name=delete]").click(function(){
					GiftManageList.deleteGift(aData.objId);
				});
				
				
				//推荐
				if(aData.isRecommended=='true'){
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="cancelRecommend"><span>取消推荐</span></a>');
					$(nRow).find("a[name=cancelRecommend]").click(function(){
						GiftManageList.recommendGift(aData.objId,'0');
					});
				}else{
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="recommend"><span>推荐</span></a>');
					$(nRow).find("a[name=recommend]").click(function(){
						GiftManageList.recommendGift(aData.objId,'1');
					});
				}
				
				
				//评价
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="comment"><span>点评</span></a>');
				$(nRow).find("a[name=comment]").click(function(){
					$.epsDialog({
						title:"新增礼品点评信息",
						url:$("#initPath").val()+"/GiftCommentController.do?method=toGiftCommentAddORModify&objId="+aData.objId
					})
				});
				
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/GiftController.do?method=list",
			"params":{},
			'searchZone':'GiftManageSearchForm'
		});
	}else {
		var params = {};
		if($("#startDate").val()&&$("#endDate").val()){
			params = {"createTime":$("#startDate").val()+","+$("#endDate").val(),"createTime_op":"bt"};
		}else if($("#startDate").val()){
			params = {"createTime":$("#startDate").val(),"createTime_op":">="};
		}else if($("#endDate").val()){
			params = {"endDate":$("#endDate").val(),"createTime_op":"<="};
		}
		$(GiftManageList.oTable.dataTableSettings).attr("params",params);
		GiftManageList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	
	//tab无法触发第一个选中，所以需要手动加载一次
	GiftManageList.getTableList();
	
    //开始时间
    $("#startDate").epsDatepicker();  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker();  //增加开始时间的规则
})