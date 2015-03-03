<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input class="hidden" id="projectId" value="${project.objId }"/>
<div class="formLayout form1Pa">
		<ul>
		<li>
			<label>招标项目名称：</label>
			<span>${project.projName }</span>
		</li>
		<li>
			<label>项目编号</label>
			<span>${project.projCode }</span>
		</li>
		<li>
			<label for="">创建人：</label>
			<span>${project.createUser.emp.name }</span>
		</li>
       </ul>
</div>

<div>
	<!-- 订单列表 -->
    <table class="frontTableList" id="suppliersignUpList">
      <thead>
      	<tr>
          <th class="left omission">供应商</th>
          <th >联系人</th>
          <th>联系电话</th>
          <th>报名时间</th>
          <th class="left">地址</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
</div>

<div class="conOperation"> 
<br>
  <button class="largeBtn" type="button" onclick="suppliersignUpConfirm.confrim('pass')"><span>确认通过</span></button>  
  <button class="largeBtn" type="button" onclick="suppliersignUpConfirm.confrim('nopass')"><span>不通过</span></button>  
  <button class="largeBtn" type="button" onclick="suppliersignUpConfirm.close()"><span>关闭</span></button>
</div>


<script type="text/javascript">
var suppliersignUpConfirm = {};
suppliersignUpConfirm.oTable;

//关闭
suppliersignUpConfirm.close = function(){
	$('.epsDialogClose').trigger('click');
}

//确认
suppliersignUpConfirm.confrim = function(auditType){
	if(suppliersignUpConfirm.oTable.dtSelects().length<=0){
		alert("请选择要确认的供应商！");
		return ;
	}
	$.getJSON($("#initPath").val()+"/SupplierSignupController.do?method=updateSignUprecordAuditStatus",{"auditType":auditType,"signIds":suppliersignUpConfirm.oTable.dtSelects()},function(json){
		if(json.success){
			alert("确认成功！");
			suppliersignUpConfirm.oTable.fnDraw();
		}
	})
}

$(document).ready(function(){

	//反拍项目列表
	suppliersignUpConfirm.oTable=$('#suppliersignUpList').dataTable( {
		'checkbox':true,
		'queryColumns':'supplierName,linker,linkerTel,applyDate,address',
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
			suppliersignUpConfirm.oTable.oSettings=oSettings;
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			return nRow;
		},
		'params':{"project.objId":$("#projectId").val(),"auditStatus":"01"},//状态为待审核
		"sAjaxSource": $('#initPath').val()+'/SignUprecordController.do?method=list'
	});
	
})
</script>