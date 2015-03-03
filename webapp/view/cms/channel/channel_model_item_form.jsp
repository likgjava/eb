<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/channel/channel_model_item_form.js"></script>
<div class="formLayout form2Pa">
<form:form id="channelModelItemForm" method="post" modelAttribute="channelModelItem">
		<form:hidden path="objId"/> 
     	<h5><span>栏目模型明细</span></h5>
     	<ul>
	     	<li>
	     		<label for="channelModelItemForm.name" style="width:37%"><spring:message code="channelModelItemForm.name"/>：</label>
					${channelModelItem.name}
    	    </li>
	     	<li>
	     		<label for="channelModelItemForm.label" style="width:37%"><spring:message code="channelModelItemForm.label"/>：</label>
					${channelModelItem.label}
    	    </li>
	     	<li>
	     		<label for="channelModelItemForm.dataType" style="width:37%"><spring:message code="channelModelItemForm.dataType"/>：</label>
					<select name="dataType" id="dataType">
					<option value="String">文本</option>
					<option value="Date">日期</option>
					<option value="Integer">整数</option>
					<option value="Boolean">布尔</option>
					</select>
    	    </li>
	     	<li>
	     		<label for="channelModelItemForm.formType" style="width:37%"><spring:message code="channelModelItemForm.formType"/>：</label>
					<select name="formType" id="formType" >
					<option value="text">text</option>
					<option value="textarea">textarea</option>
					<option value="select">select</option>
					<option value="radio">radio</option>
					<option value="checkbox">checkbox</option>
					<option value="img">img</option>
					<option value="file">file</option>
					</select>
    	    </li>
	     	<li>
	     		<label for="channelModelItemForm.textLength" style="width:37%"><spring:message code="channelModelItemForm.textLength"/>：</label>
					<form:input path="textLength" cssClass="number"/>px
    	    </li>
	     	<li>
	     		<label for="channelModelItemForm.defaultValue" style="width:37%"><spring:message code="channelModelItemForm.defaultValue"/>：</label>
					<form:input path="defaultValue" cssClass=""/>
    	    </li>
	     	<li>
	     		<label for="channelModelItemForm.textareaRows" style="width:37%"><spring:message code="channelModelItemForm.textareaRows"/>：</label>
					<form:input path="textareaRows" cssClass="number"/>px
    	    </li>
	     	<li>
	     		<label for="channelModelItemForm.textareaCols" style="width:37%"><spring:message code="channelModelItemForm.textareaCols"/>：</label>
					<form:input path="textareaCols" cssClass="number" />%
    	    </li>
	     	<li class="fullLine">
	     		<label for="channelModelItemForm.keyVal" style="width:18%"><spring:message code="channelModelItemForm.keyVal"/>：</label>
					<form:hidden path="keyVal" />
	    	   		<table style="width: 350px">
					<thead>
						<tr>
							<th style="text-align:center">名称</th>
							<th style="text-align:center">值</th>
							<th style="text-align:center" style="width: 220px">操作&nbsp;<a href="javascript:void(0)" id="addKeyAndVal"  class="">添加键值</a></th>
						</tr>
					</thead>
					<tbody id="KeyAndValTB">
					</tbody>
					<tfoot id="keyValTemplate" class="hidden">
						<tr>
							<td><input type="text" style="width:80px" value=""/></td>
							<td><input type="text" style="width:80px" value=""/></td>
							<td><a href="javascript:void(0)" name="deleteKeyVal">删除</a></td>
						</tr>
					</tfoot>
					</table>
				
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="channelModelItemSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="closeChannelModelItem" tabindex="19""><span><spring:message code="globe.close"/></span></button>
				<button type="button" id="channelModelItemReset" type="button" tabindex="20""><span><spring:message code="globe.reset"/></span></button>
		   </div>
	</form:form>
</div>

	<div class="formTips attention">
      <ul>
          <li>键值为下拉框、复选框、单选框的键和值，用英文,号隔开</li>
      </ul>
    </div>