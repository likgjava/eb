<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">
var ExpertInfoList={};
ExpertInfoList.oTable;	
ExpertInfoList.currentTabID="auditStatus_01"; //当前Tab的ID

// 生成列表中每条数据的操作：根据Tab不同，生成“取消、确定”和“查看”超链接
ExpertInfoList.getOperatorStr=function(auditStatus){	
	var operatorStr = '<td class="operation">';
	operatorStr += '<a href="javascript:void(0);" name="view" title="查看">查看</a>';
	if(ExpertInfoList.currentTabID.replace("auditStatus_","")!="02"){
		operatorStr += '<a href="javascript:void(0);" name="audit" title="审核">审核</a>';
	}
	operatorStr += '</td>';
	return operatorStr;
}

//获取列表数据，如果列表没有，则创建，否则刷新数据
ExpertInfoList.getTableList = function() {
	if(!ExpertInfoList.oTable) {
		//专家信息列表
		ExpertInfoList.oTable=$('#ExpertInfoList').dataTable( {
			'searchZone':'ExpertInfoListForm',
			'singleSelect':true,
			'checkbox':false,
			'queryColumns':'expertInfo.name,graduateSchool,degree.dicName,speciality.dicName,graduateDate',
			'alias':'expertInfo.name,graduateSchool,degree.dicName,speciality.dicName,graduateDate',
			'hiddenColumns':'objId,expertInfo.objId,auditStatus',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
				ExpertInfoList.oTable.oSettings=oSettings;
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				   
				//添加按钮
				$(nRow).append(ExpertInfoList.getOperatorStr(ExpertInfoList.currentTabID.replace("auditStatus_",""))).find("a[name=view]").click(function(){
					$.epsDialog({
						title:"教育信息",
						height:300, 
						width:500, 
						url:$("#initPath").val()+"/view/smallscale/expert/education_detil.jsp?objId="+aData['objId']
			
					})
					return false;
				}).end().find("a[name=audit]").click(function(){
					$('#conBody').loadPage($("#initPath").val()+"/view/smallscale/expert/audit_education_form.jsp?objId="+aData['objId']);
				});
				$(nRow).find("td[name=graduateDate]").empty().append(aData['graduateDate'].substring(0,16));
				return nRow;
			},
			'params':{'auditStatus':ExpertInfoList.currentTabID.replace("auditStatus_","")},
			"sAjaxSource": $('#initPath').val()+'/EducationController.do?method=list'
		});
	}else {
		$(ExpertInfoList.oTable.dataTableSettings).attr("params",{
			'auditStatus':ExpertInfoList.currentTabID.replace("auditStatus_","")
		});
		ExpertInfoList.oTable.fnDraw();
	}
}


$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val($('#initPath').val()+"/view/smallscale/expert/audit_education_list.jsp")
	
	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			ExpertInfoList.currentTabID = ui.tab.id;
			$("#currentTab").val(ui.index); //当前tab的index
			ExpertInfoList.getTableList();
		}
	});
	//指定某一个tab被选中，默认值为0
	$tabs.tabs('select', parseInt($("#currentTab").val()));
	//加载第一个tab数据
	if($("#currentTab").val() == "0"){
		ExpertInfoList.getTableList();
	}
	
	// 搜索
	$('#expertInfoSearchBtn').click(function(){
		ExpertInfoList.getTableList();
	});
});

</script>

<!-- 查询条件 -->
<div class="conSearch">
	<form id="ExpertInfoListForm">
    <h4><span>搜索</span></h4>
    <ul>
      <li>
        <label  for="input01">专家姓名：</label>
        <input type="text" id="expertInfo.name" name="expertInfo.name" value=""/>
        <input type="hidden" id="expertInfo.name_op" name="expertInfo.name_op" value="like"/>
      </li>
      <li>
        <label  for="input01">毕业院校：</label>
        <input type="text" id="graduateSchool" name="graduateSchool" value=""/>
        <input type="hidden" id="graduateSchool_op" name="graduateSchool_op" value="like"/>
      </li>
      <li>
        <label  for="input01">所学专业：</label>
        <input type="text" id="speciality" name="speciality" value=""/>
        <input type="hidden" id="speciality_op" name="speciality_op" value="like"/>
      </li>
      <li class="operationBtnDiv">
        	<button id="expertInfoSearchBtn" type="button"><span>查询</span></button>
      </li>
    </ul>
    </form>
</div>


<!-- Tab页 -->
<div id="epsTabs" class="">
  <ul>
    <li>
      <a href="#expertInfoView" id = "auditStatus_01"><span>待审核</span></a>
    </li>
    <li>
      <a href="#expertInfoView" id = "auditStatus_02"><span>已通过</span></a>
    </li>
  </ul>
  
  <div id="expertInfoView">
    <!-- 订单列表 -->
    <table class="frontTableList" id="ExpertInfoList">
      <thead>
      	<tr>
      	  <th>专家姓名</th>
          <th>毕业院校</th>
          <th>学历</th>
          <th>所学专业</th>
          <th class="center date">毕业时间</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
        