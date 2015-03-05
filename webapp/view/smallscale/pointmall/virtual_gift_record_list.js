
var virtualGiftList={};

virtualGiftList.oTable1;
virtualGiftList.oTable2;

virtualGiftList.currentTabID="tabs_deal"; //当前Tab的ID

	//生成“处理” 和“查看”超链接
	virtualGiftList.getOperatorStr=function(objId){			
		var appendStr = '<td>';
		if ("tabs_deal"==virtualGiftList.currentTabID) {			
				appendStr +=  '<a href="javascript:void(0);" onclick="virtualGiftList.dealOperatorPage(\'prDeal\',\''+objId+'\');return false;"><span>查看</span></a>';			
		}
		else if("tabs_dealed"==virtualGiftList.currentTabID){			
				appendStr +=  '<a href="javascript:void(0);" onclick="virtualGiftList.dealOperatorPage(\'received\',\''+objId+'\');return false;"><span>确认</span></a>';						
		}
		else if("tabs_less"==virtualGiftList.currentTabID){			
			appendStr +=  '<a href="javascript:void(0);" onclick="virtualGiftList.dealOperatorPage(\'less\',\''+objId+'\');return false;"><span>查看</span></a>';						
		}
		else if("tabs_received"==virtualGiftList.currentTabID){			
			appendStr +=  '<a href="javascript:void(0);" onclick="virtualGiftList.dealOperatorPage(\'show\',\''+objId+'\');return false;"><span>查看</span></a>';						
		}				
		appendStr += "</td>";

		return appendStr;
	
	}



	//打开处理/查看页面
	virtualGiftList.dealOperatorPage=function(type,objId){			
		//弹出已回复建议意见详情页面
		 $.epsDialog({
		        title:'礼品兑换信息',
				url:$('#initPath').val()+'/VirtualGiftRecordController.do?method=toDealView&objId='+ objId+'&type='+type
				,onClose:function(){
					if(virtualGiftList.oTable1){
						virtualGiftList.oTable1.fnDraw();
					}
					if(virtualGiftList.oTable2){
						virtualGiftList.oTable2.fnDraw();
					}
				}
		  });	    
	}


	$(document).ready(function(){	
	//加载tabs
	var $tabs = $('#epsTabs').tabs({}); 
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		virtualGiftList.currentTabID = $(this).attr("id");	
		
		if(virtualGiftList.currentTabID == "tabs_deal"){//未处理
			$(virtualGiftList.oTable1.dataTableSettings).attr('params', {"dealStatus":"00"});
			$('#currentTabID').attr("value","tabs_deal");
			virtualGiftList.oTable1.fnDraw();
		}else if(virtualGiftList.currentTabID == "tabs_dealed"){//已处理			
			$(virtualGiftList.oTable2.dataTableSettings).attr('params', {"dealStatus":"01"});
			$('#currentTabID').attr("value","tabs_dealed");
			virtualGiftList.oTable2.fnDraw();
		}else if(virtualGiftList.currentTabID == "tabs_less"){//缺货			
			$(virtualGiftList.oTable1.dataTableSettings).attr('params', {"dealStatus":"02"});
			$('#currentTabID').attr("value","tabs_less");
			virtualGiftList.oTable1.fnDraw();
		}else if(virtualGiftList.currentTabID == "tabs_received"){//已确认			
			$(virtualGiftList.oTable2.dataTableSettings).attr('params', {"dealStatus":"03"});
			$('#currentTabID').attr("value","tabs_received");
			virtualGiftList.oTable2.fnDraw();
		}
	});

	//开始时间
    $("#startDate").epsDatepicker({applyRule: endRule });  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker({applyRule: startRule });  //增加开始时间的规则

    var queryColumns1 = 'gift.giftName,createTime,receiveEmail';
    var queryColumns2 = 'gift.giftName,erule.score,createTime,receiveEmail,dealTime';
  
   
 	//未处理
	virtualGiftList.oTable1=$('#dealList').dataTable( {
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':queryColumns1,//指定要查询的列
		//'alias':'goodsPriceSupplier.goods.productName,goodsPriceSupplier.supplier.name,useStatus,goodsPriceSupplier.protocolCN,marktUnitPrice,dscuRate,prtcPrice,efctDate,endDate,town.parent.parent.name',
		'hiddenColumns':'objId',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	

			$(nRow).append(virtualGiftList.getOperatorStr(aData.objId))//添加操作按钮
			
			return nRow;
		},
		params:{"dealStatus":"00"},
		"sAjaxSource": $('#initPath').val()+"/VirtualGiftRecordController.do?method=list",
		'searchZone':'recordSearchZone'
	});
	
	//已处理
	virtualGiftList.oTable2=$('#dealedList').dataTable({
		'singleSelect':true,//(false表示可以多选)
		'checkbox':false,//(默认为true)为true时左边会出现复选框,  没必要用单选框(因为有更好的表达方式)
		'queryColumns':queryColumns2,//指定要查询的列
		//'alias':'goodsPriceSupplier.goods.productName,goodsPriceSupplier.supplier.name,useStatus,goodsPriceSupplier.protocolCN,marktUnitPrice,dscuRate,prtcPrice,efctDate,endDate,town.parent.parent.name',
		'hiddenColumns':'objId',//隐藏查询不显示的列属性
		'fnInitComplete':function(oSettings) {
			 //表格初始化完毕、未开始查询之前的方法
		},
		'fnDrawCallback':function(oSettings) {	
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	

			$(nRow).append(virtualGiftList.getOperatorStr(aData.objId))//添加操作按钮
			
			return nRow;
		},
		params:{"dealStatus":"01"},
		"sAjaxSource": $('#initPath').val()+"/VirtualGiftRecordController.do?method=list",
		'searchZone':'recordSearchZone'
	});
	
	if($('#currentTabID').val()=='tabs_deal'){	
		$('#tabs_deal').click();
	}
	else if ($('#currentTabID').val()=='tabs_dealed'){
		$('#tabs_dealed').click();		
	}
	
	//查询
	$("#query").click(function(){				
		if($('#currentTabID').val()=='tabs_deal' || $('#currentTabID').val()==''){	
			if($("#startDate").val().length > 0 && $("#endDate").val().length == 0){
		 		$(virtualGiftList.oTable1.dataTableSettings).attr("params",
		 				$.extend(virtualGiftList.oTable1.dataTableSettings[0].params,{"createTime":$("#startDate").val(),"createTime_op":"ge"}));
		 	}
		 	else if($("#endDate").val().length > 0 && $("#startDate").val().length == 0){
		 		$(virtualGiftList.oTable1.dataTableSettings).attr("params",
		 				$.extend(virtualGiftList.oTable1.dataTableSettings[0].params,{"createTime":$("#endDate").val(),"createTime_op":"le"}));
		 	}
		 	else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
		 		$(virtualGiftList.oTable1.dataTableSettings).attr("params",
		 				$.extend(virtualGiftList.oTable1.dataTableSettings[0].params,{"createTime":$("#startDate").val()+","+$("#endDate").val(),"createTime_op":"bt"}));
		 	}
			virtualGiftList.oTable1.fnDraw();
		}
		else if ($('#currentTabID').val()=='tabs_dealed'){
			if($("#startDate").val().length > 0 && $("#endDate").val().length == 0){
		 		$(virtualGiftList.oTable2.dataTableSettings).attr("params",
		 				$.extend(virtualGiftList.oTable1.dataTableSettings[0].params,{"createTime":$("#startDate").val(),"createTime_op":"ge"}));
		 	}
		 	else if($("#endDate").val().length > 0 && $("#startDate").val().length == 0){
		 		$(virtualGiftList.oTable2.dataTableSettings).attr("params",
		 				$.extend(virtualGiftList.oTable1.dataTableSettings[0].params,{"createTime":$("#endDate").val(),"createTime_op":"le"}));
		 	}
		 	else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
		 		$(virtualGiftList.oTable2.dataTableSettings).attr("params",
		 				$.extend(virtualGiftList.oTable1.dataTableSettings[0].params,{"createTime":$("#startDate").val()+","+$("#endDate").val(),"createTime_op":"bt"}));
		 	}
			virtualGiftList.oTable2.fnDraw();	
		}		
	});	

});


