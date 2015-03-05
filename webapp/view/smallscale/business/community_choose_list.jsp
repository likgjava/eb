<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<input type="hidden" id="bidForRangeCode" name="bidForRangeCode" value="<%=((OrgInfo)AuthenticationHelper.getCurrentUser().getOrgInfo()).getBidForRangeCode()%>">

<input type="hidden" name="orgId" id="orgId" value="${param.orgId }">


<%@page import="com.gpcsoft.plugin.acegi.AuthenticationHelper"%>
<%@page import="com.gpcsoft.bizplatform.organization.domain.OrgInfo"%><div>


<div>
	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="communityChooseSearchForm" >
			<ul>
			    <li>
					<label for="brandCode">社区名称：</label>
					<input type="text" name="communityName" value="">
					<input type="hidden" name="communityName_op" value="like">
			    </li>
			    <li class="operationBtnDiv">
			        <button id = "brandSearch" type="button" onclick="CommunityChooseList.getList()"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
	</div>
</div>


<table class="frontTableList" id="chooseCommunityList">
      <thead>
        <tr>
          <th class="operation">社区图片</th>
          <th class="left">社区名称</th>
          <th class="left">投标品目</th>
          <th class="center">创建时间</th>
          <th class="operation">操作</th>
        </tr>
      </thead>
      <tbody>
      </tbody>
</table>

<div class="conOperation">
	<button type="button" name="confirm" onclick="CommunityChooseList.confirmJoin(CommunityChooseList.oTable.dtSelectArray());"><span>确定</span></button>
	<button type="button" id="closeCommunityChoose" onclick="CommunityChooseList.closeDiv();"><span>关闭</span></button>
</div>

</div>

<script type="text/javascript">
var CommunityChooseList={};  //定义文件全局变量 处理方法名重复问题
CommunityChooseList.oTable;	

//确认加入
CommunityChooseList.confirmJoin = function(selectArray){
		var param = {};
		param.orgId = $("#orgId").val();
		param.joinIds = selectArray;
		$.getJSON($("#initPath").val()+"OrgCommunityController.do?method=joinToCommunity",param,function(json){
			if(json.success){
				alert("加入成功！");
				CommunityChooseList.closeDiv();
			}
		})
}


//关闭
CommunityChooseList.closeDiv = function(){
	$('#closeCommunityChoose').closest('.epsDialog').find('.epsDialogClose').trigger('click');
}

CommunityChooseList.getList = function(){
	if(CommunityChooseList.oTable==null){
		//加载列表
		CommunityChooseList.oTable = $('#chooseCommunityList').dataTable({   
			'searchZone':'communityChooseSearchForm',
			'params':{"bidForRangeCode":$("#bidForRangeCode").val()},
			'singleSelect':false,	
			'checkbox':true,		
			'queryColumns':'picture,communityName,tenderCategorys,createTime',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["picture"]+'" CURSOR="hand" width="60px" height="60px"/>');


				var tenderCategorysCN = aData["tenderCategorys"]? aData["tenderCategorys"].split("##||##")[1]:"";
				
				$(nRow).find("td:eq(3)").html(tenderCategorysCN.length>50?(tenderCategorysCN.substring(0,50)+"..."):tenderCategorysCN).attr("title",tenderCategorysCN);

				$(nRow).append('<td class="operation"><a href="javascript:void(0);" onclick="CommunityChooseList.confirmJoin([\''+aData.objId+'\']);"><span>加入</span></a></td>');
				
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/CommunityController.do?method=getCommunityListForChoose&orgNotIn=true&orgNotIn_Id="+$("#orgId").val()+"&isPublic=true&company_path=<%=AuthenticationHelper.getCurrentUser().getEmp().getCompany().getPath()%>"
		});
	}else{
		CommunityChooseList.oTable.fnDraw();
	}
}


$(document).ready(function(){
	CommunityChooseList.getList();
});

</script>