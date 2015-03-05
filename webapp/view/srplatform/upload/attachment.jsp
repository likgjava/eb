<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@page import="com.gpcsoft.core.context.FrameBeanFactory"%>
<%@ page language="java" import="com.gpcsoft.srplatform.auth.domain.Attachment,java.text.SimpleDateFormat,com.gpcsoft.srplatform.auth.service.AttachmentService,com.gpcsoft.core.utils.locator.ServiceLocator,java.util.*,com.gpcsoft.bizplatform.base.common.util.StringUtils"%>
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
	String isSingle = request.getParameter("isSingle");//是否只能增加一个附件,是：yes，否：no
	if(isSingle == null || "".equals(isSingle.trim()) || "null".equals(isSingle.trim())){ 
		isSingle = "no";//默认可以增加多个   		
	}
	
	String fileType = request.getParameter("fileType");//要求附件格式串，以逗号分开，如：doc,xml,txt
	if(fileType == null || "".equals(fileType.trim()) || "null".equals(fileType.trim())){ 
		fileType = "all";//表示所有的文件格式都可以上传   		
	}

	String defineSelf = request.getParameter("defineSelf");//与哪块连接可能不是用字段attachRelaId，就在此定义
	if(defineSelf == null || "".equals(defineSelf.trim()) || "null".equals(defineSelf.trim())){
		defineSelf = "attachRelaId";
	}
	String isSelect = request.getParameter("isSelect");//是否仅查看附件列表,是：yes 否：no,默认为no
	if(isSelect == null || "".equals(isSelect.trim()) || "null".equals(isSelect.trim())){
		isSelect = "no";
	}	
	String idName = request.getParameter("idName");
	if(idName == null || "".equals(idName.trim()) || "null".equals(idName.trim())){
		idName = "abcdefg";
	}
	String noScript = request.getParameter("noScript");
	if(noScript == null || "".equals(noScript.trim()) || "null".equals(noScript.trim())){
		noScript = "";
	}
	String maxSize = request.getParameter("maxSize");
	if(maxSize == null || "".equals(maxSize.trim()) || "null".equals(maxSize.trim())){
		maxSize = "";
	}
	String buttonName = request.getParameter("buttonName");
	if(buttonName == null || "".equals(buttonName.trim()) || "null".equals(buttonName.trim())){
		buttonName = "管理附件";
	}else {
		buttonName = StringUtils.ascii2Native(buttonName);
	}
%>

<table class="tableList" id="attachments_show_area">
	<tbody>
	<tr>
             <th>附件名称</th>
             <th>上传时间</th>
    </tr>
    <%for(int i=0;i<attachmentList.size();i++){
				 		Attachment attachment = (Attachment)attachmentList.get(i);
				 		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 		String createTime = date.format(attachment.getUploadTime());
				 	%>
				 	<tr id="<%=attachment.getObjId() %>">
				 		<td><a href="javascript:void(0);" onclick="showAttach_2('<%=attachment.getObjId() %>');"><%=attachment.getViewName() %></a></td>
				 		<td><%=createTime %></td>
				 	</tr>
				 	<%} %>
	
	</tbody>
</table>
    <%if(isSelect.equals("no")){%>
		    <button type="button" onclick="showAttach_1(this)"><span><%=buttonName %></span></button>
		    <input type="hidden" name="isSingle" value="<%=isSingle%>" id="_isSingle"/>
		    <input type="hidden" name="fileType" value="<%=fileType%>" id="_fileType"/>
		    <input type="hidden" name="<%=defineSelf %>" value="<%=attachRelaID%>" id="_attachRelaID"/>
		    <input type="hidden" name="maxSize" value="<%=maxSize%>" id="_maxSize"/>
	<%}%>

<% if(!"true".equals(noScript)){ %>
<script language="javascript">	
function showAttach_1(obj){	
    var isSingle =$("#_isSingle").val();	
    var fileType =$("#_fileType").val();
	var attachRelaID=$("#_attachRelaID").val();
	var maxSize=$("#_maxSize").val();	
	var left=screen.width/3;
	var futher="center: Yes;dialogLeft:"+left+"; help: No; scroll:YES; resizable: No; status: No;"+"dialogHeight: "+500+"px; dialogWidth:"+750+"px;";	 

	var strURL = $('#initPath').val()+"/AttachmentController.do?method=toUpload&attachRelaID="+attachRelaID+"&isSingle="+isSingle+"&fileType="+fileType+"&maxSize="+maxSize;

	/*
	*/
	$.epsDialog({
		id:"uploadFile",
		title:"上传文件",
		url:strURL+"&divId=uploadFile"
	})
	
	/*
	//等待弹出页面返回值
	var attachmentArray = window.showModalDialog(strURL,"new",futher);
	var tableHTML ="";
	var tableHTML ="<tbody>";
	tableHTML += "<tr><th>附件名称</th><th>上传时间</th></tr>";
	if((JSON.stringify(attachmentArray[0])).length > 2){
		for(var i=0;i<attachmentArray.length-1;i++){
			var attachment = attachmentArray[i];
			tableHTML += "<tr><td align='left'>";
			tableHTML += "<a href=\"javascript:showAttach_2(\'"+attachment.id+"\')\">"+attachment.viewName+"</a></td>";
			tableHTML += "<td align='center'>"+attachment.createTime+"</td>";
		}
		obj.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.value = attachmentArray[attachmentArray.length-1];
	}
	tableHTML += "</tbody>";
	$('#attachments_show_area').html(tableHTML);
	*/
}

function showAttach_2(attachmentId){
	window.open($('#initPath').val()+"/AttachmentController.do?method=downLoadFile&objId="+attachmentId);
}
</script>
<% } %>