<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/sysconfig/sysconfigitemList.js"></script>
	<div class="conSearch">
		<form id="sysConfigItemSearchZone" >
			<ul>
			<li>
				<label><spring:message code="sysConfigItemForm.name" />:</label>
		      	<input type="text" name="name" value="">
			  	<input type="hidden" name="name_op" value="like">
			</li>
			<li>
				<label><spring:message code="sysConfigItemForm.code" />:</label>
		      	<input type="text" name="code" value="">
			  	<input type="hidden" name="code_op" value="like">
			</li>
				
			  	
			  	<li class="operationBtnDiv">
				<button><span><spring:message code="globe.query"/></span></button>
				</li>
			</ul>
		</form>
	</div>
	<flex:flexgrid checkbox="true"
		id="sysConfigItemGrid" url="SysConfigController.do?method=getSysConfigItemForPage&sysConfigType.objId=${sysConfigTypeId}" queryColumns=""  
			searchZone="sysConfigItemSearchZone" rp="10" title="系统配置条目"  
			onSubmit="sysConfigItemList.before" onSuccess="sysConfigItemList.success">
					<flex:flexCol name="name" display="sysConfigItemForm.name" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="code" display="sysConfigItemForm.code" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="dataType" display="sysConfigItemForm.dataType" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="notes" display="sysConfigItemForm.notes" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexBtn name="globe.new" bclass="add" onpress="sysConfigItemList.add"></flex:flexBtn>	
					<flex:flexBtn name="新增待办任务条目" bclass="sysicon report_go" onpress="sysConfigItemList.initSysConfigItem"></flex:flexBtn>
					<flex:flexBtn name="批量删除" bclass="delete" onpress="sysConfigItemList.remove"></flex:flexBtn>	
					<flex:flexBtn name="导出类" bclass="sysicon siDownBtn" onpress="sysConfigItemList.exportStaticClass"></flex:flexBtn>
					<flex:flexBtn name="导出文件" bclass="sysicon siDownGray" onpress="sysConfigItemList.exportPropertysFile"></flex:flexBtn>
					<flex:flexBtn name="导出SQL" bclass="sysicon report_go" onpress="sysConfigItemList.exportSQL"></flex:flexBtn>
	</flex:flexgrid>
<button class="sysicon cog" title="配置条目数据" type="button"><span>配置条目数据</span></button>配置条目数据
<button class="sysicon siModify" title="修改" type="button"><span>修改</span></button>修改
<button class="sysicon siDelete" title="删除" type="button"><span>删除</span></button>删除
<button class="sysicon siDownBtn" title="导出类" type="button"><span>导出类</span></button>导出类
<button class="sysicon siDownGray" title="导出文件" type="button"><span>导出文件</span></button>导出文件
<button class="sysicon report_go" title="导出SQL" type="button"><span>导出SQL</span></button>导出SQL