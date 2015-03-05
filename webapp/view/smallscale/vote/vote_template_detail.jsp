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
    		<label>活动开始时间：</label>
			${voteTemplate.startTime }
  	    </li>
    	<li>
    		<label>活动结束时间：</label>
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
	<c:if test="${voteTemplate.templateComment != null }">
		<h4>主题描述：</h4>
		<div>${voteTemplate.templateComment }</div>
	</c:if>
	
	<c:if test="${voteTemplate.isSingleVote == false}">		
		<div id="votePointerListDiv">
			<h4>指标信息：</h4>
			<div id="votePointerListInfo">
				<table class="frontTableList" id="votePointerList">
					<thead>
						<tr>
							<th class="center">指标编号</th>
							<th class="center">指标名称</th>						
							<th class="center">比例系数(%)</th>												
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>		
		</div>			
	</c:if>	
	
	<div>
		<h4>参选对象：</h4>
		<table>
			<c:set var="startCount" value="1"/>
			<c:forEach begin="1" end="${trNumCount/3+1}">
				<c:set var="endCount" value="${startCount+3-1}"/>
				<tr>
					<c:forEach items="${voteAssessThingList}" var="voteAssessedThing" begin="${startCount-1}" end="${endCount-1}">
						<td>
							<div class="k1">
								<c:choose>
									<c:when test="${voteAssessedThing.picture != null}">
										<div class="img_250_1" id="newPreview">
											<img id="view"  src="AttachmentController.do?method=showImg&objId=${voteAssessedThing.picture }" width="200px" height="175px"></img>
										</div>
									</c:when>
									<c:otherwise>
											<img width="200" height="175" src="<c:url value="/view/resource/skin/goods/img/goods_add.gif"/>"></img>
									</c:otherwise>				
								</c:choose>
								<center>${voteAssessedThing.attenderName }</center>
							</div>
							<c:set var="startCount" value="${startCount+1}"/>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="conOperation">
		<button type="button" id="voteTemplateBtnReturn" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
<script>
var voteTemplateDetail={};
voteTemplateDetail.oTable;

//返回
$('#voteTemplateBtnReturn').click(function(){
	//自动关闭		
	$('.epsDialogClose').trigger('click');	
});

$(document).ready(function(){
	if(${voteTemplate.isSingleVote == false}){
		//填充列表
		voteTemplateDetail.oTable = $('#votePointerList').dataTable({
			'singleSelect':true,
			'checkbox':false,		
			'sAjaxSource':$('#initPath').val()+"/VotePointerController.do?method=list",
			 params:{'voteTemplate.objId':$('#voteTemplateId').val()},
			'queryColumns':'pointerCode,pointerName,pointerFactor',
			'alias':'',
			'hiddenColumns':'objId',
			'fnInitComplete':function(oSettings){},
			'fnDrawCallback':function(oSettings){
				voteTemplateDetail.oTable.oSettings = oSettings;
			},
			'fnRowCallback':function(nRow,aData,iDisplayIndex){
				return nRow;
			},
			'iDisplayLength':'5'
		});
	}
});
</script>