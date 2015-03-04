<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@page language="java" import="java.io.FileInputStream"%>
<%@page language="java" import="java.io.FileOutputStream"%>
<%@page language="java" import="java.util.Properties"%>
<%!     
   private String getProperties(String key,String filePath){
	   String value = "";
	   try {
	   FileInputStream fs = new FileInputStream(filePath) ;
	   Properties p = new Properties();
		p.load(fs);
		value = p.getProperty(key);
	   } catch (Exception e) {
			e.printStackTrace();
	   }
	   return value;
   }
      
%> 
    <%
       	String webAppPath = request.getSession().getServletContext().getRealPath("")+"\\WEB-INF\\classes\\";
    	String newZipPath = getProperties("newZipPath",webAppPath+"sys\\attachment.properties");
   		String bidFilePath = getProperties("bidFilePath",webAppPath+"sys\\attachment.properties");
    %>
	

	
<fieldset class="operationDiv" style="margin-top:30px">
	<legend>文件上传下载地址</legend> 
	
	<div class="formLayout">
 		<ul>
		<li>
			<label for="newZipPath">招标文件保存路径：</label>
			<input type="text" name="sys\\attachment.properties" id="newZipPath" class="required"  value="<%=newZipPath%>"/>
			<input type="button" name="updateBtn" value="修改" />
		</li>
		<li>
			<label for="content">投标文件保存路径：</label>
			<input type="text" name="sys\\attachment.properties" id="bidFilePath" class="required"  value="<%=bidFilePath%>" />
			<input type="button" name="updateBtn" value="修改" />
		</li>
	</ul>
	</div>
	</fieldset>
	
	<script>
	var propertiesPage={};
	$(document).ready(function(){
	   $("input[name=updateBtn]").click(function(){
			var key =$(this).prev().attr("id");
			var value =$(this).prev().val();
			var filePath =$(this).prev().attr("name");
			$('#conBody').loadPage($('#initPath').val()+"/view/es/common/updatePropertiesPage.jsp?key="+key+"&value="+value+"&filePath="+filePath);
	   })
	})
	</script>

