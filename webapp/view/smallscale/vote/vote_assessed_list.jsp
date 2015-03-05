<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

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
	<h4>投票记录管理</h4>
</div>

<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="voteAssessedSearchForm">
		<ul>
			<li>
				<label>评选单位:</label>
			 	<input type="text" id="voteAssessedThing.attenderName" name="voteAssessedThing.attenderName" class="sysicon">
			 	<input type="hidden" name="voteAssessedThing.attenderName_op" value="like">
			</li>				    			
			<li class="operationBtnDiv">
		        <button id = "voteAssessedSearchBtn" type="button"><span>查询</span></button>
		    </li>
		</ul>
	</form>
</div>		
<div id="voteAssessedListInfo">
	<table class="frontTableList" id="voteAssessedList">
		<thead>
			<tr>
				<th class="left">评选单位</th>
				<th class="left">投票人</th>	
				<th class="date">投票时间</th>							
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
<div class="conOperation">
	<button type="button" id="voteTemplateBtnReturn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
</div>

<script type="text/javascript">
var voteAssessedList={};
voteAssessedList.oTable;

//返回
$('#voteTemplateBtnReturn').click(function(){	
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do");
});

//刷新
voteAssessedList.reload = function(){
	voteAssessedList.oTable.fnDraw();
}

//查询
$("#voteAssessedSearchBtn").click(function(){
	voteAssessedList.oTable.fnDraw();
});

//查看详情
voteAssessedList.viewVoteAssessed = function(objId){
	$.epsDialog({
		title:'投票评选查看',
		url:$('#initPath').val()+'/VoteAssessedController.do?method=viewVoteAssessed&objId='+objId,
		onClose:function(){voteAssessedList.reload();}
	});
}

//删除
voteAssessedList.removeVoteAssessed = function(objId){
	if(confirm('确定删除?若确定将同时删除此投票人所做的评分')){
		$.getJSON($('#initPath').val()+'/VoteAssessedController.do?method=removeVoteAssessed',{objId:objId},function(json){
			if(json.failure) {
				alert(json.result);
				return;
			}else{
				alert("删除成功");
				voteAssessedList.reload();
			}
		});
	}		
}

//添加操作字符串
voteAssessedList.getOperationStr = function(objId){
	var rowOperation = '';
	rowOperation= '<td><a href="javascript:voteAssessedList.viewVoteAssessed(\''+objId+'\')" ><span>查看<span></a> <a href="javascript:voteAssessedList.removeVoteAssessed(\''+objId+'\')" ><span>删除<span></a>';
	return rowOperation;
}

//获取投票记录列表
voteAssessedList.getVoteAssessedList = function(){
	if(null == voteAssessedList.oTable){
		voteAssessedList.oTable = $('#voteAssessedList').dataTable({
			'singleSelect':true,
			'checkbox':false,
			'searchZone':'voteAssessedSearchForm',
			'sAjaxSource':$('#initPath').val()+"/VoteAssessedController.do?method=list",
			 params:{'voteTemplate.objId':$('#voteTemplateId').val()},
			'queryColumns':'voteAssessedThing.attenderName,userName,createTime',
			'alias':'',
			'hiddenColumns':'objId',
			'fnInitComplete':function(oSettings){},
			'fnDrawCallback':function(oSettings){
				voteAssessedList.oTable.oSettings = oSettings;
			},
			'iDisplayLength':'10',
			'fnRowCallback':function(nRow,aData,iDisplayIndex){
				//查看
				$(nRow).append('<td><a href="javascript:voteAssessedList.viewVoteAssessed(\''+aData.objId+'\')"><span>查看</span></a></td>');

				//删除
				$(nRow).find('td:last').append('<a href="javascript:voteAssessedList.removeVoteAssessed(\''+aData.objId+'\')"><span>删除</span></a>');
				
				return nRow;
			}
		});
	}else{
		$(voteAssessedList.oTable.dataTableSettings).attr('params', {'voteTemplate.objId':$('#voteTemplateId').val()});
		voteAssessedList.oTable.fnDraw();
	}
}


$(document).ready(function(){	
	voteAssessedList.getVoteAssessedList();
});
</script>

