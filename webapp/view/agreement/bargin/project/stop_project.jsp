<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">
	<h4><span>项目基本信息</span></h4>
	<ul>
		<li>
			<label>项目名称：</label>
			<span>${project.projName }</span>
		</li>
		<li>
			<label>项目编号：</label>
			<span>${project.projCode }</span>
		</li>
		<li class="fullLine">
			<label>报名时间：</label>
			<span><fmt:formatDate value="${project.signUpSTime }" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;--&nbsp;<fmt:formatDate value="${project.signUpETime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
		</li>
		<li class="fullLine">
			<label>报价时间：</label>
			<span><fmt:formatDate value="${project.evalStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;--&nbsp;<fmt:formatDate value="${project.evalEndTime }"pattern="yyyy-MM-dd HH:mm:ss"/></span>
		</li>
		<li>
			<label>采购方式：</label>
			<span>${project.ebuyMethodCN }</span>
		</li>
		<li>
			<label>总预算：</label>
			<span><fmt:formatNumber value="${project.budgetTotalMoney }" pattern="#,##0.00#" />元</span>
		</li>
	</ul>
</div>
		
<div class="formLayout form2Pa">
<form id="stopProjectApplyForm" method="post">
	<!--needAudit用于标记终止项目操作是否需要审核（true:需要审核；false:不需要审核）-->
	<input type="hidden" name="needAudit" value="false" />
	<input type="hidden" name="project.objId" value="${project.objId}" />
	<input type="hidden" name="failEbuyMethod" value="${project.ebuyMethod}" />
	<input type="hidden" name="agentyId" value="${project.agencies.objId}" />
	<ul>
		<li class="formTextarea">
			<label for="reasonDesc">终止原因：</label>
			<textarea name="reasonDesc" class="required" maxlength="200"></textarea>
		</li>
	</ul>
</form>
</div>

<div class="conOperation">
	<button type="button" id="stopProjectSubmitBtn" class="largeBtn"><span>确定</span></button>
	<button type="button" id="stopProjectCloseBtn" class="largeBtn"><span>取消</span></button>
</div>

<script type="text/javascript">
var StopProjectView = {};

//终止项目
StopProjectView.stopProject = function(){
	if(!$("#stopProjectApplyForm").valid()){ alert("请正确填写表单!");return; }
    if(window.confirm("确定终止该项目吗?")){
        $("#stopProjectSubmitBtn").attr("disabled",true);

    	$('#stopProjectApplyForm').ajaxSubmit({
    		url: $('#initPath').val()+"/BargainProjectController.do?method=saveStopProjectInfo",
			dataType:'json',
			success:function(json){
    			alert("操作成功!");
    			window.location.reload();
    			$("#stopProjectCloseBtn").click();
			},
			error:function(msg){
				alert(JSON.stringify(msg));
				$("#stopProjectSubmitBtn").attr("disabled",false);
			}
		});	
	}
}

$(document).ready(function(){
	//确定终止
	$("#stopProjectSubmitBtn").click(function(){
		StopProjectView.stopProject();
	});
	
	//关闭弹出层
	$("#stopProjectCloseBtn").click(function(){
		$('.epsDialogClose').trigger('click');
	});
})
</script>