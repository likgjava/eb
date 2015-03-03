
var BargainProjectQueryList={};
BargainProjectQueryList.oTable;	
BargainProjectQueryList.tree;
BargainProjectQueryList.openFlag = "close";

//获取操作字符串
BargainProjectQueryList.getOperatorStr=function(objId,ebuyMethod,useStatus){
	var operatorStr = '<td class="operation">';
	//进入项目
	operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectQueryList.openOperatorPage(\'viewProject\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>进入项目</span></a>';
	
	//未提交
	if(useStatus == '00'){
		operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectQueryList.openOperatorPage(\'remove\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>删除</span></a>';
	}
	//已提交
	else{
		operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectQueryList.openOperatorPage(\'invalid\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>作废</span></a>';
	}
	operatorStr += '</td>';
	return operatorStr;
}

//根据不同的操作，导向不同的页面
BargainProjectQueryList.openOperatorPage=function(type,ebuyMethod,objId){
	//查看项目
	if("viewProject"==type){
		//议价项目
		if(ebuyMethod == '05'){
			window.open($("#initPath").val()+"/TalkProjectController.do?method=toTalkProjectDetailView&userType=buyer&objId="+objId );
		}
		//竞价项目
		else{
			window.open( $("#initPath").val()+"/BargainProjectController.do?method=toProjectView&userType=buyer&projectId="+objId );
		}
	}
	//删除项目
	else if("remove"== type){
		BargainProjectQueryList.remove(objId,'remove');
	}
	//作废项目
	else if("invalid"== type){
		BargainProjectQueryList.remove(objId,'invalid');
	}
}

//删除或作废项目
BargainProjectQueryList.remove=function(objId,removeType){
	var url = $('#initPath').val() + "/BargainProjectController.do?method=removeRBProject&type="+removeType;
	var msg = "确定删除该项目吗";
	if(removeType=="invalid") { msg = "确定作废该项目吗"; }
	
	if(window.confirm(msg)){
		$.getJSON(url,{"objId":objId},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			BargainProjectQueryList.oTable.fnDraw();
		});
	}
}

//初始化组织机构树
BargainProjectQueryList.initOrgnizationTree = function(companyId){
	if($("#subOrgCount").val()>0){
		BargainProjectQueryList.tree = new dhtmlXTreeObject("orgnizationTreeGrid","100%","100%",0);
		BargainProjectQueryList.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
		BargainProjectQueryList.tree.setOnClickHandler(BargainProjectQueryList.nodeClick);
		BargainProjectQueryList.tree.setXMLAutoLoading("OrganizationController.do?method=getTree&order=sort&action=listById&isOpen=0");
		BargainProjectQueryList.tree.loadXML($("#initPath").val()+"/OrganizationController.do?method=getTree&id=1&action=listById&order=sort&objId="+companyId,function(){
			BargainProjectQueryList.nodeClick(companyId);
			BargainProjectQueryList.tree.selectItem(companyId);
		});
	}else{
		BargainProjectQueryList.getTableList($("#orgInfoId").val());
	}
}

//点击节点事件
BargainProjectQueryList.nodeClick = function(id){
	var nodeLevel = BargainProjectQueryList.tree.getLevel(id); //获取节点层级
	//显示上级公司的项目
	if(nodeLevel == 1){
		BargainProjectQueryList.getTableList($('#orgInfoId').val());
		$("#createProjectSpan").show()
	}
	//显示下级公司的项目
	else{
		//通过companyId获取orgInfoId
		$.getJSON($("#initPath").val()+"/OrgInfoController.do?method=getObjectQuery", {"queryColumns":"objId", "company.objId":id}, function(json){
			if(json.success){
				BargainProjectQueryList.getTableList(json.result[0].objId);
				$("#createProjectSpan").hide();
			}
		});
	}
}

//创建或刷新列表数据
BargainProjectQueryList.getTableList = function(orgId) {
	var params = {};
	if(orgId)params.orgId = orgId;
	if(!BargainProjectQueryList.oTable) {
		//项目列表
		BargainProjectQueryList.oTable=$('#BargainProjectQueryList').dataTable( {
			
			'_iDisplayLength':'10',
			'singleSelect':true,
			'checkbox':false,
			'queryColumns':'projCode,projName,ebuyMethodCN,createTime,supplierNumber,dealTotal,saveTotal,bargainStatus',
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
						addHtml += ('<tr addColumn="add_'+ $(obj).parent().attr("objid") +'" '+isOpen+'><td></td><td>项目编号：</td><td colspan="7">' + jsonRec.projCode + '</td></tr>') 
					
						//预算总金额
						addHtml += ('<tr addColumn="add_'+ $(obj).parent().attr("objid") +'" '+isOpen+'><td></td><td>预算总金额：</td><td colspan="7"><span style="color:red;">' + formatAmount(jsonRec.budgetTotalMoney,2) + '（元）</span></td></tr>') 
						
						var recSupplier = '';
						$.each( jsonRec.signUprecordList ,function(i,o){
							recSupplier += ( (recSupplier!=''?',':'')+'<a href="javascript:void(0);" onclick="common.goToOrgShop(\''+o[1]+'\');">' + o[2] + '</a>' )
						});
						
						//报名供应商
						addHtml += ('<tr addColumn="add_'+ $(obj).parent().attr("objid") +'" '+isOpen+'><td></td><td>报名供应商：</td><td colspan="7">' + ( recSupplier!=''?recSupplier:'无' ) + '</td></tr>') 
	
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
					if($(this).hasClass("openBtn")){
						$("tr[addColumn=add_"+aData.objId+"]").show();
					}else{
						$("tr[addColumn=add_"+aData.objId+"]").hide();
					}
				})
				
				//格式化
				
				if(Number(aData.dealTotal)>0){
					$(nRow).find("td[name=dealTotal]").html(formatAmount(aData.dealTotal,2) );
				}
				if(Number(aData.saveTotal)>0){
					$(nRow).find("td[name=saveTotal]").html(formatAmount(aData.saveTotal,2) );
				}else if(Number(aData.saveTotal)<0){
					$(nRow).find("td[name=saveTotal]").html('--');
				}
				
				//加操作行
				$(nRow).append( BargainProjectQueryList.getOperatorStr(aData.objId,aData.ebuyMethod ,aData.useStatus ) )
				
				//数据备用
				$(nRow).find("td:last").append('<span class="hidden" id="jsonRecString" >'+ JSON.stringify( aData ) +'</span>');

				return nRow;
			},
			'searchZone':'projectSearchForm',
			"sAjaxSource": $('#initPath').val()+'/ProjectQueryController.do?method=getProjectList&rp=10'
			,"params":params
		});
	}else {
		//搜索参数
		searchParam = BargainProjectQueryList.searchParam();
		if(orgId)searchParam.orgId = orgId;
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
	$("#returnUrl").val($("#initPath").val()+"/BargainProjectController.do?method=orgProjectQueryListView")
	
	BargainProjectQueryList.initOrgnizationTree($("#companyId").val());
	
	//创建竞价项目
	$("#createBargainProjectBtn").click(function(){
		window.open($('#initPath').val()+"/BargainProjectController.do?method=toCreateBidProject_1");
	});
});