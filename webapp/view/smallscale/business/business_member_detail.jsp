<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="BusinessMemberDetailForm" method="post" modelAttribute="businessMember">
	<div class="formLayout form1Pa">
		<ul>
			<li><label>机构名称：</label> <span>${businessMember.orgInfo.orgName}</span>
			</li>
			
			<li><label>会员时长：</label> <span>${businessMember.timeTypeCN}</span>
			</li>
			
			<li><label>开始日期：</label> <span>${businessMember.begainDate}</span>
			</li>
			
			<li><label>结束日期：</label> <span>${businessMember.endDate}</span>
			</li>
		</ul>
	</div>
	
	<div class="conOperation">
		<button type="button" name="historyBackBtn" ><span>返回</span></button>
	</div>
</form:form>
