<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/common/planTemplateTask.js"></script>
<div class="formLayout form2Pa">        
	<form id="projProcessRuleForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${projProcessRule.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="project.objId" id="projectId" value="${param.projectId}"/>
		<input type="hidden" name="fromType" id="fromType" value="${fromType}"/>
		<input type="hidden" id="sysConfigItemListBooleanLength" value="${sysConfigItemListBooleanLength}"/>
		<input type="hidden" id="sysConfigItemListStringLength" value="${sysConfigItemListStringLength}"/>
		<input type="hidden" id="sysConfigItemListDateLength" value="${sysConfigItemListDateLength}"/>
		<input type="hidden" id="fileDOC" value="${fileDOC}"/>
		<h5><span>设置项目规则</span></h5>
		<c:if test="${sysConfigItemListBooleanLength!=0}">
     		<ul>
				<c:forEach items="${sysConfigItemListBoolean}" var="sysConfigItem" varStatus="i">
					<li>
						<label class="short">
						<input type="checkbox" id="resValue_booleanCheckbox_${i.count}" code="${sysConfigItem.code}" name="resValue" style="margin-top:5px;" value='${i.count}' onclick="projProcessRuleForm.checkData('${i.count}','${sysConfigItem.code}',this);"
						<c:if test="${sysConfigItem.code=='MAKETENDER'||sysConfigItem.code=='DOPURDOC'}">disabled="disabled"</c:if>/>
						</label>
						<span>${sysConfigItem.name}</span>
						<input type="hidden" id="sysItemId_boolean_${i.count}" name="sysItemId" value="${sysConfigItem.objId}"/>
						<input type="hidden" id="sysItemName_boolean_${i.count}" name="sysItemName" value="${sysConfigItem.name}"/>
						<input type="hidden" id="code_boolean_${i.count}" name="code" value="${sysConfigItem.code}"/>
						<input type="hidden" id="resValue_boolean_${i.count}" name="resValue" value="false" />
						<input type="hidden" id="objId_boolean_${i.count}" name="objId" value=""/>
					</li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${sysConfigItemListStringLength!=0}">
			<ul>
				<c:forEach items="${sysConfigItemListString}" var="sysConfigItem" varStatus="i">
					<li>
						<label class="short">${sysConfigItem.name}：</label>
						<span>
							<input type="text" id="resValue_string_${i.count}" value=""/>
							<input type="hidden" id="sysItemId_string_${i.count}" name="sysItemId" value="${sysConfigItem.objId}"/>
							<input type="hidden" id="sysItemName_string_${i.count}" name="sysItemName" value="${sysConfigItem.name}"/>
							<input type="hidden" id="code_string_${i.count}" name="code" value="${sysConfigItem.code}"/>
							<input type="hidden" id="objId_string_${i.count}" name="objId" value=""/>
						</span>
					</li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${sysConfigItemListDateLength!=0}">
			<ul>
				<c:forEach items="${sysConfigItemListDate}" var="sysConfigItem" varStatus="i">
					<li>
						<label class="short">${sysConfigItem.name}：</label>
						<span>
							<input type="text" id="resValue_date_${i.count}"  value=""/>
							<input type="hidden" id="sysItemId_date_${i.count}" name="sysItemId" value="${sysConfigItem.objId}"/>
							<input type="hidden" id="sysItemName_date_${i.count}" name="sysItemName" value="${sysConfigItem.name}"/>
							<input type="hidden" id="code_date_${i.count}" name="code" value="${sysConfigItem.code}"/>
							<input type="hidden" id="objId_date_${i.count}" name="objId" value=""/>
						</span>
					</li>
				</c:forEach>
			</ul>
		</c:if>
	</form>
	<div class="conOperation" style="text-align:center">
		<button id="projProcessRuleSave"><span class="add">保存</span></button>
	</div>
</div>
<script>
var projProcessRuleForm={};

$(document).ready(function(){
	var fileIsCreate = $("#fileDOC").val();
	if(fileIsCreate=='isCreate'){   //如果项目已经存在招标文件或询价文件，不能修改规则
		$('#projProcessRuleSave').hide();
	}
	
	var sysConfigItemListBooleanLength = $('#sysConfigItemListBooleanLength').val();
	var sysConfigItemListStringLength = $('#sysConfigItemListStringLength').val();
	var sysConfigItemListDateLength = $('#sysConfigItemListDateLength').val();

	for (var i=1;i<sysConfigItemListDateLength+1;i++) {//显示控件
		$('#resValue_date_'+i).epsDatepicker({timeShow:true});
	}

	$.getJSON($('#initPath').val()+'/ProjProcessRuleController.do?method=getProjProcessRuleList&projectId='+$('#projectId').val(), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		var projProcessRuleList = json.projProcessRuleList;
		var sysConfigItemListBoolean = json.sysConfigItemListBoolean;
		var sysConfigItemListString = json.sysConfigItemListString;
		var sysConfigItemListDate = json.sysConfigItemListDate;
		$.each(sysConfigItemListBoolean,function(i,n){//布尔[Boolean]类型的值回填
			$.each(projProcessRuleList,function(k,o){
				if (n.objId==o.sysItemId) {
						$('#objId_boolean_'+(i+1)).val(o.objId);
					if (o.resValue=='true') {
						$('#resValue_boolean_'+(i+1)).val('true');
						$('#resValue_booleanCheckbox_'+(i+1)).attr('checked',true);
					}
				}
			});
			
		});
		$.each(sysConfigItemListString,function(i,n){//文本[String]类型的值回填
			$.each(projProcessRuleList,function(k,o){
				if (n.objId==o.sysItemId) {
						$('#objId_string_'+(i+1)).val(o.objId);
						$('#resValue_string_'+(i+1)).val(o.resValue);
				}
			});
			
		});
		$.each(sysConfigItemListDate,function(i,n){//时间[Date]类型的值回填
			$.each(projProcessRuleList,function(k,o){
				if (n.objId==o.sysItemId) {
						$('#objId_date_'+(i+1)).val(o.objId);
						$('#resValue_date_'+(i+1)).val(o.resValue);
				}
			});
			
		});
		
	});
	
	projProcessRuleForm.checkData = function(count,ruleCode,_this){//选中或取消选中
		var resValue = $('#resValue_boolean_'+count).val();
		if (resValue=='false') {
			if (ruleCode=='SUBPROJECT') {//是否分包
				$(_this).parent().parent().parent().find('input[type=checkbox][code=DOPURDOC]').attr('disabled',false).attr('checked',true);
				$(_this).parent().parent().parent().find('input[type=checkbox][code=MAKETENDER]').attr('checked',true);
			}
		}else{
			if (ruleCode=='SUBPROJECT') {//是否分包
				$(_this).parent().parent().parent().find('input[type=checkbox][code=DOPURDOC]').attr('disabled',true).attr('checked',false);
				$(_this).parent().parent().parent().find('input[type=checkbox][code=MAKETENDER]').attr('disabled',true).attr('checked',false);
			}
		}

		//是否按标段制作标书
		$(_this).parent().parent().parent().find('input[type=checkbox][code=DOPURDOC]').unbind();
		$(_this).parent().parent().parent().find('input[type=checkbox][code=DOPURDOC]').click(function(){
			if ($(this).attr('checked')) {
				$(_this).parent().parent().parent().find('input[type=checkbox][code=MAKETENDER]').attr('checked',true).attr('disabled',true);
			} else {
				$(_this).parent().parent().parent().find('input[type=checkbox][code=MAKETENDER]').attr('disabled',false);
			}
		});
		$(_this).parent().parent().parent().find('input[type=checkbox]').each(function(){
			$(this).parent().parent().find('input[name=resValue]').val($(this).attr('checked'));
		});
	}

	//提交
	$('#projProcessRuleSave').click(function(){
		
		var josnObj = {};
		for(var i = 0 ; i<sysConfigItemListBooleanLength ; i++){//组织布尔[Boolean]类型的值
			var keyname = 'sets['+i+'&com.gpcsoft.epp.projrule.domain.ProjProcessRule]';
			josnObj[keyname+'.objId']=$('#objId_boolean_'+(i+1)).val();
			josnObj[keyname+'.sysItemId']=$('#sysItemId_boolean_'+(i+1)).val();
			josnObj[keyname+'.sysItemName']=$('#sysItemName_boolean_'+(i+1)).val();
			josnObj[keyname+'.code']=$('#code_boolean_'+(i+1)).val();
			josnObj[keyname+'.resValue']=$('#resValue_boolean_'+(i+1)).val();
		}
		for(var j = 0 ; j<sysConfigItemListStringLength ; j++){//组织文本[String]型的值
			var keyname = 'sets['+(sysConfigItemListBooleanLength+j)+'&com.gpcsoft.epp.projrule.domain.ProjProcessRule]';
			josnObj[keyname+'.objId']=$('#objId_string_'+(j+1)).val();
			josnObj[keyname+'.sysItemId']=$('#sysItemId_string_'+(j+1)).val();
			josnObj[keyname+'.sysItemName']=$('#sysItemName_string_'+(j+1)).val();
			josnObj[keyname+'.code']=$('#code_string_'+(j+1)).val();
			josnObj[keyname+'.resValue']=$('#resValue_string_'+(j+1)).val();
		}
		for(var k = 0 ; k<sysConfigItemListDateLength ; k++){//组织日期[Date]型的值
			var keyname = 'sets['+(sysConfigItemListStringLength+sysConfigItemListBooleanLength+k)+'&com.gpcsoft.epp.projrule.domain.ProjProcessRule]';
			josnObj[keyname+'.objId']=$('#objId_date_'+(k+1)).val();
			josnObj[keyname+'.sysItemId']=$('#sysItemId_date_'+(k+1)).val();
			josnObj[keyname+'.sysItemName']=$('#sysItemName_date_'+(k+1)).val();
			josnObj[keyname+'.code']=$('#code_date_'+(k+1)).val();
			josnObj[keyname+'.resValue']=$('#resValue_date_'+(k+1)).val();
		}
		$.getJSON($('#initPath').val()+'/ProjProcessRuleController.do?method=saveProjProcessRule&projectId='+$('#projectId').val(),josnObj, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').loadPage("ProjectController.do?method=toProjectInfo&objId="+$("#projectId").val());
		});
		
	});

});
</script>