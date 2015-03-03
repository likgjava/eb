<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">
var FtlTemplatePropertyList={};
FtlTemplatePropertyList.oTable;	

// 生成列表中每条数据的操作：根据Tab不同，生成“取消、确定”和“查看”超链接
FtlTemplatePropertyList.getOperatorStr=function(){	
	var operatorStr = '<td class="operation">';
	operatorStr += '<a href="javascript:void(0);" name="edit" title="修改" >修改</a>';
	operatorStr += '<a href="javascript:void(0);" name="delete" title="删除" >删除</a>';
	operatorStr += '</td>';
	return operatorStr;
}

//获取列表数据，如果列表没有，则创建，否则刷新数据
FtlTemplatePropertyList.getTableList = function() {
	if(!FtlTemplatePropertyList.oTable) {
		//专家信息列表
		FtlTemplatePropertyList.oTable=$('#FtlTemplatePropertyList').dataTable( {
			'searchZone':'FtlTemplatePropertyListForm',
			'singleSelect':true,
			'checkbox':false,
			'queryColumns':'ftlType,propertyTitle',
			'alias':'ftlTypeCN,propertyTitle',
			'hiddenColumns':'objId,sort',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
				FtlTemplatePropertyList.oTable.oSettings=oSettings;
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				   
				//添加按钮
				$(nRow).append(FtlTemplatePropertyList.getOperatorStr()).find("a[name=view]").click(function(){
					$.epsDialog({
						title:"教育信息",
						height:300, 
						width:500, 
						url:$("#initPath").val()+"/view/smallscale/expert/education_detil.jsp?objId="+aData['objId']
			
					})
					return false;
				}).end().find("a[name=edit]").click(function(){
					$('#conBody').loadPage($("#initPath").val()+"/FtlTemplatePropertyController.do?method=toCreateOrUpdate&objId="+aData['objId']);
				}).end().find("a[name=delete]").click(function(){
					if(window.confirm('确认要删除该属性吗？')){
						$.getJSON($('#initPath').val()+'/FtlTemplatePropertyController.do?method=remove',{objId:aData['objId'],path:aData['ftlPath']},function(json){
							if(json.result)alert(json.result);if(json.failure){return};
							FtlTemplatePropertyList.getTableList();
						});
					}
				});

				return nRow;
			},
			'params':{},
			"sAjaxSource": $('#initPath').val()+'/FtlTemplatePropertyController.do?method=list&order=sort'
		});
	}else {
		$(FtlTemplatePropertyList.oTable.dataTableSettings).attr("params",{});
		FtlTemplatePropertyList.oTable.fnDraw();
	}
}


$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val($('#initPath').val()+"/FtlTemplatePropertyController.do")
	
	//加载数据
	FtlTemplatePropertyList.getTableList();
	
	// 搜索
	$('#ftlTemplatePropertySearchBtn').click(function(){
		FtlTemplatePropertyList.getTableList();
	});

	// 新增模板
	$('#addFtlTemplatePropertyBtn').click(function(){
		$('#conBody').loadPage($("#initPath").val()+"/FtlTemplatePropertyController.do?method=toCreateOrUpdate");
	});
});

</script>

<!-- 查询条件 -->
<div class="conSearch">
	<form id="FtlTemplatePropertyListForm">
    <h4><span>搜索</span></h4>
    <ul>
      <li>
        <label>属性名称：</label>
        <input type="text"  name="propertyTitle" value=""/>
        
        <input type="hidden"  name="propertyTitle_op" value="like"/>
      </li>
      <li>
      	<label>属性分类：</label>
         <html:select selectedValue="0" styleClass="required" id="ftlType" name="ftlType" code="ftlTemplateType"><html:option value="">-模板类型-</html:option></html:select>
      </li>
      <li class="operationBtnDiv">
        	<button id="ftlTemplatePropertySearchBtn" type="button"><span>查询</span></button>
      </li>
    </ul>
    </form>
</div>
<!-- end 导航显示 -->
<div class="formTips attention">
	<ul>
		<li>
			<em id="attentionAdd">新增模板属性请点击
			<span class="sysicon siAdd"><a id="addFtlTemplatePropertyBtn" href="javascript:void(0);"><strong>新增模板属性</strong></a></span>
			</em>
		</li>
	</ul>
</div>

 <table class="frontTableList" id="FtlTemplatePropertyList">
      <thead>
      	<tr>
          <th>属性分类</th>
          <th>属性名称</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
        