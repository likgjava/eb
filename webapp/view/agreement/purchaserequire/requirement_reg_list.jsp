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

<div id="epsTabs">
  		<div id="requirementReg">
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

//取得列表
requirementRegList.getRequirementRegList = function(param){
	if(null==requirementRegList.oTable){
		requirementRegList.oTable = $('#requirementRegList').dataTable({   
			"params":param,
			'searchZone':'requirementRegSearchForm',
			'singleSelect':true,	
			'checkbox':false,		
			'queryColumns':'regOrg.orgName,linkMen,linkTel,createTime,file',
			'hiddenColumns':'auditStatus,regOrg.auditStatus,regOrg.objId',
			'alias':'',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				$(nRow).append('<td align="center"></td>');

				//附件处理
				$(nRow).find("td[name=file]").html('<span><a href="javascript:void(0);">报名附件</a></span>').find("a").click(function(){
					requirementRegList.downLoadFile(aData.file);
				});

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

	//第一次要手动加载
	requirementRegList.getRequirementRegList({"auditStatus":'02'})
})

</script>
