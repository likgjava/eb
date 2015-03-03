<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script>
var BulletinList={};
BulletinList.oTable;	

$(document).ready(function(){
	
	$('#returnUrl').val($('#initPath').val()+'/view/agreement/bargin/bulletin/publish_bulletin_notice_list.jsp');
	// 初始化表格
	BulletinList.oTable=$('#BulletinAgreementList').dataTable( {
		'searchZone':'BulletinAgreementForm',
		'singleSelect':true,
		'checkbox':false,
		'queryColumns':'bullTitle,createTime',
		'hiddenColumns':'relStatus,auditStatus',
		'alias':'',
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {	
			BulletinList.oTable.oSettings=oSettings;
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			$(nRow).append('<td class="operation"></td>');

			if(aData.relStatus=='00'||aData.auditStatus=='02'){
				//修改
				$(nRow).find("td:last").append('<a href="javascript:void(0);" class="checkBargainHistory" name="edit"><span>修改</span></a>')
				.find("a[name=edit]").click(function(){
					$('#conBody').loadPage($('#initPath').val()+"/BulletinAgreementController.do?method=toBulletinForm&objId="+aData.objId);
				});
				//删除
				$(nRow).find("td:last").append('<a href="javascript:void(0);" class="checkBargainHistory" name="delete"><span>删除</span></a>')
				.find("a[name=delete]").click(function(){
					if(window.confirm("确定要删除该公告吗？")){
						$.getJSON($('#initPath').val()+'/BulletinController.do?method=remove', {objId:aData.objId}, function(json){
							if(json.failure){if(json.result)alert(json.result);return;}
							$(nRow).remove();
						});
					}
				});
			}

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
		'params':{"bullType":"00"},//状态为进行中
		"sAjaxSource": $('#initPath').val()+'/BulletinAgreementController.do?method=list'
	});
	
	//查询
	$("#query").click(function(){
		BulletinList.oTable.fnDraw();
	});
	
	//添加
	$("#addBulletinNoticeBtn").click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/BulletinAgreementController.do?method=toBulletinForm");
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
			<li class="operationBtnDiv" style="text-align:right">
		      <button type="button" id = "query"><span>查询</span></button>
		  </li>
		</ul>
    </form>
</div>

<div class="formTips attention">
<ul>
	<li>发布新的采购预告请点击 <span class="sysicon siAdd"><a
		id="addBulletinNoticeBtn" href="javascript:void(0);"><strong>发布采购预告</strong></a></span>
	</li>
</ul>
</div>

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
        

 
