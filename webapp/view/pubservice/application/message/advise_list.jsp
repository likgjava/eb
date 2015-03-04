<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="adviseSearchZone" >
	<input name="isAdmin" id="isAdmin"  type="hidden" value="<c:out value="${param.isAdmin}"/>" >
	<input name="currentTabID" id="currentTabID"  type="hidden" value="<c:out value="${param.currentTabID}"/>" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>	
			<li>
				<label for="createTime">建议时间：</label>	  	        
                <input name="startDate" id="startDate" style="width: 160px;" />&nbsp;&nbsp;到
	            <input name="endDate" id="endDate" style="width: 160px;" />
			</li>
			<li class="operationBtnDiv right"><button type="button" id="query"><span><spring:message code="globe.query"/></span></button></li>
		</ul>
	</div>
</form>
	
<div id="addAdviseDiv" class="formTips attention">
	<ul>
		<li><em>提示：</em><spring:message code="pubservice.message.advise"/></li>		
		<li>若要提出建议意见请点击<span class="sysicon siAdd"><a href="javascript:void(0);" id="adviseAddBtn"><strong>建议意见</strong></a></span></li>
	</ul>
</div>
	
<!-- Tab页 -->
<div id="epsTabs" class="">
	<ul>
		<li><a href="#adviseInfo" id="tabs_advise" class="refreshData"><span>未回复</span></a></li>
		<li><a href="#replyInfo" id="tabs_reply" class="refreshData"><span>已回复</span></a></li>   
	</ul>
	<div id="adviseInfo">   
		<table id="adviseList" class="frontTableList">
		<thead>
			<tr> 
				<c:if test="${param.isAdmin eq true}"><th class="center">建议人</th></c:if>
				<th class="left omission" omiLength="25">内容</th> 
				<th class="center datetime">建议时间</th>                       
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody></tbody>
		</table>`
	</div>  
  
	<div id="replyInfo">   
		<table id="replyList" class="frontTableList">
		<thead>
			<tr>
				<c:if test="${param.isAdmin eq true}"><th class="center">建议人</th></c:if>
         		<th class="left omission" omiLength="10">内容</th> 
          		<th class="center datetime">建议时间</th>                    
          		<th class="left">回复人</th>
          		<th class="center date">回复时间</th>              
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody></tbody>
		</table>
	</div>  
</div>

<script>
var adviseList={};

adviseList.oTable1;
adviseList.oTable2;

adviseList.currentTabID="tabs_advise"; //当前Tab的ID

	//生成“删除”，“回复”，“修改” 和“查看”超链接
	adviseList.getOperatorStr=function(objId){			
		var appendStr = '<td class="operation">';
		if ("tabs_advise"==adviseList.currentTabID) {
			
			if($('#isAdmin').val() == 'true'){
				appendStr +=  '<a href="javascript:void(0);" onclick="adviseList.replyOperatorPage(\'tabs_advise\',\''+objId+'\');return false;"><span>回复</span></a>';
			}
			else{
				appendStr +=  '<a href="javascript:void(0);" onclick="adviseList.updateOperatorPage(\''+objId+'\');return false;"><span>修改</span></a>';				
			}
			appendStr += '<a href="javascript:void(0);" onclick="adviseList.deleteOperatorPage(\''+objId+'\');return false;"><span>删除</span></a>';
		}
		else if("tabs_reply"==adviseList.currentTabID){			
			if($('#isAdmin').val() == 'true'){
				appendStr +=  '<a href="javascript:void(0);" onclick="adviseList.replyOperatorPage(\'tabs_reply\',\''+objId+'\');return false;"><span>修改回复</span></a>';
				appendStr +=  '<a href="javascript:void(0);" onclick="adviseList.deleteOperatorPage(\''+objId+'\');return false;"><span>删除</span></a>';
			}
			else{
				appendStr +=  '<a href="javascript:void(0);" onclick="adviseList.viewOperatorPage(\''+objId+'\');return false;"><span>查看</span></a>';				
			}
						
		}
		appendStr += "</td>";
		return appendStr
	}

	//删除
	adviseList.deleteOperatorPage=function(objId){	
		if(confirm('确认删除？')){
			$.getJSON($("#initPath").val()+"/AdviseController.do?method=remove",{"objId":objId},function(json){
				if(json.success){
					//alert("删除成功!");					
					$('#tabs_advise').click();					
				}
			})
		}
	}

	//修改
	adviseList.updateOperatorPage=function(objId){	
		$('#conBody').loadPage($('#initPath').val()+'/AdviseController.do?method=toCreateOrUpdate&objId='+ objId );		
	}

	//回复
	adviseList.replyOperatorPage=function(currentTabID,objId){	
		$('#conBody').loadPage($('#initPath').val()+'/AdviseController.do?method=toReply&objId='+ objId + '&currentTabID='+currentTabID);		
	}

	//查看
	adviseList.viewOperatorPage=function(objId){			
		//弹出已回复建议意见详情页面
		 $.epsDialog({
		        title:'建议意见',
				url:$('#initPath').val()+'/AdviseController.do?method=toView&objId='+ objId
		  });
	}

	$(document).ready(function(){	
	//加载tabs
	var $tabs = $('#epsTabs').tabs({}); 	

	//添加建议意见
	$("#adviseAddBtn").click(function() {		
		$('#conBody').loadPage($('#initPath').val()+"/AdviseController.do?method=toCreateAdvise");
	});
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		adviseList.currentTabID = $(this).attr("id");	
		
		if(adviseList.currentTabID == "tabs_advise"){//未回复
			$(adviseList.oTable1.dataTableSettings).attr('params', {"replyStatus":"0"});
			$('#currentTabID').attr("value","tabs_advise");
			adviseList.oTable1.fnDraw();
		}else if(adviseList.currentTabID == "tabs_reply"){//已回复报			
			$(adviseList.oTable2.dataTableSettings).attr('params', {"replyStatus":"1"});
			$('#currentTabID').attr("value","tabs_reply");
			adviseList.oTable2.fnDraw();
		}
	});

	//开始时间
    $("#startDate").epsDatepicker({applyRule: endRule, timeShow:true });  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker({applyRule: startRule, timeShow:true });  //增加开始时间的规则

    var queryColumns1 = 'content,createTime';
    var queryColumns2 = 'content,createTime,repplier.emp.name,replyTime';
    //区分管理员和普通用户的显示内容
    if($('#isAdmin').val() == 'true'){
		$("#addAdviseDiv").hide();//管理员用户隐藏添加建议意见功能

        //显示建议人列
		queryColumns1 = 'createUser.emp.name,content,createTime';
	    queryColumns2 = 'createUser.emp.name,content,createTime,repplier.emp.name,replyTime';
    }   
   
 	//未回复
	adviseList.oTable1=$('#adviseList').dataTable( {
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

			$(nRow).append(adviseList.getOperatorStr(aData.objId))//添加操作按钮
			
			return nRow;
		},
		params:{"replyStatus":"0"},
		"sAjaxSource": $('#initPath').val()+"/AdviseController.do?method=list",
		'searchZone':'adviseSearchZone'
	});
	
	//已回复
	adviseList.oTable2=$('#replyList').dataTable({
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

			$(nRow).append(adviseList.getOperatorStr(aData.objId))//添加操作按钮
			
			return nRow;
		},
		params:{"replyStatus":"1"},
		"sAjaxSource": $('#initPath').val()+"/AdviseController.do?method=list",
		'searchZone':'adviseSearchZone'
	});
	
	if($('#currentTabID').val()=='tabs_advise'){	
		$('#tabs_advise').click();
	}
	else if ($('#currentTabID').val()=='tabs_reply'){
		$('#tabs_reply').click();		
	}
	
	//查询
	$("#query").click(function(){				
		if($('#currentTabID').val()=='tabs_advise' || $('#currentTabID').val()==''){	
			if($("#startDate").val().length > 0 && $("#endDate").val().length == 0){
		 		$(adviseList.oTable1.dataTableSettings).attr("params",
		 				$.extend(adviseList.oTable1.dataTableSettings[0].params,{"createTime":$("#startDate").val(),"createTime_op":"ge"}));
		 	}
		 	else if($("#endDate").val().length > 0 && $("#startDate").val().length == 0){
		 		$(adviseList.oTable1.dataTableSettings).attr("params",
		 				$.extend(adviseList.oTable1.dataTableSettings[0].params,{"createTime":$("#endDate").val(),"createTime_op":"le"}));
		 	}
		 	else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
		 		$(adviseList.oTable1.dataTableSettings).attr("params",
		 				$.extend(adviseList.oTable1.dataTableSettings[0].params,{"createTime":$("#startDate").val()+","+$("#endDate").val(),"createTime_op":"bt"}));
		 	}
			adviseList.oTable1.fnDraw();
		}
		else if ($('#currentTabID').val()=='tabs_reply'){
			if($("#startDate").val().length > 0 && $("#endDate").val().length == 0){
		 		$(adviseList.oTable2.dataTableSettings).attr("params",
		 				$.extend(adviseList.oTable1.dataTableSettings[0].params,{"createTime":$("#startDate").val(),"createTime_op":"ge"}));
		 	}
		 	else if($("#endDate").val().length > 0 && $("#startDate").val().length == 0){
		 		$(adviseList.oTable2.dataTableSettings).attr("params",
		 				$.extend(adviseList.oTable1.dataTableSettings[0].params,{"createTime":$("#endDate").val(),"createTime_op":"le"}));
		 	}
		 	else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
		 		$(adviseList.oTable2.dataTableSettings).attr("params",
		 				$.extend(adviseList.oTable1.dataTableSettings[0].params,{"createTime":$("#startDate").val()+","+$("#endDate").val(),"createTime_op":"bt"}));
		 	}
			adviseList.oTable2.fnDraw();	
		}		
	});	

});
</script>