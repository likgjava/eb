<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/purchase_file_affirm_process.js"></script>
	<ul>
		<li>
			<label>附件</label>
			<a href="#"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>附件.doc</a>540Kb
		</li>
		<li>
			<label>审批结果</label>
			<input type="radio" name="radio" id="acceptyes"  checked="checked" value="1"/>通过 
			<input type="radio" name="radio" id="acceptno"  value="0"/>不通过 
			<em>*</em>
		</li>
		<li class="formTextarea">
			<label>审批意见</label>
			<textarea id="opinion" cols="120" rows="5" style="width: 80%;height:105px;margin-top:3px"></textarea>
		</li>
		<li>
			<div class="operationBtnDiv">
			  <button type="button" id="submitBtn"><span>确定</span></button>
			</div>
		</li>
	</ul>