 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/bidtype_set_inf.js"></script>
 <form id="bidtypeForm">
	    <ul style="padding-top:0px">
	      <li>
	        <label for="input01">采购方式</label>
	       <select name="comWorkName" id="comWorkName">
	         <option>-请选择-</option>
	         <option value="公开招标">公开招标</option>
	         <option value="邀请招标">邀请招标</option>
	         <option value="竞争性谈判">竞争性谈判</option>
	         <option value="询价采购">询价采购</option>
	         <option value="单一来源">单一来源</option>
	       </select>
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	      </li>
	      <li class="formTextarea">
	        <label for="input20">备注</label>
	        <textarea name="remark" id="input20" style="font-size:12px;width:40%;height:105px;margin-top:3px;"></textarea>
	        <span class="eleNote"></span>
	      </li>
	      <li>
	      	  <div class="conOperation" style="text-align:center">
	       		<button type="button" id="sure"><span>确定</span></button>
	   		</div>
	      </li>
	    </ul>
</form>