<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">
var FtlTemplateList={};
FtlTemplateList.oTable;	

// 生成列表中每条数据的操作：根据Tab不同，生成“取消、确定”和“查看”超链接
FtlTemplateList.getOperatorStr=function(){	
	var operatorStr = '<td class="operation">';
	operatorStr += '<a href="javascript:void(0);" name="edit" title="修改" >修改</a>';
	operatorStr += '<a href="javascript:void(0);" name="delete" title="删除" >删除</a>';
	operatorStr += '</td>';
	return operatorStr;
}

//获取列表数据，如果列表没有，则创建，否则刷新数据
FtlTemplateList.getTableList = function() {
	if(!FtlTemplateList.oTable) {
		//专家信息列表
		FtlTemplateList.oTable=$('#FtlTemplateList').dataTable( {
			'searchZone':'FtlTemplateListForm',
			'singleSelect':true,
			'checkbox':false,
			'queryColumns':'ftlName,ftlType,createTime',
			'alias':'ftlName,ftlTypeCN,createTime',
			'hiddenColumns':'objId,ftlPath',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
				FtlTemplateList.oTable.oSettings=oSettings;
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				   
				//添加按钮
				$(nRow).append(FtlTemplateList.getOperatorStr()).find("a[name=edit]").click(function(){
					$('#conBody').loadPage($("#initPath").val()+"/FtlTemplateController.do?method=toCreateOrUpdate&objId="+aData['objId']);
				}).end().find("a[name=delete]").click(function(){
					if(window.confirm('确认要删除该模板吗？')){
						$.getJSON($('#initPath').val()+'/FtlTemplateController.do?method=deleteFtlTemplate',{objId:aData['objId'],path:aData['ftlPath']},function(json){
							if(json.result)alert(json.result);if(json.failure){return};
							FtlTemplateList.getTableList();
						});
					}
				});

				$(nRow).find("td[name=createTime]").empty().append(aData['createTime'].substring(0,16));
				return nRow;
			},
			'params':{},
			"sAjaxSource": $('#initPath').val()+'/FtlTemplateController.do?method=list'
		});
	}else {
		$(FtlTemplateList.oTable.dataTableSettings).attr("params",{});
		FtlTemplateList.oTable.fnDraw();
	}
}


$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val($('#initPath').val()+"/FtlTemplateController.do")
	
	//加载数据
	FtlTemplateList.getTableList();
	
	// 搜索
	$('#ftlTemplateSearchBtn').click(function(){
		FtlTemplateList.getTableList();
	});

	// 新增模板
	$('#addFtlTemplateBtn').click(function(){
		$('#conBody').loadPage($("#initPath").val()+"/FtlTemplateController.do?method=toCreateOrUpdate");
	});
});

</script>

<!-- 查询条件 -->
<div class="conSearch">
	<form id="FtlTemplateListForm">
    <h4><span>搜索</span></h4>
    <ul>
      <li>
        <label>模板名称：</label>
        <input type="text"  name="ftlName" value=""/>
        <input type="hidden"  name="ftlName_op" value="like"/>
      </li>
      <li>
      	<label>模板类型：</label>
         <html:select selectedValue="0" styleClass="required" id="ftlType" name="ftlType" code="ftlTemplateType"><html:option value="">-模板类型-</html:option></html:select>
      </li>
      <li class="operationBtnDiv">
        	<button id="ftlTemplateSearchBtn" type="button"><span>查询</span></button>
      </li>
    </ul>
    </form>
</div>
<!-- end 导航显示 -->
<div class="formTips attention">
	<ul>
		<li>
			<em id="attentionAdd">新增模板请点击
			<span class="sysicon siAdd"><a id="addFtlTemplateBtn" href="javascript:void(0);"><strong>新增模板</strong></a></span>
			</em>
		</li>
	</ul>
</div>

 <table class="frontTableList" id="FtlTemplateList">
      <thead>
      	<tr>
          <th>模板名称</th>
          <th>模板类型</th>
          <th class="center date">制作时间</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
        