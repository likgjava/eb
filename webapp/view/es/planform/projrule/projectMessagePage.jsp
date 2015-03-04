<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<div class="formLayout form2Pa">        
     		<ul>
					<li class="short">
						<label for="projCode"><spring:message code="projectForm.projCode"/>：</label>
						${project.projCode}
					</li>
					<li class="short">
						<label for="projName"><spring:message code="projectForm.projName"/>：</label>
						${project.projName}
					</li>
					<li class="short">
						<label for="ebuyMethod"><spring:message code="projectForm.ebuyMethod"/>：</label>
						${project.ebuyMethodCN}
					</li>
					<li class="short">
						<label for="tenderType">项目类型：</label>
						${project.manager.name}
					</li>
					<li class="short">
						<label for="ebuyMethod">项目负责人：</label>
						${project.monitor.name}
					</li>
		</ul>
		<div class="conOperation">
			<button id="close" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
		</div>
</div>
<script>
$(document).ready(function(){

$("#close").click(function(){  //点击关闭按钮
	$('#epsDialogClose').click();
})

	
})


</script>