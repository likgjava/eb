<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/purchase_file_down.js"></script>
<div class="formLayout">
<div class="partContainers operationLog"><h5 id="fileDown"><span class="switch  left11">下载<dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></h5>	</div>
<div id="fileDownDetail">
<flex:flexgrid id="fileGrid" title="" usepager="false" showTableToggleBtn="true" url="view/esdemo/projectmanager/data/purchaseFileList.txt" queryColumns="" onSubmit="list.before" onSuccess="list.success"  height="80">
		<flex:flexCol name="num" display="序号" width="150" sortable="true"></flex:flexCol>
		<flex:flexCol name="file" display="下载文件(文件类别)" width="150" sortable="true"></flex:flexCol>
		<flex:flexCol name="size" display="文件大小" width="150" sortable="true"></flex:flexCol>
		<flex:flexCol name="type" display="文件类型" width="150" sortable="true"></flex:flexCol>
		<flex:flexCol name="option" display="操作" width="150" sortable="true"></flex:flexCol>
</flex:flexgrid>
 <div class=" systemAlert">
<p class="secondary-tips">支持断点续传，支持迅雷、flashget等下载工具</p>
</div>
</div>
</div>

 