<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var IllegalRecDetail={};
$(document).ready(function(){
});
</script>
	<div id = "addIllegalRec" class="formLayout form1Pa">
	    <h4><span>违法信息</span></h4>
		<ul>
			<li>
				<label>标题：</label>
				${illegalRec.title }
			</li>	
			<li>
				<label>违法机构：</label>
				${illegalRec.org.orgName }
				
			</li>	
			<li>
				<label>是否显示：</label>
				${illegalRec.isShow?'是':'否' }
			</li>
			<li class="formTextarea">
				<label>违规内容：</label>
				${illegalRec.reason}
			</li>	
		</ul>
	</div>
   	<div class="conOperation center">
	  	<button id="close" type="button" onclick="$('#close').closest('.epsDialog').find('.epsDialogClose').trigger('click');"><span><spring:message code="globe.close"/></span></button>
	</div>
