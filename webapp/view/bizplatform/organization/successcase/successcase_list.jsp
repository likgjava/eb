<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" >
	var successCaseList={};
	successCaseList.oTable;
	successCaseList.currentTabID = "tabs_toSubmit";
	successCaseList.getOperatorStr=function(objId){
		if ("tabs_toSubmit"==successCaseList.currentTabID) {
			return '<td align="center"><a title="修改" href="javascript:void(0);" onclick="successCaseList.toCreateOrUpdate(\''+objId+'\');return false;" id="modify"><span>修改</span></a><a title="删除"  href="javascript:void(0);" onclick="successCaseList.remove(\''+objId+'\')" id="remove" title="删除"><span>删除</span></a></td>';
		} else if ("tabs_toAudit"==successCaseList.currentTabID) {
			return '<td align="center"><a title="查看" href="javascript:void(0);" onclick="successCaseList.toView(\''+objId+'\');return false;" id="view"><span>查看</span></a></td>';
		}else if ("tabs_hadRefuse"==successCaseList.currentTabID) {
			return '<td align="center"><a title="修改" href="javascript:void(0);" onclick="successCaseList.toCreateOrUpdate(\''+objId+'\');return false;" id="modify"><span>修改</span></a></td>';
		}else if ("tabs_hadPass"==successCaseList.currentTabID) {
			return '<td align="center"><a title="查看" href="javascript:void(0);" onclick="successCaseList.toView(\''+objId+'\');return false;" id="view"><span>查看</span></a></td>';
		}
	}
	
	//新增或修改
	successCaseList.toCreateOrUpdate=function(id){
		$('#conBody').loadPage($('#initPath').val()+"/SuccessCaseController.do?method=toCreateOrUpdate&objId="+id);
	}
	//查看
	successCaseList.toView=function(id){
		$('#conBody').loadPage($('#initPath').val()+"/SuccessCaseController.do?method=toAudit&objId="+id+"&type=view");
	}  
	//删除
	successCaseList.remove=function(ids){
		if(window.confirm('确定删除吗?')){
			$.getJSON($('#initPath').val()+'/SuccessCaseController.do?method=remove',{objId:ids},function(json){
				if(json.result) {
					alert(json.result);
					$('#conBody').loadPage($('#initPath').val()+'/SuccessCaseController.do');
				}
				if(json.failure) {
					return;
				}
			});
		}
	}
	//设定返回时的路径
	successCaseList.setRetrun=function(){
		$("#returnUrl").val($('#initPath').val()+"/SuccessCaseController.do");
	}

	//获取列表
	successCaseList.getList = function(){
		if(!successCaseList.oTable){
			successCaseList.oTable=$('#successCaseList').dataTable( {  //加载列表
				'singleSelect':false,	
				'checkbox':false,		
				'queryColumns':'projectName,startTime,endTime,categoryNames',
				'hiddenColumns':'objId',
				'searchZone':'successCaseSearchForm',
				'fnInitComplete':function(oSettings) {
				},
				'fnDrawCallback':function(oSettings) {	
				},
				'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
					$(nRow).append(successCaseList.getOperatorStr(aData.objId))//添加操作按钮
					return nRow;
				},
				"sPaginationType":"full_numbers",
				"bProcessing": true,
				"bServerSide": true,
				'params':{"auditStatus":"00"},
				"sAjaxSource": $('#initPath').val()+"/SuccessCaseController.do?method=list"
			});
		}else{
			if ("tabs_toSubmit"==successCaseList.currentTabID) {
				$(successCaseList.oTable.dataTableSettings).attr("params", {"auditStatus":"00"});
			} else if ("tabs_toAudit"==successCaseList.currentTabID) {
				$(successCaseList.oTable.dataTableSettings).attr("params", {"auditStatus":"01"});
			} else if ("tabs_hadRefuse"==successCaseList.currentTabID) {
				$(successCaseList.oTable.dataTableSettings).attr("params", {"auditStatus":"03"});
			} else if ("tabs_hadPass"==successCaseList.currentTabID) {
				$(successCaseList.oTable.dataTableSettings).attr("params", {"auditStatus":"02"});
			}
			successCaseList.oTable.fnDraw();
		}
	}
	
$(document).ready(function(){
	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			successCaseList.currentTabID = ui.tab.id;
			$("#currentTab").val(ui.index); //当前tab的index
			successCaseList.getList();
		}
	});
	
	successCaseList.setRetrun();

	//加载第一次
	successCaseList.getList();

	//指定某一个tab被选中，默认值为0
	$tabs.tabs('select', parseInt($("#currentTab").val()));

	//新增
	$("#addCasBtn").click(function(){
		successCaseList.toCreateOrUpdate("");
	});
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

<!-- 操作 -->
<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>新增成功案例请点击 
			<a href="javascript:void(0);" class="sysicon siAdd" id="addCasBtn"><span><strong>新增成功案例</strong></span></a>
		</li>
	</ul>
</div>

<div id="epsTabs">
  <ul>
    <li>
      <a href="#successCaseInfo" id = "tabs_toSubmit" class="refreshData"><span>待提交</span></a>
    </li>
    <li>
      <a href="#successCaseInfo" id = "tabs_toAudit" class="refreshData"><span>待审核</span></a>
    </li>
    <li>
      <a href="#successCaseInfo" id = "tabs_hadRefuse" class="refreshData"><span>被退回</span></a>
    </li>
    <li>
      <a href="#successCaseInfo" id = "tabs_hadPass" class="refreshData"><span>已通过</span></a>
    </li>
  </ul>
	<div id="successCaseInfo">
		<table class="frontTableList" id="successCaseList">
		      <thead>
		        <tr>
		          <th class="left"><spring:message code="successCaseForm.projectName"/></th>
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
