<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="OrganizationChangeForm" method="post" modelAttribute="oldOrgInfo"> 
        <input type="hidden" id="orgId" name="orgId" value="${oldOrgInfo.objId}"/>
        
       	<input type="hidden" id="companyId" name="companyId" value="${oldOrgInfo.company.objId}"/>
        
<c:choose>
	<c:when test="${!empty orgModifyList && fn:length(orgModifyList) > 0}">
		<div class="formTips light">
			<ul>
				<li class="big">
				<em>当前状态：</em>已提交变更信息，请等待审核...
				</li>
				<li>变更信息如下：</li>
				<c:forEach var="changeOrg" items="${orgModifyList}" varStatus="status1">
					<li>
						<c:if test="${changeOrg.modifyType=='orgName'}">
							<strong>机构名称：</strong>
						</c:if>
						<c:if test="${changeOrg.modifyType=='orgCode'}">
							<strong>机构代码：</strong>
						</c:if>
						<c:if test="${changeOrg.modifyType=='bidForRange'}">
							<strong>经营范围：</strong>
						</c:if>
						${changeOrg.oldValue} <font color='red'> 变更为  >> </font> ${changeOrg.newValueName}
					</li>
				</c:forEach>
			</ul>
		</div>
	</c:when>
	<c:otherwise>	
	<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>以下信息修改后需要经过审核方生效。不需要变更的信息可以不填写。
		</li>
	</ul>
    </div>
	<div class="formLayout  form2Pa">
		<table id='changeTable'>
			<thead>
				<tr>
					<td><strong>当前信息</strong></td>
					<td width="50%"><strong>变更为</strong></td>
				</tr>
			</thead>
			<tbody>
		    	<tr>
		            <td><label>机构名称：</label>
		            	<span id="orgName_old">${oldOrgInfo.orgName}</span>
		            </td>
		            
		            <td>
		            	<span id="nameDiv" class="hidden" name="inputSpan" modType="orgName" oldVal="${oldOrgInfo.orgName}">
			            	<input type="text" id="orgName" name="orgName" maxlength="50" size="60"/>
			            	<span class="eleRequired"></span> 
		            	</span>
		            	<a href="javascript:void(0);" id="nameA" onclick="OrganizationChangeForm.changeOrgInfo('name');return false;">变更</a>
	            	</td>
		        </tr>
		        
		        <tr>
		            <td><label>机构代码：</label>
		            	<span id="orgCode_old">${oldOrgInfo.orgCode}</span>
		            </td>
		            
		            <td>
			        	<span id="codeDiv" class="hidden" name="inputSpan" modType="orgCode" oldVal="${oldOrgInfo.orgCode}">
			        		<input type="text" id="orgCode" name="orgCode" maxlength="50" size="60"/>
			        		<span class="eleRequired"></span> 
			        	</span>
		            	<a href="javascript:void(0);" id="codeA" onclick="OrganizationChangeForm.changeOrgInfo('code');return false;">变更</a>
			        </td>
		        </tr>
	
		        <tr>
		            <td><label>经营范围：</label>
		            	<span id="bidForRange_old">${oldOrgInfo.bidForRangeName}</span>
		            </td>
		            
		            <td>
			        	<span id="rangeDiv" class="hidden" name="inputSpan" modType="bidForRange" oldVal="${oldOrgInfo.bidForRangeName}">
				            <input type="hidden" name="bidForRange" id="bidForRange"/>
			        		<textarea id="bidForRange_1" readonly="readonly" name="bidForRange_1" maxlength="1000" style="height:50px;width:80%" >${oldOrgInfo.bidForRangeName}</textarea>
			        	</span>
				        <input type="hidden" id="bidForRange_2" value="${oldOrgInfo.bidForRangeCode}"/>
		            	<a href="javascript:void(0);" id="rangeA" onclick="OrganizationChangeForm.changeOrgInfo('range');return false;">变更</a>
			        </td>
		        </tr>
		        </tbody>
	    </table>
	</div>
	
	<div class="conOperation">
		<button type="button" id="submit"><span>提交</span></button>
		<button type="button" class="largeBtn" id="viewHistory"><span>查看历史</span></button>
	</div>
	</c:otherwise>
</c:choose>      
</form:form>		

<script>
var OrganizationChangeForm={};

OrganizationChangeForm.changeOrgInfo=function(divId){
	if($('span[id='+divId+'Div]').attr('class')=='hidden') {
		$("#"+divId+"A").html("取消");
		$('#'+divId+'Div').removeClass('hidden');
	}else {
		$("#"+divId+"A").html("变更");
		$('#'+divId+'Div').addClass('hidden');
	}
}

$(document).ready(function(){
	var validId = $("#orgId").val();
	var jsonCodeObj= {};
	jsonCodeObj["id"]=validId;
	jsonCodeObj["property"]="orgCode";
	var jsonNameObj= {};
	jsonNameObj["id"]=validId;
	jsonNameObj["property"]="orgName";
	//唯一性验证
	$.validator.addMethod("orgCodeUnique",function(value,element,param){return bizUniqueHandler("OrgInfoController.do?method=checkOrgUnique&orgCode="+native2ascii($("#orgCode").val()),jsonCodeObj);},'该机构代码已存在');
	$.validator.addMethod("orgNameUnique",function(value,element,param){return bizUniqueHandler("OrgInfoController.do?method=checkOrgUnique&orgName="+native2ascii($("#orgName").val()),jsonNameObj);},'该机构名称已存在');
	//机构表单验证
	$("#OrganizationChangeForm").validate({
		rules:{
			orgCode:{orgCodeUnique:"orgCode"},
			orgName:{orgNameUnique:"orgName"}
		}
	});	
	
	//选择品目（投标范围及类别）
	$("#bidForRange_1").click(function(e){
	    $.epsDialog({
	        title:'选择品目',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=bidForRange_2&NAMES=bidForRange_1&className=PurCategory&action=listTop&isCheckBox=true&childNodeOnly=true&checkStatus=true',
	        onClose: function(){ 
	      		$("#bidForRange").val($("#bidForRange_2").val()+"##||##"+$("#bidForRange_1").val());
	      	}
	    }); 
	});
	
	//提交
	$('#submit').click(function(){
		if($('#nameDiv').attr('class')=='hidden'&&$('#codeDiv').attr('class')=='hidden'&&$('#rangeDiv').attr('class')=='hidden'){
			alert('您没有录入变更信息');
		}else {
			var changeOrgArray = [];
			var res = true;
			$('span[name=inputSpan]').each(function(i,n){
				if($(n).attr('class') != 'hidden') {
					if($(n).find('input').val()!='') {
						res = false;
						var newV = $(n).find('input').val();
						var changeOrg={
								orgInfo:{objId:$('#orgId').val()},
								modifyType:$(n).attr("modType"),
								oldValue:$(n).attr("oldVal"),
								newValue:newV,
								auditStatus:'01'
						}
						changeOrgArray.push(changeOrg);
					}
				}
			})
			if(res) {
				alert('请填写变更机构信息');
				return;
			}
			if(confirm('确认要提交变更信息吗?')){
				$.getJSON($('#initPath').val()+'/OrgInfoModifyController.do?method=saveChange',{changeOrgStr:JSON.stringify(changeOrgArray)},function(json){
					if(json.failure){alert(json.result);return;}
					alert('提交成功，请等待审核！');
					$('#conBody').loadPage($('#initPath').val()+'/OrgInfoModifyController.do?method=toModifyOrg&companyId='+$("#companyId").val());
				});
			}
		}
	})

	//查看历史
	$("#viewHistory").click(function(){
		var url = $('#initPath').val()+"/OrgInfoController.do?method=getOrgHistory&id="+$("#orgId").val();
		$.epsDialog({
	        title:'机构变更历史',
	        url:url
	    }); 
	})
})
</script>