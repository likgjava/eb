<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<style type="text/css" media="print">
.noprint{display : none }
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/order/task/task_print_div.js"></script>
<OBJECT id='wb' name='wb' classid='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2' height='0' width='0' ></OBJECT> 
  <div id="content"></div>       
  <div class="operationBtnDiv noprint">	
 		<button  id="print" type="button" tabindex="17"><span>打印</span></button>	
   		<button  id="export" type="button" tabindex="17"><span>导出</span></button>			
        <button  id="close" type="button" tabindex="17"><span>关闭</span></button>
</div>


