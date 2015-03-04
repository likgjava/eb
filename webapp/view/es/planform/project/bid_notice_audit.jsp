<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/bid_notice_audit.js"></script>
<div class="formLayout">
<span style="margin-left:5px">当前位置：采购预告 >> 审核采购预告</span>
<div class="partContainers operationLog"><h5 id="preNoticeText"><span class="switch left11">采购预告</span></h5></div>
<div id="noticeDiv"></div>
<div class="partContainers operationLog"><h5 id="preNoticeAudText"><span class="switch left11">审核采购预告</span></h5></div>
<div id="preNoticeAud">
	<ul style="padding-top:0px">
		<li>
			<label>附件</label>
			<a href="#">公告附件.doc</a> 540Kb
		</li>
		<li>
			<label>审核结果</label>
			<input type="radio" name="result" id="pass" checked="checked"/>通过 <input type="radio" name="result" id="refuse"/>不通过 
		</li>
		<li>
			<label>审核意见</label>
			<textarea name="opinion" cols="50" rows="5" ></textarea>
		</li>
		<li>
			 <div class="conOperation" style="text-align:center">
	       	  	<button type="button" id="sure"><span>确定</span></button>
	   	    </div>
		</li>
		<li>
			<div class="functionBtnDiv" style="padding-top:5px;padding-bottom:5px">
			    <button type="button" id="pre"><span>上一步</span></button><button type="button" id="next"><span>下一步</span></button>
		    </div>
		</li>
	</ul>
</div>
</div>
<div id="auditHistory"></div> 
