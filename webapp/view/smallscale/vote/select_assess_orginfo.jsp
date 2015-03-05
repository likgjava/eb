<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<div class="conSearch">
	<form id="BuyerChargeDepartmentForm" >
		<input type="hidden" id="Hname" value="${param.Hname}"/>
		<input type="hidden" id="Hid" value="${param.Hid}"/>
		<ul>
			<li>
				<label for="orgName">单位名称：</label>
				<input type="text" name="orgName" id="orgName" maxlength="50" size="60" />
				<input type="hidden" name="orgName_op" value="like" />
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span>查询</span></button>
			</li>
		</ul>
	</form>
</div>

<table class="frontTableList" id="buyerSuperList">
		<thead>
			<tr>
				<th class="left">组织机构代码</th>
				<th class="left omission">机构名称</th>
				<th class="center">联系人</th>
				<th class="date center">生效日期</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
</table>

<div class="conOperation">
	<button id="close" type="button"  class="largeBtn" ><span><spring:message code="globe.close"/></span></button>
	<button id="clear" type="button"  class="largeBtn" ><span>清除</span></button>
</div>

<script>
var BuyerChargeDepartmentList={};

$(document).ready(function(){
	var params = {};
	params = {"useStatus":"01","isOff":"1"};
	
	BuyerChargeDepartmentList.oTable = $('#buyerSuperList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgCode,orgName,user.emp.name,validTime',
		'hiddenColumns':'isOff,useStatus,agencyId,buyerId,supplierId,logo,descCn',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			BuyerChargeDepartmentList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).append("<td align='center'><a name='selectOrg' href='javascript:void(0);'>选择</a></td>").find('a[name=selectOrg]').click(function(){
				$("#"+$("#Hname").val()).val(aData.orgName);
				$("#"+$("#Hid").val()).val(aData.objId);

				//加载图片
				var pictureUrl = $("#initPath").val()+"/AttachmentController.do?method=showImg&objId="+aData.logo;
				$("#newPreview").html('<img src="'+pictureUrl+'" width="200px" height="175px">');//替换图片
				$("input[id=picture]").val(aData.logo);//回填id 
				$("#thingComment").val(aData.brandDesc);
				
				$('.epsDialogClose').trigger('click');
			});
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/OrgInfoController.do?method=list",
		"params":params,
		'searchZone':'BuyerChargeDepartmentForm'
	});
	
	//查询
	$("#query").click(function(){
		BuyerChargeDepartmentList.oTable.fnDraw();
	})
	
	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
	
	//清空
	$("#clear").click(function(){
		$("#"+$("#Hname").val()).val(null);
		$("#"+$("#Hid").val()).val(null);

		$("#newPreview").html('<img src="'+$("#initPath").val()+'/view/resource/skin/goods/img/brand_add.gif" width="200px" height="175px">');//替换图片
		$("input[id=picture]").val(null);//回填id
		$("#thingComment").val(null);
		$('.epsDialogClose').trigger('click');
	})
})
</script>
