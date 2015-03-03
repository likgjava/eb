<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="AgencyApplyForm" method="post">
	<input type="hidden" name="objId" id="AgencyId"/>
	<input type="hidden" name="orgInfoId" id="orgInfoId" value="${orgInfo.objId}"/>
<div class="formLayout form2Pa">
<ul>
		<li class="fullLine">
             <label for="input01">代理机构类型：</label>
             <html:select selectedValue="0" id="agentType" name="agentType" code="biz.agent.agentType"></html:select>
        </li>
        <li class="fullLine">
             <label for="input01">企业类型：</label>
             <html:select selectedValue="0" id="unitType" name="unitType" code="biz.agent.unitType"></html:select>
        </li>
        <li>
             <label for="input01">采购代理：</label>
             <select id="purAgent" name="purAgent">
            	<option value="01">集中采购</option>
            	<option value="02">招标代理</option>
             </select>
        </li>
        <li class="fullLine">
            <label for="input02">主管单位：</label>
            <input type="text" name="supervisor" id="supervisor" />
			<input type="hidden" id="supervisorId" name="unitInCharge.objId"/>
        </li>
</ul>
</div>

<div class="conOperation">
	<button class="largeBtn" type="button" id="saveAgencyBtn"><span>申请</span></button>
</div>
</form>

<script>
var AgencyApplyForm={};

$(document).ready(function(){
	$('#AgencyApplyForm').validate();
	
	//确定
	$("#saveAgencyBtn").click(function(){
		if($("#AgencyApplyForm").valid()){
			if(window.confirm('确定申请采购人的角色？')){
				$.getJSON($('#initPath').val()+'/AgencyController.do?method=saveApplyAgency',formToJsonObject('AgencyApplyForm'), function(json){
					if(json.success=='true'){
						alert('申请成功，请耐心等待管理员的审核');
						$('#conBody').loadPage($('#initPath').val()+"/OrgInfoController.do?method=toApplyOrgInfo");
					}else{
						alert('操作失败');
					}
				})
			}
		}
	})

	//主管单位
	$("#supervisor").click(function(){
		$.epsDialog({
	        title:'选择',
	        url:$("#initPath").val()+'/view/bizplatform/agency/registration/select_chargedepartment.jsp?Hname=supervisor&Hid=supervisorId'
	    })
	})
})
</script>