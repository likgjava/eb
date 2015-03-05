<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/sqlExporterFrom.js"></script>

  <div class="formLayout ">
  <form id="sqlExporterForm" method="post">
    	<h5><span>资源和菜单数据导出</span></h5>
    	<ul>
    		<!-- 
    		<li>
    			 <label>选择sql类型:</label>
    			 <input type="radio" name="dbType" checked="checked" value="oracle">oracle</input>
    			 <input type="radio" name="dbType" value="db2">db2</input>
    			 <input type="radio" name="dbType" value="mysql">mysql</input>
    		</li>
    		 -->
		      <li>
		        <label>选择要导出的数据:</label>
		        <select name="domain" id="domain" class="required">
		        	<option value="">请选择</option>
		        	<option value="com.gpcsoft.srplatform.auth.domain.Resource">资源</option>
		        	<option value="com.gpcsoft.srplatform.auth.domain.Menu">菜单</option>
		        	<option value="com.gpcsoft.srplatform.auth.domain.Role">角色</option>
		        	<option value="com.gpcsoft.srplatform.auth.domain.User">用户</option>
		        </select>
		         <span class="eleRequired">*</span>
				<select name="module" id="module"></select>
		      </li>
	    </ul>
		   <div class="conOperation">
		       <button  id="toexport" type="button" ><span>生成sql语句</span></button>
		       <button  id="manyAtta" type="button" ><span>多附件上传</span></button>
		       <button  id="manyAtta2" type="button" ><span>ajax多附件上传</span></button>
		       <button  id="manyAtta3" type="button" ><span>ftp多附件上传</span></button>
		       <button  id="reloadEnum" type="button" ><span>重载枚举文件</span></button>
		       <button  id="light" type="button" ><span>状态灯</span></button>
		   </div>
    </form>
  </div>
  <div>
  		<div>
  			<table border="3" id="sqltext" ></table>
  		</div>
  </div>
 