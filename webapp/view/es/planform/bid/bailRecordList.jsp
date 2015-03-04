<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/bailRecordList.js"></script>
<div class="partContainers">
	<form id="bailRecordSearchZone">
		<div class="conSearch">
			
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li>
				<label for="bailRecordSearchZone.renderTime">交纳时间</label>
					<input type="text" name="renderTime" id="renderTime" class="required" 
									readonly="readonly"
						      />—<input type="text" name="renderTime2" id="renderTime2"  class="required" 
									readonly="readonly"
						      />
				</li>
				<li>
				<label for="bailRecordSearchZone.returnedTime">退回时间</label>
					<input type="text" name="returnedTime" id="returnedTime" class="required" 
									readonly="readonly"
						      />—<input type="text" name="returnedTime2" id="returnedTime2"  class="required" 
									readonly="readonly"
						      />
				</li>
				<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="bailRecordGrid" url="BailRecordController.do?method=toBailRecordList" queryColumns=""  
			searchZone="bailRecordSearchZone" rp="10" title="保证金记录表" 
			onSubmit="bailRecordList.before" onSuccess="bailRecordList.success">
					<flex:flexCol name="ballMoney" display="bailRecordForm.ballMoney" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="renderTime" format="date" display="bailRecordForm.renderTime" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="returnedTime"  format="date" display="bailRecordForm.returnedTime" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="bailStatus"   alias="bailStatusCN" display="bailRecordForm.bailStatus" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="bailRecordList.add"></flex:flexBtn>	
	</flex:flexgrid>
</div>	







 

