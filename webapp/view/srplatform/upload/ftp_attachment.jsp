<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/upload/ftp_attachment.js"></script>
<script type="text/javascript">

</script>

<%@page import="com.gpcsoft.core.context.FrameBeanFactory"%>
<%@ page language="java" import="com.gpcsoft.srplatform.auth.domain.Attachment,java.text.SimpleDateFormat,com.gpcsoft.srplatform.auth.service.AttachmentService,java.util.*"%>
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
    	<form id='form_ftp' method="post" >
    		<input type="file"  id="file_ftp" />
    		<input type="hidden" name="attachFile" />
            <label >附件名称：</label>
            <input type="text" name="viewName"/>
            <a id="go_ftp_upload" href="#"  class="linkButton"><span>上传</span></a>
	    	<input type="hidden" name="attachRelaID" value="<%=attachRelaID%>"/>
    	</form>
    </c:if>
    	<ul id="ftp_atta_display_ul">
			<%	
				for(int i=0;i<attachmentList.size();i++){
		 		Attachment attachment = (Attachment)attachmentList.get(i);
		 	%>
		 		<li id='<%=attachment.getObjId() %>'><a href="#" onclick="javascript:ftp_attachment.show('<%=attachment.getPath() %>/<%=attachment.getFileName() %>');return false;" ><%=attachment.getViewName() %></a> 
		 		<c:if test="${'yes'!=param.readOnly}">
		 			<span onclick="javascript:ftp_attachment.del('<%=attachment.getObjId() %>')" class="sysicon siDelete">删除</span>
		 		</c:if>
		 		</li>
		 	
		 	<%} %>
 		</ul>
 	
	
   

