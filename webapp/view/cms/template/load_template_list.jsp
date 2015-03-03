<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/cms/template/load_template_list.js"></script>
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
	
<div class="treeRight frameSub">
	<div id="templateFileListDiv">
	<flex:flexgrid checkbox="true"
		id="templateFileGrid" url="TemplateFileController.do?method=list" queryColumns="path,isLeaf"  
			 rp="10" title="自定义引用模板"
			onSubmit="templateFileList.before" onSuccess="templateFileList.success" >
					<flex:flexCol name="fileIco" display="类型" sortable="true" width="20"align="center"></flex:flexCol>
					<flex:flexCol name="name" display="templateFileForm.name" sortable="true" width="400"align="left"></flex:flexCol>
					<flex:flexCol name="fileSize" display="大小(KB)" sortable="true" width="100"align="right"></flex:flexCol>
					<flex:flexCol name="modifyTime" display="修改时间" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="新增目录" bclass="read" onpress="templateFileList.addTemplateDirectory"></flex:flexBtn>	
		<flex:flexBtn name="新增模板" bclass="pageExcel" onpress="templateFileList.addTemplateFile"></flex:flexBtn>	
		<flex:flexBtn name="重命名" bclass="resetPwd" onpress="templateFileList.renameDirectoryOrFileName"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="templateFileList.remove"></flex:flexBtn>	
	</flex:flexgrid>
	</div>
	<div class="formLayout formPa eleHide"  id="templateFileContentDiv" style="width:99%;height:auto">
	<h5><span>自定义引用模板</span></h5>
	        <ul id="ChannelInputForm">
		     	<li>
		     		<label for="">模板名称：</label>
					<span id="templateFileName"></span>
	    	    </li>
		     	<li style="height:300px">
					<textarea name='fileContent' id='FCKContent_fileContent' class="" style="width:98%;height:300px"></textarea>
	    	    </li>
	    	</ul>
	  <div class="conOperation">
		<button type="button" id="saveFileContent"><span><spring:message code="globe.save"/></span></button>
		<button type="button" id="reloadFileContent" type="button" tabindex="19"><span><spring:message code="globe.reset"/></span></button>
		<button type="button" id="publicLoadTemplate" type="button" tabindex="19"><span>发布该模板</span></button>
	</div>
	</div>
	<div class="formLayout detail eleHide" id="templateFileContentDiv" >
		<div class="treeEditNav">
			<ul>
				<li id="addChannelDataRow" class="add"><a href="javascript:void(0)"><span>新增</span></a></li>
			</ul>
		</div>
     	<h5><span>栏目模板数据</span></h5>
     	<form id="templateDataForm" method="post">
	<table>
	<thead>
    	<tr>
		<th style="text-align:center">数据来源</th>
		<th style="text-align:center">名称</th>
		<th style="text-align:center">查询条数</th>
		<th style="text-align:center">排序</th>
		<th style="text-align:center">查询</th>
		<th style="text-align:center">操作</th>
		</tr>
	</thead>
	<tbody id="ChannelDataList">
	
	</tbody>
		</table>
		</form>
	</div>
	
 </div>
</div>