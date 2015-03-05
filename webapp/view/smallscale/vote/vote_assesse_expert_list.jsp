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
		评审专家信息：<span class="sysicon siAdd"><a href="javascript:void(0);" id="voteAssesseExpertAdd"><strong>新增评审专家</strong></a></span>
	</h4>
</div>

<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="voteExpertSearchForm">
		<ul>
			<li>
				<label>专家名称:</label>
			 	<input type="text" id="expertName" name="expertName" class="sysicon">
			 	<input type="hidden" name="expertName_op" value="like">
			</li>
			<li>
				<label>所属评选组：</label>
				<select name="voteUnitGroupId" id="voteUnitGroupId">
					<option value=""></option>
					<c:forEach items="${voteUnitGroupList}" var="voteUnitGroup">
						<option value="${voteUnitGroup.objId}">${voteUnitGroup.groupName}</option>
					</c:forEach>
				</select>
			</li>				    			
			<li class="operationBtnDiv">
		        <button id = "voteExpertSearchBtn" type="button"><span>查询</span></button>
		    </li>
		</ul>
	</form>
</div>
	
<div id="voteAssesseExpertListDiv">
	<div id="voteAssesseExpertListInfo">
		<table class="frontTableList" id="voteAssesseExpertList">
			<thead>
				<tr>
					<th class="left">专家图像</th>
					<th class="left">专家名称</th>
					<th class="left">所属评选组</th>
					<th class="left">比例系数(%)</th>
					<th class="left">排序号</th>				
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<div id="imgViewer" class="izViewer"></div>		
</div>
		
<div class="conOperation">
	<button type="button" id="voteTemplateBtnReturn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
</div>
<script>
var voteAssesseExpertList={};
voteAssesseExpertList.oTable;

//返回
$('#voteTemplateBtnReturn').click(function(){	
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do");
});

//查询
$("#voteExpertSearchBtn").click(function(){
	voteAssesseExpertList.oTable.fnDraw();
});

//跳转到新增页面
$("#voteAssesseExpertAdd").click(function(){
	$('#conBody').loadPage($('#initPath').val()+'/VoteTemplateController.do?method=toAddOrUpdateVoteAssesseExpert&templateId='+$('#voteTemplateId').val());
});

//修改
voteAssesseExpertList.modifyVoteAssesseExpert = function(objId){
	$('#conBody').loadPage($('#initPath').val()+'/VoteTemplateController.do?method=toAddOrUpdateVoteAssesseExpert&objId='+objId);
}

//删除
voteAssesseExpertList.removeVoteAssesseExpert = function(objId){
	if(confirm('确定删除评审专家吗？')){
		$.getJSON($('#initPath').val()+'//VoteTemplateController.do?method=removeVoteAssesseExpert',{objId:objId},function(json){
			if(json.result == 'success') {
				alert("删除成功");
				voteAssesseExpertList.reload();
			}else{
				alert("操作失败！请重新删除!");
				return;
			}
		});
	}		
}
//图片放大镜
voteAssesseExpertList.onMouseOver = function(picture){
	$('#imgViewer').empty();
	new ImageZoom( picture, "imgViewer", {
		model:'simple',scale: 10, delay: 10,rangeWidth:30,rangeHeight:30
	});
}

//刷新
voteAssesseExpertList.reload = function(){
	voteAssesseExpertList.oTable.fnDraw();
}

//获取评审专家列表
voteAssesseExpertList.getvoteAssesseExpertList = function(){
	if(null == voteAssesseExpertList.oTable){
		voteAssesseExpertList.oTable = $('#voteAssesseExpertList').dataTable({
			'singleSelect':true,
			'checkbox':false,
			'searchZone':'voteExpertSearchForm',
			'sAjaxSource':$('#initPath').val()+"/VoteTemplateController.do?method=getVoteAssesseExpertList",
			 params:{'templateId':$('#voteTemplateId').val()},
			'queryColumns':'expertPic,expertName,voteUnitGroupName,proportionFactor,expertSort',
			'alias':'',
			'hiddenColumns':'',
			'iDisplayLength':'5',
			'fnInitComplete':function(oSettings){},
			'fnDrawCallback':function(oSettings){
				voteAssesseExpertList.oTable.oSettings = oSettings;
			},
			'fnRowCallback':function(nRow,aData,iDisplayIndex){
				$(nRow).find("td[name=expertPic]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["expertPic"]+'" style="cursor:pointer" width="40px" height="40px" id="'+aData["expertPic"]+'" onMouseOver="voteAssesseExpertList.onMouseOver(\''+aData["expertPic"]+'\');" />');

				//修改序号
				$(nRow).find('td[name=expertSort]').empty().append('<form id="sortInputForm'+aData.objId+'" method="post"><input type="text" id="expertSortInput'+aData.objId+'" class="digits" value="'+aData.expertSort+'"/></form>');
				$(nRow).find('input[id=expertSortInput'+aData.objId+']').change(function(){
					if(!$('#sortInputForm'+aData.objId).valid()){alert('请输入整数!');return;}
					$.getJSON($("#initPath").val()+"/VoteAssessedThingController.do?method=setNumSort",{"stringId":aData.objId,'numSort':this.value,'operClass':'VoteAssesseExpert'},function(json){
						if(json.result=='true'){alert("操作成功!");voteAssesseExpertList.reload();}
					});
				});

				//修改
				$(nRow).append('<td><a href="javascript:voteAssesseExpertList.modifyVoteAssesseExpert(\''+aData.objId+'\')" ><span>修改</span></a></td>');

				//删除
				$(nRow).find('td:last').append('<a href="javascript:voteAssesseExpertList.removeVoteAssesseExpert(\''+aData.objId+'\')" ><span>删除<span></a>');
				
				return nRow;
			}
		});
	}else{
		$(voteAssesseExpertList.oTable.dataTableSettings).attr('params', {'templateId':$('#voteTemplateId').val()});
		voteAssesseExpertList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//填充列表
	voteAssesseExpertList.getvoteAssesseExpertList();
});
</script>