<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<%@page import="com.gpcsoft.srplatform.auth.service.ExtAttachmentService"%>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/upload/extAttachmentAjaxFile.js"></script>
<%@page import="com.gpcsoft.core.context.FrameBeanFactory"%>
<%@ page language="java" import="com.gpcsoft.srplatform.auth.domain.Attachment,java.text.SimpleDateFormat,com.gpcsoft.srplatform.auth.service.AttachmentService,com.gpcsoft.core.utils.locator.ServiceLocator,java.util.*"%>
<%
	//获得接口
	ExtAttachmentService extAttachmentService = (ExtAttachmentService)FrameBeanFactory.getBean("extAttachmentServiceImpl");
    List attachmentList=new ArrayList();
	String attBizId = request.getParameter("attBizId");//附件业务主键
	String itemId = request.getParameter("itemId");//系统配置条目主键
	if(attBizId == null || "".equals(attBizId.trim())|| "null".equals(attBizId.trim())){
		attBizId = "";//默认状态
	}else{
		attachmentList = extAttachmentService.getAttachmentsByAttBizIdAndItemId(attBizId,itemId); //得到该块对应的附件列表，可以对其维护 
	} 
	String defineSelf = request.getParameter("defineSelf");//与哪块连接可能不是用字段attachRelaId，就在此定义
	if(defineSelf == null || "".equals(defineSelf.trim()) || "null".equals(defineSelf.trim())){
		defineSelf = "attachRelaId";
	}
	String maxSize = request.getParameter("maxSize");
	if(maxSize == null || "".equals(maxSize.trim()) || "null".equals(maxSize.trim())){
		maxSize = "";
	}
%>
    
<div>
	<!-- 是否只读 -->
	<input type="hidden" id="AAFReadOnly" value="<%=request.getParameter("readOnly")%>"></input>
	<!-- 允许上传文件数 -->
	<input type="hidden" id="AAFAllowUploadFileCount" value="<%=(request.getParameter("allowUploadFileCount"))%>"></input>
	<span style="color: red"><%=(request.getParameter("allowUploadFileCount") != null ? "温馨提示：只允许上传" + request.getParameter("allowUploadFileCount") + "个附件。": "")%></span>
	<form id='AAFForm_one' method="post" enctype="multipart/form-data" style="display: hidden">
		<input type="file" name="attachFile" id="extFile_one" />
        <input type="hidden" name="viewName"/>
        <a id="AAFUpload" href="#" onclick="javascript:ExtAttachmentAjaxFile.upload();"  class="linkButton" title="点击上传该附件"><span>上传</span></a>
    	<input type="hidden" name="attBizId" value="<%=attBizId%>"/>
	    <input type="hidden" name="maxSize" value="<%=maxSize%>"/>
	    <input type="hidden" name="itemId" value="<%=itemId%>"/>
	    <input type="hidden" name="attBizType" value="<%=request.getParameter("attBizType")%>"/>
	</form>	

   	<ul id="extAtta_display_ul" style="line-height: 28px; margin-top: 10px">
		<%	
			for(int i=0;i<attachmentList.size();i++){
	 		Attachment attachment = (Attachment)attachmentList.get(i);
	 	%>
	 		<li id='<%=attachment.getObjId() %>'><a href="#" onclick="javascript:ExtAttachmentAjaxFile.show('<%=attachment.getObjId() %>')" title="点击下载" class="abtn"><%=attachment.getViewName() %></a> 
		 		<c:if test="${'yes'!=param.readOnly}">
		 			<span onclick="javascript:ExtAttachmentAjaxFile.del('<%=attachment.getObjId() %>')" class="sysicon siDelete"  title="点击删除该附件">删除</span>
		 		</c:if>
	 		</li>
	 	<%} %>
	</ul>
</div>	 	