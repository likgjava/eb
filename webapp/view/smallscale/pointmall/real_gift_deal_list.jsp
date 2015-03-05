<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="conSearch">
	<h4><span><spring:message code="globe.query"/></span></h4>
	<form id="recordSearchZone" >
	<input name="currentTabID" id="currentTabID"  type="hidden" value="<c:out value="${param.currentTabID}"/>" >
		<ul>
		<li>
			<label>礼品名称：</label>
			<input name="gift.giftName" id="gift.giftName">
			<input type="hidden" name="gift.giftName_op" id="giftName_op" value="like">
		</li>	
		 <li>
	  	        <label for="createTime">兑换时间</label>	  	        
                <input class="date"  name="startDate" id="startDate">&nbsp;&nbsp;到
	            <input class="date"  name="endDate" id="endDate">
          </li>
                		
		  <li class="operationBtnDiv right"><button type="button" id="query"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
	</form>
	</div>
<!-- Tab页 -->
<div id="epsTabs" class="epsTabs">
  <ul>
    <li>
      <a href="#dealInfo" id = "tabs_deal" class="refreshData"><span>未处理</span></a>
    </li>
    <li>
      <a href="#dealedInfo" id = "tabs_dealed" class="refreshData"><span>已发货待确认</span></a>
    </li> 
    <li>
      <a href="#dealedInfo" id = "tabs_less" class="refreshData"><span>缺货</span></a>
    </li>
    <li>
      <a href="#dealedInfo" id = "tabs_received" class="refreshData"><span>已收货</span></a>
    </li>  
  </ul>
  <div id="dealInfo">   
  <div>
    <table id="dealList" class="frontTableList">
    <caption><span></span></caption>
      <thead>
      	<tr>  
      	  <th class="left">礼品名称</th>     	     
          <th class="left">兑换人</th>
		  <th class="center date">兑换日期</th>
          <th class="left">收货人</th>
          <th class="left">联系电话</th>             
          <th class="right">兑换数量</th>                      
          <th class="operation center">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>  
  </div>
  
  <div id="dealedInfo">  
  <div>
    <table id="dealedList" class="frontTableList">
    <caption><span></span></caption>
      <thead>
      	<tr>
      	  <th class="left">礼品名称</th>      	  
		  <th class="center date">兑换日期</th>
          <th class="left">收货人</th> 
          <th class="left">联系电话</th>          
          <th class="center datet">处理日期</th>                      
          <th class="operation center">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>  
</div> 
</div>

<script>

var giftDealList={};

giftDealList.oTable1;
giftDealList.oTable2;

giftDealList.currentTabID="tabs_deal"; //当前Tab的ID

	//生成“处理” 和“查看”超链接
	giftDealList.getOperatorStr=function(objId){			
		var appendStr = '<td>';
		if ("tabs_deal"==giftDealList.currentTabID) {			
				appendStr +=  '<a href="javascript:void(0);" onclick="giftDealList.dealOperatorPage(\'deal\',\''+objId+'\');return false;"><span>处理</span></a>';			
		}
		else if("tabs_dealed"==giftDealList.currentTabID){			
				appendStr +=  '<a href="javascript:void(0);" onclick="giftDealList.dealOperatorPage(\'show\',\''+objId+'\');return false;"><span>查看</span></a>';						
		}
		else if("tabs_less"==giftDealList.currentTabID){			
			appendStr +=  '<a href="javascript:void(0);" onclick="giftDealList.dealOperatorPage(\'show\',\''+objId+'\');return false;"><span>查看</span></a>';						
		}
		else if("tabs_received"==giftDealList.currentTabID){			
			appendStr +=  '<a href="javascript:void(0);" onclick="giftDealList.dealOperatorPage(\'show\',\''+objId+'\');return false;"><span>查看</span></a>';						
		}				
		appendStr += "</td>";

		return appendStr;
	
	}



	//打开处理/查看页面
	giftDealList.dealOperatorPage=function(type,objId){			
		//弹出已回复建议意见详情页面
		 $.epsDialog({
		        title:'礼品兑换信息',
				url:$('#initPath').val()+'/RealGiftRecordController.do?method=toDealView&objId='+ objId+'&type='+type
				,onClose:function(){
					//刷新
					if(giftDealList.oTable1){
						giftDealList.oTable1.fnDraw();
					}
					if(giftDealList.oTable2){
						giftDealList.oTable2.fnDraw();
					}
				}
		  });	    
	}


	$(document).ready(function(){	
	//加载tabs
	var $tabs = $('#epsTabs').tabs({}); 
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		giftDealList.currentTabID = $(this).attr("id");	
		
		if(giftDealList.currentTabID == "tabs_deal"){//未处理
			$(giftDealList.oTable1.dataTableSettings).attr('params', {"dealStatus":"00"});
			$('#currentTabID').attr("value","tabs_deal");
			giftDealList.oTable1.fnDraw();
		}else if(giftDealList.currentTabID == "tabs_dealed"){//已处理			
			$(giftDealList.oTable2.dataTableSettings).attr('params', {"dealStatus":"01"});
			$('#currentTabID').attr("value","tabs_dealed");
			giftDealList.oTable2.fnDraw();
		}else if(giftDealList.currentTabID == "tabs_less"){//已处理			
			$(giftDealList.oTable2.dataTableSettings).attr('params', {"dealStatus":"02"});
			$('#currentTabID').attr("value","tabs_less");
			giftDealList.oTable2.fnDraw();
		}else if(giftDealList.currentTabID == "tabs_received"){//已处理			
			$(giftDealList.oTable2.dataTableSettings).attr('params', {"dealStatus":"03"});
			$('#currentTabID').attr("value","tabs_received");
			giftDealList.oTable2.fnDraw();
		}
	});

	//开始时间
    $("#startDate").epsDatepicker({applyRule: endRule });  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker({applyRule: startRule });  //增加开始时间的规则

    var queryColumns1 = 'gift.giftName,createUser.emp.name,createTime,receiverName,receiverTel,giftCount';
    var queryColumns2 = 'gift.giftName,createTime,receiverName,receiverTel,dealTime';
  
   
 	//未处理
	giftDealList.oTable1=$('#dealList').dataTable( {
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

			$(nRow).append(giftDealList.getOperatorStr(aData.objId))//添加操作按钮
			
			return nRow;
		},
		params:{"dealStatus":"00"},
		"sAjaxSource": $('#initPath').val()+"/RealGiftRecordController.do?method=list&isAdmin=true",
		'searchZone':'recordSearchZone'
	});
	
	//已处理
	giftDealList.oTable2=$('#dealedList').dataTable({
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

			$(nRow).append(giftDealList.getOperatorStr(aData.objId))//添加操作按钮
			
			return nRow;
		},
		params:{"dealStatus":"01"},
		"sAjaxSource": $('#initPath').val()+"/RealGiftRecordController.do?method=list&isAdmin=true",
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
		 		$(giftDealList.oTable1.dataTableSettings).attr("params",
		 				$.extend(giftDealList.oTable1.dataTableSettings[0].params,{"createTime":$("#startDate").val(),"createTime_op":"ge"}));
		 	}
		 	else if($("#endDate").val().length > 0 && $("#startDate").val().length == 0){
		 		$(giftDealList.oTable1.dataTableSettings).attr("params",
		 				$.extend(giftDealList.oTable1.dataTableSettings[0].params,{"createTime":$("#endDate").val(),"createTime_op":"le"}));
		 	}
		 	else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
		 		$(giftDealList.oTable1.dataTableSettings).attr("params",
		 				$.extend(giftDealList.oTable1.dataTableSettings[0].params,{"createTime":$("#startDate").val()+","+$("#endDate").val(),"createTime_op":"bt"}));
		 	}
			giftDealList.oTable1.fnDraw();
		}
		else if ($('#currentTabID').val()=='tabs_dealed'){
			if($("#startDate").val().length > 0 && $("#endDate").val().length == 0){
		 		$(giftDealList.oTable2.dataTableSettings).attr("params",
		 				$.extend(giftDealList.oTable1.dataTableSettings[0].params,{"createTime":$("#startDate").val(),"createTime_op":"ge"}));
		 	}
		 	else if($("#endDate").val().length > 0 && $("#startDate").val().length == 0){
		 		$(giftDealList.oTable2.dataTableSettings).attr("params",
		 				$.extend(giftDealList.oTable1.dataTableSettings[0].params,{"createTime":$("#endDate").val(),"createTime_op":"le"}));
		 	}
		 	else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
		 		$(giftDealList.oTable2.dataTableSettings).attr("params",
		 				$.extend(giftDealList.oTable1.dataTableSettings[0].params,{"createTime":$("#startDate").val()+","+$("#endDate").val(),"createTime_op":"bt"}));
		 	}
			giftDealList.oTable2.fnDraw();	
		}		
	});	

});

</script>



