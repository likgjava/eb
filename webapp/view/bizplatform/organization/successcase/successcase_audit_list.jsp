<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" >
var successCaseList={};
successCaseList.currentTabID = "auditStatus_01";
successCaseList.oTable;

// 生成操作列
successCaseList.getOperatorStr=function(objId){
	if ("auditStatus_01"==successCaseList.currentTabID) {
		return '<td class="center operation" align="center"><a href="javascript:void(0);" onclick="successCaseList.toAudit(\''+objId+'\');return false;" id="audit" type="alink" title="审核"><span>审核</span></a></td>';
	} else if ("auditStatus_02"==successCaseList.currentTabID) {
		return '<td class="center operation" align="center"><a href="javascript:void(0);" onclick="successCaseList.toView(\''+objId+'\');return false;" id="view" type="alink" title="查看"><span>查看</span></a></td>';
	}
}
//审核
successCaseList.toAudit=function(id){
	$('#conBody').loadPage($('#initPath').val()+"/SuccessCaseController.do?method=toAudit&objId="+id);
}  
//查看
successCaseList.toView=function(id){
	$('#conBody').loadPage($('#initPath').val()+"/SuccessCaseController.do?method=toAudit&objId="+id+"&type=view");
}  
//设定返回时的路径
successCaseList.setRetrun=function(){
	$("#returnUrl").val($('#initPath').val()+"/view/bizplatform/organization/successcase/successcase_audit_list.jsp");
}

//获取列表数据，如果列表没有，则创建，否则刷新数据
successCaseList.getTableList = function(){
	if(!successCaseList.oTable){
		successCaseList.oTable=$('#successCaseList').dataTable( {
			'singleSelect':false,	
			'checkbox':false,		
			'queryColumns':'projectName,orgInfo.orgName,startTime,endTime,categoryNames',
			'hiddenColumns':'objId',
			'searchZone':'successCaseSearchForm',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				$(nRow).append(successCaseList.getOperatorStr(aData.objId))// 添加操作按钮
				return nRow;
			},
			"sPaginationType":"full_numbers",
			"bProcessing": true,
			"bServerSide": true,
			'params':{"auditStatus":successCaseList.currentTabID.replace("auditStatus_","")},
			"sAjaxSource": $('#initPath').val()+"/SuccessCaseController.do?method=list"
		});
	}else{
		$(successCaseList.oTable.dataTableSettings).attr("params",{
			'auditStatus':successCaseList.currentTabID.replace("auditStatus_","")
		});
		successCaseList.oTable.fnDraw();
	}
}
$(document).ready(function(){
	//加载tabs
	var $tabs = $('#epsTabs').tabs({
			select: function(event, ui) {
				successCaseList.currentTabID = ui.tab.id;
				$("#currentTab").val(ui.index); //当前tab的index
				successCaseList.getTableList();
			}
		}); 
	//指定某一个tab被选中，默认值为0
	$tabs.tabs('select', parseInt($("#currentTab").val()));
	//加载第一个tab数据
	if($("#currentTab").val() == "0"){
		successCaseList.getTableList();
	}
	//设置返回
	successCaseList.setRetrun();
	
	//查询
	$("#successCaseSearchBtn").click(function(){
		successCaseList.oTable.fnDraw();
	})
});

</script>

<!-- 查询条件 -->
<div class="conSearch">
	<form id="successCaseSearchForm">
    <h4><span>搜索</span></h4>
    <ul>
      <li>
        <label  for="input01">所属机构：</label>
        <input type="text" id="orgInfo.orgName" name="orgInfo.orgName" value=""/>
        <input type="hidden" id="orgInfo.orgName_op" name="orgInfo.orgName_op" value="like"/>
      </li>
      <li>
        <label  for="input01">项目名称：</label>
        <input type="text" id="projectName" name="projectName" value=""/>
        <input type="hidden" id="projectName_op" name="projectName_op" value="like"/>
      </li>
      <li class="operationBtnDiv">
        	<button id="successCaseSearchBtn" type="button"><span>查询</span></button>
      </li>
    </ul>
    </form>
</div>

<!-- Tab页 -->
<div id="epsTabs">
  <ul>
    <li>
      <a href="#successCaseInfo" id = "auditStatus_01" class="refreshData"><span>待审核</span></a>
    </li>
    <li>
      <a href="#successCaseInfo" id = "auditStatus_02" class="refreshData"><span>已通过</span></a>
    </li>
  </ul>
	<div id="successCaseInfo">
		<table class="frontTableList" id="successCaseList">
	      <thead>
	        <tr>
	          <th class="left"><spring:message code="successCaseForm.projectName"/></th>
	          <th class="center">机构名称</th>
	          <th class="center"><spring:message code="successCaseForm.startTime"/></th>
	          <th class="center"><spring:message code="successCaseForm.endTime"/></th>
	          <th class="left">采购内容</th>
	          <th class="center operation">操作</th>
	        </tr>
	      </thead>
	      <tbody>
	      </tbody>
		</table>
	</div>
</div>
