<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/recordFormList.js"></script>
</head>
<body>	  

	<form id="recordFormSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li>
			        <label><spring:message code="recordFormForm.engineeringName"/>：</label>
				  	<input name="engineeringName" type="text" >	
				  	<input name="engineeringName_op" type="hidden" value="like">	
		        </li>
				<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>
<div id="epsTabs">
	<ul>
	  	<li>
	      <a href="#recordFormInfo" id = "tabs_toTemp" class="refreshData"><span>草稿</span></a>
	    </li>
	    <li>
	      <a href="#recordFormInfo" id = "tabs_toWait" class="refreshData"><span>待审核</span></a>
	    </li>
	    <li>
	      <a href="#recordFormInfo" id = "tabs_toBack" class="refreshData"><span>被退回</span></a>
	    </li>
	    <li>
	      <a href="#recordFormInfo" id = "tabs_toFormal" class="refreshData"><span>已审核</span></a>
	    </li>
	   <li>
	    <a href="#recordFormInfo" id = "tabs_aleradyCreateHistory" class="refreshData"><span>查看历史</span></a>
	    </li>
   </ul>
     <div id="recordFormInfo">
	<flex:flexgrid checkbox="true"
		id="recordFormGrid" url="RecordFormController.do?method=list&useStatus=00" queryColumns=""  
			searchZone="recordFormSearchZone" rp="10" title="登记表" height="308" 
			onSubmit="recordFormList.before" onSuccess="recordFormList.success">
					<flex:flexCol name="recFormBuyMethod" alias="recFormBuyMethodCn"  display="recordFormForm.recFormBuyMethod" sortable="true" width="100"align="center"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCn" display="recordFormForm.ebuyMethod" sortable="true" width="100"align="center"></flex:flexCol>
					<flex:flexCol name="engineeringName" display="recordFormForm.engineeringName" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="recFormAddr" display="recordFormForm.recFormAddr" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="projNumber" display="工程个数" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="investent" display="投资金额（元）" format="money" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="recordFormList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="recordFormList.remove"></flex:flexBtn>	
	</flex:flexgrid>
	</div>
</div>
</body>
</html>
