<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div id="voteTemplateInfo" class="formLayout form2Pa">
	<h4>主题信息：</h4>
   	<ul>
    	<li>
    		<label>评选主题：</label>
			${voteTemplate.templateName }
			<input type="hidden" name="voteTemplate.objId" id="voteTemplateId" value="${voteTemplate.objId }"/>
  	    </li>
    	<li>
    		<label>主题编号：</label>
			${voteTemplate.templateCode }
  	    </li>
    	<li>
    		<label>开始时间：</label>
			${voteTemplate.startTime }
  	    </li>
    	<li>
    		<label>结束时间：</label>
			${voteTemplate.endTime }
  	    </li>
  	    <li>
    		<label>评价专员：</label>
			${voteTemplate.appraiseCommissioner }
  	    </li>
  	    <li>
    		<label>联系手机：</label>
			${voteTemplate.commissionerPhone }
  	    </li>
  	    <li>
    		<label>电子邮箱：</label>
			${voteTemplate.commissionerEmail }
  	    </li>
  	    <li>
    		<label>联系电话：</label>
			${voteTemplate.commissionerTel }
  	    </li>  	    
  	    <li>
    		<label>评选类型：</label>
    		<c:if test="${voteTemplate.isSingleVote == true}">单一投票</c:if><c:if test="${voteTemplate.isSingleVote == false}">指标评选</c:if>			
  	    </li> 
  	    <li>
    		<label>传真：</label>
			${voteTemplate.commissionerFax }
  	    </li> 
  	    <li>
    		<label>创建时间：</label>
    		<fmt:formatDate value="${voteTemplate.createTime }" pattern="yyyy-MM-dd"/>
  	    </li>
  	    <li>
    		<label>修改时间：</label>
    		<fmt:formatDate value="${voteTemplate.updateTime }" pattern="yyyy-MM-dd"/>
  	    </li>
	</ul>
	<h4>
		评选组信息：<span class="sysicon siAdd"><a href="javascript:void(0);" id="voteUnitGroupAdd"><strong>新增评选组</strong></a></span>
	</h4>
</div>

<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="voteUnitGroupSearchForm">
		<ul>
			<li>
				<label>评选组名称:</label>
			 	<input type="text" id="groupName" name="groupName" class="sysicon">
			 	<input type="hidden" name="groupName_op" value="like">
			</li>				    			
			<li class="operationBtnDiv">
		        <button id = "voteUnitGroupSearchBtn" type="button"><span>查询</span></button>
		    </li>
		</ul>
	</form>
</div>
<div id="voteUnitGroupListDiv">
	<div id="voteUnitGroupListInfo">
		<table class="frontTableList" id="voteUnitGroupList">
			<thead>
				<tr>
					<th class="left">评选组名称</th>
					<th class="left">评选组类型</th>
					<th class="left omission">备注</th>						
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>		
</div>
<div class="conOperation">
	<button type="button" id="voteTemplateBtnReturn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
</div>

<script>
var voteUnitGroupList={};
voteUnitGroupList.oTable;

//返回
$('#voteTemplateBtnReturn').click(function(){	
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do");
});

//查询
$("#voteUnitGroupSearchBtn").click(function(){
	voteUnitGroupList.oTable.fnDraw();
});

//跳转到新增页面
$("#voteUnitGroupAdd").click(function(){
	$.epsDialog({
		title:'新增评选组',
		url:$('#initPath').val()+'/VoteTemplateController.do?method=toCreateOrUpdateVoteUnitGroup&templateId='+$('#voteTemplateId').val(),
		onClose:function(){voteUnitGroupList.reload();}
	});
});

//修改
voteUnitGroupList.modifyVotePointer = function(objId){
	$.epsDialog({
		title:'评选组修改',
		url:$('#initPath').val()+'/VoteTemplateController.do?method=toCreateOrUpdateVoteUnitGroup&objId='+objId,
		onClose:function(){voteUnitGroupList.reload();}
	});
}

//删除
voteUnitGroupList.removeVotePointer = function(objId){
	if(confirm('确定删除评选组吗？')){
		$.getJSON($('#initPath').val()+'/VoteTemplateController.do?method=removeVoteUnitGroup',{objId:objId},function(json){
			if(json.result == 'success') {
				alert("删除成功");
				voteUnitGroupList.reload();
			}else{
				alert("操作失败！请重新删除!");
				return;
			}
		});
	}		
}

//刷新
voteUnitGroupList.reload = function(){
	voteUnitGroupList.oTable.fnDraw();
}

//操作字符串
voteUnitGroupList.getOperationStr = function(objId){
	var rowOperation = '';
	rowOperation= '<a href="javascript:voteUnitGroupList.modifyVotePointer(\''+objId+'\')" ><span>修改</span></a>  <a href="javascript:voteUnitGroupList.removeVotePointer(\''+objId+'\')" ><span>删除<span></a></td>';
	return rowOperation;
}

//获取评选组列表
voteUnitGroupList.getVoteUnitGroupList = function(){
	if(null == voteUnitGroupList.oTable){
		voteUnitGroupList.oTable = $('#voteUnitGroupList').dataTable({
			'singleSelect':true,
			'checkbox':false,
			'searchZone':'voteUnitGroupSearchForm',
			'sAjaxSource':$('#initPath').val()+"/VoteTemplateController.do?method=getVoteUnitGroupList",
			 params:{'templateId':$('#voteTemplateId').val()},
			'queryColumns':'groupName,groupType,memo',
			'alias':'groupName,groupTypeCN,memo',
			'hiddenColumns':'',
			'fnInitComplete':function(oSettings){},
			'fnDrawCallback':function(oSettings){
				voteUnitGroupList.oTable.oSettings = oSettings;
			},
			'fnRowCallback':function(nRow,aData,iDisplayIndex){
				//修改
				$(nRow).append('<td><a href="javascript:voteUnitGroupList.modifyVotePointer(\''+aData.objId+'\')" ><span>修改</span></a></td>');

				//删除
				$(nRow).find('td:last').append('<a href="javascript:voteUnitGroupList.removeVotePointer(\''+aData.objId+'\')" ><span>删除<span></a>');
				return nRow;
			},
			'iDisplayLength':'10'
		});
	}else{
		$(voteUnitGroupList.oTable.dataTableSettings).attr('params', {'templateId':$('#voteTemplateId').val()});
		voteUnitGroupList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//填充列表
	voteUnitGroupList.getVoteUnitGroupList();
});
</script>