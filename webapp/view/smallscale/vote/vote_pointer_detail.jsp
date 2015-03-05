<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div id="votePointerInfo" class="formLayout form2Pa">
   	<ul>
    	<li class="fullLine">
    		<label>评选主题：</label>
			${votePointer.voteTemplate.templateName }
  	    </li>
    	<li class="fullLine">
    		<label>指标名称：</label>
			${votePointer.pointerName }    		
  	    </li>
    	<li class="fullLine">
    		<label>指标编号：</label>
			${votePointer.pointerCode }
  	    </li>    	
  	    <li class="fullLine">
    		<label>比例系数：</label>
    		${votePointer.pointerFactor }
  	    </li>  	    
  	    <li class="fullLine">
    		<label>创建时间：</label>
			${votePointer.createTime }
  	    </li>  	    
	</ul>
		
	<div class="conOperation">
		<button type="button" id="votePointerBtnReturn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
<script>
//返回
$('#votePointerBtnReturn').click(function(){
	//自动关闭		
	$('.epsDialogClose').trigger('click');	
});
</script>