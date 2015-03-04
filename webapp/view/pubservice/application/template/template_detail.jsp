<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">
	<ul>
    	<li class="fullLine">
            <label>范本名称：</label>
            <p>${dotTemplate.templateName}</p>
        </li>
    	<li class="fullLine">
            <label>区域名称：</label>
            <p>${dotTemplate.districtName}</p>
        </li>
    	<li class="fullLine">
            <label>品目名称：</label>
            <p>${dotTemplate.categoryName}</p>
        </li>
        <c:if test="${dotTemplate.org.objId != null}">
    	<li class="fullLine">
            <label>所属机构：</label>
            <p>${dotTemplate.org.orgName}</p>
        </li>
        </c:if>
    	<li class="fullLine">
            <label>范本类型：</label>
            <p>${dotTemplate.templateTypeCN}</p>
        </li>
    	<li class="fullLine">
            <label>是否共享：</label>
            <p><c:choose><c:when test="${dotTemplate.isShare}">共享</c:when><c:otherwise>不共享</c:otherwise></c:choose></p>
        </li>
        <li class="fullLine">
            <label>简要描述：</label>
            <p>${dotTemplate.templateDesc}</p>
        </li>
    	<li class="fullLine">
            <label>范本文件：</label>
            <p>
            	<c:choose>
            		<c:when test="${dotTemplate.isShare || canView}"><a href="AttachmentController.do?method=downLoadFile&objId=${dotTemplate.templateFile}">${attachmentViewName}</a></c:when>
            		<c:otherwise>${attachmentViewName}</c:otherwise>
            	</c:choose>
            </p>
        </li>
    </ul>
</div>
<div class="conOperation">
	<button type="button" id="templateCloseBut"><span>关闭</span></button>
</div>

<script>
$(document).ready(function(){
	//关闭
	$("#templateCloseBut").click(function(){
		$('.epsDialogClose').trigger('click');
	});
})
</script>