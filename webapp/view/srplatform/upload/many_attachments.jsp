<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/upload/many_attachments.js"></script>

<!--操作类型-->
<input type="hidden" id="type" value="<c:out value="${param.type}"/>"/>

<div class="formLayout form2Pa">
  <form id="contractForm" method="post">
  <h4><span>合同信息</span></h4>
    <ul>
      <li class="formTextarea"><label>备注：</label>
      		<textarea name="memo" id="memo"></textarea>
      </li>
      <li class="fullLine">
      		<label>附件：</label>
      		<div id="contractFile" class="uploadFile"></div>
      </li>   
    </ul>
  </form>
  
</div>

 <div class="conOperation">	
 		<button  id="save" type="button" class="hidden"><span>显示表单数据</span></button>
</div>

 <div id="operatorList"></div>