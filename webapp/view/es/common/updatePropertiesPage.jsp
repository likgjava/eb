<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@page language="java" import="java.io.FileInputStream"%>
<%@page language="java" import="java.io.FileOutputStream"%>
<%@page language="java" import="java.util.Properties"%>
<%!
   private void updateProperties(String key,String value,String filePath){
	try {
		FileInputStream fs = new FileInputStream(filePath) ;
		Properties p = new Properties();
		p.load(fs);
		p.setProperty(key,value);
		FileOutputStream fos=new FileOutputStream(filePath);
		p.store(fos,"");
		} catch (Exception e) {
			e.printStackTrace();
		}
   }    
%> 
    <%
    	String key= request.getParameter("key");
    	String value= request.getParameter("value");
    	String filePath= request.getParameter("filePath");
       	String webAppPath = request.getSession().getServletContext().getRealPath("")+"\\WEB-INF\\classes\\";
    	updateProperties(key,value,webAppPath+filePath);
    %>
	<script>
	var updatePropertiesPage={};
	$(document).ready(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/es/common/propertiesPage.jsp");
	})
	</script>

