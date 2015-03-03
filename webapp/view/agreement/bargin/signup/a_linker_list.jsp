<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript">
var LinkerList={};  //定义文件全局变量 处理方法名重复问题
LinkerList.oTable;	

$(document).ready(function(){
	//加载列表
	LinkerList.oTable=$('#LinkerList').dataTable( {   
		'singleSelect':false,	
		'checkbox':false,		
		'queryColumns':'name,mobile',
		'hiddenColumns':'idCard,zipCode,address',
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			$(nRow).append('<td align="center"><a href="javascript:void(0);" name="choose"><span>选择</span></a></td>')//添加修改按钮
			$(nRow).find("a[name=choose]").click(function(){
				//回填
				$("input[name=linker]").val(aData.name);
				$("input[name=linkerTel]").val(aData.mobile);
				$("input[name=mobilePhone]").val(aData.mobile);
				$("input[name=idCard]").val(aData.idCard);
				$("input[name=address]").val(aData.address);
				$("input[name=zipCode]").val(aData.zipCode);
				$("input[name=currentEmpId]").val(aData.objId);
				$("#linkerSelect").find('.epsDialogClose').trigger('click');
			})
			return nRow;
		},
		"params":{"company.objId":$("input[name=companyId]").val()},
		"sAjaxSource": $('#initPath').val()+"/EmployeeController.do?method=list"
	});

	//关闭
	$("button[name=closeDiv]").click(function(){
		$("#linkerSelect").find('.epsDialogClose').trigger('click');
	})
});



</script>

<div>
<table class="frontTableList" id="LinkerList">
      <thead>
        <tr>
          <th class="left">联系人</th>
          <th class="center">移动电话</th>
          <th class="center">操作</th>
        </tr>
      </thead>
      <tbody>
      </tbody>
</table>
<div class="conOperation">
	<button type="button" name="closeDiv"><span>关闭</span></button>
</div>


</div>