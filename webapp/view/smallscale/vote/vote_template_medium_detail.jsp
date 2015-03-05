<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">
	<h4>媒体信息：</h4>
   	<ul>
    	<li>
    		<label>媒体名称：</label>
			${voteTemplateMedium.orgInfo.orgName }
  	    </li>
    	<li>
    		<label>媒体网址：</label>
			${voteTemplateMedium.orgInfo.webUrl }
  	    </li>
    	<li>
    		<label>媒体联系人：</label>
			${voteTemplateMedium.orgInfo.user.usName }
  	    </li>
    	<li>
    		<label>媒体电话：</label>
			${voteTemplateMedium.orgInfo.company.mobilePhone }
  	    </li>
    	<li>
    		<label>媒体传真：</label>
			${voteTemplateMedium.orgInfo.company.fax }
  	    </li>
    	<li>
    		<label>媒体邮箱：</label>
			${voteTemplateMedium.orgInfo.company.email }
  	    </li>
    	<li>
    		<label>媒体地址：</label>
			${voteTemplateMedium.orgInfo.company.address }
  	    </li>
    	<li>
    		<label>媒体备注：</label>
			${voteTemplateMedium.orgInfo.descCn }
  	    </li>
  	    <li>
    		<label>创建时间：</label>
    		<fmt:formatDate value="${voteTemplateMedium.createTime }" pattern="yyyy-MM-dd"/>
  	    </li>
  	    <li>
    		<label>媒体图标：</label>
    		<img src="AttachmentController.do?method=showImg&objId=${voteTemplateMedium.orgInfo.logo }" />
  	    </li>
	</ul>
	
	<div class="conOperation">
		<button type="button" id="voteTemplateMediumBtnReturn" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
<script type="text/javascript">
	//返回
	$('#voteTemplateMediumBtnReturn').click(function(){
		$('.epsDialogClose').trigger('click');	
	});
</script>

