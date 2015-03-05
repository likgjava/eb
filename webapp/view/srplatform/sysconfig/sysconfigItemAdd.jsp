<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/scripts/jquery/epsSelect/jquery.select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/sysconfig/sysconfigItemAdd.js"></script>
		<div class="formLayout">
		<form id="sysConfigTypeItemForm" method="post">
			<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
			<input type="hidden" name="parent.objId" id="parent.objId" type="hidden" value="<c:out value='${parentObjId }'/>"/>
			<h4><span>新增系统配置条目信息</span></h4>
			<!-- 隐藏不显示的数据 -->
			<!-- 系统配置类型ID -->
			<input type="hidden" name="sysConfigType.objId" id="sysConfigType.objId" class="required" value="${sysconfigTypeId }"/>
		    <input type="hidden" name="itemPath" id="itemPath"  value=""/>
		    <ul>
	     		<li>
		       		<label><spring:message code="sysConfigItemForm.name"/>:</label>
		       		<input type="text" name="name" id="name" class="required" 
						      />
		       		<span class="eleRequired">*</span>
		       </li>
		       <li>
		       		<label><spring:message code="sysConfigItemForm.code"/>:</label>
		       		<input type="text" name="code" id="code" class="required" 
						      />
		       		<span class="eleRequired">*</span>
		       </li>
		       <li>
		       		<label><spring:message code="sysConfigItemForm.dataType"/>:</label>
		       		<select id="dataType" name="dataType">
						<option value="String">文本类型</option>
						<option value="List">枚举类型</option>
						<option value="number">数字类型</option>
						<option value="Boolean">布尔类型</option>
						<option value="Date">日期类型</option>
					</select>
		       </li>
		       <li id="preValueLi" style="display:none">
		       		<label>新增<spring:message code="sysConfigItemForm.preValues"/>:</label>
	       			<input type="text" name="preValueName" id="preValueName"   />
				    <input type="text" name="preValueCode" id="preValueCode"   />
				    
				    <button id="preValueAdd" type="button" ><span><spring:message code="globe.save"/></span></button>      		
		       		
		       </li>
		       <li id="preValuesLi" style="display:none">
		       		
		       		<table class="tableList"   >
		       			<caption><spring:message code="sysConfigItemForm.preValues"/>:</caption>
		       			<thead>
					      <tr>
					        <th class="name">预设名称</th>
					        <th class="name">预设值</th>       
					        <th class="name">操作</th>       
					      </tr>
					    </thead>
					    <tbody id="preValuesTable">
					    </tbody>
		       			
		       		</table>
		       </li>
		       
		       <li>
		       		<label><spring:message code="sysConfigItemForm.notes"/>:</label>
		       		<input type="text" name="notes" id="notes" class="required" 
						      />
		       		<span class="eleRequired">*</span>
		       </li>
			</ul>
			<div class="conOperation">
				<button id="sysConfigItemSave" type="button" ><span><spring:message code="globe.save"/></span></button>
		        <button id="sysConfigItemReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
		   </div>
		</form>
		</div>
