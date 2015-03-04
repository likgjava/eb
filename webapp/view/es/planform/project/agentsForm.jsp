<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers">
	<form id="agentForm" method="post">
		<div class="formLayout form2Pa">
     		<ul>
				 <li class="fullLine">
				     <label for="input02">招标中心：</label>
			         <input type="text" name="orgName" id="orgName" value="" class="required" />
			         <input type="hidden" name="orgInfoId" id="orgInfoId" value=""/>
				</li>
			</ul>
		<div class="conOperation">
			<button id="agentSubmit" type="button" tabindex="19"><span>确认</span></button>
			<button id="agentReturn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
		</div>
		</div>
	</form>
</div>

<script type="text/javascript">

$(document).ready(function(){
	var orgInfoId = new Array();
	$('input[name=orgInfoObjId]').each(function(){
		orgInfoId.push($(this).val());
	});
	var orgIds = orgInfoId.toString();
 //选择招标中心
	$("input[id=orgName]").autocomplete(
		$('#initPath').val() + '/UserApiController.do?method=getOrgInfoForAgent&orgInfoIds='+orgIds, 
		{
			matchColumn:'orgName',//作为查询显示, 被选中之后匹配的列
			extraParams:{"agencyId":null,"agencyId_op":"!="},//查询条件
			mustMatch: true,
			formatItem: function(data, i, total) {
				return data.orgName;
			},
			formatMatch: function(data, i, total) {
				return data.orgName;
			},
			formatResult: function(data) {
				return data.orgName;
			}
		}
	).result(function(event,data,formatted){
		if(data){
			$("input[id=orgName]").val(data.orgName);//回填id
			$("input[id=orgInfoId]").val(data.objId);//回填id
		}
	});
	//提交
	$('#agentSubmit').click(function(){
		var orgName = $('#orgName').val();
		var orgInfoId = $('#orgInfoId').val();
		if(!$('#agentForm').valid()){alert('请正确填写表单!');return;}
		if(window.confirm('确定要提交招标中心吗？')){
			$.getJSON($('#initPath').val()+'/UserApiController.do?method=submitAgentForBlockTrade',{'orgName':orgName,'orgInfoId':orgInfoId},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$("#agentReturn").click();
				$('#conBody').loadPage($('#initPath').val()+'/UserApiController.do?method=toBlockTradeAgent');
			});
		}
	});

	//关闭弹出层
	$("#agentReturn").click(function(){
		$('#epsDialogCloseNoReload').click();
	});
});


</script>