<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>
			如果加入商圈并通过审核成为商圈会员,您可以看到供应商或采购人的联系方式。
			<span id="msgSpan_valid" class="hidden">提示：您目前是正式商圈会员.</span>
			<span id="msgSpan_notvalid" class="hidden">提示：您目前还不是商圈会员或者已过期,加入商圈会员请点击
				<a href="javascript:void(0);" id="joinMember"><strong>申请加入商圈</strong></a>
			</span>
		</li>
	</ul>
</div>

<form id="BusinessMemberForm" name="BusinessMemberForm">
	<input type="hidden" id="objId" name="objId" value=""/>
	<input type="hidden" id="orgInfoId" name="orgInfo.objId" value="${orgInfo.objId}"/>

	<div id="addBusinessMember" class="hidden">
		<div class="formLayout form1Pa">
			<ul>
				<li>
					<label>我的机构名称：</label>
					<span>${orgInfo.orgName}</span>
				</li>	
				<li>
					<label>我想成为：</label>
					<span><input type="radio" id="oneYear" name="timeType" value="1"/>一年会员</span>
					<span><input type="radio" id="twoYear" name="timeType" value="2"/>两年会员</span>
					<span><input type="radio" id="threeYear" name="timeType" value="3"/>三年会员</span>
				</li>
			</ul>	
		</div>
		
		<div class="conOperation center">
  			<button  id="submit" type="button" ><span><spring:message code="globe.submit"/></span></button>
		</div>
	</div>
</form>

<div id="memberList" class="formLayout">
	<h4><span>我申请过的商圈会员</span></h4>
	<table class="frontTableList" id="MembersApplied">
		<thead>
			<tr>
				<th class="left">机构名称</th>
				<th class="center">会员时长</th>
				<th class="date center">到期时间</th>
				<th class="center">会员状态</th>
				<th class="hidden">审核状态</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>

<script type="text/javascript">
var BusinessMemberForm={};

//查看
function　toDetail(objId){
	$('#conBody').loadPage($('#initPath').val()+"/BusinessMemberController.do?method=toView&objId="+objId);
}

//控制加入商圈的显示
BusinessMemberForm.ctrl=function(audit_use_valid) {
	if(audit_use_valid.indexOf('有效')==-1) {
		if(audit_use_valid.indexOf('审核中')==-1) {
			$('#msgSpan_notvalid').removeClass('hidden');
		}
	}
}

$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val("view/smallscale/business/business_member_join.jsp");
	//提交
	$("#submit").click(function(){	
		if(!$('input:radio:checked').val()) {			
			alert('请选择会员时长!');
			return;
		}
		if(!$('#BusinessMemberForm').valid()){alert('请正确填写表单!');return;}

		var businessMember={
				objId:$('#objId').val(),
				orgInfo:{objId:$('#orgInfoId').val()},
				timeType:$('input:radio:checked').val()
		}
		
		$.getJSON($('#initPath').val()+'/BusinessMemberController.do?method=saveJoin',{businessMemberStr:JSON.stringify(businessMember)},
			function(json){
				if(json.failure){alert(json.result);return;}
				alert("提交成功,等待审核!");
				$('#conBody').loadPage($('#initPath').val()+'/BusinessMemberController.do?method=toJoin');
			}
		);
		
	})
	var audit_use_valid = '';
	BusinessMemberForm.oTable = $('#MembersApplied').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgInfo.orgName,timeType,endDate,useStatus,auditStatus',
		'alias':'orgInfo.orgName,timeTypeCN,endDate,useStatusCN,auditStatus',
		'hiddenColumns':'begainDate,endDate',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			BusinessMemberForm.oTable.oSettings = oSettings;
			//循环表格
			$('#MembersApplied tr').not(':first').each(function(i,n){
				audit_use_valid += $(n).find('td[name=useStatus]').text()+','+$(n).find('td[name=auditStatus]').text();
			});
			BusinessMemberForm.ctrl(audit_use_valid);
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			var html = '';
			html += '<td align="center"><a title="查看" href="javascript:void(0);" onclick="toDetail(\''+aData.objId+'\');return false;">查看</a>';
			html += '</td>';
			$(nRow).append(html);
			if(aData["auditStatus"]=='01' && aData["useStatus"]=='00') {
				$(nRow).find('td[name=useStatus]').html('审核中...');
			}		
			if(aData["auditStatus"]=='02' && aData["useStatus"]=='01') {
				$(nRow).find('td[name=useStatus]').html('有效');
			}	
			if(aData["auditStatus"]=='03' && aData["useStatus"]=='00') {
				$(nRow).find('td[name=useStatus]').html('审核未通过');
			}		
			if(StringToDate(aData["endDate"].replace('-', "/").replace('-', "/")).getTime() < new Date().getTime()) {//过期
				$(nRow).find('td[name=useStatus]').html('已过期');
			}	
			if(aData["useStatus"]=='02') {//过期
				$(nRow).find('td[name=useStatus]').html('已过期');
			}	
			return nRow;
		},
		"params":{"owner":"mine"},
		"sAjaxSource" : $('#initPath').val()+ "/BusinessMemberController.do?method=list",
		'searchZone':'BusinessMemberForm'
	});

	//加入商圈
	$('#joinMember').click(function(){
		$('#addBusinessMember').removeClass('hidden');
	})
});
</script>
