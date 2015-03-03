<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/bargain/modify_bargain_turn.js"></script>

<form id="BargainProjectModifyForm" method="post">
<input type="hidden" name="projId" id="projId" value="${projId}"/>
<input type="hidden" name="evalStartTime" id="evalStartTime" value="<fmt:formatDate value="${evalStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
<input type="hidden" name="evalEndTime" id="evalEndTime" value="<fmt:formatDate value="${evalEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>

		<div id="barginTurn">
			<ul class="safe-info">
				<li class="item identity">
					<div class="title" style="width:120px;">轮次信息</div>
					<div class="supply" style="width:400px">[项目的竞价起止时间为第一轮开始时间和最后一轮结束时间]</div>
					<div class="state"><span class="over"></span></div>
					<div class="operate"></div>
				</li>
				<c:choose>
				<c:when test="${!empty bargainTurnList && fn:length(bargainTurnList) > 0}">
					<c:forEach var="bargainTurn" items="${bargainTurnList}" varStatus="status">
						<li class="item identity temp">
							<input type="hidden" name="turnNo${bargainTurn.turnNo}" id="turnNo${bargainTurn.turnNo}" value="${bargainTurn.turnNo}"/>
							<input type="hidden" name="objId${bargainTurn.turnNo}" id="objId${bargainTurn.turnNo}" value="${bargainTurn.objId}"/>
							<div class="title" style="width:120px;">
								<label>第<b id="${bargainTurn.turnNo}">${bargainTurn.turnNo}</b>轮：</label>
							</div>
							<div class="supply" style="width:400px">
								<span><input type="text" class="required" style="width:150px;" name="startTime" id="startTime${bargainTurn.turnNo}" value="<fmt:formatDate value="${bargainTurn.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"/></span>
								至
								<span><input type="text" class="required" style="width:150px;" name="endTime" id="endTime${bargainTurn.turnNo}" value="<fmt:formatDate value="${bargainTurn.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"/></span>
							</div>
							<div class="supply" style="width:220px">
								<label>降幅：</label>
								<span>
									<c:if test="${bargainTurn.totalCutType=='01'}">
										<input name="totalCutType${bargainTurn.turnNo}" id="totalCutType${bargainTurn.turnNo}" type="radio" checked="checked" value="01" />
										<input name="totalCut_${bargainTurn.turnNo}" id="totalCut_${bargainTurn.turnNo}" type="text" maxlength="2" class="digits" style="width:20px" value="${bargainTurn.totalCut}"/>%
										<input name="totalCutType${bargainTurn.turnNo}" id="totalCutType${bargainTurn.turnNo}" type="radio" value="02"/>
										<input name="totalCut${bargainTurn.turnNo}" id="totalCut${bargainTurn.turnNo}" type="text" class="money" style="width:40px" value="0.0"/>元
									</c:if>
									<c:if test="${bargainTurn.totalCutType=='02'}">
										<input name="totalCutType${bargainTurn.turnNo}" id="totalCutType${bargainTurn.turnNo}" type="radio" value="01" />
										<input name="totalCut_${bargainTurn.turnNo}" id="totalCut_${bargainTurn.turnNo}" type="text" maxlength="2" class="digits" style="width:20px"/>%
										<input name="totalCutType${bargainTurn.turnNo}" id="totalCutType${bargainTurn.turnNo}" type="radio" checked="checked" value="02"/>
										<input name="totalCut${bargainTurn.turnNo}" id="totalCut${bargainTurn.turnNo}" type="text" class="money" style="width:40px" value="${bargainTurn.totalCut}"/>元
									</c:if>
								</span>
							</div>
							
							<div class="state">
								<span class="over"></span>
							</div>
							<div class="operate">
								<c:choose>
								<c:when test="${fn:length(bargainTurnList) == status.index+1}">
									<c:if test="${status.index!=0}">
										<span id="delTurn${status.index+1}" class="sysicon siDelete"><a class="tempa" href="javascript:void(0);">删除</a></span>
									</c:if>
									<span id="addTurn${status.index+1}" class="sysicon siAdd"><a href="javascript:BargainProjectModifyForm.cloneTurn();">增加</a></span>
								</c:when>
								<c:otherwise>
									<span id="delTurn${status.index+1}" class="sysicon siDelete hidden"><a class="tempa" href="javascript:void(0);">删除</a></span>
									<span id="addTurn${status.index+1}" class="sysicon siAdd hidden"><a href="javascript:BargainProjectModifyForm.cloneTurn();">增加</a></span>
								</c:otherwise>
								</c:choose>
							</div>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<li class="item identity temp">
						<input type="hidden" name="objId1" id="objId1" value=""/>
						<input type="hidden" name="turnNo1" id="turnNo1" value="1"/>
						<div class="title" style="width:120px;">
							<label>第<b id="1">1</b>轮：</label>
						</div>
						<div class="supply" style="width:400px">
							<span><input type="text" name="startTime" id="startTime1" style="width:150px;" class="required" value="" readonly="readonly"/></span>
							至
							<span><input type="text" name="endTime" id="endTime1" style="width:150px;" class="required" value="" readonly="readonly"/></span>
						</div>
						
						<div class="supply" style="width:220px">
								<label>降幅：</label>
								<span>
						        <input name="totalCutType" id="totalCutType" type="radio" checked="checked" value="01" />
								<input name="totalCut" id="totalCut" type="text" maxlength="2" class="digits" style="width:40px" value="0"/>%
								<input name="totalCutType" id="totalCutType" type="radio" value="02"/>
								<input name="totalCut_" id="totalCut_" type="text" class="money" style="width:40px" value="0.0"/>元		
								</span>
						</div>
						
						<div class="state">
							<span class="over"></span>
						</div>
						<div class="operate">
							<span id="delTurn1" class="sysicon siDelete"><a class="tempa" href="javascript:void(0);">删除</a></span>
							<span id="addTurn1" class="sysicon siAdd"><a href="javascript:BargainProjectModifyForm.cloneTurn();">增加</a></span>
						</div>
					</li>
				</c:otherwise>
				</c:choose>
			</ul>
		</div>
		
		<div style="height:30px;"></div>
		
		<div id="projRuleDiv">
		<ul class="safe-info">
			<c:set var="bargainNumber_value" value="1"></c:set>
			<c:set var="maxPrice_value" value="0"></c:set>
			<c:set var="minPrice_value" value="0"></c:set>
			<c:set var="averagePrice_value" value="0"></c:set>
			<c:set var="selfRanking_value" value="1"></c:set>
			<c:set var="buyerBudget_value" value="1"></c:set>
			<c:set var="threeMust_value" value="0"></c:set>
			<c:forEach var="sysconfigItem" items="${projruleList}" varStatus="status1">
			<c:if test="${sysconfigItem.code=='bargainNumber'}">
				<input type="hidden" id="id_bargainNumber" name="id_bargainNumber" value="${sysconfigItem.objId}"/>
				<input type="hidden" id="itemid_bargainNumber" name="itemid_bargainNumber" value="${sysconfigItem.sysItemId}"/>
				<c:if test="${!empty bargainNumber_value}">
				<c:set var="bargainNumber_value" value="${sysconfigItem.resValue}"></c:set>
				</c:if>
			</c:if>
			<c:if test="${sysconfigItem.code=='maxPrice'}">
				<input type="hidden" id="id_maxPrice" name="id_maxPrice" value="${sysconfigItem.objId}"/>
				<input type="hidden" id="itemid_maxPrice" name="itemid_maxPrice" value="${sysconfigItem.sysItemId}"/>
				<c:if test="${!empty maxPrice_value}">
				<c:set var="maxPrice_value" value="${sysconfigItem.resValue}"></c:set>
				</c:if>
			</c:if>
			<c:if test="${sysconfigItem.code=='minPrice'}">
				<input type="hidden" id="id_minPrice" name="id_minPrice" value="${sysconfigItem.objId}"/>
				<input type="hidden" id="itemid_minPrice" name="itemid_minPrice" value="${sysconfigItem.sysItemId}"/>
				<c:if test="${!empty minPrice_value}">
				<c:set var="minPrice_value" value="${sysconfigItem.resValue}"></c:set>
				</c:if>
			</c:if>
			<c:if test="${sysconfigItem.code=='averagePrice'}">
				<input type="hidden" id="id_averagePrice" name="id_averagePrice" value="${sysconfigItem.objId}"/>
				<input type="hidden" id="itemid_averagePrice" name="itemid_averagePrice" value="${sysconfigItem.sysItemId}"/>
				<c:if test="${!empty averagePrice_value}">
				<c:set var="averagePrice_value" value="${sysconfigItem.resValue}"></c:set>
				</c:if>
			</c:if>
			<c:if test="${sysconfigItem.code=='selfRanking'}">
				<input type="hidden" id="id_selfRanking" name="id_selfRanking" value="${sysconfigItem.objId}"/>
				<input type="hidden" id="itemid_selfRanking" name="itemid_selfRanking" value="${sysconfigItem.sysItemId}"/>
				<c:if test="${!empty selfRanking_value}">
				<c:set var="selfRanking_value" value="${sysconfigItem.resValue}"></c:set>
				</c:if>
			</c:if>
			<c:if test="${sysconfigItem.code=='buyerBudget'}">
				<input type="hidden" id="id_buyerBudget" name="id_buyerBudget" value="${sysconfigItem.objId}"/>
				<input type="hidden" id="itemid_buyerBudget" name="itemid_buyerBudget" value="${sysconfigItem.sysItemId}"/>
				<c:if test="${!empty buyerBudget_value}">
				<c:set var="buyerBudget_value" value="${sysconfigItem.resValue}"></c:set>
				</c:if>
			</c:if>
			
			<c:if test="${sysconfigItem.code=='threeMust'}">
				<input type="hidden" id="id_threeMust" name="id_threeMust" value="${sysconfigItem.objId}"/>
				<input type="hidden" id="itemid_threeMust" name="itemid_threeMust" value="${sysconfigItem.sysItemId}"/>
				<c:if test="${!empty threeMust_value}">
				<c:set var="threeMust_value" value="${sysconfigItem.resValue}"></c:set>
				</c:if>
			</c:if>
			
			</c:forEach>
			
			<li class="item identity">
				<div class="title" style="width:120px;">项目规则</div>
				<div class="supply" style="width:400px">[点击下边的单选按钮，设置项目规则信息]</div>
				<div class="state"><span class="over"></span></div>
				<div class="operate"></div>
			</li>
			
			<li class="item identity" code="bargainNumber" title="<spring:message code="BargainProjectRule.bargainNumber"/>">
				<input type="hidden" id="bargainNumber" value="${bargainNumber_value}">
				<div class="title" style="width:120px;" style="width:120px;">
					<spring:message code="BargainProjectRule.bargainNumber"/>
				</div>
				<div class="supply" style="width:400px">
					<spring:message code="BargainProjectRule.bargainNumber_desc"/>
				</div>
				<div class="state">
					<span class="over hidden" id="bargainNumber_span">
						<c:if test="${bargainNumber_value == '0'}">
							单次报价
						</c:if>
						<c:if test="${bargainNumber_value == '1'}">
							多次报价
						</c:if>
					</span>
				</div>
				<div class="operate">
					<c:choose>
					<c:when test="${!empty bargainNumber_value}">
						<input name="bargainNumber" id="bargainNumber_1" type="radio"
							<c:if test="${bargainNumber_value == '0'}">
								checked="checked"
							</c:if>
						 value="0" title="单次报价"/>单次报价
				    	<input name="bargainNumber" id="bargainNumber_2" type="radio" 
				    		<c:if test="${bargainNumber_value == '1'}">
								checked="checked"
							</c:if>
				    	value="1" title="多次报价"/>多次报价
			    	</c:when>
			    	<c:otherwise>
			    		<input name="bargainNumber" id="bargainNumber_1" type="radio" value="0" title="单次报价"/>单次报价
				    	<input name="bargainNumber" id="bargainNumber_2" type="radio" checked="checked" value="1" title="多次报价"/>多次报价
			    	</c:otherwise>
			    	</c:choose>
				</div>
			</li>
			<li class="item identity " code="buyerBudget" title="<spring:message code="BargainProjectRule.buyerBudget"/>">
				<input type="hidden" id="buyerBudget" value="${buyerBudget_value}">
				<div class="title" style="width:120px;">
					<spring:message code="BargainProjectRule.buyerBudget"/>
				</div>
				<div class="supply" style="width:400px">
					<spring:message code="BargainProjectRule.buyerBudget_desc"/>
				</div>
				<div class="state">
				   <span class="over hidden" id="buyerBudget_span">
				   		<c:if test="${buyerBudget_value == '1'}">
							显示
						</c:if>
						<c:if test="${buyerBudget_value == '0'}">
							不显示
						</c:if>
				   </span>
				</div>
				<div class="operate">
				<c:choose>
					<c:when test="${!empty buyerBudget_value}">
				    	<input name="buyerBudget" id="buyerBudget_1" type="radio" 
				    		<c:if test="${buyerBudget_value == '0'}">
								checked="checked" 
							</c:if>
				    		value="0" title="不显示"/>不显示
						<input name="buyerBudget" id="buyerBudget_2" type="radio" 
							<c:if test="${buyerBudget_value == '1'}">
								checked="checked" 
							</c:if>
							value="1" title="显示"/>显示
					</c:when>
					<c:otherwise>
						<input name="buyerBudget" id="buyerBudget_1" type="radio" value="0" title="不显示"/>不显示
						<input name="buyerBudget" id="buyerBudget_2" type="radio" checked="checked" value="1" title="显示"/>显示
					</c:otherwise>
				</c:choose>
				</div>
			</li>
			<li class="item identity " code="maxPrice" title="<spring:message code="BargainProjectRule.maxPrice"/>">
				<input type="hidden" id="maxPrice" value="${maxPrice_value}">
				<div class="title" style="width:120px;">
					<spring:message code="BargainProjectRule.maxPrice"/>
				</div>
				<div class="supply" style="width:400px">
					<spring:message code="BargainProjectRule.maxPrice_desc"/>
				</div>
				<div class="state">
				   <span class="over hidden" id="maxPrice_span">
				   		<c:if test="${maxPrice_value == '1'}">
							显示
						</c:if>
						<c:if test="${maxPrice_value == '0'}">
							不显示
						</c:if>
				   </span>
				</div>
				<div class="operate">
				<c:choose>
					<c:when test="${!empty maxPrice_value}">
				    	<input name="maxPrice" id="maxPrice_1" type="radio"
				    		<c:if test="${maxPrice_value == '0'}">
								 checked="checked" 
							</c:if>
				    	value="0" title="不显示"/>不显示
						<input name="maxPrice" id="maxPrice_2" type="radio" 
							<c:if test="${maxPrice_value == '1'}">
								 checked="checked" 
							</c:if>
						value="1" title="显示"/>显示
					</c:when>
					<c:otherwise>
						<input name="maxPrice" id="maxPrice_1" type="radio" checked="checked" value="0" title="不显示"/>不显示
						<input name="maxPrice" id="maxPrice_2" type="radio" value="1" title="显示"/>显示
					</c:otherwise>
				</c:choose>
				</div>
			</li>
			<li class="item identity " code="minPrice" title="<spring:message code="BargainProjectRule.minPrice"/>">
				<input type="hidden" id="minPrice" value="${minPrice_value}">
				<div class="title" style="width:120px;">
					<spring:message code="BargainProjectRule.minPrice"/>
				</div>
				<div class="supply" style="width:400px">
					<spring:message code="BargainProjectRule.minPrice_desc"/>
				</div>
				<div class="state">
				   <span class="over hidden" id="minPrice_span">
				   		<c:if test="${minPrice_value == '0'}">
							 不显示
						</c:if>
						<c:if test="${minPrice_value == '1'}">
							 显示
						</c:if>
				   </span>
				</div>
				<div class="operate">
				<c:choose>
					<c:when test="${!empty minPrice_value}">
				    	<input name="minPrice" id="minPrice_1" type="radio" 
				    		<c:if test="${minPrice_value == '0'}">
								 checked="checked" 
							</c:if>
				    	value="0" title="不显示"/>不显示
						<input name="minPrice" id="minPrice_2" type="radio" 
							<c:if test="${minPrice_value == '1'}">
								 checked="checked" 
							</c:if>
						value="1" title="显示"/>显示
					</c:when>
					<c:otherwise>
						<input name="minPrice" id="minPrice_1" type="radio" checked="checked" value="0" title="不显示"/>不显示
						<input name="minPrice" id="minPrice_2" type="radio" value="1" title="显示"/>显示
					</c:otherwise>
				</c:choose>
				</div>
			</li>
			<li class="item identity " code="averagePrice" title="<spring:message code="BargainProjectRule.averagePrice"/>">
				<input type="hidden" id="averagePrice" value="${averagePrice_value}">
				<div class="title" style="width:120px;">
					<spring:message code="BargainProjectRule.averagePrice"/>
				</div>
				<div class="supply" style="width:400px">
					<spring:message code="BargainProjectRule.averagePrice_desc"/>
				</div>
				<div class="state">
				   <span class="over hidden" id="averagePrice_span">
				   		<c:if test="${averagePrice_value == '0'}">
							 不显示
						</c:if>
						<c:if test="${averagePrice_value == '1'}">
							 显示
						</c:if>
				   </span>
				</div>
				<div class="operate">
				<c:choose>
					<c:when test="${!empty averagePrice_value}">
				    	<input name="averagePrice" id="averagePrice_1" type="radio" 
				    		<c:if test="${averagePrice_value == '0'}">
								 checked="checked" 
							</c:if>
				    	value="0" title="不显示"/>不显示
						<input name="averagePrice" id="averagePrice_2" type="radio" 
							<c:if test="${averagePrice_value == '1'}">
								 checked="checked" 
							</c:if>
						value="1" title="显示"/>显示
					</c:when>
					<c:otherwise>
						<input name="averagePrice" id="averagePrice_1" type="radio" checked="checked" value="0" title="不显示"/>不显示
						<input name="averagePrice" id="averagePrice_2" type="radio" value="1" title="显示"/>显示
					</c:otherwise>
				</c:choose>
				</div>
			</li>
			<li class="item identity " code="selfRanking" title="<spring:message code="BargainProjectRule.selfRanking"/>">
				<input type="hidden" id="selfRanking" value="${selfRanking_value}">
				<div class="title" style="width:120px;">
					<spring:message code="BargainProjectRule.selfRanking"/>
				</div>
				<div class="supply" style="width:400px">
					<spring:message code="BargainProjectRule.selfRanking_desc"/>
				</div>
				<div class="state">
				   <span class="over hidden" id="selfRanking_span">
				   		<c:if test="${selfRanking_value == '0'}">
							 不显示
						</c:if>
						<c:if test="${selfRanking_value == '1'}">
							 显示
						</c:if>
				   </span>
				</div>
				<div class="operate">
				<c:choose>
					<c:when test="${!empty selfRanking_value}">
				    	<input name="selfRanking" id="selfRanking_1" type="radio" 
				    		<c:if test="${selfRanking_value == '0'}">
								checked="checked"
							</c:if> 
				    	value="0" title="不显示"/>不显示
						<input name="selfRanking" id="selfRanking_2" type="radio"
							<c:if test="${selfRanking_value == '1'}">
								checked="checked"
							</c:if> 
						value="1" title="显示"/>显示
					</c:when>
					<c:otherwise>
						<input name="selfRanking" id="selfRanking_1" type="radio" value="0" title="不显示"/>不显示
						<input name="selfRanking" id="selfRanking_2" type="radio" checked="checked" value="1" title="显示"/>显示
					</c:otherwise>
				</c:choose>
				</div>
			</li>
			
			<li class="item threeMust " code="threeMust" title="<spring:message code="BargainProjectRule.threeMust"/>">
				<input type="hidden" id="threeMust" value="${threeMust_value}">
				<div class="title" style="width:120px;">
					<spring:message code="BargainProjectRule.threeMust"/>
				</div>
				<div class="supply" style="width:400px">
					<spring:message code="BargainProjectRule.threeMust_desc"/>
				</div>
				<div class="state">
				   <span class="over hidden" id="threeMust_span">
				   		<c:if test="${threeMust_value == '0'}">
							 不限定
						</c:if>
						<c:if test="${threeMust_value == '1'}">
							 至少三家
						</c:if>
				   </span>
				</div>
				<div class="operate">
				<c:choose>
					<c:when test="${!empty threeMust_value}">
				    	<input name="threeMust" id="threeMust_1" type="radio" 
				    		<c:if test="${threeMust_value == '0'}">
								checked="checked"
							</c:if> 
				    	value="0" title="不显示"/>不限定
						<input name="threeMust" id="threeMust_2" type="radio"
							<c:if test="${threeMust_value == '1'}">
								checked="checked"
							</c:if> 
						value="1" title="至少三家"/>至少三家
					</c:when>
					<c:otherwise>
						<input name="selfRanking" id="threeMust_1" type="radio" value="0" title="不显示"/>不显示
						<input name="selfRanking" id="threeMust_2" type="radio" checked="checked" value="1" title="显示"/>显示
					</c:otherwise>
				</c:choose>
				</div>
			</li>
			
			
			
		</ul>
	    </div>
		
		<div class="conOperation">
			<button id="save" type="button"><span>保存</span></button>
		</div>
</form>

<ul class="hidden safe-info" id="tempUL">
	<li class="item identity temp">
		<input type="hidden" name="objId1" id="objId1" value=""/>
		<input type="hidden" name="turnNo1" id="turnNo1" value="1"/>
		<div class="title" style="width:120px;">
			<label>第<b id="1">1</b>轮：</label>
		</div>
		<div class="supply" style="width:400px">
			<span><input type="text" name="startTime1" style="width:150px;" id="startTime1" class="required" value="" readonly="readonly"/></span>
			至
			<span><input type="text" name="endTime1" style="width:150px;" id="endTime1" class="required" value="" readonly="readonly"/></span>
		</div>
		
		<div class="supply" style="width:220px">
			<label>降幅：</label>
			<span>
	        <input name="totalCutType" id="totalCutType" type="radio" checked="checked" value="01" />
			<input name="totalCut" id="totalCut" type="text" maxlength="2" class="digits" style="width:20px" value="0"/>%
			<input name="totalCutType" id="totalCutType" type="radio" value="02"/>
			<input name="totalCut_" id="totalCut_" type="text" class="money" style="width:40px" value="0.0"/>元		
			</span>
		</div>
		
		<div class="state">
			<span class="over"></span>
		</div>
		<div class="operate">
			<span id="delTurn1" class="sysicon siDelete"><a class="tempa" href="javascript:void(0);">删除</a></span>
			<span id="addTurn1" class="sysicon siAdd"><a href="javascript:BargainProjectModifyForm.cloneTurn();">增加</a></span>
		</div>
	</li>
</ul>
