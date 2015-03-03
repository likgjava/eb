<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript">
var QuotaList={};  //定义文件全局变量 处理方法名重复问题
QuotaList.oTable;	

//删除
QuotaList.delQuota = function(){					
	var quotaIds = $("#QuotaList").dtSelects();
	if(quotaIds.length<=0){alert("请至少选择一行数据！");return;}
	if(confirm("确定删除!")){
		$.getJSON($('#initPath').val()+'/QuotaController.do?method=delQuota',
				{
					"quotaIds":quotaIds
				},
				function(json){
					if(json.success){
						alert(json.result);
						QuotaList.oTable.fnDraw();	//刷新
					}
				}
		);
	}
}

$(document).ready(function(){
	
	//新增指标
	$("#addQuota").click(function(){				
		$('#conBody').loadPage($('#initPath').val()+"/QuotaController.do?method=toCreateOrUpdateQuota");
	})
	
	//删除指标
	$("#delQuota").click(function(){				
		QuotaList.delQuota();
	})
	
	//加载列表
	QuotaList.oTable=$('#QuotaList').dataTable( {   
		'singleSelect':false,	
		'checkbox':true,		
		'queryColumns':'name,proportion,type',
		'alias':'name,proportion,typeCN',
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
		$(nRow).append('<td align="center"><a href="javascript:void(0);" type="alink"><span>修改</span></a></td>')//添加修改按钮
			$(nRow).find('a').click(function(){
				$('#conBody').loadPage($('#initPath').val()+"/QuotaController.do?method=toCreateOrUpdateQuota&objId="+aData.objId);
			})
			return nRow;
		},
		"sAjaxSource": $('#initPath').val()+"/QuotaController.do?method=list"
	});
	
});
</script>

<div>

<div class="operationBtnDiv r">
			<button id="addQuota"><span>新增指标</span></button>
        	<button id="delQuota"><span>删除指标</span></button>
</div>

<table class="frontTableList" id="QuotaList">
      <thead>
        <tr>
          <th class="left">指标名称</th>
          <th class="center">比重</th>
          <th class="center">角色类型</th>
          <th class="operation">操作</th>
        </tr>
      </thead>
      <tbody>
      </tbody>
</table>
</div>