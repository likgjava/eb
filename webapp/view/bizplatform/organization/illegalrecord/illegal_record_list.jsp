<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript">
var IllegalRecList={};  //定义文件全局变量 处理方法名重复问题
IllegalRecList.oTable;	

//删除
IllegalRecList.delIllegalRec = function(){					
	var IllegalRecIds = $("#IllegalRecList").dtSelects();
	if(IllegalRecIds.length<=0){alert("请至少选择一行数据！");return;}
	if(confirm("确定删除!")){
		$.getJSON($('#initPath').val()+'/IllegalRecController.do?method=remove',
				{
					"objId":IllegalRecIds.split(',')
				},
				function(json){
					if(json.success){
						alert(json.result);
						IllegalRecList.oTable.fnDraw();	//刷新
					}
				}
		);
	}
}

//新增或者修改
IllegalRecList.createOrUpdate = function(id){
	$('#conBody').loadPage($('#initPath').val()+"/IllegalRecController.do?method=toCreateOrUpdateIllegalRec&objId="+id);
}

$(document).ready(function(){

	//返回路径
	$("#returnUrl").val($("#initPath").val()+"/IllegalRecController.do");
	
	//加载列表
	IllegalRecList.oTable=$('#IllegalRecList').dataTable( {   
		'singleSelect':false,	
		'checkbox':true,		
		'queryColumns':'org.orgName,title,reason,isShow,createTime',
		//'alias':'name,proportion,typeCN',
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
		$(nRow).append('<td align="center"><a href="javascript:void(0);" type="alink"><span>修改</span></a></td>')//添加修改按钮
			$(nRow).find('a').click(function(){
				IllegalRecList.createOrUpdate(aData.objId);
			})
			
			//中文简单处理
			$(nRow).find("td[name=isShow]").html( aData.isShow =='true'?'是':'否' );
			
			return nRow;
		},
		"sAjaxSource": $('#initPath').val()+"/IllegalRecController.do?method=list"
	});
	
});
</script>

<div>
	<div class="operationBtnDiv r">
		<button id="addIllegalRec" type="button" onclick="IllegalRecList.createOrUpdate('');"><span>新增记录</span></button>
		<button id="delIllegalRec" onclick="IllegalRecList.delIllegalRec();"><span>删除指标</span></button>
	</div>

<table class="frontTableList" id="IllegalRecList">
      <thead>
        <tr>
          <th class="left">违规机构</th>
          <th class="center">标题</th>
          <th class="left">违规原因</th>
          <th class="center">是否显示</th>
          <th class="center">创建时间</th>
          <th class="operation">操作</th>
        </tr>
      </thead>
      <tbody>
      </tbody>
</table>
</div>