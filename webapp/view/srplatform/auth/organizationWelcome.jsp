<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<!--这里引入CSS、JS文件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/organizationWelcome.js"></script>
<!--表单包含内容 .form-container跟form一定要加-->
<div class="form-container">
     <div class="buttonClass">
          <button  type="button"  id="createCom"><span>新增公司</SPAN></button>
          <button  type="button" id="createDep"><span>新增部门</span></button>
          <button  type="button" id="createPost"><span>新增岗位</span></button>
     </div>  
</div>