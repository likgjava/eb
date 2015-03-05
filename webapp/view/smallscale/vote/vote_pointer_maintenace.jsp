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
		指标信息：<span class="sysicon siAdd"><a href="javascript:void(0);" id="votePointerAdd"><strong>新增指标</strong></a></span>
	</h4>
</div>

<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="votePointerSearchForm">
		<ul>
			<li>
				<label>指标名称:</label>
			 	<input type="text" name="pointerName" id="pointerName" value=""/>
				<input type="hidden" name="pointerName_op" value="like">
			</li>				    			
			<li class="operationBtnDiv">
		        <button id = "votePointerSearchBtn" type="button"><span>查询</span></button>
		    </li>
		</ul>
	</form>
</div>
	
<div id="votePointerListDiv">
	<div id="votePointerListInfo">
		<table class="frontTableList" id="votePointerList">
			<thead>
				<tr>
					<th class="center">指标编号</th>
					<th class="center">指标名称</th>						
					<th class="center">比例系数(%)</th>												
					<th class="center">操作</th>
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
var votePointerMaintenace={};
votePointerMaintenace.oTable;

//查询
$('#votePointerSearchBtn').click(function(){
	votePointerMaintenace.reload();
});

//返回
$('#voteTemplateBtnReturn').click(function(){	
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do");
});

//跳转到新增页面
$("#votePointerAdd").click(function(){
	$.epsDialog({
		title:'新增指标',
		url:$('#initPath').val()+'/VotePointerController.do?method=toCreateOrUpdateVotePointer&voteTemplateId='+$('#voteTemplateId').val(),
		onClose:function(){votePointerMaintenace.reload();}
	});
});

//修改
votePointerMaintenace.modifyVotePointer = function(objId){
	$.epsDialog({
		title:'指标修改',
		url:$('#initPath').val()+'/VotePointerController.do?method=toCreateOrUpdateVotePointer&objId='+objId,
		onClose:function(){votePointerMaintenace.reload();}
	});
}

//删除
votePointerMaintenace.removeVotePointer = function(objId){
	if(confirm('确定删除吗？')){
		$.getJSON($('#initPath').val()+'//VotePointerController.do?method=remove',{objId:objId},function(json){
			if(json.failure) {
				alert(json.result);
				return;
			}else{
				alert("删除成功");
				votePointerMaintenace.reload();
			}
		});
	}		
}

//刷新
votePointerMaintenace.reload = function(){
	votePointerMaintenace.oTable.fnDraw();
}

//操作字符串
votePointerMaintenace.getOperationStr = function(objId){
	var rowOperation = '';
	rowOperation= '<td><a href="javascript:votePointerMaintenace.modifyVotePointer(\''+objId+'\')" ><span>修改</span></a>  <a href="javascript:votePointerMaintenace.removeVotePointer(\''+objId+'\')" ><span>删除<span></a></td>';
	return rowOperation;
}

$(document).ready(function(){	
	//填充列表
	votePointerMaintenace.oTable = $('#votePointerList').dataTable({
		'singleSelect':true,
		'checkbox':false,
		'searchZone':'votePointerSearchForm',
		'sAjaxSource':$('#initPath').val()+"/VotePointerController.do?method=list",
		 params:{'voteTemplate.objId':$('#voteTemplateId').val()},
		'queryColumns':'pointerCode,pointerName,pointerFactor',
		'alias':'',
		'hiddenColumns':'objId',
		'fnInitComplete':function(oSettings){},
		'fnDrawCallback':function(oSettings){
			votePointerMaintenace.oTable.oSettings = oSettings;
		},
		'fnRowCallback':function(nRow,aData,iDisplayIndex){
			$(nRow).append(votePointerMaintenace.getOperationStr(aData.objId));
			return nRow;
		},
		'iDisplayLength':'5'
	});
});
</script>