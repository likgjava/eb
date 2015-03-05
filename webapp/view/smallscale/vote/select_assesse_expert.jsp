<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<div class="conSearch">
	<form id="expertInfoSearchForm" >
		<input type="hidden" id="Hname" value="${param.Hname}"/>
		<input type="hidden" id="Hid" value="${param.Hid}"/>
		<ul>
			<li>
				<label for="orgName">专家名称：</label>
				<input type="text" name="name" id="name" maxlength="50" size="60" />
				<input type="hidden" name="name_op" value="like" />
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span>查询</span></button>
			</li>
		</ul>
	</form>
</div>

<table class="frontTableList" id="expertInfoList">
		<thead>
			<tr>
				<th class="left">专家照片</th>
				<th class="left omission">专家名称</th>
				<th class="left omission">所属行业</th>
				<th class="left omission">资格等级</th>
				<th class="left omission">评审品目</th>
				<th class="left omission">评审区域</th>
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
var voteAssesseExpertInfo={};

$(document).ready(function(){
	var params = {};
	params = {"useStatus":"01","isOff":"1"};
	
	voteAssesseExpertInfo.oTable = $('#expertInfoList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'photo,name,belongIndustry.name,professionQualificationLevel,appCategoryValue,appDistrictValue',
		'alias':'photo,name,belongIndustry.name,professionQualificationLevelCN,appCategoryName,appDistinctName',
		'hiddenColumns':'isOff,useStatus,technicalExcellence',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			voteAssesseExpertInfo.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=photo]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["photo"]+'" style="cursor:pointer" width="40px" height="40px" id="'+aData["photo"]+'" />');
			
			$(nRow).append("<td align='center'><a name='selectExp' href='javascript:void(0);'>选择</a></td>").find('a[name=selectExp]').click(function(){
				$("#"+$("#Hname").val()).val(aData.name);
				$("#"+$("#Hid").val()).val(aData.objId);

				//加载图片
				var pictureUrl = $("#initPath").val()+"/AttachmentController.do?method=showImg&objId="+aData.photo;
				$("#newPreview").html('<img src="'+pictureUrl+'" width="200px" height="175px">');//替换图片
				$("input[id=expertPic]").val(aData.photo);//回填id 
				$("#expertComment").val(aData.technicalExcellence);

				$('.epsDialogClose').trigger('click');
			});
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/ExpertInfoController.do?method=list",
		"params":params,
		'searchZone':'expertInfoSearchForm'
	});
	
	//查询
	$("#query").click(function(){
		voteAssesseExpertInfo.oTable.fnDraw();
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
		$("input[id=expertPic]").val(null);//回填id

		$("#expertComment").val(null);		
		$('.epsDialogClose').trigger('click');
	})
})
</script>
