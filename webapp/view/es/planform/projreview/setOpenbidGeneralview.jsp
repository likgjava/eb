<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/setOpenbidGeneralview.js"></script>
<div class="partContainers">
<input type="hidden" id="project_objId" value="${projectId}"></input>
<input type="hidden" id="lastNum" value="${lastNum}"></input>
<div id="tabs">
<input type="hidden" id="weightCoefficient"></input>
	<ul>
		<c:forEach items="${congruousFactorTypeList}" var="congruousFactorType" >
			<li>
				<a href="#factorType_div${congruousFactorType.objId}" id="${congruousFactorType.objId}" >
					<span>
						${congruousFactorType.typeName}
					</span>
				</a>
			</li>
		</c:forEach>
	</ul>
	<c:forEach items="${congruousFactorTypeList}" var="congruousFactorType">
		<div id="factorType_div${congruousFactorType.objId}"  >
		   <table class="tableList">
		    <thead>	<tr>
		    <th><input type="checkbox" id="checkAll" val="${congruousFactorType.objId}"></th>
		    <th>指标名称</th>
		    <th>适用<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out></th>
		    <th id="fenzhi1">分值</th>
		    <th>评审标准</th>
		    </tr></thead>    
		    <tbody >
		    <c:forEach items="${congruousFactorType.congruousFactorSet}" var="congruousFactor">
		     <tr>
		     <td align="center">
		      <input id="${congruousFactor.objId}" type="checkbox" name="factor" val="${congruousFactorType.objId}"
		      	 <c:forEach items="${genviewDefineList}" var="genviewDefine" >
		         <c:if test="${genviewDefine.factorId == congruousFactor.objId}">checked="checked"</c:if>
		     	 </c:forEach>>
		     <input type="hidden" id="projIds" value="${congruousFactor.projIds}">
		     </td>
		     <td>${congruousFactor.factorName}</td>
		     <td>${congruousFactor.projName}</td>
		     <td name="score1">${congruousFactor.score}</td>
		     </tr>
		    </c:forEach>
		    </tbody>
		   </table>
		</div>	
	</c:forEach>
</div>
	<div class="conOperation">
		<input type="hidden" name="factorIds" id="factorIds" value=""/>
		<button id="nextId" type="button" tabindex="18"><span>预览</span></button>
		
	</div>
<div id="genViewDefine">
    <c:forEach items="${subProjectList}" var="subProject">
		<h4>${subProject.projName}</h4>
		<table class="tableList" id="${subProject.objId}">
		<thead>	<tr><th><input type="hidden" id="subProjectId" value="${subProject.objId}">投标单位名称:</th></tr></thead>
		<tbody >
		       <tr><td>&nbsp;</td></tr>
		</tbody>
		</table>
		<br>
		<br>
	</c:forEach>
</div>	
<div class="conOperation">
	<button id="backId" type="button" tabindex="18"><span>上一步</span></button>
	<button id="saveId" type="button" tabindex="18"><span>保存</span></button>
</div>
</div>	
