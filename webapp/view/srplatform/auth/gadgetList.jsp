<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/gadgetList.js"></script>
	<form id="gadgetSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li><label>名称:</label>
					<input type="text" name="name" value=""><input type="hidden" name="name_op" value="like">
				</li>
				 <li class="operationBtnDiv">
				      <button type="submit"><span><spring:message code="globe.search"/></span></button>
				      
				    </li>
			</ul>
		</div>
	</form>
	<flex:flexgrid showTableToggleBtn="true" usepager="true"
		id="gadgetGrid" url="GadgetController.do?method=list" queryColumns=""  
			searchZone="gadgetSearchZone" title="小工具列表"  rp="10" width="100%"
			onSuccess="gadgetList.success" >
		<flex:flexCol name="name" display="名称" width="100"></flex:flexCol>
		<flex:flexCol name="resource.name" sortable="true" display="资源"  align="left" width="100"></flex:flexCol>
		<flex:flexCol name="resource.url" display="URL" sortable="true" align="left" width="300"></flex:flexCol>
		<flex:flexCol name="descs" display="描述" width="160"></flex:flexCol>
		<flex:flexBtn name="新增" bclass="add" onpress="gadgetList.add"></flex:flexBtn>	
	</flex:flexgrid>

