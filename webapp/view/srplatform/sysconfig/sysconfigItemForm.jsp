<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/scripts/jquery/epsSelect/jquery.select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/sysconfig/sysconfigItemForm.js"></script>
<input type="hidden" id="parent_tree_path_code" value="${parentTreePathCode}"></input>
<form id="sysConfigTypeItemForm" method="post">
	<input type="hidden" name="objId" id="objId" value="${sysConfigItem.objId}"/>
	<input type="hidden" name="sysConfigType.objId" id="sysConfigType.objId" class="required" value="${sysconfigTypeId}"/>
    <input type="hidden" name="itemPath" id="itemPath"  value="${sysConfigItem.itemPath}"/>
    <input type="hidden" id="data_type" name="data_type" value="${sysConfigItem.dataType}"/>
    <input type="hidden" name="data_num"></input>
    <div class="formLayout">
	    <h5><span>新增系统配置条目信息</span></h5>
	    <table>
	    		<tr>
	       		<th><label><spring:message code="sysConfigItemForm.name"/>:</label></th>
	       		<td><input type="text" name="name" id="name" class="required" value="${sysConfigItem.name}" style="width: 200px"/>
	       		<span class="eleRequired">*</span></td>
	       </tr>
	       <tr>
	       		<th><label><spring:message code="sysConfigItemForm.code"/>:</label></th>
	       		<td><input type="text" name="code" id="code" class="required" value="${sysConfigItem.code}" style="width: 200px"/>
	       		<span class="eleRequired">*</span></td>
	       </tr>
	       <tr>
	       		<th><label><spring:message code="sysConfigItemForm.dataType"/>:</label></th>
	       		<td><select id="dataType" name="dataType" style="width: 200px">
					<option value="String">文本类型</option>
					<option value="trst">枚举类型</option>
					<option value="number">数字类型</option>
					<option value="Boolean">布尔类型</option>
					<option value="Date">日期类型</option>
				</select>
				</td>
	       </tr>
	       </table>
       </div>
       <div class="formLayout" id="preValueTable">
	       <table>
	       		<tr>
			        <th class="name">预设名称</th>
			        <th class="name">预设值</th>       
			        <th class="name">操作</th>       
				</tr>
				<tbody id="preValuesTable">
					<c:set var="i" value="0"/>
					<c:forEach items="${sysConfigItemPreValueList}" var="sysConfigItemPreValue">
						<tr num="${i}">
							<td><input class="required" name="name${i}" value="${sysConfigItemPreValue.name}"></input><span class="eleRequired">*</span>
								<input type="hidden" name="objId${i}" value="${sysConfigItemPreValue.objId}"></input>
							</td>
							<td><input class="required" name="code${i}" value="${sysConfigItemPreValue.code}"></input><span class="eleRequired">*</span></td>
							<td><button onClick="deletePreValue(this);" class="sysicon siDelete" type="button" title="删除"><span>删除</span></button></td>
						</tr>
						<c:set var="i" value="${i+1}"/>
					</c:forEach>
					<input type="hidden" id="num" value="${i}"></input>
				</tbody>
	     </table>
     </div>
     <div class="formLayout">
	     <table>
	       <tr>
	       		<th><label><spring:message code="sysConfigItemForm.notes"/>:</label></th>
	       		<td><textarea name="notes" id="notes" class="required" style="width: 95%;height: 35px">${sysConfigItem.notes}</textarea>
	      			<span class="eleRequired">*</span></td>
	       </tr>
	    </table>
    </div>
</form>
<div class="conOperation">
	<button id="sysConfigItemSave" type="button" ><span><spring:message code="globe.save"/></span></button>
    <button id="sysConfigItemReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
</div>