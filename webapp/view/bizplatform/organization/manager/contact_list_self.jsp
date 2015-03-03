<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>



<input type="hidden" name="currentEmployeeId" id="currentEmployeeId" value="${currentEmployeeId}" />
<input type="hidden" name="currentCompanyId" id="currentCompanyId" value="${currentCompanyId}" />

<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="EmployeeListForm">
	<ul>
		<li><label>员工姓名 ：</label><input type="text" name="name" id="name" value=""><input type="hidden" name="name_op" value="like"></li>
		<li class="operationBtnDiv">
		<button type="button" id="query" onclick="EmployeeList.oTable.fnDraw();"><span><spring:message code="globe.query" /></span></button>
		</li>
	</ul>
	</form>
</div>

<div class="formLayout form2Pa">
	<div class="operationBtnDiv r" style="float:right;">
		<button id="addContactBtn"><span>新增联系人</span></button>
		<button id="removeContactBtn"><span>删除联系人</span></button>
	</div>
	<table class="frontTableList" id="EmployeeList">
		<thead>
			<tr>
				<th class="left">联系人姓名</th>
				<th class="center">手机</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>

<script>
var EmployeeList = {};

EmployeeList.currentCompanyId;
//新增或修改
EmployeeList.createOrUpdate=function(id){
	$.epsDialog({
		title:"修改",
		url:$('#initPath').val()+"/OrgInfoController.do?method=toContractorForm&employeeId="+id+"&companyId="+EmployeeList.currentCompanyId
	})
}
//删除
EmployeeList.remove=function(ids){
	var objIdArr = [];
    for(var i=0;i<ids.split(",").length;i++){
        objIdArr[i] = ids.split(",")[i];
    }
	if(window.confirm('确定删除吗?')){
		$.getJSON($('#initPath').val()+'/EmployeeController.do?method=remove',{"objId":objIdArr},function(json){
			if(json.result) {
				alert(json.result);
				EmployeeList.oTable.fnDraw();
			}
			if(json.failure) {
				return;
			}
		});
	}
}
//查看
EmployeeList.view=function(id){
	$.epsDialog({
		title:"查看",
		url:$('#initPath').val()+"/OrgInfoController.do?method=toContractorForm&employeeId="+id+"&type=view"
	})
}

//获得操作按钮
EmployeeList.getOperatorStr=function(objId,isSelf){
	var operatorHtml = "";
	operatorHtml += '<td class="operation">';
	operatorHtml += '<a href="javascript:void(0);" onclick="EmployeeList.createOrUpdate(\''+objId+'\');return false;" id="modify" title="修改">修改</a>';
	operatorHtml += '<a title="查看" href="javascript:void(0);" onclick="EmployeeList.view(\''+objId+'\');return false;">查看</a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//获取联系人列表
EmployeeList.getEmployeeList = function( companyId ){
	if(!EmployeeList.oTable){
		EmployeeList.oTable = $('#EmployeeList').dataTable({
			'params':{"company.objId":companyId},
			'singleSelect' : false,
			'checkbox' : true,
			'queryColumns' : 'name,mobile',
			'fnInitComplete' : function(oSettings) {
			},
			'fnDrawCallback' : function(oSettings) {
				EmployeeList.oTable.oSettings = oSettings;
			},
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				if(aData.sex){
					if(aData.sex=="true" || aData.sex == true){
						$(nRow).find('td[name=sex]').html('男');
					}else if(aData.sex=="false" || aData.sex == false){
						$(nRow).find('td[name=sex]').html('女');
					}
				}
				if($("#currentEmployeeId").val() != null && $("#currentEmployeeId").val() != ""){
					if($("#currentEmployeeId").val() == aData.objId){//自己不能删除自己的联系人信息
						$(nRow).find('input:checkbox').each(function(i,n){
							$(n).attr("disabled","disabled");
						})
						$(nRow).append(EmployeeList.getOperatorStr(aData.objId));
					}else{
						$(nRow).append(EmployeeList.getOperatorStr(aData.objId));
					}
				}
				return nRow;
			},
			"sPaginationType":"full_numbers",
			"bProcessing": true,
			"bServerSide": true,
			"sAjaxSource" : $('#initPath').val()+ "/EmployeeController.do?method=list&order=createTime",
			'searchZone':'EmployeeListForm'
		});
	} else {
		$(EmployeeList.oTable.dataTableSettings).attr("params",{"company.objId":companyId});
		EmployeeList.oTable.fnDraw();
	}
	//当前公司id
	EmployeeList.currentCompanyId = companyId;
}

$(document).ready(function() {

	//获得列表
	EmployeeList.getEmployeeList($("#currentCompanyId").val());
	
	//点击新增
	$("#addContactBtn").click(function(){
		EmployeeList.createOrUpdate("");
	});
	//点击删除
	$("#removeContactBtn").click(function(){
		var selects = EmployeeList.oTable.dtSelects();
		if(selects.length == 0){
			alert("请至少选中一个联系人");return;
		}
		EmployeeList.remove(EmployeeList.oTable.dtSelects());
	});
});
</script>