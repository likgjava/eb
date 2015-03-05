/**
 * 礼品管理js文件 by yucy 2011.1.7
 */
var GiftManageList = {};
GiftManageList.currentTabID = "tabs_newGift";

//修改或新增
GiftManageList.UpdateBySupplier = function(giftId){
	GiftManageList.setBackUrl();//设置返回路径
	$("#conBody").loadPage($("#initPath").val()+"/GiftController.do?method=toUpdateGiftBySupplier&objId="+(giftId==null?"":giftId));
}


//设置返回路径
GiftManageList.setBackUrl = function(){
	$("#returnUrl").val($("#initPath").val()+"/GiftController.do?method=toGiftListbySupplier");
}

//创建或刷新列表数据
GiftManageList.getTableList = function() {
	//加载礼品列表
	if(!GiftManageList.oTable) {
		GiftManageList.oTable = $('#giftManageList').dataTable({
			'singleSelect' : false,
			'checkbox' : true,
			'queryColumns' : 'picture,giftName,giftCode,giftSeries.name,giftType,exchangeCount,isUsed,createTime',
			'alias' : 'picture,giftName,giftCode,giftSeries.name,giftTypeCN,exchangeCount,isUsedCN,createTime',
			//'hiddenColumns':'',
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
					GiftManageList.UpdateBySupplier(aData.objId);
				});
				
				//查看
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="detail"><span>查看</span></a>');
				$(nRow).find("a[name=detail]").click(function(){
					$.epsDialog({
						title:"查看礼品信息",
						url:$("#initPath").val()+"/GiftController.do?method=toGiftDetail&objId="+aData.objId
					})
				});	
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/GiftController.do?method=list&operateOrg=supplier",
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