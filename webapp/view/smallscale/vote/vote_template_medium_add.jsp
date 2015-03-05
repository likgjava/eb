<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<div class="conSearch">
	<form id="templateMediumSearchForm" >
		<input type="hidden" id="Hname" value="${param.Hname}"/>
		<input type="hidden" id="Hid" value="${param.Hid}"/>
		<ul>
			<li>
				<label for="orgName">媒体名称：</label>
				<input type="text" name="orgName" id="orgName" maxlength="50" size="60" />
				<input type="hidden" name="orgName_op" value="like" />
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span>查询</span></button>
			</li>
		</ul>
	</form>
</div>

<table class="frontTableList" id="templateMediumSelectList">
	<thead>
		<tr>
			<th class="left omission">媒体编码</th>
			<th class="left omission">媒体名称</th>
			<th class="left">媒体网址</th>
			<th class="left omission">媒体地址</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<div class="conOperation">
	<button id="close" type="button"  class="largeBtn" ><span><spring:message code="globe.close"/></span></button>
</div>

<script>
var voteTemplateMediumSelect={};
voteTemplateMediumSelect.oTable;

$(document).ready(function(){
	var params = {};
	params = {"useStatus":"01","isOff":"1"};
	
	voteTemplateMediumSelect.oTable = $('#templateMediumSelectList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgCode,orgName,webUrl,distinctName',
		'hiddenColumns':'',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			voteTemplateMediumSelect.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).append("<td align='center'><a name='selectOrg' href='javascript:void(0);'>选择</a></td>").find('a[name=selectOrg]').click(function(){
				$.getJSON($('#initPath').val()+'/VoteTemplateMediumController.do?method=isHasMediumOfTemplate',{'templateId':$('#voteTemplateId').val(),'mediumId':aData.objId},function(json){
					if(json.result=='true') {
						alert("此媒体已经是合作媒体！请重新选择合作媒体！");
					}else{
						$('#templateOrgInfoId').val(aData.objId);
						templateMediumList.submit();
						$('.epsDialogClose').trigger('click');
					}
				});
				
			});
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/VoteTemplateMediumController.do?method=getMediumOrgList",
		"params":params,
		'searchZone':'templateMediumSearchForm'
	});
	
	//查询
	$("#query").click(function(){
		voteTemplateMediumSelect.oTable.fnDraw();
	})
	
	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
})
</script>
