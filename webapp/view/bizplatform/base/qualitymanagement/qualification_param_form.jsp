<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
	$.validator.addMethod("qualityCodeUnique",function(value,element,param){return uniqueHandler("Qualification",param,value,$("#objId").val());},'该资质编号已存在');

	//表单验证
	$("#qualificationParamForm").validate({
		rules:{
			qualityCode:{qualityCodeUnique:"qualityCode"}
		}
	});	

	// 如果是级别，则将级别折分
	if($('#paramType option:selected').val() == "5") {
		$('#addLevel').show();
		$('#paramUnit').hide();
		$('#paramUnit').find("input").removeClass("required");
		var levelArry = $('#level').val().split(",");
		for(var i = 0; i <  levelArry.length; i++) {
			var nrow = $('#levelShow table tfoot tr').clone(true);
			$(nrow).find("td:eq(0) input").val(levelArry[i].split("#")[0]);
			$(nrow).find("td:eq(1) input").val(levelArry[i].split("#")[1]);
			$(nrow).appendTo('#qualificationLevel'); 
		}
		if($('#levelShow table tbody tr').length > 0) {
			$('#levelShow').show();
		}else {
			$('#levelShow').hide();
		}
	}
})

// 保存资质分类
$('button[id=savequalificationParamBtn]').click(function() {
	if(!$('#qualificationParamForm').valid()){alert('请正确填写表单!');return;}

	
	var levelStr = "";

	if($('#paramType option:selected').val() == "5") {
		// 将资质按一定的格式封装好
		$('#qualificationLevel tr').each(function(i,n) {
			if(levelStr == "") {
				levelStr = $(n).find("td:eq(0) input").val()+"#"+$(n).find("td:eq(1) input").val();
			}else {
				levelStr = levelStr + "," + $(n).find("td:eq(0) input").val()+"#"+$(n).find("td:eq(1) input").val();
			}
		});
	}
	$('#level').val(levelStr);
	$.getJSON($("#initPath").val()+"/QualificationParamController.do?method=save",formToJsonObject('qualificationParamForm'),function(json){
		if(json.success == "true"){

			// 将详情的样式改为已修改
			$("tr[id="+json.qualificationParam.objId+"]").find("span").each(function() {
				if($(this).hasClass("siEditBtn")) {
					$(this).removeClass("siEditBtn").addClass("siEdit");
				}
			})
			$('#epsDialogClose').click(); 
		}
		if(json.failure) {
			return;
		}
	});
})

// 级别显示
$('#paramType').change(function() {
	if($(this).find("option:selected").val()=="5") {
		$('#addLevel').show();
		$('#paramUnit').hide();
		$('#paramUnit').find("input").removeClass("required");
		if($('#levelShow table tbody tr').length > 0) {
			$('#levelShow').show();
		}else {
			$('#levelShow').hide();
		}
	}else {
		$('#addLevel').hide();
		$('#levelShow').hide();
		$('#paramUnit').show();
		$('#paramUnit').find("input").addClass("required");
	}
});

// 添加级别 
$('#addLevel').click(function() {
	$('#levelShow').show();
	$('#levelShow table tfoot tr').clone(true).appendTo('#qualificationLevel'); 
});

// 删除级别
$('[name=deleteLevel]').click(function() {
	$(this).parent().parent().remove();
	if($('#qualificationLevel tr').length <= 0) {
		$('#levelShow').hide();
	}
});
</script>

<div class="partContainers formLayout">
	<form:form id="qualificationParamForm" method="post" modelAttribute="qualificationParam">
	<form:hidden path="objId"></form:hidden>
	<form:hidden path="level"></form:hidden>
     	<ul>
	     	<li>
	     		<label>资质名称：</label>
					<span>${qualificationParam.qualityName}</span>
    	    </li>
	     	<li>
	     		<label>资质编号：</label>
	     			<form:input path="qualityCode" cssClass="required"></form:input><span class="eleRequired">*</span>
    	    </li>
	     	
	     	<li id="paramUnit">
	     		<label>参数单位：</label>
	     			<form:input path="unit" cssClass="required"></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>参数类型：</label>
	     		<form:select path="paramType">
	     		<form:option value="1">字符</form:option>
	     		<form:option value="2">整数</form:option>
	     		<form:option value="3">含小数</form:option>
	     		<form:option value="4">日期</form:option>
	     		<form:option value="5">级别</form:option>
	     		</form:select>&nbsp;<a href="javascript:void(0);" id="addLevel"  class="hidden">添加级别</a>
    	    </li>
    	    <li id="levelShow" class="hidden">
    	    	<label>级别：</label>
				<table style="width: 80%">
				<thead>
					<tr>
						<th style="text-align:center">级别名称</th>
						<th style="text-align:center">级别值</th>
						<th style="text-align:center" class="operation">操作</th>
					</tr>
				</thead>
				<tbody id="qualificationLevel">
				</tbody>
				<tfoot id="levelTemplate" class="hidden">
					<tr  style="width: 80%">
						<td><input type="text" style="width:80px" value=""/></td>
						<td><input type="text" style="width:50px" class="digits" value=""/></td>
						<td class="operation"><a href="javascript:void(0);" name="deleteLevel">删除</a></td>
					</tr>
				</tfoot>
				</table>
		</li>
		<li>
	     		<label>是否显示：</label>
	     		<form:radiobutton path="isDisplay" value="true" />显示
				<form:radiobutton path="isDisplay" value="false"/>不显示
    	    </li>
	     	<li>
	     		<label>是否必填：</label>
					<form:radiobutton path="isRequired" value="true" />必填
					<form:radiobutton path="isRequired" value="false" />非必填
    	    </li>
    	</ul>
     </form:form>
        <div class="conOperation">
				<button type="button" id="savequalificationParamBtn"><span><spring:message code="globe.save"/></span></button>
		   </div>
 </div>
    	    