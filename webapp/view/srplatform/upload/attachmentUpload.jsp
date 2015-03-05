<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page language="java" import="com.gpcsoft.srplatform.auth.domain.Attachment,java.text.SimpleDateFormat,com.gpcsoft.srplatform.auth.service.AttachmentService,com.gpcsoft.core.utils.locator.ServiceLocator,java.util.*"%>
<link rel="stylesheet"  type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/others/attachment.css"/>
<link rel="stylesheet"  type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/button.css"/>
	<style>
.icons_not_found {
	background-color:#FEFAEA;
	border:1px solid #FEC799;
	height:200px;
}
.icons_not_found .beer_head {
	float:left;
	padding-left:50px;
	width:170px;
}
.icons_not_found .tab_form {
line-height:24px;
overflow:hidden;
padding-top:20px;
}
.icons_not_found h5 {
font-size:22px;
font-weight:bold;
margin-bottom:10px;
}
.tab_form .tab_content p {
	line-height:24px;
	margin-top:10px;
	overflow:hidden;
	width:540px;
}
</style>
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

	function delectAttachmentAjax(attachmentId,attachRelaID,isSingle,fileType){		
		if(window.confirm("确认要删除该附件吗？")){
			$.getJSON($('#initPath').val()+'/AttachmentController.do?method=delectAjax',{objId:attachmentId},function(json){
				if(json.failtrue){
					alert(json.result);
					return;
				}
				$("tr[id="+attachmentId+"]").remove();

				//文件个数
				if(document.getElementById("_count")){
					document.getElementById("_count").value = $("#fileUpload").find("tbody").find("tr").length;
				}
			})
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
				fileSubmit();
			}else{
				alert("您可以输入的格式为:"+fileType);
				return false;
			}
		} 

		//提交
		fileSubmit();
	}

	//提交
	function fileSubmit(){
		if(document.getElementById("divId").value){
			//使上传按钮不可用
			$('#upload_input').attr('disabled','disabled');
			$('#attachmentForm').ajaxSubmit({
				url:$('#initPath').val()+'/AttachmentController.do?method=upLoadAjax',
				dataType:'json',
				success:function(json){
					if(json.failure){
						alert(json.result);
						return;
					}
					$('input[name=attachRelaID]').val(json.relationId);
					$('#attachmentForm').prev('input').val(json.relationId);//给领域类关联的属性赋值
					var count = $("#fileUpload").find("tbody").find("tr")==null?1:($("#fileUpload").find("tbody").find("tr").length+1);
				    var html = '<tr id="'+json.attachmentId+'">'+
				    	'<td><a href="javascript:void(0);" onclick="viewAttachment(\''+json.attachmentId+'\');return false;">'+ascii2native(json.viewName)+'</a>'+
				    		'<input type="hidden" id="id_'+count+'" name="id" value="'+json.attachmentId+'"/>'+
				    		'<input type="hidden" id="viewName_'+count+'" name="viewName" value="'+json.viewName+'">'+
				        '</td>'+
				    	'<td >'+json.uploadTime+
				    		'<input type="hidden" id="createTime_'+count+'" name="createTime" value="'+json.uploadTime+'">'+
				    	'</td>'+
				    	'<td><img src="'+$("#initPath").val()+'/view/resource/skin/others/images/cancel.png" onclick="delectAttachmentAjax(\''+json.attachmentId+'\',\''+json.relationId+'\')"></td>'+
				    '</tr>';

					$("#fileUpload").find("tbody").append(html);
					
					$("#viewName").val("");
					$("#attachFile").val("");

					//文件个数
					if(document.getElementById("_count")){
						document.getElementById("_count").value = $("#fileUpload").find("tbody").find("tr").length;
					}
					
					//父窗口
					adAjax(json);
					
					//成功后上传按钮可用
					$('#upload_input').removeAttr('disabled');
				},
				error:function(msg){
					$("#errorMsg").empty().html(msg.responseText);
					var file = $("#file_one");  
				    file.after(file.clone().val(""));  
				    file.remove();  
				}
			});
		}else{
			document.getElementById("attachmentForm").submit();
			//$('#attachmentForm').submit();
		}
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
		if(document.getElementById("divId").value){
			$("div[id="+$("#divId").val()+"]").find('#epsDialogClose').click();
		}else{
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
	}
	
	function ad(){
		var attachRelaID = document.getElementsByName("attachRelaID").value;
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

	function adAjax(json){
		var tableHTML ="";
		tableHTML += "<tr id='"+json.attachmentId+"'><td align='left'>";
		tableHTML += "<a href=\"javascript:showAttach_2(\'"+json.attachmentId+"\')\">"+ascii2native(json.viewName)+"</a></td>";
		tableHTML += "<td align='center'>"+json.uploadTime+"</td>";
		if(document.getElementById("_attachRelaID")){
			document.getElementById("_attachRelaID").value = json.relationId;
		}
		$('#attachments_show_area').append(tableHTML);
	}	
	
</script>
<body onbeforeunload="ad()" id="attUpBody">
<div id="errorMsg">

<c:if test="${!(isSingle=='yes' && size=='1') }">
<form name="attachmentForm" id="attachmentForm" 
 <c:if test="${param.divId==null }">
 action="<%=request.getContextPath()%>/AttachmentController.do?method=upLoad"
 </c:if>
  method="post" ENCTYPE="multipart/form-data">
  <input type="hidden" id="divId" name="divId" value="${param.divId }">
  <table class="upFile">
    <thead>
	    <TR>
	        <TD>附件名称：<input type="text" id="viewName" class="txt" name="viewName"/>&nbsp;&nbsp;
	                                文件：<input type="file" name="attachFile" id="attachFile"/>&nbsp;&nbsp;
	            <input type="hidden" name="attachRelaID" value="<c:out value="${attachRelaID}"/>"/>
    			<input type="hidden" name="fileType" value="<c:out value="${fileType}"/>"/>
    			<input type="hidden" name="isSingle" value="<c:out value="${isSingle}"/>"/>   
    			<input type="hidden" name="reducedWidth" id="reducedWidth" value="${reducedWidth}" />
				<input type="hidden" name="reducedHeight" id="reducedHeight" value="${reducedHeight}" />               
	            <input type="button" class="button" id="upload_input" value="上传" onclick="submitForm('<c:out value="${fileType}"/>')"/>                    
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
	  
	  <table class="upFileSub" id="fileUpload">
		    <thead>
			    <TR> 
			      <th  scope="col">附件名称</th>
			      <th  scope="col">上传时间</th>
			      <th  scope="col">删除</th>
			    </TR> 
		    </thead>
		    <tbody>
		    <c:forEach var="attachment" items="${attachmentList}" varStatus="status">
		    <tr id="${attachment.objId}">
		    	<td><a href="javascript:void(0);" onclick="viewAttachment('<c:out value="${attachment.objId}"/>');return false;"><c:out value="${attachment.viewName }"/></a>
		    		<input type="hidden" id="id_<c:out value="${status.count}"/>" name="id" value="<c:out value="${attachment.objId }"/>"/>
		    		<input type="hidden" id="viewName_<c:out value="${status.count}"/>" name="viewName" value="<c:out value="${attachment.viewName }"/>">
		        </td>
		    	<td ><fmt:formatDate value='${attachment.uploadTime}' pattern="yyyy-MM-dd HH:mm:ss"/>
		    		<input type="hidden" id="createTime_<c:out value="${status.count }"/>" name="createTime" value="<fmt:formatDate value='${attachment.uploadTime}' pattern="yyyy-MM-dd HH:mm:ss"/>">
		    	</td> 
		    	<td ><img src="<%=request.getContextPath()%>/view/resource/skin/others/images/cancel.png"
		    		  	onclick=" <c:if test="${param.divId==null }">delectAttachment</c:if><c:if test="${param.divId!=null }">delectAttachmentAjax</c:if>('<c:out value="${attachment.objId }"/>','<c:out value="${attachRelaID}"/>','<c:out value="${isSingle}"/>','<c:out value="${fileType}"/>')">
		    	</td>
		    </tr>
		    </c:forEach> 
		    </tbody>
	    </table>  	
	  </td>
	</tr>
</TABLE>
</form> 
	<div class="conOperation"><button onclick="windowClose('<c:out value="${attachRelaID }"/>')" type="button"><span>关闭</span></button></div>
</div>
<!-- 加一个iframe解决ie底下的select不被遮挡的问题 -->
<iframe style="position:absolute;top:0;z-index:-1;left:0;width:100%;height:100%;filter:alpha(opacity=0);"></iframe>
</body>