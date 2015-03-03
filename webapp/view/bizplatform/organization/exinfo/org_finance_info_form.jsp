<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!-- 导航显示 -->
<div class="nextBar">
    <ol class="num4">
        <li class="done current-prev"><span class="first">1. 填写基本信息</span></li> 
        <li class="current"><span>2. 填写财务信息</span></li>
        <li><span>3. 填写法务信息</span></li>
        <li class="last"><span>4. 填写企业资质</span></li>
    </ol>
</div>
<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>如果您的企业基本信息还未提交，请将企业的信息完善后再进行提交操作，提交操作在"基本信息"页面！
		</li>
	</ul>
</div>
<!-- end 导航显示 -->

<script type="text/javascript">
	$(document).ready(function() {
		$('#qualificationDetailForm').validate({});
		
		// 获取资质内容
		$.getJSON($('#initPath').val()+"/QualificationDetailController.do?method=getObjectQuery&queryColumns=objId,paramValue,qualityParam.objId,orgQuality.objId,orgQuality.qualificationDefine.objId",{"orgQuality.org.objId":$('#orgId').val()},function(json){
			$('li[name=qualificationDetail]').each(function(j,li){
					var orgQualityId = "";
					$.each(json.result,function(i,n){
						orgQualityId = n['orgQuality.objId'];
						if($(li).attr('id') == n['qualityParam.objId']){
							$(li).find('input[id=objId]').val(n.objId);
							$(li).find('input[name$=paramValue]').val(n.paramValue);
							$(li).find('select[name$=paramValue]').find('option[value='+n.paramValue+']').attr('selected',true);;
						}
						if($(li).attr('def') == n['orgQuality.qualificationDefine.objId']){
							
							$(li).find('input[id=orgQuality.objId]').val(orgQualityId);
						}
					});
				});
		})

		// 保存
		$('#saveQualificationDetailBtn').click(function(){
			if(!$('#qualificationDetailForm').valid()){alert('请正确填写表单!');return;}
			$('#saveQualificationDetailBtn').attr('disabled',true);
			var financeInfoJSONString=JSON.stringify(formToJsonObject("qualificationDetailForm","json").QualificationDetail);
			$.getJSON($('#initPath').val()+'/ExOrgInfoController.do?method=saveOrgFinanceInfo',{financeInfoJSONString:financeInfoJSONString}, function(json){
				if(json.result)alert(json.result);if(json.failure){$('#saveQualificationDetailBtn').attr('disabled',false);return};
				$('#saveQualificationDetailBtn').attr('disabled',false);
				if($('input[id=returnUrlType][name=qualificationForm]').val() !="" && $('input[id=returnUrlType][name=qualificationForm]').val() !="null"){
					$('#conBody').loadPage($('#initPath').val()+"/ExOrgInfoController.do?method=toOrgFinanceInfoView&urlType=self&qualificationClassId="+$('#qualificationClassId').val());
				}else{
					$('#conBody').loadPage($('#returnUrl').val());
				}
		    	
			})
		});
		
	})

</script>
<div class="formLayout">
<input type="hidden" id="orgId" value="${orgId }" />
<input type="hidden" id="returnUrlType" name="qualificationForm" value="${param.urlType}" />
<input type="hidden" id="qualificationClassId" value="${qualificationClassId}" />
<input type="hidden" id="qualificationCode" value="${qualificationCode}" />
<form id="qualificationDetailForm">
<c:set var="qualificationDetailCount" value="0"/>
<c:choose>
<c:when test="${null != qualificationList && fn:length(qualificationList) > 0}">
<c:forEach var="qualificationDef" items="${qualificationList}" varStatus="status">
	<c:if test="${qualificationDef.type=='01'}">
		<c:set var="count" value="0"/>
		<c:forEach var="qualificationParam" items="${qualificationDef.subQualification}">
		<c:if test="${count == 0}">
			<h5>${qualificationDef.qualityName }</h5>
		</c:if>
		<c:if test="${qualificationParam.type=='02'}">
		<ul>
		<c:choose>
			<c:when test="${qualificationParam.paramType!='5'}">
			<li name="qualificationDetail" id="${qualificationParam.objId }" def="${qualificationDef.objId}"><label>${qualificationParam.qualityName }：</label> 
			<span id="parent.name">
			<input type="hidden" name="QualificationDetail[${qualificationDetailCount}].objId" id="objId" value="" />
			<input type="hidden" name="QualificationDetail[${qualificationDetailCount}].orgQuality.objId" id="orgQuality.objId" value="" />
			<input type="hidden" name="QualificationDetail[${qualificationDetailCount}].orgQuality.qualificationDefine.objId" id="qualificationDefId" value="${qualificationDef.objId}" />
			<input type="hidden" name="QualificationDetail[${qualificationDetailCount}].orgQuality.qualificationClass.objId" id="qualificationClassId" value="${qualificationClassId}" />
			<input type="hidden" name="QualificationDetail[${qualificationDetailCount}].orgQuality.org.objId" value="${orgId}" />
			<input type="hidden" name="QualificationDetail[${qualificationDetailCount}].qualityParam.objId" value="${qualificationParam.objId }" />
			<input type="text" name="QualificationDetail[${qualificationDetailCount}].paramValue"  maxlength="50" value="" class="<c:if test="${qualificationParam.isRequired == true}">required </c:if>
				 <c:choose>
				 	<c:when test = "${qualificationParam.paramType=='2' }">
				 		digits 
				 	</c:when>
				 	<c:when test = "${qualificationParam.paramType=='4' }">
				 		sysicon siDate bbit-dp-input
				 	</c:when>
				 	<c:when test = "${qualificationParam.paramType=='3' }">
				 		floats
				 	</c:when>
				 	<c:otherwise>
				 		text
				 	</c:otherwise>
				 </c:choose>
				"/>
				<c:if test="${fn:length(qualificationParam.unit) > 0 }">
				(${qualificationParam.unit})
				</c:if>
				<c:if test="${qualificationParam.isRequired == true}">
					<span class="eleRequired">*</span>
				</c:if>
			</span>
			</li>
			</c:when>
			<c:otherwise>
			<li name="qualificationDetail" id="${qualificationParam.objId }" def="${qualificationDef.objId}"><label>${qualificationParam.qualityName }：</label> 
			<span id="parent.name">
			<input type="hidden" name="QualificationDetail[${qualificationDetailCount}].objId" id="objId" value="" />
			<input type="hidden" name="QualificationDetail[${qualificationDetailCount}].orgQuality.objId" id="orgQuality.objId" value="" />
						<input type="hidden" name="QualificationDetail[${qualificationDetailCount}].orgQuality.qualificationDefine.objId" id="qualificationDefId" value="${qualificationDef.objId}" />
			<input type="hidden" name="QualificationDetail[${qualificationDetailCount}].orgQuality.qualificationClass.objId" id="qualificationClassId" value="${qualificationClassId}" />
			<input type="hidden" name="QualificationDetail[${qualificationDetailCount}].orgQuality.org.objId" value="${orgId}" />
			<input type="hidden" name="QualificationDetail[${qualificationDetailCount}].qualityParam.objId" value="${qualificationParam.objId }" />
			<select name="QualificationDetail[${qualificationDetailCount}].paramValue"  maxlength="50" class="<c:if test="${qualificationParam.isRequired == true}">required </c:if>" >
					<option value="">-请选择-</option>
					<c:forEach var = "level" items = "${fn:split(qualificationParam.level,',')}">
							<option value="${(fn:split(level,'#'))[1] }"
							
							
							>${(fn:split(level,'#'))[0] }</option>
					</c:forEach>
				</select>
				<c:if test="${qualificationParam.isRequired == true}">
					<span class="eleRequired">*</span>
				</c:if>
				</span>
			</li>
			</c:otherwise>
		</c:choose>
		<c:set var="qualificationDetailCount" value="${qualificationDetailCount + 1}"/>	
		</ul>
		</c:if>
		<c:set var="count" value="${count + 1}"/>	
		</c:forEach>
	</c:if>
</c:forEach>

</c:when>
</c:choose>
<div class="conOperation">
	<button type="button" id="saveQualificationDetailBtn"><span><spring:message code='globe.save'/></span></button>
</div>
</form>
</div>