<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/baseData/templateForm.js"></script>
<!--页面按钮开始-->
<div id="pageMenu" class="pageMenu ui-state-default">
	<a href="javascript:void(0);" id="refresh" class="addicon refresh">刷新</a>
    <a href="javascript:void(0);" id="print" class="addicon print">打印</a>
    <a href="javascript:void(0);" id="help" class="addicon help">帮助</a>
</div>
<!--页面按钮结束-->
<div class="form-container">     
<form method="post" name="TemplateForm" id="TemplateForm"  action="TemplateController.do?method=save" onsubmit="return validateTemplateForm(this);">
	<input type="hidden" name="objId" id="objId" value="${param.objId}">
	<fieldset>
  		<legend>模版基本信息</legend>
  		<div><label><spring:message code="templateForm.name"/><em>*</em>:</label>
         	<input type="text" name="name" class="required" size="40">
			<span class="requisite"> </span>
        </div>
        <div><label><spring:message code="templateForm.type"/><em>*</em>:</label>
         	<select id="type" name="type.objId" class="required">
         	</select>
			<span class="requisite"> </span>
        </div>
        <div><label><spring:message code="templateForm.remark"/>:</label>
         	<textarea name="remark" cols="32"></textarea>
        </div>
     </fieldset> 
     <fieldset>
  		<legend>模版内容（必填）</legend>
        <div class="editor" id="TepDescEditor">
			<textarea id="FCKContent" name="des" class="hidden"></textarea>
		</div>
  	 </fieldset>     	
     <div class="buttonClass">
      		<button  type="button"  id="submit"><span><spring:message code="globe.save"/></span></button>
     		<button  type="reset"><span><spring:message code="globe.reset"/></span></button>
     		<button  type="button" id="return"><span><spring:message code="globe.return"/></span></button>
	</div>
	
</form>
</div>