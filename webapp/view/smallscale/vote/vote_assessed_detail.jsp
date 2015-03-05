<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div id="voteTemplateInfo" class="formLayout form2Pa">
	<h4>主题信息：</h4>
   	<ul>
    	<li>
    		<label>评选主题：</label>
			${voteTemplate.templateName }
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
    		<label>创建时间：</label>
			${voteTemplate.createTime }
  	    </li>
  	    <li>
    		<label>修改时间：</label>
			${voteTemplate.updateTime }
  	    </li>
  	    <li class="fullLine">
    		<label>评选类型：</label>
    		<c:if test="${voteTemplate.isSingleVote == true}">单一投票</c:if><c:if test="${voteTemplate.isSingleVote == false}">指标评选</c:if>			
  	    </li>
	</ul>
	<h4>主题描述：</h4>
	<div>${voteTemplate.templateComment }</div>
	
	<div id="voteTemplateInfo" class="formLayout form2Pa">
		<h4>评选单位：</h4>
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
		<ul>
			<li class="fullLine"><label>投票人：</label>${voteAssessed.userName }</li>
			<li class="fullLine"><label>投票时间：</label>${voteAssessed.createTime }</li>
			<li class="fullLine"><label>是否匿名投票：</label><c:if test="${voteAssessed.isAnonymity == false}">全名投票</c:if><c:if test="${voteAssessed.isAnonymity == true}">匿名投票</c:if></li>
			<li class="formTextarea"><label>备注：</label>${voteAssessed.memo}</li>
		</ul>
	</div>
	
	<c:if test="${voteAssessedThing.thingComment != null}">
		<h4>单位描述</h4>
		<div>${voteAssessedThing.thingComment }</div>
	</c:if>
	
	<c:if test="${voteTemplate.isSingleVote == false}">
		<div class="formLayout">
			<h4>指标评分信息：</h4>
			<table class="frontTableList">
				<thead>
					<tr>
						<th class="left ">指标名称</th>
						<th class="left ">比例系数</th>
						<th class="left ">分         值</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="votePointerGrade" items="${voteAssessedGradeList}">
						<tr>
							<td align="center">${votePointerGrade.votePointer.pointerName }</td>
							<td align="center">${votePointerGrade.votePointer.pointerFactor }</td>
							<td align="center"><fmt:formatNumber value="${votePointerGrade.grade}" pattern="##0.0#"/>&nbsp;分</td>
						</tr>
					</c:forEach>										
				</tbody>
			</table>
		</div>				
	</c:if>	
	

	
	<div class="conOperation">
		<button type="button" id="voteTemplateBtnReturn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
<script>
//返回
$('#voteTemplateBtnReturn').click(function(){
	//自动关闭		
	$('.epsDialogClose').trigger('click');	
});
</script>