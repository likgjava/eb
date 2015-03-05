<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<!-- 操作机构  manager 或者 org -->
<input type="hidden" id="orgId" name="orgId" value="${param.orgId}"/>

<div>

<div>
	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="communityJoinSearchForm" >
			<ul>
			    <li>
					<label for="community.communityName">社区名称：</label>
					<input type="text" name="community.communityName" value="">
					<input type="hidden" name="community.communityName_op" value="like">
			    </li>
			    <li class="operationBtnDiv">
			        <button id = "brandSearch" type="button" onclick="OrgCommunityList.getList()"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
	</div>
</div>


<div class="operationBtnDiv r">
			<button id="joinCommunity" type="button" onclick="OrgCommunityList.chooseCommunity();"><span>加入社区</span></button>
        	<button id="quiteCommunity" type="button" onclick="OrgCommunityList.minusCommunity(OrgCommunityList.oTable.dtSelectArray());"><span>脱离社区</span></button>
</div>


<table class="frontTableList" id="orgCommunityList">
      <thead>
        <tr>
          <th class="operation">社区图片</th>
          <th class="left">社区名称</th>
          <th class="left">投标品目</th>
          <th class="center">是否显示</th>
          <th class="center">创建时间</th>
          <th class="operation">操作</th>
        </tr>
      </thead>
      <tbody>
      </tbody>
</table>
</div>



<script type="text/javascript">
var OrgCommunityList={};  //定义文件全局变量 处理方法名重复问题
OrgCommunityList.oTable;	

//选择社区
OrgCommunityList.chooseCommunity = function(){
	$.epsDialog({
		id:"chooseCommunityDiv",
		title:"加入社区",
		url:$("#initPath").val()+"/view/smallscale/business/community_choose_list.jsp?orgId="+$("#orgId").val()
	})
}

//脱离社区
OrgCommunityList.minusCommunity = function(minusArray){
	if( !minusArray || minusArray.length==0 )	{alert("请选择数据！");	return}
	$.getJSON($("#initPath").val()+"/OrgCommunityController.do?method=minusOrgCommunity",{"minusIds":minusArray},function(json){
		if(json.success){
			alert("成功脱离选定社区！");
			OrgCommunityList.oTable.fnDraw();
		}
	})
}


OrgCommunityList.getList = function(minusArray){


	if(OrgCommunityList.oTable==null){
		//加载列表
		OrgCommunityList.oTable=$('#orgCommunityList').dataTable( {   
			'searchZone':'communityJoinSearchForm',
			'singleSelect':false,	
			'checkbox':true,		
			'queryColumns':'community.picture,community.communityName,community.tenderCategorys,community.isDisplay,community.createTime',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				$(nRow).find("td[name=community.picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["community.picture"]+'" CURSOR="hand" width="60px" height="60px"/>');

				var tenderCategorysCN =aData["community.tenderCategorys"].split("##||##")[1];
				
				$(nRow).find("td:eq(3)").html(tenderCategorysCN.length>50?(tenderCategorysCN.substring(0,50)+"..."):tenderCategorysCN).attr("title",tenderCategorysCN);
				
				
				
				$(nRow).find("td:eq(4)").html(aData.isDisplay=="false"?"否":"是");
				$(nRow).append('<td class="operation"><a href="javascript:void(0);" onclick="OrgCommunityList.minusCommunity([\''+aData.objId+'\']);"><span>脱离社区</span></a></td>');
			
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/OrgCommunityController.do?method=list&orgInfo.objId="+$("#orgId").val()
		});
		
	}else{
		OrgCommunityList.oTable.fnDraw();
	}
}


$(document).ready(function(){

	//获取列表
	OrgCommunityList.getList();
})

</script>