<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout form2Pa">
	<h5><span>报名符合性要求设置</span></h5>
	<ul>
		<li><a onClick="insertRow()" class="linkButton"><span>新增指标</span></a></li>
	</ul>
	<table width="100%" border="0" class="tableList">
		<thead>
			<tr>
                <th width="30%">指标名称</th>
				<th width="30%">说明</th>
				<th width="15%">操作</th>
	  		</tr>
		</thead>
		<tbody id="signupCongruous">
		<c:forEach items="${signUpCondFactorList}" var="signUpCondFactor">
	   		<tr>
                <td>
	                <input name="signupfacId" value="${signUpCondFactor.objId }" type="hidden"/>
	                <input  name="factorId" value="${signUpCondFactor.factorId }" type="hidden"/>
	                <input name='factorName'  maxlength='35' size='25' value="${signUpCondFactor.factorName }" type="text"/>
	                <span style='color: red;' size='1'>*</span>
	            </td>
                <td width='70%'>
	                <textarea name='remark' style='width:80%;height:40px;' >${signUpCondFactor.remark }</textarea>
	                <span style='color:red;' size='1'>*</span>
                </td>
                <td class="center"><a class='abtn' id='remove' onClick='signUpCondFactorView.liveButtonClick(this);'><span>删除</span></a>
                    &nbsp;&nbsp;&nbsp;&nbsp;<a class='hidden' id='update' onClick='signUpCondFactorView.liveButtonClick(this);' name='savesignUpCondFactor'><span>保存</span></a>
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<script>
var signUpCondFactorView ={}; 
//新增指标
function insertRow(){
	var html = "";
	html += "<tr><td>";
	html+="<input  name='signupfacId' value='' type='hidden' /><input  name='factorId' value='' type='hidden'/><input name='factorName'  type='text' maxlength='35' size='35' style='' /><span style='color: red;' size='1'>*</span>";		
	html += "</td><td width='70%'>";
	html+="<textarea name='remark' style='width:80%;height:40px;'></textarea>";
	html += " <span style='color:red;' size='1'>*</span></td><td class='center'>";
	html+="<a  id='remove' onClick='signUpCondFactorView.liveButtonClick(this);'><span>删除</span></a>";
	html+="&nbsp;&nbsp;&nbsp;&nbsp;<a class='hidden' id='update' onClick='signUpCondFactorView.liveButtonClick(this);' name='savesignUpCondFactor'><span>保存</span></a>";
	html += " </td></tr>";
	$("#signupCongruous").append(html);
}



signUpCondFactorView.liveButtonClick = function(thisObj){
	var id = thisObj.id;
	var jsonObj = {};
	jsonObj.signupfacId = $(thisObj).parent().parent().find('input[name=signupfacId]').val();
	jsonObj.factorId = $(thisObj).parent().parent().find('input[name=factorId]').val();
	jsonObj.factorName = $(thisObj).parent().parent().find('input[name=factorName]').val();
	jsonObj.remark = $(thisObj).parent().parent().find('textarea[name=remark]').val();
	if(""==jsonObj.signupfacId){
		$(thisObj).parent().parent().remove();return false;
	}
	// 删除
	if("remove" == id){
		$(thisObj).parent().parent().remove();
	    if(""!=jsonObj.signupfacId){
	    	$.getJSON($('#initPath').val()+'/SignUpCondFactorController.do?method=removeSignUpCondFactor',{signupfacId:jsonObj.signupfacId},function(json){
				alert('删除成功！');
			});
	    }
	    if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		  } else {
			$("#myDesktop").click();
		}
	}
}


</script>				