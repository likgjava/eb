<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/baseData/dictionaryList.js"></script>

<form id="dictionarySearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li><label><spring:message code="dictionaryForm.dicName"/></label>
				<input type="text" name="dicName" id="dicName"/>
				<input type="hidden" name="dicName_op" value="like"/>
			</li>
			<li><label><spring:message code="dictionaryTypeForm.groupName"/></label>
				<input type="text" name="dicType.groupName" id="dicTypeGroupName"/>
				<input type="hidden" name="dicType.groupName_op" value="like"/>
			</li>
			<li><label><spring:message code="dictionaryForm.dicMemo"/></label>
				<input type="text" name="dicMemo" id="dicMemo"/>
				<input type="hidden" name="dicMemo_op" value="like"/>
			</li>
			<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
		</ul>
	</div>
</form>

<flex:flexgrid checkbox="true"
	id="dictionaryGrid" url="DictionaryController.do?method=list" queryColumns=""  
		searchZone="dictionarySearchZone" rp="10" title="数据字典" height="200" 
		onSubmit="dictionaryList.before" onSuccess="dictionaryList.success">
				<flex:flexCol name="dicName" display="dictionaryForm.dicName" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="dicValue" display="dictionaryForm.dicValue" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="dicType.groupName" display="dictionaryForm.dicType" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="dicCacheName" display="dictionaryForm.dicCacheName" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="dicUseCache" display="dictionaryForm.dicUseCache" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="dicMemo" display="dictionaryForm.dicMemo" sortable="true" width="100"align="left"></flex:flexCol>	
	<flex:flexBtn name="globe.new" bclass="add" onpress="dictionaryList.add"></flex:flexBtn>	
	<flex:flexBtn name="globe.newGroup" bclass="copyInfo" onpress="dictionaryList.addGroup"></flex:flexBtn>
	<flex:flexBtn name="globe.modify" bclass="modify" onpress="dictionaryList.update"></flex:flexBtn>	
	<flex:flexBtn name="globe.delete" bclass="delete" onpress="dictionaryList.remove"></flex:flexBtn>	
</flex:flexgrid>

