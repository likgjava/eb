<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="supplierId" value="${supplierId }" >


<div class="conSearch">
<form id="signUpProjectSearch">
<!-- 搜索条件 -->
	<h4><span>搜索</span></h4>
	<ul>
			<li>
				<label>招标项目名称：</label>
				<input type="text" name="project.projName">
				<input type="hidden" name="project.projName_op" value="like">
			</li>
			<li>
				<label>项目编号</label>
				<input type="text" name="project.projCode">
				<input type="hidden" name="project.projCode_op" value="like">
			</li>
		    <li class="operationBtnDiv">
		        <button type="button" onclick="mySignupProject.getSignUpProjectList()"><span>查询</span></button>
		    </li>
	  </ul>
</form>
</div>

<div id="epsTabs">
	<ul>
		<li>
			<a href="#signRecord" class="refreshData"><span>报名待审</span></a>
		</li>
		<li>
			<a href="#signRecord" class="refreshData"><span>报名通过</span></a>
		</li>
		<li>
			<a href="#signRecord" class="refreshData"><span>撤销/未通过</span></a>
		</li>
	</ul>
	<div id="signRecord">
	    <table class="frontTableList" id="signUpProjectList">
	      <thead>
	      	<tr>
	          <th class="left omission">项目名称</th>
	          <th class="left omission">项目编号</th>
	          <th >联系人</th>
	          <th>联系电话</th>
	          <th class="date">报名时间</th>
	          <th class="left">地址</th>
	          <th>操作</th>
	      	</tr>
	      </thead>
	      <tbody></tbody>
	    </table>
	</div>
</div>

<div id="cancelHtmlTemp" class="hidden">
<div class="formLayout form1Pa">
	<ul>
		<li class="formTextarea">
			<label>撤销原因：</label>
			<textarea  cols="80" name="unApplyReason"></textarea>
		</li>
	</ul>
</div>
<div class="conOperation">
	<button type="button" id="cancelConfirmBtn"><span>确认撤销</span></button>
	<button type="button" id="closeBtn"><span>关闭</span></button>
</div>
</div>
<script type="text/javascript">
var mySignupProject = {};
mySignupProject.oTable;

//撤销报名
mySignupProject.cancelSign = function(id){
	$.epsDialog({
		width:400,
		height:150,
		id:'cancelSignDiv',
		title:'撤销报名',
		content:$("#cancelHtmlTemp").html()
	});

	$("#cancelSignDiv").find("button[id=cancelConfirmBtn]").click(function(){
		var signUprecord = {};
		signUprecord.objId = id;
		signUprecord.unApplyReason = $("#cancelSignDiv").find("textarea[name=unApplyReason]").val();
		
		$.getJSON($("#initPath").val()+"/SupplierSignupController.do?method=updateSignUprecordAppllyStatus",signUprecord,function(json){
			if(json.success){
				alert("成功撤销！");
				$("#cancelSignDiv").find('.epsDialogClose').trigger('click');
			}
		})

	})
	$("#cancelSignDiv").find("button[id=closeBtn]").click(function(){$("#cancelSignDiv").find('.epsDialogClose').trigger('click'); });
}

//查看报名
mySignupProject.viewSign = function(id){
	$.epsDialog({
		title:"查看",
		id:"signUpViewDiv",
		url:$("#initPath").val()+"/SupplierSignupController.do?method=toSupplierSignUpDetail&objId="+id,
	});
}


//获取列表
mySignupProject.getSignUpProjectList  = function(){
	if(mySignupProject.oTable == null){
		mySignupProject.oTable=$('#signUpProjectList').dataTable( {
			'searchZone':'signUpProjectSearch',
			'checkbox':false,
			'queryColumns':'project.projName,project.projCode,linker,linkerTel,applyDate,address',
			'fnInitComplete':function(oSettings) {
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				$(nRow).append('<td class="operation"></td>');

				if(aData.applyStatus=="00"){
					$(nRow).find("td:last").append('<a name="cancel" href="javascript:void(0);">撤销报名</a>');
					$(nRow).find("a[name=cancel]").click(function(){
						mySignupProject.cancelSign(aData.objId);
					})	
				}
				$(nRow).find("td:last").append('<a name="view" href="javascript:void(0);">查看</a>');
				$(nRow).find("a[name=view]").click(function(){
					mySignupProject.viewSign(aData.objId);
				})	
				return nRow;
			},
			'params':{"supplier.objId":$("#supplierId").val(),"auditStatus":"01"},//状态为待审核
			"sAjaxSource": $('#initPath').val()+'/SignUprecordController.do?method=list'
		});
	}else{
		mySignupProject.oTable.fnDraw();
	}
}


$(document).ready(function(){
	//初始化tabs
	$('#epsTabs').tabs({});

	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				//待审
			$(mySignupProject.oTable.dataTableSettings).attr("params",{"supplier.objId":$("#supplierId").val(),"auditStatus":"01"});
		}else if(ui.index==1){			//完成
			$(mySignupProject.oTable.dataTableSettings).attr("params",{"supplier.objId":$("#supplierId").val(),"auditStatus":"02","applyStatus":"00"});
		}else if(ui.index==2){			//失败
			$(mySignupProject.oTable.dataTableSettings).attr("params",{"supplier.objId":$("#supplierId").val(),"auditStatus_relative":"[and:or]","auditStatus":"03","applyStatus_relative":"[and:or]","applyStatus":"01"});
		}
		mySignupProject.getSignUpProjectList();
	});
	
	//获得列表
	mySignupProject.getSignUpProjectList();
})
</script>