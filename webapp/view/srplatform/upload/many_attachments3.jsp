<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/upload/many_attachments3.js"></script>



<div class="formLayout form2Pa">
  <form id="contractForm" method="post">
  <h5>合同信息</h5>
   <table>
      <tr>
        <th><label for="input01">输入字段01：</label></th>
        <td><input type="text" value="" id="input01" />
          <span class='eleRequired'>*</span></td>
        <th><label for="input02">输入字段02：</label></th>
        <td><select name="input02">
            <option>选项一</option>
            <option>选项二</option>
          </select></td>
      </tr>
      
       <tr>
        <th>文件ftp上传：</th>
        <td colspan="3">
        	<div class="uploadFile" id="contractFile3">
        		
        	</div>
        </td>
       </tr>
      		
    </table>
  </form>
  
</div>

