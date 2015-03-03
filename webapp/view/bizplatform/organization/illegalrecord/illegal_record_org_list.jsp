<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript">
var IllegalRecOrgList={};  //定义文件全局变量 处理方法名重复问题
IllegalRecOrgList.oTable;	


$(document).ready(function(){
	//返回路径
	$("#returnUrl").val($("#initPath").val()+"/IllegalRecController.do?method=toIllegalRecOrgList");
	
	//加载列表
	IllegalRecOrgList.oTable=$('#IllegalRecOrgList').dataTable( {   
		'params':{"org.objId":$("#currentOrgId").val()},
		'singleSelect':true,	
		'checkbox':false,		
		'queryColumns':'title,reason,isShow,createTime',
		//'alias':'name,proportion,typeCN',
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
		$(nRow).append('<td align="center"><a href="javascript:void(0);" type="alink"><span>查看</span></a></td>')//添加修改按钮
			$(nRow).find('a').click(function(){
				$.epsDialog({
					id:"IllegalRecDiv",
					title:"查看",
					url:$("#initPath").val()+"/IllegalRecController.do?method=toViewIllegalRec&objId="+aData.objId
				})
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
<input type="hidden" name="currentOrgId" id="currentOrgId" value="${currentOrgId}">
<table class="frontTableList" id="IllegalRecOrgList">
      <thead>
        <tr>
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