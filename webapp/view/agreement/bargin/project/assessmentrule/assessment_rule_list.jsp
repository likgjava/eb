<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript">
var AssessmentRuleList={};  //定义文件全局变量 处理方法名重复问题
AssessmentRuleList.oTable;	

//删除
AssessmentRuleList.delAssessmentRule = function(){					
	var AssessmentRuleIds = $("#AssessmentRuleList").dtSelectArray();
	if(AssessmentRuleIds.length<=0){alert("请至少选择一行数据！");return;}
	if(confirm("确定删除!")){
		$.getJSON($('#initPath').val()+'/AssessmentRuleController.do?method=remove',
				{
					"objId":AssessmentRuleIds
				},
				function(json){
					if(json.success){
						alert(json.result);
						AssessmentRuleList.oTable.fnDraw();	//刷新
					}
				}
		);
	}
}
AssessmentRuleList.getList = function(){
	if(!AssessmentRuleList.oTable){
		//加载列表
		AssessmentRuleList.oTable=$('#AssessmentRuleList').dataTable( {   
			'searchZone':'AssessSearchForm',
			'singleSelect':false,	
			'checkbox':true,		
			'queryColumns':'name,categoryName,assessmentFile.viewName,createUser.emp.name',
			'hiddenColumns':'assessmentFile.objId',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {

				//下载链接 
				$(nRow).find("td[name=assessmentFile.viewName]").html('<a href="AttachmentController.do?method=downLoadFile&objId='+aData["assessmentFile.objId"]+'">'+aData["assessmentFile.viewName"]+'</a>');

				$(nRow).append('<td align="center"><a name="modify" href="javascript:void(0);" type="alink"><span>修改</span></a></td>')//添加修改按钮
				$(nRow).find('a[name=modify]').click(function(){
					AssessmentRuleList.createOrUpdate( aData.objId );
				})
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/AssessmentRuleController.do?method=list"
		});
	}else{
		AssessmentRuleList.oTable.fnDraw();
	}
}

//新增或更新
AssessmentRuleList.createOrUpdate = function( id ){
	$('#conBody').loadPage( $('#initPath').val()+"/AssessmentRuleController.do?method=toCreateOrUpdateRule&objId="+(id==null?"":id) );
}

$(document).ready(function(){

	//返回的路径
	$("#returnUrl").val($("#initPath").val()+"/AssessmentRuleController.do");
	//加载列表
	AssessmentRuleList.getList();
});
</script>

<div>

<!-- 搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="AssessSearchForm" >
		<ul>
			<li >
				<label for="goodsClassNames">品目名称：</label>
				<input type="text" name="categoryName">
				<input type="hidden" name="categoryName_op" value="like">
			</li>
		    <li class="operationBtnDiv">
		        <button id="brandSearch" type="button" onclick="AssessmentRuleList.getList()"><span>查询</span></button>
		    </li>
	  </ul>
	 </form>
</div>

<div class="operationBtnDiv r">
	<button id="addAssessmentRule" type="button" onclick="AssessmentRuleList.createOrUpdate();"><span>新增规则</span></button>
	<button id="delAssessmentRule" type="button" onclick="AssessmentRuleList.delAssessmentRule();"><span>删除规则</span></button>
</div>

<table class="frontTableList" id="AssessmentRuleList">
      <thead>
        <tr>
          <th class="left">规则名称</th>
          <th class="center">规则品目</th>
          <th class="left">规则文件</th>
          <th class="center">创建人</th>
          <th class="operation">操作</th>
        </tr>
      </thead>
      <tbody>
      </tbody>
</table>

</div>