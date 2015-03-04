<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout form2Pa">
    <input type="hidden" id="projectId" value="${project.objId}">
	<h5><span>报名符合性要求设置</span></h5>
	<ul>
		<li><a onClick="insertRow()" class="linkButton"><span>新增指标</span></a></li>
	</ul>
	<table width="100%" border="0" class="tableList">
		<thead>
			<tr>
                <th width="30%">指标名称</th>
				<th width="30%">说明</th>
				<th width="15%"></th>
	  		</tr>
		</thead>
		<tbody id="signupCongruous">
		<c:set var="count" value="0"></c:set>
		<c:forEach items="${signUpCondFactorList}" var="signUpCondFactor">
	   		<tr>
                <td>
                <input id="sets[${count}&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].objId" name="sets[${count}&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].objId" value="${signUpCondFactor.objId }" type="hidden"/>
                <input id='sets[${count}&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].factorId' name="sets[${count}&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].factorId" value="${signUpCondFactor.factorId }" type="hidden"/>
                <input id='sets[${count}&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].project.objId' name="sets[${count}&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].project.objId" value="${signUpCondFactor.project.objId}" type="hidden"/>
                <input id='sets[${count}&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].factorName' name='sets[${count}&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].factorName'  maxlength='35' size='25' value="${signUpCondFactor.factorName }" type="text"/></td>
                <td><textarea id='sets[${count}&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].remark'  name='sets[${count}&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].remark'>${signUpCondFactor.remark }</textarea></td>
                <td><a class='abtn' id='remove' onClick='signUpCondFactorView.liveButtonClick(this);'><span>删除</span></a>
                </td>
			</tr>
			<c:set var="count" value="${count+1}"></c:set>
		</c:forEach>
		</tbody>
	</table>
	<input type="hidden" id="rowNum" value="${count}">
</div>
<script>
var signUpCondFactorView ={}; 
//新增指标
var projectId = $("#projectId").val();
var i = $("#rowNum").val();
function insertRow(){
	var html = "";
	html +="<tr><td>";
	html +="<input id='sets["+i+"&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].objId' name='sets["+i+"&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].objId' value='' type='hidden'/>";
	html +="<input id='sets["+i+"&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].project.objId' name='sets["+i+"&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].project.objId' value='"+projectId+"' type='hidden'/>";
	html +="<input id='sets["+i+"&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].factorId' name='sets["+i+"&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].factorId' value='' type='hidden'/>";
	html +="<input id='sets["+i+"&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].factorName'  name='sets["+i+"&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].factorName'  type='text' maxlength='35' size='35'/><span class='STYLE1'><strong>*</strong></span>";		
	html += "</td><td>";
	html +="<textarea id='sets["+i+"&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].remark' name='sets["+i+"&com.gpcsoft.es.planform.signuprecord.domain.SignUpCondFactor].remark' ></textarea>";
	html += "</td><td>";
	html +="<a class='abtn' id='remove' onClick='signUpCondFactorView.liveButtonClick(this);'><span>删除</span></a>";
	html += " </td></tr>";
	i++;
	$("#signupCongruous").append(html);
}



signUpCondFactorView.liveButtonClick = function(thisObj){
	var id = thisObj.id;
	var jsonObj = {};
	jsonObj.signupfacId = $(thisObj).parent().parent().find('input[id$=objId]').val();
	jsonObj.factorId = $(thisObj).parent().parent().find('input[id$=factorId]').val();
	jsonObj.factorName = $(thisObj).parent().parent().find('input[id$=factorName]').val();
	jsonObj.remark = $(thisObj).parent().parent().find('textarea[id$=remark]').val();
	// 删除
	if("remove" == id){
		$(thisObj).parent().parent().remove();
	    if(""!=jsonObj.signupfacId){
	    	$.getJSON($('#initPath').val()+'/SignUpCondFactorController.do?method=removeSignUpCondFactor',{signupfacId:jsonObj.signupfacId},function(json){
				alert('删除成功！');
				$("#signUpCondFactorView").loadPage($('#initPath').val()+'/SignUpCondFactorController.do?method=getSignUpCondFactorListByProjectId&projectId='+$("input[name=project.objId]").val());
			});
	    }
	}
}


</script>				