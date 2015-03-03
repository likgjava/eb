<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/bizplatform/organization/manager/org_quality_form.js"/>'></script>

<!-- 选择分类和定义 -->

<div class="formLayout form2Pa" id="paramDiv">
	<form id="paramForm" method="post">
	<input id="objId" name="objId" type="hidden" value="${param.objId }">
   	<h4><span>资质分类与资质定义</span></h4>
    <ul>
      <li>
        <label  for="input01">资质分类：</label>
        <input id="qualificationClass.objId" name="qualificationClass.objId" type="hidden" value="${orgQuality.qualificationClass.objId }">
		<input id = "qualificationClass.qualityName" name="qualificationClass.qualityName" value="${orgQuality.qualificationClass.qualityName }" 
		<c:if test="${orgQuality.qualificationClass!=null }">
			disabled="disabled"
		</c:if>
		readonly="readonly" class="sysicon siSearch required" size="40"><span class="eleRequired">*</span>
      </li>
      <li>
      	<label for="qualificationDefine">资质定义：</label>
      	<select id="qualificationDefine.objId" name="qualificationDefine.objId" style="width:180px;" class="required">
      	<c:choose>
      		<c:when test="${orgQuality.qualificationDefine != null}">
      			<option value="${orgQuality.qualificationDefine.objId }">${orgQuality.qualificationDefine.qualityName }</option>
      		</c:when>
      		<c:otherwise>
      		<option>---请选择定义---</option>
      		</c:otherwise>
      	</c:choose>
      	</select><span class="eleRequired">*</span>
      </li>
      <li class="fullLine">
      	<label>资质文件：</label>
      	<div class="uploadFile" id="qualificationFile" name="qualificationFile" value="${orgQuality.qualificationFile }"></div>
      </li>
    </ul>
    </form>
    <form id="param">
    <h4><span>参数信息</span></h4>
    <ul>
	  <c:choose>
	  	<c:when test="${!empty qualificationDetailList}">
	  		<c:forEach var="detail" items="${qualificationDetailList}" varStatus="status">
	  			<li class="fullLine">
	  			<label><span>${detail.qualityParam.qualityName }： </span></label>
	  			<input name="${detail.objId }.ParamId" value="${detail.qualityParam.objId }" class="hidden"/>
				<c:choose>
					<c:when test="${detail.qualityParam.paramType!='5'}">
										<c:set var="className" value=""/>
										<c:if test="${detail.qualityParam.isRequired == true}"><c:set var="className" value="required"/></c:if>
										<c:choose>
											<c:when test = "${detail.qualityParam.paramType=='2' }"><c:set var="className" value="${className} digits"/></c:when>
										 	<c:when test = "${detail.qualityParam.paramType=='4' }"><c:set var="className" value="${className} sysicon siDate bbit-dp-input"/></c:when>
										 	<c:when test = "${detail.qualityParam.paramType=='3' }"><c:set var="className" value="${className} floats"/></c:when>
										 	<c:otherwise><c:set var="className" value="${className} text"/></c:otherwise>
										 </c:choose>
										<input name="${detail.objId }.paramValue" value="${detail.paramValue }" size="70" class="${className}"/>
					</c:when>
					<c:otherwise>
						<select name="${detail.objId }.paramValue" class="<c:if test="${detail.qualityParam.isRequired == true}">required </c:if>" >
							<option value="" 
								<c:if test="${detail.paramValue ==null||detail.paramValue ==''}">
									selected = "selected"	
								</c:if>
							>---请选择---</option>
							<c:forEach var = "level" items = "${fn:split(detail.qualityParam.level,',')}">
									<option value="${(fn:split(level,'#'))[1] }"
									
									<c:if test="${detail.paramValue ==(fn:split(level,'#'))[1] }">
									selected = "selected"	
									</c:if>
									
									>${(fn:split(level,'#'))[0] }</option>
							</c:forEach>
						</select>
					</c:otherwise>
				</c:choose>
	  			<input name="${detail.objId }.detailId" value="${detail.objId }" class="hidden"/>
	  			
				<c:if test="${detail.qualityParam.isRequired == true}">
					<span class="eleRequired">*</span>
				</c:if>
				
				<c:choose>
					<c:when test = "${detail.qualityParam.paramType=='5' }">
				 		<span>级别格式</span>
				 	</c:when>
				 	<c:when test = "${detail.qualityParam.paramType=='4' }">
				 		<span>日期格式</span>
				 	</c:when>
				 	<c:when test = "${detail.qualityParam.paramType=='3' }">
				 		<span>含小数格式</span>
				 	</c:when>
				 	<c:when test = "${detail.qualityParam.paramType=='2' }">
				 		<span>整数格式</span>
				 	</c:when>
				 	<c:otherwise>
				 		<span>文本格式</span>
				 	</c:otherwise>
				 </c:choose>
				</li>
	  		</c:forEach>
	  	</c:when>
	  	<c:otherwise>
      		<li class="fullLine"><span>请先选择分类和定义！</span></li>
	  	</c:otherwise>
	  </c:choose>
	  <c:if test="${orgQuality.opinion!=null}">
		  <li id="opinion" class="fullLine">
			<label>审核意见：</label>	  
			<span>${orgQuality.opinion }(${orgQuality.auditStatusCN })</span>
		  </li>
	  </c:if>
    </ul>
    </form>
</div>

<div class="conOperation" id="goodsClassAddBtnDiv">
    <button type="button" class="largeBtn" id="saveQuality"><span><spring:message code='globe.save'/></span></button>
    <button type="button" class="largeBtn" id="submitQuality"><span><spring:message code='globe.submit'/></span></button>
    <button type="button" class="largeBtn" id="close"><span><spring:message code='globe.close'/></span></button>
</div>