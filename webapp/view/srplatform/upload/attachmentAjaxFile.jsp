<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/upload/attachmentAjaxFile.js"></script>
<%@page import="com.gpcsoft.core.context.FrameBeanFactory"%>
<%@ page language="java" import="com.gpcsoft.srplatform.auth.domain.Attachment,java.text.SimpleDateFormat,com.gpcsoft.srplatform.auth.service.AttachmentService,com.gpcsoft.core.utils.locator.ServiceLocator,java.util.*"%>
<%
	//获得接口
	AttachmentService attachmentService = (AttachmentService)FrameBeanFactory.getBean("attachmentServiceImpl");
    List attachmentList=new ArrayList();
	String attachRelaID = request.getParameter("attachRelaId");//与哪块连接?
	if(attachRelaID == null || "".equals(attachRelaID.trim())|| "null".equals(attachRelaID.trim())){
		attachRelaID = "";//默认状态
	}else{
		attachmentList = attachmentService.getAttachmentsByRealID(attachRelaID); //得到该块对应的附件列表，可以对其维护 
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
    
    <input type="hidden" name="<%=defineSelf %>" value="<%=attachRelaID%>"/>
    <c:if test="${'yes'!=param.readOnly}">
    	<form id='form_one' method="post" enctype="multipart/form-data">
    		<input type="file" name="attachFile" id="file_one" />
            <label >附件名称：</label>
            <input type="hidden" name="viewName"/>
            <a id="go_upload" href="#"  class="linkButton" title="点击上传该附件"><span>上传</span></a>
	    	<input type="hidden" name="attachRelaID" value="<%=attachRelaID%>"/>
		    <input type="hidden" name="maxSize" value="<%=maxSize%>"/>
    	</form>
    </c:if>
    	<ul id="atta_display_ul">
			<%	
				for(int i=0;i<attachmentList.size();i++){
		 		Attachment attachment = (Attachment)attachmentList.get(i);
		 	%>
		 		<li id='<%=attachment.getObjId() %>'><a href="#" onclick="javascript:attachmentAjaxFile.show('<%=attachment.getObjId() %>')" title="点击下载" class="abtn"><%=attachment.getViewName() %></a> 
		 		<c:if test="${'yes'!=param.readOnly || (param.isShowDelFileBtn !=null && 'yes'==param.isShowDelFileBtn)}">
		 		<span onclick="javascript:attachmentAjaxFile.del('<%=attachment.getObjId() %>')" class="sysicon siDelete"  title="点击删除该附件">删除</span>
		 		</c:if>
		 		</li>
		 	
		 	<%} %>
 		</ul>
 	
	
   

