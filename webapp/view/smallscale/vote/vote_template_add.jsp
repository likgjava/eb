<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form id="voteTemplateForm" name="voteTemplateForm" method="post">
	<input type="hidden" name="objId" id="voteTemplateId" value="${voteTemplate.objId }"/>
	<input type="hidden" name="templateCode" id="templateCode" value="${voteTemplate.templateCode }"/>
	<input type="hidden" name="" id="voteIsSingleVote" value="${voteTemplate.isSingleVote }"/>
	
	<div>
		<div class="formLayout form2Pa">
			<h4><span>新增评选主题</span></h4>
			<ul>
				<li  class="fullLine">
					<label>评选主题：</label>
					<input type="text" name="templateName" id="templateName" value="${voteTemplate.templateName }" class="required" size="50"/><span class="eleRequired">*</span>
				</li>
				<li>
					<label>活动开始时间：</label>
					<input type="text" name="startTime" id="startTime" value="${voteTemplate.startTime }" readonly="readonly" class="required"/><span class="eleRequired">*</span>
				</li>
				<li>
					<label>活动结束时间：</label>
					<input type="text" name="endTime" id="endTime" value="${voteTemplate.endTime }" readonly="readonly" class="required"/><span class="eleRequired">*</span>
				</li>
				<li>
					<label>评价专员：</label>
					<input type="text" name="appraiseCommissioner" id="appraiseCommissioner" value="${voteTemplate.appraiseCommissioner }" class="required" />
				</li>
				<li>
					<label>联系手机：</label>
					<input type="text" name="commissionerPhone" id="commissionerPhone" value="${voteTemplate.commissionerPhone }" class="cnMobile" />
				</li>
				<li>
					<label>电子邮箱：</label>
					<input type="text" name="commissionerEmail" id="commissionerEmail" value="${voteTemplate.commissionerEmail }" class="email" />
				</li>
				<li>
					<label>办公电话：</label>
					<input type="text" name="commissionerTel" id="commissionerTel" value="${voteTemplate.commissionerTel }" class="cnPhone" />
				</li>
				<li>
					<label>传真：</label>
					<input type="text" name="commissionerFax" id="commissionerFax" value="${voteTemplate.commissionerFax }" class="cnPhone" />
				</li>
				<li>
					<label>评选类型：</label>
					<input type="radio" name="isSingleVote" id="isSingleVote_no" value="false" checked="checked"/>指标评选
					<input type="radio" name="isSingleVote" id="isSingleVote_yes" value="true"/>单一评选
				</li>
				<li class="fullLine">
					<label>主题编号：</label>${voteTemplate.templateCode}
				</li>
			</ul>
		</div>
		
		<div>
			<h4 style="background-color: #CCE2F8;">主题描述：</h4>
			<div id="htmlEditor"></div>
			<textarea name="templateComment" id="templateComment" class="hidden">${voteTemplate.templateComment}</textarea>
		</div>
	
		<div class="conOperation">
			<button type="button" id="voteTemplateSave"><span><spring:message code="globe.save"/></span></button>
			<button type="button" id="historyBackBtn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
	   </div>
   </div>
</form>

<script>
var htmlEditor;

$(document).ready(function(){
	$("#startTime").epsDatepicker();
	$("#endTime").epsDatepicker();

	//修改时评选类型判定
	if($('#voteIsSingleVote').val() != ''){
		var isSingleVote_no = document.getElementById('isSingleVote_no');
		var isSingleVote_yes = document.getElementById('isSingleVote_yes');

		if($('#voteIsSingleVote').val() == 'false'){
			isSingleVote_no.checked = 'checked';
			isSingleVote_yes.checked = '';
		}
		if($('#voteIsSingleVote').val() == 'true'){
			isSingleVote_no.checked = '';
			isSingleVote_yes.checked = 'checked';
		}
	}
	
	//加载ExtJs的HTML编辑器
	htmlEditor = new Ext.form.HtmlEditor({
		height: 220,
		anchor: '100%'
	});
	new Ext.panel.Panel({
	    renderTo: 'htmlEditor',
	    layout: 'anchor',
	    border : false,
	    items: [htmlEditor]
	});
	htmlEditor.setValue($("#templateComment").val()+" ");
});

//返回
$('#historyBackBtn').click(function(){
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do");
});

//保存
$('#voteTemplateSave').click(function(){
	$("#templateComment").val(htmlEditor.getValue());
	if(!$('#voteTemplateForm').valid()){alert('请正确填写表单!');return;}
	$('#voteTemplateForm').ajaxSubmit({
		url:$("#initPath").val()+"/VoteTemplateController.do?method=save",
		dataType:'json',
		success:function(json){
			alert("操作成功！");
			$("button[id=historyBackBtn]").click();//返回
		},
		error:function(msg){
			alert("操作失败！");
		}
	});
});
</script>