<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/eval/importSysFactorList.js"></script>
<input type="hidden" id="_select_type" value="checkbox"></input>
<input type="hidden" id="_return_id" value="${param.returnId}"></input>
<input type="hidden" id="_return_name" value="${param.returnName}"></input>
<input type="hidden" id="_epsDialog_id" value="${param.epsDialogId}">
<input type="hidden" id="PROJ_ID_" value="${project.objId}" ></input>
<input type="hidden" id="score_num" value="${param.scoreNum}"></input>
<div class="frameMainSub frameMs12">
	<div class="accordion frameMain">
		<div class="accordionHeader">
			<h2><span></span><a></a></h2>
		</div>
		<div class="accordionContent">
			<div id="menu_tree" style="height: 420px"></div>
		</div>
	</div>
	<div id="rightOptionDiv" class="frameSub">
		<div id="factor_type_info"></div>
		<div id="selectFactorIsRadioList"></div>
		<div id="selectFactorIsCheckboxList" >
			<div class="frameParallel frame3Pa noborder">
			    <div class="framePaCon ">
			      <ul>
			        <li><select size="20" id="toSelect" multiple="true" style="width: 100%;height: 100px"></select></li>
			      </ul>
			    </div>
			     <div class="framePaCon option" style="width: 10%">
			      <ul>
			        <li><button id="ADD"><span>添加&nbsp;&gt;&gt;</span></button></li>
			        <li></li>
			        <li><button id="DELETE"><span>&lt;&lt;&nbsp;移除</span></button></li>
			      </ul>
			    </div>
			    <div class="framePaCon" style="float: left;">
			      <ul>
			        <li><select size="20" id="alreadySelect" multiple="true" style="width: 100%;height: 100px"></select></li>
			      </ul>
			   </div>
			  </div>
			  <div>
				<table class="tableList">
					<tr>
						<c:if test="${fn:length(packList) > 0}">
							<th style="width: 111px"><label>请选择适用<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>：</label></th>
							<td id="SUB_PROJ_CHECK_TD">
								<c:forEach items="${packList}" var="pack">
				   	       			<input type="checkbox" id="projId" value="${pack.objId}" />${pack.projName}
				   	       		</c:forEach>
							</td>
						</c:if>
						<th style="width: 130px"><label><spring:message code="congruousFactorForm.isNeed"/>：</label></th>
						<td>
							是<input type="radio" name="isNeed"  value="01" checked="checked"/>否<input type="radio" name="isNeed" value="00"/>
						</td>
					</tr>
				</table>
				<form id="importCongruousFactorForm" type="post">
					<table class="tableList" id="congruousFactorTable">
						<caption>已选指标信息</caption>
						<tr>
							<th>指标名称</th>
							<c:if test="${param.scoreNum > 0.0}">
								<th>分值</th>
							</c:if>
							<th>评审标准</th>
						</tr>
					</table>
				</form>
			</div>

			<div class="conOperation"><button id="OK"><span>确定</span></button>&nbsp;<button id="CLEAR"><span>清空</span></button></div>
		</div>
	</div>
</div>


