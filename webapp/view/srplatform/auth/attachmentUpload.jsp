<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/resource/plug-in/jquery/jquery.js'></script>
<link rel="stylesheet"  type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/others/attachment.css"/>
<link rel="stylesheet"  type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/button.css"/>
<%@ page language="java" import="com.gpcsoft.srplatform.auth.domain.Attachment,java.text.SimpleDateFormat,com.gpcsoft.srplatform.auth.service.AttachmentService,com.gpcsoft.core.utils.locator.ServiceLocator,java.util.*"%>
<script language="javascript">	
	function delectAttachment(attachmentId,attachRelaID,isSingle,fileType){		
		if(confirm("确认要删除该附件吗？")){
			document.getElementById("attachmentId").value = attachmentId;
			document.getElementById("abc").value = attachRelaID;
			document.getElementById("cdf").value = isSingle;
			document.getElementById("fen").value = fileType;
			document.form1.submit();
		}
	}
	
	function submitForm(fileType){
		var fileName = document.getElementById("attachFile").value;
		if (!fileName ||(fileName=="")){
			alert("您还没有输入文件！");
			return false;
		}
		var viewName = document.getElementById("viewName").value;//显示名称
		if(viewName==""){
			var abs = fileName.split('\\');
			var sba = abs[abs.length-1];
			var sko = sba.split(".");
			document.getElementById("viewName").value = sko[0];
		}
 
		if(fileType!='all'){//不能输入全部的类型
			var fileNameArray = fileName.split(".");
			var exeName = fileNameArray[fileNameArray.length-1];//后缀名
			var fileTypeArray = fileType.split(",");
			var flag = "";
			for(var i=0;i<fileTypeArray.length;i++){
				if(exeName==fileTypeArray[i]){
					flag = "1";
				} 
			}
			if(flag=="1"){
				$('#attachmentForm').submit();
			}else{
				alert("您可以输入的格式为:"+fileType);
				return false;
			}
		} 

		$('#attachmentForm').submit();
	}
	
	function viewAttachment(attachmentId){
		//this.location = $('#initPath').val()+"/AttachmentController.do?method=downLoadFile&objId="+attachmentId;
		this.location = "AttachmentController.do?method=downLoadFile&objId="+attachmentId;
		
	}
	
	function attachmentClass(id,viewName,createTime){
		this.id = id;
		this.viewName = viewName;
		this.createTime = createTime;
	}
	
	function windowClose(attachRelaID){
		var idCount = document.getElementsByName("id").length;
		var attachmentArray = new Array(idCount+1);
		for(var i=1;i<=idCount;i++){
			var id = document.getElementById("id_"+i).value
			var viewName = document.getElementById("viewName_"+i).value;
			var createTime = document.getElementById("createTime_"+i).value;
			var attachment = new attachmentClass(id,viewName,createTime);
			attachmentArray[i-1] = attachment;
		}
		attachmentArray[attachmentArray.length-1] = attachRelaID;
		window.returnValue = attachmentArray;		
		window.close();
	}
	
	function ad(){
		var attachRelaID = $('input[name=attachRelaID]').val();
		var idCount = document.getElementsByName("id").length;
		var attachmentArray = new Array(idCount+1);
		for(var i=1;i<=idCount;i++){
			var id = document.getElementById("id_"+i).value
			var viewName = document.getElementById("viewName_"+i).value;
			var createTime = document.getElementById("createTime_"+i).value;
			var attachment = new attachmentClass(id,viewName,createTime);
			attachmentArray[i-1] = attachment;
		}
		attachmentArray[attachmentArray.length-1] = attachRelaID;
		window.returnValue = attachmentArray;	
	}
</script>
<base target="_self">
<body onbeforeunload="ad()">
<c:if test="${!(isSingle=='yes' && size=='1') }">
<form name="attachmentForm" id="attachmentForm" action="<%=request.getContextPath()%>/AttachmentController.do?method=upLoad" method="post" ENCTYPE="multipart/form-data">
  <table class="upFile">
    <thead>
	    <TR>
	        <TD>附件名称：<input type="text" id="viewName" class="txt" name="viewName"/>&nbsp;&nbsp;
	                                文件：<input type="file" name="attachFile" id="attachFile"/>&nbsp;&nbsp;
	            <input type="hidden" name="attachRelaID" value="<c:out value="${attachRelaID}"/>"/>
    			<input type="hidden" name="fileType" value="<c:out value="${fileType}"/>"/>
    			<input type="hidden" name="isSingle" value="<c:out value="${isSingle}"/>"/>                    
	            <input type="button" class="button" value="上传" onclick="submitForm('<c:out value="${fileType}"/>')"/>                    
			</TD>
	    </TR>
    </thead> 
  </table>
</form>
</c:if>

<form name="form1" action="<%=request.getContextPath()%>/AttachmentController.do?method=delectAttachment" method="post">
<TABLE class="upFile">
 <tr>
	 <td scope="col">
	  <input type="hidden" id="attachmentId" name="objId" value="">
	  <input type="hidden" id="abc" name="attachRelaID" value="<c:out value="attachRelaID"/>">
	  <input type="hidden" id="cdf" name="isSingle" value="">
	  <input type="hidden" id="fen" name="fileType" value="">
	  <input type="hidden" id="maxSize" name="maxSize" value="<c:out value="maxSize"/>">			 
	  
	  <table class="upFileSub">
		    <thead>
			    <TR> 
			      <th  scope="col">附件名称</th>
			      <th  scope="col">上传时间</th>
			      <th  scope="col">删除</th>
			    </TR> 
		    </thead>
		    <tbody>
		    <c:forEach var="attachment" items="${attachmentList}" varStatus="status">
		    <tr>
		    	<td><a href="javascript:void(0);" onclick="viewAttachment('<c:out value="${attachment.objId }"/>');return false;"><c:out value="${attachment.viewName }"/></a>
		    		<input type="hidden" id="id_<c:out value="${status.count}"/>" name="id" value="<c:out value="${attachment.objId }"/>"/>
		    		<input type="hidden" id="viewName_<c:out value="${status.count}"/>" name="viewName" value="<c:out value="${attachment.viewName }"/>">
		        </td>
		    	<td ><fmt:formatDate value='${attachment.uploadTime}' pattern="yyyy-MM-dd HH:mm:ss"/>
		    		<input type="hidden" id="createTime_<c:out value="${status.count }"/>" name="createTime" value="<fmt:formatDate value='${attachment.uploadTime}' pattern="yyyy-MM-dd HH:mm:ss"/>">
		    	</td> 
		    	<td ><img src="<%=request.getContextPath()%>/view/resource/skin/others/images/cancel.png"
		    		  onclick="delectAttachment('<c:out value="${attachment.objId }"/>','<c:out value="${attachRelaID}"/>',
		    		                            '<c:out value="${isSingle}"/>','<c:out value="${fileType}"/>')"></td>
		    </tr>
		    </c:forEach> 
		    </tbody>
	    </table>  	
	  </td>
	</tr>
</TABLE>
</form> 
<div  class="conOperation"><button onclick="windowClose('<c:out value="${attachRelaID }"/>')" type="button"><span>关闭</span></button></div>
</body>