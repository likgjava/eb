<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/make_submission.js"></script>

<div class="formLayout">
	<div class="partContainers operationLog"><h5 id="reportResults"><span class="switch  left11">制作<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>文件</span></h5></div>
	<form id="makeSubmissionForm">
		<div id="makeSubmissionContent">
		<ul style="padding-top:0px">
			<li>
	        <label for="input01">采购招标名称：</label>
	        <input type="text" name="name" class="required">
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	       </li>
	       <li>
	        <label for="input01">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp投标单位：</label>
	        <input type="text" name="supply" class="required">
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	       </li>
			<li class="formTextarea">
	        <label for="input20"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>文件内容：</label>
	        <textarea name="content" id="input20" style="width:40%;height:105px;margin-top:3px"></textarea>
	        <span class="eleNote"></span>
	        </li>
	        <li>
	        	<div class="conOperation" style="text-align:center">
					<button type="button" id="save"><span>保存</span></button>
				</div>
	        </li>
		</ul>
		</div>
	</form>
	
</div>

