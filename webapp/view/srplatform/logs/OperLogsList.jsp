<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<input type="hidden" name="onlineUserId" value="<c:out value="${param.onlineUserId}"/>">
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/logs/OperLogsList.js"></script>
	<div class="conSearch">
	  <h4><span>查询</span></h4>
	  <form id="operLogsSearchZone" >
		  <ul>
		  	<li>
		      <label>用户名:</label>
		      <input type="text" name="user.usName" value="">
			  <input type="hidden" name="user.usName_op" value="like">
		    </li>
		    <li>
		      <label>开始时间:</label>
		      <input type="text" name="startTime" id="startTime" class="sysicon siDate" readonly="readonly" />
		    </li>
		    <li>
		      <label>结束时间:</label>
			  <input type="text" name="endTime" id="endTime" class="sysicon siDate" readonly="readonly" />
		    </li>
		    <li>
		      <label>业务方法:</label>
			  <select name="methodName" id="methodName">
              	<option value="">全部</option>
              	<option value="saveBuyerOfRegister">采购人注册</option>
              	<option value="saveSupplierOfRegister">供应商注册</option>
              	<option value="saveRequirement">创建采购需求</option>
              	<option value="saveRequirementReg">供应商对采购需求报名</option>
	          </select>
		    </li>
		    <li>
		      <label>过滤IP:</label>
		      <select name="logIp" id="logIp">
              	<option value="0:0:0:0:0:0:0:1,127.0.0.1,219.239.33.95,124.207.131.86,172.16.7.,124.42.69.,192.168.,202.85.219.168">全部</option>
              	<option value="0:0:0:0:0:0:0:1">0:0:0:0:0:0:0:1</option>
              	<option value="127.0.0.1">127.0.0.1</option>
              	<option value="219.239.33.95">219.239.33.95</option>
              	<option value="124.207.131.86">124.207.131.86</option>
              	<option value="172.16.7.">172.16.7.*</option>
              	<option value="124.42.69.">124.42.69.*</option>
              	<option value="192.168.">192.168.*.*</option>
              	<option value="202.85.219.168">192.168.*.*</option>
	          </select>
		    </li>
		    <li class="operationBtnDiv">
		      <button type="submit"><span><spring:message code="globe.query"/></span></button>
		    </li>
		  </ul>
	  </form>
	</div>
		<flex:flexgrid
			id="operLogsGrid" url="OperLogsController.do?method=list" queryColumns="user.usName,logResName,logUsrIP,logVisitDate,methodName"  
				searchZone="operLogsSearchZone" rp="10" title="操作日志列表"    checkbox="true"
				onSubmit="operLogsList.before" onSuccess="operLogsList.success">
			<flex:flexCol name="user.usName" display="用户名" sortable="true" width="150"align="left"></flex:flexCol>
			<flex:flexCol name="logUsrIP" display="IP" sortable="true" width="150"align="left"></flex:flexCol>
			<flex:flexCol name="logVisitDate" display="操作时间" sortable="true" width="150"align="left"></flex:flexCol>
			<flex:flexCol name="logResName" display="类名" sortable="true" width="150"align="left"></flex:flexCol>
			<flex:flexCol name="methodName" display="方法名" sortable="true" width="150"align="left"></flex:flexCol>
			<flex:flexBtn name="删除" bclass="delete" onpress="operLogsList.remove"></flex:flexBtn>	
		</flex:flexgrid>
