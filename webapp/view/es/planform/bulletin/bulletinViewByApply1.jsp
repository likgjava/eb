<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout formPa">
	 <h5>
	 <c:if test="${signUprecord!=null}">
	  <span>
	  <a onclick="javascript:$('#tabform4').click();" href="#">[查看<dm:out value="local__signup" tenderType="${project.tenderType}">报名信息</dm:out>]</a>
	  </span>
	  </c:if>
	 <c:if test="${signUprecord==null}">
	 <c:if test="${isApply=='01'}">
	  <span>
	  <a onclick="javascript:$('#tabform4').click();" href="#">[我要报名]</a>
	  </span>
	  </c:if>
	 </c:if>
	  </h5>
	  <input type="hidden" name="project.objId" id="project.objId" value="${bulletin.project.objId}">
	  <input type="hidden" name="orgInfo.objId" id="orgInfo.objId" value="${orgInfoId}">
	<span style="height:auto;">${bulletin.bullContents}</span>
</div>
<script type="text/javascript">

</script>