<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/sub_entrust_inf.js"></script>
<div id="infoDiv">
	 <ul>
	 	<li><a href="#subEntrust">录入委托</a></li>
		<li><a href="#uploadEntrust">导入委托</a></li>
	 </ul>
	 <div id="uploadEntrust">
	 	<form id="uploadEntrustForm">
	  	  	<ul style="padding-top:10px">
	      	<li>
		        <label for="input01">导入委托</label>
		        <input type="file" name="entrustFile" id="entrustFile"/>
		        <button type="button" id="upload"><span>导入</span></button>    
	      	</li>
	     	</ul>
     	</form>
  	 </div>
	 <div id="subEntrust">
	 	<form id="subEntrustForm" class="form2Pa">
	    <ul style="padding-top:0px">
	      <li>
	        <label for="input01">委托协议名称</label>
	        <input type="text" name="consName" class="required">
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	      </li>
	      <li>
	        <label for="input01">委托编号</label>
	        <input type="text" name="consCode" class="required">
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	      </li>
	      <li>
	        <label for="input01">委托人</label>
	        <input type="text" name="consBuyerName" class="required">
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	      </li>
	       <li>
	        <label for="input01">委托方联系人</label>
	        <input type="text" name="consBuyerLinker" class="required">
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	      </li>
	       <li>
	        <label for="input01">委托方联系电话</label>
	        <input type="text" name="consBuyerTel" class="required">
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	      </li>
	      <li>
	        <label for="input01">受托机构</label>
	        <input type="text" name="consAgentName" class="required">
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	      </li>
	      <li>
	        <label for="input01">受托机构联系人</label>
	        <input type="text" name="consAgentLinker" class="required">
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	      </li>
	      <li>
	        <label for="input01">受托机构联系电话</label>
	        <input type="text" name="consAgentTel" class="required">
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	      </li>
	       <li>
	        <label for="input01">委托时间</label>
	        <input type="text" name="consTime" id="constime" class="required" style="width:145px">
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	      </li>
	       <li>
	        <label for="input01">拟完成时间</label>
	        <input type="text" name="consFinishTime" id="consfinishtime" class="required" style="width:145px">
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	      </li>
	      <li>
	        <label for="input20">其它附件</label>
	        <input type="file" name="consOtherattachment"/>
	        <span class="eleNote"></span>
	      </li>
	      <li>
	        <label for="input01">意见</label>
	        <input type="text" name="consOpinion">
	      </li>
	      <li style="list-style:none">
	        <label for="input20">委托协议内容</label>
			<textarea id="consContents" name="consContents" class="hidden"></textarea>
	        <span class="eleNote"></span>
	      </li>
	      <li class="formTextarea">
	        <label for="input20">备注</label>
	        <textarea name="consRemark" style="width:40%;height:105px;margin-top:3px;"></textarea>
	        <span class="eleNote"></span>
	      </li>
	     </ul>
	     </form>
  	  </div>
  	  <div class="conOperation" style="text-align:center">
	      	<button type="button" id="sure"><span>确定</span></button>
	  </div>
</div>   