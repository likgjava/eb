<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/expertrule/expertRule_drawn.js"></script>
<div class="formLayout form2Pa">        
	<form id="resultForm">
		<input type="hidden" name="subProjectId.objId" id="objId"  value="${projectId}">
		<div class="partContainers operationLog"><h5 id="reportResults"><span class="switch  left11">项目基本信息</span></h5></div>
    	<ul>
    		<li>
				<label  class="short" for="purchaseName">招标名称：</label>
				<span>${takeExpertRule.subProjectId.projName}</span>
			</li>
    		<li>
				<label  class="short" for="purchaseName">招标编号：</label>
				<span>${takeExpertRule.subProjectId.projCode}</span>
			</li>
    		<li class="fullLine">
				<label  class="short" for="purchaseName">采购方式：</label>
				<span>${takeExpertRule.subProjectId.ebuyMethodCN}</span>
			</li>
			<li class="fullLine" id="room_Arr_li">
                <label  class="short">评审室：</label>
                	<c:forEach items="${takeExpertRule.bidRooms}" var="room">
                	<input type="radio" id="bidRoom_${room.column_value}" name="roomArr" value="${room.column_value}" <c:if test="${meetRoom==room.column_name}">checked="checked"</c:if>/>${room.column_name}
                	</c:forEach>
                	<span class="eleRequired">*</span>
                  <input type="hidden" name="bidRoom" id="bidRoom_Id" value="${takeExpertRule.bidRoom}"/>
                	
            </li>
			<li>
				<label  class="short"  class="short" for="buyerDeptName">招标单位：</label>
				<input type="text" name="buyerNames" id="buyerNames_Id" value="${takeExpertRule.buyerNames}" class="required"/><span class="eleRequired">*</span>
				<input type="hidden" name="buyerNameIds" id="buyerNameIds_Ids" value="${takeExpertRule.buyerNameIds}"/>
			</li>
			<li>
				<label  class="short" for="buyerDeptName">回避单位：</label>
				<input type="text" name="outBuyerNames" id="outBuyerNames_Id" value="${takeExpertRule.outBuyerNames}" onfocus="sendSperviseInvitation.getUnit('回避单位','01');" class="required"/><span class="eleRequired">*</span>
				<input type="hidden" name="outBuyerIds" id="outBuyerIds_Id" value="${takeExpertRule.outBuyerIds}"/>
			</li>
			<li>
				<label  class="short" for="assembleTime">集合时间：</label>
				<input id="_applyDate" name="assembleTime" type="text" value='<fmt:formatDate value="${takeExpertRule.assembleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</li>
			<li>
				<label  class="short" for="assembleAddr">集合地点：</label>
				<input id="assemble_addr" name="assembleAddr" size="15" value="${takeExpertRule.assembleAddr}" maxlength="100"/>
			</li>
			 <li>
				<label  class="short" for="dateStartTime">评审开始时间：</label>
				<input  id="openBidStartDate" type="hidden" value="<fmt:formatDate value="${project.openBidStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
				<input id="_bid_starttime" name="bidStarttime" type="text"  value='<fmt:formatDate value="${takeExpertRule.bidStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>' class="required"/><span class="eleRequired">*</span>
			</li>
			<li>
				<label  class="short" for="dateStartTime">评审结束时间：</label>
				<input id="_bid_endtime" name="bidEndtime" type="text"  value='<fmt:formatDate value="${takeExpertRule.bidEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>' class="required"/><span class="eleRequired">*</span>
			</li>
    	</ul>
	<c:if test="${takeExpertRule.useStatus != '01'}">
    	<div class="conOperation" style="text-align:center" id="operBtn">
				<button type="button" id="SaveExpertRule" ><span>保存</span></button>
			<c:if test="${takeExpertRule.useStatus == '00'}">
				<button type="button" id="SubmitExpertRule"><span>提交</span></button>
				<button type="button" id="printExpertRule"><span>打印预览</span></button>
			</c:if>
		</div>
	</c:if>
    	<input type="hidden" name="useStatus" id="useStatusId" value="${takeExpertRule.useStatus}"/>
    	<input type="hidden" name="isSubRule" id="isSubRuleId" value="${takeExpertRule.isSubRule}"/>
		</form>
	</div>
<div id="expertConditionId"></div>
<div class="formLayout form2Pa" id="subExpertruleId" style="display: none">
<form id="subExpertruleForm">
    	<ul>
			 <li>
				<label  class="short" for="purchaseName">采购品目：</label>
				<%--<span>${takeExpertRule.purCategory.categoryName} </span>--%>
				<input type="text" name="purcategory.name" id="purcategory.name" value=""  readonly="readonly" class="required sysicon siSearch"/><span class="eleRequired">*</span>
				<input type="hidden" name="purcategory.objId" id="purcategory.objId" value="" class="purcategoryId"/>
			</li>
		  	<li class="fullLine">
               <label  class="short" for="expert_level_1">抽取类型：</label>
               <input type="radio" name="expertLevel" id="expert_level_1" checked="checked" value="1" >正常抽取
               	<input type="radio" name="expertLevel" id="expert_level_2"  value="2">只抽主评 
            </li>
			<li>
                <label for="specialty_year" class="short labelHead">专业工龄：</label>
                <input type="text" name="specialtyYear" id="specialty_year" style="width: 50px" value="" class="digits" min="1"/>年以上
            </li>
			<li class="fullLine">
                <label for="amount" class="labelHead short">所需人数：</label>
                	<input type="text" size="5" name="amount" id="amount" value="${takeExpertRule.amount}" class="required digits"/>正选<span class="eleRequired">*</span>
                	<input type="text" size="5" name="subAmount" id="sub_Amount" value="${takeExpertRule.subAmount}" class="digits"/>备选
                	
            </li>
              <li class="fullLine">
                <label  class="short">年龄：</label>
                <input type="text" id="expert_nlStart" name="ageAr" size="5" class="digits" min="1"/>至
                <input type="text" id="expert_nlEnd" name="ageArr" size="5" class="digits"/>岁
                <input type="hidden" name="age" id="ageId" value="${takeExpertRule.age}"/>
              </li>
            
             <li class="fullLine" id="city_codeArr_li">
             	<label  class="short">评审地域：</label>
                	<c:forEach items="${takeExpertRule.cityCodeName}" var="cityCode" varStatus="status"> 
                		<input type="checkbox" id="city_code_${cityCode.column_value}" name="city_codeArr" value="${cityCode.column_value}"/>${cityCode.column_name}  
                		<c:choose>
                			<c:when test="${(status.count%5)==0}"> <br/><label  class="short" style="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></c:when>
                		</c:choose>
                	</c:forEach>
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="全选" id="chooseAllCityCode"/>
                  <input type="hidden" name="cityCode" id="city_codeId" value="${takeExpertRule.cityCode}"/>
                  
              </li>
			 <li class="fullLine" id="expert_group_li">
                <label for="expert_type" class="labelHead short">专家类型：</label>
                
                <c:forEach items="${takeExpertRule.expertGroupName}" var="expertGroup">
                <input type="checkbox" id="expert_group_${expertGroup.column_value}" name="expert_groupArr" value="${expertGroup.column_value}"/>${expertGroup.column_name}
                </c:forEach>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="全选" id="chooseAllExpertGroup"/>
                <input type="hidden" name="expertGroup" id="expert_groupId" value="${takeExpertRule.expertGroup}"/>
              </li>
               <li class="fullLine" id="top_educ_li">
                <label class="short labelHead">学历：</label>
                 <c:forEach items="${takeExpertRule.topEducName}" var="topEduc">
                 	<input type="checkbox" id="top_educ_${topEduc.column_value}" name="top_educArr" value="${topEduc.column_value}"/>${topEduc.column_name}
                 </c:forEach>
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="全选" id="chooseAllTopEduc"/>
                  <input type="hidden" name="topEduc" id="top_educId" value="${takeExpertRule.topEduc}"/>
              </li>
		</ul>
              <input type="hidden" name="takeExpertRule.objId" id="takeExpertRuleId" value="${takeExpertRule.objId}"/>
              <input type="hidden" name="objId" id="takeExpertRuleItemId" value=""/>
			<div class="conOperation" style="text-align:center">
				<button type="button" id="createTakeCondition" ><span>保存</span></button>
				<button type="button" id="resetCondition" ><span>清空</span></button>
			</div>
	</form>
		</div>
              