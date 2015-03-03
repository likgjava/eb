<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script>
var BulletinList={};
BulletinList.oTable;

BulletinList.currentTabID = "tabs_valid";

//批量操作前确认
BulletinList.batConfrim = function(){
	if(BulletinList.oTable.dtSelects().length<=0){alert('请至少选择一行数据！');return false}
	if(confirm("确认执行？")){return true}else{return false}
}

//批量发布
BulletinList.batPublishBulletin = function(){
	if(!BulletinList.batConfrim()){return}
	$.getJSON($("#initPath").val()+'/BulletinAgreementController.do?method=updateStatus',{"objIds":BulletinList.oTable.dtSelects(),"relStatus":"02"},function(json){
		if(json.success){
			alert("操作成功!");
			BulletinList.getPreBulletinList();//刷新列表
		}else{
			alert("操作失败!");
		}
	})
}

//批量审核
BulletinList.batAuditBulletin = function(type){
	if(!BulletinList.batConfrim()){return}
	$.getJSON($("#initPath").val()+'/BulletinAgreementController.do?method=updateStatus',{"objIds":BulletinList.oTable.dtSelects(),"auditStatus":type},function(json){
		if(json.success){
			alert("操作成功!");
			BulletinList.getPreBulletinList();//刷新列表
		}else{
			alert("操作失败!");
		}
		$('.epsDialogClose').trigger('click');
	})
}

//批量审核
BulletinList.batAudRequirementHref = function(){
	$.epsDialog({
		title:"批量审核",
		content:"<div class='conOperation'>" +
		"<button type='button' class='largeBtn' onclick='BulletinList.batAuditBulletin(\"01\");'><span>通过</span></button>" +
		"<button type='button' class='largeBtn' onclick='BulletinList.batAuditBulletin(\"02\");'><span>不通过</span></button>" +
		"</div>",
		height:100,
		width:200
	})
}

//获取列表
BulletinList.getPreBulletinList = function (){
	var parmas = {"bullType":"00","auditStatus":'01'};
	if(BulletinList.currentTabID=="tabs_valid"){
		$("#batPublishBulletinSpan").show(); $("#batAuditBulletinSpan").hide();
		parmas.auditStatus ="01";
	}else if(BulletinList.currentTabID=="tabs_toAudit"){
		$("#batPublishBulletinSpan").hide(); $("#batAuditBulletinSpan").show();
		parmas.auditStatus ="00";
	}else{
		$("#batPublishBulletinSpan").hide(); $("#batAuditBulletinSpan").hide();
		parmas.auditStatus ="02";
	}
	if(!BulletinList.oTable) {
		// 初始化表格
		BulletinList.oTable=$('#BulletinAgreementList').dataTable( {
			'checkbox':true,		
			'searchZone':'BulletinAgreementForm',
			'singleSelect':false,
			'queryColumns':'bullTitle,createTime',
			'hiddenColumns':'useStatus,auditStatus,relStatus',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
				BulletinList.oTable.oSettings=oSettings;
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {

				//加操作按钮
				$(nRow).append('<td class="operation"></td>');

				//已发布状态不能被删除
				if(aData.relStatus=='02'){
					$(nRow).find('input:checkbox').attr("disabled","disabled");
				}

				//审核通过的集中采购预告
				if(aData.auditStatus=='01'&&aData.relStatus!='02'){
					$(nRow).find("td:last").append("<a name='publish' href='javascript:void(0);'><span>发布</span></a>");
					$(nRow).find("a[name=publish]").click(function(){
						if(confirm("确认发布！")){
							$.getJSON($('#initPath').val()+'/BulletinAgreementController.do?method=updateStatus', {objIds:aData.objId,"relStatus":"02"}, function(json){
								if(json.failure){if(json.result)alert(json.result);return;}
								BulletinList.oTable.fnDraw();
							});
						}
					})
				}
				if(aData.auditStatus=='01'&&aData.relStatus!='02'){
					$(nRow).find("td:last").append("<a name='edit' href='javascript:void(0);'><span>修改</span></a>");
					$(nRow).find("a[name=edit]").click(function(){
						$('#conBody').loadPage($('#initPath').val()+"/BulletinAgreementController.do?method=toBulletinForm&viewName=bulletinManagerForm&objId="+aData.objId);
					})
				}

				if(aData.auditStatus=='00'){
					$(nRow).find("td:last").append("<a name='audit' href='javascript:void(0);'><span>审核</span></a>");
					$(nRow).find("a[name=audit]").click(function(){
						$.epsDialog({
							id:"auditDivView",
							title:"审核采购预告",
							url:$("#initPath").val()+"/BulletinAgreementController.do?method=toPreBulletinAudit&objId="+aData.objId
						})
					})
				}

				$(nRow).find("td:last").append("<a name='delete' href='javascript:void(0);'><span>删除</span></a>");
				$(nRow).find("a[name=delete]").click(function(){
					$.getJSON($('#initPath').val()+'/BulletinController.do?method=remove', {objId:aData.objId}, function(json){
						if(json.failure){if(json.result)alert(json.result);return;}
						BulletinList.oTable.fnDraw();
					});
				})

				//查看
				$(nRow).find("td:last").append("<a name='view' href='javascript:void(0);'><span>查看</span></a>");
				$(nRow).find("a[name=view]").click(function(){
					$.epsDialog({
						id:"detailDivView",
						title:"查看采购预告",
						url:$("#initPath").val()+"/BulletinAgreementController.do?method=toPreBulletinDetail&objId="+aData.objId
					})
				})
				
				return nRow;
			},
			"params":parmas,
			"sAjaxSource": $('#initPath').val()+'/BulletinController.do?method=list'
		});
	}else{
		$(BulletinList.oTable.dataTableSettings).attr("params",parmas);
		BulletinList.oTable.fnDraw();
	}
}


$(document).ready(function(){


	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			$("#currentTab").val(ui.index); //当前tab的index
			BulletinList.currentTabID = ui.tab.id;
			BulletinList.getPreBulletinList();
		}
	});

	//tab无法触发第一个选中，所以需要手动加载一次
	if($("#currentTab").val() == "0") {
		BulletinList.getPreBulletinList();
	}

	//指定某一个tab被选中，默认值为0
	$tabs.tabs('select', parseInt($("#currentTab").val()));

	//设置返回
	$('#returnUrl').val($('#initPath').val()+'/BulletinAgreementController.do?method=toPreBulletinList');
	
	//查询
	$("#query").click(function(){
		BulletinList.oTable.fnDraw();
	});
	
	//添加
	$("#addBulletinNoticeBtn").click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/BulletinAgreementController.do?method=toBulletinForm&viewName=bulletinManagerForm");
	});
});
</script>
 
<!-- 查询条件 -->

<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="BulletinAgreementForm">
		<ul>
			<li>
				<label for="input01">采购预告名称：</label>
				<input name="bullTitle" type="text" >
				<input type="hidden" name="bullTitle_op" value="like">
			</li>
			<li>
				<label for="input01">发布状态：</label>
				<select name="relStatus" >
					<option value="">所有</option>
					<option value="02">已发布</option>
					<option value="01">未发布</option>
				</select>
			</li>
			<li class="operationBtnDiv" style="text-align:right">
		      <button type="button" id = "query"><span>查询</span></button>
		  </li>
		</ul>
    </form>
</div>

<div class="formTips attention">
<ul>
	<li>
	发布新的采购预告请点击 <span class="sysicon siAdd"><a id="addBulletinNoticeBtn" href="javascript:void(0);"><strong>起草集中采购预告</strong></a>
	</span>
	<span id="batPublishBulletinSpan">&nbsp;在此批量<a id="batPublishBulletin" href="javascript:void(0);" onclick="BulletinList.batPublishBulletin()"><strong>发布预告</strong></a></span>
	
	<span id="batAuditBulletinSpan">&nbsp;在此批量<a id="batAuditBulletin" href="javascript:void(0);" onclick="BulletinList.batAudRequirementHref()"><strong>审核预告</strong></a></span>
	
	
	</li>
</ul>
</div>

<div id="impExcel" class="pageSearch">	
<form name="impExcelToBulletin" id="impExcelToBulletin" action="<%=request.getContextPath() %>/BulletinAgreementController.do?method=saveBulletinBatch" enctype="multipart/form-data" method="post">
	<ul>
		<li>
			选择Excel文件：<input type='file' name="uploadFile" id="uploadFile"></input>
			<button id="impExcelButt" type="button" ><SPAN><SPAN>批量上传公告</SPAN></SPAN></button>
		</li>		
	</ul>
</form>
</div>

<div id="epsTabs">
		<ul>
			<li>
				<a href="#preBulletin" id = "tabs_valid" class="refreshData"><span>已发布</span></a>
			</li>
			<li>
				<a href="#preBulletin" id = "tabs_toAudit" class="refreshData"><span>待审核</span></a>
			</li>
			<li>
				<a href="#preBulletin" id = "tabs_noPass" class="refreshData"><span>未通过</span></a>
			</li>
		</ul>
  		<div id="preBulletin">
			<table class="frontTableList" id="BulletinAgreementList">
			  <thead>
			  	<tr>
			      <th >采购预告名称</th>
			      <th class="center">发布时间</th>
			      <th class="operation">操作</th>
			  	</tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>
		</div>
</div>

<script>
var bulletinForm = {};
$(document).ready(function(){
	//批量上传公告
	$('#impExcelButt').click(function(){
		var url = $('#initPath').val()+"/BulletinAgreementController.do?method=saveBulletinBatch";
		$('#impExcelToBulletin').ajaxSubmit({
			url:url,
			dataType:'json',
			success:function(json){
				if(json.result=="") {
					alert("导入公告成功！");
					$("#conBody").loadPage($("#initPath").val()+'/BulletinAgreementController.do?method=toPreBulletinList');
				}else {
					alert(ascii2native(json.result));
				}
			},
			error:function(msg){
				alert("导入文件失败！请重试");
			}
		});	
	})
})
</script>
 
