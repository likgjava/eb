<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/supplier_enter.js"></script>
<form id="form">
	<div class="formLayout" >

	 <div class="partContainers operationLog"><h5 id="supplierEnter"><span class="switch  left11"><dm:out value="local__signup" tenderType="${project.tenderType}">报名信息</dm:out></span></h5>	</div>
	 
	    <div id="supplierEnterDetail">
	    <ul >
	      <li>
	      	<label>投标单位名称</label>
	      	<input type="text" value="" class="name" />
	      	
	      </li>
	      <li>
	      	<label>联系人</label>
	      	&nbsp&nbsp&nbsp&nbsp&nbsp<input type="text" value="" class="required"/>
	      
	      </li>
	      <li>
	      	<label>联系电话</label>
	      	&nbsp&nbsp&nbsp<input type="text" value="" class="cnPhone" />
	      </li>
	      <li>
	      	<label>身份证号码</label>
	      	<input type="text" value="" class="IdCard"/>
	      	
	      </li>
	      <li>
	      	<label>联系地址</label>
	      	&nbsp&nbsp&nbsp<input type="text" value=""/>
	      </li>
	      <li>
	      
		    <div class="conOperation" align="center">
			  <button type="button" id="submitBtn" ><span >提交报名</span></button>  
			</div>
      	  </li>
      	 
	    </ul>
	    </div>
	    </div>
</form>
 