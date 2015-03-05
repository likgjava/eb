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
		合作媒体：<span class="sysicon siAdd"><a href="javascript:void(0);" id="voteTemplateMediumAdd"><strong>新增合作媒体</strong></a></span>
	</h4>
</div>
<form name="voteTemplateMediumForm" id="voteTemplateMediumForm" method="post">
	<input type="hidden" name="voteTemplate.objId" value="${voteTemplate.objId}"/>
	<input type="hidden" name="orgInfo.objId" id="templateOrgInfoId" value=""/>
</form>

<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="voteMediumSearchForm">
		<ul>
			<li>
				<label>媒体名称:</label>
			 	<input type="text" id="orgInfo.orgName" name="orgInfo.orgName" class="sysicon">
			 	<input type="hidden" name="orgInfo.orgName_op" value="like">
			</li>				    			
			<li class="operationBtnDiv">
		        <button id = "voteMediumSearchBtn" type="button"><span>查询</span></button>
		    </li>
		</ul>
	</form>
</div>
	
<div id="voteTemplateMediumListDiv">
	<div id="voteTemplateMediumListInfo">
		<table class="frontTableList" id="voteTemplateMediumList">
			<thead>
				<tr>
					<th class="left">媒体名称</th>
					<th class="left">媒体网址</th>
					<th class="left">排序号</th>						
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
var templateMediumList = {};
templateMediumList.oTable;

//获取合作媒体列表
templateMediumList.getVoteTemplateMediumList = function(){
	if(null == templateMediumList.oTable){
		templateMediumList.oTable = $('#voteTemplateMediumList').dataTable({
			'singleSelect':true,
			'checkbox':false,
			'searchZone':'voteMediumSearchForm',
			'sAjaxSource':$('#initPath').val()+"/VoteTemplateMediumController.do?method=list",
			 params:{'voteTemplate.objId':$('#voteTemplateId').val()},
			'queryColumns':'orgInfo.orgName,orgInfo.webUrl,mediumSort',
			'alias':'',
			'hiddenColumns':'voteTemplate.objId,orgInfo.webUrl',
			'fnInitComplete':function(oSettings){},
			'fnDrawCallback':function(oSettings){
				templateMediumList.oTable.oSettings = oSettings;
			},
			'iDisplayLength':'10',
			'fnRowCallback':function(nRow,aData,iDisplayIndex){
				//媒体链接
				$(nRow).find('td[name=orgInfo.webUrl]').empty().append('<a href="'+aData['orgInfo.webUrl']+'" target="_blank">'+aData['orgInfo.webUrl']+'</a>');
								
				//查看
				$(nRow).append('<td><a href="javascript:templateMediumList.viewMediumOrgInfo(\''+aData.objId+'\')" ><span>查看</span></a></td>');

				//删除
				$(nRow).find('td:last').append('<a href="javascript:templateMediumList.removeTemplateMedium(\''+aData.objId+'\')" ><span>删除<span></a>');

				//修改序号
				$(nRow).find('td[name=mediumSort]').empty().append('<form id="sortInputForm'+aData.objId+'" method="post"><input type="text" id="mediumSortInput'+aData.objId+'" class="digits" value="'+aData.mediumSort+'"/></form>');
				$(nRow).find('input[id=mediumSortInput'+aData.objId+']').change(function(){
					if(!$('#sortInputForm'+aData.objId).valid()){alert('请输入整数!');return;}
					$.getJSON($("#initPath").val()+"/VoteTemplateMediumController.do?method=setMediumSort",{"templateMediumId":aData.objId,'mediumSort':this.value},function(json){
						if(json.result=='true'){alert("操作成功!");templateMediumList.reload();}
					});
				});
				
				
				return nRow;
			}
		});
	}else{
		$(templateMediumList.oTable.dataTableSettings).attr('params', {'voteTemplate.objId':$('#voteTemplateId').val()});
		templateMediumList.oTable.fnDraw();
	}
}

//返回
$('#voteTemplateBtnReturn').click(function(){
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do");
});

//查询
$("#voteMediumSearchBtn").click(function(){
	templateMediumList.oTable.fnDraw();
});

//刷新
templateMediumList.reload = function(){
	templateMediumList.oTable.fnDraw();
}

//新增
$("#voteTemplateMediumAdd").click(function(){
	$.epsDialog({
		title:'新增合作媒体',
		width:'800',
		height:'500',
		url:$('#initPath').val()+'/view/smallscale/vote/vote_template_medium_add.jsp',
		onClose:function(){templateMediumList.reload();}
	});
});

//删除
templateMediumList.removeTemplateMedium = function(objId){
	if(confirm('确定删除合作媒体吗？')){
		$.getJSON($('#initPath').val()+'/VoteTemplateMediumController.do?method=remove',{'objId':objId},function(json){
			if(json.failure) {
				alert("操作失败！请重新删除!");
				
			}else{
				alert("删除成功");
				templateMediumList.reload();
			}
		});
	}		
}

//查看详情
templateMediumList.viewMediumOrgInfo = function(objId){
	$.epsDialog({
		title:'媒体详情查看',
		url:$('#initPath').val()+'/VoteTemplateMediumController.do?method=viewMediumOrgInfo&objId='+objId,
		onClose:function(){templateMediumList.reload();}
	});
}

//form提交
templateMediumList.submit = function(){
	$('#voteTemplateMediumForm').ajaxSubmit({
		url:$("#initPath").val()+"/VoteTemplateMediumController.do?method=saveTemplateMedium",
		dataType:'json',
		success:function(json){
			alert("操作成功！");templateMediumList.reload();
		},
		error:function(msg){
			alert("操作失败！");
		}
	});
}

$(document).ready(function(){
	templateMediumList.getVoteTemplateMediumList();
});
</script>