<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<!--这里引入CSS、JS文件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/menuForm.js"></script>

<div class="form-container">
<form method="post" name="MenuForm" id="MenuForm"  action="MenuController.do?method=save">
	<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
	<input type="hidden" name="parent.objId"  id="parent.objId"  value="<c:out value="${param.parentId}"/>"/>
 
  	<fieldset>
  		<legend>菜单信息</legend>
	 	<div><label><spring:message code="MenuForm.name"/><em>*</em>:</label>
           	 <input type="text" name="name" class="required" size="40">
           	 <span class="requisite"> </span>
        </div>
        <div><label><spring:message code="MenuForm.resource"/><em>*</em>:</label>
           	 <input type="hidden" name="resource.objId" id="resource.objId" >
					<input type="text" name="resource.name" id="resource.name" size="40" class="required" readonly="readonly">
					<a href="javascript:void(0);" id="ResourceHov"><image src="<%=request.getContextPath()%>/view/resource/images/find.gif" border="0"></a>
					<span class="requisite"> </span>
        </div>
        <div><label><spring:message code="MenuForm.tip"/>:</label>
           	 <input type="text" name="tip" size="40"> 
        </div>
        <div><label><spring:message code="MenuForm.showFlag"/>:</label>
           	<input class="checkboxInput"  type="radio" checked="checked" name="showFlag" id="showFlag_1" value="1"/>显示&nbsp;
           	<input class="checkboxInput"  type="radio" name="showFlag" id="showFlag_0" value="0"/>不显示
        </div>
        <div><label><spring:message code="MenuForm.memo"/>:</label>
           	<textarea name="memo" rows="4" cols="40" ></textarea>
        </div>
      </fieldset>
		<div class="buttonClass">
		  <button  type="button"  id="submit"><span><spring:message code="globe.save"/></span></button>
          <button  type="reset"><span><spring:message code="globe.reset"/></span></button>
          <button  type="button" id="delete"><span><spring:message code="globe.delete"/></span></button>
		</div>
</form>
</div>