<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/proofOpinionList.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
  <div id="proofOpinionView">
   <input type="hidden" id="projectId" name="projectId" value="${param.projectId}"/>
   <input type="hidden" id="isProof" value="${isProof}"/>
	<form id="proofOpinionSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
			    <li><label><spring:message code="proofOpinionForm.name"/></label>
					<input type="text" name="name"  >
					<input type="hidden" name="name_op" value="like"/>
				</li>
				<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
		
	</form>
	<flex:flexgrid 
		id="proofOpinionGrid" url="ProofOpinionController.do?method=list" queryColumns=""  
			searchZone="proofOpinionSearchZone" rp="10" title="招标文件论证" 
			onSubmit="proofOpinionList.before" onSuccess="proofOpinionList.success">
			<flex:flexCol name="name" display="proofOpinionForm.name" sortable="true" width="100"align="left"></flex:flexCol>
			<flex:flexCol name="phone" display="proofOpinionForm.phone" sortable="true" width="100"align="left"></flex:flexCol>
			<flex:flexCol name="opinion" display="proofOpinionForm.opinion" sortable="true" width="100"align="left"></flex:flexCol>
			<flex:flexBtn name="globe.new" bclass="add" onpress="proofOpinionList.add"></flex:flexBtn>				
			<flex:flexBtn name="录入论证结果" bclass="look" onpress="proofOpinionList.look"></flex:flexBtn>	
	</flex:flexgrid>
 </div>