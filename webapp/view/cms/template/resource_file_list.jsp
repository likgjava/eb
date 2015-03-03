<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/template/resource_file_list.js"></script>
<div class="treePage frameMainSub frameMs12 fullScreen ">
	<div class="treeOutside frameMain">
	    <div class="treeBtn">
		      	<ul>
		            <li><button type="button" id="reflushTemplateFile" class="">刷新目录</button></li>
		        </ul> 		
		</div>
	  	<div id="templateFileTree" class="treeContentDiv"></div>
		<div class="treeResize" ></div>
	</div>
	
<!--Form表单div开始-->
<div class="treeRight frameSub">
	<div id="templateFileListDiv">
	<flex:flexgrid checkbox="true"
		id="templateFileGrid" url="TemplateFileController.do?method=list" queryColumns="path,isLeaf"  
			 rp="10" title="模板"
			onSubmit="templateFileList.before" onSuccess="templateFileList.success" >
					<flex:flexCol name="fileIco" display="类型" sortable="true" width="20"align="center"></flex:flexCol>
					<flex:flexCol name="name" display="templateFileForm.name" sortable="true" width="200"align="left"></flex:flexCol>
					<flex:flexCol name="fileSize" display="大小(KB)" sortable="true" width="100"align="right"></flex:flexCol>
					<flex:flexCol name="modifyTime" display="修改时间" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="新增目录" bclass="read" onpress="templateFileList.addTemplateDirectory"></flex:flexBtn>	
		<flex:flexBtn name="上传资源" bclass="import" onpress="templateFileList.uploadFile"></flex:flexBtn>	
		<flex:flexBtn name="重命名" bclass="resetPwd" onpress="templateFileList.renameDirectoryOrFileName"></flex:flexBtn>	
		<flex:flexBtn name="查看路径" bclass="info" onpress="templateFileList.viewFilePath"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="templateFileList.remove"></flex:flexBtn>	
	</flex:flexgrid>
	</div>
	<div class="formLayout formPa eleHide"  id="templateFileContentDiv" style="width:99%;height:auto">
		<form id="templateFileForm" method="post">
			<input type="hidden" id="fileContent" name="fileContent" />
			<h5><span>栏目</span></h5>
			<ul id="ChannelInputForm">
				<li>
		     		<label for="">模板名称：</label>
					<span id="templateFileName"></span>
	    	    </li>
	    	</ul>
		</form>	
	</div>
	<div id="htmlEditor"></div>
	<div class="conOperation">
		<button type="button" id="saveFileContent"><span><spring:message code="globe.save"/></span></button>
		<button type="button" id="reloadFileContent" type="button" tabindex="19"><span><spring:message code="globe.reset"/></span></button>
	</div>
 </div>
</div>