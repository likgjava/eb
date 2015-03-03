
var BargainProjectQueryList={};
BargainProjectQueryList.oTable;	

BargainProjectQueryList.openFlag = "close";

//创建或刷新列表数据
BargainProjectQueryList.getTableList = function() {
	if(!BargainProjectQueryList.oTable) {
		//项目列表
		BargainProjectQueryList.oTable=$('#BargainProjectQueryList').dataTable( {
			'_iDisplayLength':'10',
			'singleSelect':true,
			'checkbox':false,
			'queryColumns':'projCode,projName,buyersName,ebuyMethodCN,createTime,dealTotal,bargainStatus',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
				BargainProjectQueryList.oTable.oSettings=oSettings;
				
				//每行附加信息
				$.each( $("#BargainProjectQueryList").find("tbody tr").find("td:last") , function( index , obj ){
					var addHtml = '';
					
					var jsonRecString =  $(obj).parent().find("span[id=jsonRecString]").html();
					var jsonRec = eval('('+ jsonRecString +')');
					
					//如果有数据
					if(jsonRec){
						
						var isOpen = BargainProjectQueryList.openFlag=='close'?'class="hidden"':'';
						
						//只打开第一个
						if(index == 0){
							isOpen = 'open';
						}
						
						//项目编号
						addHtml += ('<tr addColumn="add_'+ $(obj).parent().attr("objid") +'" style="font-style:italic;" '+isOpen+'><td></td><td>项目编号：</td><td colspan="6">' + jsonRec.projCode + '</td></tr>') 
					
						//预算总金额
						addHtml += ('<tr addColumn="add_'+ $(obj).parent().attr("objid") +'" style="font-style:italic;" '+isOpen+'><td></td><td>预算总金额：</td><td colspan="6"><span style="color:red;">' + formatAmount(jsonRec.budgetTotalMoney,2) + '（元）</span></td></tr>') 
						
						//按需求最低价
						if(jsonRec.requirementMinRec){
							$.each(jsonRec.requirementMinRec , function( i , o){
								addHtml += ('<tr addColumn="add_'+ $(obj).parent().attr("objid") +'" style="font-style:italic;" ' + isOpen + '><td></td><td>'+ ( i == 0 ? '按需求最低报价：':'' ) +'</td><td colspan="2">【需求】' + o[1] + '</td><td colspan="4">【最低价】' + formatAmount(o[2],2) + '（元）</td></tr>') 
							})
						}
	
						$(obj).parent().after(addHtml);
						
						$(obj).parent().css("font-weight","bold").css("color","#494949");
						
						//虚行点击事件
						$("tr[addColumn=add_"+ $(obj).parent().attr("objid") +"]").click(function(){
							if( $(obj).parent().hasClass("row_selected") ){
								$("tr[objid="+ $(obj).parent().attr("objid")+"]").removeClass("row_selected");
							}else{
								$("tr[objid="+ $(obj).parent().attr("objid")+"]").addClass("row_selected");
							}
							$("tr[addColumn=add_"+ $(obj).parent().attr("objid") +"]").attr("class", $(obj).parent().attr("class") );
						});
					
					}
				});
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				
				//行点击事件
				$(nRow).click(function(){
					$("tr[addColumn=add_"+aData.objId+"]").attr("class", $(nRow).attr("class") );
				})
				
				$(nRow).find("td:eq(0)").css("min-width","0px").html('<span title="展开/关闭" class="'+ ( iDisplayIndex==0?"open":BargainProjectQueryList.openFlag ) +'Btn" id="openOrclose">&nbsp;</span>').find("span[id=openOrclose]").click(function(){
					$(this).hasClass("openBtn")?$(this).addClass("closeBtn").removeClass("openBtn"):$(this).addClass("openBtn").removeClass("closeBtn");
					$("tr[addColumn=add_"+aData.objId+"]").toggle();
				})
				
				//加操作行
				$(nRow).append('<td class="operator"></td>');
				
				//数据备用
				$(nRow).find("td:last").append('<span class="hidden" id="jsonRecString" >'+ JSON.stringify( aData ) +'</span>');
				
				//中标金额处理（供应商中标可以查看）
				$(nRow).find("td[name=dealTotal]").html( aData.isDeal=='true'?(Number(aData.dealTotal)>0?formatAmount(aData.dealTotal,2):aData.dealTotal): aData.isDealCN );
				
				//添加按钮
				$(nRow).find("td:last").append('<a name="viewProject" href="javascript:void(0);">查看</a>').find("a[name=viewProject]").click(function(){
					window.open( $("#initPath").val()+"/BargainProjectController.do?method=toProjectView&userType=buyer&projectId="+aData.objId  );
				})
				return nRow;
			},
			'searchZone':'projectSearchForm',
			"sAjaxSource": $('#initPath').val()+'/ProjectQueryController.do?method=getMyProjectList&rp=10'
		});
	}else {
		
		//搜索参数
		searchParam = BargainProjectQueryList.searchParam();
		
		$(BargainProjectQueryList.oTable.dataTableSettings).attr("params", searchParam );
		
		BargainProjectQueryList.oTable.fnDraw();
	}
}

//搜索参数
BargainProjectQueryList.searchParam = function(){
	return formToJsonObject("projectSearchForm")  ;
}


//打开或关闭
BargainProjectQueryList.closeOrOpenAll = function(e){
	if( $(e).hasClass("openBtn") ){
		$("tbody").find("span[id=openOrclose]").removeClass("openBtn").addClass("closeBtn");
		$("tr[addColumn*=add_]").hide();
		$(e).removeClass("openBtn").addClass("closeBtn");
		BargainProjectQueryList.openFlag = "close"
	}else{
		$("tbody").find("span[id=openOrclose]").removeClass("closeBtn").addClass("openBtn");
		$("tr[addColumn*=add_]").show();
		$(e).removeClass("closeBtn").addClass("openBtn");
		BargainProjectQueryList.openFlag = "open"
	}
}

$(document).ready(function(){
	
	//开始时间
    $("#startTime").epsDatepicker({applyRule: endRule });  //增加结束时间的规则
    //结束时间
    $("#endTime").epsDatepicker({applyRule: startRule });  //增加开始时间的规则
	
	//设定返回路径
	$("#returnUrl").val("view/agreement/bargin/project/bargain/bargain_project_query_list.jsp")
	
	BargainProjectQueryList.getTableList();

});
	
