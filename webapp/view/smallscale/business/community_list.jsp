<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript">
var communityList={};  //定义文件全局变量 处理方法名重复问题
communityList.oTable;	

//删除
communityList.delCommunity = function(){					
	var communityIds = $("#communityList").dtSelects();
	if(communityIds.length<=0){alert("请至少选择一行数据！");return;}
	if(confirm("确定删除!")){
		$.getJSON($('#initPath').val()+'/CommunityController.do?method=delCommunity',
				{"communityIds":communityIds},
				function(json){
					if(json.success){
						alert(json.result);
						communityList.oTable.fnDraw();	//刷新
					}
				}
		);
	}
}

//获得列表
communityList.getList = function(){
	if(communityList.oTable == null){
		//加载列表
		communityList.oTable=$('#communityList').dataTable( {   
			'searchZone':'communitySearchForm',
			'singleSelect':false,	
			'checkbox':true,		
			'queryColumns':'picture,communityName,tenderCategorys,isDisplay,orgInfo.orgName',
			//'alias':'name,proportion,typeCN',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {

				//图片
				$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["picture"]+'" CURSOR="hand" width="60px" height="60px"/>');
			
				$(nRow).append('<td align="center"><a href="javascript:void(0);" type="alink"><span>修改</span></a></td>')//添加修改按钮
				
				$(nRow).find("td:eq(3)").html(aData.tenderCategorys!=null?aData.tenderCategorys.split("##||##")[1]:"");

				$(nRow).find("td:eq(4)").html(aData.isDisplay=="false"?"否":"是");
				
				$(nRow).find('a').click(function(){
					$('#conBody').loadPage($('#initPath').val()+"/CommunityController.do?method=toCreateOrUpdateCommunity&objId="+aData.objId +"&orgInfo.objId="+$("#orgId").val());
				})
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/CommunityController.do?method=list&orgInfo.objId="+$("#orgId").val()
		});
	}else{
		communityList.oTable.fnDraw();
	}
}

$(document).ready(function(){

	//返回路径
	
	if($("#returnUrl").val() != ($("#initPath").val()+"/CommunityController.do?method=toOrgComunityList") ){
		$("#returnUrl").val($("#initPath").val()+"/CommunityController.do");
	}
	
	//新增社区
	$("#addCommunity").click(function(){				
		$('#conBody').loadPage($('#initPath').val()+"/CommunityController.do?method=toCreateOrUpdateCommunity&orgInfo.objId="+$("#orgId").val());
	})
	
	//删除指标
	$("#delCommunity").click(function(){				
		communityList.delCommunity();
	})
	
	communityList.getList();
	
});
</script>

<!-- 操作机构  manager 或者 org -->
<input type="hidden" id="orgId" name="orgId" value="${param.orgId}"/>

<div>
	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="communitySearchForm" >
			<ul>
			    <li>
					<label for="communityName">社区名称：</label>
					<input type="text" name="communityName" value="">
					<input type="hidden" name="communityName_op" value="like">
			    </li>
			    <li class="operationBtnDiv">
			        <button id = "brandSearch" type="button" onclick="communityList.getList()"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
	</div>
</div>

<div>

<div class="operationBtnDiv r">
		<button id="addCommunity"><span>新增社区</span></button>
       	<button id="delCommunity"><span>删除社区</span></button>
</div>

<table class="frontTableList" id="communityList">
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