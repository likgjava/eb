<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/bizplatform/organization/manager/org_quality_view.js"/>'></script>

<!-- 选择分类和定义 -->

<div class="formLayout form2Pa" id="paramDiv">
	<form id="paramForm" method="post">
	<input id="objId" name="objId" type="hidden" value="${param.objId }">
   	<h4><span>资质分类与资质定义</span></h4>
    <ul>
      <li>
        <label  for="input01">资质分类：</label>
        <span>${orgQuality.qualificationClass.qualityName }</span>
      </li>
      <li>
      	<label for="qualificationDefine">资质定义：</label>
		<span>${orgQuality.qualificationDefine.qualityName }</span>
      </li>
      <c:if test="${orgQuality.qualificationClass.qualityCode != 'C01'}">
      <li class="fullLine">
      	<label>资质文件：</label>
      	<div id="qualificationFile" name="qualificationFile" value="${orgQuality.qualificationFile }"></div>
      </li>
      </c:if>
    </ul>
    </form>
    <form id="param">
    <h4><span>参数信息</span></h4>
    <ul>
	  <c:choose>
	  	<c:when test="${!empty detailList}">
	  		<c:forEach var="detail" items="${detailList}" varStatus="status">
				<c:choose>
					<c:when test="${detail.qualityParam.paramType=='5'}">
						<c:if test="${detail.paramValue != null}">
						<li class="fullLine">
						<label><span>${detail.qualityParam.qualityName }：</span></label>
						<span>
							<c:forEach var = "level" items = "${fn:split(detail.qualityParam.level,',')}">
									<c:if test="${(fn:split(level,'#'))[1] == detail.paramValue}">
										${(fn:split(level,'#'))[0] }
									</c:if>
							</c:forEach>
						</span>
						</li>
						</c:if>
					</c:when>
					<c:otherwise>
						<c:if test="${detail.paramValue != null}">
						<li class="fullLine">
						<label><span>${detail.qualityParam.qualityName }：</span></label>
						<span>${detail.paramValue} ${detail.qualityParam.unit }</span>
						</li>
						</c:if>
					</c:otherwise>
				</c:choose>
	  		</c:forEach>
	  	</c:when>
	  	<c:otherwise>
      		<li class="fullLine"><label>无任何参数！</label></li>
	  	</c:otherwise>
	  </c:choose>
    </ul>
    </form>
</div>

<div class="conOperation" id="goodsClassAddBtnDiv">
    <button type="button" class="largeBtn" id="close"><span><spring:message code='globe.close'/></span></button>
</div>