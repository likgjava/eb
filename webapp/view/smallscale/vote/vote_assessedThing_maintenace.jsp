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
		参选单位信息：<span class="sysicon siAdd"><a href="javascript:void(0);" id="voteAssessedThingAdd"><strong>新增参选单位</strong></a></span>
	</h4>
</div>

<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="voteAssessedThingSearchForm">
		<ul>
			<li>
				<label>参选单位名称:</label>
			 	<input type="text" name="attenderName" id="attenderName" value=""/>
				<input type="hidden" name="attenderName_op" value="like">
			</li>
			<li>
				<label>所属评选组：</label>
				<select name="voteUnitGroup.objId" id="voteUnitGroupId">
					<option value=""></option>
					<c:forEach items="${voteUnitGroupList}" var="voteUnitGroup">
						<option value="${voteUnitGroup.objId}">${voteUnitGroup.groupName}</option>
					</c:forEach>
				</select>
			</li>			    			
			<li class="operationBtnDiv">
		        <button id = "voteAssessedThingSearchBtn" type="button"><span>查询</span></button>
		    </li>
		</ul>
	</form>
</div>
	
<div id="voteAssessedThingListDiv">
	<div id="voteAssessedThingListInfo">
		<table class="frontTableList" id="voteAssessedThingList">
			<thead>
				<tr>
					<th class="left">参选单位简图</th>
					<th class="left">参选单位名称</th>
					<th class="left">所属评选组</th>							
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
var voteAssessedThingMaintenace={};
voteAssessedThingMaintenace.oTable;

//查询
$('#voteAssessedThingSearchBtn').click(function(){
	voteAssessedThingMaintenace.reload();
});

//返回
$('#voteTemplateBtnReturn').click(function(){
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do");
});

//新增
$("#voteAssessedThingAdd").click(function(){
	$('#conBody').loadPage($('#initPath').val()+"//VoteAssessedThingController.do?method=toCreateOrUpdateVoteAssessedThing&voteTemplateId="+$('#voteTemplateId').val());
});

//修改
voteAssessedThingMaintenace.modifyVoteAssessedThing = function(objId){
	$('#conBody').loadPage($('#initPath').val()+"//VoteAssessedThingController.do?method=toCreateOrUpdateVoteAssessedThing&objId="+objId);
}

//是否推荐
voteAssessedThingMaintenace.isRecommended = function(objId,isRecommended){
	var tips = isRecommended == 'true' ?"取消":"";
	if(confirm('确定'+tips+'推荐吗？')){
		$.getJSON($('#initPath').val()+'/VoteAssessedThingController.do?method=updateIsRecommendedStatus',{'objId':objId,'isStatusVale':isRecommended},function(json){
			if(json.failure) {
				alert(json.result);
				return;
			}else{
				alert(tips+"推荐成功！");
				voteAssessedThingMaintenace.reload();
			}
		});
	}
}

//审核
voteAssessedThingMaintenace.auditUseStatus = function(objId,useStatus){
	$.getJSON($('#initPath').val()+'/VoteAssessedThingController.do?method=auditAttenderUseStatus',{'objId':objId,'status':useStatus},function(json){
		var strHtml = "";
		if(json.brandInValid == 'true'){strHtml += "此品牌不是有效的！   ";}
		if(json.orgInfoInValid == 'true'){strHtml += "此品牌的所属机构不是有效的！审核未通过！";}
		if(json.result == 'true'){strHtml = "审核参选对象为有效的,审核成功！";}
		alert(strHtml);
		voteAssessedThingMaintenace.reload();
		if(json.failure) {
			alert("审核失败,请重新审核!");
			return;
		}
	});
}

//查看详情
voteAssessedThingMaintenace.viewVoteAssessedThing = function(objId){
	$.epsDialog({
		title:'评选单位查看',
		url:$('#initPath').val()+'/VoteAssessedThingController.do?method=viewVoteAssessedThing&objId='+objId,
		onClose:function(){voteAssessedThingMaintenace.reload();}
	});
}

//删除
voteAssessedThingMaintenace.removeVoteAssessedThing = function(objId){
	if(confirm('确定删除吗？')){
		$.getJSON($('#initPath').val()+'/VoteAssessedThingController.do?method=remove',{objId:objId},function(json){
			if(json.failure) {
				alert(json.result);
				return;
			}else{
				alert("删除成功");
				voteAssessedThingMaintenace.reload();
			}
		});
	}		
}

//图片放大镜
voteAssessedThingMaintenace.onMouseOver = function(picture){
	$('#imgViewer').empty();
	new ImageZoom( picture, "imgViewer", {
		model:'simple',scale: 10, delay: 10,rangeWidth:30,rangeHeight:30
	});
}

//刷新
voteAssessedThingMaintenace.reload = function(){
	voteAssessedThingMaintenace.oTable.fnDraw();
}

//获取列表
voteAssessedThingMaintenace.getvoteAssessedThingList = function(){
	if(null == voteAssessedThingMaintenace.oTable){
		voteAssessedThingMaintenace.oTable = $('#voteAssessedThingList').dataTable({
			'singleSelect':true,
			'checkbox':false,
			'searchZone':'voteAssessedThingSearchForm',
			'sAjaxSource':$('#initPath').val()+"/VoteAssessedThingController.do?method=list",
			 params:{'voteTemplate.objId':$('#voteTemplateId').val()},
			'queryColumns':'picture,attenderName,voteUnitGroup.groupName,assessSort',
			'alias':'',
			'hiddenColumns':'objId,isRecommended,useStatus',
			'iDisplayLength':'5',
			'fnInitComplete':function(oSettings){},
			'fnDrawCallback':function(oSettings){
				voteAssessedThingMaintenace.oTable.oSettings = oSettings;
			},
			'fnRowCallback':function(nRow,aData,iDisplayIndex){
				//图片
				$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["picture"]+'" style="cursor:pointer" width="40px" height="40px" id="'+aData["picture"]+'" onMouseOver="voteAssessedThingMaintenace.onMouseOver(\''+aData["picture"]+'\');" />');

				//修改序号
				$(nRow).find('td[name=assessSort]').empty().append('<form id="sortInputForm'+aData.objId+'" method="post"><input type="text" id="assessSortInput'+aData.objId+'" class="digits" value="'+aData.assessSort+'"/></form>');
				$(nRow).find('input[id=assessSortInput'+aData.objId+']').change(function(){
					if(!$('#sortInputForm'+aData.objId).valid()){alert('请输入整数!');return;}
					$.getJSON($("#initPath").val()+"/VoteAssessedThingController.do?method=setNumSort",{"stringId":aData.objId,'numSort':this.value,'operClass':'VoteAssessedThing'},function(json){
						if(json.result=='true'){alert("操作成功!");voteAssesseExpertList.reload();}
					});
				});

				//修改
				$(nRow).append('<td><a href="javascript:voteAssessedThingMaintenace.modifyVoteAssessedThing(\''+aData.objId+'\')" ><span>修改</span></a></td>');

				//删除
				$(nRow).find("td:last").append('<a href="javascript:voteAssessedThingMaintenace.removeVoteAssessedThing(\''+aData.objId+'\')" ><span>删除<span></a>');

				//是否推荐
				if(aData.isRecommended == 'false'){
					$(nRow).find("td:last").append('<a href="javascript:voteAssessedThingMaintenace.isRecommended(\''+aData.objId+'\',\''+aData.isRecommended+'\')" ><span>未推荐</span></a>');
				}
				if(aData.isRecommended == 'true'){
					$(nRow).find("td:last").append('<a href="javascript:voteAssessedThingMaintenace.isRecommended(\''+aData.objId+'\',\''+aData.isRecommended+'\')" ><span>已推荐</span></a>');
				}

				//审核(是否有效)
				if(aData.useStatus == '00'){
					$(nRow).find("td:last").append('<a href="javascript:voteAssessedThingMaintenace.auditUseStatus(\''+aData.objId+'\',\''+aData.useStatus+'\')" ><span>审核</span></a>');
				}
				
				return nRow;
			}
		});
	}else{
		$(voteAssessedThingMaintenace.oTable.dataTableSettings).attr('params', {'voteTemplate.objId':$('#voteTemplateId').val()});
		voteAssessedThingMaintenace.oTable.fnDraw();
	}
}

$(document).ready(function(){
	voteAssessedThingMaintenace.getvoteAssessedThingList();
});
</script>