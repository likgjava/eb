<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<div class="formLayout form2Pa">
	<input type="hidden" id="requirementId" name="requirementId" value="${requirement.objId}">
	<h4 class="title"><span>采购需求信息</span><span class="eleRequired"></span></h4>
	<ul>
		<li class="fullLine">
	 		<label>需求标题：</label>
	 		<span>${requirement.title}</span>
	 	</li>
	 	<li>
	 		<label>采购品目：</label>
	 		<span>${requirement.category.categoryName}</span>
	 	</li>
	 	<li>
	 		<label>发布机构：</label>
	 		<span>${requirement.pubOrg.orgName}</span>
	 	</li>
	 	<li>
	 		<label>采购数量：</label>
	 		<span>${requirement.purchaseQty}</span>
	 	</li>
	 	<li>
	 		<label>采购预算（元）：</label>
	 		<fmt:formatNumber value="${requirement.purchaseBudget}" pattern="#,##0.00#" />
	 	</li>
	 	<li>
	 		<label>供货区域：</label>
	 		<span>${requirement.districtNames}</span>
	 	</li>
	 	<li>
	 		<label>结束时间：</label>
	 		<fmt:formatDate value="${requirement.endTime}" pattern="yyyy-MM-dd"/>
	 	</li>
	 	<li>
	 		<label>电子邮箱：</label>
	 		<span>${requirement.email }</span>
	 	</li>
	 	<li>
	 		<label>联系电话：</label>
	 		<span>${requirement.linkTel }</span>
	 	</li>		 	
	 	<li>
	 		<label>联系人：</label>
	 		<span>${requirement.linkMen}</span>
	 	</li>
	 	<li>
	 		<label>发布时间：</label>
	 		<fmt:formatDate value="${requirement.pubTime}" pattern="yyyy-MM-dd"/>
	 	</li>
	 	<li class="fullLine">
	 		<label>创建人：</label>
	 		<span>${requirement.createUser.usName}</span>
	 	</li>
	</ul>
		<h4 class="title"><span>需求报名信息</span><span class="eleRequired"></span></h4>
	
</div>

<div class="formTips attention">
	<form id="requirementRegSearchForm">
	<ul>
		<li>
		<span id="batPublishBulletinSpan">注意：报名的机构需要机构信息审核通过后才能 审核报名信息<br>
			机构名称：<input type="text" name="regOrg.orgName" value="">
			<input type="hidden" name="regOrg.orgName_op" value="like">
			<button id = "requirementRegSearch" type="button" onclick="requirementRegList.getRequirementRegList();"><span>查询</span></button>
			&nbsp;可在此批量
			<a id="batPubrequirementReg" href="javascript:void(0);" onclick="{if(requirementRegList.batConfrim()){requirementRegList.auditRequirementReg(requirementRegList.oTable.dtSelects());}}"><strong>审核报名</strong></a>
		</span>
		</li>
	</ul>
	</form>
</div>

<div id="epsTabs">
		<ul>
			<li><a href="#requirementReg" id = "tabs_pass" class="refreshData"><span>报名列表</span></a></li>
			<li><a href="#requirementReg" id = "tabs_to_pass" class="refreshData"><span>待审核</span></a></li>
		</ul>
  		<div id="requirementReg">
			<!-- 需求列表 -->
			<div id="tabDemo">
				<table class="frontTableList" id="requirementRegList">
			      <thead>
			        <tr>
			          <th class="left omission" omiLength="4">报名机构</th>
			          <th class="left">联系人</th>
			          <th class="left">联系电话</th>
			          <th class="left">报名时间</th>
			          <th class="left">报名附件</th>
			          <th class="operation">操作</th>
			        </tr>
			      </thead>
		      	  <tbody>
		      	  </tbody>
				</table>
			</div>
		</div>
</div>
<div class="conOperation">
	<button name="historyBackBtn"><span>返回</span></button>
</div>
<script type="text/javascript">


var requirementRegList={};
requirementRegList.oTable;

//添加附件事件
requirementRegList.downLoadFile = function(id){
	$.epsDialog({
		title:"附件下载",
		url:$("#initPath").val()+"/AttachmentController.do?defineSelf=qualificationFile&isSelect=yes&attachRelaId="+id,
		width: 600,
		height: 300
	});
}

//审核需求报名
requirementRegList.auditRequirementReg = function(){
	$.epsDialog({
		title:"批量审核",
		content:"<div class='conOperation'>" +
		"<button type='button' class='largeBtn' onclick='requirementRegList.batAudRequirementReg(\"02\");'><span>通过</span></button>" +
		"<button type='button' class='largeBtn' onclick='requirementRegList.batAudRequirementReg(\"03\");'><span>不通过</span></button>" +
		"</div>",
		height:100,
		width:200
	});
}

//批量审核
requirementRegList.batAudRequirementReg = function(auditStatus){
	$.getJSON($('#initPath').val()+'/RequirementRegController.do?method=updateStatus', {objIds:requirementRegList.oTable.dtSelects(),"auditStatus":auditStatus}, function(json){
		if(json.failure){
			if(json.result)alert(json.result);return;
		}else{
			alert("操作成功！");
		}
		$('.epsDialogClose').trigger('click');
		requirementRegList.getRequirementRegList();
	});
}


//批量操作前确认
requirementRegList.batConfrim = function(){
	if(requirementRegList.oTable.dtSelects().length<=0){
		alert('请至少选择一行数据！');
		return false
	}else{
		return true;
	}
}

//取得列表
requirementRegList.getRequirementRegList = function(param){
	if(null==requirementRegList.oTable){
		requirementRegList.oTable = $('#requirementRegList').dataTable({   
			"params":param,
			'searchZone':'requirementRegSearchForm',
			'singleSelect':true,	
			'checkbox':true,		
			'queryColumns':'regOrg.orgName,linkMen,linkTel,createTime,file',
			'hiddenColumns':'auditStatus,regOrg.auditStatus,regOrg.objId',
			'alias':'',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				$(nRow).append('<td align="center"></td>');

				//禁用复选
				if(aData.auditStatus!='01'||aData['regOrg.auditStatus']!='02'){
					$(nRow).find('input:checkbox').attr("disabled","disabled");
				}

				//附件处理
				$(nRow).find("td[name=file]").html('<span><a href="javascript:void(0);">报名附件</a></span>').find("a").click(function(){
					requirementRegList.downLoadFile(aData.file);
				});

				//审核
				if(aData.auditStatus=='01'&& aData['regOrg.auditStatus']=='02'){
					//审核
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="auditReg"><span>审核报名</span></a>').find("a[name=auditReg]").click(function(){
						$.epsDialog({
							title:"审核报名信息",
							url:$("#initPath").val()+"/RequirementRegController.do?method=toRequirementRegAudit&objId="+aData.objId
						});
					});
				}else if(aData.auditStatus=='01'&& aData['regOrg.auditStatus']=='01'){
					//设置返回路径
					$("#returnUrl").val($("#initPath").val()+"/RequirementRegController.do?method=list&requirement.objId="+$("#requirementId").val());
					
					//先审核机构审核
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="auditOrg"><span>审核机构</span></a>').find("a[name=auditOrg]").click(function(){
						$("#conBody").loadPage($("#initPath").val()+"/OrgInfoController.do?method=toAuditOrgInfo&orgInfoId="+aData["regOrg.objId"] );
					});
				}

				//查看
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="view"><span>查看</span></a>').find("a[name=view]").click(function(){
					$.epsDialog({
						title:"查看报名信息",
						url:$("#initPath").val()+"/RequirementRegController.do?method=toRequirementRegDetail&objId="+aData.objId
					});
				});
				
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/RequirementRegController.do?method=list&requirement.objId="+$("#requirementId").val()
		});
	}else{
		requirementRegList.oTable.fnDraw();
	}
}

$(document).ready(function(){

	$("#returnUrl").val($("#initPath").val()+"/RequirementController.do?method=toRequirementManagerList");

	//初始化tabs
	$('#epsTabs').tabs({}); 
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				//审核通过
			$(requirementRegList.oTable.dataTableSettings).attr("params",{'auditStatus':'02'});
		}else if(ui.index==1){			//待审核
			$(requirementRegList.oTable.dataTableSettings).attr("params",{'auditStatus':'01'});
		}
		requirementRegList.getRequirementRegList();
	});

	//第一次要手动加载
	requirementRegList.getRequirementRegList({"auditStatus":'02'})
})

</script>
