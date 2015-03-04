<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script>
$(document).ready(function(){
	$("#submitBtn").click(function(){
		//评审未通过且未填写意见
		if($("#opinion").val()=='' && $("#acceptno").attr("checked")==true)
		{
			alert("请填写审核意见");
		}
		else if(window.confirm("确认提交?"))
		{
			alert("提交成功");
		}
		
	})
})
</script>
<div class="partContainers">
<div class="formLayout form2Pa">
	<h5><span>变更公告信息</span></h5>
	<ul>
		<li>
			<label for="bullTitle"><spring:message code="bulletinForm.bullTitle"/>：</label>
			<span id="bullTitle">${bulletin.bullTitle }</span>
		</li>
		<li>
			<label for="bulletinNo"><spring:message code="bulletinForm.bulletinNo"/>：</label>
			<span id="bulletinNo">${bulletin.bulletinNo }</span>
		</li>
		<li>
			<label>&nbsp;</label>
			<span>&nbsp;</span>
		</li>
		<li class="formTextarea">
			<label for="bullContents"><spring:message code="bulletinForm.bullContents"/>：</label>
			<span id="bullContents">${bulletin.bullContents }</span>
		</li>
	</ul>
</div>
  		<div class="formLayout form2Pa">
  			<h5>审核变更公告</h5>
      		<form id="BulletinForm" method="post">
      		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
			<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
			<input type="hidden" name="auditStatus" id="auditStatus" value="Y"/>
			<ul>
				<li class="formTextarea">
					<label>意见：</label>
					<textarea name="opinion" id="opinion"></textarea>
				</li>
			</ul>
			 <div class="conOperation">
	       	  	<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
				<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
	   	    </div>
			</form>
    	</div>
</div>