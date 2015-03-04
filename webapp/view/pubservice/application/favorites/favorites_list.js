/*
 * 维护品牌页面
 * created by yucy
 */
var favoritesList={};
favoritesList.oTable;

//定位
favoritesList.gotoFavorites = function(favoriteId,favoriteType){
	//商品
	if('01'==favoriteType){
		common.geToGoodsDetail(favoriteId);
	}
	//供应商
	else if('02'==favoriteType){
		common.goToOrgShop(favoriteId); //跳转到企业商铺
	}
	//采购人
	else if('04'==favoriteType){
		common.geToBuyerDetail(favoriteId);
	}
	//专家
	else if('05'==favoriteType){
		common.goToExpertDetail(favoriteId);
	}
	//项目
	else if('07'==favoriteType){
		common.goToBulletinDetail(favoriteId,"");
	}
//代理机构暂屏蔽
//	else if('03'==favoriteType){
//		var targetUrl = $('#initPath').val()+"/AgencyShowController.do?method=getAgencyInfo::objId=" + favoriteId;
//		window.open($('#initPath').val()+"/IndexViewController.do?method=index&select=goToSupplier&targetUrl="+targetUrl);
//	}
//酒店暂屏蔽
//	else if('06'==favoriteType){
//		var targetUrl = $('#initPath').val()+"/HotelShowController.do?method=getHotelInfo::objId=" +favoriteId;
//		var contentSuppUrl = $('#initPath').val()+'/HotelShowController.do?method=getRecommendHotel::rp=10::page=1';
//		window.open( $('#initPath').val()+"/IndexViewController.do?method=index&contentMainUrl="+targetUrl+"&contentSuppUrl="+contentSuppUrl );
//	}
}

//取得品牌列表
favoritesList.getFavoritesList = function(){
	if(null==favoritesList.oTable){
		favoritesList.oTable = $('#favofitesList').dataTable({   
			"params":{'favoriteType':'01'},
			'searchZone':'favofitesSearchForm',
			'singleSelect':true,	
			'checkbox':false,		
			'queryColumns':'favoriteName,favoriteKey,createTime',
			'hiddenColumns':'favoriteId,favoriteType',
			'alias':'',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				$(nRow).find("td:eq(0)").html('<a href="javascript:void(0);" onclick="favoritesList.gotoFavorites(\''+aData.favoriteId+'\',\''+aData.favoriteType+'\');return false;">'+aData.favoriteName+'</a>');
				
				$(nRow).append('<td align="center"><a name="delete" title="删除" ><span>删除</span></a></td>');
				$(nRow).find("a[name=delete]").click(function(){
					if(confirm('确认删除？')){
						$.getJSON($("#initPath").val()+"/FavoritesController.do?method=remove",{"objId":aData.objId},function(json){
							if(json.success){
								favoritesList.getFavoritesList();//刷新列表
							}
						})
					}
				})
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/FavoritesController.do?method=list"
		});
	}else{
		favoritesList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	
	//初始化tabs
	$('#epsTabs').tabs({}); 
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				//商品
			$(favoritesList.oTable.dataTableSettings).attr("params",{'favoriteType':'01'});
		}else if(ui.index==1){			//供应商
			$(favoritesList.oTable.dataTableSettings).attr("params",{'favoriteType':'02'});
		}
		else if(ui.index==2){			//采购人
			$(favoritesList.oTable.dataTableSettings).attr("params",{'favoriteType':'04'});
		}
		else if(ui.index==3){			//专家
			$(favoritesList.oTable.dataTableSettings).attr("params",{'favoriteType':'05'});
		}
		else if(ui.index==4){			//项目
			$(favoritesList.oTable.dataTableSettings).attr("params",{'favoriteType':'07'});
		}
//		else if(ui.index==2){			//代理机构暂屏蔽
//			$(favoritesList.oTable.dataTableSettings).attr("params",{'favoriteType':'03'});
//		}
//		else if(ui.index==3){			//酒店
//			$(favoritesList.oTable.dataTableSettings).attr("params",{'favoriteType':'06'});
//		}
		
		favoritesList.getFavoritesList();
	});

	//加载列表
	favoritesList.getFavoritesList();
	
	//点击查询按钮
	$("#favofitesSearch").click(function(){
		favoritesList.getFavoritesList();
	})
});