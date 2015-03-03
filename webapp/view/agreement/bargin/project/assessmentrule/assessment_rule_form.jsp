<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form name="AssessmentRuleForm" id="AssessmentRuleForm" method="post">
<input type="hidden" name="objId" id="objId" value="${assessmentRule.objId }"/>
<div id = "addAssessmentRule" class="formLayout form1Pa">
    <h4><span>项目规则信息</span></h4>
	<ul>
		<li>
			<label>规则名称：</label>
			<input type="text" name="name" id="name" value="${assessmentRule.name}"/>
			<span class="eleRequired">*</span>
		</li>	
		<li>
			<label>规则品目：</label>
			<input type="text" name="categoryName" id="assessmentRule.name" value="${assessmentRule.categoryName}" class="sysicon siSearch required" readonly="readonly"/>
			<input type="hidden" name="categoryId" id="assessmentRule.objId" value="${assessmentRule.categoryId}"/>
			<span class="eleRequired">*</span>
		</li>	
		<li>
			<label>规则附件：</label>
			<input type="file" name="assessment_File">
			<c:if test="${ assessmentRule.assessmentFile != null }">
				<span><a href="AttachmentController.do?method=downLoadFile&objId=${assessmentRule.assessmentFile.objId}">${assessmentRule.assessmentFile.viewName }</a></span>
			</c:if>
		</li>
	</ul>
</div>
</form>
  	
<div class="conOperation center">
  	<button  id="AssessmentRuleSave" type="button" ><span><spring:message code="globe.save"/></span></button>
  	<button  name="historyBackBtn" type="button" ><span><spring:message code="globe.return"/></span></button>
</div>
	
	
<script type="text/javascript">
var AssessmentRuleForm={};

$(document).ready(function(){
	//保存
	$("#AssessmentRuleSave").click(function(){				
		if(!$('#AssessmentRuleForm').valid()){alert('请正确填写表单!');return;}
		$('#AssessmentRuleForm').ajaxSubmit({
			url:$("#initPath").val()+"/AssessmentRuleController.do?method=saveAssessmentRule",
			dataType:'json',
			success:function(json){
				if(json.result=='success') {
					alert("操作成功！");
				}else {
					alert(ascii2native(json.result));
				}
				$("button[name=historyBackBtn]").click();//返回
			},
			error:function(msg){
				alert("操作失败！");
			}
		});
	})

	//选择品目
	$("input[name=categoryName]").click(function(){
		 $.epsDialog({
		        title:'请选择品目',
		        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=assessmentRule&className=PurCategory&action=listTop&isOpen=1&isCheckBox=true&checkValues='+$("input[id=assessmentRule.objId]").val()
		 }); 
	})
	
});
</script>
